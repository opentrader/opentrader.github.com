/*    */ package de.hdtconsulting.yahoo.finance;
/*    */ 
/*    */ public class YExchangeRate
/*    */ {
/*    */   private YCurrency fromCurrency;
/*    */   private YCurrency toCurrency;
/*    */ 
/*    */   public YExchangeRate(YCurrency from, YCurrency to)
/*    */   {
/* 15 */     this.fromCurrency = from;
/* 16 */     this.toCurrency = to;
/*    */   }
/*    */ 
/*    */   public int hashCode()
/*    */   {
/* 21 */     String code = this.fromCurrency.getCode() + this.toCurrency.getCode();
/* 22 */     return code.hashCode();
/*    */   }
/*    */ 
/*    */   public boolean equals(Object o)
/*    */   {
/*    */     YExchangeRate rate;
/* 30 */     if ((o instanceof YExchangeRate))
/* 31 */       rate = (YExchangeRate)o;
/*    */     else
/* 33 */       return false;
/*    */     YExchangeRate rate;
/* 37 */     return (this.fromCurrency.getCode().equals(rate.getFromCurrency().getCode())) && (this.toCurrency.getCode().equals(rate.getToCurrency().getCode()));
/*    */   }
/*    */ 
/*    */   public YCurrency getFromCurrency()
/*    */   {
/* 45 */     return this.fromCurrency;
/*    */   }
/*    */ 
/*    */   public YCurrency getToCurrency() {
/* 49 */     return this.toCurrency;
/*    */   }
/*    */ 
/*    */   public YSymbol getSymbol() {
/* 53 */     YSymbol symbol = new YSymbol(this.fromCurrency.getCode() + this.toCurrency.getCode() + "=X");
/* 54 */     return symbol;
/*    */   }
/*    */ }

/* Location:           Z:\home\andrey\Documents\Projects\OpenTrader\docs\External\yahoofinanceapi-0.0.5-SNAPSHOT.jar
 * Qualified Name:     de.hdtconsulting.yahoo.finance.YExchangeRate
 * JD-Core Version:    0.6.0
 */