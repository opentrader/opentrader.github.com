package com.gain.api;
/**
 * Title:        Margin Trader Development
 * Description:  MT WebServices
 * Copyright:    Copyright (c) 2001
 * Company:      Gain Capital
 * @author       Phil Cave
 * @version 1.0
 * Date          02 July 2002
 */

/** Java Imports **/
import java.util.*;
import java.net.URLEncoder;

/**
 * MTWebService - provides access to the Margin Trader ASP.NET
 * WebService.  Allows caller to call methods on the WebService
 * and have results returned in the appropiate Java Object Types
 */
public class MTWebService {

    private static String className="MTWebService";

    /** Constants **/
    private static final String DEFAULT_METHOD ="POST";

    /** WebService Methods **/
    private static final String SYMBOL_BLOTTER = "Service.asmx/GetSymbolBlotter";
    private static final String MARGIN_BLOTTER = "Service.asmx/GetMarginBlotter";
    private static final String ORDER_BLOTTER = "Service.asmx/GetOrderBlotter";
    private static final String DEAL_BLOTTER = "Service.asmx/GetDealBlotter";
    private static final String POSITION_BLOTTER = "Service.asmx/GetPositionBlotter";
    private static final String GET_ACCOUNT = "Service.asmx/GetAccount";

    /** private properties */
    private String host_;
    private String queryKeyParam_;

    /**
    * Construct a MTWebService with the Host and Key Parameter
    */
    public MTWebService( String host, String Key) {

        // Set the internal members
        host_ = host;

        // Must services requires only the key
        // No others parameters are required, so create the query
        // parameters on construction
        queryKeyParam_ = "Key=" + URLEncoder.encode( Key );

    }

    /***************************************************************************************
     * Symbol Blotter Routines
     ***************************************************************************************/

    /**
    * Calls the SymbolBlotter WebService returning a CurrencyPair List;
    */
    public MTCurrencyList callSymbolBlotter() {

        // Call the WebService
        String xml = WebServiceUtil.callWebService( host_, SYMBOL_BLOTTER, queryKeyParam_, DEFAULT_METHOD );

        // if we do not get a result them return null, error already logged
        if( xml == null )
          return null;


        // Convert the currency list xml into the appropriate object
        MTCurrencyList ccyList = new  MTCurrencyList ( xml );

        // We know if the list has elemets if the static "currencyListInitialized" is true
        // if so we can return the list, otherwise return null as we did not get any currencies.
        // @TODO  This is very wierd use of static varibale for globalisation, should at least
        // have factor getInstance methods, not public constructors.
        if( MTCurrencyList.currencyListInitialized == true )
            return ccyList;
        else
            return null;
    }

    /***************************************************************************************
     * Margin Blotter Routines
     ***************************************************************************************/

    /**
    * Calls the MarginBlotter WebService returning a Margin;
    */
    public MTMargin callMarginBlotter() {

        // Call the WebService
        String xml = WebServiceUtil.callWebService( host_, MARGIN_BLOTTER, queryKeyParam_, DEFAULT_METHOD);

        // if we do not get a result them return null, error already logged
        if( xml == null )
          return null;

        try {

            // return a margin object of the xml data
            return new MTMargin( xml  );
        }
        catch( Exception e ) {
            // Conversion / Null pointer exception probably occured
            EventLog.log( className, "callMarginBlotter failed : " + xml );
            EventLog.logException( className, e );
            return null;
        }
    }

    /***************************************************************************************
     * Order Blotter Routines
     ***************************************************************************************/

    /**
    * Calls the OrderBlotter WebService returning a Order List;
    */
    public ArrayList callOrderBlotter() {

        // Call the WebService
        String xml = WebServiceUtil.callWebService( host_, ORDER_BLOTTER, queryKeyParam_, DEFAULT_METHOD);

        // if we do not get a result them return null, error already logged
        if( xml == null )
          return null;

        // convert xml response into Order List
        return createOrderListFromXML( xml );
    }

    /**
    * Create a order list from the XML passed in
    */
    protected ArrayList createOrderListFromXML( String xml ){

        // Create the target array list and the xml node parser
        ArrayList           orderList  = new ArrayList();
        SimpleXMLNodeParser ordersNode = new SimpleXMLNodeParser( xml );
        int count = 0;

        try {

            // Iterate all the order nodes getting extracting the orders and
            // adding them to the list
            String orderXML = ordersNode.getNextNode( "OrderBlotter" );
            while( orderXML != null ) {

                // Increment the count
                count++;

                // Create a new order from the order xml
                MTOrder order = new MTOrder ( orderXML );

                // add the order to the list
                orderList.add( order );

                // Get the next order in the list
                orderXML = ordersNode.getNextNode( "OrderBlotter" );
            }
        }
        catch( Exception e ) {
            // Conversion / Null pointer exception probably occured
            EventLog.log( className, "createOrderListFromXML failed : " + xml );
            EventLog.logException( className, e );
            return null;
        }

        /** [pc] 23/07/02 - Changed to be same as Servlet code
         *  Should return the list even if it is empty
        // if we did not add any then return null, otherwise return list.
        if( count == 0 )
            return null;
        else
            return orderList;
          */
          return orderList;
    }


    /***************************************************************************************
     * Deal Blotter Routines
     ***************************************************************************************/

    /**
    * Calls the DealBlotter WebService returning a Deal List;
    */
    public ArrayList callDealBlotter() {

        // Call the WebService
        String xml = WebServiceUtil.callWebService( host_, DEAL_BLOTTER, queryKeyParam_, DEFAULT_METHOD );

        // if we do not get a result them return null, error already logged
        if( xml == null )
          return null;

        // convert xml response into Order List
        return createDealListFromXML( xml );
    }

    /**
    * Create a deal list from the XML passed in
    */
    protected ArrayList createDealListFromXML( String xml ){

        // Create the target array list and the xml node parser
        ArrayList           dealList  = new ArrayList();
        SimpleXMLNodeParser dealsNode = new SimpleXMLNodeParser( xml );
        int count = 0;

        try {

            // Iterate all the deal nodes getting the deals and
            // adding them to the list
            String dealXML = dealsNode.getNextNode( "DealBlotter" );
            while( dealXML != null ) {

                // Increment the count
                count++;

                // Create a new deal from the deal xml
                MTDealResult deal = new MTDealResult ( dealXML );

                // add the deal to the list
                dealList.add( deal );

                // Get the next deal in the list
                dealXML = dealsNode.getNextNode( "DealBlotter" );
            }
        }
        catch( Exception e ) {
            // Conversion / Null pointer exception probably occured
            EventLog.log( className, "createDealListFromXML failed : " + xml );
            EventLog.logException( className, e );
            return null;
        }

        /** [pc] 23/07/02 - Changed to be same as Servlet code
         *  Should return the list even if it is empty
        // if we did not add any then return null, otherwise return list.
        if( count == 0 )
            return null;
        else
            return dealList;
        */
        return dealList;
    }


    /***************************************************************************************
     * Position Blotter Routines
     ***************************************************************************************/

    /**
    * Calls the PositionBlotter WebService returning a Position List
    */
    public ArrayList callPositionBlotter() {

        // Call the WebService
        String xml = WebServiceUtil.callWebService( host_, POSITION_BLOTTER, queryKeyParam_, DEFAULT_METHOD );

        // if we do not get a result them return null, error already logged
        if( xml == null )
          return null;

        // convert xml response into Order List
        return createPositionListFromXML( xml );
    }

    /**
    * Create a Position list from the XML passed in
    */
    protected ArrayList createPositionListFromXML( String xml ){

        // Create the target array list and the xml node parser
        ArrayList           positionList  = new ArrayList();
        SimpleXMLNodeParser positionsNode = new SimpleXMLNodeParser( xml );
        int count = 0;

        try {

            // Iterate all the position nodes getting the positions and
            // adding them to the list
            String positionXML = positionsNode.getNextNode( "PositionBlotter" );
            while( positionXML != null ) {

                // Increment the count
                count++;

                // Create a new position from the position xml
                MTRealTimePosition position = new MTRealTimePosition ( positionXML );

                // add the position to the list
                positionList.add( position );

                // Get the next position in the list
                positionXML = positionsNode.getNextNode( "PositionBlotter" );
            }
        }
        catch( Exception e ) {
            // Conversion / Null pointer exception probably occured
            EventLog.log( className, "createPositionListFromXML failed : " + xml );
            EventLog.logException( className, e );
            return null;
        }

        /** [pc] 23/07/02 - Changed to be same as Servlet code
         *  Should return the list even if it is empty
        // if we did not add any then return null, otherwise return list.
        if( count == 0 )
            return null;
        else
            return positionList;
        */
        return positionList;
    }

    /***************************************************************************************
     * Get Account Routines
     ***************************************************************************************/

    /**
    * Calls the GetAccount WebService returning the MTAccount
    */
    public MTAccount callGetAccount( String login, String password ) {

        // Call the WebService
        String xml = WebServiceUtil.callWebService( host_, GET_ACCOUNT,
                                                    "UserId=" + URLEncoder.encode( login ) + "&PWD=" + URLEncoder.encode( password ),
                                                    DEFAULT_METHOD );

        // if we do not get a result them return null, error already logged
        if( xml == null )
          return null;

        // create a MTAccount from the xml
        MTAccount mtAccount = new MTAccount( xml );

        // Check the account is valid, if not return null else return the account
        if( mtAccount.getCustomerCode() == null || mtAccount.getGUID() == null )
            return null;
        else
            return mtAccount;
    }


}