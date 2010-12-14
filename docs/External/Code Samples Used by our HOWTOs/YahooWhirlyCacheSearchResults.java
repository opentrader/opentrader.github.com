
/**
 * Yahoo! Web Services Example: cache search results
 *
 * @author Daniel Jones www.danieljones.org
 * Copyright 2007 Daniel Jones
 * 
 * This example shows how to cache the results of a Yahoo! search with an in-memory cache 
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

import com.whirlycott.cache.*;

public class YahooWhirlyCacheSearchResults {	
	
	public static void main(String[] args) throws Exception {
		String request = "http://api.search.yahoo.com/WebSearchService/V1/webSearch?appid=YahooDemo&query=umbrella&results=10";
		
		// Format XML document
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
	    Transformer transformer = transformerFactory.newTransformer();
	    transformer.setOutputProperty("indent", "yes");
    	
    	//Use the cache manager to create the default cache
    	Cache c = CacheManager.getInstance().getCache();

    	for (int i=0; i<10; i++) {    		
    		//Get the object back out of the cache
        	Document cachedResponse = (Document) c.retrieve(request);
        	
        	long now = new Date().getTime();
        	
	        if (cachedResponse == null) {
	        	// Search result does not exist, so perform query and cache it
	        	System.out.println("Request " + i + ": [" + now + "]: Cache Miss");
	        	
	        	// Initialize request
	        	HttpClient client = new HttpClient();
	            GetMethod method = new GetMethod(request);
	            
	            // Send GET request
	            int statusCode = client.executeMethod(method);
	            
	            if (statusCode != HttpStatus.SC_OK) {
	            	System.err.println("Method failed: " + method.getStatusLine());
	            	continue;
	            }
	            InputStream rstream = null;
	           
	            // Get the response body
	            rstream = method.getResponseBodyAsStream();
	        	
	            // Save response as a Document object
	            cachedResponse = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(rstream);
	            
	            // Print out XML
	            transformer.transform(new DOMSource(cachedResponse), new StreamResult(System.out));
	            
	            // Store response in cache, expire after 5 seconds
	        	c.store(request, cachedResponse, 5000);
	        } else {
		        System.out.println("Request " + i + ": [" + now + "]: Cache Hit");        
		        //transformer.transform(new DOMSource(cachedResponse), new StreamResult(System.out));
	        }
	        // Wait 1 second before next request
	        Thread.sleep(1000);
    	}
    	//Shut down the cache manager
    	CacheManager.getInstance().shutdown();
	}
}