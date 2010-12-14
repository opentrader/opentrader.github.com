/*    */ package de.hdtconsulting.yahoo.finance;
/*    */ 
/*    */ import java.math.BigDecimal;
/*    */ 
/*    */ public class YTrade
/*    */ {
/*    */   private String date;
/*    */   private String time;
/*    */   private BigDecimal value;
/*    */   private Integer volume;
/*    */   private Integer unknown;
/*    */ 
/*    */   public String getTime()
/*    */   {
/* 23 */     return this.time;
/*    */   }
/*    */ 
/*    */   protected void setTime(String time) {
/* 27 */     this.time = time;
/*    */   }
/*    */ 
/*    */   public BigDecimal getValue() {
/* 31 */     return this.value;
/*    */   }
/*    */ 
/*    */   protected void setValue(BigDecimal value) {
/* 35 */     this.value = value;
/*    */   }
/*    */ 
/*    */   public Integer getVolume() {
/* 39 */     return this.volume;
/*    */   }
/*    */ 
/*    */   protected void setVolume(Integer volume) {
/* 43 */     this.volume = volume;
/*    */   }
/*    */ 
/*    */   public Integer getUnknown() {
/* 47 */     return this.unknown;
/*    */   }
/*    */ 
/*    */   protected void setUnknown(Integer unknown) {
/* 51 */     this.unknown = unknown;
/*    */   }
/*    */ 
/*    */   public String getDate() {
/* 55 */     return this.date;
/*    */   }
/*    */ 
/*    */   protected void setDate(String date) {
/* 59 */     this.date = date;
/*    */   }
/*    */ }

/* Location:           Z:\home\andrey\Documents\Projects\OpenTrader\docs\External\yahoofinanceapi-0.0.5-SNAPSHOT.jar
 * Qualified Name:     de.hdtconsulting.yahoo.finance.YTrade
 * JD-Core Version:    0.6.0
 */