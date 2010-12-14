/*    */ package de.hdtconsulting.yahoo.finance.csv.connection;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import org.apache.http.HttpEntity;
/*    */ import org.apache.http.HttpResponse;
/*    */ import org.apache.http.client.ClientProtocolException;
/*    */ import org.apache.http.client.ResponseHandler;
/*    */ import org.apache.http.util.EntityUtils;
/*    */ 
/*    */ public class YCsvResponseHandler
/*    */   implements ResponseHandler<String>
/*    */ {
/*    */   public String handleResponse(HttpResponse response)
/*    */     throws ClientProtocolException, IOException
/*    */   {
/* 16 */     String result = null;
/*    */ 
/* 18 */     HttpEntity entity = response.getEntity();
/* 19 */     if (entity != null) {
/* 20 */       result = EntityUtils.toString(entity);
/*    */     }
/*    */ 
/* 23 */     entity.consumeContent();
/*    */ 
/* 25 */     return result;
/*    */   }
/*    */ }

/* Location:           Z:\home\andrey\Documents\Projects\OpenTrader\docs\External\yahoofinanceapi-0.0.5-SNAPSHOT.jar
 * Qualified Name:     de.hdtconsulting.yahoo.finance.csv.connection.YCsvResponseHandler
 * JD-Core Version:    0.6.0
 */