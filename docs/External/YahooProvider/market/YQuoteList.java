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
 *  %name      YQuoteList.java
 *  %pkg       com.opentrader.market
 *  %date      6:42:08 PM, Apr 17, 2011
 */
public class YQuoteList {
    
    private static final java.util.logging.Logger LOG 
            = java.util.logging.Logger.getLogger("opentrader");

    private java.util.ArrayList<YQuote> quoteList 
            = new java.util.ArrayList<YQuote>(10);
    private java.util.HashMap<YSymbol, YQuote> quoteMap 
            = new java.util.HashMap(10);
    private java.util.HashMap<String, YSymbol> symbolMap 
            = new java.util.HashMap(10);

    public YQuote getStockQuote(String code) {
        return this.quoteMap.get(this.symbolMap.get(code));
    }

    public YQuote getStockQuote(YSymbol symbol) {
        return this.quoteMap.get(symbol);
    }

    public void add(YSymbol symbol) {
        YQuote quote = new YQuote(symbol);
        
        this.quoteList.add(quote);
        this.quoteMap.put(symbol, quote);
        this.symbolMap.put(symbol.getCode(), symbol);
    }

    public void remove(YSymbol symbol) {
        YQuote quote = this.quoteMap.get(symbol);
        
        this.quoteList.remove(quote);
        this.quoteMap.remove(symbol);
        this.symbolMap.remove(symbol.getCode());
    }

    public void clear() {
        this.quoteList.clear();
        this.quoteMap.clear();
        this.symbolMap.clear();
    }

    public String getUrlSymbolParameter() {
        StringBuilder sb = new StringBuilder();

        for (YQuote s : this.quoteList) {
            sb.append("+");
            sb.append(s.getSymbol().getCode());
        }

        return sb.toString();
    }

    public int size() {
        return this.quoteList.size();
    }

    public java.util.ArrayList<YQuote> getQuotes() {
        java.util.ArrayList<YQuote> list 
                = new java.util.ArrayList<YQuote>(this.quoteList);
        
        return list;
    }
}
