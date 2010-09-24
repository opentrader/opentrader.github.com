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

package com.services.rateservice;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.InetAddress;
import java.net.Socket;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.StringTokenizer;
import java.util.logging.Logger;

/**
 *  @author    Andrey Pudov        <syscreat@gmail.com>
 *  @version   0.00.00
 *  %name      RateService.java
 *  %pkg       org.opentrader.api.rateservice
 *  %date      6:55:02 AM, Aug 12, 2010
 */
public class RateService implements Runnable {

    private static final Logger LOG = Logger.getLogger("opentrader");

    private static final int SOCKET_TIMEOUT_MS = 30000;
    private static final String SOCKET_HEARTBEAT = "\r";
    private static final String VERSION = "1.6.3";

    Object localObject; // empty
    RateServiceListener _listener;
    String _host;
    int _port;
    String _key;
    boolean _compactRates;
    boolean _disconnect;
    boolean _connected;
    Hashtable _compactRatesTable;
    int _successfullConnections;
    int _failedConnections;
    int _consecutiveFailedConnections;
    int _connectionIndex;
    int _connectionLoopCount;
    HashMap _ratesConnection;

    private static final char MESSAGE_START_CH = '\002';
    private static final char MESSAGE_END_CHAR = '\r';

    public RateService(
            RateServiceListener paramRateServiceListener,
            String paramString1,
            String paramString2,
            String paramString3) {
        this._listener = paramRateServiceListener;

        this._key = paramString3;
        this._compactRates = true;
        this._disconnect = false;
        this._connected = false;
        this._successfullConnections = 0;
        this._failedConnections = 0;
        this._consecutiveFailedConnections = 0;

        this._connectionIndex = 0;
        this._connectionLoopCount = 0;
        this._ratesConnection = new HashMap<String, Object>(4);

        loadConnectionInfo(paramString1, paramString2);
    }

    public RateService(
            RateServiceListener paramRateServiceListener,
            String paramString1,
            String paramString2,
            String paramString3,
            boolean paramBoolean) {
        this._listener = paramRateServiceListener;

        this._key = paramString3;
        this._compactRates = paramBoolean;
        this._disconnect = false;
        this._connected = false;
        this._successfullConnections = 0;
        this._failedConnections = 0;
        this._failedConnections = 0;
        this._consecutiveFailedConnections = 0;

        this._connectionIndex = 0;
        this._connectionLoopCount = 0;
        this._ratesConnection = new HashMap<String, Object>(4);

        loadConnectionInfo(paramString1, paramString2);
    }

    @SuppressWarnings("unchecked")
    public boolean loadConnectionInfo(
            String paramString1,
            String paramString2) {
        StringTokenizer localStringTokenizer1 =
                new StringTokenizer(paramString1, ";");
        StringTokenizer localStringTokenizer2 =
                new StringTokenizer(paramString2, ";");

        int i = 0;
        int j = 0;

        while ((localStringTokenizer1.countTokens() != 0) &&
                (localStringTokenizer1.countTokens() ==
                    localStringTokenizer2.countTokens()) &&
                (localStringTokenizer1.hasMoreTokens())) {
           
            HashMap<String, Object> localHashMap =
                    new HashMap<String, Object>(4);

            localHashMap.put("IP", localStringTokenizer1.nextToken());
            localHashMap.put("Port",
                    new Integer(localStringTokenizer2.nextToken()));

            this._ratesConnection.put("RatesConnection" + i, localHashMap);
            ++i;
            j = 1;
        }

        return (j != 0); // return j;
    }

    public static String getVersion() {
        return "1.6.3";
    }

    public boolean connect() {
        if (isConnected()) {
            return false;
        }
        this._disconnect = false;

        int i = 0;
        while (i == 0) {
            localObject = (HashMap) this._ratesConnection.get(
                    "RatesConnection" + this._connectionIndex);
            
            if (localObject == null) {
                this._connectionIndex = 0;
                this._connectionLoopCount += 1;
                try {
                    Thread.sleep(3000L);
                } catch (Exception localException) {
                    // Nothing
                }
            }
            this._connectionIndex += 1;
            this._host = ((String) ((HashMap)localObject).get("IP"));
            this._port = ((Integer) ((HashMap)localObject).get("Port")).intValue();
            i = 1;
        }
        //Object localObject = new Thread(this);
        localObject = new Thread(this);
        ((Thread)localObject).start();

        return true;
    }

    public boolean disconnect() {
        if (!isConnected()) {
          return false;
        }

        this._disconnect = true;

        return true;
    }

    public boolean isConnected() {
        return this._connected;
    }

    public int getSuccessfullCounnectionCount() {
        return this._successfullConnections;
    }

    public int getConnectionLoopCount() {
        return this._connectionLoopCount;
    }

    public int getFailedCounnectionCount() {
        return this._failedConnections;
    }

    public int getFailedConsecutiveCounnectionCount() {
        return this._consecutiveFailedConnections;
    }

    public void run() {
        Socket localSocket = null;
        BufferedInputStream localBufferedInputStream = null;
        BufferedOutputStream localBufferedOutputStream = null;
        long l = 0L;
        Object localObject1 = null;
        
        try {
            localSocket = new Socket(
                    InetAddress.getByName(this._host), this._port);
            localSocket.setSoTimeout(30000);

            System.out.println("RateService: Socket Connected");

            String str1 = this._key;

            if ((this._compactRates == true) &&
                    (this._key.length() == 32)) {
                str1 = str1 + "\\COMPACT";
            }

            localBufferedOutputStream =
                    new BufferedOutputStream(localSocket.getOutputStream());
            localBufferedOutputStream.write(str1.getBytes());
            localBufferedOutputStream.flush();

            System.out.println("RateService: Authenicated Sent");

            localBufferedInputStream =
                    new BufferedInputStream(localSocket.getInputStream());

            while (!this._disconnect) {
                String str2 = null;
                try {
                    str2 = readMessage(localBufferedInputStream);
                } catch (InterruptedIOException localInterruptedIOException) {
                    System.out.println(
                            "RateService: Socket Timeout, checking connection");
                    localBufferedOutputStream.write("\r".getBytes());
                    localBufferedOutputStream.flush();

                    str2 = null;

                    localBufferedInputStream = new BufferedInputStream(
                            localSocket.getInputStream());
                }

                if ((str2 != null) && (str2.trim().compareTo("") != 0)) {
                    if (l == 0L) {
                        this._connected = true;
                        this._successfullConnections += 1;
                        this._consecutiveFailedConnections = 0;
                        this._listener.OnRateServiceConnected();
                    }

                    if (this._compactRates == true)
                        processCompactMessage(str2);
                    else {
                        processStandardMessage(str2);
                    }

                    l += 1L;
                }

                str2 = null;
            }

        } catch (Exception localException1) {
            System.out.println(
                    "RateService: Exception Processing Messages: " +
                    localException1.getClass().getName() +
                    ": " + localException1.getMessage());

            localObject1 = localException1;
        } finally {
            try {
                this._connected = false;

                if (localBufferedInputStream != null) {
                    localBufferedInputStream.close();
                }

                if (localBufferedOutputStream != null) {
                    localBufferedOutputStream.close();
                }

                if (localSocket != null) {
                    localSocket.close();
                }
            } catch (Exception localException2) {
                // Nothing
            }
        }

        if (localObject1 != null) {
            this._failedConnections += 1;
            this._consecutiveFailedConnections += 1;

            if (l > 0L) {
                this._listener.OnRateServiceConnectionLost(
                        (Exception) localObject1);
            } else {
                this._listener.OnRateServiceConnectionFailed(
                        (Exception) localObject1);
            }
        } else {
            this._listener.OnRateServiceDisconnected();
        }
    }

    protected void processStandardMessage(String paramString) {
        Rate localRate = null;

        try {
            String str1 = paramString.substring(23, 26);
            String str2 = paramString.substring(26, 33);
            String str3 = paramString.substring(23);
            StringTokenizer localStringTokenizer1;

            if (str1.compareTo("RTS") == 0) {
                localStringTokenizer1 =
                        new StringTokenizer(paramString.substring(33), "\\");

                if (localStringTokenizer1.hasMoreTokens()) {
                    localRate = new Rate();

                    localRate.setCcyPair(str2);
                    localStringTokenizer1.nextToken();
                    localRate.setBid(Double.valueOf(
                            localStringTokenizer1.nextToken()).doubleValue());
                    localRate.setAsk(Double.valueOf(
                            localStringTokenizer1.nextToken()).doubleValue());
                    localRate.setDealable(localStringTokenizer1.nextToken());
                    localRate.setHigh(Double.valueOf(
                            localStringTokenizer1.nextToken()).doubleValue());
                    localRate.setLow(Double.valueOf(
                            localStringTokenizer1.nextToken()).doubleValue());
                    localStringTokenizer1.nextToken();
                    localRate.setDecimalPlaces(
                            Integer.parseInt(localStringTokenizer1.nextToken()));
                    localRate.setDomain(localStringTokenizer1.nextToken());

                    if (localStringTokenizer1.hasMoreTokens()) {
                        localRate.setClosingBidRate(
                                Double.valueOf(
                                    localStringTokenizer1.nextToken()
                                ).doubleValue());
                    }

                    if (localStringTokenizer1.hasMoreTokens()) {
                        localRate.setBidBankName(
                                localStringTokenizer1.nextToken());
                    }

                    if (localStringTokenizer1.hasMoreTokens()) {
                        localRate.setAskBankName(
                                localStringTokenizer1.nextToken());
                    }

                    this._listener.OnRateServiceRate(localRate);

                    localStringTokenizer1 = null;
                    localRate = null;
                }

            }

            if (str1.compareTo("SUR") == 0) {
                localStringTokenizer1 =
                        new StringTokenizer(paramString.substring(26), "$");

                while (localStringTokenizer1.hasMoreTokens()) {
                    StringTokenizer localStringTokenizer2 =
                            new StringTokenizer(
                                localStringTokenizer1.nextToken(), "\\");

                    if (localStringTokenizer2.countTokens() < 10) {
                        continue;
                    }

                    localRate = new Rate();

                    localRate.setCcyPair(localStringTokenizer2.nextToken());
                    localStringTokenizer2.nextToken();
                    localRate.setBid(Double.valueOf(
                            localStringTokenizer2.nextToken()).doubleValue());
                    localRate.setAsk(Double.valueOf(
                            localStringTokenizer2.nextToken()).doubleValue());
                    localRate.setDealable(localStringTokenizer2.nextToken());
                    localRate.setHigh(Double.valueOf(
                            localStringTokenizer2.nextToken()).doubleValue());
                    localRate.setLow(Double.valueOf(
                            localStringTokenizer2.nextToken()).doubleValue());
                    localStringTokenizer2.nextToken();
                    localRate.setDecimalPlaces(
                            Integer.parseInt(localStringTokenizer2.nextToken()));
                    localRate.setDomain(localStringTokenizer2.nextToken());

                    if (localStringTokenizer2.hasMoreTokens()) {
                        localRate.setClosingBidRate(
                                Double.valueOf(
                                    localStringTokenizer2.nextToken()
                                ).doubleValue());
                    }

                    if (localStringTokenizer2.hasMoreTokens()) {
                        localRate.setBidBankName(
                                localStringTokenizer2.nextToken());
                    }

                    if (localStringTokenizer2.hasMoreTokens()) {
                        localRate.setAskBankName(
                                localStringTokenizer2.nextToken());
                    }

                    this._listener.OnRateServiceRate(localRate);

                    localStringTokenizer2 = null;
                    localRate = null;
                }

                localStringTokenizer1 = null;
            }

        } catch (Exception localException) {
            System.out.println(
                    "RateService: Exception Processing Standard Message: " +
                    localException.getClass().getName() +
                    ": " + localException.getMessage());
        }
    }

    protected void processCompactMessage(String paramString) {
        try {
            if (paramString.compareTo("") != 0) {
                if (paramString.startsWith("S")) {
                    processCompactSMessage(paramString.substring(1));
                } else if (paramString.startsWith("R")) {
                    processCompactRMessage(paramString.substring(1));
                } else if (paramString.startsWith("BUP")) {
                    processCompactBUPMessage(paramString.substring(3));
                } else if (paramString.startsWith("SYS")) {
                    processCompactSYSMessage(paramString.substring(3));
                } else if (paramString.startsWith("MSG")) {
                  processCompactMSGMessage(paramString.substring(3));
                }
            }
        } catch (Exception localException) {
            System.out.println(
                    "RateService: Exception Processing Compact Message: " +
                    localException.getClass().getName() +
                    ": " + localException.getMessage());
        }
    }

    protected void processCompactSMessage(String paramString) throws Exception {
        this._compactRatesTable = new Hashtable();

        StringTokenizer localStringTokenizer1 =
                new StringTokenizer(paramString, "$");

        Rate localRate = null;

        while (localStringTokenizer1.hasMoreTokens()) {
            try {
                if (localStringTokenizer1.hasMoreTokens()) {
                    StringTokenizer localStringTokenizer2 =
                            new StringTokenizer(
                                localStringTokenizer1.nextToken(), "\\");

                    if (localStringTokenizer2.countTokens() >= 9) {
                        localRate = new Rate();

                        localRate.setKey(localStringTokenizer2.nextToken());
                        localRate.setCcyPair(localStringTokenizer2.nextToken());
                        localRate.setBid(Double.valueOf(
                                localStringTokenizer2.nextToken()).doubleValue());
                        localRate.setAsk(Double.valueOf(
                                localStringTokenizer2.nextToken()).doubleValue());
                        localRate.setHigh(Double.valueOf(
                                localStringTokenizer2.nextToken()).doubleValue());
                        localRate.setLow(Double.valueOf(
                                localStringTokenizer2.nextToken()).doubleValue());
                        localRate.setDealable(localStringTokenizer2.nextToken());
                        localRate.setDomain(localStringTokenizer2.nextToken());
                        localRate.setDecimalPlaces(
                                Integer.parseInt(
                                    localStringTokenizer2.nextToken()));

                        if (localStringTokenizer2.hasMoreTokens()) {
                            localRate.setClosingBidRate(
                                    Double.valueOf(
                                            localStringTokenizer2.nextToken()
                                        ).doubleValue());
                        }

                        if (localStringTokenizer2.hasMoreTokens()) {
                            localRate.setBidBankName(localStringTokenizer2.nextToken());
                        }

                        if (localStringTokenizer2.hasMoreTokens()) {
                            localRate.setAskBankName(localStringTokenizer2.nextToken());
                        }

                        this._compactRatesTable.put(localRate.getKey(), localRate);
                        this._listener.OnRateServiceRate(localRate);
                    }

                    localStringTokenizer2 = null;
                    localRate = null;
                }
            } catch (Exception localException) {
                System.out.println(
                        "RateService: Exception Processing Compact S Message: "
                        + localException.getClass().getName()
                        + ": " + localException.getMessage());
            }
        }

        localStringTokenizer1 = null;
    }

    protected void processCompactRMessage(String paramString) throws Exception {
        try {
            StringTokenizer localStringTokenizer1 =
                    new StringTokenizer(paramString, "\\");
            String str = localStringTokenizer1.nextToken();

            Rate localRate = (Rate)this._compactRatesTable.get(str);

            if (localRate != null) {
                StringTokenizer localStringTokenizer2 =
                        new StringTokenizer(paramString, "\\");

                if (localStringTokenizer2.hasMoreTokens()) {
                    localStringTokenizer2.nextToken();
                    localRate.setBid(Double.valueOf(
                            localStringTokenizer2.nextToken()).doubleValue());
                    localRate.setAsk(Double.valueOf(
                            localStringTokenizer2.nextToken()).doubleValue());
                    localRate.setDealable(localStringTokenizer2.nextToken());

                    if (localRate.getBid() < localRate.getLow()) {
                        localRate.setLow(localRate.getBid());
                    }

                    if (localRate.getAsk() > localRate.getHigh()) {
                        localRate.setHigh(localRate.getAsk());
                    }

                    if (localStringTokenizer2.countTokens() >= 7) {
                        if (localStringTokenizer2.hasMoreTokens()) {
                            localRate.setBidBankName(localStringTokenizer2.nextToken());
                        }

                        if (localStringTokenizer2.hasMoreTokens()) {
                            localRate.setAskBankName(localStringTokenizer2.nextToken());
                        }
                    }

                    this._listener.OnRateServiceRate(localRate);
                }

                localStringTokenizer2 = null;
                localRate = null;
            } else {
                System.out.println("RateService: Failed to find currency pair with key: " + str + " Message: " + paramString);
            }
        } catch (Exception localException) {
            System.out.println("RateService: Exception Parsing Compact R Message: " + localException.getClass().getName() + ": " + localException.getMessage());
        }
    }

    protected void processCompactBUPMessage(String paramString) throws Exception {
        try {
            StringTokenizer localStringTokenizer =
                    new StringTokenizer(paramString, "\\");

            if (localStringTokenizer.hasMoreTokens()) {
                Bup localBup = new Bup();

                localBup.setType(localStringTokenizer.nextToken());
                localBup.setProduct(localStringTokenizer.nextToken());

                if (localBup.getType().compareTo("DEAL") == 0) {
                    localBup.setPositionContract(Double.valueOf(
                            localStringTokenizer.nextToken()).doubleValue());
                    localBup.setPositionCounter(Double.valueOf(
                            localStringTokenizer.nextToken()).doubleValue());
                    localBup.setPostedMargin(Double.valueOf(
                            localStringTokenizer.nextToken()).doubleValue());
                    localBup.setRealizedMarginInUSD(Double.valueOf(
                            localStringTokenizer.nextToken()).doubleValue());
                    localBup.setRealizedMarginInBaseCcy(Double.valueOf(
                            localStringTokenizer.nextToken()).doubleValue());
                    localBup.setDealRef(localStringTokenizer.nextToken());
                    localBup.setDealConfirmationNumber(Long.valueOf(
                            localStringTokenizer.nextToken()).longValue());
                    localBup.setPositionInUSD(Double.valueOf(
                            localStringTokenizer.nextToken()).doubleValue());
                    localBup.setPositionAverageRate(Double.valueOf(
                            localStringTokenizer.nextToken()).doubleValue());

                    this._listener.OnRateServiceBUPMessage(localBup);
                } else if (localBup.getType().compareTo("ORD") == 0) {
                    localBup.setOrderReference(localStringTokenizer.nextToken());

                    if (localBup.getOrderReference().compareTo("CANCELLED") != 0) {
                        localBup.setOrderStatus(localStringTokenizer.nextToken());
                    } else {
                        localBup.setOrderStatus(localBup.getOrderReference());
                        localBup.setOrderReference(localBup.getProduct());
                        localBup.setProduct("");
                    }

                    this._listener.OnRateServiceBUPMessage(localBup);
                } else {
                    System.out.println("RateService: Exception Parsing Compact BUP Message: " + localBup.getType() + " is not currently supported.");
                }

                localBup = null;
            }

            localStringTokenizer = null;
        } catch (Exception localException) {
            System.out.println("RateService: Exception Parsing Compact BUP Message: " + localException.getClass().getName() + ": " + localException.getMessage());
        }
    }

    protected void processCompactSYSMessage(String paramString) throws Exception {
        try {
            StringTokenizer localStringTokenizer =
                    new StringTokenizer(paramString, "\\");
            if (localStringTokenizer.hasMoreTokens()) {
                String str = localStringTokenizer.nextToken();
                
                if (str.trim().compareTo("") != 0) {
                    this._listener.OnRateServiceSYSMessage(new Sys(str));
                }
            }

            localStringTokenizer = null;
        } catch (Exception localException) {
            System.out.println("RateService: Exception Parsing Compact SYS Message: " + localException.getClass().getName() + ": " + localException.getMessage());
        }
    }

    protected void processCompactMSGMessage(String paramString) throws Exception {
        try {
            StringTokenizer localStringTokenizer =
                    new StringTokenizer(paramString, "\\");
            
            if (localStringTokenizer.hasMoreTokens()) {
                String str1 = localStringTokenizer.nextToken();
                String str2 = localStringTokenizer.nextToken();

                this._listener.OnRateServiceMSGMessage(new Msg(str1, str2));
            }
        } catch (Exception localException) {
            System.out.println("RateService: Exception Parsing Compact MSG Message: " + localException.getClass().getName() + ": " + localException.getMessage());
        }
    }

    private String readMessage(BufferedInputStream paramBufferedInputStream) 
            throws Exception {
        int i = (this._compactRates == true) ? 32 : 128;

        StringBuffer localStringBuffer = new StringBuffer(i);
        char c = ' ';
        int j = 0;

        while (c != '\r') {
            int k = paramBufferedInputStream.read();
            if (k == -1) {
                throw new IOException("Unexpected EOF");
            }

            c = (char)k;

            if (c == '\002') {
                j = 1;
            }

            if (this._compactRates == true) {
                j = 1;
            }

            if (j != 0);
            localStringBuffer.append(c);
        }

        String str = localStringBuffer.toString();
        localStringBuffer = null;

        return str;
    }

}
