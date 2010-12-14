/*     */ package de.hdtconsulting.yahoo.finance;
/*     */ 
/*     */ import de.hdtconsulting.yahoo.finance.csv.format.YTag;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ 
/*     */ public class YQuote
/*     */ {
/*     */   private YSymbol symbol;
/*  18 */   private HashMap<YTag, String> values = new HashMap();
/*     */ 
/*  20 */   private ArrayList<YTrade> dayTrades = new ArrayList();
/*     */ 
/*  22 */   private ArrayList<YHistoric> historics = new ArrayList();
/*     */   private String csvDayTrade;
/*     */   private String csvHistoric;
/*  60 */   private boolean valid = false;
/*     */ 
/*     */   public ArrayList<YHistoric> getHistorics()
/*     */   {
/*  25 */     return new ArrayList(this.historics);
/*     */   }
/*     */ 
/*     */   protected void setHistorics(ArrayList<YHistoric> historics) {
/*  29 */     this.historics = historics;
/*     */   }
/*     */ 
/*     */   public ArrayList<YTrade> getDayTrades() {
/*  33 */     return new ArrayList(this.dayTrades);
/*     */   }
/*     */ 
/*     */   protected void setDayTrades(ArrayList<YTrade> dayTrades) {
/*  37 */     this.dayTrades = dayTrades;
/*     */   }
/*     */ 
/*     */   public String getCsvHistoric()
/*     */   {
/*  45 */     return this.csvHistoric;
/*     */   }
/*     */ 
/*     */   protected void setCsvHistoric(String csvHistoric) {
/*  49 */     this.csvHistoric = csvHistoric;
/*     */   }
/*     */ 
/*     */   public String getCsvDayTrade() {
/*  53 */     return this.csvDayTrade;
/*     */   }
/*     */ 
/*     */   protected void setCsvDayTrade(String csvDayTrade) {
/*  57 */     this.csvDayTrade = csvDayTrade;
/*     */   }
/*     */ 
/*     */   protected YQuote(YSymbol symbol)
/*     */   {
/*  63 */     this.symbol = symbol;
/*     */   }
/*     */ 
/*     */   public boolean isValid() {
/*  67 */     return this.valid;
/*     */   }
/*     */ 
/*     */   protected void setValid(boolean valid) {
/*  71 */     this.valid = valid;
/*     */   }
/*     */ 
/*     */   public YSymbol getSymbol() {
/*  75 */     return this.symbol;
/*     */   }
/*     */ 
/*     */   public String getValue(String key) {
/*  79 */     YTag tag = new YTag(key);
/*  80 */     return (String)this.values.get(tag);
/*     */   }
/*     */ 
/*     */   protected void setValue(YTag tag, String value) {
/*  84 */     this.values.put(tag, value);
/*     */   }
/*     */ 
/*     */   public int hashCode()
/*     */   {
/*  89 */     return this.symbol.hashCode();
/*     */   }
/*     */ 
/*     */   public boolean equals(Object o)
/*     */   {
/*     */     YQuote quote;
/*  97 */     if ((o instanceof YQuote))
/*  98 */       quote = (YQuote)o;
/*     */     else
/* 100 */       return false;
/*     */     YQuote quote;
/* 104 */     return this.symbol.equals(quote.getSymbol());
/*     */   }
/*     */ }

/* Location:           Z:\home\andrey\Documents\Projects\OpenTrader\docs\External\yahoofinanceapi-0.0.5-SNAPSHOT.jar
 * Qualified Name:     de.hdtconsulting.yahoo.finance.YQuote
 * JD-Core Version:    0.6.0
 */