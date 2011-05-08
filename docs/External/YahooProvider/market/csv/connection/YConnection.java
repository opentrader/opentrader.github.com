/*
 * OpenTrader Trading Platform
 * The solution for online trading, technical analysis and automated trading.
 *
 * Copyright (C) 2011  Andrey Pudov
 * Andrey Pudov     <syscreat@gmail.com>
 *
 * http://opentrader.github.com/
 */

/*
 * CDDL HEADER START
 *
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright 2011 Andrey Pudov. All rights reserved.
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
 * Copyright 2011 Andrey Pudov.  All rights reserved.
 * Use is subject to license terms.
 *
 * Contributor(s):
 *
 * Portions Copyrighted 2011 Andrey Pudov.
 *
 */
package com.opentrader.market.csv.connection;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.utils.URIUtils;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.message.BasicNameValuePair;
import org.apache.log4j.Logger;

/**
 *  @author    Andrey Pudov        <syscreat@gmail.com>
 *  @version   0.00.00
 *  %name      YConnection.java
 *  %pkg       com.opentrader.market.csv.connection
 *  %date      7:06:56 PM, Apr 17, 2011
 */
public class YConnection extends AbstractYConnection {

    private static final Logger LOG = Logger.getLogger("opentrader");
    
    private static String PARAM_FORMAT = "f";
    private static String PARAM_E = "e";
    private String symbols;
    private String format;
    
    public YConnection() {
        super();
    }

    public String getSymbols() {
        return this.symbols;
    }

    public void setSymbols(String symbols) {
        this.symbols = symbols;
    }

    public String getFormat() {
        return this.format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    private java.net.URI getUrl() throws java.net.URISyntaxException {
        if (LOG.isDebugEnabled()) {
            LOG.debug("getUrl() - start");
        }

        java.util.List<BasicNameValuePair> qparams 
                = new java.util.ArrayList<BasicNameValuePair>(10);
        
        qparams.add(new BasicNameValuePair(PARAM_SYMBOL, this.symbols));
        qparams.add(new BasicNameValuePair(PARAM_FORMAT, this.format));
        qparams.add(new BasicNameValuePair(PARAM_E, ".csv"));

        java.net.URI uri =
                URIUtils.createURI("http", "quote.yahoo.com", PORT, 
                "/d/quotes.csv", URLEncodedUtils.format(qparams, "UTF-8"), 
                null);

        if (LOG.isDebugEnabled()) {
            LOG.debug("getUrl() - URI uri=" + uri);
        }

        if (LOG.isDebugEnabled()) {
            LOG.debug("getUrl() - end");
        }

        return uri;
    }

    public String getCsv() 
            throws ClientProtocolException, 
                   java.io.IOException, 
                   java.net.URISyntaxException {
        
        return getCsv(getUrl());
    }
}
