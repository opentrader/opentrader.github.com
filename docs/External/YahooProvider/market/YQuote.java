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
 *  %name      YQuote.java
 *  %pkg       com.opentrader.market
 *  %date      6:38:44 PM, Apr 17, 2011
 */
public class YQuote {
    
    private static final java.util.logging.Logger LOG 
            = java.util.logging.Logger.getLogger("opentrader");

    private YSymbol symbol;
    private java.util.HashMap<com.opentrader.market.csv.format.YTag, String> values 
            = new java.util.HashMap<com.opentrader.market.csv.format.YTag, String>(10);
    private java.util.ArrayList<YTrade> dayTrades 
            = new java.util.ArrayList<YTrade>(10);
    private java.util.ArrayList<YHistoric> historics 
            = new java.util.ArrayList<YHistoric>(10);
    private String csvDayTrade;
    private String csvHistoric;
    private boolean valid = false;
    
    public YQuote() {
        super();
    }

    public java.util.ArrayList<YHistoric> getHistorics() {
        return new java.util.ArrayList<YHistoric>(this.historics);
    }

    protected void setHistorics(java.util.ArrayList<YHistoric> historics) {
        this.historics = historics;
    }

    public java.util.ArrayList<YTrade> getDayTrades() {
        return new java.util.ArrayList<YTrade>(this.dayTrades);
    }

    protected void setDayTrades(java.util.ArrayList<YTrade> dayTrades) {
        this.dayTrades = dayTrades;
    }

    public String getCsvHistoric() {
        return this.csvHistoric;
    }

    protected void setCsvHistoric(String csvHistoric) {
        this.csvHistoric = csvHistoric;
    }

    public String getCsvDayTrade() {
        return this.csvDayTrade;
    }

    protected void setCsvDayTrade(String csvDayTrade) {
        this.csvDayTrade = csvDayTrade;
    }

    protected YQuote(YSymbol symbol) {
        this.symbol = symbol;
    }

    public boolean isValid() {
        return this.valid;
    }

    protected void setValid(boolean valid) {
        this.valid = valid;
    }

    public YSymbol getSymbol() {
        return this.symbol;
    }

    public String getValue(String key) {
        com.opentrader.market.csv.format.YTag tag 
                = new com.opentrader.market.csv.format.YTag(key);
        
        return this.values.get(tag);
    }

    protected void setValue(com.opentrader.market.csv.format.YTag tag, 
                            String value) {
        
        this.values.put(tag, value);
    }

    @Override
    public int hashCode() {
        return this.symbol.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        YQuote quote;
        
        if ((o instanceof YQuote)) {
            quote = (YQuote) o;
        } else {
            return false;
        }

        return this.symbol.equals(quote.getSymbol());
    }
}
