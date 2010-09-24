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

/**
 *  @author    Andrey Pudov        <syscreat@gmail.com>
 *  @version   0.00.00
 *  %name      RateServiceListener.java
 *  %pkg       org.opentrader.api.rateservice
 *  %date      6:56:28 AM, Aug 12, 2010
 */
public abstract interface RateServiceListener {

    public abstract void OnRateServiceConnected();

    public abstract void OnRateServiceConnectionFailed(Exception paramException);

    public abstract void OnRateServiceConnectionLost(Exception paramException);

    public abstract void OnRateServiceDisconnected();

    public abstract void OnRateServiceRate(Rate paramRate);

    public abstract void OnRateServiceBUPMessage(Bup paramBup);

    public abstract void OnRateServiceSYSMessage(Sys paramSys);

    public abstract void OnRateServiceMSGMessage(Msg paramMsg);

}
