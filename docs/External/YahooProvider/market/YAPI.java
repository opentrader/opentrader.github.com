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

package com.opentrader.market;

import au.com.bytecode.opencsv.CSVReader;
import org.apache.log4j.Logger;

/**
 *  @author    Andrey Pudov        <syscreat@gmail.com>
 *  @version   0.00.00
 *  %name      YAPI.java
 *  %pkg       com.opentrader.market
 *  %date      6:12:55 PM, Apr 17, 2011
 */
public class YAPI {

    private static final Logger LOG = Logger.getLogger("opentrader");
    
    public static String HIST_DAYLY = "d";
    public static String HIST_WEEKLY = "w";
    public static String HIST_MONTHLY = "m";
    public static String HIST_DIVIDEND = "v";
    private com.opentrader.market.csv.connection.YConnection connection 
            = new com.opentrader.market.csv.connection.YConnection();
    private com.opentrader.market.csv.connection.YConnectionDayTrade connectionDayTrade 
            = new com.opentrader.market.csv.connection.YConnectionDayTrade();
    private com.opentrader.market.csv.connection.YConnectionHistoric connectionHistoric 
            = new com.opentrader.market.csv.connection.YConnectionHistoric();
    private YQuoteList quoteList = new YQuoteList();
    private com.opentrader.market.csv.format.YFormat format 
            = new com.opentrader.market.csv.format.YFormat();
    private java.util.Date refreshTime;
    private String csv;

    public YAPI() {
        //
    }

    public void setProxy(com.opentrader.market.csv.connection.YHost proxy) {
        this.connection.setProxy(proxy);
        this.connectionDayTrade.setProxy(proxy);
        this.connectionHistoric.setProxy(proxy);
    }

    public void resetProxy() {
        this.connection.resetProxy();
        this.connectionHistoric.resetProxy();
        this.connectionDayTrade.resetProxy();
    }

    public void setFormat(com.opentrader.market.csv.format.YFormat format) {
        this.format = format;
    }

    public java.util.Date getRefreshTime() {
        return this.refreshTime;
    }

    public String getCsv() {
        return this.csv;
    }

    public void addTag(String code) {
        this.format.setStatusOn(code);
    }

    public void removeTag(String code) {
        this.format.setStatusOff(code);
    }

    public void addQuote(YSymbol symbol) {
        this.quoteList.add(symbol);
    }

    public void removeQuote(YSymbol symbol) {
        this.quoteList.remove(symbol);
    }

    public void removeAllQuotes() {
        this.quoteList.clear();
    }

    public void refresh() {
        if (LOG.isDebugEnabled()) {
            LOG.debug("refresh() - start");
        }

        try {
            refreshRealTime();

            refreshDayTrade();

            this.refreshTime = new java.util.Date();
        } catch (Exception e) {
            LOG.error("refresh()", e);

            System.out.println(e.getMessage());
        }

        if (LOG.isDebugEnabled()) {
            LOG.debug("refresh() - end");
        }
    }

    public void refreshRealTime()
            throws java.net.MalformedURLException, 
                   java.io.IOException, 
                   java.net.URISyntaxException {
        
        if (LOG.isDebugEnabled()) {
            LOG.debug("refreshRealTime() - start");
        }

        this.connection.setFormat(this.format.getUrlFormatParameter());
        this.connection.setSymbols(this.quoteList.getUrlSymbolParameter());

        this.csv = this.connection.getCsv();

        java.io.StringReader br = new java.io.StringReader(this.csv);
        CSVReader reader = new CSVReader(br);

        java.util.List<String[]> csvdata = reader.readAll();

        java.util.List<com.opentrader.market.csv.format.YTag> enabledTags 
                = this.format.getEnabledTags();

        for (String[] csvline : csvdata) {
            YQuote quote = this.quoteList.getStockQuote(csvline[0]);
            int i = 0;
            
            for (com.opentrader.market.csv.format.YTag tag : enabledTags) {
                quote.setValue(tag, csvline[i]);
                i++;
            }
            
            if ("N/A".equals(quote.getValue(
                    com.opentrader.market.csv.format.YTag.ERROR_INDICATION))) {
                
                quote.setValid(true);
            }

        }

        if (LOG.isDebugEnabled()) {
            LOG.debug("refreshRealTime() - end");
        }
    }

    public void refreshDayTrade()
            throws java.net.MalformedURLException, 
                   java.io.IOException, 
                   java.net.URISyntaxException {
        
        if (LOG.isDebugEnabled()) {
            LOG.debug("refreshDayTrade() - start");
        }

        for (YQuote quote : this.quoteList.getQuotes()) {
            this.connectionDayTrade.setSymbol(quote.getSymbol().getCode());

            quote.setCsvDayTrade(this.connectionDayTrade.getCsv());

            java.io.StringReader br 
                    = new java.io.StringReader(quote.getCsvDayTrade());
            CSVReader reader = new CSVReader(br, ';');

            java.util.List<String[]> csvdata = reader.readAll();
            java.util.ArrayList<YTrade> dayTrades 
                    = new java.util.ArrayList<YTrade>(10);

            for (String[] csvline : csvdata) {
                YTrade trade = new YTrade();
                
                trade.setDate(csvline[0]);
                trade.setTime(csvline[1]);
                trade.setValue(new java.math.BigDecimal(csvline[2]));
                trade.setVolume(new Integer(csvline[3]));
                trade.setUnknown(new Integer(csvline[4]));
                
                dayTrades.add(trade);
            }
            quote.setDayTrades(dayTrades);
        }

        if (LOG.isDebugEnabled()) {
            LOG.debug("refreshDayTrade() - end");
        }
    }

    public YQuote getHistoric(YSymbol symbol, java.util.Date startDate, 
                              java.util.Date endDate, String interval) {
        
        if (LOG.isDebugEnabled()) {
            LOG.debug("getHistoric(YSymbol, Date, Date, String) - start");
        }
        
        YQuote quote;
        if ((quote = this.quoteList.getStockQuote(symbol)) == null) {
            quote = new YQuote(symbol);
        }

        this.connectionHistoric.setSymbol(quote.getSymbol().getCode());
        this.connectionHistoric.setStartDate(startDate);
        this.connectionHistoric.setEndDate(endDate);
        this.connectionHistoric.setInterval(interval);
        try {
            quote.setCsvHistoric(this.connectionHistoric.getCsv());

            java.io.StringReader br 
                    = new java.io.StringReader(quote.getCsvHistoric());
            CSVReader reader = new CSVReader(br);

            java.util.List<String[]> csvdata = reader.readAll();
            java.util.ArrayList<YHistoric> historics 
                    = new java.util.ArrayList<YHistoric>(10);

            for (String[] csvline : csvdata) {
                if (!"Date".equals(csvline[0])) {
                    YHistoric historic = new YHistoric();
                    
                    historic.setDate(csvline[0]);
                    historic.setOpen(new java.math.BigDecimal(csvline[1]));
                    historic.setHigh(new java.math.BigDecimal(csvline[2]));
                    historic.setLow(new java.math.BigDecimal(csvline[3]));
                    historic.setClose(new java.math.BigDecimal(csvline[4]));
                    historic.setVolume(new java.math.BigDecimal(csvline[5]));
                    historic.setAdjClose(new java.math.BigDecimal(csvline[6]));
                    
                    historics.add(historic);
                }
            }

            quote.setHistorics(historics);
        } catch (Exception e) {
            LOG.error("getHistoric(YSymbol, Date, Date, String)", e);

            System.out.println(e.getMessage());
        }

        if (LOG.isDebugEnabled()) {
            LOG.debug("getHistoric(YSymbol, Date, Date, String) - end");
        }

        return quote;
    }

    public java.util.ArrayList<YQuote> getQuotes() {
        return this.quoteList.getQuotes();
    }
}
