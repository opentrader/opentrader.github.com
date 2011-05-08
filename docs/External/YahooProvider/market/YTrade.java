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
 *  %name      YTrade.java
 *  %pkg       com.opentrader.market
 *  %date      6:49:47 PM, Apr 17, 2011
 */
public class YTrade {
    
    private static final java.util.logging.Logger LOG 
            = java.util.logging.Logger.getLogger("opentrader");

    private String               date;
    private String               time;
    private java.math.BigDecimal value;
    private Integer              volume;
    private Integer              unknown;
    
    public YTrade() {
        super();
    }

    public String getTime() {
        return this.time;
    }

    protected void setTime(String time) {
        this.time = time;
    }

    public java.math.BigDecimal getValue() {
        return this.value;
    }

    protected void setValue(java.math.BigDecimal value) {
        this.value = value;
    }

    public Integer getVolume() {
        return this.volume;
    }

    protected void setVolume(Integer volume) {
        this.volume = volume;
    }

    public Integer getUnknown() {
        return this.unknown;
    }

    protected void setUnknown(Integer unknown) {
        this.unknown = unknown;
    }

    public String getDate() {
        return this.date;
    }

    protected void setDate(String date) {
        this.date = date;
    }
}
