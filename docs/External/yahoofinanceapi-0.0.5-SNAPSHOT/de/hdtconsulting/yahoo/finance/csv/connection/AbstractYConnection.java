/*    */ package de.hdtconsulting.yahoo.finance.csv.connection;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.net.URI;
/*    */ import org.apache.http.HttpHost;
/*    */ import org.apache.http.client.ClientProtocolException;
/*    */ import org.apache.http.client.HttpClient;
/*    */ import org.apache.http.client.methods.HttpGet;
/*    */ import org.apache.http.impl.client.DefaultHttpClient;
/*    */ import org.apache.http.params.HttpParams;
/*    */ import org.apache.log4j.Logger;
/*    */ 
/*    */ public abstract class AbstractYConnection
/*    */ {
/* 19 */   private static final Logger logger = Logger.getLogger(AbstractYConnection.class);
/*    */ 
/* 21 */   protected static int PORT = -1;
/*    */ 
/* 23 */   protected static String PARAM_SYMBOL = "s";
/*    */ 
/* 25 */   private HttpHost proxy = null;
/*    */ 
/* 27 */   private HttpClient client = new DefaultHttpClient();
/*    */ 
/* 29 */   private YCsvResponseHandler responseHandler = new YCsvResponseHandler();
/*    */ 
/*    */   public void setProxy(YHost proxy) {
/* 32 */     this.proxy = new HttpHost(proxy.getServer(), proxy.getPort());
/*    */   }
/*    */ 
/*    */   public void resetProxy() {
/* 36 */     this.proxy = null;
/*    */   }
/*    */ 
/*    */   protected String getCsv(URI uri) throws ClientProtocolException, IOException
/*    */   {
/* 41 */     if (logger.isDebugEnabled()) {
/* 42 */       logger.debug("getCsv(URI) - start");
/*    */     }
/*    */ 
/* 45 */     if (this.proxy != null)
/* 46 */       this.client.getParams().setParameter("http.route.default-proxy", this.proxy);
/*    */     else {
/* 48 */       this.client.getParams().removeParameter("http.route.default-proxy");
/*    */     }
/*    */ 
/* 51 */     HttpGet httpget = new HttpGet(uri);
/* 52 */     String response = (String)this.client.execute(httpget, this.responseHandler);
/*    */ 
/* 54 */     if (logger.isDebugEnabled()) {
/* 55 */       logger.debug("getCsv(URI) - String response=" + response.length());
/*    */     }
/*    */ 
/* 58 */     if (logger.isDebugEnabled()) {
/* 59 */       logger.debug("getCsv(URI) - end");
/*    */     }
/*    */ 
/* 62 */     return response;
/*    */   }
/*    */ }

/* Location:           Z:\home\andrey\Documents\Projects\OpenTrader\docs\External\yahoofinanceapi-0.0.5-SNAPSHOT.jar
 * Qualified Name:     de.hdtconsulting.yahoo.finance.csv.connection.AbstractYConnection
 * JD-Core Version:    0.6.0
 */