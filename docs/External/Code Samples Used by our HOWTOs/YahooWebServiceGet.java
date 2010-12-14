/**
 * Yahoo! Web Services Example: search API via GET
 *
 * @author Daniel Jones www.danieljones.org
 * Copyright 2007 Daniel Jones
 * 
 * This example illustrates how to perform a web service request via HTTP GET.
 * See the YahooWebServicePost example if you are interested in an example of
 * how to perform a web service request via POST.
 * 
 */

import java.io.*;

import org.apache.commons.httpclient.*;
import org.apache.commons.httpclient.methods.*;

public class YahooWebServiceGet {	
	
	public static void main(String[] args) throws Exception {
		String request = "http://api.search.yahoo.com/WebSearchService/V1/webSearch?appid=YahooDemo&query=umbrella&results=10";
            
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
        
        // Process the response from Yahoo! Web Services
        BufferedReader br = new BufferedReader(new InputStreamReader(rstream));
        String line;
        while ((line = br.readLine()) != null) {
            System.out.println(line);
        }
        br.close();
	}

}
