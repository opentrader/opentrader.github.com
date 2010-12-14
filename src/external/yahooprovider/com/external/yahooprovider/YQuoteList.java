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

import com.opentrader.market.feeds.Quote;
import com.opentrader.market.feeds.QuoteList;
import com.opentrader.market.feeds.Symbol;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Logger;

/**
 *  @author    Andrey Pudov        <syscreat@gmail.com>
 *  @version   0.00.00
 *  %name      YQuoteList.java
 *  %pkg       com.external.yahooprovider
 *  %date      9:22:01 AM, Dec 14, 2010
 */
public class YQuoteList implements QuoteList {

    private static final long serialVersionUID = 3893069240714548349L;
    private static final Logger LOG = Logger.getLogger("yahooprovider");

    private ArrayList<YQuote> quoteList = new ArrayList<YQuote>(10);
    private HashMap<YSymbol, YQuote> quoteMap = new HashMap<YSymbol, YQuote>(10);
    private HashMap<String, YSymbol> symbolMap = new HashMap<String, YSymbol>(10);

    public YQuote getStockQuote(String code) {
        return this.quoteMap.get(this.symbolMap.get(code));
    }

    public YQuote getStockQuote(Symbol symbol) {
        return this.quoteMap.get((YSymbol) symbol);
    }

    public void add(Symbol symbol) {
        YQuote quote = new YQuote((YSymbol) symbol);

        this.quoteList.add(quote);
        this.quoteMap.put((YSymbol) symbol, quote);
        this.symbolMap.put(symbol.getCode(), (YSymbol) symbol);
    }

    public void remove(Symbol symbol) {
        YQuote quote = this.quoteMap.get((YSymbol) symbol);

        this.quoteList.remove(quote);
        this.quoteMap.remove((YSymbol) symbol);
        this.symbolMap.remove(((YSymbol) symbol).getCode());
    }

    public void clear() {
        this.quoteList.clear();
        this.quoteMap.clear();
        this.symbolMap.clear();
    }

    public String getUrlSymbolParameter() {
        StringBuilder sb = new StringBuilder(10);

        for (YQuote s : this.quoteList) {
            sb.append("+");
            sb.append(s.getSymbol().getCode());
        }

        return sb.toString();
    }

    public int size() {
        return this.quoteList.size();
    }

    public ArrayList<Quote> getQuotes() {
        ArrayList<Quote> list = new ArrayList<Quote>(this.quoteList);
        
        return list;
    }
}
