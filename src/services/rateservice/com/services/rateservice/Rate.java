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
 *  %name      Rate.java
 *  %pkg       org.opentrader.api.rateservice
 *  %date      6:52:17 AM, Aug 12, 2010
 */
public class Rate {

    private static final Logger LOG = Logger.getLogger("opentrader");

    private String _key;
    private String _ccyPair;
    private double _bid;
    private double _ask;
    private double _high;
    private double _low;
    private String _dealable;
    private String _domain;
    private int _decimalPlaces;
    private double _closingBidRate;
    private String _bidBankName;
    private String _askBankName;

    public Rate() {
        this._key = null;
        this._ccyPair = null;

        this._bidBankName = "";
        this._askBankName = "";
    }

    public String getKey() {
        return this._key;
    }

    public String getCurrencyPair() {
        return this._ccyPair;
    }

    public double getBid() {
        return this._bid;
    }

    public double getAsk() {
        return this._ask;
    }

    public double getHigh() {
        return this._high;
    }

    public double getLow() {
        return this._low;
    }

    public String getDealable() {
        return this._dealable;
    }

    public String getDomain() {
        return this._domain;
    }

    public int getDecimalPlaces() {
        return this._decimalPlaces;
    }

    public double getClosingBidRate() {
        return this._closingBidRate;
    }

    public String getBidBankName() {
        return this._bidBankName;
    }

    public String getAskBankName() {
        return this._askBankName;
    }

    public void setKey(String paramString) {
        this._key = paramString;
    }

    public void setCcyPair(String paramString) {
        this._ccyPair = paramString;
    }

    public void setBid(double paramDouble) {
        this._bid = paramDouble;
    }

    public void setAsk(double paramDouble) {
        this._ask = paramDouble;
    }

    public void setHigh(double paramDouble) {
        this._high = paramDouble;
    }

    public void setLow(double paramDouble) {
        this._low = paramDouble;
    }

    public void setDealable(String paramString) {
        this._dealable = paramString;
    }

    public void setDomain(String paramString) {
        this._domain = paramString;
    }

    public void setDecimalPlaces(int paramInt) {
        this._decimalPlaces = paramInt;
    }

    public void setClosingBidRate(double paramDouble) {
        this._closingBidRate = paramDouble;
    }

    public void setBidBankName(String paramString) {
        this._bidBankName = paramString;
    }

    public void setAskBankName(String paramString) {
        this._askBankName = paramString;
    }

    @Override
    public String toString() {
        StringBuilder localStringBuffer = new StringBuilder(130);

        localStringBuffer.append("Key: ");
        localStringBuffer.append(getKey());
        localStringBuffer.append(" Currency Pair: ");
        localStringBuffer.append(getCurrencyPair());
        localStringBuffer.append(" Bid: ");
        localStringBuffer.append(getBid());
        localStringBuffer.append(" Ask: ");
        localStringBuffer.append(getAsk());
        localStringBuffer.append(" High: ");
        localStringBuffer.append(getHigh());
        localStringBuffer.append(" Low: ");
        localStringBuffer.append(getLow());
        localStringBuffer.append(" Dealable: ");
        localStringBuffer.append(getDealable());
        localStringBuffer.append(" Domian: ");
        localStringBuffer.append(getDomain());
        localStringBuffer.append(" Decimal Places: ");
        localStringBuffer.append(getDecimalPlaces());
        localStringBuffer.append(" Closing BidRate: ");
        localStringBuffer.append(getClosingBidRate());
        localStringBuffer.append(" BidBankName: ");
        localStringBuffer.append(getBidBankName());
        localStringBuffer.append(" AskBankName: ");
        localStringBuffer.append(getAskBankName());

        return localStringBuffer.toString();
    }

}
