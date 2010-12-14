/*    */ package de.hdtconsulting.yahoo.finance.csv.connection;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.net.URI;
/*    */ import java.net.URISyntaxException;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import org.apache.http.client.ClientProtocolException;
/*    */ import org.apache.http.client.utils.URIUtils;
/*    */ import org.apache.http.client.utils.URLEncodedUtils;
/*    */ import org.apache.http.message.BasicNameValuePair;
/*    */ import org.apache.log4j.Logger;
/*    */ 
/*    */ public class YConnectionDayTrade extends AbstractYConnection
/*    */ {
/* 20 */   private static final Logger logger = Logger.getLogger(YConnectionDayTrade.class);
/*    */   private static final String HOST = "logtrade.finance.vip.ukl.yahoo.com";
/*    */   private static final String PATH = "/lastTrades";
/*    */   private String symbol;
/*    */ 
/*    */   public String getSymbol()
/*    */   {
/* 29 */     return this.symbol;
/*    */   }
/*    */ 
/*    */   public void setSymbol(String symbol) {
/* 33 */     this.symbol = symbol;
/*    */   }
/*    */ 
/*    */   private URI getUrl() throws URISyntaxException
/*    */   {
/* 38 */     if (logger.isDebugEnabled()) {
/* 39 */       logger.debug("getUrl() - start");
/*    */     }
/*    */ 
/* 42 */     List qparams = new ArrayList();
/* 43 */     qparams.add(new BasicNameValuePair("output", "user"));
/* 44 */     qparams.add(new BasicNameValuePair("i", "eu"));
/* 45 */     qparams.add(new BasicNameValuePair(PARAM_SYMBOL, this.symbol));
/*    */ 
/* 47 */     URI uri = URIUtils.createURI("http", "logtrade.finance.vip.ukl.yahoo.com", PORT, "/lastTrades", 
/* 48 */       URLEncodedUtils.format(qparams, "UTF-8"), null);
/*    */ 
/* 50 */     if (logger.isDebugEnabled()) {
/* 51 */       logger.debug("getUrl() - URI uri=" + uri);
/*    */     }
/*    */ 
/* 54 */     if (logger.isDebugEnabled()) {
/* 55 */       logger.debug("getUrl() - end");
/*    */     }
/* 57 */     return uri;
/*    */   }
/*    */ 
/*    */   public String getCsv() throws ClientProtocolException, IOException, URISyntaxException
/*    */   {
/* 62 */     return getCsv(getUrl());
/*    */   }
/*    */ }

/* Location:           Z:\home\andrey\Documents\Projects\OpenTrader\docs\External\yahoofinanceapi-0.0.5-SNAPSHOT.jar
 * Qualified Name:     de.hdtconsulting.yahoo.finance.csv.connection.YConnectionDayTrade
 * JD-Core Version:    0.6.0
 */