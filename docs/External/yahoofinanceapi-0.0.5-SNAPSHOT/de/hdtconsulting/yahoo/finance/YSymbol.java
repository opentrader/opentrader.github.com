/*     */ package de.hdtconsulting.yahoo.finance;
/*     */ 
/*     */ public class YSymbol
/*     */ {
/*     */   private String code;
/*  48 */   public static String AMEX = "";
/*     */ 
/*  50 */   public static String NASDAQ = "";
/*     */ 
/*  52 */   public static String NYSE = "";
/*     */ 
/*  54 */   public static String OB = "OB";
/*     */ 
/*  56 */   public static String PK = "PK";
/*     */ 
/*  58 */   public static String BA = "BA";
/*     */ 
/*  60 */   public static String VI = "VI";
/*     */ 
/*  62 */   public static String AX = "AX";
/*     */ 
/*  64 */   public static String SA = "SA";
/*     */ 
/*  66 */   public static String TO = "TO";
/*     */ 
/*  68 */   public static String V = "V";
/*     */ 
/*  70 */   public static String SS = "SS";
/*     */ 
/*  72 */   public static String SZ = "SZ";
/*     */ 
/*  74 */   public static String CO = "CO";
/*     */ 
/*  76 */   public static String PA = "PA";
/*     */ 
/*  78 */   public static String BE = "BE";
/*     */ 
/*  80 */   public static String DU = "DU";
/*     */ 
/*  82 */   public static String F = "F";
/*     */ 
/*  84 */   public static String HM = "HM";
/*     */ 
/*  86 */   public static String HA = "HA";
/*     */ 
/*  88 */   public static String MU = "MU";
/*     */ 
/*  90 */   public static String SG = "SG";
/*     */ 
/*  92 */   public static String DE = "DE";
/*     */ 
/*  94 */   public static String HK = "HK";
/*     */ 
/*  96 */   public static String BO = "BO";
/*     */ 
/*  98 */   public static String NS = "NS";
/*     */ 
/* 100 */   public static String JK = "JK";
/*     */ 
/* 102 */   public static String IR = "IR";
/*     */ 
/* 104 */   public static String TA = "TA";
/*     */ 
/* 106 */   public static String MI = "MI";
/*     */ 
/* 108 */   public static String KS = "KS";
/*     */ 
/* 110 */   public static String KQ = "KQ";
/*     */ 
/* 112 */   public static String MX = "MX";
/*     */ 
/* 114 */   public static String AS = "AS";
/*     */ 
/* 116 */   public static String NZ = "NZ";
/*     */ 
/* 118 */   public static String OL = "OL";
/*     */ 
/* 120 */   public static String LS = "LS";
/*     */ 
/* 122 */   public static String SI = "SI";
/*     */ 
/* 124 */   public static String BC = "BC";
/*     */ 
/* 126 */   public static String BI = "BI";
/*     */ 
/* 128 */   public static String MF = "MF";
/*     */ 
/* 130 */   public static String MC = "MC";
/*     */ 
/* 132 */   public static String MA = "MA";
/*     */ 
/* 134 */   public static String ST = "ST";
/*     */ 
/* 136 */   public static String SW = "SW";
/*     */ 
/* 138 */   public static String VX = "VX";
/*     */ 
/* 140 */   public static String TWO = "TWO";
/*     */ 
/* 142 */   public static String TW = "TW";
/*     */ 
/* 144 */   public static String BK = "BK";
/*     */ 
/* 146 */   public static String L = "L";
/*     */ 
/*     */   public YSymbol(String code)
/*     */   {
/*  13 */     this.code = code;
/*     */   }
/*     */ 
/*     */   public String getCode() {
/*  17 */     return this.code;
/*     */   }
/*     */ 
/*     */   public int hashCode()
/*     */   {
/*  22 */     return this.code.hashCode();
/*     */   }
/*     */ 
/*     */   public boolean equals(Object o)
/*     */   {
/*     */     YSymbol symbol;
/*  30 */     if ((o instanceof YSymbol))
/*  31 */       symbol = (YSymbol)o;
/*     */     else
/*  33 */       return false;
/*     */     YSymbol symbol;
/*  37 */     return this.code.equals(symbol.getCode());
/*     */   }
/*     */ }

/* Location:           Z:\home\andrey\Documents\Projects\OpenTrader\docs\External\yahoofinanceapi-0.0.5-SNAPSHOT.jar
 * Qualified Name:     de.hdtconsulting.yahoo.finance.YSymbol
 * JD-Core Version:    0.6.0
 */