/*     */ package de.hdtconsulting.yahoo.finance;
/*     */ 
/*     */ import au.com.bytecode.opencsv.CSVReader;
/*     */ import de.hdtconsulting.yahoo.finance.csv.connection.YConnection;
/*     */ import de.hdtconsulting.yahoo.finance.csv.connection.YConnectionDayTrade;
/*     */ import de.hdtconsulting.yahoo.finance.csv.connection.YConnectionHistoric;
/*     */ import de.hdtconsulting.yahoo.finance.csv.connection.YHost;
/*     */ import de.hdtconsulting.yahoo.finance.csv.format.YFormat;
/*     */ import de.hdtconsulting.yahoo.finance.csv.format.YTag;
/*     */ import java.io.IOException;
/*     */ import java.io.PrintStream;
/*     */ import java.io.StringReader;
/*     */ import java.math.BigDecimal;
/*     */ import java.net.MalformedURLException;
/*     */ import java.net.URISyntaxException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Date;
/*     */ import java.util.List;
/*     */ import org.apache.log4j.Logger;
/*     */ 
/*     */ public class Yapi
/*     */ {
/*  27 */   private static final Logger logger = Logger.getLogger(Yapi.class);
/*     */ 
/*  29 */   public static String HIST_DAYLY = "d";
/*     */ 
/*  31 */   public static String HIST_WEEKLY = "w";
/*     */ 
/*  33 */   public static String HIST_MONTHLY = "m";
/*     */ 
/*  35 */   public static String HIST_DIVIDEND = "v";
/*     */ 
/*  37 */   private YConnection connection = new YConnection();
/*     */ 
/*  39 */   private YConnectionDayTrade connectionDayTrade = new YConnectionDayTrade();
/*     */ 
/*  41 */   private YConnectionHistoric connectionHistoric = new YConnectionHistoric();
/*     */ 
/*  43 */   private YQuoteList quoteList = new YQuoteList();
/*     */ 
/*  45 */   private YFormat format = new YFormat();
/*     */   private Date refreshTime;
/*     */   private String csv;
/*     */ 
/*     */   public void setProxy(YHost proxy)
/*     */   {
/*  52 */     this.connection.setProxy(proxy);
/*  53 */     this.connectionDayTrade.setProxy(proxy);
/*  54 */     this.connectionHistoric.setProxy(proxy);
/*     */   }
/*     */ 
/*     */   public void resetProxy() {
/*  58 */     this.connection.resetProxy();
/*  59 */     this.connectionHistoric.resetProxy();
/*  60 */     this.connectionDayTrade.resetProxy();
/*     */   }
/*     */ 
/*     */   public void setFormat(YFormat format) {
/*  64 */     this.format = format;
/*     */   }
/*     */ 
/*     */   public Date getRefreshTime() {
/*  68 */     return this.refreshTime;
/*     */   }
/*     */ 
/*     */   public String getCsv() {
/*  72 */     return this.csv;
/*     */   }
/*     */ 
/*     */   public void addTag(String code) {
/*  76 */     this.format.setStatusOn(code);
/*     */   }
/*     */ 
/*     */   public void removeTag(String code) {
/*  80 */     this.format.setStatusOff(code);
/*     */   }
/*     */ 
/*     */   public void addQuote(YSymbol symbol) {
/*  84 */     this.quoteList.add(symbol);
/*     */   }
/*     */ 
/*     */   public void removeQuote(YSymbol symbol) {
/*  88 */     this.quoteList.remove(symbol);
/*     */   }
/*     */ 
/*     */   public void removeAllQuotes() {
/*  92 */     this.quoteList.clear();
/*     */   }
/*     */ 
/*     */   public void refresh()
/*     */   {
/*  97 */     if (logger.isDebugEnabled()) {
/*  98 */       logger.debug("refresh() - start");
/*     */     }
/*     */ 
/*     */     try
/*     */     {
/* 103 */       refreshRealTime();
/*     */ 
/* 105 */       refreshDayTrade();
/*     */ 
/* 107 */       this.refreshTime = new Date();
/*     */     }
/*     */     catch (Exception e) {
/* 110 */       logger.error("refresh()", e);
/*     */ 
/* 112 */       System.out.println(e.getMessage());
/*     */     }
/*     */ 
/* 115 */     if (logger.isDebugEnabled())
/* 116 */       logger.debug("refresh() - end");
/*     */   }
/*     */ 
/*     */   public void refreshRealTime()
/*     */     throws MalformedURLException, IOException, URISyntaxException
/*     */   {
/* 124 */     if (logger.isDebugEnabled()) {
/* 125 */       logger.debug("refreshRealTime() - start");
/*     */     }
/*     */ 
/* 128 */     this.connection.setFormat(this.format.getUrlFormatParameter());
/* 129 */     this.connection.setSymbols(this.quoteList.getUrlSymbolParameter());
/*     */ 
/* 131 */     this.csv = this.connection.getCsv();
/*     */ 
/* 133 */     StringReader br = new StringReader(this.csv);
/* 134 */     CSVReader reader = new CSVReader(br);
/*     */ 
/* 136 */     List csvdata = reader.readAll();
/*     */ 
/* 139 */     List enabledTags = this.format.getEnabledTags();
/*     */ 
/* 141 */     for (String[] csvline : csvdata)
/*     */     {
/* 143 */       YQuote quote = this.quoteList.getStockQuote(csvline[0]);
/* 144 */       int i = 0;
/* 145 */       for (YTag tag : enabledTags) {
/* 146 */         quote.setValue(tag, csvline[i]);
/* 147 */         i++;
/*     */       }
/* 149 */       if ("N/A".equals(quote.getValue(YTag.ERROR_INDICATION))) {
/* 150 */         quote.setValid(true);
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 155 */     if (logger.isDebugEnabled())
/* 156 */       logger.debug("refreshRealTime() - end");
/*     */   }
/*     */ 
/*     */   public void refreshDayTrade()
/*     */     throws MalformedURLException, IOException, URISyntaxException
/*     */   {
/* 164 */     if (logger.isDebugEnabled()) {
/* 165 */       logger.debug("refreshDayTrade() - start");
/*     */     }
/*     */ 
/* 168 */     for (YQuote quote : this.quoteList.getQuotes())
/*     */     {
/* 170 */       this.connectionDayTrade.setSymbol(quote.getSymbol().getCode());
/*     */ 
/* 172 */       quote.setCsvDayTrade(this.connectionDayTrade.getCsv());
/*     */ 
/* 174 */       StringReader br = new StringReader(quote.getCsvDayTrade());
/* 175 */       CSVReader reader = new CSVReader(br, ';');
/*     */ 
/* 177 */       List csvdata = reader.readAll();
/* 178 */       ArrayList dayTrades = new ArrayList();
/*     */ 
/* 180 */       for (String[] csvline : csvdata) {
/* 181 */         YTrade trade = new YTrade();
/* 182 */         trade.setDate(csvline[0]);
/* 183 */         trade.setTime(csvline[1]);
/* 184 */         trade.setValue(new BigDecimal(csvline[2]));
/* 185 */         trade.setVolume(new Integer(csvline[3]));
/* 186 */         trade.setUnknown(new Integer(csvline[4]));
/* 187 */         dayTrades.add(trade);
/*     */       }
/* 189 */       quote.setDayTrades(dayTrades);
/*     */     }
/*     */ 
/* 193 */     if (logger.isDebugEnabled())
/* 194 */       logger.debug("refreshDayTrade() - end");
/*     */   }
/*     */ 
/*     */   public YQuote getHistoric(YSymbol symbol, Date startDate, Date endDate, String interval)
/*     */   {
/* 202 */     if (logger.isDebugEnabled())
/* 203 */       logger.debug("getHistoric(YSymbol, Date, Date, String) - start");
/*     */     YQuote quote;
/* 208 */     if ((quote = this.quoteList.getStockQuote(symbol)) == null) {
/* 209 */       quote = new YQuote(symbol);
/*     */     }
/*     */ 
/* 212 */     this.connectionHistoric.setSymbol(quote.getSymbol().getCode());
/* 213 */     this.connectionHistoric.setStartDate(startDate);
/* 214 */     this.connectionHistoric.setEndDate(endDate);
/* 215 */     this.connectionHistoric.setInterval(interval);
/*     */     try
/*     */     {
/* 219 */       quote.setCsvHistoric(this.connectionHistoric.getCsv());
/*     */ 
/* 221 */       StringReader br = new StringReader(quote.getCsvHistoric());
/* 222 */       CSVReader reader = new CSVReader(br);
/*     */ 
/* 224 */       List csvdata = reader.readAll();
/* 225 */       ArrayList historics = new ArrayList();
/*     */ 
/* 227 */       for (String[] csvline : csvdata)
/*     */       {
/* 230 */         if (!"Date".equals(csvline[0])) {
/* 231 */           YHistoric historic = new YHistoric();
/* 232 */           historic.setDate(csvline[0]);
/* 233 */           historic.setOpen(new BigDecimal(csvline[1]));
/* 234 */           historic.setHigh(new BigDecimal(csvline[2]));
/* 235 */           historic.setLow(new BigDecimal(csvline[3]));
/* 236 */           historic.setClose(new BigDecimal(csvline[4]));
/* 237 */           historic.setVolume(new BigDecimal(csvline[5]));
/* 238 */           historic.setAdjClose(new BigDecimal(csvline[6]));
/* 239 */           historics.add(historic);
/*     */         }
/*     */       }
/*     */ 
/* 243 */       quote.setHistorics(historics);
/*     */     }
/*     */     catch (Exception e) {
/* 246 */       logger.error("getHistoric(YSymbol, Date, Date, String)", e);
/*     */ 
/* 248 */       System.out.println(e.getMessage());
/*     */     }
/*     */ 
/* 251 */     if (logger.isDebugEnabled()) {
/* 252 */       logger.debug("getHistoric(YSymbol, Date, Date, String) - end");
/*     */     }
/*     */ 
/* 255 */     return quote;
/*     */   }
/*     */ 
/*     */   public ArrayList<YQuote> getQuotes()
/*     */   {
/* 260 */     return this.quoteList.getQuotes();
/*     */   }
/*     */ }

/* Location:           Z:\home\andrey\Documents\Projects\OpenTrader\docs\External\yahoofinanceapi-0.0.5-SNAPSHOT.jar
 * Qualified Name:     de.hdtconsulting.yahoo.finance.Yapi
 * JD-Core Version:    0.6.0
 */