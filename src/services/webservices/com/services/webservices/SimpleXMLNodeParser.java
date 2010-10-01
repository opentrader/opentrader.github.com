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
 *  %name      SimpleXMLNodeParser.java
 *  %pkg       com.services.webservices
 *  %date      7:15:59 AM, Aug 6, 2010
 */
public class SimpleXMLNodeParser {

    private static final Logger LOG = Logger.getLogger("opentrader");

    private SimpleXMLNodeParser() {
    }

    // NOTE: Usually these would be < >.  However we are
    // dealing with Microsoft XML Content from Soap Service
    // This is encoded as data is contained within a string tag
    private final String LEFT_BRACE = "&lt;";
    private final String RIGHT_BRACE = "&gt;";

    private String xml_;
    private int    index_;

    /**
    * Constructs the Node Parser with the specified xml content
     * @param xml
     */
    public SimpleXMLNodeParser(String xml) {
        xml_ = xml;
        index_ = 0;

        xml_ = xml_.replaceAll("<", LEFT_BRACE);
        xml_ = xml_.replaceAll(">", RIGHT_BRACE);
    }

    /**
     * getNextNode - Get the contents of the contents of the specified node name
     *
     * @param nodeName
     * @return
     */
    public String getNextNode(String nodeName) {
        String content = null;

        // Get the index of the start node
        String startNode = LEFT_BRACE + nodeName;
        int startIndex = xml_.indexOf(startNode, index_);

        // If we have a start, get the end node
        if (startIndex != -1) {
            String endNode = LEFT_BRACE + "/" + nodeName  +  RIGHT_BRACE;
            int endIndex   = xml_.indexOf(
                    endNode, startIndex + startNode.length());

            if (endIndex != -1) {
                // set the content and the index for the Next Node
                content = xml_.substring(
                        xml_.indexOf(
                            RIGHT_BRACE,
                            startIndex + startNode.length()) +
                            RIGHT_BRACE.length(),
                        endIndex);
                index_ = endIndex + endNode.length();
            }
        }

        // return the content
        return content;
    }

}
