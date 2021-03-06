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
import java.awt.event.ActionEvent;

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

    private String rootName;

    // Create the listener list
    protected javax.swing.event.EventListenerList listenerList =
            new javax.swing.event.EventListenerList();

    private enum Node {
        Accounts, AccountItem, Servers, ServerItem,
        NOVALUE;

        public static Node toNode(String str) {
            try {
                return valueOf(str);
            } catch (Exception e) {
                LOG.warning(e.getMessage());
                return NOVALUE;
            }
        }
    }

    // <editor-fold defaultstate="collapsed" desc="EventListeners Code">

    // This methods allows classes to register for MyEvents
    public synchronized void addUserLoginEvent(CommonTreeUserLoginEventListener listener) {
        listenerList.add(CommonTreeUserLoginEventListener.class, listener);
    }

    // This methods allows classes to unregister for MyEvents
    public synchronized void removeUserLoginEvent(CommonTreeUserLoginEventListener listener) {
        listenerList.remove(CommonTreeUserLoginEventListener.class, listener);
    }

    // This private class is used to fire CommonTreeUserLoginEvent
    private synchronized void fireUserLoginEvent(CommonTreeUserLoginEvent evnt) {
        for (CommonTreeUserLoginEventListener listener :
            listenerList.getListeners(CommonTreeUserLoginEventListener.class)) {
            listener.loginEventOccurred(evnt);
        }
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Popup Menu Code">
    private java.awt.event.MouseAdapter mouseAdapter = new java.awt.event.MouseAdapter() {
        private void PopupEvent(java.awt.event.MouseEvent e) {
            int x = e.getX();
            int y = e.getY();

            javax.swing.JTree tree = (javax.swing.JTree) e.getSource();
            javax.swing.tree.TreePath path = tree.getPathForLocation(x, y);

            if (path == null) {
                return;
            }

            tree.setSelectionPath(path);

            DefaultMutableTreeNode obj =
                    (DefaultMutableTreeNode) path.getLastPathComponent();
            
            javax.swing.JPopupMenu popup;

            switch (obj.getLevel()) {
                case 0:
                    break;
                case 1:
                    break;
                case 2:
                    switch (
                        Node.toNode( 
                            ((TreeNode) obj.getPreviousNode().getUserObject()
                                ).getName() )) {
                        case Accounts:
                            popup = getPopupMenu(
                                    Node.AccountItem,
                                    ((TreeNode) obj.getUserObject())
                            );
                            popup.show(tree, x, y);

//                            selectedAccount = (TradeAccount)
//                                (
//                                    (TreeNode) obj.getUserObject()
//                                ).getUserObject();
                            break;
                        case Servers:
                            break;
                    }
                    break;
                default:
                    break;
            }
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

    private javax.swing.JPopupMenu getPopupMenu(Node node, final TreeNode item) {
        javax.swing.JPopupMenu popup = new javax.swing.JPopupMenu();

        switch (node) {
            case Accounts:
                break;
            case AccountItem:
                popup.add(new javax.swing.JMenuItem("Login",
                    Resources.getIcon("/16x16/user_add.png"))).addActionListener(
                        new java.awt.event.ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                fireUserLoginEvent(new CommonTreeUserLoginEvent(
                                        item.getUserObject()));
                            }
                        }
                    );
                popup.add(new javax.swing.JMenuItem("Delete",
                        Resources.getIcon("/16x16/user_remove.png")));
                popup.add(new javax.swing.JSeparator());
                popup.add(new javax.swing.JMenuItem("Open an Account",
                        Resources.getIcon("/16x16/user_accept.png")));
                break;
            case Servers:
                break;
            case ServerItem:
                break;
            default:
                break;
        }

        return popup;
    }
    // </editor-fold>

    public CommonTree() {
        super();

        rootName = "OpenTrader";

        addMouseListener(mouseAdapter);

        putClientProperty("JTree.lineStyle", "Horizontal");
    }
    
//    public void addAccount(TradeAccount account) {
//        accounts.add(account);
//
//        updateTree();
//    }
//
//    public void addServer(TradeServer server) {
//        servers.add(server);
//
//        updateTree();
//    }
//
//    public TradeAccount getSelectedAccount() {
//        return selectedAccount;
//    }
//
//    public TradeServer getServierForAccount(TradeAccount account)
//            throws CommonTreeServerNotFoundException {
//
//        for (TradeServer server : servers) {
//            if (server.getName().equals(account.getServer())) {
//                return server;
//            }
//        }
//
//        throw new CommonTreeServerNotFoundException();
//    }

    public void updateTree() {        
        DefaultMutableTreeNode rootNode = new DefaultMutableTreeNode(rootName);
        DefaultTreeModel defaultTreeModel = new DefaultTreeModel(rootNode);

        DefaultMutableTreeNode accountRootTreeNode =
                new DefaultMutableTreeNode(
                    new TreeNode(
                        "Accounts",
                        Resources.getIcon("/16x16/users.png"),
                        null));
        DefaultMutableTreeNode accountTreeNode = null;

        DefaultMutableTreeNode serverRootTreeNode =
                new DefaultMutableTreeNode(
                    new TreeNode(
                        "Servers",
                        Resources.getIcon("/16x16/computers.png"),
                        null));
        DefaultMutableTreeNode serverTreeNode = null;

        setModel(defaultTreeModel);
        setCellRenderer(new NodeTreeCellRenderer());

//        for (TradeAccount account : accounts) {
//            accountTreeNode = new DefaultMutableTreeNode(
//                    new TreeNode(
//                        account.getName(),
//                        Resources.getIcon("/16x16/computer.png"),
//                        account));
//            accountRootTreeNode.add(accountTreeNode);
//        }
//
//        rootNode.add(accountRootTreeNode);
//
//        for (TradeServer server : servers) {
//            serverTreeNode = new DefaultMutableTreeNode(
//                    new TreeNode(
//                        server.getName(),
//                        Resources.getIcon("/16x16/computer.png"),
//                        server));
//            serverRootTreeNode.add(serverTreeNode);
//        }
//
//        rootNode.add(serverRootTreeNode);

        this.updateUI();

        /* display jtree without root node */
        //this.setRootVisible(false);
        
        for (int i = 0; i < this.getRowCount(); i++) {
            this.expandRow(i);
        }
    }

    private class NodeTreeCellRenderer
            implements javax.swing.tree.TreeCellRenderer {

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

    private class TreeNode {

        private String              name;
        private javax.swing.Icon    icon;
        private Object              userObject;

        TreeNode (String name, javax.swing.Icon icon, Object userObject) {
            this.name = name;
            this.icon = icon;
            this.userObject = userObject;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setUserObject(Object userObject) {
            this.userObject = userObject;
        }

        public javax.swing.Icon getIcon() {
            return icon;
        }

        public void setIcon(javax.swing.Icon icon) {
            this.icon = icon;
        }

        public Object getUserObject() {
            return userObject;
        }

    }

}
