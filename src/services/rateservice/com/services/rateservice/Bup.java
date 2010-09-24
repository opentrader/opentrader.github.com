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
 *  %name      Bup.java
 *  %pkg       org.opentrader.api.rateservice
 *  %date      6:44:57 AM, Aug 12, 2010
 */
public class Bup {

    private static final Logger LOG = Logger.getLogger("opentrader");

    protected static final String TYPE_UNSUPPORTED_MESSAGE = "Error - This type is currently unsupported.";

    public static final String ORDERSTATUS_PLACED = "PLACED";
    public static final String ORDERSTATUS_MODIFIED = "MODIFIED";
    public static final String ORDERSTATUS_CANCELLED = "CANCELLED";
    public static final String ORDERSTATUS_EXPIRED = "EXPIRED";
    public static final String ORDERSTATUS_DEALT = "DEALT";
    public static final String ORDERSTATUS_FAILEDMARGIN = "FAILEDMARGIN";
    public static final String ORDERSTATUS_PENDING = "PENDING";
    public static final String MSGTYPE_DEAL = "DEAL";
    public static final String MSGTYPE_ORDER = "ORD";

    private String _type;
    private String _product;
    private double _positionContract;
    private double _positionCounter;
    private double _positionInUSD;
    private double _positionAverageRate;
    private double _postedMargin;
    private double _realizedMarginInUSD;
    private double _realizedMarginInBaseCcy;
    private String _dealRef;
    private long _dealConfirmationNumber;
    private String _orderReference;
    private String _orderStatus;

    public String getType() {
        return this._type;
    }

    public String getProduct() {
        return this._product;
    }

    public double getPositionContract() {
        return this._positionContract;
    }

    public double getPositionCounter() {
        return this._positionCounter;
    }

    public double getPositionInUSD() {
        return this._positionInUSD;
    }

    public double getPositionAverageRate() {
        return this._positionAverageRate;
    }

    public double getPostedMargin() {
        return this._postedMargin;
    }

    public double getRealizedMarginInUSD() {
        return this._realizedMarginInUSD;
    }

    public double getRealizedMarginInBaseCcy() {
        return this._realizedMarginInBaseCcy;
    }

    public String getDealRef() {
        return this._dealRef;
    }

    public long getDealConfirmationNumber() {
        return this._dealConfirmationNumber;
    }

    public String getOrderReference() {
        return this._orderReference;
    }

    public String getOrderStatus() {
        return this._orderStatus;
    }

    public void setType(String paramString) {
        this._type = paramString;
    }

    public void setProduct(String paramString) {
        this._product = paramString;
    }

    public void setPositionContract(double paramDouble) {
        this._positionContract = paramDouble;
    }
    public void setPositionCounter(double paramDouble) {
        this._positionCounter = paramDouble;
    }

    public void setPositionInUSD(double paramDouble) {
        this._positionInUSD = paramDouble;
    }

    public void setPositionAverageRate(double paramDouble) {
        this._positionAverageRate = paramDouble;
    }

    public void setPostedMargin(double paramDouble) {
        this._postedMargin = paramDouble;
    }

    public void setRealizedMarginInUSD(double paramDouble) {
        this._realizedMarginInUSD = paramDouble;
    }

    public void setRealizedMarginInBaseCcy(double paramDouble) {
        this._realizedMarginInBaseCcy = paramDouble;
    }

    public void setDealRef(String paramString) {
        this._dealRef = paramString;
    }

    public void setDealConfirmationNumber(long paramLong) {
        this._dealConfirmationNumber = paramLong;
    }

    public void setOrderReference(String paramString) {
        this._orderReference = paramString;
    }

    public void setOrderStatus(String paramString) {
        this._orderStatus = paramString;
    }

    @Override
    public String toString() {
        return toString(getType());
    }

    public String toString(String paramString) {
        StringBuffer localStringBuffer = new StringBuffer(130);

        localStringBuffer.append("Type : ");
        localStringBuffer.append(getType());
        localStringBuffer.append("  Product : ");
        localStringBuffer.append(getProduct());

        if (paramString.equals("DEAL")) {
            localStringBuffer.append("  Deal Reference : ");
            localStringBuffer.append(getDealRef());
            localStringBuffer.append("  Deal Confirmation Number : ");
            localStringBuffer.append(getDealConfirmationNumber());
            localStringBuffer.append("  Position Average Rate : ");
            localStringBuffer.append(getPositionAverageRate());
            localStringBuffer.append("  Postion Contract : ");
            localStringBuffer.append(getPositionContract());
            localStringBuffer.append("  Posted Margin : ");
            localStringBuffer.append(getPostedMargin());
            localStringBuffer.append("  Postion Counter : ");
            localStringBuffer.append(getPositionCounter());
            localStringBuffer.append("  Postion In USD : ");
            localStringBuffer.append(getPositionInUSD());
            localStringBuffer.append("  Realized Margin In Base Ccy : ");
            localStringBuffer.append(getRealizedMarginInBaseCcy());
            localStringBuffer.append("  Realized Margin In USD : ");
            localStringBuffer.append(getRealizedMarginInUSD());
        } else if (paramString.equals("ORD")) {
            localStringBuffer.append("  Order Reference : ");
            localStringBuffer.append(getOrderReference());
            localStringBuffer.append("  Order Status : ");
            localStringBuffer.append(getOrderStatus());
        } else {
            localStringBuffer = new StringBuffer(130);
            localStringBuffer.append("Error - This type is currently unsupported.");
        }

        return localStringBuffer.toString();
    }

}
