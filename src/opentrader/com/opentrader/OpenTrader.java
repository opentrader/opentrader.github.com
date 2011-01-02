/*
 * OpenTrader Trading Platform
 * The solution for online trading, technical analysis and automated trading.
 *
 * Copyright (C) 2010  Andrey Pudov
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

import com.internal.initializer.Application;
import com.opentrader.ui.OpenTraderJFrame;
import java.util.logging.Logger;

/**
 *  @author    Andrey Pudov        <syscreat@gmail.com>
 *  @version   0.00.00
 *  %name      OpenTrader.java
 *  %pkg       atoletrader
 *  %date      5:42:29 AM, Aug 1, 2010
 */
public class OpenTrader {
    
    private static final Logger LOG = Logger.getLogger("opentrader");

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
        try {
            /* Do not sent the message to parent handlers.  */
            LOG.setUseParentHandlers(false);
            LOG.addHandler(Application.getConsoleHandler());

            LOG.info("OpenTrader Trading Platform");
            LOG.info("Copyright 2010 Andrey Pudov. All rights reserved.");
            LOG.info("Use is subject to license terms.\n");

            if(!Application.installed()) {
                LOG.info("Creating user configuration...");
                Application.remove();
                Application.install();
            }

            LOG.addHandler(Application.getFileHandler());

            // Load user configuration.s
            LOG.info("Load user configuration...");

            // Set Look And Feel
            try {
                Application.setLookAndFeel(Application.LookAndFeel.SEAGLASS);
            } catch (Exception e) {
                LOG.warning(e.getMessage());
            }

            //OpenTraderJFrame mainForm = new OpenTraderJFrame();
            //mainForm.setVisible(true);

            //com.external.yahooprovider.YahooProvider provider =
                    //new com.external.yahooprovider.YahooProvider();
            //provider.connect();
        } catch (Exception e) {
            LOG.info("Core Initialization Failed.");
            LOG.severe(e.getMessage());
            System.exit(Integer.MIN_VALUE);
        }
    }
        
}
