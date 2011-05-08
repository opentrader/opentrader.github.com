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
 *  %name      ExchangeProvider.java
 *  %pkg       com.opentrader.market
 *  %date      8:08:20 AM, Apr 15, 2011
 */
public class ExchangeProvider {
    
    private static final java.util.logging.Logger LOG 
            = java.util.logging.Logger.getLogger("opentrader");
    
    private static final int CONNECT_TIME_SECONDS = 20;
    private static final int READ_TIME_SECONDS    = 30;

    private static final String quotesURL 
            = "http://finance.yahoo.com/d/quotes.csv";
    private static final String historicURL 
            = "http://ichart.yahoo.com/table.csv";
    
    public ExchangeProvider() {
        super();
    }
    
    public java.util.List<Quote> getQuoteList(java.util.List<Symbol> symbols) {  
        java.util.List<Quote> quotes  = new java.util.ArrayList<>(20);
                
        java.net.URL           url;
        java.net.URLConnection connection;
        
        /* all format tags*/
//        String format = "aa2a5bb2b3b4b6cc1c3c6c8dd1d2ee1e7e8e9f6ghjkg1g3g4g5g6"
//                        + "ii5j1j3j4j5j6k1k2k3k4k5ll1l2l3mm2m3m4m5m6m7m8nn4op"
//                        + "p1p2p5p6qrr1r2r5r6r7ss1s7t1t6t7t8vv1v7ww1w4xy";
        String format = "snl1d1t1c1p2vx";
        
        StringBuilder sb = new StringBuilder(20);
        int count = 0;
        for (Symbol s : symbols) {
            sb.append(s.getCode());
            if (++count != symbols.size()) {
                sb.append('+');
            }
        }
        
        try {
            url = new java.net.URL(quotesURL + "?s=" + sb.toString() 
                                   +  "&f=" + format);

            connection = url.openConnection();
            connection.setConnectTimeout(CONNECT_TIME_SECONDS * 1000);
            connection.setReadTimeout(READ_TIME_SECONDS * 1000);
            connection.setDoInput(true);
            connection.setUseCaches(false);
            
            /**
             * G	Era designator          AD
            *  y	Year                    1996; 96
            *  M	Month in year           July; Jul; 07
            *  w	Week in year            27
            *  W	Week in month           2
            *  D	Day in year             189
            *  d	Day in month            10
            *  F	Day of week in month	2
            *  E	Day in week             Tuesday; Tue
            *  a	Am/pm marker            PM
            *  H	Hour in day (0-23)	0
            *  k	Hour in day (1-24)	24
            *  K	Hour in am/pm (0-11)	0
            *  h	Hour in am/pm (1-12)	12
            *  m	Minute in hour          30
            *  s	Second in minute	55
            *  S	Millisecond             978
            *  z	Time zone	Pacific Standard Time; PST; GMT-08:00
            *  Z	Time zone               -0800
             */
            java.text.SimpleDateFormat sdf 
                    = new java.text.SimpleDateFormat("MM/dd/yyyy hh:mma");
            
            try (java.io.BufferedReader br = new java.io.BufferedReader(
                    new java.io.InputStreamReader(connection.getInputStream()))) {
                String s;
                while ((s = br.readLine()) != null) {                    
                    String otherThanQuote = " [^\"] ";
                    String quotedString = String.format(
                            " \" %s* \" ", otherThanQuote);
                    String regex = String.format("(?x) "+ // enable comments, ignore white spaces
                            ",                         "+ // match a comma
                            "(?=                       "+ // start positive look ahead
                            "  (                       "+ //   start group 1
                            "    %s*                   "+ //     match 'otherThanQuote' zero or more times
                            "    %s                    "+ //     match 'quotedString'
                            "  )*                      "+ //   end group 1 and repeat it zero or more times
                            "  %s*                     "+ //   match 'otherThanQuote'
                            "  $                       "+ // match the end of the string
                            ")                         ", // stop positive look ahead
                            otherThanQuote, quotedString, otherThanQuote);
                    String[] tokens = s.split(regex);
                    
                    quotes.add(new Quote(null, tokens));
                }
             }
        } catch (java.net.MalformedURLException 
                 | java.io.IOException ex)
                {
                 //| java.text.ParseException ex) {
            LOG.warning(ex.toString());
        }
        
        return quotes;
    }
    
    public java.util.List<Historic> getHistorical(Symbol symbol, 
                                                  java.util.Date start, 
                                                  java.util.Date end, 
                                                  Interval ival) {

        java.util.List<Historic> historic = new java.util.ArrayList<>(20);

        java.net.URL           url;
        java.net.URLConnection connection;

        /* a - Month number, starting with 0 for January. */
        String a = Integer.toString(Integer.parseInt(
                new java.text.SimpleDateFormat("MM").format(start)) - 1);
        /* b - Day number, eg, 1 for the first of the month. */
        String b = Integer.toString(Integer.parseInt(
                new java.text.SimpleDateFormat("dd").format(start)));
        /* c - Year. */
        String c = Integer.toString(Integer.parseInt(
                new java.text.SimpleDateFormat("yyyy").format(start)));

        /* d - Month number, starting with 0 for January. */
        String d = Integer.toString(Integer.parseInt(
                new java.text.SimpleDateFormat("MM").format(end)) - 1);
        /* e - Day number, eg, 1 for the first of the month. */
        String e = Integer.toString(Integer.parseInt(
                new java.text.SimpleDateFormat("dd").format(end)));
        /* f - Year. */
        String f = Integer.toString(Integer.parseInt(
                new java.text.SimpleDateFormat("yyyy").format(end)));

        /* frequency of historical prices */
        String g;
        switch (ival) {
            case DAYLY:
                g = "d";
                break;
            case WEEKLY:
                g = "w";
                break;
            case MONTHLY:
                g = "m";
                break;
            default:
                g = "d";
                break;
        }

        try {
            url = new java.net.URL(historicURL + "?s=" + symbol.getCode() 
                                   + "&d=" + d + "&e=" + e + "&f=" + f + "&g=" 
                                   + g +"&a=" + a + "&b=" + b + "&c=" + c 
                                   + "&ignore=.csv");

            connection = url.openConnection();
            connection.setConnectTimeout(CONNECT_TIME_SECONDS * 1000);
            connection.setReadTimeout(READ_TIME_SECONDS * 1000);
            connection.setDoInput(true);
            connection.setUseCaches(false);

            try (java.io.BufferedReader br = new java.io.BufferedReader(
                    new java.io.InputStreamReader(connection.getInputStream()))) {
                Historic historicValue;
                java.util.StringTokenizer st;
                String s;

                /* read title - Date,Open,High,Low,Close,Volume,Adj Close */
                br.readLine(); 

                while ((s = br.readLine()) != null) {
                    st = new java.util.StringTokenizer(s, ",");

                    java.util.Date date = new java.util.Date(
                            new java.text.SimpleDateFormat("yyyy-MM-dd").parse(
                                st.nextToken()).getTime());
                    double open     = Double.parseDouble(st.nextToken());
                    double high     = Double.parseDouble(st.nextToken());
                    double low      = Double.parseDouble(st.nextToken());
                    double close    = Double.parseDouble(st.nextToken());
                    double volume   = Double.parseDouble(st.nextToken());
                    double adjClose = Double.parseDouble(st.nextToken());

                    historic.add(new Historic(date, open, high, low, close, 
                                              volume, adjClose));
                }
            }
        } catch (java.net.MalformedURLException 
                 | java.io.IOException
                 | java.text.ParseException ex) {
            LOG.warning(ex.toString());
            
            return null;
        }
        
        java.util.Collections.reverse(historic);

        return java.util.Collections.unmodifiableList(historic);
    }
    
}