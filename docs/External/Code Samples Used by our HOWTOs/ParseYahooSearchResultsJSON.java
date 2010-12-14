/**
 * ParseYahooSearchResultsJSON.java
 * This example shows how to parse Yahoo! Web Service search results returned in JSON format. 
 *
 * @author Daniel Jones www.danieljones.org
 */

import java.io.*;

import org.json.*;

import org.apache.commons.httpclient.*;
import org.apache.commons.httpclient.methods.*;

public class ParseYahooSearchResultsJSON {

	public static void main(String[] args) throws Exception {
		String request = "http://api.search.yahoo.com/WebSearchService/V1/webSearch?appid=YahooDemo&query=umbrella&results=10&output=json";
        
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
        String jsonString = "";
        String line;
        while ((line = br.readLine()) != null) {
            jsonString += line;
        }
        br.close();
       
        // Construct a JSONObject from a source JSON text string.
        // A JSONObject is an unordered collection of name/value pairs. Its external 
        // form is a string wrapped in curly braces with colons between the names 
        // and values, and commas between the values and names.
        JSONObject jo = new JSONObject(jsonString);
        
        // A JSONArray is an ordered sequence of values. Its external form is a 
        // string wrapped in square brackets with commas between the values.
        JSONArray ja;
        
        // Get the JSONObject value associated with the search result key.
        jo = jo.getJSONObject("ResultSet");
        
        //System.out.println(jo.toString());
        
        // Get the JSONArray value associated with the Result key
        ja = jo.getJSONArray("Result");
        
        // Get the number of search results in this set
        int resultCount = ja.length();
        
        // Loop over each result and print the title, summary, and URL
        for (int i = 0; i < resultCount; i++)
        {
        	JSONObject resultObject = ja.getJSONObject(i);
        	System.out.println(resultObject.get("Title"));
        	System.out.println(resultObject.get("Summary"));
        	System.out.println(resultObject.get("Url"));
        	System.out.println("--");
        }
	}
}
