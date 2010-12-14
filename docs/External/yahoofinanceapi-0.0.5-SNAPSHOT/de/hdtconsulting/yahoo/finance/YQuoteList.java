/*    */ package de.hdtconsulting.yahoo.finance;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.HashMap;
/*    */ 
/*    */ public class YQuoteList
/*    */ {
/* 13 */   private ArrayList<YQuote> quoteList = new ArrayList();
/*    */ 
/* 15 */   private HashMap<YSymbol, YQuote> quoteMap = new HashMap();
/*    */ 
/* 17 */   private HashMap<String, YSymbol> symbolMap = new HashMap();
/*    */ 
/*    */   public YQuote getStockQuote(String code) {
/* 20 */     return (YQuote)this.quoteMap.get(this.symbolMap.get(code));
/*    */   }
/*    */ 
/*    */   public YQuote getStockQuote(YSymbol symbol) {
/* 24 */     return (YQuote)this.quoteMap.get(symbol);
/*    */   }
/*    */ 
/*    */   public void add(YSymbol symbol) {
/* 28 */     YQuote quote = new YQuote(symbol);
/* 29 */     this.quoteList.add(quote);
/* 30 */     this.quoteMap.put(symbol, quote);
/* 31 */     this.symbolMap.put(symbol.getCode(), symbol);
/*    */   }
/*    */ 
/*    */   public void remove(YSymbol symbol) {
/* 35 */     YQuote quote = (YQuote)this.quoteMap.get(symbol);
/* 36 */     this.quoteList.remove(quote);
/* 37 */     this.quoteMap.remove(symbol);
/* 38 */     this.symbolMap.remove(symbol.getCode());
/*    */   }
/*    */ 
/*    */   public void clear() {
/* 42 */     this.quoteList.clear();
/* 43 */     this.quoteMap.clear();
/* 44 */     this.symbolMap.clear();
/*    */   }
/*    */ 
/*    */   public String getUrlSymbolParameter()
/*    */   {
/* 49 */     StringBuffer sb = new StringBuffer();
/*    */ 
/* 51 */     for (YQuote s : this.quoteList) {
/* 52 */       sb.append("+");
/* 53 */       sb.append(s.getSymbol().getCode());
/*    */     }
/*    */ 
/* 56 */     return sb.toString();
/*    */   }
/*    */ 
/*    */   public int size()
/*    */   {
/* 61 */     return this.quoteList.size();
/*    */   }
/*    */ 
/*    */   public ArrayList<YQuote> getQuotes() {
/* 65 */     ArrayList list = new ArrayList(this.quoteList);
/* 66 */     return list;
/*    */   }
/*    */ }

/* Location:           Z:\home\andrey\Documents\Projects\OpenTrader\docs\External\yahoofinanceapi-0.0.5-SNAPSHOT.jar
 * Qualified Name:     de.hdtconsulting.yahoo.finance.YQuoteList
 * JD-Core Version:    0.6.0
 */