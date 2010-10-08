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

import com.internal.resources.Resources;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;

import java.util.Stack;
import java.util.logging.Logger;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 *  @author    Andrey Pudov        <syscreat@gmail.com>
 *  @version   0.00.00
 *  %name      Packager.java
 *  %pkg       com.initializer
 *  %date      7:47:54 AM, Sep 15, 2010
 */
class Packager {

    private static final Logger LOG = Logger.getLogger("opentrader");

    private Packager() {
        /**
         * Private constructors prevent a class from being explicitly
         * instantiated by callers. No objects can be constructed, either by
         * the caller or by the native class.
         */
    }

    /**
     * The method checks the availability of the basic configuration directories
     * and files. If that returns True, otherwise False.
     *
     * @return
     *          the value indicates the presence or absence of directories and
     *          files user configuration
     */
    public static boolean exist()
            throws NotInitializedException {

        try {
            SAXParserFactory    factory = SAXParserFactory.newInstance();
            SAXParser           saxParser = factory.newSAXParser();

            class CheckOutHadler extends DefaultHandler {
                /** Stack directory - contains the active directory */
                private Stack<String>   dirs = new Stack<String>();

                private File    file;
                private String  text;
                private boolean state = true;

                @Override
                public void startElement(String uri, String localName,
                        String qName, Attributes attributes)
                        throws SAXException {

                    /*
                     * Analysis of the root tag <filesystem> and choice of the
                     * appropriate root directory. The selection procedure is
                     * based on the analysis of properties of the "type".
                     */
                    if (qName.equals("filesystem")) {
                        for(int i = 0; i < attributes.getLength(); i++) {
                            if (attributes.getQName(i).equals("type")) {
                                if (attributes.getValue(i).equals("userhome")) {
                                    dirs.removeAllElements();
                                    dirs.add(System.getProperty("user.home"));
                                }
                            }
                        }
                    } else if (qName.equals("directory")) {
                        // View the properties belonging to the attribute.
                        for(int i = 0; i < attributes.getLength(); i++) {
                            if (attributes.getQName(i).equals("text")) {
                                dirs.add(attributes.getValue(i));
                            }
                        }

                        file = new File(getPath());
                        if (!file.exists()) {
                            state = false;
                        }
                    } else if (qName.equals("file")) {
                        text = new String();

                        for(int i = 0; i < attributes.getLength(); i++) {
                            if (attributes.getQName(i).equals("text")) {
                                text = attributes.getValue(i);
                            }
                        }

                        file = new File(getPath() + text);
                        if (!file.exists()) {
                            state = false;
                        }
                    }
                }

                @Override
                public void endElement(String uri, String localName,
                        String qName)
                        throws SAXException {

                    if (qName.equals("directory")) {
                        dirs.pop();
                    }
                }

                public boolean getState() {
                    return state;
                }

                private String getPath() {
                    String path = new String();

                    for (int i = 0; i < dirs.size(); i++) {
                        path += dirs.get(i) + "/";
                    }

                    return path;
                }
            }

            CheckOutHadler handler = new CheckOutHadler();

            saxParser.parse(
                    Resources.getStream("/scheme/filesystem.xml"),
                    handler);

            if (!handler.getState()) {
                return false;
            }
        } catch(org.xml.sax.SAXException e) {
            LOG.warning(e.getMessage());
        } catch(javax.xml.parsers.ParserConfigurationException e) {
            LOG.warning(e.getMessage());
        } catch(java.io.IOException e) {
            LOG.warning(e.getMessage());
        }

        return true;
    }

    /**
     * The method creates the necessary files and directories. Check
     * availability of files is not performed.
     */
    public static void install()
            throws NotInitializedException {

        try {
            SAXParserFactory    factory = SAXParserFactory.newInstance();
            SAXParser           saxParser = factory.newSAXParser();

            DefaultHandler handler = new DefaultHandler() {

                /** Stack directory - contains the active directory */
                private Stack<String> dirs = new Stack<String>();

                private File    file;
                private String  text;
                private String  source;

                @Override
                public void startElement(String uri, String localName,
                        String qName, Attributes attributes)
                        throws SAXException {

                    /*
                     * Analysis of the root tag <filesystem> and choice of the
                     * appropriate root directory. The selection procedure is
                     * based on the analysis of properties of the "type".
                     */
                    if (qName.equals("filesystem")) {
                        for(int i = 0; i < attributes.getLength(); i++) {
                            if (attributes.getQName(i).equals("type")) {
                                if (attributes.getValue(i).equals("userhome")) {
                                    dirs.removeAllElements();
                                    dirs.add(System.getProperty("user.home"));
                                }
                            }
                        }
                    } else if (qName.equals("directory")) {
                        // View the properties belonging to the attribute.
                        for(int i = 0; i < attributes.getLength(); i++) {
                            if (attributes.getQName(i).equals("text")) {
                                dirs.add(attributes.getValue(i));
                            }
                        }

                        // Create an new directory.
                        file = new File(getPath());
                        if (!file.mkdir()) {
                            LOG.info("Unable to create the necessary files.");
                            System.exit(Integer.MIN_VALUE);
                        }
                    } else if (qName.equals("file")) {
                        text = new String();
                        source = new String();

                        for(int i = 0; i < attributes.getLength(); i++) {
                            if (attributes.getQName(i).equals("text")) {
                                text = attributes.getValue(i);
                            } else if (
                                    attributes.getQName(i).equals("source")) {
                                source = attributes.getValue(i);
                            }
                        }

                        /*
                         * If the property "source" is set on  - copy a file
                         * from the specified source.
                         */
                        if (source.length() != 0) {
                            try {
                                // allocate the stream
                                final OutputStream output =
                                        new FileOutputStream(getPath() + text);
                                // get an channel from the stream
                                final ReadableByteChannel inputChannel =
                                        Channels.newChannel(
                                            Resources.getStream(source)
                                        );
                                final WritableByteChannel outputChannel =
                                        Channels.newChannel(output);

                                // copy the channels
                                copy(inputChannel, outputChannel);

                                // closing the channels
                                inputChannel.close();
                                outputChannel.close();
                            } catch (java.io.FileNotFoundException e) {
                                LOG.warning(e.getMessage());
                            } catch (java.io.IOException e) {
                                LOG.warning(e.getMessage());
                            }
                        } else {
                            //create File object
                            File emptyFile = new File(getPath() + text);

                            try {
                                if (!emptyFile.createNewFile()) {
                                    LOG.info(
                                      "Error while creating a new empty file.");
                                    System.exit(Integer.MIN_VALUE);
                                }
                            } catch (java.io.IOException e) {
                                LOG.warning(e.getMessage());
                            }
                        }
                    }
                }

                @Override
                public void endElement(String uri, String localName,
                        String qName)
                        throws SAXException {

                    if (qName.equals("directory")) {
                        dirs.pop();
                    }
                }

                //@Override
                //public void characters(char ch[], int start, int length)
                        //throws SAXException {}

                private String getPath() {
                    String path = new String();

                    for (int i = 0; i < dirs.size(); i++) {
                        path += dirs.get(i) + "/";
                    }

                    return path;
                }

                private void copy(
                        final ReadableByteChannel src,
                        final WritableByteChannel dest) throws IOException {
                    final ByteBuffer buffer =
                            ByteBuffer.allocateDirect(16 * 1024);

                    while (src.read(buffer) != -1) {
                        // prepare the buffer to be drained
                        buffer.flip();
                        // write to the channel, may block
                        dest.write(buffer);
                        // If partial transfer, shift remainder down
                        // If buffer is empty, same as doing clear()
                        buffer.compact();
                    }

                    // EOF will leave buffer in fill state
                    buffer.flip();

                    // make sure the buffer is fully drained.
                    while (buffer.hasRemaining()) {
                        dest.write(buffer);
                    }
                }
            };

            saxParser.parse(
                    Resources.getStream("/scheme/filesystem.xml"),
                    handler);
        } catch(org.xml.sax.SAXException e) {
            LOG.warning(e.getMessage());
        } catch(javax.xml.parsers.ParserConfigurationException e) {
            LOG.warning(e.getMessage());
        } catch(java.io.IOException e) {
            LOG.warning(e.getMessage());
        }
    }

    /**
     * The method makes removal of all custom application configuration file
     * (if any).
     */
    public static void remove()
            throws NotInitializedException {

        deleteDirectory(new File(
                System.getProperty("user.home") +
                File.separator +
                "." + Application.getName()));
    }

    private static boolean deleteDirectory(File path) {
        if (path.exists()) {
            File[] files = path.listFiles();

            for (int i = 0; i < files.length; i++) {
                if (files[i].isDirectory()) {
                    deleteDirectory(files[i]);
                } else {
                    files[i].delete();
                }
            }
        }

        return (path.delete());
    }

}
