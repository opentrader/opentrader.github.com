/*     */ package de.hdtconsulting.yahoo.finance.csv.connection;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.net.MalformedURLException;
/*     */ import java.net.URI;
/*     */ import java.net.URISyntaxException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Date;
/*     */ import java.util.List;
/*     */ import org.apache.http.client.ClientProtocolException;
/*     */ import org.apache.http.client.utils.URIUtils;
/*     */ import org.apache.http.client.utils.URLEncodedUtils;
/*     */ import org.apache.http.message.BasicNameValuePair;
/*     */ import org.apache.log4j.Logger;
/*     */ 
/*     */ public class YConnectionHistoric extends AbstractYConnection
/*     */ {
/*  22 */   private static final Logger logger = Logger.getLogger(YConnectionHistoric.class);
/*     */   private static final String PATH = "table.csv";
/*     */   private static final String HOST = "ichart.finance.yahoo.com";
/*  28 */   private static String PARAM_START_MONTH = "a";
/*     */ 
/*  30 */   private static String PARAM_START_DAY = "b";
/*     */ 
/*  32 */   private static String PARAM_START_YEAR = "c";
/*     */ 
/*  34 */   private static String PARAM_END_MONTH = "d";
/*     */ 
/*  36 */   private static String PARAM_END_DAY = "e";
/*     */ 
/*  38 */   private static String PARAM_END_YEAR = "f";
/*     */ 
/*  40 */   private static String PARAM_INTERVAL = "g";
/*     */   private Date startDate;
/*     */   private Date endDate;
/*     */   private String interval;
/*     */   private String symbol;
/*     */ 
/*     */   public String getInterval()
/*     */   {
/*  51 */     return this.interval;
/*     */   }
/*     */ 
/*     */   public void setInterval(String interval) {
/*  55 */     this.interval = interval;
/*     */   }
/*     */ 
/*     */   public Date getStartDate() {
/*  59 */     return this.startDate;
/*     */   }
/*     */ 
/*     */   public void setStartDate(Date startDate) {
/*  63 */     this.startDate = startDate;
/*     */   }
/*     */ 
/*     */   public Date getEndDate() {
/*  67 */     return this.endDate;
/*     */   }
/*     */ 
/*     */   public void setEndDate(Date endDate) {
/*  71 */     this.endDate = endDate;
/*     */   }
/*     */ 
/*     */   public String getSymbol()
/*     */   {
/*  77 */     return this.symbol;
/*     */   }
/*     */ 
/*     */   public void setSymbol(String symbol) {
/*  81 */     this.symbol = symbol;
/*     */   }
/*     */ 
/*     */   private URI getUrl() throws MalformedURLException, URISyntaxException
/*     */   {
/*  86 */     if (logger.isDebugEnabled()) {
/*  87 */       logger.debug("getUrl() - start");
/*     */     }
/*     */ 
/*  90 */     List qparams = new ArrayList();
/*     */ 
/*  92 */     qparams.add(new BasicNameValuePair(PARAM_SYMBOL, this.symbol));
/*     */ 
/*  95 */     qparams.add(
/*  96 */       new BasicNameValuePair(PARAM_START_DAY, 
/*  96 */       this.startDate.getDate()));
/*  97 */     qparams.add(
/*  98 */       new BasicNameValuePair(PARAM_START_MONTH, 
/*  98 */       this.startDate.getMonth()));
/*  99 */     qparams.add(
/* 100 */       new BasicNameValuePair(PARAM_START_YEAR, 
/* 100 */       this.startDate.getYear() + 1900));
/*     */ 
/* 102 */     qparams.add(
/* 103 */       new BasicNameValuePair(PARAM_END_DAY, 
/* 103 */       this.endDate.getDate()));
/* 104 */     qparams.add(
/* 105 */       new BasicNameValuePair(PARAM_END_MONTH, 
/* 105 */       this.endDate.getMonth()));
/* 106 */     qparams.add(
/* 107 */       new BasicNameValuePair(PARAM_END_YEAR, 
/* 107 */       this.endDate.getYear() + 1900));
/*     */ 
/* 109 */     qparams.add(new BasicNameValuePair(PARAM_INTERVAL, this.interval));
/*     */ 
/* 111 */     URI uri = 
/* 112 */       URIUtils.createURI("http", "ichart.finance.yahoo.com", PORT, "table.csv", 
/* 113 */       URLEncodedUtils.format(qparams, "UTF-8"), null);
/*     */ 
/* 115 */     if (logger.isDebugEnabled()) {
/* 116 */       logger.debug("getUrl() - URI uri=" + uri);
/*     */     }
/*     */ 
/* 119 */     if (logger.isDebugEnabled()) {
/* 120 */       logger.debug("getUrl() - end");
/*     */     }
/*     */ 
/* 123 */     return uri;
/*     */   }
/*     */ 
/*     */   public String getCsv() throws ClientProtocolException, IOException, URISyntaxException
/*     */   {
/* 128 */     return getCsv(getUrl());
/*     */   }
/*     */ }

/* Location:           Z:\home\andrey\Documents\Projects\OpenTrader\docs\External\yahoofinanceapi-0.0.5-SNAPSHOT.jar
 * Qualified Name:     de.hdtconsulting.yahoo.finance.csv.connection.YConnectionHistoric
 * JD-Core Version:    0.6.0
 */