//
// RateServiceExample.vb
//
// Gain Capital
//
// Phil Cave
//
// April 2003
//
//

// Namespaces Import
using System;

// Use the Threading Namespace
using System.Threading;

// Use the Text Namespace
using System.Text;

// Use the Rates Service API 
using RateServiceAPI.Gain.RateService;

namespace RateServiceExampleCSharp
{
	/// <summary>
	///  Rate Service Example provides an example of how to use the RateService Library objects
	//   from C#.NET
	/// </summary>
	class RateServiceExample
	{
		
		// Private members
		private String       _host;
		private int          _port;
		private String       _key;
		private RateService  _rateService;

		/// <summary>
		/// RateServiceExample constructor, create the example class and initializes varibales
		/// </summary>
		/// <param name="host">The Rate Service Host to connect to</param>
		/// <param name="port">The Rate Service Port to connect to</param>
		/// <param name="key">The Key to use for authentication</param>
		RateServiceExample( String host, int port, String key )
		{
			// Store parameters in members
			_host = host;
			_port = port;
			_key  = key;

			 // Create a new Rate Service Object, this object should last the lifetime of the class
			 // as the object maintains connection count information which is initialised to 0 
             // on construction only.  We create the object once so we can use this information when deciding
			 // our reconnect policy
			_rateService = new RateService();

			// Hook up the event handler to our callback methods
			_rateService.OnRateServiceConnected += new RateService.OnRateServiceConnectedEventHandler(this.OnRateServiceConnected );
			_rateService.OnRateServiceDisconnected += new RateService.OnRateServiceDisconnectedEventHandler(this.OnRateServiceDisconnected );
			_rateService.OnRateServiceConnectionFailed += new RateService.OnRateServiceConnectionFailedEventHandler(this.OnRateServiceConnectionFailed );
			_rateService.OnRateServiceConnectionLost += new RateService.OnRateServiceConnectionLostEventHandler(this.OnRateServiceConnectionLost );
			_rateService.OnRateServiceRate += new RateService.OnRateServiceRateEventHandler(this.OnRateServiceRate );

		}

		/// <summary>
		/// Runs the RateService Example which connects to the Rates Service for the specified number of seconds
		/// and terminates
		/// </summary>
		/// <param name="executeSeconds">The number of seconds to execute the Rate Service connection for</param>
		public void Run( int executeSeconds )
		{
			// Make the connection request to the Rates Service, this call creates the rates thread
			// which makes the connection to the Rates Service
			if (_rateService.Connect(_host, _port, _key) == true)
			{
				// We are not connected and authenticated until we get the "OnRateServiceConnected()"
				// event.  If we do not get this event we will get the "OnRateServiceFailed()" event.

				
				// Sleep the requested execute seconds
				Thread.Sleep(executeSeconds * 1000);

				// Finished so disconnect from the rates service
				_rateService.Disconnect();

				// Sleep a few seconds so we get the disconnect confirmation
				Thread.Sleep(3000);

			}
			else
				Console.Out.WriteLine("RateServiceExample: Run Failed to connect to RateService");
				
		}

		/// <summary>
		/// Reconnects the Rates Service
		/// </summary>
		public void Reconnect()
		{
			Console.Out.WriteLine("RateServiceExample: Reconnecting......");

			// If we are already connected, then disconnect
			if(_rateService.IsConnected() == true )
				_rateService.Disconnect();
        
			// Sleep a few seconds so we do not hit the Rates Service with constant reconnecting
			Thread.Sleep(5000);

			// Reconnect to the Rates Service with the same parameters
			_rateService.Connect(_host, _port, _key);
		}


		////////////////////////////////////////////////////////////////////////////////////////////
		/// RateService Callback Methods 
		////////////////////////////////////////////////////////////////////////////////////////////

		/// <summary>
		/// Called when the rate service is connected
		/// </summary>
		public void OnRateServiceConnected()
		{
			Console.Out.WriteLine("RateServiceExampleCSharp: OnRateServiceConnected" );
		}

		/// <summary>
		/// Called when the rate service is disconnected
		/// </summary>
		public void OnRateServiceDisconnected()
		{
			Console.Out.WriteLine("RateServiceExampleCSharp: OnRateServiceDisonnected" );
		}

		/// <summary>
		/// Called when the rate service is connection is Failed
		/// </summary>
		public void OnRateServiceConnectionFailed( Exception e)
		{
			Console.Out.WriteLine("RateServiceExampleCSharp: OnRateServiceConnectionFailed: " + e.ToString() );

			// Clients may reconnect to the RateService but should employ a stratgey for limiting reconnecting attempts
			// when network outage or problems occur.

			// The follow stragey checks we have had at least one successfull connection, implying connection
			// parameters are correct. And limits the reconnecting attempts to 10 consecutive failures
			if(_rateService.SuccessfullConnectionCount > 0 && _rateService.FailedConnectionCount <= 10)
				Reconnect();
       	}

		/// <summary>
		/// Called when the rate service is connection is lost
		/// </summary>
		public void OnRateServiceConnectionLost( Exception e)
		{
			Console.Out.WriteLine("RateServiceExampleCSharp: OnRateServiceConnectionLost: " + e.ToString() );

			// Clients may reconnect to the RateService but should employ a stratgey for limiting reconnecting attempts
			// when network outage or problems occur.

			// The follow stragey checks we have had at least one successfull connection, implying connection
			// parameters are correct. And limits the reconnecting attempts to 10 consecutive failures
			if(_rateService.SuccessfullConnectionCount > 0 && _rateService.FailedConnectionCount <= 10)
				Reconnect();
		}

		/// <summary>
		/// Called when the rate service updates a rate
		/// </summary>
		public void OnRateServiceRate( Rate rate )
		{
			// Create a string representation of the Rate and Display it
			StringBuilder rateStr =  new StringBuilder(130);

			// Append the fields descriptions and data
			rateStr.Append("Key: ");
			rateStr.Append(rate.Key);
			rateStr.Append(" Currency Pair: ");
			rateStr.Append(rate.CurrencyPair);
			rateStr.Append(" Bid: ");
			rateStr.Append(rate.Bid);
			rateStr.Append(" Ask: ");
			rateStr.Append(rate.Ask);
			rateStr.Append(" High: ");
			rateStr.Append(rate.High);
			rateStr.Append(" Low: ");
			rateStr.Append(rate.Low);
			rateStr.Append(" Dealable: ");
			rateStr.Append(rate.Dealable);
			rateStr.Append(" Domain: ");
			rateStr.Append(rate.Domain);
			rateStr.Append(" Decimal Places: ");
			rateStr.Append(rate.DecimalPlaces);

			Console.Out.WriteLine("RateServiceExampleCSharp: OnRateServiceRate() called with Rate: " + rateStr.ToString());

			// NOTE: rate.ToString() provides the same functionality above, repeated in the example
			// to show all properties in the Rate class
		}


		
		
		/// <summary>
		/// The main entry point for the application.  
		/// </summary>
		[STAThread]
		static void Main(string[] args)
		{
			
			// Display a title banner, including the rate service verison
			Console.Out.WriteLine("RateServiceExampleCSharp using RateService API version: " + RateService.Version);

			// Check we have enough arguments
			if(args.Length < 4)
			{
				Console.Out.WriteLine("Usage RatesServiceExampleCSharp host port key executeSeconds");
				return;
			}

			try
			{
        
				// Convert the arguments to the correct types
				string host = args[0];
				int    port = Convert.ToInt32(args[1]);
				string key  = args[2];
				int    executeSeconds = Convert.ToInt32(args[3]);

				// Create the new application
				RateServiceExample example = new RateServiceExample( host, port, key );

				// Run the application for the desired amount of seconds
				example.Run( executeSeconds );

			}
			catch( Exception )
			{
				Console.Out.WriteLine("Invalid Parameters: Usage RatesServiceExampleCSharp host port key executeSeconds");
				return;
			}

			// Display a ending banner
			Console.Out.WriteLine("RateServiceExampleCSharp Complete");

			return;

		}
		
	}
}
