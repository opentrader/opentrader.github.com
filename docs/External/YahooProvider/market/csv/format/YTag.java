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
package com.opentrader.market.csv.format;

/**
 *  @author    Andrey Pudov        <syscreat@gmail.com>
 *  @version   0.00.00
 *  %name      YTag.java
 *  %pkg       com.opentrader.market.csv.format
 *  %date      7:33:57 PM, Apr 17, 2011
 */
public class YTag {
    
    private static final java.util.logging.Logger LOG 
            = java.util.logging.Logger.getLogger("opentrader");

    private String  code;
    private boolean enabled;
    
    public static String a = "a";
    public static String a2 = "a2";
    public static String a5 = "a5";
    public static String b = "b";
    public static String b2 = "b2";
    public static String b3 = "b3";
    public static String b4 = "b4";
    public static String b6 = "b6";
    public static String c = "c";
    public static String c1 = "c1";
    public static String c3 = "c3";
    public static String c6 = "c6";
    public static String c8 = "c8";
    public static String d = "d";
    public static String d1 = "d1";
    public static String d2 = "d2";
    public static String e = "e";
    public static String e1 = "e1";
    public static String e7 = "e7";
    public static String e8 = "e8";
    public static String e9 = "e9";
    public static String f6 = "f6";
    public static String g = "g";
    public static String g1 = "g1";
    public static String g3 = "g3";
    public static String g4 = "g4";
    public static String g5 = "g5";
    public static String g6 = "g6";
    public static String h = "h";
    public static String i = "i";
    public static String i5 = "i5";
    public static String j = "j";
    public static String j1 = "j1";
    public static String j3 = "j3";
    public static String j4 = "j4";
    public static String j5 = "j5";
    public static String j6 = "j6";
    public static String k = "k";
    public static String k1 = "k1";
    public static String k2 = "k2";
    public static String k3 = "k3";
    public static String k4 = "k4";
    public static String k5 = "k5";
    public static String l = "l";
    public static String l1 = "l1";
    public static String l2 = "l2";
    public static String l3 = "l3";
    public static String m = "m";
    public static String m2 = "m2";
    public static String m3 = "m3";
    public static String m4 = "m4";
    public static String m5 = "m5";
    public static String m6 = "m6";
    public static String m7 = "m7";
    public static String m8 = "m8";
    public static String n = "n";
    public static String n4 = "n4";
    public static String o = "o";
    public static String p = "p";
    public static String p1 = "p1";
    public static String p2 = "p2";
    public static String p5 = "p5";
    public static String p6 = "p6";
    public static String q = "q";
    public static String r = "r";
    public static String r1 = "r1";
    public static String r2 = "r2";
    public static String r5 = "r5";
    public static String r6 = "r6";
    public static String r7 = "r7";
    public static String s = "s";
    public static String s1 = "s1";
    public static String s7 = "s7";
    public static String t1 = "t1";
    public static String t6 = "t6";
    public static String t7 = "t7";
    public static String t8 = "t8";
    public static String v = "v";
    public static String v1 = "v1";
    public static String v7 = "v7";
    public static String w = "w";
    public static String w1 = "w1";
    public static String w4 = "w4";
    public static String x = "x";
    public static String y = "y";
    public static String ASK = a;
    public static String AVERAGE_DAILY_VOLUME = a2;
    public static String ASK_SIZE = a5;
    public static String BID = b;
    public static String ASK_REAL_TIME = b2;
    public static String BID_REAL_TIME = b3;
    public static String BOOK_VALUE = b4;
    public static String BID_SIZE = b6;
    public static String CHANGE_CHANGE_PERCENT = c;
    public static String CHANGE = c1;
    public static String COMMISSION = c3;
    public static String CHANGE_REAL_TIME = c6;
    public static String AFTER_HOURS_CHANGE_REAL_TIME = c8;
    public static String DIVIDEND_SHARE = d;
    public static String LAST_TRADE_DATE = d1;
    public static String TRADE_DATE = d2;
    public static String EARNINGS_SHARE = e;
    public static String ERROR_INDICATION = e1;
    public static String EPS_ESTIMATE_CURRENT_YEAR = e7;
    public static String EPS_ESTIMATE_NEXT_YEAR = e8;
    public static String EPS_ESTIMATE_NEXT_QUARTER = e9;
    public static String FLOAT_SHARES = f6;
    public static String LOW_DAY = g;
    public static String HOLDINGS_GAIN_PERCENT = g1;
    public static String ANNUALIZED_GAIN = g3;
    public static String HOLDINGS_GAIN = g4;
    public static String HOLDINGS_GAIN_PERCENT_REAL_TIME = g5;
    public static String HOLDINGS_GAIN_REAL_TIME = g6;
    public static String HIGH_DAY = h;
    public static String MORE_INFO = i;
    public static String ORDER_BOOK_REAL_TIME = i5;
    public static String LOW_WEEK_52 = j;
    public static String MARKET_CAPITALIZATION = j1;
    public static String MARKET_CAPITALIZATION_REAL_TIME = j3;
    public static String EBITDA = j4;
    public static String CHANGE_FROM_52_WEEK_LOW = j5;
    public static String CHANGE_FROM_52_WEEK_LOW_PERCENT = j6;
    public static String HIGH_52_WEEK = k;
    public static String LAST_TRADE_WITH_TIME_REAL_TIME = k1;
    public static String CHANGE_PERCENT_REAL_TIME = k2;
    public static String LAST_TRADE_SIZE = k3;
    public static String CHANGE_FROM_52_WEEK_HIGH = k4;
    public static String CHANGE_FROM_52_WEEK_HIGH_PERCENT = k5;
    public static String LAST_TRADE_WITH_TIME = l;
    public static String LAST_TRADE_PRICE_ONLY = l1;
    public static String HIGH_LIMIT = l2;
    public static String LOW_LIMIT = l3;
    public static String RANGE_DAY = m;
    public static String RANGE_DAY_REAL_TIME = m2;
    public static String MOVING_AVERAGE_50_DAY = m3;
    public static String MOVING_AVERAGE_200_DAY = m4;
    public static String CHANGE_FROM_200_DAY_MOVING_AVERAGE = m5;
    public static String CHANGE_FROM_200_DAY_MOVING_AVERAGE_PERCENT = m6;
    public static String CHANGE_FROM_50_DAY_MOVING_AVERAGE = m7;
    public static String CHANGE_FROM_50_DAY_MOVING_AVERAGE_PERCENT = m8;
    public static String NAME = n;
    public static String NOTES = n4;
    public static String OPEN = o;
    public static String PREVIOUS_CLOSE = p;
    public static String PRICE_PAID = p1;
    public static String CHANGE_PERCENT = p2;
    public static String PRICE_SALES = p5;
    public static String PRICE_BOOK = p6;
    public static String EX_DIVIDEND_DATE = q;
    public static String PE_RATIO = r;
    public static String DIVIDEND_PAY_DATE = r1;
    public static String PE_RATIO_REAL_TIME = r2;
    public static String PEG_RATIO = r5;
    public static String PRICE_EPS_ESTIMATE_CURRENT_YEAR = r6;
    public static String PRICE_EPS_ESTIMATE_NEXT_YEAR = r7;
    public static String SYMBOL = s;
    public static String SHARES_OWNED = s1;
    public static String SHORT_RATIO = s7;
    public static String LAST_TRADE_TIME = t1;
    public static String TRADE_LINKS = t6;
    public static String TICKER_TREND = t7;
    public static String TARGET_PRICE_1_YEAR = t8;
    public static String VOLUME = v;
    public static String HOLDINGS_VALUE = v1;
    public static String HOLDINGS_VALUE_REAL_TIME = v7;
    public static String RANGE_52_WEEK = w;
    public static String VALUE_CHANGE_DAY = w1;
    public static String VALUE_CHANGE_DAY_REAL_TIME = w4;
    public static String STOCK_EXCHANGE = x;
    public static String DIVIDEND_YIELD = y;

    public YTag(String code) {
        this.code = code;

        setEnabled(false);
    }

    public boolean isEnabled() {
        return this.enabled;
    }

    public void setEnabled(boolean enabled) {
        if ((SYMBOL.equals(this.code)) || (ERROR_INDICATION.equals(this.code))) {
            this.enabled = true;
        } else {
            this.enabled = enabled;
        }
    }

    public String getCode() {
        return this.code;
    }

    @Override
    public int hashCode() {
        return this.code.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        YTag tag;

        if ((o instanceof YTag)) {
            tag = (YTag) o;
        } else {
            return false;
        }

        return this.code.equals(tag.getCode());
    }
}
