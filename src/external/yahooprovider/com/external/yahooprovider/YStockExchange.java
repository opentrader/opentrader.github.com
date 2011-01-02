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

import com.opentrader.market.feeds.StockExchange;
import com.opentrader.market.feeds.Symbol;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

/**
 *  @author    Andrey Pudov        <syscreat@gmail.com>
 *  @version   0.00.00
 *  %name      YStockExchange.java
 *  %pkg       com.external.yahooprovider
 *  %date      11:48:47, 24-Dec-2010
 */
public class YStockExchange implements StockExchange {

    private static final long serialVersionUID = 2008219402060209730L;
    private static final Logger LOG = Logger.getLogger("yahooprovider");

    private String name;
    private ArrayList<Symbol> symbols = new ArrayList<Symbol>(10);

    public YStockExchange(String name, ArrayList<Symbol> symbols) {
        this.name = name;
        this.symbols = symbols;
    }

    public String getName() {
        return this.name;
    }

    public List<Symbol> getSymbols() {
        return Collections.unmodifiableList(symbols);
    }

    @Override
    public int hashCode() {
        return this.name.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        YStockExchange stock;

        if ((o instanceof YStockExchange)) {
            stock = (YStockExchange) o;
        } else {
            return false;
        }

        return this.name.equals(stock.getName());
    }

}
