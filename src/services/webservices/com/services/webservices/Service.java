/*
 * OpenTrader Trading Platform
 * The solution for online trading, technical analysis and automated trading.
 *
 * Copyright (C) 2010  Andrey Pudov
 * Andrey Pudov     <syscreat@gmail.com>
 *
 * http://opentrader.github.com/
 */

/*
 * CDDL HEADER START
 *
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright 2010 Andrey Pudov. All rights reserved.
 *
 * The contents of this file are subject to the terms of the
 * Common Development and Distribution License (the "License").
 * You may not use this file except in compliance with the License.
 *
 * You can obtain a copy of the license at usr/src/OPENSOLARIS.LICENSE
 * or http://www.opensolaris.org/os/licensing.
 * See the License for the specific language governing permissions
 * and limitations under the License.
 *
 * When distributing Covered Code, include this CDDL HEADER in each
 * file and include the License file at usr/src/OPENSOLARIS.LICENSE.
 * If applicable, add the following below this CDDL HEADER, with the
 * fields enclosed by brackets "[]" replaced with your own identifying
 * information: Portions Copyright [yyyy] [name of copyright owner]
 *
 * CDDL HEADER END
 *
 *
 * Copyright 2010 Andrey Pudov.  All rights reserved.
 * Use is subject to license terms.
 *
 * Contributor(s):
 *
 * Portions Copyrighted 2010 Andrey Pudov.
 *
 */

package com.services.webservices;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;

import java.net.URL;
import java.net.URLConnection;

import java.util.logging.Logger;

/**
 *  @author    Andrey Pudov        <syscreat@gmail.com>
 *  @version   0.00.00
 *  %name      Service.java
 *  %pkg       com.services.webservices
 *  %date      9:52:55 AM, Sep 25, 2010
 */
public class Service {
    
    private static final Logger LOG = Logger.getLogger("opentrader");

    private static int RESPONSE_BUFFER_INIT_SIZE = 512;
    private static int MAX_TRYS                  = 3;
    private static int DELAY_SLEEP_MS            = 1000;

    public Service() {
        //
    }

    /**
     * callWebService - calls the ASP.NET service on the specified host with
     * the query string parameters.
     * @param host
     * @param webService
     * @param queryString
     * @return
     * @returns XML Repsonse of the web service call or null on failure
    */
    public String invoke(
            String host, String webService, String queryString) {

        int     tries    = 0;
        String  webServiceURL;

        // work out the URL depending on the method
        webServiceURL = host + "/" + webService;

        do {
            tries++;

            try {
                // Construct a URL of the WebServiceUtil + paramters
                URL webServiceAddress = new URL(webServiceURL);
                URLConnection serviceConnection =
                        webServiceAddress.openConnection();

                // Set the HTTP connection parameters
                serviceConnection.setDoInput(true);
                // using output if were posting
                serviceConnection.setDoOutput(true);
                serviceConnection.setUseCaches(false);
                serviceConnection.setDefaultUseCaches(false);

                // Set the appropiate parameters depending on the connect method
                serviceConnection.setRequestProperty(
                    "Content-Type", "application/x-www-form-urlencoded");
                serviceConnection.setRequestProperty(
                    "Content-Length", String.valueOf(queryString.length()));

                // if we are posting the request then send the query string
                // to webservice changes the method to a POST
                OutputStream out = serviceConnection.getOutputStream();

                out.write(queryString.getBytes());
                out.flush();
                out.close();

                // Get the Response into a string for return
                // Get the input stream reader from the connection
                BufferedReader in = new BufferedReader(
                    new InputStreamReader(serviceConnection.getInputStream()));

                // Create a string buffer for the repsonse
                StringBuilder data = new StringBuilder(
                        RESPONSE_BUFFER_INIT_SIZE);
                String line = in.readLine();

                while(line != null) {
                    data.append(line);
                    line = in.readLine();
                }

                in.close();

                // Get the data into a string format
                String response = data.toString();

                return( response );
            } catch (Exception e) {
                LOG.severe("Error executing Webservice URL: " + webServiceURL);
                LOG.severe(e.toString());
                try {
                    Thread.sleep(DELAY_SLEEP_MS);
                } catch (Exception ex) {}
            }

        } while ((tries < MAX_TRYS));

        return null;
    }

}
