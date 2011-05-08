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
 *  %name      YExchangeRate.java
 *  %pkg       com.opentrader.market
 *  %date      6:28:26 PM, Apr 17, 2011
 */
public class YExchangeRate {
    
    private static final java.util.logging.Logger LOG 
            = java.util.logging.Logger.getLogger("opentrader");

    private YCurrency fromCurrency;
    private YCurrency toCurrency;

    public YExchangeRate(YCurrency from, YCurrency to) {
        this.fromCurrency = from;
        this.toCurrency   = to;
    }

    @Override
    public int hashCode() {
        String code = this.fromCurrency.getCode() + this.toCurrency.getCode();
        
        return code.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        YExchangeRate rate;
        
        if ((o instanceof YExchangeRate)) {
            rate = (YExchangeRate) o;
        } else {
            return false;
        }

        return (this.fromCurrency.getCode().equals(
                    rate.getFromCurrency().getCode())) 
                && (this.toCurrency.getCode().equals(
                    rate.getToCurrency().getCode()));
    }

    public YCurrency getFromCurrency() {
        return this.fromCurrency;
    }

    public YCurrency getToCurrency() {
        return this.toCurrency;
    }

    public YSymbol getSymbol() {
        YSymbol symbol = new YSymbol(
                this.fromCurrency.getCode() 
                + this.toCurrency.getCode() + "=X");
        
        return symbol;
    }
}
