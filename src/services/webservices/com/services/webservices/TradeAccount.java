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

import com.ximpleware.NavException;
import com.ximpleware.VTDGen;
import com.ximpleware.VTDNav;

import java.io.File;

import java.util.logging.Logger;

/**
 *  @author    Andrey Pudov        <syscreat@gmail.com>
 *  @version   0.00.00
 *  %name      TradeAccount.java
 *  %pkg       com.services.webservices
 *  %date      5:40:09 PM, Sep 24, 2010
 */
public class TradeAccount {

    private static final Logger LOG = Logger.getLogger("opentrader");

    private final String name;
    private final String login;
    private final String password;
    private final String server;

    private enum Properties {
        login, password, server,
        NOVALUE;

        public static Properties toProperties(String str) {
            try {
                return valueOf(str);
            } catch (Exception ex) {
                return NOVALUE;
            }
        }
    }

    public TradeAccount(String name, String login, String password, String server) {
        this.name = name;
        this.login = login;
        this.password = password;
        this.server = server;
    }

    public TradeAccount(File xmlFile) throws NavException {
        VTDGen vg = new VTDGen();

        String nameLocal = null;
        String loginLocal = null;
        String passwordLocal = null;
        String serverLocal = null;

        if (vg.parseFile(xmlFile.getAbsolutePath(), true)) {
            VTDNav vn = vg.getNav();
            // move the cursor to first child named "opentrader"
            if (vn.matchElement("opentrader")){
                if (vn.toElement(VTDNav.FIRST_CHILD, "account")) {
                    vn.toString(vn.getCurrentIndex());
                    nameLocal =  vn.toString(vn.getAttrVal("name"));

                    if (vn.toElement(VTDNav.FIRST_CHILD)){
                        do {
                            switch (
                                    Properties.toProperties(vn.toString(
                                        vn.getCurrentIndex()))) {
                                case login:
                                    loginLocal = (vn.getText() != -1) ?
                                         vn.toString(vn.getText()) :
                                         "";
                                    break;
                                case password:
                                    passwordLocal = (vn.getText() != -1) ?
                                         vn.toString(vn.getText()) :
                                         "";
                                    break;
                                case server:
                                    serverLocal = (vn.getText() != -1) ?
                                         vn.toString(vn.getText()) :
                                         "";
                                    break;
                                default:
                                    LOG.info("Invalid attribute.");
                                    break;
                            }
                        } while (vn.toElement(VTDNav.NEXT_SIBLING));
                    }
                }
            }
        }

        this.name = nameLocal;
        this.login = loginLocal;
        this.password = passwordLocal;
        this.server = serverLocal;
    }

    public String getName() {
        return name;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

}
