'
' RateService.vb
'
' Gain Capital
'
' Phil Cave
'
' April 2003
'
'

''' Imports
Imports System.Threading
Imports System.Net
Imports System.Net.Sockets
Imports System.Text

'''<summary>
'''The Gain.RateService namespace provides the classes to connect clients to the Gain Rate Servers
'''</summary>
Namespace Gain.RateService

    '''<summary>
    '''RateService class is the primary interface for connecting to the Rates Server
    '''</summary>
    Public Class RateService

        '''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
        ''' Private Variables
        '''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''

        ' Constants
        Private Const SOCKET_TIMEOUT_MS As Integer = 30000

        ' Members
        Private _IsConnected As Boolean
        Private _Disconnect As Boolean
        Private _Host As String
        Private _Port As Integer
        Private _Key As String
        Private _FailedCount As Integer
        Private _FailedConsecutiveCount As Integer
        Private _SuccessCount As Integer
        Private _Rates As Hashtable

        '''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
        ''' Public API
        '''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''


        '''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
        ''' Events
        '''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''

        '''<summary>
        '''Raised when connected and authenticated with the Rate Service
        '''</summary>
        Public Event OnRateServiceConnected()

        '''<summary>
        '''Raised when disconnected from the Rate Service after a disconnect request
        '''</summary>
        Public Event OnRateServiceDisconnected()

        '''Raised when the connection request to Rate Service has failed
        Public Event OnRateServiceConnectionFailed(ByVal ex As Exception)

        '''<summary>
        '''Raised when the connection request to Rate Service has failed
        '''</summary>
        Public Event OnRateServiceConnectionLost(ByVal ex As Exception)

        '''<summary>
        '''Raised when a Rate is updated
        '''</summary>
        Public Event OnRateServiceRate(ByVal rate As Rate)

        '''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
        ''' Properties
        '''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''

        '''<summary>
        '''The version of the rates service library
        '''</summary>
        Public Shared ReadOnly Property Version() As String
            Get
                Return "1.0.0"
            End Get
        End Property

        '''<summary>
        '''The number of successfull connections to the Rate Service
        '''</summary>
        Public ReadOnly Property SuccessfullConnectionCount() As Integer
            Get
                Return _SuccessCount
            End Get
        End Property

        '''<summary>
        '''The number of failed connections to the Rate Service
        '''</summary>
        Public ReadOnly Property FailedConnectionCount() As Integer
            Get
                Return _FailedCount
            End Get
        End Property

        '''<summary>
        '''The number of consecutive failed connections to the Rate Service
        '''</summary>
        Public ReadOnly Property FailedConsecutiveConnectionCount() As Integer
            Get
                Return _FailedConsecutiveCount
            End Get
        End Property

        '''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
        ''' Methods
        '''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''

        '''<summary>Constructor initializes the members variables</summary>
        Public Sub New()
            ' initialize the counters to zero
            _FailedCount = 0
            _FailedConsecutiveCount = 0
            _SuccessCount = 0
        End Sub

        '''<summary>Connects to the Rate Service with the specified parameters</summary>
        '''<param name="Host">Rate Service Host to connect</param>
        '''<param name="Port">Rate Service Port to connect</param>
        '''<param name="Key">Key to authenticate with the Rate Service</param>
        '''<returns>True if connection attempted or false if not attempted, i.e already connection</returns>
        Public Function Connect(ByVal Host As String, ByVal Port As Integer, ByVal Key As String) As Boolean

            ' Check we not already connected
            If (IsConnected()) Then
                Console.Error.WriteLine("RateService:Connect - Already connected")
                Connect = False
                Exit Function
            End If

            ' Store the parameters are we need them in the process thread
            _Host = Host
            _Port = Port
            _Key = Key

            ' Create a thread and start the feed process
            Try
                ' Create a Thread object which used the ProcessRatesFeed as its ThreadStart
                Dim thread As New Thread(New ThreadStart(AddressOf Me.ProcessRatesFeed))

                ' Start the Thread
                thread.Start()

            Catch ex As Exception
                ' We have an exception, log the problem to the console
                Console.Error.WriteLine("RateService:Connect - Exception starting rate service thread: " & ex.ToString)

                ' Set a failed return and exit
                Connect = False
                Exit Function
            End Try

            ' if we reach here we have been successfull and successfully started the connection process
            Connect = True

        End Function


        '''<summary>Disconnects from the Rate Service</summary>
        '''<returns>True if disconnection attempted or false if not attempted, i.e not connection</returns>
        Public Function Disconnect() As Boolean

            ' Check we are connected
            If (IsConnected() = False) Then
                Console.Error.WriteLine("RateService:Disconnect - Not connected")
                Disconnect = False
                Exit Function
            End If

            ' Set the disconnect flag, this will cuase the rate thread to stop
            _Disconnect = True

            ' if we reach here we have been successfull started the disconnect process
            Disconnect = True
        End Function

        '''<summary>Checks if the RateService object is connected</summary>
        '''<returns>True if connected or false if not connected</returns>
        Public Function IsConnected() As Boolean
            IsConnected = _IsConnected
        End Function

        '''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
        ''' Private Methods
        '''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''

        ' This routine runs in a separate thread and processes the rates data feed.
        ' The routine connects to the Rate Servers, reads/parses the messages 
        ' and distributes the rate information via events
        Private Sub ProcessRatesFeed()

            ' Log the process has started
            Console.Out.WriteLine("RateService:ProcessRatesFeed started in thread")

            ' Locals
            Dim socket As Socket
            Dim MessagesRead As Integer = 0
            Dim LastException As Exception

            Try
                ' Create a new host end point and a socket connection with appropriate socket options
                Dim hostEndPoint As New IPEndPoint(Dns.Resolve(_Host).AddressList(0), _Port)
                socket = New Socket(AddressFamily.InterNetwork, SocketType.Stream, ProtocolType.Tcp)
                socket.Blocking = True
                socket.SetSocketOption(SocketOptionLevel.Socket, SocketOptionName.ReceiveTimeout, SOCKET_TIMEOUT_MS)

                ' Connect the socket
                socket.Connect(hostEndPoint)
                If (socket.Connected = False) Then
                    Throw New Exception("Socket failed to connected to " & hostEndPoint.ToString())
                End If

                Console.Out.WriteLine("RateService:ProcessRatesFeed Sockect Connected")

                ' Send the Authentication Key down the socket stream
                Dim AuthMessage As String
                AuthMessage = _Key & "\COMPACT"
                socket.Send(Encoding.ASCII.GetBytes(AuthMessage))

                Console.Out.WriteLine("RateService:ProcessRatesFeed Sockect Authenication Sent")

                ' While we are not disconnect read and process the messages
                While _Disconnect = False

                    Dim Message As String

                    Message = ReadMessage(socket)

                    If (Message <> "") Then

                        MessagesRead += 1

                        ' if this is the first message then we are connected ok
                        If (MessagesRead = 1) Then
                            _IsConnected = True
                            _SuccessCount += 1
                            _FailedConsecutiveCount = 0
                            RaiseEvent OnRateServiceConnected()
                        End If

                        ' process the message 
                        ProcessMessage(Message)

                    End If
                End While

            Catch ex As Exception
                ' We have an exception, log the problem to the console
                Console.Error.WriteLine("RateService:ProcessRateFeed - Exception processing messages : " & ex.ToString)

                ' Store the exception for distrubuting later
                LastException = ex

            Finally
                ' No longer Connected 
                _IsConnected = False

                ' Clean up any resourses
                If (Not IsNothing(socket)) Then

                    If (socket.Connected) Then
                        socket.Shutdown(SocketShutdown.Both)
                        socket.Close()
                    End If
                End If

            End Try

            ' if we failed due to an expection, then deal with it
            If Not IsNothing(LastException) Then

                ' increment the failed counts
                _FailedCount += 1
                _FailedConsecutiveCount += 1

                ' if we had an exception, but had read at least one message then we
                ' have had a lost connection
                If (MessagesRead > 0) Then
                    RaiseEvent OnRateServiceConnectionLost(LastException)
                Else
                    ' else the connection has failed
                    RaiseEvent OnRateServiceConnectionFailed(LastException)

                End If
            Else
                ' We disconnect gracefully, raise disconnect event
                RaiseEvent OnRateServiceDisconnected()
            End If

            ' Log the process has finished
            Console.Out.WriteLine("RateService:ProcessRatesFeed completed")

        End Sub


        ' Process the Rate Message
        Private Sub ProcessMessage(ByVal Message As String)

            ' Process the appropriate message type
            If (Message.StartsWith("S")) Then
                ProcessSMessage(Message.Substring(1))
            ElseIf (Message.StartsWith("R")) Then
                ProcessRMessage(Message.Substring(1))
            Else
                Console.Out.WriteLine("RateService:ProcessMessage Unknown message: " & Message)
            End If

        End Sub

        ' Process the S Rate Message, which passes the initial Rates information
        ' in full for all rates.
        Private Sub ProcessSMessage(ByVal Message As String)

            ' SUR Message refresh all the rates, so create a new Rates cache
            _Rates = New Hashtable(20)

            ' Split the SUR messages into individual Rate Messages
            Dim RateMessages() As String = Split(Message, "$")
            Dim RateMessage As String
            For Each RateMessage In RateMessages

                Try
                    ' Split the Rates Message into its fields components
                    Dim RateFields() As String = Split(RateMessage, "\")

                    ' Make sure we have enough fields
                    If (RateFields.Length >= 9) Then

                        ' Create a new rate and assign it the appropriate values
                        ' from the rate fields
                        Dim rate As New Rate()
                        rate.Key = RateFields(0)
                        rate.CurrencyPair = RateFields(1)
                        rate.Bid = Convert.ToDecimal(RateFields(2))
                        rate.Ask = Convert.ToDecimal(RateFields(3))
                        rate.High = Convert.ToDecimal(RateFields(4))
                        rate.Low = Convert.ToDecimal(RateFields(5))
                        rate.Dealable = RateFields(6)
                        rate.Domain = RateFields(7)
                        rate.DecimalPlaces = Convert.ToInt32(RateFields(8))

                        ' add the rate to the hash table by its key
                        _Rates.Add(rate.Key, rate)

                        ' Raise the Rate Update Event
                        RaiseEvent OnRateServiceRate(rate)

                    End If

                Catch ex As Exception
                    ' Log the exception but continue with the remaining messages
                    Console.Out.WriteLine("RateService:ProcessSMessage Exception: " & ex.ToString())
                End Try
            Next

        End Sub

        ' Process the R Rate Message, which passes the a single rate update
        ' in full.
        Private Sub ProcessRMessage(ByVal Message As String)

            Try
                ' Split the Rates Message into its fields components
                Dim RateFields() As String = Split(Message, "\")

                ' Make sure we have enough fields
                If (RateFields.Length >= 4) Then

                    ' Get the rate from the rate cache based on the field key at parameter 0
                    Dim rate As Rate
                    rate = _Rates.Item(RateFields(0))

                    ' if we have the rate update then process it
                    If (Not (IsNothing(rate))) Then

                        ' Extract the updateable fields
                        rate.Bid = Convert.ToDecimal(RateFields(1))
                        rate.Ask = Convert.ToDecimal(RateFields(2))
                        rate.Dealable = RateFields(3)

                        ' Work out if high and low have change
                        If (rate.Bid < rate.Low) Then
                            rate.Low = rate.Bid
                        End If

                        If (rate.Ask > rate.High) Then
                            rate.High = rate.Ask
                        End If

                        ' Raise the Rate Update Event
                        RaiseEvent OnRateServiceRate(rate)

                    Else
                        Console.Out.WriteLine("RateService:ProcessCMessage Failed to find currency pair with key: " & RateFields(0))
                    End If
                End If

            Catch ex As Exception
                ' Log the exception but continue with the remaining messages
                Console.Out.WriteLine("RateService:ProcessCMessage Exception: " & ex.ToString())
            End Try

        End Sub


        ' Reads a message from the Rate Server, read a byte at a time
        ' checking for terminated stream and end of message characters
        Private Function ReadMessage(ByRef socket As Socket) As String

            ' Locals
            Dim Done As Boolean = False
            Dim Message As New StringBuilder(10)
            Dim Buffer(0) As Byte

            ' Read all the bytes in the message
            While Done = False

                socket.Receive(Buffer)

                ' Check we haven't received a -1 byte, which is unexpected end of stream
                If (Buffer(0) = -1) Then
                    Throw New Exception("Unexpected EOF during ReadMessage")
                End If

                ' if the message is \r, then its the end of message marker so we are done
                If (Buffer(0) = 13) Then
                    Done = True
                Else
                    ' else add the character to our message
                    Message.Append(Convert.ToChar(Buffer(0)))
                End If
            End While

            ' Return the string representation of the message
            ReadMessage = Message.ToString()

        End Function


    End Class


End Namespace