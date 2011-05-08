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

import org.apache.http.HttpHost;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.log4j.Logger;

/**
 *  @author    Andrey Pudov        <syscreat@gmail.com>
 *  @version   0.00.00
 *  %name      AbstractYConnection.java
 *  %pkg       com.opentrader.market.csv.connection
 *  %date      6:54:54 PM, Apr 17, 2011
 */
public class AbstractYConnection {

    private static final Logger LOG = Logger.getLogger("opentrader");
    
    protected static int    PORT = -1;
    protected static String PARAM_SYMBOL = "s";
    private HttpHost   proxy = null;
    private HttpClient client = new DefaultHttpClient();
    private YCsvResponseHandler responseHandler = new YCsvResponseHandler();
    
    public AbstractYConnection() {
        super();
    }

    public void setProxy(YHost proxy) {
        this.proxy = new HttpHost(proxy.getServer(), proxy.getPort());
    }

    public void resetProxy() {
        this.proxy = null;
    }

    protected String getCsv(java.net.URI uri) 
            throws ClientProtocolException, 
                   java.io.IOException {
        
        if (LOG.isDebugEnabled()) {
            LOG.debug("getCsv(URI) - start");
        }

        if (this.proxy != null) {
            this.client.getParams().setParameter(
                    "http.route.default-proxy", this.proxy);
        } else {
            this.client.getParams().removeParameter(
                    "http.route.default-proxy");
        }

        HttpGet httpget = new HttpGet(uri);
        String response = this.client.execute(httpget, this.responseHandler);

        if (LOG.isDebugEnabled()) {
            LOG.debug("getCsv(URI) - String response=" + response.length());
        }

        if (LOG.isDebugEnabled()) {
            LOG.debug("getCsv(URI) - end");
        }

        return response;
    }
}
