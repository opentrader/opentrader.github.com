/*
 * OpenTrader Trading Platform
 * The solution for online trading, technical analysis and automated trading.
 *
 * Copyright (C) 2011  Andrey Pudov
 * Andrey Pudov     <syscreat@gmail.com>
 *
 * http://opentrader.github.com/
 */

/*
 * CDDL HEADER START
 *
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright 2011 Andrey Pudov. All rights reserved.
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
 * Copyright 2011 Andrey Pudov.  All rights reserved.
 * Use is subject to license terms.
 *
 * Contributor(s):
 *
 * Portions Copyrighted 2011 Andrey Pudov.
 *
 */

package com.opentrader.ui.components;

/**
 *  @author    Andrey Pudov        <syscreat@gmail.com>
 *  @version   0.00.00
 *  %name      Chart.java
 *  %pkg       com.opentrader.ui.components
 *  %date      11:43:28 AM, Apr 23, 2011
 */
public class Chart extends java.awt.Canvas 
                   implements java.awt.event.ComponentListener {
    
    private static final long serialVersionUID = 6_639_826_293_774_293_930L;
    private static final java.util.logging.Logger LOG 
            = java.util.logging.Logger.getLogger("opentrader");
    
    private com.opentrader.market.Symbol                   symbol;
    private java.util.List<com.opentrader.market.Historic> historic;
    
    private java.awt.Image image;
    
    public Chart() {
        super();
        
        addComponentListener(this);
    }
    
    public void setHistoric(com.opentrader.market.Symbol symbol,
            java.util.List<com.opentrader.market.Historic> historic) {
        if ((symbol == null) || (historic == null)) {
            return;
        }
        
        this.symbol   = symbol;
        this.historic = java.util.Collections.unmodifiableList(historic);
        
        repaint();
    }
    
    @Override
    public void paint(java.awt.Graphics g) {
        if (image != null) {
            g.drawImage(image, 0, 0, this.getWidth(), this.getHeight(), this);
            
            return;
        }
        
        if ((historic != null) && (symbol != null)) {            
            // Defining Line            
            double[] volume         = new double[historic.size()];
            double   volumeMin      = Double.MAX_VALUE;
            double   volumeMax      = Double.MIN_VALUE;
            int      volumeMinIndex = 0;
            int      volumeMaxIndex = 0;
            for (int i = 0; i < historic.size(); ++i) {
                volume[i] = historic.get(i).getVolume();
                
                if (volume[i] < volumeMin) {
                    volumeMin = volume[i];
                    volumeMinIndex = i;
                }
                
                if (volume[i] > volumeMax) {
                    volumeMax = volume[i];
                    volumeMaxIndex = i;
                }
            }
            
            double[] open    = new double[historic.size()];
            double   openMin = Double.MAX_VALUE;
            double   openMax = Double.MIN_VALUE;
            for (int i = 0; i < historic.size(); ++i) {
                open[i] = historic.get(i).getOpen();
                
                if (open[i] < openMin) {
                    openMin = open[i];
                }
                
                if (open[i] > openMax) {
                    openMax = open[i];
                }
            }
            
            double[] adjClose    = new double[historic.size()];
            double   adjCloseMin = Double.MAX_VALUE;
            double   adjCloseMax = Double.MIN_VALUE;
            for (int i = 0; i < historic.size(); ++i) {
                adjClose[i] = historic.get(i).getAdjClose();
                
                if (adjClose[i] < adjCloseMin) {
                    adjCloseMin = adjClose[i];
                }
                
                if (adjClose[i] > adjCloseMax) {
                    adjCloseMax = adjClose[i];
                }
            }
            
            double[] close    = new double[historic.size()];
            double   closeMin = Double.MAX_VALUE;
            double   closeMax = Double.MIN_VALUE;
            for (int i = 0; i < historic.size(); ++i) {
                close[i] = historic.get(i).getClose();
                
                if (close[i] < closeMin) {
                    closeMin = close[i];
                }
                
                if (close[i] > closeMax) {
                    closeMax = close[i];
                }
            }
            
            double[] high    = new double[historic.size()];
            double   highMin = Double.MAX_VALUE;
            double   highMax = Double.MIN_VALUE;
            for (int i = 0; i < historic.size(); ++i) {
                high[i] = historic.get(i).getHigh();
                
                if (high[i] < highMin) {
                    highMin = high[i];
                }
                
                if (high[i] > highMax) {
                    highMax = high[i];
                }
            }
            
            double[] low    = new double[historic.size()];
            double   lowMin = Double.MAX_VALUE;
            double   lowMax = Double.MIN_VALUE;
            for (int i = 0; i < historic.size(); ++i) {
                low[i] = historic.get(i).getLow();
                
                if (low[i] < lowMin) {
                    lowMin = low[i];
                }
                
                if (low[i] > lowMax) {
                    lowMax = low[i];
                }
            }

            com.googlecode.charts4j.Line lineVolume 
                    = com.googlecode.charts4j.Plots.newLine(
                        com.googlecode.charts4j.DataUtil.scaleWithinRange(
                            volumeMin, volumeMax, volume), 
                        com.googlecode.charts4j.Color.BLUE, 
                        "Volume");
            lineVolume.setLineStyle(
                    com.googlecode.charts4j.LineStyle.newLineStyle(1, 1, 0));
            lineVolume.addShapeMarkers(
                    com.googlecode.charts4j.Shape.CIRCLE, 
                    com.googlecode.charts4j.Color.ROYALBLUE, 5);
            lineVolume.addShapeMarker(
                    com.googlecode.charts4j.Shape.VERTICAL_LINE_PARTIAL, 
                    com.googlecode.charts4j.Color.BLUE, 1, volumeMinIndex);
            lineVolume.addShapeMarker(
                    com.googlecode.charts4j.Shape.VERTICAL_LINE_PARTIAL, 
                    com.googlecode.charts4j.Color.BLUE, 1, volumeMaxIndex);
            lineVolume.setFillAreaColor(com.googlecode.charts4j.Color.DODGERBLUE);
            
            com.googlecode.charts4j.Line lineOpen 
                    = com.googlecode.charts4j.Plots.newLine(
                        com.googlecode.charts4j.DataUtil.scaleWithinRange(
                            openMin, openMax, open), 
                        com.googlecode.charts4j.Color.ROYALBLUE, 
                        "Open");
            lineOpen.setLineStyle(
                    com.googlecode.charts4j.LineStyle.newLineStyle(1, 1, 0));
            
            com.googlecode.charts4j.Line lineAdjClose 
                    = com.googlecode.charts4j.Plots.newLine(
                        com.googlecode.charts4j.DataUtil.scaleWithinRange(
                            adjCloseMin, adjCloseMax, adjClose), 
                        com.googlecode.charts4j.Color.PALEGREEN, 
                        "AdjClose");
            lineAdjClose.setLineStyle(
                    com.googlecode.charts4j.LineStyle.newLineStyle(4, 1, 0));
            
            com.googlecode.charts4j.Line lineClose 
                    = com.googlecode.charts4j.Plots.newLine(
                        com.googlecode.charts4j.DataUtil.scaleWithinRange(
                            closeMin, closeMax, close), 
                        com.googlecode.charts4j.Color.GREEN, 
                        "Close");
            lineClose.setLineStyle(
                    com.googlecode.charts4j.LineStyle.newLineStyle(1, 1, 0));
            
            com.googlecode.charts4j.Line lineHigh 
                    = com.googlecode.charts4j.Plots.newLine(
                        com.googlecode.charts4j.DataUtil.scaleWithinRange(
                            highMin, highMax, high), 
                        com.googlecode.charts4j.Color.RED, 
                        "High");
            lineHigh.setLineStyle(
                    com.googlecode.charts4j.LineStyle.newLineStyle(1, 1, 0));
            
            com.googlecode.charts4j.Line lineLow 
                    = com.googlecode.charts4j.Plots.newLine(
                        com.googlecode.charts4j.DataUtil.scaleWithinRange(
                            lowMin, lowMax, low), 
                        com.googlecode.charts4j.Color.FUCHSIA, 
                        "Low");
            lineLow.setLineStyle(
                    com.googlecode.charts4j.LineStyle.newLineStyle(1, 1, 0));

            // Defining chart.
            com.googlecode.charts4j.LineChart chart 
                    = com.googlecode.charts4j.GCharts.newLineChart(
                        lineVolume, lineOpen, lineAdjClose, lineClose, lineHigh, lineLow);
            if ((this.getWidth() + this.getHeight()) > 1000) {
                double scale = 1000.0 / (this.getWidth() + this.getHeight());
                int width    = (int) Math.round(this.getWidth() * scale);
                int height   = (int) Math.round(this.getHeight() * scale);

                chart.setSize(width, height);
            } else {
                chart.setSize(this.getWidth(), this.getHeight());
            }
            chart.setTitle(
                    symbol.getCode() + "|" + getFirstDate() + " - " 
                    + getLastDate(), 
                    com.googlecode.charts4j.Color.BLACK, 14);

            // Defining axis info and styles
            com.googlecode.charts4j.AxisStyle axisStyle 
                    = com.googlecode.charts4j.AxisStyle.newAxisStyle(
                        com.googlecode.charts4j.Color.BLACK, 12, 
                        com.googlecode.charts4j.AxisTextAlignment.CENTER);
            com.googlecode.charts4j.AxisLabels yAxis 
                    = com.googlecode.charts4j.AxisLabelsFactory.newNumericRangeAxisLabels(
                        0, volume[volume.length-1]);
            yAxis.setAxisStyle(axisStyle);
            com.googlecode.charts4j.AxisLabels xAxis1 
                    = com.googlecode.charts4j.AxisLabelsFactory.newAxisLabels(
                        java.util.Arrays.asList("Min", "Max"), 
                        java.util.Arrays.asList(volumeMinIndex, volumeMaxIndex));
            xAxis1.setAxisStyle(axisStyle);
            com.googlecode.charts4j.AxisLabels xAxis2 
                    = com.googlecode.charts4j.AxisLabelsFactory.newNumericRangeAxisLabels(1962, 2008);
            xAxis2.setAxisStyle(axisStyle);
            com.googlecode.charts4j.AxisLabels xAxis3 
                    = com.googlecode.charts4j.AxisLabelsFactory.newAxisLabels(
                        "Time", 50.0);
            xAxis3.setAxisStyle(com.googlecode.charts4j.AxisStyle.newAxisStyle(
                    com.googlecode.charts4j.Color.BLACK, 14, 
                    com.googlecode.charts4j.AxisTextAlignment.CENTER));

            // Adding axis info to chart.
            chart.addYAxisLabels(yAxis);
            chart.addXAxisLabels(xAxis1);
            chart.addXAxisLabels(xAxis2);
            chart.addXAxisLabels(xAxis3);
            chart.setGrid(100, 6.78, 5, 0);

            // Defining background and chart fills.
            chart.setBackgroundFill(
                    com.googlecode.charts4j.Fills.newSolidFill(
                        com.googlecode.charts4j.Color.WHITE));
            chart.setAreaFill(
                    com.googlecode.charts4j.Fills.newSolidFill(
                        com.googlecode.charts4j.Color.WHITE));
            String url = chart.toURLString();
            
            try {
                image = getImage(url);
                g.drawImage(image, 0, 0, this.getWidth(), this.getHeight(), 
                            this);
            } catch (Exception e) {
                LOG.warning(e.getMessage());
            }
        }
    }
    
    private static java.awt.Image getImage(String path) 
            throws java.io.IOException {
        java.awt.Image image = null;
        
        java.net.URL server = new java.net.URL(path);
        java.net.HttpURLConnection connection 
                = (java.net.HttpURLConnection) server.openConnection();
        connection.setRequestMethod("GET");
        connection.setDoInput(true);
        connection.setDoOutput(true);
        connection.setUseCaches(false);
        connection.addRequestProperty("Accept", 
                "image/gif, image/x-xbitmap, image/jpeg, image/pjpeg, "
                + "application/msword, application/vnd.ms-excel, "
                + "application/vnd.ms-powerpoint, "
                + "application/x-shockwave-flash, */*");
        connection.addRequestProperty("Accept-Language", "en-us,zh-cn;q=0.5");
        connection.addRequestProperty("Accept-Encoding", "gzip, deflate");
        connection.addRequestProperty("User-Agent", 
                "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.0; "
                + ".NET CLR 2.0.50727; MS-RTC LM 8)");
        connection.connect();
        java.io.InputStream is = connection.getInputStream();
        
        image = javax.imageio.ImageIO.read(is);
        
        return image;
    }
    
    private String getFirstDate() {
        java.text.SimpleDateFormat format 
                = new java.text.SimpleDateFormat("MM/dd/yyyy");
        
        com.opentrader.market.Historic entry = historic.get(0);
        
        return format.format(new java.util.Date(entry.getDate()));
    }
    
    private String getLastDate() {
        java.text.SimpleDateFormat format 
                = new java.text.SimpleDateFormat("MM/dd/yyyy");
        
        com.opentrader.market.Historic entry = historic.get(historic.size() - 1);
        
        return format.format(new java.util.Date(entry.getDate()));
    }
    
    
    /* 
     * Catch the different events of these components by using four methods 
     * of Component Listener  
     */

    @Override
    public void componentResized(java.awt.event.ComponentEvent e) {
        image = null;
    }

    @Override
    public void componentMoved(java.awt.event.ComponentEvent e) {
        //throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void componentShown(java.awt.event.ComponentEvent e) {
        //throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void componentHidden(java.awt.event.ComponentEvent e) {
        //throw new UnsupportedOperationException("Not supported yet.");
    }

}
