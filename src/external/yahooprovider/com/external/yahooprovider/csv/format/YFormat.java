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
package com.external.yahooprovider.csv.format;

import com.external.yahooprovider.csv.exception.UnknownTagException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Logger;

/**
 *  @author    Andrey Pudov        <syscreat@gmail.com>
 *  @version   0.00.00
 *  %name      YFormat.java
 *  %pkg       com.external.yahooprovider.csv.format
 *  %date      7:23:42 AM, Dec 14, 2010
 */
public class YFormat {

    private static final long serialVersionUID = 1186098321738613059L;
    private static final Logger LOG = Logger.getLogger("yahooprovider");

    private ArrayList<YTag> tagList = new ArrayList<YTag>(10);
    private HashMap<String, YTag> tagMap = new HashMap<String, YTag>(10);

    public YFormat() {
        YTag tag = new YTag(YTag.s);
        this.tagList.add(tag);

        tag = new YTag(YTag.c8);
        this.tagList.add(tag);
        tag = new YTag(YTag.g3);
        this.tagList.add(tag);
        tag = new YTag(YTag.a);
        this.tagList.add(tag);
        tag = new YTag(YTag.b2);
        this.tagList.add(tag);
        tag = new YTag(YTag.a5);
        this.tagList.add(tag);
        tag = new YTag(YTag.a2);
        this.tagList.add(tag);
        tag = new YTag(YTag.b);
        this.tagList.add(tag);
        tag = new YTag(YTag.b3);
        this.tagList.add(tag);
        tag = new YTag(YTag.b6);
        this.tagList.add(tag);
        tag = new YTag(YTag.b4);
        this.tagList.add(tag);
        tag = new YTag(YTag.c1);
        this.tagList.add(tag);
        tag = new YTag(YTag.c);
        this.tagList.add(tag);
        tag = new YTag(YTag.m5);
        this.tagList.add(tag);
        tag = new YTag(YTag.m6);
        this.tagList.add(tag);
        tag = new YTag(YTag.m7);
        this.tagList.add(tag);
        tag = new YTag(YTag.m8);
        this.tagList.add(tag);
        tag = new YTag(YTag.k4);
        this.tagList.add(tag);
        tag = new YTag(YTag.k5);
        this.tagList.add(tag);
        tag = new YTag(YTag.j5);
        this.tagList.add(tag);
        tag = new YTag(YTag.j6);
        this.tagList.add(tag);
        tag = new YTag(YTag.k2);
        this.tagList.add(tag);
        tag = new YTag(YTag.p2);
        this.tagList.add(tag);
        tag = new YTag(YTag.c6);
        this.tagList.add(tag);
        tag = new YTag(YTag.c3);
        this.tagList.add(tag);
        tag = new YTag(YTag.r1);
        this.tagList.add(tag);
        tag = new YTag(YTag.d);
        this.tagList.add(tag);
        tag = new YTag(YTag.y);
        this.tagList.add(tag);
        tag = new YTag(YTag.e);
        this.tagList.add(tag);
        tag = new YTag(YTag.j4);
        this.tagList.add(tag);
        tag = new YTag(YTag.e7);
        this.tagList.add(tag);
        tag = new YTag(YTag.e9);
        this.tagList.add(tag);
        tag = new YTag(YTag.e8);
        this.tagList.add(tag);
        tag = new YTag(YTag.q);
        this.tagList.add(tag);
        tag = new YTag(YTag.f6);
        this.tagList.add(tag);
        tag = new YTag(YTag.k);
        this.tagList.add(tag);
        tag = new YTag(YTag.h);
        this.tagList.add(tag);
        tag = new YTag(YTag.l2);
        this.tagList.add(tag);
        tag = new YTag(YTag.g4);
        this.tagList.add(tag);
        tag = new YTag(YTag.g1);
        this.tagList.add(tag);
        tag = new YTag(YTag.g5);
        this.tagList.add(tag);
        tag = new YTag(YTag.g6);
        this.tagList.add(tag);
        tag = new YTag(YTag.v1);
        this.tagList.add(tag);
        tag = new YTag(YTag.v7);
        this.tagList.add(tag);
        tag = new YTag(YTag.d1);
        this.tagList.add(tag);
        tag = new YTag(YTag.l1);
        this.tagList.add(tag);
        tag = new YTag(YTag.k3);
        this.tagList.add(tag);
        tag = new YTag(YTag.t1);
        this.tagList.add(tag);
        tag = new YTag(YTag.l);
        this.tagList.add(tag);
        tag = new YTag(YTag.k1);
        this.tagList.add(tag);
        tag = new YTag(YTag.g);
        this.tagList.add(tag);
        tag = new YTag(YTag.l3);
        this.tagList.add(tag);
        tag = new YTag(YTag.j);
        this.tagList.add(tag);
        tag = new YTag(YTag.j1);
        this.tagList.add(tag);
        tag = new YTag(YTag.j3);
        this.tagList.add(tag);
        tag = new YTag(YTag.i);
        this.tagList.add(tag);
        tag = new YTag(YTag.m4);
        this.tagList.add(tag);
        tag = new YTag(YTag.m3);
        this.tagList.add(tag);
        tag = new YTag(YTag.n);
        this.tagList.add(tag);
        tag = new YTag(YTag.n4);
        this.tagList.add(tag);
        tag = new YTag(YTag.o);
        this.tagList.add(tag);
        tag = new YTag(YTag.i5);
        this.tagList.add(tag);
        tag = new YTag(YTag.r);
        this.tagList.add(tag);
        tag = new YTag(YTag.r2);
        this.tagList.add(tag);
        tag = new YTag(YTag.r5);
        this.tagList.add(tag);
        tag = new YTag(YTag.p);
        this.tagList.add(tag);
        tag = new YTag(YTag.p6);
        this.tagList.add(tag);
        tag = new YTag(YTag.r6);
        this.tagList.add(tag);
        tag = new YTag(YTag.r7);
        this.tagList.add(tag);
        tag = new YTag(YTag.p1);
        this.tagList.add(tag);
        tag = new YTag(YTag.p5);
        this.tagList.add(tag);
        tag = new YTag(YTag.w);
        this.tagList.add(tag);
        tag = new YTag(YTag.m);
        this.tagList.add(tag);
        tag = new YTag(YTag.m2);
        this.tagList.add(tag);
        tag = new YTag(YTag.s1);
        this.tagList.add(tag);
        tag = new YTag(YTag.s7);
        this.tagList.add(tag);
        tag = new YTag(YTag.x);
        this.tagList.add(tag);
        tag = new YTag(YTag.t8);
        this.tagList.add(tag);
        tag = new YTag(YTag.t7);
        this.tagList.add(tag);
        tag = new YTag(YTag.d2);
        this.tagList.add(tag);
        tag = new YTag(YTag.t6);
        this.tagList.add(tag);
        tag = new YTag(YTag.w1);
        this.tagList.add(tag);
        tag = new YTag(YTag.w4);
        this.tagList.add(tag);
        tag = new YTag(YTag.v);
        this.tagList.add(tag);

        tag = new YTag(YTag.e1);
        this.tagList.add(tag);

        for (YTag tag1 : this.tagList) {
            this.tagMap.put(tag1.getCode(), tag1);
        }
    }

    public String getUrlFormatParameter() {
        StringBuilder sb = new StringBuilder(10);

        for (YTag tag : this.tagList) {
            if (tag.isEnabled()) {
                sb.append(tag.getCode());
            }
        }
        
        return sb.toString();
    }

    public ArrayList<YTag> getEnabledTags() {
        ArrayList<YTag> list = new ArrayList<YTag>(10);

        for (YTag tag : this.tagList) {
            if (tag.isEnabled()) {
                list.add(tag);
            }
        }

        return list;
    }

    public void setStatusOn(String code) {
        YTag tag = (YTag) this.tagMap.get(code);

        if (tag != null) {
            tag.setEnabled(true);
        } else {
            throw new UnknownTagException();
        }

    }

    public void setStatusOff(String code) {
        YTag tag = (YTag) this.tagMap.get(code);
        
        if (tag != null) {
            tag.setEnabled(false);
        } else {
            throw new UnknownTagException();
        }
    }
}
