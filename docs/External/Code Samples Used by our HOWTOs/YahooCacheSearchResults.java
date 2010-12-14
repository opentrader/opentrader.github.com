
/**
 * Yahoo! Web Services Example: cache search results
 *
 * @author Daniel Jones www.danieljones.org
 * Copyright 2007 Daniel Jones
 * 
 * This example shows how to cache the results of a Yahoo! search 
*/

import java.io.*;
import java.util.Date;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.w3c.dom.Document;

public class YahooCacheSearchResults {	
	
	public static void main(String[] args) throws Exception {
		String request = "http://api.search.yahoo.com/WebSearchService/V1/webSearch?appid=YahooDemo&query=umbrella&results=10";
      
    	/**
    	 * Replace cached result if it is older than 10 minutes.
    	 */
    	int maxAgeMins = 10;
        
    	/**
    	 * Here, we arbitrarily chose the cache file name corresponding to
    	 * this particular web services request. Typically, however, you
    	 * would have a unique filename corresponding to each variation of
    	 * web services request. So, you would need to come up with a
    	 * mapping function to map requests into filenames if you want to
    	 * use the caching with different variations of web services requests. 
    	 */
    	String fileName = "searchresults.xml";
    	
        /**
         * Initialize XML TransformerFactory
         */
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
	    Transformer transformer = transformerFactory.newTransformer();
	    transformer.setOutputProperty("indent", "yes");
		
        File xmlResultFile = new File(fileName);
        
        boolean fileExists = xmlResultFile.exists();
        if (!fileExists) {
        	/**
        	 * Search result does not exist, so perform query and save it to disk.
        	 */
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
        	
            Document response = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(rstream);
        	FileOutputStream fos;
	        fos = new FileOutputStream(xmlResultFile);
	        transformer.transform(new DOMSource(response), new StreamResult(fos));
	        String fileLocation = xmlResultFile.getAbsolutePath();
	        System.out.println("Saved search results to: " + fileLocation);
        } else {
        	/**
        	 * Search result found, so check to see how old it is
        	 */
        	String fileLocation = xmlResultFile.getAbsolutePath();
        	long lastModified = xmlResultFile.lastModified();
        	long now = new Date().getTime();
        	long diff = now - lastModified;
        	float timeSecs = diff / 1000;
        	float timeMins = timeSecs / 60;
        	System.out.println("Search results found, saved " + timeMins + " minutes ago: " + fileLocation);
        	
        	if (timeMins > maxAgeMins) {
        		/**
        		 * Search result exists, but it is too old. Perform query and save it to disk.
        		 */
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
	        	
	            Document response = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(rstream);
        		FileOutputStream fos;
		        fos = new FileOutputStream(xmlResultFile);
		        transformer.transform(new DOMSource(response), new StreamResult(fos));
		        System.out.println("Replaced search results older than 10 minutes: " + fileLocation);
        	} 
        }
	}
}
	
	