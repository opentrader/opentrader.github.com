/*     */ package de.hdtconsulting.yahoo.finance.csv.format;
/*     */ 
/*     */ import de.hdtconsulting.yahoo.finance.csv.exception.UnknownTagException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ 
/*     */ public class YFormat
/*     */ {
/* 316 */   private ArrayList<YTag> tagList = new ArrayList();
/*     */ 
/* 318 */   private HashMap<String, YTag> tagMap = new HashMap();
/*     */ 
/*     */   public YFormat()
/*     */   {
/* 106 */     YTag tag = new YTag(YTag.s);
/* 107 */     this.tagList.add(tag);
/*     */ 
/* 109 */     tag = new YTag(YTag.c8);
/* 110 */     this.tagList.add(tag);
/* 111 */     tag = new YTag(YTag.g3);
/* 112 */     this.tagList.add(tag);
/* 113 */     tag = new YTag(YTag.a);
/* 114 */     this.tagList.add(tag);
/* 115 */     tag = new YTag(YTag.b2);
/* 116 */     this.tagList.add(tag);
/* 117 */     tag = new YTag(YTag.a5);
/* 118 */     this.tagList.add(tag);
/* 119 */     tag = new YTag(YTag.a2);
/* 120 */     this.tagList.add(tag);
/* 121 */     tag = new YTag(YTag.b);
/* 122 */     this.tagList.add(tag);
/* 123 */     tag = new YTag(YTag.b3);
/* 124 */     this.tagList.add(tag);
/* 125 */     tag = new YTag(YTag.b6);
/* 126 */     this.tagList.add(tag);
/* 127 */     tag = new YTag(YTag.b4);
/* 128 */     this.tagList.add(tag);
/* 129 */     tag = new YTag(YTag.c1);
/* 130 */     this.tagList.add(tag);
/* 131 */     tag = new YTag(YTag.c);
/* 132 */     this.tagList.add(tag);
/* 133 */     tag = new YTag(YTag.m5);
/* 134 */     this.tagList.add(tag);
/* 135 */     tag = new YTag(YTag.m6);
/* 136 */     this.tagList.add(tag);
/* 137 */     tag = new YTag(YTag.m7);
/* 138 */     this.tagList.add(tag);
/* 139 */     tag = new YTag(YTag.m8);
/* 140 */     this.tagList.add(tag);
/* 141 */     tag = new YTag(YTag.k4);
/* 142 */     this.tagList.add(tag);
/* 143 */     tag = new YTag(YTag.k5);
/* 144 */     this.tagList.add(tag);
/* 145 */     tag = new YTag(YTag.j5);
/* 146 */     this.tagList.add(tag);
/* 147 */     tag = new YTag(YTag.j6);
/* 148 */     this.tagList.add(tag);
/* 149 */     tag = new YTag(YTag.k2);
/* 150 */     this.tagList.add(tag);
/* 151 */     tag = new YTag(YTag.p2);
/* 152 */     this.tagList.add(tag);
/* 153 */     tag = new YTag(YTag.c6);
/* 154 */     this.tagList.add(tag);
/* 155 */     tag = new YTag(YTag.c3);
/* 156 */     this.tagList.add(tag);
/* 157 */     tag = new YTag(YTag.r1);
/* 158 */     this.tagList.add(tag);
/* 159 */     tag = new YTag(YTag.d);
/* 160 */     this.tagList.add(tag);
/* 161 */     tag = new YTag(YTag.y);
/* 162 */     this.tagList.add(tag);
/* 163 */     tag = new YTag(YTag.e);
/* 164 */     this.tagList.add(tag);
/* 165 */     tag = new YTag(YTag.j4);
/* 166 */     this.tagList.add(tag);
/* 167 */     tag = new YTag(YTag.e7);
/* 168 */     this.tagList.add(tag);
/* 169 */     tag = new YTag(YTag.e9);
/* 170 */     this.tagList.add(tag);
/* 171 */     tag = new YTag(YTag.e8);
/* 172 */     this.tagList.add(tag);
/* 173 */     tag = new YTag(YTag.q);
/* 174 */     this.tagList.add(tag);
/* 175 */     tag = new YTag(YTag.f6);
/* 176 */     this.tagList.add(tag);
/* 177 */     tag = new YTag(YTag.k);
/* 178 */     this.tagList.add(tag);
/* 179 */     tag = new YTag(YTag.h);
/* 180 */     this.tagList.add(tag);
/* 181 */     tag = new YTag(YTag.l2);
/* 182 */     this.tagList.add(tag);
/* 183 */     tag = new YTag(YTag.g4);
/* 184 */     this.tagList.add(tag);
/* 185 */     tag = new YTag(YTag.g1);
/* 186 */     this.tagList.add(tag);
/* 187 */     tag = new YTag(YTag.g5);
/* 188 */     this.tagList.add(tag);
/* 189 */     tag = new YTag(YTag.g6);
/* 190 */     this.tagList.add(tag);
/* 191 */     tag = new YTag(YTag.v1);
/* 192 */     this.tagList.add(tag);
/* 193 */     tag = new YTag(YTag.v7);
/* 194 */     this.tagList.add(tag);
/* 195 */     tag = new YTag(YTag.d1);
/* 196 */     this.tagList.add(tag);
/* 197 */     tag = new YTag(YTag.l1);
/* 198 */     this.tagList.add(tag);
/* 199 */     tag = new YTag(YTag.k3);
/* 200 */     this.tagList.add(tag);
/* 201 */     tag = new YTag(YTag.t1);
/* 202 */     this.tagList.add(tag);
/* 203 */     tag = new YTag(YTag.l);
/* 204 */     this.tagList.add(tag);
/* 205 */     tag = new YTag(YTag.k1);
/* 206 */     this.tagList.add(tag);
/* 207 */     tag = new YTag(YTag.g);
/* 208 */     this.tagList.add(tag);
/* 209 */     tag = new YTag(YTag.l3);
/* 210 */     this.tagList.add(tag);
/* 211 */     tag = new YTag(YTag.j);
/* 212 */     this.tagList.add(tag);
/* 213 */     tag = new YTag(YTag.j1);
/* 214 */     this.tagList.add(tag);
/* 215 */     tag = new YTag(YTag.j3);
/* 216 */     this.tagList.add(tag);
/* 217 */     tag = new YTag(YTag.i);
/* 218 */     this.tagList.add(tag);
/* 219 */     tag = new YTag(YTag.m4);
/* 220 */     this.tagList.add(tag);
/* 221 */     tag = new YTag(YTag.m3);
/* 222 */     this.tagList.add(tag);
/* 223 */     tag = new YTag(YTag.n);
/* 224 */     this.tagList.add(tag);
/* 225 */     tag = new YTag(YTag.n4);
/* 226 */     this.tagList.add(tag);
/* 227 */     tag = new YTag(YTag.o);
/* 228 */     this.tagList.add(tag);
/* 229 */     tag = new YTag(YTag.i5);
/* 230 */     this.tagList.add(tag);
/* 231 */     tag = new YTag(YTag.r);
/* 232 */     this.tagList.add(tag);
/* 233 */     tag = new YTag(YTag.r2);
/* 234 */     this.tagList.add(tag);
/* 235 */     tag = new YTag(YTag.r5);
/* 236 */     this.tagList.add(tag);
/* 237 */     tag = new YTag(YTag.p);
/* 238 */     this.tagList.add(tag);
/* 239 */     tag = new YTag(YTag.p6);
/* 240 */     this.tagList.add(tag);
/* 241 */     tag = new YTag(YTag.r6);
/* 242 */     this.tagList.add(tag);
/* 243 */     tag = new YTag(YTag.r7);
/* 244 */     this.tagList.add(tag);
/* 245 */     tag = new YTag(YTag.p1);
/* 246 */     this.tagList.add(tag);
/* 247 */     tag = new YTag(YTag.p5);
/* 248 */     this.tagList.add(tag);
/* 249 */     tag = new YTag(YTag.w);
/* 250 */     this.tagList.add(tag);
/* 251 */     tag = new YTag(YTag.m);
/* 252 */     this.tagList.add(tag);
/* 253 */     tag = new YTag(YTag.m2);
/* 254 */     this.tagList.add(tag);
/* 255 */     tag = new YTag(YTag.s1);
/* 256 */     this.tagList.add(tag);
/* 257 */     tag = new YTag(YTag.s7);
/* 258 */     this.tagList.add(tag);
/* 259 */     tag = new YTag(YTag.x);
/* 260 */     this.tagList.add(tag);
/* 261 */     tag = new YTag(YTag.t8);
/* 262 */     this.tagList.add(tag);
/* 263 */     tag = new YTag(YTag.t7);
/* 264 */     this.tagList.add(tag);
/* 265 */     tag = new YTag(YTag.d2);
/* 266 */     this.tagList.add(tag);
/* 267 */     tag = new YTag(YTag.t6);
/* 268 */     this.tagList.add(tag);
/* 269 */     tag = new YTag(YTag.w1);
/* 270 */     this.tagList.add(tag);
/* 271 */     tag = new YTag(YTag.w4);
/* 272 */     this.tagList.add(tag);
/* 273 */     tag = new YTag(YTag.v);
/* 274 */     this.tagList.add(tag);
/*     */ 
/* 276 */     tag = new YTag(YTag.e1);
/* 277 */     this.tagList.add(tag);
/*     */ 
/* 280 */     for (YTag tag1 : this.tagList)
/* 281 */       this.tagMap.put(tag1.getCode(), tag1);
/*     */   }
/*     */ 
/*     */   public String getUrlFormatParameter()
/*     */   {
/* 292 */     StringBuffer sb = new StringBuffer();
/* 293 */     for (YTag tag : this.tagList) {
/* 294 */       if (tag.isEnabled()) {
/* 295 */         sb.append(tag.getCode());
/*     */       }
/*     */     }
/* 298 */     return sb.toString();
/*     */   }
/*     */ 
/*     */   public ArrayList<YTag> getEnabledTags()
/*     */   {
/* 307 */     ArrayList list = new ArrayList();
/* 308 */     for (YTag tag : this.tagList) {
/* 309 */       if (tag.isEnabled()) {
/* 310 */         list.add(tag);
/*     */       }
/*     */     }
/* 313 */     return list;
/*     */   }
/*     */ 
/*     */   public void setStatusOn(String code)
/*     */   {
/* 327 */     YTag tag = (YTag)this.tagMap.get(code);
/* 328 */     if (tag != null)
/* 329 */       tag.setEnabled(true);
/*     */     else
/* 331 */       throw new UnknownTagException();
/*     */   }
/*     */ 
/*     */   public void setStatusOff(String code)
/*     */   {
/* 342 */     YTag tag = (YTag)this.tagMap.get(code);
/* 343 */     if (tag != null)
/* 344 */       tag.setEnabled(false);
/*     */     else
/* 346 */       throw new UnknownTagException();
/*     */   }
/*     */ }

/* Location:           Z:\home\andrey\Documents\Projects\OpenTrader\docs\External\yahoofinanceapi-0.0.5-SNAPSHOT.jar
 * Qualified Name:     de.hdtconsulting.yahoo.finance.csv.format.YFormat
 * JD-Core Version:    0.6.0
 */