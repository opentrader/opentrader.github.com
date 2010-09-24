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

import java.util.logging.Logger;

/**
 *  @author    Andrey Pudov        <syscreat@gmail.com>
 *  @version   0.00.00
 *  %name      RateServiceController.java
 *  %pkg       org.opentrader.api
 *  %date      8:54:55 AM, Aug 19, 2010
 */
public class RateServiceController implements RateServiceListener {

    private static final Logger LOG = Logger.getLogger("opentrader");

    /**
     * private properties
     */
    private RateService _rateService = null;

    private RateServiceController() {
    }

    /**
     * Construct the Rate Service Example class with the required parameters
     * @param host
     * @param port
     * @param key
     */
    public RateServiceController(String host, String port, String key) {

        // Create a rate service object, using this class as an 
        // RateServiceListener passing the required host, port and key
        _rateService = new RateService(this, host, port, key);
    }

    /**
     * Start the Rates service
     */
    public void start() {
        // Start the rates service by making the connection
        // NOTE: Rate Service caches host, port and key, this mechanism
        // provided lazy initialisation.
        // Notification is passed when the connection is either created 
        // successfully or if the connection fails using
        // RateServiceListener callbacks
        _rateService.connect();
    }

    /**
     * Stops the rates service
     */
    public void stop() {
        // Stop the service by disconnecting
        // Sockets and resources are clean up when this is called
        // notification passed by the RateService Listener when disconnected
        _rateService.disconnect();
    }

    /**
     * Restarts the rates service
     */
    public void restart() {
        // If the service is connected is disconnects
        if(_rateService.isConnected()) {
            _rateService.disconnect();
        }

        // NOTE: it is recommened then you pause your service before 
        // reconnecting the rate service. This is so you do not overloaded
        // the system with constant reconnects on failures.
        try {
            Thread.sleep(5000);
        } catch(Exception e) {}

        // then start the service again.
        _rateService.connect();
    }

    // <editor-fold defaultstate="collapsed" desc="RateServiceListener Callbacks">

    /**
     * OnRateServiceConnected() - Called when the Rate service connects to
     * the Rate Server and the connection has been authenicated.
     */
    public void OnRateServiceConnected() {
        // Notification when a successfull connection attempt has been made, 
        // this can be used to set an internal flag that the service is
        // connected or notifty clients of the service that the service is
        // connected and ready
        System.out.println( "RateServiceExample: connected successfully");
    }

    /**
     * OnRateServiceConnectionFailed(Exception e) - Called when the Rate service
     * fails to connect to the Rate Server with the Exception that caused the
     * failure.
     * 
     * @param e Exception which caused the lost connection.
     */
    public void OnRateServiceConnectionFailed(Exception e) {
        // Notification when a failed connection attempt has been made, this 
        // failure is general fatal and most likely reasons for failure are the
        // connection paramaters are incorrect.  The exception provides the
        // details of the failure.  The client should only attempt an 
        // reconnection if they are certain the connection parameters are
        // correct and the error is due to network or remote server issues.
        System.out.println( "RateServiceExample: connection failed: " +
                e.getClass().getName() + " : " + e.getMessage() );

        // Here is a simply strategy for deciding if you should reconnect using 
        // data maintain by rate service. More advanced techniques could be used
        // which incorporate a longer reconnect sleep time based
        // on the number of failures or the type of exception.

        // If we have ever connected then we will retry the connection, 
        // providing the consectutive failed connection is less then 10 times.
        if( _rateService.getSuccessfullCounnectionCount() > 0 && 
                _rateService.getFailedConsecutiveCounnectionCount() < 10 ) {
            restart();
         }
    }

    /**
     * OnRateServiceConnectionLost(Exception e) - Called when the Rate service
     * connection to the Rate Server is lost with the Exception that causes the
     * loss.
     *
     * @param e Exception which caused the lost connection.
     */
    public void OnRateServiceConnectionLost(Exception e) {
        // Notification when a failed connection attempt has been made, this 
        // failure is general caused by a Network outage or remote server
        // failure. The exception provides the details of the failure.
        // The client should only attempt an reconnection if they are certain 
        // the connection parameters are correct and the error is due to network
        // or remote server issues.
        System.out.println( "RateServiceExample: connection lost: " +
                e.getClass().getName() + " : " + e.getMessage() );

        // Here is a simply strategy for deciding if you should reconnect using 
        // data maintain by rate service. More advanced techniques could be used
        // which incorporate a longer reconnect sleep time based
        // on the number of failures or the type of exception.

        // If we have ever connected then we will retry the connection, 
        // providing the consectutive failed connection is less then 10 times.
        if(_rateService.getSuccessfullCounnectionCount() > 0 &&
                _rateService.getFailedConsecutiveCounnectionCount() < 10) {
            restart();
         }
    }

    /**
     * OnRateServiceDisconnected() - Called when the Rate service has been
     * disconnected from the Rate Server after a client disconnect request
     */
    public void OnRateServiceDisconnected() {
        // Notification when a successfull disconnection has completed, this can 
        // be used to set an internal flag that the service is disconnected or
        // notifty clients of the service that the service no longer connected
        System.out.println("RateServiceExample: disconnected successfully");
    }

    /**
     * OnRateServiceRate( Rate rate ) - Called when a rate is updated
     * @param rate The rate which is updating
     */
    public void OnRateServiceRate(Rate rate) {
        // Notification of a Rate update, the rate object contains all the data 
        // regarding the updated rate. The object is a lightweight Java Bean
        // object which simply contains the Rate Data.  Ideal for storing in an
        // interal collection (recommend hashtable hashed on currency pair).

        // Create a string buffer to store the rate message, preinitialse with
        // approx size for performance
        StringBuilder msg = new StringBuilder( 128 );

        msg.append("OnRate: Currency Pair: ");
        msg.append(rate.getCurrencyPair());
        msg.append(" Bid: ");
        msg.append(rate.getBid());
        msg.append(" Ask: " );
        msg.append(rate.getAsk());
        msg.append(" High: " );
        msg.append(rate.getHigh());
        msg.append(" Low: " );
        msg.append(rate.getLow());
        msg.append(" Dealable: ");
        msg.append(rate.getDealable());
        msg.append(" Domian: " );
        msg.append(rate.getDomain());
        msg.append(" Decimal Places: ");
        msg.append(rate.getDecimalPlaces());

        System.out.println("OnRate: " + msg.toString());

        // NOTE: rate.toString() would have provided the same output, repeated 
        // code here to so example of rate object class.
    }

    public void OnRateServiceBUPMessage(Bup paramBup) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void OnRateServiceSYSMessage(Sys paramSys) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void OnRateServiceMSGMessage(Msg paramMsg) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    // </editor-fold>

}
