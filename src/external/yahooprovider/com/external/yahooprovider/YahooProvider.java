/*
 * OpenTrader Trading Platform
 * The solution for online trading, technical analysis and automated trading.
 *
 * Copyright (C) 2010-2011  Andrey Pudov
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

import com.opentrader.market.feeds.DefaultExchangeProvider;
import com.opentrader.market.feeds.Historic;
import com.opentrader.market.feeds.StockExchange;
import com.opentrader.market.feeds.Symbol;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import java.net.URL;
import java.net.URLConnection;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;
import java.util.logging.Logger;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 *  @author    Andrey Pudov        <syscreat@gmail.com>
 *  @version   0.00.00
 *  %name      YahooProvider.java
 *  %pkg       com.external.yahooprovider
 *  %date      12:00:15 PM, Nov 10, 2010
 */
public class YahooProvider implements DefaultExchangeProvider {

    private static final long serialVersionUID = 1060606745559226566L;
    private static final Logger LOG = Logger.getLogger("yahooprovider");

    private static final int CONNECT_TIME_SECONDS = 2;
    private static final int READ_TIME_SECONDS = 3;

    private static final String provider = "http://finance.yahoo.com/d/quotes.csv";
    private static final String historic = "http://ichart.yahoo.com/table.csv";

    private static enum Format {BASIC}

    /** Yahoo! Volume Leaders http://finance.yahoo.com/actives?e=us */
    private ArrayList<StockExchange> stocks = new ArrayList<StockExchange>(20);
    private ArrayList<Symbol> selectedSymbols = new ArrayList<Symbol>(20);

    public YahooProvider() {
        InputStream in = YahooProvider.class.getResourceAsStream(
                "/com/external/yahooprovider/resources/volumeleaders.xml"
            );

        try {
            SAXParserFactory    factory = SAXParserFactory.newInstance();
            SAXParser           saxParser = factory.newSAXParser();

            class StockHadler extends DefaultHandler {
                private ArrayList<Symbol> symbols = new ArrayList<Symbol>(10);
                private String StockExchange;

                @Override
                public void startElement(String uri, String localName,
                        String qName, Attributes attributes)
                        throws SAXException {

                    if (qName.equals("stockexchange")) {
                        for(int i = 0; i < attributes.getLength(); i++) {
                            if (attributes.getQName(i).equals("name")) {
                                StockExchange = attributes.getValue(i);
                            }
                        }
                    } else if (qName.equals("symbol")) {
                        String code = new String();
                        String description = new String();
                        
                        for(int i = 0; i < attributes.getLength(); i++) {
                            if (attributes.getQName(i).equals("code")) {
                                code = attributes.getValue(i);
                            } else if (attributes.getQName(i).equals("description")) {
                                description = attributes.getValue(i);
                            }
                        }

                        symbols.add(new YSymbol(code, description));
                        //selectedSymbols.add(new YSymbol(code, description));
                    }
                }

                @Override
                public void endElement(String uri, String localName,
                        String qName)
                        throws SAXException {

                    if (qName.equals("stockexchange")) {
                        stocks.add(new YStockExchange(StockExchange, symbols));
                        symbols = new ArrayList<Symbol>(10);
                    }
                }
            }

            StockHadler handler = new StockHadler();
            saxParser.parse(in, handler);
        } catch(org.xml.sax.SAXException e) {
            LOG.warning(e.getMessage());
        } catch(javax.xml.parsers.ParserConfigurationException e) {
            LOG.warning(e.getMessage());
        } catch(java.io.IOException e) {
            LOG.warning(e.getMessage());
        }
    }

    public void connect() {
        URL                 url;
        URLConnection       urlConn;
        BufferedReader      br = null;

        try {
            url = new URL(provider);

            urlConn = url.openConnection();
            urlConn.setConnectTimeout(CONNECT_TIME_SECONDS * 1000);
            urlConn.setReadTimeout(READ_TIME_SECONDS * 1000);
            urlConn.setDoInput(true);
            urlConn.setUseCaches(false);

            br = new BufferedReader(
                    new InputStreamReader(urlConn.getInputStream()));

            String s;
            while ((s = br.readLine()) != null) {
                 //System.out.println(s);
            }
        } catch (java.net.MalformedURLException e) {
            LOG.warning(e.toString());
        } catch (java.io.IOException e) {
            LOG.warning(e.toString());
        } finally {
            try {
                br.close();
            } catch(java.io.IOException e) {
                LOG.warning(e.toString());
            }
        }
        
        try {
            updateSymbols(selectedSymbols, Format.BASIC);
        } catch (java.text.ParseException e) {
            LOG.warning(e.toString());
        } catch (Exception e) {
            LOG.warning(e.toString());
        }
    }

    public List<StockExchange> getStockExchanges() {

        return Collections.unmodifiableList(stocks);
    }
    
    public List<Symbol> getSelectedSymbols() {
        
        try {
            updateSymbols(selectedSymbols, Format.BASIC);
        } catch (java.text.ParseException e) {
            LOG.warning(e.toString());
        } catch (Exception e) {
            LOG.warning(e.toString());
        }
        
        return Collections.unmodifiableList(selectedSymbols);
    }

    public List<Symbol> getSymbolsList(StockExchange stock) {
        
        return stocks.get(stocks.indexOf(stock)).getSymbols();
    }
    
    /**
     * http://ichart.finance.yahoo.com/table.csv?s=STOCK
     * 
     * @param symbol
     *        STOCK is the ticker symbol
     *        You can limit what that returns with some additional parameters:
     *        s - Ticker symbol. This is the only parameter that isn't optional.
     * @param start
     *        Start date for historical prices:
     *        a - Month number, starting with 0 for January.
     *        b - Day number, eg, 1 for the first of the month.
     *        c - Year.
     * @param end
     *        End date for historical prices (default is the most current 
     *        available closing price):
     *        d - Month number, starting with 0 for January.
     *        e - Day number, eg, 1 for the first of the month.
     *        f - Year     * 
     * @param ival 
     *        And finally, the frequency of historical prices:
     *        g - Possible values are 'd' for daily (the default), 'w' for weekly, 
     *        and 'm' for monthly.
     * 
     * @return 
     *        'GOOG', "20100101", "20101201"
     *        http://ichart.yahoo.com/table.csv?s=GOOG&d=11&e=1&f=2010&g=d&a=0&b=1&c=2010&ignore=.csv
     * 
     *        'GOOG', "20090131", "20101201"
     *        http://ichart.yahoo.com/table.csv?s=GOOG&d=11&e=1&f=2010&g=d&a=0&b=31&c=2009&ignore=.csv
     */
    public List<Historic> getHistoricalPrices(Symbol symbol, Date start, Date end, Interval ival) {
        ArrayList<Historic> list = new ArrayList<Historic>(20);
        
        URL                 url;
        URLConnection       urlConn;
        BufferedReader      br = null;
        
        try {            
            /* a - Month number, starting with 0 for January. */
            String a = Integer.toString(Integer.parseInt(new SimpleDateFormat("MM").format(start)) - 1);
            /* b - Day number, eg, 1 for the first of the month. */
            String b = Integer.toString(Integer.parseInt(new SimpleDateFormat("dd").format(start)));
            /* c - Year. */
            String c = Integer.toString(Integer.parseInt(new SimpleDateFormat("yyyy").format(start)));
            
            /* d - Month number, starting with 0 for January. */
            String d = Integer.toString(Integer.parseInt(new SimpleDateFormat("MM").format(end)) - 1);
            /* e - Day number, eg, 1 for the first of the month. */
            String e = Integer.toString(Integer.parseInt(new SimpleDateFormat("dd").format(end)));
            /* f - Year. */
            String f = Integer.toString(Integer.parseInt(new SimpleDateFormat("yyyy").format(end)));
            
            /* frequency of historical prices */
            String g;
            if (ival == Interval.DAYLY) {
                g = "d";
            } else if (ival == Interval.WEEKLY) {
                g = "w";
            } else if (ival == Interval.MONTHLY) {
                g = "m";
            } else {
                g = "d";
            }
            
            url = new URL(
                    historic + 
                    "?s=" + symbol.getCode() +  
                    "&d=" + d + "&e=" + e + "&f=" + f +
                    "&g=" + g +
                    "&a=" + a + "&b=" + b + "&c=" + c +
                    "&ignore=.csv");

            urlConn = url.openConnection();
            urlConn.setConnectTimeout(CONNECT_TIME_SECONDS * 1000);
            urlConn.setReadTimeout(READ_TIME_SECONDS * 1000);
            urlConn.setDoInput(true);
            urlConn.setUseCaches(false);

            br = new BufferedReader(
                    new InputStreamReader(urlConn.getInputStream()));
            YHistoric historyValue;
            StringTokenizer st;
            String s;
            /* read title - Date,Open,High,Low,Close,Volume,Adj Close */
            br.readLine(); 
            while ((s = br.readLine()) != null) {
                // 2010-12-01,563.00,571.57,562.40,564.35,7508200,564.35
                historyValue = new YHistoric();
                st = new StringTokenizer(s, ",");
                
                historyValue.setDate(
                        new SimpleDateFormat("yyyy-MM-dd").parse(
                            st.nextToken()).getTime());
                historyValue.setOpen(Double.parseDouble(st.nextToken()));
                historyValue.setHigh(Double.parseDouble(st.nextToken()));
                historyValue.setLow(Double.parseDouble(st.nextToken()));
                historyValue.setClose(Double.parseDouble(st.nextToken()));
                historyValue.setVolume(Double.parseDouble(st.nextToken()));
                historyValue.setAdjClose(Double.parseDouble(st.nextToken()));
                
                list.add(historyValue);
            }
        } catch (java.net.MalformedURLException e) {
            LOG.warning(e.toString());
        } catch (java.io.IOException e) {
            LOG.warning(e.toString());
        } catch (java.text.ParseException e) {
            LOG.warning(e.toString());
        }finally {
            try {
                br.close();
            } catch(java.io.IOException e) {
                LOG.warning(e.toString());
            }
        } 
        
        return Collections.unmodifiableList(list);
    }
    
    public List<Symbol> getPortfolio() {
        
        return Collections.unmodifiableList(selectedSymbols);
    }
    
    public void setPortfolio(List<Symbol> selectedSymbols) {
        this.selectedSymbols.addAll(selectedSymbols);
    }
            
    public static void main(String[] args) {
        ArrayList <Symbol> symbols = new ArrayList<Symbol>(10);
        symbols.add(new YSymbol("C", ""));
        symbols.add(new YSymbol("YHOO", ""));
        symbols.add(new YSymbol("MSFT", ""));
        
        try {
            YahooProvider yprovider = new YahooProvider();
            yprovider.setPortfolio(symbols);
            yprovider.connect();
            
            for (Historic hvalue : yprovider.getHistoricalPrices(
                    new YSymbol("GOOG", ""), 
                    new SimpleDateFormat("yyyy/MM/dd").parse("2010/01/01"), 
                    new SimpleDateFormat("yyyy/MM/dd").parse("2010/12/01"), 
                    Interval.MONTHLY)) {
                        System.out.println(hvalue.toString());
                    }
        } catch (Exception e) {
            
        }
    }

    private void updateSymbols(List<Symbol> selectedSymbols, Format format) 
            throws ParseException {
        URL                 url;
        URLConnection       urlConn;
        BufferedReader      br = null;

        StringBuilder sb = new StringBuilder(20);
        int count = 0;
        for (Symbol s : selectedSymbols) {
            sb.append(s.getCode());
            if (++count != selectedSymbols.size()) {
                sb.append('+');
            }
        }
        
        selectedSymbols.clear();

        String symbols = sb.toString();
        String options = "snl1d1t1c1p2vx";
        
        switch (format) {
            case BASIC:
                /**
                 * s    Symbol
                 * n    Name
                 * a    Ask //
                 * b    Bid //
                 * l1   Last Trade (Price Only)
                 * d1   Last Trade Date
                 * t1   Last Trade Time
                 * c1   Change (c1 + p2 = c)
                 * p2   Change in Percent (c1 + p2 = c)
                 * v    Volume
                 * x    Stock Exchange
                 */
                options = "snabl1d1t1c1p2vx";
                break;
            default:
                options = "snabl1d1t1c1p2vx";
                break;
        }

        try {
            url = new URL(provider + "?s=" + symbols +  "&f=" + options);

            urlConn = url.openConnection();
            urlConn.setConnectTimeout(CONNECT_TIME_SECONDS * 1000);
            urlConn.setReadTimeout(READ_TIME_SECONDS * 1000);
            urlConn.setDoInput(true);
            urlConn.setUseCaches(false);

            br = new BufferedReader(
                    new InputStreamReader(urlConn.getInputStream()));
            
            YSymbol symbol;
            
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
            SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy hh:mma");

            String s;
            while ((s = br.readLine()) != null) {
                 //System.out.println(s);
                 switch (format) {
                    case BASIC:
                        // ""C","Citigroup, Inc. C",N/A,N/A,4.80,"2/10/2011",
                        // "10:30am",-0.04,"-0.83%",126972024,"NYSE""
                        
                        /* Replace all Yahoo! NOVALUE with standard impl. */
                        s = s.replaceAll("N/A", "-1");
                        
                        String otherThanQuote = " [^\"] ";
                        String quotedString = String.format(" \" %s* \" ", otherThanQuote);
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
                        
                        symbol = new YSymbol(
                                tokens[0].substring(1, tokens[0].length() - 1), 
                                tokens[1].substring(1, tokens[1].length() - 1));
                        symbol.setAsk(Double.parseDouble(tokens[2]));
                        symbol.setBid(Double.parseDouble(tokens[3]));
                        symbol.setLastTradePrice(Double.parseDouble(tokens[4]));
                        symbol.setLastTradeDateAndTime(
                            sdf.parse(
                                tokens[5].substring(1, tokens[5].length() - 1) + 
                                " " + 
                                tokens[6].substring(1, tokens[6].length() - 1)
                            ).getTime());
                        symbol.setChange(Double.parseDouble(tokens[7]));
                        symbol.setChangeInPercent(
                                Double.parseDouble(
                                    tokens[8].substring(
                                        1, 
                                        tokens[8].length() - 2)));
                        symbol.setVolume(Long.parseLong(tokens[9]));
                        symbol.setStockExchange(
                                tokens[10].substring(
                                    1, tokens[10].length() - 1));
                        
                        selectedSymbols.add(symbol);
                        break;
                    default:
                        break;
                }
            }
        } catch (java.net.MalformedURLException e) {
            LOG.warning(e.toString());
        } catch (java.io.IOException e) {
            LOG.warning(e.toString());
        } finally {
            try {
                    br.close();
            } catch(java.io.IOException e) {
                LOG.warning(e.toString());
            }
        }
    }

}
