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

/**
 *  @author    Andrey Pudov        <syscreat@gmail.com>
 *  @version   0.00.00
 *  %name      Quote.java
 *  %pkg       com.opentrader.market
 *  %date      3:01:37 PM, Apr 18, 2011
 */
public class Quote {
    
    private static final java.util.logging.Logger LOG 
            = java.util.logging.Logger.getLogger("opentrader");
    
    private Symbol symbol;
    
    private static final String[] name = {
        "Ask",                              "Average Daily Volume", 
        "Ask Size",                         "Bid", 
        "Ask (Real-time)",                  "Bid (Real-time)", 
        "Book Value",                       "Bid Size", 
        "Change & Percent Change",          "Change", 
        "Commission",                       "Change (Real-time)", 
        "After Hours Change (Real-time)",   "Dividend/Share",
        "Last Trade Date",                  "Trade Date", 
        "Earnings/Share", 
        "Error Indication (returned for symbol changed / invalid)", 
        "EPS Estimate Current Year",        "EPS Estimate Next Year",
        
        "EPS Estimate Next Quarter",        //"Float Shares", 
        "Day’s Low",                        "Day’s High", 
        "52-week Low",                      "52-week High", 
        "Holdings Gain Percent",            "Annualized Gain", 
        "Holdings Gain",                    "Holdings Gain Percent (Real-time)", 
        "Holdings Gain (Real-time)",        "More Info", 
        "Order Book (Real-time)",           "Market Capitalization", 
        "Market Cap (Real-time)",           "EBITDA", 
        "Change From 52-week Low",          "Percent Change From 52-week Low", 
        "Last Trade (Real-time) With Time", "Change Percent (Real-time)", 
        
        "Last Trade Size",                  "Change From 52-week High", 
        "Percent Change From 52-week High", "Last Trade (With Time)", 
        "Last Trade (Price Only)",          "High Limit", 
        "Low Limit",                        "Day’s Range", 
        "Day’s Range (Real-time)",          "50-day Moving Average", 
        "200-day Moving Average",           "Change From 200-day Moving Average", 
        "Percent Change From 200-day Moving Average", 
        "Change From 50-day Moving Average", 
        "Percent Change From 50-day Moving Average", 
        "Name", 
        "Notes",                            "Open", 
        "Previous Close",                   "Price Paid", 
        
        "Change in Percent",                "Price/Sales", 
        "Price/Book",                       "Ex-Dividend Date", 
        "P/E Ratio",                        "Dividend Pay Date", 
        "P/E Ratio (Real-time)",            "PEG Ratio", 
        "Price/EPS Estimate Current Year",  "Price/EPS Estimate Next Year", 
        "Symbol",                           "Shares Owned", 
        "Short Ratio",                      "Last Trade Time", 
        "Trade Links",                      "Ticker Trend", 
        "1 yr Target Price",                "Volume", 
        "Holdings Value",                   "Holdings Value (Real-time)", 
        
        "52-week Range",                    "Day’s Value Change", 
        "Day’s Value Change (Real-time)",   "Stock Exchange", 
        "Dividend Yield"};
    
    private static final Class[] type = {Double.class};
    
    private String[] value;
    
    public Quote(Symbol symbol, String[] value) {
        super();
        
        this.symbol = symbol;
        this.value  = value;
    }
    
    /** Symbol */
    public String getSymbol() {
        return symbol.getCode();
    }
    
    /** Name */
    public String getName() {
        return trim(value[54]);
    }
    
    /** Stock Exchange */
    public String getStockExchange() {
        return trim(value[82]);
    }
    
    /** Last Trade (Price Only) */
    public double getLastTrade() {
        try {
            return Double.parseDouble(value[43]); 
        } catch (NumberFormatException e) {
            return -1.0d;
        }
    }
    
    /** Trade Time */
    public java.util.Date getTradeTime() {
        if (value[42].equals("\"N/A\"")) {
            return null;
        }
        
        java.text.DateFormat df; //= new java.text.SimpleDateFormat("MMM d");
        
        if (value[42].indexOf(':') != -1) {
            df = new java.text.SimpleDateFormat("hh:mma");
        } else {
            df = new java.text.SimpleDateFormat("MMM d");
        }
        
        try {
            System.err.println("val:" + value[42]);
            return df.parse(value[42].substring(1, value[42].indexOf('-') - 1));
        } catch (java.text.ParseException e) {
            System.err.println("val:" + value[42]);
            return null;
        }
    }
    
    /** Change (Real-time) */
    public double getChange() {
        try {
            return Double.parseDouble(trim(value[11])); 
        } catch (NumberFormatException e) {
            return -1.0d;
        }
    }
    
    /** Change Percent NOT (Real-time) */
    public double getChangeInPercents() {
        try {
            return Double.parseDouble(
                    value[59].substring(1, value[59].indexOf('%'))); 
        } catch (NumberFormatException e) {
            return -1.0d;
        }
    }
    
    /** Previous Close */
    public double getPreviousClose() {
        try {
            return Double.parseDouble(value[57]); 
        } catch (NumberFormatException e) {
            return -1.0d;
        }
    }
    
    /** Open */
    public double getOpen() {
        try {
            return Double.parseDouble(value[56]); 
        } catch (NumberFormatException e) {
            return -1.0d;
        }
    }
    
    /** Bid (Real-time) */
    public double getBid() {
        try {
            return Double.parseDouble(value[5]); 
        } catch (NumberFormatException e) {
            return -1.0d;
        }
    }
    
    /** Bid Size */
    public double getBidSize() {
        try {
            return Double.parseDouble(value[7]); 
        } catch (NumberFormatException e) {
            return -1.0d;
        }
    }
    
    /** Ask (Real-time) */
    public double getAsk() {
        try {
            return Double.parseDouble(value[4]); 
        } catch (NumberFormatException e) {
            return -1.0d;
        }
    }
    
    /** Ask Size */
    public double getAskSize() {
        try {
            return Double.parseDouble(value[2]); 
        } catch (NumberFormatException e) {
            return -1.0d;
        }
    }
    
    /** 1 yr Target Price */
    public double getTargetPrice() {
        try {
            return Double.parseDouble(value[75]); 
        } catch (NumberFormatException e) {
            return -1.0d;
        }
    }
    
    ///// ranges
    
    /** Volume */
    public double getVolume() {
        try {
            return Double.parseDouble(value[76]); 
        } catch (NumberFormatException e) {
            return -1.0d;
        }
    }
    
    /** Average Daily Volume */
    public double getAvgVol() {
        try {
            return Double.parseDouble(value[1]); 
        } catch (NumberFormatException e) {
            return -1.0d;
        }
    }
    
    /** 
     * Average Daily Volume
     * @return Average Daily Volume (Number and class, ex. 168.67B)
     */
    public String getMarketCap() {
        return value[32]; 
    }
    
    /* P/E Ratio */
    public double getPERatio() {
        try {
            return Double.parseDouble(value[63]); 
        } catch (NumberFormatException e) {
            return -1.0d;
        }
    }
    
    /* Earnings/Share */
    public double getEPSEstimate() {
        try {
            return Double.parseDouble(value[16]); 
        } catch (NumberFormatException e) {
            return -1.0d;
        }
    }
    
    // Day’s Value Change (Real-time)
    
    @Override
    public String toString() {
        StringBuilder    sb = new StringBuilder(16);
        
        return sb.append("Symbol:       ").append(getName()
                    ).append(" (").append(getStockExchange()).append(")"
                ).append("\nLast Trade:   ").append(getLastTrade()
                ).append("\nTrade Time:   ").append(getTradeTime()
                ).append("\nChange:       ").append(getChange()
                    ).append(" (").append(getChangeInPercents()).append(")"
                ).append("\nPrev Close:   ").append(getPreviousClose()
                ).append("\nOpen:         ").append(getOpen()
                ).append("\nBid:          ").append(getBid()
                    ).append(" x ").append(getBidSize()
                ).append("\nAsk:          ").append(getAsk()
                    ).append(" x ").append(getAskSize()
                ).append("\n1y Target Est:").append(getTargetPrice()
                ).append("\nDay's Range:  ").append(getLastTrade()
                ).append("\n52wk Range:   ").append(getLastTrade()
                ).append("\nVolume:       ").append(getVolume()
                ).append("\nAvg Vol (3m): ").append(getAvgVol()
                ).append("\nMarket Cap:   ").append(getMarketCap()
                ).append("\nP/E (ttm):    ").append(getPERatio()
                ).append("\nEPS (ttm):    ").append(getEPSEstimate()
                ).append("\nDiv & Yield:  ").append(getLastTrade()
                ).toString();
    }
    
    /** 
     * Returns a copy of the string, with leading and trailing 
     * whitespace and quotes omitted. 
     */
    private String trim(String str) {
        if (str == null) {
            return str;
        }
        
        str = str.trim();
        
        if ( str.startsWith("\"") && str.endsWith("\"") ) {
            return str.substring(1, str.length( ) - 1);
        }
        
        return str;
    }
    
}
