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

package com.services.webservices.types;

import com.services.webservices.SimpleXMLNodeParser;
import java.util.logging.Logger;

/**
 *  @author    Andrey Pudov        <syscreat@gmail.com>
 *  @version   0.00.00
 *  %name      Key.java
 *  %pkg       com.services.webservices.types
 *  %date      1:47:51 PM, Sep 26, 2010
 */
public class Key {
    
    private static final Logger LOG = Logger.getLogger("opentrader");

    private final String key;

    public Key(String xml) {
        key = new SimpleXMLNodeParser(xml).getNextNode("string");
    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return key;
    }

    /**
     * Compare this Key object with another key object.
     *
     * @param obj   other Object, usually a Key, to compare with.
     * @return      true if the objects are of identical key.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Key) {
            Key k = (Key) obj;

            if (this.key.equals(k.key)) {
                return true;
            }
        }
        
        return false;
    }

    /**
     * Returns a hash code for this key.
     * @return  a hash code value for this object.
     */
    @Override
    public int hashCode() {
        int hash = 5;

        hash = 59 * hash + (this.key != null ? this.key.hashCode() : 0);
        
        return hash;
    }

}
