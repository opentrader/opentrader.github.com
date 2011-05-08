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
 *  %name      Historic.java
 *  %pkg       com.opentrader.market
 *  %date      2:55:51 PM, Apr 18, 2011
 */
public class Historic  {
    
    private static final java.util.logging.Logger LOG 
            = java.util.logging.Logger.getLogger("opentrader");
    
    private java.util.Date date;
    private double         open;
    private double         high;
    private double         low;
    private double         close;
    private double         volume;
    private double         adjClose;
    
    public Historic(java.util.Date date, double open, double high, double low,
                    double close, double volume, double adjClose) {
        super();
        
        this.date     = date;
        this.open     = open;
        this.high     = high;
        this.low      = low;
        this.close    = close;
        this.volume   = volume;
        this.adjClose = adjClose;
    }
    
    public long getDate() {
        return date.getTime();
    }
    
    public double getOpen() {
        return open;
    }
    
    public double getHigh() {
        return high;
    }
    
    public double getLow() {
        return low;
    }
    
    public double getClose() {
        return close;
    }
    
    public double getVolume() {
        return volume;
    }
    
    public double getAdjClose() {
        return adjClose;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(20);
        return sb.append("Date:       "
                ).append(new java.text.SimpleDateFormat(
                    "dd-MMM-yy").format(date)
                ).append("\tOpen: ").append(String.format("%1$.2f", open)
                ).append("\tHigh: ").append(String.format("%1$.2f", high)
                ).append("\tLow: ").append(String.format("%1$.2f", low)
                ).append("\tClose: ").append(String.format("%1$.2f", close)
                ).append("\tVolume: ").append(String.format("%1$.2f", volume)
                ).append("\tAdjClose: ").append(String.format("%1$.2f", adjClose)
                ).toString();
    }
    
    @Override
    public int hashCode() {
        return this.date.hashCode() + Double.valueOf(open).hashCode()
               + Double.valueOf(high).hashCode() 
               + Double.valueOf(low).hashCode()
               + Double.valueOf(close).hashCode()
               + Double.valueOf(volume).hashCode()
               + Double.valueOf(adjClose).hashCode();
    }
    
}
