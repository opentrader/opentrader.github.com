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
 *  %name      YHistoric.java
 *  %pkg       com.opentrader.market
 *  %date      6:31:46 PM, Apr 17, 2011
 */
public class YHistoric {
    
    private static final java.util.logging.Logger LOG 
            = java.util.logging.Logger.getLogger("opentrader");

    private String date;
    
    private java.math.BigDecimal open;
    private java.math.BigDecimal high;
    private java.math.BigDecimal low;
    private java.math.BigDecimal close;
    private java.math.BigDecimal volume;
    private java.math.BigDecimal adjClose;
    
    public YHistoric() {
        super();
    }

    public java.math.BigDecimal getOpen() {
        return this.open;
    }

    protected void setOpen(java.math.BigDecimal open) {
        this.open = open;
    }

    public String getDate() {
        return this.date;
    }

    protected void setDate(String date) {
        this.date = date;
    }

    public java.math.BigDecimal getHigh() {
        return this.high;
    }

    protected void setHigh(java.math.BigDecimal high) {
        this.high = high;
    }

    public java.math.BigDecimal getLow() {
        return this.low;
    }

    protected void setLow(java.math.BigDecimal low) {
        this.low = low;
    }

    public java.math.BigDecimal getClose() {
        return this.close;
    }

    protected void setClose(java.math.BigDecimal close) {
        this.close = close;
    }

    public java.math.BigDecimal getVolume() {
        return this.volume;
    }

    protected void setVolume(java.math.BigDecimal volume) {
        this.volume = volume;
    }

    public java.math.BigDecimal getAdjClose() {
        return this.adjClose;
    }

    protected void setAdjClose(java.math.BigDecimal adjClose) {
        this.adjClose = adjClose;
    }
}
