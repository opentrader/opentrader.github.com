/**
 * Yahoo! Web Services Example: search via POST using sockets
 *
 * @author Daniel Jones www.danieljones.org
 * Copyright 2007 Daniel Jones
 * 
 * This example illustrates how to perform a web service request via HTTP POST.
 * See the YahooWebServicePost example for a similar request using a URLConnection
 * instead of sockets. 
 */

import java.net.*;
import java.io.*;

public class YahooWebServicePostSocket {	
	
	public static void main(String[] args) {
		try {
        	
			// Create POST data string
            String postdata = "appid" + "=" + URLEncoder.encode("YahooDemo", "UTF-8");
            postdata += "&" + "query" + "=" + URLEncoder.encode("umbrella", "UTF-8");
            postdata += "&" + "results" + "=" + URLEncoder.encode("10", "UTF-8");
            
            // Create a socket to the host
            String hostname = "api.search.yahoo.com";
            int port = 80;
            InetAddress addr = InetAddress.getByName(hostname);
            Socket socket = new Socket(addr, port);
            
            // Send header
            String path = "/WebSearchService/V1/webSearch";
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF8"));
            bw.write("POST " + path + " HTTP/1.0\r\n");
            bw.write("Content-Length: " + postdata.length() + "\r\n");
            bw.write("Content-Type: application/x-www-form-urlencoded\r\n");
            bw.write("\r\n");
        
            // Send POST data string
            bw.write(postdata);
            bw.flush();
        
            // Process the response from Yahoo! Web Services
            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
            bw.close();
            br.close();
        } catch (Exception e) {
        	System.out.println("Web services request failed");
        	System.err.println(e.getMessage());
            e.printStackTrace(System.err);
        }
        
		
	}

}
