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
package com.opentrader.market.feeds;

/**
 *  ISO 4217 Currency Code List based
 * 
 *  @author    Andrey Pudov        <syscreat@gmail.com>
 *  @version   0.00.00
 *  %name      Currency.java
 *  %pkg       feeds
 *  %date      6:06:57 PM, Dec 4, 2010
 */
public interface Currency {
    
    String AED = "AED";     /* United Arab Emirates, Dirhams */
    String AFN = "AFN";     /* Afghanistan, Afghanis */
    String ALL = "ALL";     /* Albania, Leke */
    String AMD = "AMD";     /* Armenia, Drams */
    String ANG = "ANG";     /* Netherlands Antilles, Guilders (also called Florins) */
    String AOA = "AOA";     /* Angola, Kwanza */
    String ARS = "ARS";     /* Argentina, Pesos */
    String AUD = "AUD";     /* Australia, Dollars */
    String AWG = "AWG";     /* Aruba, Guilders (also called Florins) */
    String AZN = "AZN";     /* Azerbaijan, New Manats */
    String BAM = "BAM";     /* Bosnia and Herzegovina, Convertible Marka */
    String BBD = "BBD";     /* Barbados, Dollars */
    String BDT = "BDT";     /* Bangladesh, Taka */
    String BGN = "BGN";     /* Bulgaria, Leva */
    String BHD = "BHD";     /* Bahrain, Dinars */
    String BIF = "BIF";     /* Burundi, Francs */
    String BMD = "BMD";     /* Bermuda, Dollars */
    String BND = "BND";     /* Brunei Darussalam, Dollars */
    String BOB = "BOB";     /* Bolivia, Bolivianos */
    String BRL = "BRL";     /* Brazil, Brazil Real */
    String BSD = "BSD";     /* Bahamas, Dollars */
    String BTN = "BTN";     /* Bhutan, Ngultrum */
    String BWP = "BWP";     /* Botswana, Pulas */
    String BYR = "BYR";     /* Belarus, Rubles */
    String BZD = "BZD";     /* Belize, Dollars */
    String CAD = "CAD";     /* Canada, Dollars */
    String CDF = "CDF";     /* Congo/Kinshasa, Congolese Francs */
    String CHF = "CHF";     /* Switzerland, Francs */
    String CLP = "CLP";     /* Chile, Pesos */
    String CNY = "CNY";     /* China, Yuan Renminbi */
    String COP = "COP";     /* Colombia, Pesos */
    String CRC = "CRC";     /* Costa Rica, Colones */
    String CUP = "CUP";     /* Cuba, Pesos */
    String CVE = "CVE";     /* Cape Verde, Escudos */
    String CYP = "CYP";     /* Cyprus, Pounds (expires 2008-Jan-31) */
    String CZK = "CZK";     /* Czech Republic, Koruny */
    String DJF = "DJF";     /* Djibouti, Francs */
    String DKK = "DKK";     /* Denmark, Kroner */
    String DOP = "DOP";     /* Dominican Republic, Pesos */
    String DZD = "DZD";     /* Algeria, Algeria Dinars */
    String EEK = "EEK";     /* Estonia, Krooni */
    String EGP = "EGP";     /* Egypt, Pounds */
    String ERN = "ERN";     /* Eritrea, Nakfa */
    String ETB = "ETB";     /* Ethiopia, Birr */
    String EUR = "EUR";     /* Euro Member Countries, Euro */
    String FJD = "FJD";     /* Fiji, Dollars */
    String FKP = "FKP";     /* Falkland Islands (Malvinas), Pounds */
    String GBP = "GBP";     /* United Kingdom, Pounds */
    String GEL = "GEL";     /* Georgia, Lari */
    String GGP = "GGP";     /* Guernsey, Pounds */
    String GHS = "GHS";     /* Ghana, Cedis */
    String GIP = "GIP";     /* Gibraltar, Pounds */
    String GMD = "GMD";     /* Gambia, Dalasi */
    String GNF = "GNF";     /* Guinea, Francs */
    String GTQ = "GTQ";     /* Guatemala, Quetzales */
    String GYD = "GYD";     /* Guyana, Dollars */
    String HKD = "HKD";     /* Hong Kong, Dollars */
    String HNL = "HNL";     /* Honduras, Lempiras */
    String HRK = "HRK";     /* Croatia, Kuna */
    String HTG = "HTG";     /* Haiti, Gourdes */
    String HUF = "HUF";     /* Hungary, Forint */
    String IDR = "IDR";     /* Indonesia, Rupiahs */
    String ILS = "ILS";     /* Israel, New Shekels */
    String IMP = "IMP";     /* Isle of Man, Pounds */
    String INR = "INR";     /* India, Rupees */
    String IQD = "IQD";     /* Iraq, Dinars */
    String IRR = "IRR";     /* Iran, Rials */
    String ISK = "ISK";     /* Iceland, Kronur */
    String JEP = "JEP";     /* Jersey, Pounds */
    String JMD = "JMD";     /* Jamaica, Dollars */
    String JOD = "JOD";     /* Jordan, Dinars */
    String JPY = "JPY";     /* Japan, Yen */
    String KES = "KES";     /* Kenya, Shillings */
    String KGS = "KGS";     /* Kyrgyzstan, Soms */
    String KHR = "KHR";     /* Cambodia, Riels */
    String KMF = "KMF";     /* Comoros, Francs */
    String KPW = "KPW";     /* Korea (North), Won */
    String KRW = "KRW";     /* Korea (South), Won */
    String KWD = "KWD";     /* Kuwait, Dinars */
    String KYD = "KYD";     /* Cayman Islands, Dollars */
    String KZT = "KZT";     /* Kazakhstan, Tenge */
    String LAK = "LAK";     /* Laos, Kips */
    String LBP = "LBP";     /* Lebanon, Pounds */
    String LKR = "LKR";     /* Sri Lanka, Rupees */
    String LRD = "LRD";     /* Liberia, Dollars */
    String LSL = "LSL";     /* Lesotho, Maloti */
    String LTL = "LTL";     /* Lithuania, Litai */
    String LVL = "LVL";     /* Latvia, Lati */
    String LYD = "LYD";     /* Libya, Dinars */
    String MAD = "MAD";     /* Morocco, Dirhams */
    String MDL = "MDL";     /* Moldova, Lei */
    String MGA = "MGA";     /* Madagascar, Ariary */
    String MKD = "MKD";     /* Macedonia, Denars */
    String MMK = "MMK";     /* Myanmar (Burma), Kyats */
    String MNT = "MNT";     /* Mongolia, Tugriks */
    String MOP = "MOP";     /* Macau, Patacas */
    String MRO = "MRO";     /* Mauritania, Ouguiyas */
    String MTL = "MTL";     /* Malta, Liri (expires 2008-Jan-31) */
    String MUR = "MUR";     /* Mauritius, Rupees */
    String MVR = "MVR";     /* Maldives (Maldive Islands), Rufiyaa */
    String MWK = "MWK";     /* Malawi, Kwachas */
    String MXN = "MXN";     /* Mexico, Pesos */
    String MYR = "MYR";     /* Malaysia, Ringgits */
    String MZN = "MZN";     /* Mozambique, Meticais */
    String NAD = "NAD";     /* Namibia, Dollars */
    String NGN = "NGN";     /* Nigeria, Nairas */
    String NIO = "NIO";     /* Nicaragua, Cordobas */
    String NOK = "NOK";     /* Norway, Krone */
    String NPR = "NPR";     /* Nepal, Nepal Rupees */
    String NZD = "NZD";     /* New Zealand, Dollars */
    String OMR = "OMR";     /* Oman, Rials */
    String PAB = "PAB";     /* Panama, Balboa */
    String PEN = "PEN";     /* Peru, Nuevos Soles */
    String PGK = "PGK";     /* Papua New Guinea, Kina */
    String PHP = "PHP";     /* Philippines, Pesos */
    String PKR = "PKR";     /* Pakistan, Rupees */
    String PLN = "PLN";     /* Poland, Zlotych */
    String PYG = "PYG";     /* Paraguay, Guarani */
    String QAR = "QAR";     /* Qatar, Rials */
    String RON = "RON";     /* Romania, New Lei */
    String RSD = "RSD";     /* Serbia, Dinars */
    String RUB = "RUB";     /* Russia, Rubles */
    String RWF = "RWF";     /* Rwanda, Rwanda Francs */
    String SAR = "SAR";     /* Saudi Arabia, Riyals */
    String SBD = "SBD";     /* Solomon Islands, Dollars */
    String SCR = "SCR";     /* Seychelles, Rupees */
    String SDG = "SDG";     /* Sudan, Pounds */
    String SEK = "SEK";     /* Sweden, Kronor */
    String SGD = "SGD";     /* Singapore, Dollars */
    String SHP = "SHP";     /* Saint Helena, Pounds */
    String SLL = "SLL";     /* Sierra Leone, Leones */
    String SOS = "SOS";     /* Somalia, Shillings */
    String SPL = "SPL";     /* Seborga, Luigini */
    String SRD = "SRD";     /* Suriname, Dollars */
    String STD = "STD";     /* São Tome and Principe, Dobras */
    String SVC = "SVC";     /* El Salvador, Colones */
    String SYP = "SYP";     /* Syria, Pounds */
    String SZL = "SZL";     /* Swaziland, Emalangeni */
    String THB = "THB";     /* Thailand, Baht */
    String TJS = "TJS";     /* Tajikistan, Somoni */
    String TMM = "TMM";     /* Turkmenistan, Manats */
    String TND = "TND";     /* Tunisia, Dinars */
    String TOP = "TOP";     /* Tonga, Pa'anga */
    String TRY = "TRY";     /* Turkey, New Lira */
    String TTD = "TTD";     /* Trinidad and Tobago, Dollars */
    String TVD = "TVD";     /* Tuvalu, Tuvalu Dollars */
    String TWD = "TWD";     /* Taiwan, New Dollars */
    String TZS = "TZS";     /* Tanzania, Shillings */
    String UAH = "UAH";     /* Ukraine, Hryvnia */
    String UGX = "UGX";     /* Uganda, Shillings */
    String USD = "USD";     /* United States of America, Dollars */
    String UYU = "UYU";     /* Uruguay, Pesos */
    String UZS = "UZS";     /* Uzbekistan, Sums */
    String VEB = "VEB";     /* Venezuela, Bolivares (expires 2008-Jun-30) */
    String VEF = "VEF";     /* Venezuela, Bolivares Fuertes */
    String VND = "VND";     /* Viet Nam, Dong */
    String VUV = "VUV";     /* Vanuatu, Vatu */
    String WST = "WST";     /* Samoa, Tala */
    String XAF = "XAF";     /* Communauté Financière Africaine BEAC, Francs */
    String XAG = "XAG";     /* Silver, Ounces */
    String XAU = "XAU";     /* Gold, Ounces */
    String XCD = "XCD";     /* East Caribbean Dollars */
    String XDR = "XDR";     /* International Monetary Fund (IMF) Special Drawing Rights */
    String XOF = "XOF";     /* Communauté Financière Africaine BCEAO, Francs */
    String XPD = "XPD";     /* Palladium Ounces */
    String XPF = "XPF";     /* Comptoirs Français du Pacifique Francs */
    String XPT = "XPT";     /* Platinum, Ounces */
    String YER = "YER";     /* Yemen, Rials */
    String ZAR = "ZAR";     /* South Africa, Rand */
    String ZMK = "ZMK";     /* Zambia, Kwacha */
    String ZWD = "ZWD";     /* Zimbabwe, Zimbabwe Dollars */

    String getCode();
}
