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
/*    */ public class YConnection extends AbstractYConnection
/*    */ {
/* 20 */   private static final Logger logger = Logger.getLogger(YConnection.class);
/*    */ 
/* 22 */   private static String PARAM_FORMAT = "f";
/*    */ 
/* 24 */   private static String PARAM_E = "e";
/*    */   private String symbols;
/*    */   private String format;
/*    */ 
/*    */   public String getSymbols()
/*    */   {
/* 31 */     return this.symbols;
/*    */   }
/*    */ 
/*    */   public void setSymbols(String symbols) {
/* 35 */     this.symbols = symbols;
/*    */   }
/*    */ 
/*    */   public String getFormat() {
/* 39 */     return this.format;
/*    */   }
/*    */ 
/*    */   public void setFormat(String format) {
/* 43 */     this.format = format;
/*    */   }
/*    */ 
/*    */   private URI getUrl() throws URISyntaxException
/*    */   {
/* 48 */     if (logger.isDebugEnabled()) {
/* 49 */       logger.debug("getUrl() - start");
/*    */     }
/*    */ 
/* 52 */     List qparams = new ArrayList();
/* 53 */     qparams.add(new BasicNameValuePair(PARAM_SYMBOL, this.symbols));
/* 54 */     qparams.add(new BasicNameValuePair(PARAM_FORMAT, this.format));
/* 55 */     qparams.add(new BasicNameValuePair(PARAM_E, ".csv"));
/*    */ 
/* 57 */     URI uri = 
/* 58 */       URIUtils.createURI("http", "quote.yahoo.com", PORT, "/d/quotes.csv", 
/* 59 */       URLEncodedUtils.format(qparams, "UTF-8"), null);
/*    */ 
/* 61 */     if (logger.isDebugEnabled()) {
/* 62 */       logger.debug("getUrl() - URI uri=" + uri);
/*    */     }
/*    */ 
/* 65 */     if (logger.isDebugEnabled()) {
/* 66 */       logger.debug("getUrl() - end");
/*    */     }
/*    */ 
/* 69 */     return uri;
/*    */   }
/*    */ 
/*    */   public String getCsv() throws ClientProtocolException, IOException, URISyntaxException
/*    */   {
/* 74 */     return getCsv(getUrl());
/*    */   }
/*    */ }

/* Location:           Z:\home\andrey\Documents\Projects\OpenTrader\docs\External\yahoofinanceapi-0.0.5-SNAPSHOT.jar
 * Qualified Name:     de.hdtconsulting.yahoo.finance.csv.connection.YConnection
 * JD-Core Version:    0.6.0
 */