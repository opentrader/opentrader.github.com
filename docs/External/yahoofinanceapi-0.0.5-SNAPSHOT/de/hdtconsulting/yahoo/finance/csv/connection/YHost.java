/*    */ package de.hdtconsulting.yahoo.finance.csv.connection;
/*    */ 
/*    */ public class YHost
/*    */ {
/*    */   private String server;
/*    */   private int port;
/*    */ 
/*    */   public YHost(String server, int port)
/*    */   {
/* 10 */     this.server = server;
/* 11 */     this.port = port;
/*    */   }
/*    */ 
/*    */   public String getServer() {
/* 15 */     return this.server;
/*    */   }
/*    */ 
/*    */   public void setServer(String server) {
/* 19 */     this.server = server;
/*    */   }
/*    */ 
/*    */   public int getPort() {
/* 23 */     return this.port;
/*    */   }
/*    */ 
/*    */   public void setPort(int port) {
/* 27 */     this.port = port;
/*    */   }
/*    */ }

/* Location:           Z:\home\andrey\Documents\Projects\OpenTrader\docs\External\yahoofinanceapi-0.0.5-SNAPSHOT.jar
 * Qualified Name:     de.hdtconsulting.yahoo.finance.csv.connection.YHost
 * JD-Core Version:    0.6.0
 */