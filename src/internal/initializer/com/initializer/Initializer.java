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

package com.initializer;

import com.resources.Resources;
import java.util.logging.ConsoleHandler;
import java.util.logging.Logger;

/**
 *  @author    Andrey Pudov        <syscreat@gmail.com>
 *  @version   0.00.00
 *  %name      Initializer.java
 *  %pkg       org.initializer
 *  %date      4:01:13 PM, Sep 14, 2010
 */
public class Initializer {

    private static final Logger LOG = Logger.getLogger("opentrader");

    private String      appName;
    private Packager    packager;
    private Resources   resources;

    public Initializer(String appName, Resources res) {
        this.appName = appName;
        this.resources = res;

        packager = new Packager (appName, resources);
    }

    /**
     * Verify app settings in user home directory
     * 
     * @return
     * @throws NotInitializedException
     */
    public boolean verifyUserHome()
            throws NotInitializedException {

        return packager.exist();
    }

    /**
     * Create a configuration files
     * @throws NotInitializedException
     */
    public void createUserHome()
            throws NotInitializedException {
        
        packager.install();
    }

    /**
     * Remove user configuration
     * 
     * @throws NotInitializedException
     */
    public void removeUserHome()
            throws NotInitializedException {

        packager.remove();
    }

    /**
     * Get console handler for logger
     * 
     * @return
     */
    public ConsoleHandler getConsoleHandler() {
        ConsoleHandler cHandler = new ConsoleHandler();
        ConsoleFormatter formatter = new ConsoleFormatter();

        cHandler.setFormatter(formatter);

        return cHandler;
    }
    
}
