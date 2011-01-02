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

package com.external.yahooprovider;

import com.opentrader.market.feeds.DefaultExchangeProvider;
import com.opentrader.market.feeds.StockExchange;
import com.opentrader.market.feeds.Symbol;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import java.net.URL;
import java.net.URLConnection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 *  @author    Andrey Pudov        <syscreat@gmail.com>
 *  @version   0.00.00
 *  %name      YahooProvider.java
 *  %pkg       com.external.yahooprovider
 *  %date      12:00:15 PM, Nov 10, 2010
 */
public class YahooProvider implements DefaultExchangeProvider {

    private static final long serialVersionUID = 1060606745559226566L;
    private static final Logger LOG = Logger.getLogger("yahooprovider");

    private static final int CONNECT_TIME_SECONDS = 2;
    private static final int READ_TIME_SECONDS = 3;

    private static final String provider = "http://finance.yahoo.com/d/quotes.csv";

    /** Yahoo! Volume Leaders http://finance.yahoo.com/actives?e=us */
    private ArrayList<StockExchange> stocks = new ArrayList<StockExchange>(20);
    private ArrayList<Symbol> selectedSymbols = new ArrayList<Symbol>(20);

    public YahooProvider() {
        InputStream in = YahooProvider.class.getResourceAsStream(
                "/com/external/yahooprovider/resources/volumeleaders.xml"
            );

        try {
            SAXParserFactory    factory = SAXParserFactory.newInstance();
            SAXParser           saxParser = factory.newSAXParser();

            class StockHadler extends DefaultHandler {
                private ArrayList<Symbol> symbols = new ArrayList<Symbol>(10);
                private String StockExchange;

                @Override
                public void startElement(String uri, String localName,
                        String qName, Attributes attributes)
                        throws SAXException {

                    if (qName.equals("stockexchange")) {
                        for(int i = 0; i < attributes.getLength(); i++) {
                            if (attributes.getQName(i).equals("name")) {
                                StockExchange = attributes.getValue(i);
                            }
                        }
                    } else if (qName.equals("symbol")) {
                        String code = new String();
                        String description = new String();
                        
                        for(int i = 0; i < attributes.getLength(); i++) {
                            if (attributes.getQName(i).equals("code")) {
                                code = attributes.getValue(i);
                            } else if (attributes.getQName(i).equals("description")) {
                                description = attributes.getValue(i);
                            }
                        }

                        symbols.add(new YSymbol(code, description));
                        selectedSymbols.add(new YSymbol(code, description));
                    }
                }

                @Override
                public void endElement(String uri, String localName,
                        String qName)
                        throws SAXException {

                    if (qName.equals("stockexchange")) {
                        stocks.add(new YStockExchange(StockExchange, symbols));
                        symbols = new ArrayList<Symbol>(10);
                    }
                }
            }

            StockHadler handler = new StockHadler();
            saxParser.parse(in, handler);
        } catch(org.xml.sax.SAXException e) {
            LOG.warning(e.getMessage());
        } catch(javax.xml.parsers.ParserConfigurationException e) {
            LOG.warning(e.getMessage());
        } catch(java.io.IOException e) {
            LOG.warning(e.getMessage());
        }
    }

    public void connect() {
        URL                 url;
        URLConnection       urlConn;
        BufferedReader      br = null;

        try {
            url = new URL(provider);

            urlConn = url.openConnection();
            urlConn.setConnectTimeout(CONNECT_TIME_SECONDS * 1000);
            urlConn.setReadTimeout(READ_TIME_SECONDS * 1000);
            urlConn.setDoInput(true);
            urlConn.setUseCaches(false);

            br = new BufferedReader(
                    new InputStreamReader(urlConn.getInputStream()));

            String s;
            while ((s = br.readLine()) != null) {
                 //System.out.println(s);
            }
        } catch (java.net.MalformedURLException e) {
            LOG.warning(e.toString());
        } catch (java.io.IOException e) {
            LOG.warning(e.toString());
        } finally {
            try {
                br.close();
            } catch(java.io.IOException e) {
                LOG.warning(e.toString());
            }
        }

        updateSymbols();
    }

    public List<StockExchange> getStockExchanges() {

        return Collections.unmodifiableList(stocks);
    }

    public List<Symbol> getSymbols(StockExchange stock) {
        
        return stocks.get(stocks.indexOf(stock)).getSymbols();
    }

    public static void main(String[] args) {
        YahooProvider yprovider = new YahooProvider();
        yprovider.connect();
    }

    private void updateSymbols() {
        URL                 url;
        URLConnection       urlConn;
        BufferedReader      br = null;

        StringBuilder sb = new StringBuilder(20);
        int count = 0;
        for (Symbol s : selectedSymbols) {
            sb.append(s.getCode());
            if (++count != selectedSymbols.size()) {
                sb.append('+');
            }
        }

        String symbols = sb.toString();
        String options = "snl1d1t1ohgdr";

        try {
            url = new URL(provider + "?s=" + symbols +  "&f=" + options);

            urlConn = url.openConnection();
            urlConn.setConnectTimeout(CONNECT_TIME_SECONDS * 1000);
            urlConn.setReadTimeout(READ_TIME_SECONDS * 1000);
            urlConn.setDoInput(true);
            urlConn.setUseCaches(false);

            br = new BufferedReader(
                    new InputStreamReader(urlConn.getInputStream()));

            String s;
            while ((s = br.readLine()) != null) {
                 System.out.println(s);
            }
        } catch (java.net.MalformedURLException e) {
            LOG.warning(e.toString());
        } catch (java.io.IOException e) {
            LOG.warning(e.toString());
        } finally {
            try {
                br.close();
            } catch(java.io.IOException e) {
                LOG.warning(e.toString());
            }
        }
    }

}
