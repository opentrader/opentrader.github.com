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

import com.internal.resources.Resources;
import com.services.webservices.TradeAccount;
import com.services.webservices.TradeServer;

import java.util.ArrayList;
import java.util.logging.Logger;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

/**
 *  @author    Andrey Pudov        <syscreat@gmail.com>
 *  @version   0.00.00
 *  %name      CommonTree.java
 *  %pkg       com.opentrader.ui.controls
 *  %date      7:15:30 PM, Oct 4, 2010
 */
public class CommonTree extends javax.swing.JTree {

    private static final long serialVersionUID = 992478959333420522L;

    private static final Logger LOG = Logger.getLogger("opentrader");

    private ArrayList<TradeAccount> accounts = new ArrayList<TradeAccount>(10);
    private ArrayList<TradeServer> servers = new ArrayList<TradeServer>(10);

    private String rootName;

    java.awt.event.MouseAdapter ma = new java.awt.event.MouseAdapter() {
        private void PopupEvent(java.awt.event.MouseEvent e) {
            int x = e.getX();
            int y = e.getY();

            javax.swing.JTree tree = (javax.swing.JTree) e.getSource();
            javax.swing.tree.TreePath path = tree.getPathForLocation(x, y);

            if (path == null) {
                return;
            }

            tree.setSelectionPath(path);

            DefaultMutableTreeNode obj = (DefaultMutableTreeNode) path.getLastPathComponent();

            //String label = "popup: " + ((TreeNode) obj.getUserObject()).getName();
            String label = "popup: " + ((TreeNode) obj.getUserObject()).getName();

            javax.swing.JPopupMenu popup = new javax.swing.JPopupMenu();
            popup.add(new javax.swing.JMenuItem(label));
            popup.show(tree, x, y);
        }

        @Override
        public void mousePressed(java.awt.event.MouseEvent e) {
            if (e.isPopupTrigger()) {
                PopupEvent(e);
            }
        }

        @Override
        public void mouseReleased(java.awt.event.MouseEvent e) {
            if (e.isPopupTrigger()) {
                PopupEvent(e);
            }
        }
    };

    public CommonTree() {
        super();

        /* display jtree without root node */
        setRootVisible(false);

        rootName = "OpenTrader";

        addMouseListener(ma);

        putClientProperty("JTree.lineStyle", "Horizontal");
    }
    
    public void addAccount(TradeAccount account) {
        accounts.add(account);

        updateTree();
    }

    public void addServer(TradeServer server) {
        servers.add(server);

        updateTree();
    }

    public void updateTree() {
        DefaultMutableTreeNode rootNode = new DefaultMutableTreeNode(rootName);
        DefaultTreeModel defaultTreeModel = new DefaultTreeModel(rootNode);

        DefaultMutableTreeNode accountRootTreeNode =
                new DefaultMutableTreeNode(
                    new TreeNode(
                        "Accounts",
                        Resources.getIcon("/16x16/accounts.png")));
        DefaultMutableTreeNode accountTreeNode = null;

        DefaultMutableTreeNode serverRootTreeNode =
                new DefaultMutableTreeNode(
                    new TreeNode(
                        "Servers",
                        Resources.getIcon("/16x16/servers.png")));
        DefaultMutableTreeNode serverTreeNode = null;

        setModel(defaultTreeModel);
        setCellRenderer(new NodeTreeCellRenderer());

        for (TradeAccount account : accounts) {
            accountTreeNode = new DefaultMutableTreeNode(
                    new TreeNode(
                        account.getName(),
                        Resources.getIcon("/16x16/account.png")));
            accountRootTreeNode.add(accountTreeNode);
        }

        rootNode.add(accountRootTreeNode);

        for (TradeServer server : servers) {
            serverTreeNode = new DefaultMutableTreeNode(
                    new TreeNode(
                        server.getName(),
                        Resources.getIcon("/16x16/server.png")));
            serverRootTreeNode.add(serverTreeNode);
        }

        rootNode.add(serverRootTreeNode);

        this.updateUI();
    }

    class NodeTreeCellRenderer implements javax.swing.tree.TreeCellRenderer {

        private javax.swing.JLabel label;

        NodeTreeCellRenderer() {
            label = new javax.swing.JLabel();
        }

        @Override
        public java.awt.Component getTreeCellRendererComponent(
                javax.swing.JTree tree,
                Object value,
                boolean selected,
                boolean expanded,
                boolean leaf,
                int row,
                boolean hasFocus) {

            Object o = ((DefaultMutableTreeNode) value).getUserObject();
            
            if (o instanceof TreeNode) {
                TreeNode node = (TreeNode) o;

                label.setIcon(node.getIcon());
                label.setText(node.getName());
            } else {
                label.setIcon(null);
                label.setText("" + value);
            }

            //setToolTipText("This book is in the Tutorial series.");

            return label;
        }
    }

    class TreeNode {

        private String              name;
        private javax.swing.Icon    icon;

        TreeNode (String name, javax.swing.Icon icon) {
            this.name = name;
            this.icon = icon;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public javax.swing.Icon getIcon() {
            return icon;
        }

        public void setIcon(javax.swing.Icon icon) {
            this.icon = icon;
        }
    }

}
