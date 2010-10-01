'
' RateServiceExampleModule.vb
'
' Gain Capital
'
' Phil Cave
'
' April 2003
'
'


' Import the System classes
Imports System.Threading

' Import the Rates Service API 
Imports RateServiceAPI.Gain.RateService


' Rate Service Example Module provides an example of how to use the RateService Library objects
' from VS.NET
Module RateServiceExampleModule

    ' Create a new Rate Service Object, this object should last the lifetime of the module/class
    ' as the object maintains connection count information which is initialised to 0 
    ' on construction only.  We create the object once so we can use this information when deciding
    ' our reconnect policy
    Dim WithEvents MyRateService As New RateService()

    ' Keep a copy of the connection parameters so we can reconnect if we lose the connection
    Dim Host As String
    Dim Port As Integer
    Dim Key As String


    ' Main Entry Point Subroutine, reads executes and starts the Rates Service with apporpriate parameters
    Sub Main(ByVal CmdArgs() As String)

        ' Display a title banner, including the rate service verison
        Console.Out.WriteLine("RateServiceExampleVB using RateService API version: " & RateService.Version)

        ' Check we have enough arguments
        If (CmdArgs.Length < 4) Then
            Console.Out.WriteLine("Usage RatesServiceExampleVB host port key executeSeconds")
            Exit Sub
        End If

        ' Convert the arguments to the correct types
        Dim executeSeconds As Integer
        Try
            Host = CmdArgs(0)
            Port = Convert.ToInt32(CmdArgs(1))
            Key = CmdArgs(2)
            executeSeconds = Convert.ToInt32(CmdArgs(3))
        Catch
            Console.Out.WriteLine("Invalid Parameters: Usage RatesServiceExampleVB host port key executeSeconds")
            Exit Sub
        End Try


        ' Make the connection request to the Rates Service, this call creates the rates thread
        ' which makes the connection to the Rates Service
        If (MyRateService.Connect(Host, Port, Key) = True) Then

            ' We are not connected and authenticated until we get the "OnRateServiceConnected()"
            ' event.  If we do not get this event we will get the "OnRateServiceFailed()" event.

            ' Sleep the requested execute seconds
            Thread.CurrentThread.Sleep(executeSeconds * 1000)

            ' Finished so disconnect from the rates service
            MyRateService.Disconnect()

            ' Sleep a few seconds so we get the disconnect confirmation
            Thread.CurrentThread.Sleep(3000)

        End If

        Console.Out.WriteLine("RateServiceExampleVB completed")

        ' Perform a hard reset here, because RateService Thread might be in a blocking call
        ' and not terminate.  .NET allows childs threads to continue even when Main/Parent thread
        ' terminates
        End

    End Sub

    ' This rountine reconnects the existing rates service if request 
    Private Sub Reconnect()

        Console.Out.WriteLine("RateServiceExampleVB Reconnecting...")

        ' If we are already connected, then disconnect
        If (MyRateService.IsConnected()) Then
            MyRateService.Disconnect()
        End If

        ' Sleep a few seconds so we do not hit the Rates Service with constant reconnecting
        Thread.CurrentThread.Sleep(5000)

        ' Reconnect to the Rates Service with the same parameters
        MyRateService.Connect(Host, Port, Key)

    End Sub

    ''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
    ' Rate Service Events
    ' These events are raised by the RateService class.  The rates service operates in 
    ' its own thread. The client is reponsible for marshalling data/call to the main thread
    ' if this is required by the client
    ''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''

    ' Event raised when the Rate Service has connected and authenticated
    Public Sub MyRateService_OnRateServiceConnected() Handles MyRateService.OnRateServiceConnected
        Console.Out.WriteLine("RateServiceExampleVB: OnRateServiceConnected() called")
    End Sub

    ' Event raised when the Rate Service has disconnected after a disconnect request
    Public Sub MyRateService_OnRateServiceDisconnected() Handles MyRateService.OnRateServiceDisconnected
        Console.Out.WriteLine("RateServiceExampleVB: OnRateServiceDisconnected() called")
    End Sub

    ' Event raised when the Rate Service has failed to connect, exception contains cause of failure
    Public Sub MyRateService_OnRateServiceConnectionFailed(ByVal ex As System.Exception) Handles MyRateService.OnRateServiceConnectionFailed

        Console.Out.WriteLine("RateServiceExampleVB: OnRateServiceConnectionFailed() called with Exception: " & ex.ToString())

        ' Clients may reconnect to the RateService but should employ a stratgey for limiting reconnecting attempts
        ' when network outage or problems occur.

        ' The follow stragey checks we have had at least one successfull connection, implying connection
        ' parameters are correct. And limits the reconnecting attempts to 10 consecutive failures
        If (MyRateService.SuccessfullConnectionCount > 0 And MyRateService.FailedConnectionCount <= 10) Then
            Reconnect()
        Else
            ' if we are not reconnecting then we will just terminate, as the main thread is sleeping
            Console.Out.WriteLine("RateServiceExampleVB: OnRateServiceConnectionFailed() - Terminating program as can not reconnect")
            End
        End If
    End Sub

    ' Event raised when the Rate Service has lost the connection, exception contains cause of failure
    Public Sub MyRateService_OnRateServiceConnectionLost(ByVal ex As System.Exception) Handles MyRateService.OnRateServiceConnectionLost

        Console.Out.WriteLine("RateServiceExampleVB: OnRateServiceConnectionLost() called with Exception: " & ex.ToString())

        ' Clients may reconnect to the RateService but should employ a stratgey for limiting reconnecting attempts
        ' when network outage or problems occur.

        ' The follow stragey checks we have had at least one successfull connection, implying connection
        ' parameters are correct. And limits the reconnecting attempts to 10 consecutive failures
        If (MyRateService.SuccessfullConnectionCount > 0 And MyRateService.FailedConnectionCount <= 10) Then
            Reconnect()
        Else
            ' if we are not reconnecting then we will just terminate, as the main thread is sleeping
            Console.Out.WriteLine("RateServiceExampleVB: OnRateServiceConnectionLost() - Terminating program as can not reconnect")
            End
        End If

    End Sub

    ' Event raised when a Rate is update from the Rate Service
    Public Sub MyRateService_OnRateServiceRate(ByVal rate As RateServiceAPI.Gain.RateService.Rate) Handles MyRateService.OnRateServiceRate

        ' Create a string representation of the Rate and Display it
        Dim rateStr As New System.Text.StringBuilder(130)

        ' Append the fields descriptions and data
        rateStr.Append("Key: ")
        rateStr.Append(rate.Key)
        rateStr.Append(" Currency Pair: ")
        rateStr.Append(rate.CurrencyPair)
        rateStr.Append(" Bid: ")
        rateStr.Append(rate.Bid)
        rateStr.Append(" Ask: ")
        rateStr.Append(rate.Ask)
        rateStr.Append(" High: ")
        rateStr.Append(rate.High)
        rateStr.Append(" Low: ")
        rateStr.Append(rate.Low)
        rateStr.Append(" Dealable: ")
        rateStr.Append(rate.Dealable)
        rateStr.Append(" Domain: ")
        rateStr.Append(rate.Domain)
        rateStr.Append(" Decimal Places: ")
        rateStr.Append(rate.DecimalPlaces)

        Console.Out.WriteLine("RateServiceExampleVB: OnRateServiceRate() called with Rate: " & rateStr.ToString())

        ' NOTE: rate.ToString() provides the same functionality above, repeated in the example
        ' to show all properties in the Rate class
    End Sub

End Module
