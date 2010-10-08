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

import java.awt.Point;
import java.util.Hashtable;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;

/**
 *  The methods in this class allow the JTable component to get
 *  and display data about the files in a specified directory.
 *  It represents a table with six columns: filename, size, modification date,
 *  plus three columns for flags: directory, readable, writable.
 *
 *  @author    Andrey Pudov        <syscreat@gmail.com>
 *  @version   0.00.00
 *  %name      SymbolTableModel.java
 *  %pkg       com.opentrader.ui.controls
 *  %date      2:24:42 PM, Sep 23, 2010
 */
public class SymbolTableModel extends AbstractTableModel {

    private static final long serialVersionUID = 327356327567177397L;

    private static final Logger LOG = Logger.getLogger("opentrader");

    private Class[]     columnClasses = new Class[] {
        String.class, double.class, double.class};
    private String[]    columnNames = {"Symbol", "Bid", "Ask"};
    private Hashtable   lookup;

    public SymbolTableModel() {
        lookup = new Hashtable();
    }

    /**
     *
     * @return
     */
    @Override
    public int getRowCount() {
        return lookup.size();
    }

    /**
     *
     * @return
     */
    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    /**
     *
     * @param row
     * @param col
     * @return
     */
    @Override
    public Object getValueAt(int row, int col) {
        return lookup.get(new Point(row, col));
    }

    @Override
    public String getColumnName(int col) {
        return columnNames[col];
    }

    /**
     * JTable uses this method to determine the default renderer/ editor for
     * each cell.
     *
     * @param c
     * @return
     */
    @Override
    public Class getColumnClass(int c) {
        return columnClasses[c];
    }

    /**
     *
     * @param value
     * @param row
     * @param column
     */
    @Override
    public void setValueAt(Object value, int row, int column) {
        //
    }

}