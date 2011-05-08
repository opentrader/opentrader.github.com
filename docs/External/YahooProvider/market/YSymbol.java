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
 *  %name      YSymbol.java
 *  %pkg       com.opentrader.market
 *  %date      6:46:17 PM, Apr 17, 2011
 */
public class YSymbol {
    
    private static final java.util.logging.Logger LOG 
            = java.util.logging.Logger.getLogger("opentrader");

    private String code;
    
    public static String AMEX = "";
    public static String NASDAQ = "";
    public static String NYSE = "";
    public static String OB = "OB";
    public static String PK = "PK";
    public static String BA = "BA";
    public static String VI = "VI";
    public static String AX = "AX";
    public static String SA = "SA";
    public static String TO = "TO";
    public static String V = "V";
    public static String SS = "SS";
    public static String SZ = "SZ";
    public static String CO = "CO";
    public static String PA = "PA";
    public static String BE = "BE";
    public static String DU = "DU";
    public static String F = "F";
    public static String HM = "HM";
    public static String HA = "HA";
    public static String MU = "MU";
    public static String SG = "SG";
    public static String DE = "DE";
    public static String HK = "HK";
    public static String BO = "BO";
    public static String NS = "NS";
    public static String JK = "JK";
    public static String IR = "IR";
    public static String TA = "TA";
    public static String MI = "MI";
    public static String KS = "KS";
    public static String KQ = "KQ";
    public static String MX = "MX";
    public static String AS = "AS";
    public static String NZ = "NZ";
    public static String OL = "OL";
    public static String LS = "LS";
    public static String SI = "SI";
    public static String BC = "BC";
    public static String BI = "BI";
    public static String MF = "MF";
    public static String MC = "MC";
    public static String MA = "MA";
    public static String ST = "ST";
    public static String SW = "SW";
    public static String VX = "VX";
    public static String TWO = "TWO";
    public static String TW = "TW";
    public static String BK = "BK";
    public static String L = "L";

    public YSymbol(String code) {
        this.code = code;
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
        YSymbol symbol;
        
        if ((o instanceof YSymbol)) {
            symbol = (YSymbol) o;
        } else {
            return false;
        }

        return this.code.equals(symbol.getCode());
    }
}
