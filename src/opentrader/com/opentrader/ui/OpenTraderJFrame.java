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

package com.opentrader.ui;

import com.opentrader.ui.controls.SymbolList;
import java.util.logging.Logger;
import javax.swing.ImageIcon;

/**
 *  @author    Andrey Pudov        <syscreat@gmail.com>
 *  @version   0.00.00
 *  %name      OpenTraderJFrame.java
 *  %pkg       org.opentrader.ui
 *  %date      2:44:13 PM, Aug 6, 2010
 */
public class OpenTraderJFrame extends javax.swing.JFrame {

    private static final long serialVersionUID = 868169820364193080L;

    private static final Logger LOG = Logger.getLogger("opentrader");
   
    private javax.swing.JMenuBar        jMenuBar1;
    private javax.swing.JMenu           jMenu1;
    private javax.swing.JMenu           jMenu2;
    
    private javax.swing.JPanel          jPanelTrade;
    private javax.swing.JPanel          jPanelHistory;
    private javax.swing.JPanel          jPanelNews;
    private javax.swing.JPanel          jPanelAlerts;
    private javax.swing.JPanel          jPanelMailbox;
    private javax.swing.JPanel          jPanelJournal;
    private javax.swing.JPanel          jPanelCommon;
    private javax.swing.JPanel          jPanel3;
    private javax.swing.JPanel          jPanelChart;
    
    private javax.swing.JSplitPane      jSplitPaneVertical;
    private javax.swing.JSplitPane      jSplitPaneHorizontal;
    
    private javax.swing.JTabbedPane     jTabbedPaneBottom;
    private javax.swing.JTabbedPane     jTabbedPaneMain;
    private javax.swing.JTabbedPane     jTabbedPaneCommon;
    
    private javax.swing.JTextField      jTextFieldSearch;
    
    private javax.swing.JToolBar        jToolBarMain;

    private javax.swing.JScrollPane     scrollPaneSymbol;

    private SymbolList                  symbolList;

    /** Creates new form OpenTraderJFrame */
    public OpenTraderJFrame() {
        initComponents();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {
        
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();

        jPanelTrade = new javax.swing.JPanel();
        jPanelHistory = new javax.swing.JPanel();
        jPanelNews = new javax.swing.JPanel();
        jPanelAlerts = new javax.swing.JPanel();
        jPanelMailbox = new javax.swing.JPanel();
        jPanelJournal = new javax.swing.JPanel();
        jPanelCommon = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanelChart = new javax.swing.JPanel();

        jSplitPaneVertical = new javax.swing.JSplitPane();
        jSplitPaneHorizontal = new javax.swing.JSplitPane();

        jTabbedPaneBottom = new javax.swing.JTabbedPane();
        jTabbedPaneCommon = new javax.swing.JTabbedPane();
        jTabbedPaneMain = new javax.swing.JTabbedPane();

        jTextFieldSearch = new javax.swing.JTextField();
        
        jToolBarMain = new javax.swing.JToolBar();

        scrollPaneSymbol = new javax.swing.JScrollPane();

        symbolList = new SymbolList();


        /** UI properties */
        rootPane.putClientProperty("SeaGlass.UnifiedToolbarLook", Boolean.TRUE);
        rootPane.putClientProperty("JRootPane.MenuInTitle", Boolean.TRUE);
        jTextFieldSearch.putClientProperty("JTextField.variant", "search");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("OpenTrader Trading Platform");
//        setIconImage(
//            new ImageIcon(
//                getClass().getResource(
//                    "/org/opentrader/resource/icons/shield_64.png")
//            ).getImage());
        setMinimumSize(new java.awt.Dimension(800, 600));
        

        jToolBarMain.setFloatable(false);
        jToolBarMain.setRollover(true);
        jToolBarMain.add(javax.swing.Box.createHorizontalGlue());

        jTextFieldSearch.setText("");
        jTextFieldSearch.setMinimumSize(new java.awt.Dimension(200,28));
        jTextFieldSearch.setPreferredSize(new java.awt.Dimension(200,28));
        jToolBarMain.add(jTextFieldSearch);

        javax.swing.GroupLayout jPanelCommonLayout =
                new javax.swing.GroupLayout(jPanelCommon);
        jPanelCommon.setLayout(jPanelCommonLayout);
        jPanelCommonLayout.setHorizontalGroup(
            jPanelCommonLayout.createParallelGroup(
                javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 147, Short.MAX_VALUE)
        );
        jPanelCommonLayout.setVerticalGroup(
            jPanelCommonLayout.createParallelGroup(
                javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 104, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel3Layout = 
                new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(
                javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 147, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(
                javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 68, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanelChartLayout =
                new javax.swing.GroupLayout(jPanelChart);
        jPanelChart.setLayout(jPanelChartLayout);
        jPanelChartLayout.setHorizontalGroup(
            jPanelChartLayout.createParallelGroup(
                javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 147, Short.MAX_VALUE)
        );
        jPanelChartLayout.setVerticalGroup(
            jPanelChartLayout.createParallelGroup(
                javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 68, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanelTradeLayout =
                new javax.swing.GroupLayout(jPanelTrade);
        jPanelTrade.setLayout(jPanelTradeLayout);
        jPanelTradeLayout.setHorizontalGroup(
            jPanelTradeLayout.createParallelGroup(
                javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 656, Short.MAX_VALUE)
        );
        jPanelTradeLayout.setVerticalGroup(
            jPanelTradeLayout.createParallelGroup(
                javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 78, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanelHistoryLayout =
                new javax.swing.GroupLayout(jPanelHistory);
        jPanelHistory.setLayout(jPanelHistoryLayout);
        jPanelHistoryLayout.setHorizontalGroup(
            jPanelHistoryLayout.createParallelGroup(
                javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 656, Short.MAX_VALUE)
        );
        jPanelHistoryLayout.setVerticalGroup(
            jPanelHistoryLayout.createParallelGroup(
                javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 78, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanelNewsLayout =
                new javax.swing.GroupLayout(jPanelNews);
        jPanelNews.setLayout(jPanelNewsLayout);
        jPanelNewsLayout.setHorizontalGroup(
            jPanelNewsLayout.createParallelGroup(
                javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 656, Short.MAX_VALUE)
        );
        jPanelNewsLayout.setVerticalGroup(
            jPanelNewsLayout.createParallelGroup(
                javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 78, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanelAlertsLayout =
                new javax.swing.GroupLayout(jPanelAlerts);
        jPanelAlerts.setLayout(jPanelAlertsLayout);
        jPanelAlertsLayout.setHorizontalGroup(
            jPanelAlertsLayout.createParallelGroup(
                javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 656, Short.MAX_VALUE)
        );
        jPanelAlertsLayout.setVerticalGroup(
            jPanelAlertsLayout.createParallelGroup(
                javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 78, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanelMailboxLayout =
                new javax.swing.GroupLayout(jPanelMailbox);
        jPanelMailbox.setLayout(jPanelMailboxLayout);
        jPanelMailboxLayout.setHorizontalGroup(
            jPanelMailboxLayout.createParallelGroup(
                javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 656, Short.MAX_VALUE)
        );
        jPanelMailboxLayout.setVerticalGroup(
            jPanelMailboxLayout.createParallelGroup(
                javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 78, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanelJournalLayout =
                new javax.swing.GroupLayout(jPanelJournal);
        jPanelJournal.setLayout(jPanelJournalLayout);
        jPanelJournalLayout.setHorizontalGroup(
            jPanelJournalLayout.createParallelGroup(
                javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 656, Short.MAX_VALUE)
        );
        jPanelJournalLayout.setVerticalGroup(
            jPanelJournalLayout.createParallelGroup(
                javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 78, Short.MAX_VALUE)
        );
 
        scrollPaneSymbol.setViewportView(symbolList);

        jTabbedPaneCommon.setTabPlacement(javax.swing.JTabbedPane.BOTTOM);
        jTabbedPaneCommon.addTab("Symbols", scrollPaneSymbol);
        jTabbedPaneCommon.addTab("Common", jPanelCommon);

        jTabbedPaneMain.setTabPlacement(javax.swing.JTabbedPane.BOTTOM);
        jTabbedPaneMain.addTab("Chart", jPanelChart);

        jTabbedPaneBottom.setTabPlacement(javax.swing.JTabbedPane.BOTTOM);
        jTabbedPaneBottom.addTab("Trade", jPanelTrade);
        jTabbedPaneBottom.addTab("Account Hostory", jPanelHistory);
        jTabbedPaneBottom.addTab("News", jPanelNews);
        jTabbedPaneBottom.addTab("Alerts", jPanelAlerts);
        jTabbedPaneBottom.addTab("Mailbox", jPanelMailbox);
        jTabbedPaneBottom.addTab("Journal", jPanelJournal);

        jSplitPaneVertical.setDividerLocation(350);
        jSplitPaneVertical.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);
        jSplitPaneVertical.setTopComponent(jSplitPaneHorizontal);
        jSplitPaneVertical.setBottomComponent(jTabbedPaneBottom);

        jSplitPaneHorizontal.setDividerLocation(200);
        jSplitPaneHorizontal.setLeftComponent(jTabbedPaneCommon);
        jSplitPaneHorizontal.setRightComponent(jTabbedPaneMain);

        jMenu1.setText("File");
        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");
        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = 
                new javax.swing.GroupLayout(getContentPane());
        
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(
                jToolBarMain,
                javax.swing.GroupLayout.DEFAULT_SIZE,
                664,
                Short.MAX_VALUE)
            .addComponent(
                jSplitPaneVertical,
                javax.swing.GroupLayout.DEFAULT_SIZE,
                664,
                Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(
                    jToolBarMain,
                    javax.swing.GroupLayout.PREFERRED_SIZE,
                    50,
                    javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(
                    javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(
                    jSplitPaneVertical,
                    javax.swing.GroupLayout.PREFERRED_SIZE,
                    370,
                    Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new OpenTraderJFrame().setVisible(true);
            }
        });
    }

}
