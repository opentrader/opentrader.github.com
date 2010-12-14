/**
 * Yahoo! Web Services Search Example: Parse returned error codes
 *
 * @author Daniel Jones www.danieljones.org
 * Copyright 2007
 * 
 * This example illustrates how to parse a Yahoo! Web Service
 * XML error response codes.
 * 
 * HTTP Status Error Codes:
 * 400  Bad request. The parameters passed to the service did not match as expected. The Message should tell you what was missing or incorrect.
 * 403 	Forbidden. You do not have permission to access this resource, or are over your rate limit.
 * 503 	Service unavailable. An internal problem prevented us from returning data to you.
 */

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.commons.httpclient.*;
import org.apache.commons.httpclient.methods.*;


public class YahooWebServiceParseErrorCodes {	
	
	public static void main(String[] args) throws Exception {
		//There is an intentional error in the request: the appid parameter name has been changed to appXd
    	String request = "http://api.search.yahoo.com/WebSearchService/V1/webSearch?appXd=YahooDemo&query=umbrella&results=10";
    	
        HttpClient client = new HttpClient();
        GetMethod method = new GetMethod(request);
        
        int statusCode = client.executeMethod(method);
        
        if (statusCode != HttpStatus.SC_OK) {
        	switch (statusCode) {
	    		case 400: {
	    			System.out.println("Bad request. The parameters passed to the service did not match as expected. The Message should tell you what was missing or incorrect."); 
	    			System.out.println("Change the parameter appcd to appid and this error message will go away.");
	    			break;
	    		}
	    		case 403: {
	    			System.out.println("Forbidden. You do not have permission to access this resource, or are over your rate limit.");
	    			break;
	    		}
	    		case 503: {
	    			System.out.println("Service unavailable. An internal problem prevented us from returning data to you.");
	    			break;
	    		}
	    		default: System.out.println("Your call to Yahoo! Web Services returned an unexpected  HTTP status of: " + statusCode);
        	}
        }
        InputStream rstream = null;
        
        // Get the response body
        rstream = method.getResponseBodyAsStream();
        
        // Process the response from Yahoo! Web Services
        BufferedReader br = new BufferedReader(new InputStreamReader(rstream));
        String line;
        while ((line = br.readLine()) != null) {
            System.out.println(line);
        }
        br.close();
	}
}