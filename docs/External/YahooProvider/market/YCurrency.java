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
 *  %name      YCurrency.java
 *  %pkg       com.opentrader.market
 *  %date      6:20:48 PM, Apr 17, 2011
 */
public class YCurrency {
    
    private static final java.util.logging.Logger LOG 
            = java.util.logging.Logger.getLogger("opentrader");

    private String code;
    public static String AFA = "AFA";
    public static String ALL = "ALL";
    public static String DZD = "DZD";
    public static String ARS = "ARS";
    public static String AWG = "AWG";
    public static String AUD = "AUD";
    public static String ATS = "ATS";
    public static String BHD = "BHD";
    public static String BDT = "BDT";
    public static String BBD = "BBD";
    public static String BEF = "BEF";
    public static String BZD = "BZD";
    public static String BMD = "BMD";
    public static String BTN = "BTN";
    public static String BOB = "BOB";
    public static String BWP = "BWP";
    public static String BRL = "BRL";
    public static String GBP = "GBP";
    public static String BND = "BND";
    public static String BGN = "BGN";
    public static String KHR = "KHR";
    public static String CAD = "CAD";
    public static String CVE = "CVE";
    public static String KYD = "KYD";
    public static String XOF = "XOF";
    public static String XAF = "XAF";
    public static String XPF = "XPF";
    public static String CLP = "CLP";
    public static String COP = "COP";
    public static String KMF = "KMF";
    public static String CRC = "CRC";
    public static String HRK = "HRK";
    public static String CUP = "CUP";
    public static String CYP = "CYP";
    public static String CZK = "CZK";
    public static String DKK = "DKK";
    public static String DJF = "DJF";
    public static String DOP = "DOP";
    public static String NLG = "NLG";
    public static String XCD = "XCD";
    public static String EGP = "EGP";
    public static String SVC = "SVC";
    public static String EEK = "EEK";
    public static String ETB = "ETB";
    public static String EUR = "EUR";
    public static String FJD = "FJD";
    public static String FIM = "FIM";
    public static String FRF = "FRF";
    public static String GMD = "GMD";
    public static String DEM = "DEM";
    public static String GHC = "GHC";
    public static String GIP = "GIP";
    public static String GRD = "GRD";
    public static String GTQ = "GTQ";
    public static String GNF = "GNF";
    public static String GYD = "GYD";
    public static String HTG = "HTG";
    public static String HNL = "HNL";
    public static String HKD = "HKD";
    public static String HUF = "HUF";
    public static String ISK = "ISK";
    public static String INR = "INR";
    public static String IDR = "IDR";
    public static String IEP = "IEP";
    public static String ILS = "ILS";
    public static String ITL = "ITL";
    public static String JMD = "JMD";
    public static String JPY = "JPY";
    public static String JOD = "JOD";
    public static String KES = "KES";
    public static String KWD = "KWD";
    public static String LAK = "LAK";
    public static String LVL = "LVL";
    public static String LBP = "LBP";
    public static String LSL = "LSL";
    public static String LTL = "LTL";
    public static String MGF = "MGF";
    public static String MWK = "MWK";
    public static String MYR = "MYR";
    public static String MVR = "MVR";
    public static String MTL = "MTL";
    public static String MRO = "MRO";
    public static String MUR = "MUR";
    public static String MXN = "MXN";
    public static String MNT = "MNT";
    public static String MAD = "MAD";
    public static String MZM = "MZM";
    public static String MMK = "MMK";
    public static String NAD = "NAD";
    public static String NPR = "NPR";
    public static String ANG = "ANG";
    public static String NZD = "NZD";
    public static String NIO = "NIO";
    public static String NGN = "NGN";
    public static String NOK = "NOK";
    public static String OMR = "OMR";
    public static String PKR = "PKR";
    public static String PGK = "PGK";
    public static String PEN = "PEN";
    public static String PHP = "PHP";
    public static String PLN = "PLN";
    public static String PTE = "PTE";
    public static String QAR = "QAR";
    public static String CNY = "CNY";
    public static String ROL = "ROL";
    public static String RUB = "RUB";
    public static String SBD = "SBD";
    public static String STD = "STD";
    public static String SAR = "SAR";
    public static String SCR = "SCR";
    public static String SLL = "SLL";
    public static String SGD = "SGD";
    public static String SKK = "SKK";
    public static String SIT = "SIT";
    public static String ZAR = "ZAR";
    public static String KRW = "KRW";
    public static String ESP = "ESP";
    public static String LKR = "LKR";
    public static String SHP = "SHP";
    public static String SDD = "SDD";
    public static String SRG = "SRG";
    public static String SZL = "SZL";
    public static String SEK = "SEK";
    public static String CHF = "CHF";
    public static String SYP = "SYP";
    public static String TWD = "TWD";
    public static String TZS = "TZS";
    public static String THB = "THB";
    public static String TOP = "TOP";
    public static String TTD = "TTD";
    public static String TND = "TND";
    public static String TRL = "TRL";
    public static String UGX = "UGX";
    public static String UAH = "UAH";
    public static String AED = "AED";
    public static String USD = "USD";
    public static String VUV = "VUV";
    public static String VEB = "VEB";
    public static String VND = "VND";
    public static String WST = "WST";
    public static String ZMK = "ZMK";
    public static String ZWD = "ZWD";

    public YCurrency(String code) {
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
        YCurrency currency;
        
        if ((o instanceof YCurrency)) {
            currency = (YCurrency) o;
        } else {
            return false;
        }
        
        return this.code.equals(currency.getCode());
    }
}
