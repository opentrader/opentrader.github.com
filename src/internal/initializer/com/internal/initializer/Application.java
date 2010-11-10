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

package com.internal.initializer;

import java.io.File;
import java.io.IOException;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.XMLFormatter;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *  @author    Andrey Pudov        <syscreat@gmail.com>
 *  @version   0.00.00
 *  %name      Application.java
 *  %pkg       com.initializer
 *  %date      4:59:08 PM, Sep 17, 2010
 */
public class Application {

    private static final Logger LOG = Logger.getLogger("opentrader");

    public enum LookAndFeel {
        GTK,            /* Solaris, Linux with GTK+ 2.2 or later */
        MOTIF,          /* Other Solaris, Linux */
        // IBM,            /* IBM UNIX Supplied by the system vendor. */
        // HP,             /* HP UX Supplied by the system vendor. */
        WINDOWS,        /* Classic Windows */
        // WINDOWS_XP,     /* Windows XP */
        // WINDOWS_VISTA,  /* Windows Vista */
        // MACINTOSH,      /* Macintosh Supplied by the system vendor. */
        NIMBUS,
        SEAGLASS,
        SYSTEM
    };

    private Application() {
    }

    /**
     * Returns the name of the application.
     * 
     * @return
     */
    public static String getName() {
        return Properties.APP_NAME;
    }

    public static String getUserDir() {
        return Properties.USER_DIR;
    }

    public static String getAppDir() {
        return Properties.APP_DIR;
    }

    public static String getConfigDir() {
        return Properties.CONFIG_DIR;
    }

    public static String getLogDir() {
        return Properties.LOG_DIR;
    }

    public static String getAccountsDir() {
        return Properties.ACCOUNTS_DIR;
    }

    public static String getServersDir() {
        return Properties.SERVERS_DIR;
    }

    /**
     * Checks if the configuration files in the user directory. Returns TRUE if
     * the file exists and FALSE otherwise.
     *
     * @return 
     * @throws NotInitializedException
     */
    public static boolean installed()
            throws NotInitializedException {
        
        return Packager.exist();
    }

    /**
     * Create a custom directory application configuration file.
     * 
     * @throws NotInitializedException
     */
    public static void install()
            throws NotInitializedException {

        Packager.install();
    }

    /**
     * Removes the user directory the application configuration file.
     * 
     * @throws NotInitializedException
     */
    public static void remove()
            throws NotInitializedException {

        Packager.remove();
    }

    /**
     * Returns the handler console output applications. The output is done using
     * a class format - ConsoleFormatter.
     *
     * @return
     */
    public static ConsoleHandler getConsoleHandler() {
        ConsoleHandler cHandler = new ConsoleHandler();
        ConsoleFormatter formatter = new ConsoleFormatter();

        cHandler.setFormatter(formatter);

        return cHandler;
    }

    public static FileHandler getFileHandler() 
            throws IOException {

        /** the maximum number of bytes to write to any one file */
        int limit = 1024 * 1024;

        /** the number of files to use */
        int count = 10;

        /** specifies append mode */
        boolean append = false;
        
        FileHandler fHandler = new FileHandler(
                        Properties.LOG_DIR +
                        File.separator + Properties.APP_NAME + "%g.log",
                    limit, count, append);
        fHandler.setFormatter(new XMLFormatter());

        return fHandler;
    }

    /**
     * Set application look and feel
     *
     * @param laf
     * @throws ClassNotFoundException
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws UnsupportedLookAndFeelException
     */
    public static void setLookAndFeel(LookAndFeel laf)
            throws ClassNotFoundException,
                InstantiationException, 
                IllegalAccessException, 
                UnsupportedLookAndFeelException {

        switch (laf) {
            case GTK:
                UIManager.setLookAndFeel(
                    "com.sun.java.swing.plaf.gtk.GTKLookAndFeel");
                break;
            case MOTIF:
                UIManager.setLookAndFeel(
                    "com.sun.java.swing.plaf.motif.MotifLookAndFeel");
                break;
            case WINDOWS:
                UIManager.setLookAndFeel(
                    "com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
                break;
            case NIMBUS:
                UIManager.setLookAndFeel(
                    "com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
                break;
            case SEAGLASS:
                UIManager.setLookAndFeel(
                    "com.seaglasslookandfeel.SeaGlassLookAndFeel");
                break;
            case SYSTEM:
                UIManager.setLookAndFeel(
                    UIManager.getSystemLookAndFeelClassName());
                break;
            default:
                UIManager.setLookAndFeel(
                    UIManager.getSystemLookAndFeelClassName());
                break;
        }
    }

}
