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

import com.opentrader.market.feeds.Symbol;
import java.util.Date;
import java.util.logging.Logger;

/**
 *  @author    Andrey Pudov        <syscreat@gmail.com>
 *  @version   0.00.00
 *  %name      YSymbol.java
 *  %pkg       com.external.yahooprovider
 *  %date      10:16:32 AM, Dec 7, 2010
 */
public class YSymbol implements Symbol {

    private static final long serialVersionUID = 9028214829167211588L;
    private static final Logger LOG = Logger.getLogger("yahooprovider");

    private String code;
    private String description;

    private double ask = -1;        /* Ask (a) */
    private double avg = -1;        /* Average Daily Volume (a2) */
    private double askSize = -1;    /* Ask Size (a5) */
    private double bid = -1;        /* Bid (b) */
    private double askRT = -1;      /* Ask (Real-time) (b2) */
    private double bidRT = -1;      /* Bid (Real-time) (b3) */
    private double bookkValue = -1; /* Book Value (b4) */
    private double badSize = -1;    /* Bid Size (b6) */
    //private double pchange = -1;    /* Change & Percent Change (c) */ $(c1 + p2 = c)
    private double change = -1;     /* Change (c1) */
    private double commission = -1; /* Commission (c3) */
    private double changeRT = -1;   /* Change (Real-time) (c6) */
    private double afterHrChg = -1; /* After Hours Change (Real-time) (c8) */
    private double share = -1;      /* Dividend/Share (d) */
    private long   lastTrdDate = -1;/* Last Trade Date (d1) */
    private double tradeDate = -1;  /* Trade Date (d2) */
    private double earning = -1;    /* Earnings/Share (e) */
    private double error = -1;      /* Error Indication (returned for symbol changed / invalid) (e1) */
    private double EPSCurYear = -1; /* EPS Estimate Current Year (e7) */
    private double EPSNextYear = -1;/* EPS Estimate Next Year (e8) */
    private double EPSNextQrtr = -1;/* EPS Estimate Next Quarter (e9) */
    private double floatShares = -1;/* Float Shares (f6) */
    private double dayLow = -1;     /* Day’s Low (g) */
    private double dayHigh = -1;    /* Day’s High (h) */
    private double w52low = -1;     /* 52-week Low (j) */
    private double w52high = -1;    /* 52-week High (k) */
    private double holdGainP = -1;  /* Holdings Gain Percent (g1) */
    private double annualGain = -1; /* Annualized Gain (g3) */
    private double holdGain = -1;   /* Holdings Gain (g4) */
    private double holdGainPRT = -1;/* Holdings Gain Percent (Real-time) (g5) */
    private double holdGainRT = -1; /* Holdings Gain (Real-time) (g6) */
    private double more = -1;       /* More Info (i) */
    private double orderBookRT = -1;/* Order Book (Real-time) (i5) */
    private double marketCap = -1;  /* Market Capitalization (j1) */
    private double marketCapRT = -1;/* Market Cap (Real-time) (j3) */
    private double EBITDA = -1;     /* EBITDA (j4) */
    private double w52lowChng = -1; /* Change From 52-week Low (j5) */
    private double prcChng52w = -1; /* Percent Change From 52-week Low (j6) */
    private double lastTradeRT = -1;/* Last Trade (Real-time) With Time (k1) */
    private double chngPerRT = -1;  /* Change Percent (Real-time) (k2) */
    private double lastTrSize = -1; /* Last Trade Size (k3) */
    private double chng52wHigh = -1;/* Change From 52-week High (k4) */
    private double pchg52wHigh = -1;/* Percebt Change From 52-week High (k5) */
    private double lastTradeTm = -1;/* Last Trade (With Time) (l) */
    private double lastTrPrice = -1;/* Last Trade (Price Only) (l1) */
    private double highLimit = -1;  /* High Limit (l2) */
    private double lowLimit = -1;   /* Low Limit (l3) */
    private double daysRange = -1;  /* Day’s Range (m) */
    private double daysRangeR = -1; /* Day’s Range (Real-time) (m2) */
    private double d50MovAvg = -1;  /* 50-day Moving Average (m3) */
    private double d200MovAvg = -1; /* 200-day Moving Average (m4) */
    private double cng200dMAvg = -1;/* Change From 200-day Moving Average (m5) */
    private double pCgd200VAvg = -1;/* Percent Change From 200-day Moving Average (m6) */
    private double cngd50MAvg = -1; /* Change From 50-day Moving Average (m7) */
    private double pCgd50MAvg = -1; /* Percent Change From 50-day Moving Average (m8) */
    private double name = -1;       /* Name (n) */
    private double notes = -1;      /* Notes (n4) */
    private double open = -1;       /* Open (o) */
    private double prevClose = -1;  /* Previous Close (p) */
    private double pricePaid = -1;  /* Price Paid (p1) */
    private double chgPercent = -1; /* Change in Percent (p2) */
    private double priceSales = -1; /* Price/Sales (p5) */
    private double priceBook = -1;  /* Price/Book (p6) */
    private double exDivDate = -1;  /* Ex-Dividend Date (q) */
    private double PERation = -1;   /* P/E Ratio (r) */
    private double divPayDate = -1; /* Dividend Pay Date (r1) */
    private double PERatioTR = -1;  /* P/E Ratio (Real-time) (r2) */
    private double PEGRatio = -1;   /* PEG Ratio (r5) */
    private double EPSECrYrPrc = -1;/* Price/EPS Estimate Current Year (r6) */
    private double EPSCrYrPrc = -1; /* Price/EPS Estimate Next Year (r7) */
    private double symbolName = -1; /* Symbol (s) */
    private double sharesOwn = -1;  /* Shares Owned (s1) */
    private double shortRatio = -1; /* Short Ratio (s7) */
    //private double lastTrdTime = -1;/* Last Trade Time (t1) */ $(d1)
    private double tradeLinks = -1; /* Trade Links (t6) */
    private double tickerTrend = -1;/* Ticker Trend (t7) */
    private double yr1Proce = -1;   /* 1 yr Target Price (t8) */
    private long   volume = -1;     /* Volume (v) */
    private double holdvalue = -1;  /* Holdings Value (v1) */
    private double holdvalueRT = -1;/* Holdings Value (Real-time) (v7) */
    private double w52range = -1;   /* 52-week Range (w) */
    private double dValChng = -1;   /* Day’s Value Change (w1) */
    private double dValChngRT = -1; /* Day’s Value Change (Real-time) (w4) */
    private String stock = null;      /* Stock Exchange (x) */
    private double dvdndYield = -1; /* Dividend Yield (y) */

    public YSymbol(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public String getCode() {
        return this.code;
    }

    public String getDescription() {
        return this.description;
    }
    
    /**
     * s    Symbol
     * n    Name
     * a    Ask //
     * b    Bid //
     * l1   Last Trade (Price Only)
     * d1   Last Trade Date
     * t1   Last Trade Time
     * c1   Change (c1 + p2 = c)
     * p2   Change in Percent (c1 + p2 = c)
     * v    Volume
     * x    Stock Exchange
     */
    
    /**
     * 
     * @return a    Ask
     */
    public double getAsk() {
        return ask;
    }   
    
    /**
     * 
     * @return b    Bid
     */
    public double getBid() {
        return bid;
    } 
    
    /**
     * 
     * @return l1   Last Trade (Price Only)
     */
    public double getLastTradePrice() {
        return lastTrPrice;
    }
    
    /**
     * 
     * @return  d1   Last Trade Date
     *          t1   Last Trade Time
     */
    public long getLastTradeDateAndTime() {
        return lastTrdDate;
    }
    
    /**
     * 
     * @return c1   Change (c1 + p2 = c)
     */
    public double getChange() {
        return change;
    }
    
    /**
     * 
     * @return p2   Change in Percent (c1 + p2 = c)
     */
    public double getChangeInPercent() {
        return chgPercent;
    }
    
    /**
     * 
     * @return x    Stock Exchange
     */
    public String getStockExchange() {
        return stock;
    }
    
    /**
     * 
     * @return v    Volume
     */
    public long getVolume() {
        return volume;
    }
    
    /**
     * 
     * @param a     Ask
     */
    public void setAsk(double value) {
        ask = value;
    }
    
    /**
     * 
     * @param b     Bid
     */
    public void setBid(double value) {
        bid = value;
    }
    
    /**
     * 
     * @param value 
     *        l1   Last Trade (Price Only)
     */
    public void setLastTradePrice(double value) {
        lastTrPrice = value;
    }
    
    /**
     * 
     * @param value 
     *        d1   Last Trade Date
     *        t1   Last Trade Time
     */
    public void setLastTradeDateAndTime(long value) {
        lastTrdDate = value;
    }
    
    /**
     * 
     * @param value 
     *        c1   Change (c1 + p2 = c)
     */
    public void setChange(double value) {
        change = value;
    }
    
    /**
     * 
     * @param value 
     *        p2   Change in Percent (c1 + p2 = c)
     */
    public void setChangeInPercent(double value) {
        chgPercent = value;
    }
    
    /**
     * 
     * @param value 
     *        v    Volume
     */
    public void setVolume(long value) {
        volume = value;
    }
    
    /**
     * 
     * @param value 
     *        x    Stock Exchange
     */
    public void setStockExchange(String value) {
        stock = value;
    }

    @Override
    public int hashCode() {
        return this.code.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        YSymbol symbol;

        if ((o instanceof YSymbol)) {
            symbol = (YSymbol) o;
        } else {
            return false;
        }

        return this.code.equals(symbol.getCode());
    }

}
