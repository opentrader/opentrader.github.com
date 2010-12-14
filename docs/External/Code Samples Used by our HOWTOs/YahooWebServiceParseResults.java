/**
 * Yahoo! Web Services Example: Parse search results
 *
 * @author Daniel Jones www.danieljones.org
 * Copyright 2007
 * 
 * This example illustrates how easy it is to parse a Yahoo! Web Service
 * XML response via XPath.
 * 
 * XPath expressions are much more straight forward than navigating the DOM. 
 * Java 5 introduced the javax.xml.xpath package, an XML object-model
 * independent library for querying documents with XPath. 
 * 
 * Learn more about XPath here: 
 * http://www.ibm.com/developerworks/xml/library/x-javaxpathapi.html
 */

import java.io.InputStream;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.*;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.w3c.dom.*;

public class YahooWebServiceParseResults {	
	
	/**
	 * This example illustrates how easy it is to parse a Yahoo! Web Service XML result with XPath.
	 */
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
    	
        // Process response
        Document response = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(rstream);
        
        XPathFactory factory = XPathFactory.newInstance();
        XPath xPath=factory.newXPath();
        
        //Get all search Result nodes
        NodeList nodes = (NodeList)xPath.evaluate("/ResultSet/Result", response, XPathConstants.NODESET);
        int nodeCount = nodes.getLength();
        
        //iterate over search Result nodes
        for (int i = 0; i < nodeCount; i++) {
            //Get each xpath expression as a string
        	String title = (String)xPath.evaluate("Title", nodes.item(i), XPathConstants.STRING);
            String summary = (String)xPath.evaluate("Summary", nodes.item(i), XPathConstants.STRING);
            String url = (String)xPath.evaluate("Url", nodes.item(i), XPathConstants.STRING);
            //print out the Title, Summary, and URL for each search result
            System.out.println("Title: " + title);
            System.out.println("Summary: " + summary);
            System.out.println("URL: " + url);
            System.out.println("--");
            
        }
	}
}
