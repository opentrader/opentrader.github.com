package com.gain.api;

/**
 * Title:        Margin Trader Development
 * Description:
 *               WebServicesUtil provides utilities methods to connect
 *               to ASP.NET based web services via the HTTP GET Method
 * Copyright:    Copyright (c) 2001
 * Company:      Gain Capital
 * @author       Phil Cave
 * @version 1.0
 * Date          02 July 2002
 */

/* @PHILCAVE - Added File */

/**
 * Java Imports
 */
import java.io.*;
import java.net.*;
import java.util.*;

/**
 * WebServiceUtil Class
 * Provides utility functions to call ASP.NET Web Services using HTTP GET Request.
 */
public class WebServiceUtil {

    static private String className="WebServiceUtil";
    static private int RESPONSE_BUFFER_INIT_SIZE = 512;
    static private int MAX_TRYS                  = 5;
    static private int DELAY_SLEEP_MS            = 1000;

    /**
     * callWebService - calls the ASP.NET service on the specified host with the query string
     * parameters.
     * @returns XML Repsonse of the web service call or null on failure
     */
    public static String callWebService( String host,  String webService, String queryString,  String method  )
    {
      // locals
      int     tries    = 0;
      boolean bPosting = ( method.toUpperCase().compareTo("POST") == 0) ;
      String  webServiceURL;

      // work out the URL depending on the method
      if( bPosting )
        webServiceURL = host + "/" + webService;
      else
        webServiceURL = host + "/" + webService + "?" + queryString;

      EventLog.logDebug( className, "Connecting to Web Service: " + webServiceURL + " " + method  );

      do
      {
          tries++;

          try
          {
            // Construct a URL of the WebService + paramters
            URL webServiceAddress = new URL( webServiceURL );
            URLConnection serviceConnection = webServiceAddress.openConnection();

            // Set the HTTP connection parameters
            serviceConnection.setDoInput(true);
            serviceConnection.setDoOutput( bPosting );  // using output if were posting
            serviceConnection.setUseCaches (false);
            serviceConnection.setDefaultUseCaches (false);

            // Set the appropiate parameters depending on the connect method
            if( bPosting ) {
                serviceConnection.setRequestProperty ("Content-Type", "application/x-www-form-urlencoded");
                serviceConnection.setRequestProperty ("Content-Length", String.valueOf(queryString.length()) );
            } else
                serviceConnection.setRequestProperty ("Content-Type", "xml/text");

            // if we are posting the request then send the query string to webservice
            // changes the method to a POST
            if( bPosting ) {
                OutputStream out = serviceConnection.getOutputStream();
	        out.write( queryString.getBytes() );
                out.flush();
	        out.close();
            }

            // Get the Response into a string for return
            // Get the input stream reader from the connection
            BufferedReader in = new BufferedReader( new InputStreamReader( serviceConnection.getInputStream() ) );

            // Create a string buffer for the repsonse
            StringBuffer data = new StringBuffer( RESPONSE_BUFFER_INIT_SIZE );
            String line = in.readLine();
            while(  line != null ) {
               data.append( line );
               line = in.readLine();
            }
            in.close();

            // Get the data into a string format
            String response = data.toString();

            // Log the reponse and return the data
            EventLog.logDebug( className, "WebService returned data: " + response );
            return( response );
          }
          catch (Exception e)
          {
            EventLog.log( className, "Error executing Webservice URL: " + webServiceURL  );
            EventLog.logException( className, e );
            try { Thread.sleep(DELAY_SLEEP_MS ); } catch ( Exception ex ) {}
          }

      } while ( ( tries < MAX_TRYS ) );

      return null;
  }

}