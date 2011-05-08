/*
 * OpenTrader Trading Platform
 * The solution for online trading, technical analysis and automated trading.
 *
 * Copyright (C) 2010-2011  Andrey Pudov
 * Andrey Pudov     <syscreat@gmail.com>
 *
 * http://opentrader.github.com/OpenTrader/
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

package com.opentrader;

/**
 *  @author    Andrey Pudov        <syscreat@gmail.com>
 *  @version   0.00.00
 *  %name      OpenTrader.java
 *  %pkg       com.opentrader
 *  %date      5:42:29 AM, Aug 1, 2010
 */
public class OpenTrader {
    
    private static final java.util.logging.Logger LOG 
            = java.util.logging.Logger.getLogger("opentrader");

    static {
        //
    }

    private OpenTrader() {
        /**
         * Private constructors prevent a class from being explicitly
         * instantiated by callers. No objects can be constructed, either by
         * the caller or by the native class.
         */
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        java.util.logging.ConsoleHandler     cHandler 
                = new java.util.logging.ConsoleHandler();
        com.opentrader.util.ConsoleFormatter formatter 
                = new com.opentrader.util.ConsoleFormatter();
        cHandler.setFormatter(formatter);
        
        /* Do not sent the message to parent handlers.  */
        LOG.setUseParentHandlers(false);
        LOG.addHandler(cHandler);

        LOG.info("OpenTrader Trading Platform");
        LOG.info("Copyright 2010 Andrey Pudov. All rights reserved.");
        LOG.info("Use is subject to license terms.\n");
        
        try {
            javax.swing.UIManager.setLookAndFeel(
                    javax.swing.UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException
                 | InstantiationException
                 | IllegalAccessException 
                 | javax.swing.UnsupportedLookAndFeelException e) {
            LOG.warning(e.getMessage());
        }
        
        com.opentrader.ui.OpenTraderJFrame mainJFrame 
                = new com.opentrader.ui.OpenTraderJFrame();
        //mainJFrame.setVisible(true);
        
        
         com.opentrader.market.ExchangeProvider provider
                = new com.opentrader.market.ExchangeProvider();
        
        com.opentrader.market.Symbol symbol =
                new com.opentrader.market.Symbol("GOOG", null);
        
//        java.util.List<com.opentrader.market.Historic>  historic = 
//                provider.getHistorical(new com.opentrader.market.Symbol(
//                        "GOOG", null),
//                    new java.util.GregorianCalendar(
//                            0000, java.util.Calendar.JANUARY, 01).getTime(),
//                    //new java.util.GregorianCalendar(
//                            //2011, java.util.Calendar.JANUARY, 01).getTime(), 
//                    new java.util.Date(),
//                    com.opentrader.market.Interval.MONTHLY);
        
                java.util.List<com.opentrader.market.Historic>  historic = 
                provider.getHistorical(new com.opentrader.market.Symbol(
                        "GOOG", null),
                    new java.util.GregorianCalendar(
                            2011, java.util.Calendar.JANUARY, 01).getTime(),
                    new java.util.Date(),
                    com.opentrader.market.Interval.WEEKLY);
//        
//        for (com.opentrader.market.Historic value : historic) {
//            System.out.println(value);
//        }
//        
        
//        com.opentrader.market.Quote quote = provider.getQuote(symbol);
//        System.out.println(quote.toString());
          com.opentrader.ui.components.Chart chart 
                  = new com.opentrader.ui.components.Chart();
          chart.setHistoric(symbol, historic);
          
//          javax.swing.JFrame frame = new javax.swing.JFrame();
//          frame.setSize(500, 500);
//          frame.add(chart);
//          frame.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
//          frame.setVisible(true);
    }
        
}
