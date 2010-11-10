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

package com.opentrader.ui.controls;

import java.util.logging.Logger;

/**
 *  @author    Andrey Pudov        <syscreat@gmail.com>
 *  @version   0.00.00
 *  %name      CommonTreeServerNotFoundException.java
 *  %pkg       com.opentrader.ui.controls
 *  %date      8:41:11 AM, Nov 10, 2010
 */
public class CommonTreeServerNotFoundException extends Exception {
    
    private static final long serialVersionUID = 393434238879758904L;

    private static final Logger LOG = Logger.getLogger("opentrader");

    private String mistake;

    /**
     * Default constructor - initializes instance variable to unknown
     *
     * @param msg
     */
    public CommonTreeServerNotFoundException() {
        super();                // call superclass constructor

        mistake = "unknown";
    }

    /**
     * Constructor receives some kind of message that is saved in an
     * instance variable.
     *
     * @param err
     */
    public CommonTreeServerNotFoundException(String err) {
        super(err);             // call superclass constructor

        mistake = err;          // save message
    }

    /**
     * Public method, callable by exception catcher.
     * It returns the error message.
     * 
     * @return
     */
    public String getError() {
        return mistake;
    }

}
