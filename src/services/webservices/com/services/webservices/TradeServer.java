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

package com.services.webservices;

import java.util.logging.Logger;

/**
 *  @author    Andrey Pudov        <syscreat@gmail.com>
 *  @version   0.00.00
 *  %name      TradeServer.java
 *  %pkg       com.services.webservices
 *  %date      5:39:50 PM, Sep 24, 2010
 */
public class TradeServer {

    private static final Logger LOG = Logger.getLogger("opentrader");

    private final String webService;
    private final String ratePrimary;
    private final String rateSecondary;
    private final String brand;
    private final String lang;
    private final String notes;

    public TradeServer(
            String webService,
            String ratePrimary,
            String rateSecondary,
            String brand,
            String lang,
            String notes) {
        
        this.webService = webService;
        this.ratePrimary = ratePrimary;
        this.rateSecondary = rateSecondary;
        this.brand = brand;
        this.lang = lang;
        this.notes = notes;
    }

    public String getWebServiceAddress() {
        return webService;
    }

    public String getBrand() {
        return brand;
    }

}
