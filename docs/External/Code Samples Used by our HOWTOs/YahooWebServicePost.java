/**
 * Yahoo! Web Services Example: search API via POST
 *
 * @author Daniel Jones www.danieljones.org
 * Copyright 2007 Daniel Jones
 *
 * This example illustrates how to perform a web service request via HTTP POST.
 * See the YahooWebServiceGet example if you want to include all named parameters 
 * in the URL as a GET request.
 */

import java.io.*;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.PostMethod;

public class YahooWebServicePost {	
	
	public static void main(String[] args) throws Exception {
    	String request = "http://api.search.yahoo.com/WebSearchService/V1/webSearch";
        
        HttpClient client = new HttpClient();
        PostMethod method = new PostMethod(request);
        
        method.addParameter("appid","YahooDemo");
        method.addParameter("query","umbrella");
        method.addParameter("results","10");
        
        // Send POST request
        int statusCode = client.executeMethod(method);
        
        if (statusCode != HttpStatus.SC_OK) {
        	System.err.println("Method failed: " + method.getStatusLine());
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
