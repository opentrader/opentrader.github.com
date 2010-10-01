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
 *  %name      WebServices.java
 *  %pkg       com.services.webservices
 *  %date      5:27:40 PM, Sep 24, 2010
 */
public class WebServices {

    private static final Logger LOG = Logger.getLogger("opentrader");

    private TradeServer     server;
    private TradeAccount    account;
    
    public WebServices(TradeServer server, TradeAccount account) {
        this.server = server;
        this.account = account;
    }

    // <editor-fold defaultstate="collapsed" desc="Services Code">

    /**
     * Core Web services offered by GAIN Capital. These services are offered
     * to customers of GAIN Capital for the purposes of carrying out business
     * with GAIN Capital. All data supplied remains copyright GAIN Capital inc
     * and may not be reproduced without the prior written permission of
     * GAIN Capital inc. USAGE: The web functions have been developed with
     * Microsoft .NET WebServiceController. Best results can be found when used with
     * Visual Studio.NET. For further information,
     * see http://api.efxnow.com/Docs
     */

    /**
     * Cancel trade order. OrderConfirmation as reference number provided for
     * original order; note: in the case of an OCO, both legs should be
     * cancelled.
     *
     * @return
     */
    public String cancelOrder() {
        Service service = new Service();

        return service.invoke(
                server.getWebServiceAddress(),
                "",
                "");
    }

    /**
     * Cancel trade Order. Please provide Order Reference Number for the
     * Reference Number.. Please provide Order Reference Number for the
     * Reference Number. The Order Reference Number can be retrieved from
     * GetOrderBlotterDataSet. All the legs of the Order will be removed.
     * Please provide Order Reference Number for the Reference Number. All the
     * legs of the Order will get removed
     *
     * @return
     */
    public String cancelOrderByOrderID() {
        Service service = new Service();

        return service.invoke(
                server.getWebServiceAddress(),
                "",
                "");
    }

    /**
     * Place trade deal request.Here the function is intended to be used when
     * you wish to enter the market at a specified rate. If the specified rate
     * is the current market rate the request is processed else it will get
     * rejected (in other words, a fill or kill scenario). Pair as GBP/USD,
     * BuySell as B or S, Amount as multiple of 10,000 or 100,000 depending upon
     * account.
     *
     * @return
     */
    public String dealRequest() {
        Service service = new Service();

        return service.invoke(
                server.getWebServiceAddress(),
                "",
                "");
    }

    /**
     * This function is used to deal at market at the best rate available. The
     * DealRequest function may take longer to enter the market, especially in
     * a fast moving market if the deal is rejected. Place trade deal request
     * with Rate at Best. Pair as GBP/USD, BuySell as B or S, Amount as multiple
     * of 10,000 or 100,000 depending upon account.
     *
     * @return
     */
    public String dealRequestAtBest() {
        Service service = new Service();

        return service.invoke(
                server.getWebServiceAddress(),
                "",
                "");
    }

    /**
     * This function is like the point and shoot option in position management
     * on the platform. It is used when multiple lots are in exposure for a
     * specified currency pair. You can choose which position you want to trade.
     * This function is not used when only one position is open. A DealID will
     * be requested, which is the ITID returned from the GetDealBlotter
     * function. GetDealBlotter should be called after you enter the position
     * due to the DealBlotter being reset each day at 5 pm EST.Place trade deal
     * request by deal id.DealId as the ID of the deal that you want to close,
     * Pair as GBP/USD, BuySell as B or S, Amount as multiple of 10,000 or
     * 100,000 depending upon account
     *
     * @return
     */
    public String dealRequestByDealId() {
        Service service = new Service();

        return service.invoke(
                server.getWebServiceAddress(),
                "",
                "");
    }

    /**
     * Returns string that you sent as a literal and as a list of ASCII char
     * codes. Use this routine if you wish to test the WebService process or
     * check connectivity.
     *
     * @return Message='Echo request' ASCII:69 99 104 111 32 114 101 113 ...
     */
    public String echo() {
        Service service = new Service();

        return service.invoke(
                server.getWebServiceAddress(),
                "Echo",
                "Message=Echo request");
    }

    /**
     * Account parameters
     *
     * @return
     */
    public String getAccount() {
        Service service = new Service();

        return service.invoke(
                server.getWebServiceAddress(),
                "GetAccount",
                    "UserID=" + account.getLogin() +
                    "&PWD=" + account.getPassword() +
                    "&Brand=" + server.getBrand());
    }

    /**
     * Account parameters. Notes field may be used to describe the client.
     * Use 'GAIN' as the default value for brand.
     *
     * @return
     */
    public String getAccountObject() {
        Service service = new Service();

        return service.invoke(
                server.getWebServiceAddress(),
                "",
                "");
    }

    /**
     * Account Services
     *
     * @return
     */
    public String getAccountServices() {
        Service service = new Service();

        return service.invoke(
                server.getWebServiceAddress(),
                "",
                "");
    }

    /**
     * GAIN Commentary. Returns top three GAIN Commentry items.
     *
     * @return
     */
    public String getComment() {
        Service service = new Service();

        return service.invoke(
                server.getWebServiceAddress(),
                "",
                "");
    }

    /**
     * Comment DataSet
     *
     * @return
     */
    public String getCommentDataSet() {
        Service service = new Service();

        return service.invoke(
                server.getWebServiceAddress(),
                "",
                "");
    }

    /**
     * User Deal blotter
     *
     * @return
     */
    public String getDealBlotter() {
        Service service = new Service();

        return service.invoke(
                server.getWebServiceAddress(),
                "",
                "");
    }

    /**
     * Deal Blotter DataSet
     *
     * @return
     */
    public String getDealBlotterDataSet() {
        Service service = new Service();

        return service.invoke(
                server.getWebServiceAddress(),
                "",
                "");
    }

    /**
     * Deal Blotter DataSet with filter
     *
     * @return
     */
    public String getDealBlotterDataSetWithFilter() {
        Service service = new Service();

        return service.invoke(
                server.getWebServiceAddress(),
                "",
                "");
    }

    /**
     * User Deal blotter with Filter
     *
     * @return
     */
    public String getDealBlotterWithFilter() {
        Service service = new Service();

        return service.invoke(
                server.getWebServiceAddress(),
                "",
                "");
    }

    /**
     * Delayed Rates DataSet
     *
     * @return
     */
    public String getDelayedRatesDataSet() {
        Service service = new Service();

        return service.invoke(
                server.getWebServiceAddress(),
                "",
                "");
    }

    /**
     * Economic Calendar
     *
     * @return
     */
    public String getEconomicCalendar() {
        Service service = new Service();

        return service.invoke(
                server.getWebServiceAddress(),
                "",
                "");
    }

    /**
     * DataSet of historic market rates up to 24 hours maximum.. The required
     * parameter is a Key, Quote, StartDateTime, and EndDateTime. ‘Key’ can be
     * retrieved from the GetRatesServerAuth function. Quote is in the form
     * CCY/CCY i.e. GBP/USD.
     *
     * @return
     */
    public String getHistoricRatesDataSet() {
        Service service = new Service();

        return service.invoke(
                server.getWebServiceAddress(),
                "",
                "");
    }

    /**
     * User Margin blotter
     *
     * @return
     */
    public String getMarginBlotter() {
        Service service = new Service();

        return service.invoke(
                server.getWebServiceAddress(),
                "",
                "");
    }

    /**
     * Margin Blotter DataSet.Returns account specific balance information such
     * as Margin Balance, Account Balance, P/L, and more.
     *
     * @return
     */
    public String getMarginBlotterDataSet() {
        Service service = new Service();

        return service.invoke(
                server.getWebServiceAddress(),
                "",
                "");
    }

    /**
     * Market News. Returns top three News items.
     *
     * @return
     */
    public String getNews() {
        Service service = new Service();

        return service.invoke(
                server.getWebServiceAddress(),
                "",
                "");
    }

    /**
     * News DataSet
     *
     * @return
     */
    public String getNewsDataSet() {
        Service service = new Service();

        return service.invoke(
                server.getWebServiceAddress(),
                "",
                "");
    }

    /**
     * User Order blotter
     *
     * @return
     */
    public String getOrderBlotter() {
        Service service = new Service();

        return service.invoke(
                server.getWebServiceAddress(),
                "",
                "");
    }

    /**
     * Order Blotter DataSet which Returns any open orders in the account.
     *
     * @return
     */
    public String getOrderBlotterDataSet() {
        Service service = new Service();

        return service.invoke(
                server.getWebServiceAddress(),
                "",
                "");
    }

    /**
     * Provides Customer Pair Settings
     *
     * @return
     */
    public String getPairSettings() {
        Service service = new Service();

        return service.invoke(
                server.getWebServiceAddress(),
                "",
                "");
    }

    /**
     * User Position blotter.Returns the current open positions as displayed in
     * position management. The input, Key, can be retrieved from the
     * GetRatesServerAuth function.
     *
     * @return
     */
    public String getPositionBlotter() {
        Service service = new Service();

        return service.invoke(
                server.getWebServiceAddress(),
                "",
                "");
    }

    /**
     * Position Blotter DataSet which relates to all the details for any current
     * open positions associated to the account.
     *
     * @return
     */
    public String getPositionBlotterDataSet() {
        Service service = new Service();

        return service.invoke(
                server.getWebServiceAddress(),
                "",
                "");
    }

    /**
     * Provides the list of currency pairs with their counter and contract pairs
     * and two flags bSubscibe and bcansubscribe. The Contract and Counter
     * specify what Pairs are related to the CcyPair and are required to be
     * subscribed if cCcyPair is subscribed to. The bSubscribe indicates if the
     * Pair is currently subscribed to or not. The bCanunscribe defines if the
     * specific Pair can be unscribed or not.
     *
     * @return
     */
    public String getProductSubscriptionRelationshipBlotter() {
        Service service = new Service();

        return service.invoke(
                server.getWebServiceAddress(),
                "",
                "");
    }

    /**
     * Provides the list of currency pairs with their counter and contract pairs
     * and two flags bSubscibe and bcansubscribe. The Contract and Counter
     * specify what Pairs are related to the CcyPair and are required to be
     * subscribed if cCcyPair is subscribed to. The bSubscribe indicates if the
     * Pair is currently subscribed to or not. The bCanunscribe defines if the
     * specific Pair can be unscribed or not.
     *
     * @return
     */
    public String getProductSubscriptionRelationshipBlotterDataset() {
        Service service = new Service();

        return service.invoke(
                server.getWebServiceAddress(),
                "",
                "");
    }

    /**
     * Returns Rate details for all the pairs in string format delimited
     * by \ and $
     *
     * @return
     */
    public String getRates() {
        Service service = new Service();

        return service.invoke(
                server.getWebServiceAddress(),
                "",
                "");
    }

    /**
     * Returns Complete Rate details as in Rates Blotter for all the pairs in
     * string format delimited by \ and $.Message Parameters -
     * PAIR\BID\OFFER\STATUS\HIGH\LOW\DECIMALPLACES\NOTATION\CLOSINGBID\COUNTERPAIR\UPDATEDATETIME$
     *
     * @return
     */
    public String getRatesBlotter() {
        Service service = new Service();

        return service.invoke(
                server.getWebServiceAddress(),
                "",
                "");
    }

    /**
     * Rates Snapshot DataSet.Returns the rates and timestamps for all the
     * currency pairs. The required parameter is a ‘Key’ which can be retrieved
     * from the GetRatesServerAuth function.
     *
     * @return
     */
    public String getRatesDataSet() {
        Service service = new Service();

        return service.invoke(
                server.getWebServiceAddress(),
                "",
                "");
    }

    /**
     * This function is primarily to authenticate your account. Returns the
     * 'key' string valid for 24hrs used to authenticate with the Rates Server
     * and the blotter functions. This must be called prior to connecting with
     * the rates server or the blotters each day. Please contact Customer
     * Service with any questions regarding Brand Code.
     *
     * @return
     */
    public String getRatesServerAuth() {
        Service service = new Service();

        return service.invoke(
                server.getWebServiceAddress(),
                "GetRatesServerAuth",
                    "UserID=" + account.getLogin() +
                    "&PWD=" + account.getPassword() +
                    "&Brand=" + server.getBrand());
    }

    /**
     * Returns the Sub Account 'Key' string valid for 24hrs used to authenticate
     * with the Rates Server and the blotter functions. This must be called
     * prior to connecting with the rates server or the blotters each day.
     *
     * @return
     */
    public String getSubAccountAuthenticationKey() {
        Service service = new Service();

        return service.invoke(
                server.getWebServiceAddress(),
                "",
                "");
    }

    /**
     * List of traded symbols
     *
     * @return
     */
    public String getSymbolBlotter() {
        Service service = new Service();

        return service.invoke(
                server.getWebServiceAddress(),
                "",
                "");
    }

    /**
     * Symbol Blotter DataSet. Returns all the possible currency pairs to trade
     * in their respective currency symbols. The parameters are your UserID and
     * password.
     *
     * @return
     */
    public String getSymbolBlotterDataSet() {
        Service service = new Service();

        return service.invoke(
                server.getWebServiceAddress(),
                "",
                "");
    }

    /**
     * Ticker
     *
     * @return
     */
    public String getTicker() {
        Service service = new Service();

        return service.invoke(
                server.getWebServiceAddress(),
                "",
                "");
    }

    /**
     * Returns the server time (UTC), can be used as a connection keep-alive
     * if you so wish.
     *
     * @return
     */
    public String getTime() {
        Service service = new Service();

        return service.invoke(
                server.getWebServiceAddress(),
                "GetTime",
                "");
    }

    /**
     * Modify If Then OCO Trade order.Pair as GBP/USD, Expiry as EOD or GTC,
     * BuySell as B or S, Amount as multiple of 10,000 or 100,000 depending upon
     * account, Order Basis as S or T for Stop loss or LimiT. Warning: Trade
     * Order can take up to 60 seconds to be placed into the order process.
     * Use DealRequest for immediate execution.
     *
     * @return
     */
    public String modifyIfThenOCOOrder() {
        Service service = new Service();

        return service.invoke(
                server.getWebServiceAddress(),
                "",
                "");
    }

    /**
     * Modify If Then Trade order.Pair as GBP/USD, Expiry as EOD or GTC, BuySell
     * as B or S, Amount as multiple of 10,000 or 100,000 depending upon
     * account, Order Basis as S or T for Stop loss or LimiT. Warning: Trade
     * Order can take up to 60 seconds to be placed into the order process.
     * Use DealRequest for immediate execution.
     *
     * @return
     */
    public String modifyIfThenOrder() {
        Service service = new Service();

        return service.invoke(
                server.getWebServiceAddress(),
                "",
                "");
    }

    /**
     * Modify OCO Associated Position Trade order. Pair as GBP/USD. Warning:
     * Trade Order can take up to 60 seconds to be placed into the order
     * process. Use DealRequest for immediate execution.
     *
     * @return
     */
    public String modifyOCOASSPOrder() {
        Service service = new Service();

        return service.invoke(
                server.getWebServiceAddress(),
                "",
                "");
    }

    /**
     * Modify OCO trade order. Expiry as EOD or GTC, BuySell as B or S, Amount
     * as multiple of 10,000 or 100,000 depending upon account, Order Basis as
     * S or T for Stop loss or LimiT. Warning: Trade Order can take up to 60
     * seconds to be placed into the order process. Use DealRequest for
     * immediate execution.
     *
     * @return
     */
    public String modifyOCOOrder() {
        Service service = new Service();

        return service.invoke(
                server.getWebServiceAddress(),
                "",
                "");
    }

    /**
     * Modify Single Associated Position Trade order.This allows you to modify
     * an associated position2 order which has already been created.
     * OrderReference is a parameter to identify which particular order is to be
     * modified. OrderReference can be retrieved when creating the order through
     * the PlaceSingleASSOrder or the PlaceSingleOrder function.
     * CustomerOrderReference, returned from these functions, can be used as the
     * OrderReference. Pair as GBP/USD, Order Basis as S or T for Stop loss or
     * LimiT. Warning: Trade Order can take up to 60 seconds to be placed into
     * the order process. Use DealRequest for immediate execution.
     *
     * @return
     */
    public String modifySingleASSPOrder() {
        Service service = new Service();

        return service.invoke(
                server.getWebServiceAddress(),
                "",
                "");
    }

    /**
     * Modify single trade order. This allows you to modify an order which has
     * already been created. OrderReference is a parameter that identifies which
     * particular order is to be modified and can be retrieved from many order
     * functions. The order functions return CustomerOrderReference which
     * corresponds to the OrderReference parameter. Expiry as EOD or GTC,
     * BuySell as B or S, Amount as multiple of 10,000 or 100,000 depending upon
     * account, Order Basis as S or T for Stop loss or LimiT. Warning: Trade
     * Order can take up to 60 seconds to be placed into the order process.
     * Use DealRequest for immediate execution.
     *
     * @return
     */
    public String modifySingleOrder() {
        Service service = new Service();

        return service.invoke(
                server.getWebServiceAddress(),
                "",
                "");
    }

    /**
     * If/Then OCO order is a conditional order providing that if the first
     * order (If order) is executed, the second order (Then order) is activated
     * as a live, One Cancels Other (OCO) order. Full description of an OCO1
     * order. The execution of either one of the two 'Then' orders automatically
     * cancels the other. In cases where the 'If' single order does not execute,
     * the 'Then' OCO1 order will remain dormant. When any part of an If / Then
     * OCO1 order is cancelled, including either leg of the OCO1 order, all
     * parts of the order are cancelled as well. Place If Then OCO Trade order.
     * Pair as GBP/USD, Expiry as EOD or GTC, BuySell as B or S, Amount as
     * multiple of 10,000 or 100,000 depending upon account, Order Basis as
     * S or T for Stop loss or LimiT. Warning: Trade Order can take up to 60
     * seconds to be placed into the order process. Use DealRequest for
     * immediate execution.
     *
     * @return
     */
    public String placeIfThenOCOOrder() {
        Service service = new Service();

        return service.invoke(
                server.getWebServiceAddress(),
                "",
                "");
    }

    /**
     * Place If Then Trade order. The If/Then order is a conditional order
     * providing that if the first order (If order) is executed, the second
     * order (Then order) is activated as a live, single order. In cases where
     * the If order does not execute, the Then single order will remain dormant.
     * When either part of an If / Then order is cancelled, all parts of the
     * order are cancelled as well. Pair as GBP/USD, Expiry as EOD or GTC,
     * BuySell as B or S, Amount as multiple of 10,000 or 100,000 depending upon
     * account, Order Basis as S or T for Stop loss or LimiT. Warning: Trade
     * Order can take up to 60 seconds to be placed into the order process. Use
     * DealRequest for immediate execution.
     *
     * @return
     */
    public String placeIfThenOrder() {
        Service service = new Service();

        return service.invoke(
                server.getWebServiceAddress(),
                "",
                "");
    }

    /**
     * Place OCO Associated Position Trade order. Pair as GBP/USD. Warning:
     * Trade Order can take up to 60 seconds to be placed into the order
     * process. Use DealRequest for immediate execution.
     *
     * @return
     */
    public String placeOCOASSPOrder() {
        Service service = new Service();

        return service.invoke(
                server.getWebServiceAddress(),
                "",
                "");
    }

    /**
     * Place OCO trade order.This function enables you to create an order in
     * which one part of the order is cancelled if the other part is executed.
     * Pair as GBP/USD, Expiry2 as EOD or GTC, BuySell as B or S, Amount as
     * multiple of 10,000 or 100,000 depending upon account, Order Basis as
     * S or T for Stop loss or LimiT.Pair as GBP/USD, Expiry as EOD or GTC,
     * BuySell as B or S, Amount as multiple of 10,000 or 100,000 depending upon
     * account, Order Basis as S or T for Stop loss or LimiT. Warning: Trade
     * Order can take up to 60 seconds to be placed into the order process. Use
     * DealRequest for immediate execution.
     *
     * @return
     */
    public String placeOCOOrder() {
        Service service = new Service();

        return service.invoke(
                server.getWebServiceAddress(),
                "",
                "");
    }

    /**
     * Place Single Associated Position Trade order. Pair as GBP/USD, Order
     * Basis as S or T for Stop loss or LimiT. Warning: Trade Order can take up
     * to 60 seconds to be placed into the order process. Use DealRequest for
     * immediate execution.
     *
     * @return
     */
    public String placeSingleASSPOrder() {
        Service service = new Service();

        return service.invoke(
                server.getWebServiceAddress(),
                "",
                "");
    }

    /**
     * Place single trade order.Allows you to create a simple order to be
     * executed in the future through which a position can be opened. Pair as
     * GBP/USD, Expiry as EOD or GTC, BuySell as B or S, Amount as multiple of
     * 10,000 or 100,000 depending upon account, Order Basis as S or T for Stop
     * loss or LimiT. Warning: Trade Order can take up to 60 seconds to be
     * placed into the order process. Use DealRequest for immediate execution.
     *
     * @return
     */
    public String placeSingleOrder() {
        Service service = new Service();

        return service.invoke(
                server.getWebServiceAddress(),
                "",
                "");
    }

    /**
     * Updates the Subscribed Product List based on the comma separated Pairs
     * listed in SubscribedPairs String
     *
     * @return
     */
    public String saveUserProductSubscriptionSettings() {
        Service service = new Service();

        return service.invoke(
                server.getWebServiceAddress(),
                "",
                "");
    }

    // </editor-fold>

}
