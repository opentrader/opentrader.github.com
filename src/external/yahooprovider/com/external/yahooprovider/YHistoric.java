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

import com.opentrader.market.feeds.Historic;
import java.math.BigDecimal;
import java.util.logging.Logger;

/**
 *  @author    Andrey Pudov        <syscreat@gmail.com>
 *  @version   0.00.00
 *  %name      YHistoric.java
 *  %pkg       com.external.yahooprovider
 *  %date      10:24:36 AM, Dec 7, 2010
 */
public class YHistoric implements Historic {

    private static final long serialVersionUID = 4682150895981968598L;
    private static final Logger LOG = Logger.getLogger("yahooprovider");
    
    private String date;
    private BigDecimal open;
    private BigDecimal high;
    private BigDecimal low;
    private BigDecimal close;
    private BigDecimal volume;
    private BigDecimal adjClose;

    public BigDecimal getOpen() {
        return this.open;
    }

    protected void setOpen(BigDecimal open) {
        this.open = open;
    }

    public String getDate() {
        return this.date;
    }

    protected void setDate(String date) {
        this.date = date;
    }

    public BigDecimal getHigh() {
        return this.high;
    }

    protected void setHigh(BigDecimal high) {
        this.high = high;
    }

    public BigDecimal getLow() {
        return this.low;
    }

    protected void setLow(BigDecimal low) {
        this.low = low;
    }

    public BigDecimal getClose() {
        return this.close;
    }

    protected void setClose(BigDecimal close) {
        this.close = close;
    }

    public BigDecimal getVolume() {
        return this.volume;
    }

    protected void setVolume(BigDecimal volume) {
        this.volume = volume;
    }

    public BigDecimal getAdjClose() {
        return this.adjClose;
    }

    protected void setAdjClose(BigDecimal adjClose) {
        this.adjClose = adjClose;
    }
}
