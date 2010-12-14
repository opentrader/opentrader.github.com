/*    */ package de.hdtconsulting.yahoo.finance;
/*    */ 
/*    */ import java.math.BigDecimal;
/*    */ 
/*    */ public class YHistoric
/*    */ {
/*    */   private String date;
/*    */   private BigDecimal open;
/*    */   private BigDecimal high;
/*    */   private BigDecimal low;
/*    */   private BigDecimal close;
/*    */   private BigDecimal volume;
/*    */   private BigDecimal adjClose;
/*    */ 
/*    */   public BigDecimal getOpen()
/*    */   {
/* 27 */     return this.open;
/*    */   }
/*    */ 
/*    */   protected void setOpen(BigDecimal open) {
/* 31 */     this.open = open;
/*    */   }
/*    */ 
/*    */   public String getDate() {
/* 35 */     return this.date;
/*    */   }
/*    */ 
/*    */   protected void setDate(String date) {
/* 39 */     this.date = date;
/*    */   }
/*    */ 
/*    */   public BigDecimal getHigh() {
/* 43 */     return this.high;
/*    */   }
/*    */ 
/*    */   protected void setHigh(BigDecimal high) {
/* 47 */     this.high = high;
/*    */   }
/*    */ 
/*    */   public BigDecimal getLow() {
/* 51 */     return this.low;
/*    */   }
/*    */ 
/*    */   protected void setLow(BigDecimal low) {
/* 55 */     this.low = low;
/*    */   }
/*    */ 
/*    */   public BigDecimal getClose() {
/* 59 */     return this.close;
/*    */   }
/*    */ 
/*    */   protected void setClose(BigDecimal close) {
/* 63 */     this.close = close;
/*    */   }
/*    */ 
/*    */   public BigDecimal getVolume() {
/* 67 */     return this.volume;
/*    */   }
/*    */ 
/*    */   protected void setVolume(BigDecimal volume) {
/* 71 */     this.volume = volume;
/*    */   }
/*    */ 
/*    */   public BigDecimal getAdjClose() {
/* 75 */     return this.adjClose;
/*    */   }
/*    */ 
/*    */   protected void setAdjClose(BigDecimal adjClose) {
/* 79 */     this.adjClose = adjClose;
/*    */   }
/*    */ }

/* Location:           Z:\home\andrey\Documents\Projects\OpenTrader\docs\External\yahoofinanceapi-0.0.5-SNAPSHOT.jar
 * Qualified Name:     de.hdtconsulting.yahoo.finance.YHistoric
 * JD-Core Version:    0.6.0
 */