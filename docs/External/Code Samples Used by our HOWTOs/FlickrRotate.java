/**
 * Flickr Web Service API Example: Authenticate Desktop Application
 * User and Rotate Uploaded Image 
 *
 * @author Daniel Jones www.danieljones.org
 * Copyright 2007
 * 
 * This example shows how to perform the steps necessary to authenticate a 
 * Yahoo! Flickr user and use the Flickr API to rotate an image on flickr.com. 
 * The Flickr Authentication API Desktop Applications How-To can be found 
 * here: http://www.flickr.com/services/api/auth.howto.desktop.html
 *
 * Before you can use the Flickr API, you must obtain an API key at the following 
 * URL: http://www.flickr.com/services/api/keys/apply/
 * 
 * For the purposes of this example, be sure to choose the Desktop Application
 * radio button for the Authentication Type. We do not want the user
 * automatically redirected to a callback URL with the frob passed in the URL
 * as a GET argument because our desktop application's token request will fail.
 * You should choose Web Application for the Authentication Type if you want
 * the frob argument automatically passed back to the auth handler callback URL
 * for a subsequent call to flickr.auth.getToken. 
 *   
 * Now that you have an API key and at least one image uploaded to your Flickr
 * account, lets get started.  
 *  
 */


import java.math.BigInteger;
import java.security.MessageDigest;
import java.io.*;

import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class FlickrRotate {	
	
	public static void main(String[] args) throws Exception {
		/**
	 	* Use the key and secret provided when you registered your application
		* http://www.flickr.com/services/api/keys/apply/
		*/
		String key = "";
		String secret = "";
		
		/**
		 * This example assumes that you have uploaded a photo to
		 * your Flickr account.
		 * 
		 * The photo id can be found in the URL of the photo
		 * http://flickr.com/photos/xxxxxxx@N03/<use_this_photo_id>/
		 */
		String photoId = "";
		
		/**
		 * Request a frob to identify the login session. This call requires 
		 * a signature. The signature starts with your shared secret and
		 * is followed by your API key and the method name. The API key and
		 * method name are prepended by the words "api_key" and "method" as
		 * shown in the following line.
		**/
		String methodGetFrob = "flickr.auth.getFrob";
		String sig = secret + "api_key" + key + "method" + methodGetFrob;
		
		/**
		 * The API signature must be MD5 encoded and appended to the request
		 */
		String signature = MD5(sig); 
			
        String request = "http://api.flickr.com/services/rest/?method=" + methodGetFrob + "&api_key=" + key + "&api_sig=" + signature;
        System.out.println("GET frob request: " + request); 
        
        HttpClient client = new HttpClient();
        GetMethod method = new GetMethod(request);
        
        // Send GET request
        int statusCode = client.executeMethod(method);
        
        if (statusCode != HttpStatus.SC_OK) {
        	System.err.println("Method failed: " + method.getStatusLine());
        }
        
        InputStream rstream = null;
        
        // Get the response body
        rstream = method.getResponseBodyAsStream();
        
        // Retrieve the XML response to the frob request and get the frob value.
        Document response = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(rstream);
        
        String frob = null;
        
        // Check if frob is in the response
        NodeList frobResponse = response.getElementsByTagName("frob");
        Node frobNode = frobResponse.item(0);
        if (frobNode != null) {
        	frob = frobNode.getTextContent();
        	System.out.println("Successfully retrieved frob: " + frob);
        } else {
        	printFlickrError(response);
        	return;
        }
        System.out.println("Get frob XML response:");
        /**
         * Create a Flickr login link
         * http://www.flickr.com/services/auth/?api_key=[api_key]&perms=[perms]&frob=[frob]&api_sig=[api_sig]
         * We are using "write" for the perms value because we will be rotating an image. 
         */
        sig = secret + "api_key" + key + "frob" + frob + "permswrite";
        signature = MD5(sig);
        request = "http://www.flickr.com/services/auth/?api_key=" + key + "&perms=write&frob=" + frob + "&api_sig=" + signature;
        
        /**
         * Copy/paste the generated link into your favorite web browser and follow the instructions
         */
        System.out.println("Browse to the following flickr url to authenticate yourself and then press enter.");
        System.out.println(request);
        BufferedReader infile = new BufferedReader ( new InputStreamReader (System.in) );
		String line = infile.readLine();
			
		/**
		 * Get auth token using frob. Once again, a signature is required
		 * for authenticated calls to the Flickr API.  
		 */
		String methodGetToken = "flickr.auth.getToken";
		sig = secret + "api_key" + key + "frob" + frob + "method" + methodGetToken;
			signature = MD5(sig);
		request = "http://api.flickr.com/services/rest/?method=" + methodGetToken + "&api_key=" + key + "&frob=" + frob + "&api_sig=" + signature;
		System.out.println("Token request: " + request);
		
		method = new GetMethod(request);
        
        // Send GET request
        statusCode = client.executeMethod(method);
        
        if (statusCode != HttpStatus.SC_OK) {
        	System.err.println("Method failed: " + method.getStatusLine());
        }
        
        rstream = null;
        
        // Get the response body
        rstream = method.getResponseBodyAsStream();
		/**
         * Retrieve the XML response to the token request and get the token value
         */
		response = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(rstream);
	
		String token = null;
	
		// Check if token is in the response
        NodeList tokenResponse = response.getElementsByTagName("token");
        Node tokenNode = tokenResponse.item(0);
        if (tokenNode != null) {
        	token = tokenNode.getTextContent();
        	System.out.println("Successfully retrieved token: " + token);
        } else {
        	printFlickrError(response);
        	return;
        }
        
        /**
         * Now that we have authenticated with Flickr and obtained an auth
         * token with write privileges, it's time to rotate the image.
         * 
         * http://api.flickr.com/services/rest/?method=flickr.photos.transform.rotate&
         * api_key=[api_key]&photo_id=[photo_id]&degrees=180&auth_token=[auth_token]&api_sig=[api_sig]
         */
		String methodRotate = "flickr.photos.transform.rotate";
		sig = secret + "api_key" + key + "auth_token" + token + "degrees180" + "method" + methodRotate + "photo_id" + photoId;
    	signature = MD5(sig);
		
    	/**
    	 * Create POST data for rotate request. The Flickr API documentation
    	 * specifies that calls to flickr.photos.transform.rotate be made
    	 * via HTTP POST.
    	 */
        
        request = "http://api.flickr.com/services/rest/";
        
        PostMethod pmethod = new PostMethod(request);
        
        pmethod.addParameter("method", methodRotate);
        pmethod.addParameter("photo_id", photoId);
        pmethod.addParameter("degrees", "180");
        pmethod.addParameter("api_key", key);
        pmethod.addParameter("auth_token", token);
        pmethod.addParameter("api_sig", signature);
        
        // Send POST request
        statusCode = client.executeMethod(pmethod);
    
        if (statusCode != HttpStatus.SC_OK) {
        	System.err.println("Method failed: " + pmethod.getStatusLine());
        }
        
        rstream = null;
        
        // Get the response body
        rstream = pmethod.getResponseBodyAsStream();
        
        /**
         * Retrieve the XML response to the rotate request and get the XML response
         */
        response = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(rstream);
        
        String photoid = null;
		
		// Check if photoid is in the response
        NodeList rotateResponse = response.getElementsByTagName("photoid");
        Node rotateNode = rotateResponse.item(0);
        if (rotateNode != null) {
        	photoid = rotateNode.getTextContent();
        	System.out.println("Successfully flipped photo " + photoid);
            	System.out.println("Refresh the photo in your web browser and you will see it has been flipped.");
        } else {
        	printFlickrError(response);
        	return;
        }
	}
	
	/**
	 * Get the MD5 hash of a text string
	 */
	public static String MD5(String text)
	{
		String md5Text = "";
		try {
			MessageDigest digest = MessageDigest.getInstance("MD5");
			md5Text = new BigInteger(1, digest.digest((text).getBytes())).toString(16);
		} catch (Exception e) {
			System.out.println("Error in call to MD5");
		}
		
		if (md5Text.length() == 31) {
            md5Text = "0" + md5Text;
        }
		return md5Text;
	}
	
	/**
	 * Print the Flickr response error code and message
	 * @param response The XML error response Document
	 */
	public static void printFlickrError(Document response) 
	{
		NodeList error = response.getElementsByTagName("err");
    	String code = error.item(0).getAttributes().item(0).getTextContent();
    	String msg = error.item(0).getAttributes().item(1).getTextContent();
    	System.out.println("Flickr request failed with error code " + code + ", " + msg);
	}
}
