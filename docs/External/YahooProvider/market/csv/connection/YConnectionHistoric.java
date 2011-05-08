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

import org.joda.time.DateTime;

/**
 *  @author    Andrey Pudov        <syscreat@gmail.com>
 *  @version   0.00.00
 *  %name      YConnectionHistoric.java
 *  %pkg       com.opentrader.market.csv.connection
 *  %date      7:16:48 PM, Apr 17, 2011
 */
public class YConnectionHistoric extends AbstractYConnection {

    private static final Logger LOG = Logger.getLogger("opentrader");
    
    private static final String PATH = "table.csv";
    private static final String HOST = "ichart.finance.yahoo.com";
    
    private static String PARAM_START_MONTH = "a";
    private static String PARAM_START_DAY = "b";
    private static String PARAM_START_YEAR = "c";
    private static String PARAM_END_MONTH = "d";
    private static String PARAM_END_DAY = "e";
    private static String PARAM_END_YEAR = "f";
    private static String PARAM_INTERVAL = "g";
    
    private java.util.Date startDate;
    private java.util.Date endDate;
    private String interval;
    private String symbol;
    
    public YConnectionHistoric() {
        super();
    }

    public String getInterval() {
        return this.interval;
    }

    public void setInterval(String interval) {
        this.interval = interval;
    }

    public java.util.Date getStartDate() {
        return this.startDate;
    }

    public void setStartDate(java.util.Date startDate) {
        this.startDate = startDate;
    }

    public java.util.Date getEndDate() {
        return this.endDate;
    }

    public void setEndDate(java.util.Date endDate) {
        this.endDate = endDate;
    }

    public String getSymbol() {
        return this.symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    private java.net.URI getUrl() 
            throws java.net.MalformedURLException, 
                   java.net.URISyntaxException {
        
        if (LOG.isDebugEnabled()) {
            LOG.debug("getUrl() - start");
        }

        java.util.List<BasicNameValuePair> qparams 
                = new java.util.ArrayList<BasicNameValuePair>(10);

        qparams.add(new BasicNameValuePair(PARAM_SYMBOL, this.symbol));

        qparams.add(
                new BasicNameValuePair(PARAM_START_DAY,
                Integer.toString(new DateTime(startDate).getDayOfMonth())));
        qparams.add(
                new BasicNameValuePair(PARAM_START_MONTH,
                Integer.toString(new DateTime(startDate).getMonthOfYear())));
        qparams.add(
                new BasicNameValuePair(PARAM_START_YEAR,
                Integer.toString(new DateTime(startDate).getYear() + 1900)));
        
        qparams.add(
                new BasicNameValuePair(PARAM_END_DAY,
                Integer.toString(new DateTime(endDate).getDayOfMonth())));
        qparams.add(
                new BasicNameValuePair(PARAM_END_MONTH,
                Integer.toString(new DateTime(endDate).getMonthOfYear())));
        qparams.add(
                new BasicNameValuePair(PARAM_END_YEAR,
                Integer.toString(new DateTime(endDate).getYear() + 1900)));

        qparams.add(new BasicNameValuePair(PARAM_INTERVAL, this.interval));

        java.net.URI uri =
                URIUtils.createURI("http", "ichart.finance.yahoo.com", PORT, 
                                   "table.csv", 
                                   URLEncodedUtils.format(qparams,"UTF-8"), 
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
