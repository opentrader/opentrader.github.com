/**
 * Rate Service Example
 *
 * Phil Cave
 *
 * April 2003
 *
 * Gain Capital
 */

// Import the rates service classes
import com.gain.rateservice.*;

/**
 * This class provides an example of using the Rates Service Library
 */
public class RateServiceExample implements RateServiceListener {

    /**
     * private propperties
     */
    private RateService _rateService = null;

    /**
     * Construct the Rate Service Example class with the required parameters
     */
    public RateServiceExample( String host, int port, String key ) {

        // Create a rate service object, using this class as an RateServiceListener
        // passing the required host, port and key
        _rateService = new RateService( this, host, port, key );
    }

    /**
     * Start the Rates service
     */
    public void start()
    {
        // Start the rates service by making the connection
        // NOTE: Rate Service caches host, port and key, this mechanism
        // provided lazy initialisation.
        // Notification is passed when the connection is either created successfully
        // or if the connection fails using RateServiceListener callbacks
        _rateService.connect();
    }

    /**
     * Stops the rates service
     */
    public void stop()
    {
        // Stop the service by disconnecting
        // Sockets and resources are clean up when this is called
        // notification passed by the RateService Listener when disconnected
        _rateService.disconnect();
    }

    /**
     * Restarts the rates service
     */
    public void restart()
    {
        // If the service is connected is disconnects
        if( _rateService.isConnected() )
        {
            _rateService.disconnect();
        }

        // NOTE: it is recommened then you pause your service before reconnecting
        // the rate service. This is so you do not overloaded the system with constant
        // reconnects on failures.
        try { Thread.sleep( 5000 ); } catch( Exception e) {}

        // then start the service again.
        _rateService.connect();
    }

    /***************************************************************************
     * RateServiceListener Callbacks
     **************************************************************************/

    /**
      * OnRateServiceConnected() - Called when the Rate service connects to the Rate Server and the connection has been authenicated.
      */
    public void OnRateServiceConnected()
    {
        // Notification when a successfull connection attempt has been made, this can be
        // used to set an internal flag that the service is connected or notifty clients of the
        // service that the service is connected and ready
        System.out.println( "RateServiceExample: connected successfully");
    }

    /**
     * OnRateServiceConnectionFailed(Exception e) - Called when the Rate service fails to connect to the Rate Server with the Exception that caused the failure.
     * @param e Exception which caused the lost connection.
     */
     public void OnRateServiceConnectionFailed( Exception e )
    {
        // Notification when a failed connection attempt has been made, this failure is general fatal and
        // most likely reasons for failure are the connection paramaters are incorrect.  The exception provides the
        // details of the failure.  The client should only attempt an reconnection if they are certain the connection
        // parameters are correct and the error is due to network or remote server issues.
        System.out.println( "RateServiceExample: connection failed: " + e.getClass().getName() + " : " + e.getMessage() );

        // Here is a simply strategy for deciding if you should reconnect using data maintain by rate service
        // More advanced techniques could be used which incorporate a longer reconnect sleep time based
        // on the number of failures or the type of exception.

        // If we have ever connected then we will retry the connection, providing the consectutive failed connection is less then
        // 10 times.
        if( _rateService.getSuccessfullCounnectionCount() > 0 && _rateService.getFailedConsecutiveCounnectionCount() < 10 )
            restart();
    }

    /**
     * OnRateServiceConnectionLost(Exception e) - Called when the Rate service connection to the Rate Server is lost with the Exception that causes the loss.
     * @param e Exception which caused the lost connection.
     */
     public void OnRateServiceConnectionLost( Exception e )
    {
        // Notification when a failed connection attempt has been made, this failure is general caused by a
        // Network outage or remote server failure. The exception provides the details of the failure.
        // The client should only attempt an reconnection if they are certain the connection
        // parameters are correct and the error is due to network or remote server issues.
        System.out.println( "RateServiceExample: connection lost: " + e.getClass().getName() + " : " + e.getMessage() );

        // Here is a simply strategy for deciding if you should reconnect using data maintain by rate service
        // More advanced techniques could be used which incorporate a longer reconnect sleep time based
        // on the number of failures or the type of exception.

        // If we have ever connected then we will retry the connection, providing the consectutive failed connection is less then
        // 10 times.
        if( _rateService.getSuccessfullCounnectionCount() > 0 && _rateService.getFailedConsecutiveCounnectionCount() < 10 )
            restart();
    }

    /**
     * OnRateServiceDisconnected() - Called when the Rate service has been disconnected from the Rate Server after a client disconnect request
     */
     public void OnRateServiceDisconnected()
    {
        // Notification when a successfull disconnection has completed, this can be
        // used to set an internal flag that the service is disconnected or notifty clients of the
        // service that the service no longer connected
        System.out.println( "RateServiceExample: disconnected successfully");
    }

    /**
     * OnRateServiceRate( Rate rate ) - Called when a rate is updated
     * @param rate The rate which is updating
     */
    public void OnRateServiceRate( Rate rate )
    {
        // Notification of a Rate update, the rate object contains all the data regarding the updated rate
        // The object is a lightweight Java Bean object which simply contains the Rate Data.  Ideal for storing in an
        // interal collection (recommend hashtable hashed on currency pair).

        // Create a string buffer to store the rate message, preinitialse with approx size for performance
        StringBuffer msg = new StringBuffer( 128 );

        msg.append( "OnRate: Currency Pair: " );
        msg.append( rate.getCurrencyPair() );
        msg.append( " Bid: " );
        msg.append( rate.getBid() );
        msg.append( " Ask: " );
        msg.append( rate.getAsk() );
        msg.append( " High: " );
        msg.append( rate.getHigh() );
        msg.append( " Low: " );
        msg.append( rate.getLow() );
        msg.append( " Dealable: " );
        msg.append( rate.getDealable() );
        msg.append( " Domian: " );
        msg.append( rate.getDomain() );
        msg.append( " Decimal Places: " );
        msg.append( rate.getDecimalPlaces() );

        System.out.println( "OnRate: " + msg.toString() );

        // NOTE: rate.toString() would have provided the same output, repeated code
        // here to so example of rate object class.
    }

    /**
     * RateServiceExample main routine
     *
     */
    public static void main(String[] args)
    {
        try
        {
            // Print opening banner
            System.out.println( "RateServiceExample using RateService version: " + RateService.getVersion() );

            // Check we have enough paramters
            if( args.length < 4 )
            {
                System.out.println( "Usage: RateServiceExample host port key executeSeconds" );
                return;
            }

            // Create a rates service with host, port key
            RateServiceExample example = new RateServiceExample( args[0], Integer.parseInt(args[1]), args[2] );

            // start the example service
            example.start();

            // Sleep for the execute seconds stated by the parameter
            try{ Thread.sleep( Integer.parseInt( args[3] ) * 1000 ); } catch( Exception e ){}

            // stop the example service
            example.stop();

            // Sleep for the 5 seconds to allow the service to disconnect cleanly
            try{ Thread.sleep( 5000 ); } catch( Exception e ){}

            // Print closing banenr
            System.out.println( "RateServiceExample terminating ");


        }
        catch( Exception e )
        {
            System.out.println( "RateServiceExample, Exception: " + e.getClass().getName() + " : " + e.getMessage() );
        }
    }
}