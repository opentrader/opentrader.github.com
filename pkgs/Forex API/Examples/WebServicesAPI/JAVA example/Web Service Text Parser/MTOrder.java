
package com.gain.api;

import com.gain.fxmarginserver.*;
import java.util.*;

public class MTOrder
{
    private static String className="MTOrder";
    private String operation="";
    private long orderID;
    private String currencyPair="";
    private String orderDate="";
    private String orderType="";
    private String orderExpiration="";
    private String referenceNumber="";
    private String buySell="";
    private long amount;
    private String rate="";
    private String basis="";
    private boolean orderAccepted=true;
    private boolean orderExecuted=false;
    private String updateData="";
    public boolean backSideOCO=false;

    public String getOperation() {       return operation;   }
    public long getOrderId() {       return orderID;   }
    public String getCurrencyPair() {   return currencyPair;      }
    public String getOrderDate() {  return orderDate;   }
    public String getOrderType() {  return orderType;   }
    public String getOrderExpiration(){   return orderExpiration ;        }
    public String getReferenceNumber() {    return referenceNumber;      }
    public String getBuySell() {       return buySell;   }
    public long getAmount() {        return amount;        }
    public String getRate() {        return rate;        }
    public String getBasis() {      return basis;      }
    public boolean getBackSideOCO(){ return backSideOCO; }
    public boolean getOrderAccepted()   {       return orderAccepted;      }
    public boolean getOrderExecuted()   {       return orderExecuted;      }
    public String getUpdateData()   {       return updateData;      }

    // 12-12-02 [pc] Added Order Status
    private String orderStatus = "";
    public String getOrderStatus() { return orderStatus; }

    public String toString()
    {
          StringBuffer buffer=new StringBuffer();

          buffer.append( "MTOrder object values ...\n" );
          buffer.append( "  Operation=" + getOperation()  );
          buffer.append( "  Order Id=" + getOrderId()  );
          buffer.append( "  Currency Pair=" + getCurrencyPair() );
          buffer.append( "  Order Date=" + getOrderDate() );
          buffer.append( "  Order Type=" + getOrderType() );
          buffer.append( "  Order Expiration=" + getOrderExpiration() + "\n" );
          buffer.append( "  Reference Number=" + getReferenceNumber() );
          buffer.append( "  Buy/Sell=" + getBuySell() );
          buffer.append( "  Amount=" + getAmount());
          buffer.append( "  Rate=" + getRate());
          buffer.append( "  Basis=" + getBasis() );
          buffer.append( "  Amount=" + getAmount() );
          buffer.append( "  Reason=" + getUpdateData() + "\n" );
          buffer.append( "  Order Accepted=" + getOrderAccepted() );
          buffer.append( "  Order Executed=" + getOrderExecuted() );
          buffer.append( "  Order Status=" + getOrderStatus() );

          return( buffer.toString() );
    }


    public MTOrder( ODL_Data odlData, String currencyPair )
    {
      operation = "Order Listing";
      orderID = odlData.GetOrderID();

      char buySellChar = odlData.GetBuySell();
      if (buySellChar == 'B')
          buySell="Buy";
      else
          buySell="Sell";

      this.currencyPair = currencyPair;
      amount = odlData.GetAmount();
      rate = odlData.GetRate();
      orderDate = odlData.GetDateTime();
      referenceNumber = odlData.GetConfirmation();

      String orderBasis = odlData.GetOrderBasis();
      if ( orderBasis.equals( "T" ) == true )
        basis = "Limit";
      else
        basis = "Stop Loss";

      orderExpiration = odlData.GetExpiryType();

    }



    public MTOrder(ArrayList orderList)
    {

      try
      {
          if ( orderList.size() != 10 )
          {
              EventLog.log( className, "MTOrder constructor error size=" + orderList.size());
              return;
          }

          operation = "Order Listing";

          Integer orderRef = (Integer)orderList.get(0);
          orderID = (long)orderRef.intValue();

          currencyPair = (String)orderList.get(1);

          String buySellChar = (String)orderList.get(2);
          if (buySellChar.charAt(0) == 'B')
              buySell="Buy";
          else
              buySell="Sell";

          Integer orderAmount = (Integer)orderList.get(3);
          amount = orderAmount.longValue();

          rate = (String)orderList.get(4);

          java.sql.Timestamp orderDateInfo = (java.sql.Timestamp)orderList.get(5);
          orderDate = orderDateInfo.toString();

          referenceNumber = (String)orderList.get(6);

          orderType = (String)orderList.get(7);

          String orderBasis = (String)orderList.get(8);
          if ( orderBasis.equals( "T" ) == true )
              basis = "Limit";
          else
              basis = "Stop Loss";

          orderExpiration = (String)orderList.get(9);



      }
      catch (Exception e)
      {
          EventLog.log( className, "MTOrder exception caught=");
          EventLog.logException( className, e );
      }
    }





    public MTOrder(ORC orc)
    {
      operation = "Order Cancelled";
      orderID = orc.GetOrderID();
      referenceNumber = orc.GetBankConf();
      updateData = orc.GetReasonCode();

    }

    public MTOrder(ORF orf)
    {
      operation = "Order Failed";
      orderID = orf.GetOrderID();
      updateData = orf.GetReasonCode();

    }

    public MTOrder(ODF odf)
    {
      operation = "Order Deal Failed";
      orderID = odf.GetOrderID();
      referenceNumber = odf.getBankReference();
      currencyPair = odf.GetCurrencyPair();
      updateData = odf.GetUpdateData();

    }

    public MTOrder(ODD odd)
    {
      operation = "Order Executed";
      orderID = odd.GetOrderID();
      currencyPair = odd.GetCurrencyPair();
      updateData = odd.GetUpdateData();
      orderExecuted = true;
    }

    public MTOrder(ORD ord, int order)
    {
      operation = "Order Accepted";
      orderID = ord.GetOrderID();
      currencyPair = ord.GetCurrencyPair();
      orderDate = ord.GetOrderDate();
      orderExpiration = ord.GetOrderExpiry();

      if ( order == 1 )
      {
          referenceNumber = ord.GetBankConf1();

          char buySellChar = ord.GetBuySell1();
          if (buySellChar == 'B')
              buySell="Buy";
          else
              buySell="Sell";

          amount = ord.GetAmount1();

          rate = ord.GetRate1();

          String orderBasis = ord.GetBasis1();
          if ( orderBasis.equals( "T" ) == true )
            basis = "Limit";
          else
            basis = "Stop Loss";
      }
      // 16-01-03 [pc] Add 3rd order
      else if (order == 2)  //second order in THEN or OCO
      {
          // its an OCO if it not a THEN order
          backSideOCO = (ord.getThenOrder2() == false);
          referenceNumber = ord.GetBankConf2();
          if ( referenceNumber.length() == 0 ) //No OCO part in order
              return;

          char buySellChar = ord.GetBuySell2();
          if (buySellChar == 'B')
              buySell="Buy";
          else
              buySell="Sell";

          amount = ord.GetAmount2();

          rate = ord.GetRate2();

          String orderBasis = ord.GetBasis2();
          if ( orderBasis.equals( "T" ) == true )
            basis = "Limit";
          else
            basis = "Stop Loss";
      }
      else if (order == 3)  //second order in THEN or OCO
      {
         // its an THEN order
         backSideOCO = false;
         referenceNumber = ord.GetBankConf3();
         if ( referenceNumber.length() == 0 )
             return;

         char buySellChar = ord.GetBuySell3();
         if (buySellChar == 'B')
             buySell="Buy";
         else
             buySell="Sell";

         amount = ord.GetAmount3();

         rate = ord.GetRate3();

         String orderBasis = ord.GetBasis3();
         if ( orderBasis.equals( "T" ) == true )
           basis = "Limit";
         else
           basis = "Stop Loss";
     }



    }

    /* @PHILCAVE - Added Function */
    /**
     * Construct a MTOrder from the xml passed in, throws exception if problem converting
     */
     public MTOrder( String xml ) throws Exception {

        // Create a node parser from the order
        SimpleXMLNodeParser orderNode = new SimpleXMLNodeParser( xml );

        // Set the internal members
        orderID = Long.parseLong( orderNode.getNextNode( "CustomerOrderReference"));
        currencyPair = orderNode.getNextNode( "Symbol");
        buySell = ( orderNode.getNextNode( "BuySell").charAt(0)=='B' ? "Buy" : "Sell" );
        amount = Long.parseLong( orderNode.getNextNode( "ContractValue") );
        rate =  orderNode.getNextNode( "Price");
        orderDate = orderNode.getNextNode( "OrderDate");
        referenceNumber = orderNode.getNextNode( "ConfirmationNumber");
        orderType = orderNode.getNextNode( "OrderType");
        basis = ( orderNode.getNextNode( "OrderBasis").charAt(0)=='T' ? "Limit" : "Stop Loss" );
        orderExpiration = orderNode.getNextNode( "Expiry");
        orderStatus     = orderNode.getNextNode( "OrderStatus");

        operation = "Order Listing";
    }

}
