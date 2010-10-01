'
' Rate.vb
'
' Gain Capital
'
' Phil Cave
'
' April 2003
'
'

'''<summary>
'''The Gain.RateService namespace provides the classes to connect clients to the Gain Rate Servers
'''</summary>
Namespace Gain.RateService

    '''<summary>
    '''Rate class hold all the data information associated with rates
    '''</summary>
    Public Class Rate

        '''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
        ''' Private Variables
        '''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''

        Private _Key As String
        Private _CurrencyPair As String
        Private _Ask As Decimal
        Private _Bid As Decimal
        Private _High As Decimal
        Private _Low As Decimal
        Private _DecimalPlaces As Integer
        Private _Dealable As String
        Private _Domain As String

        '''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
        ''' Public API
        '''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''


        '''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
        ''' Properties
        '''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''

        '''<summary>
        '''The unique key associated with the currency pair
        '''</summary>
        Public Property Key() As String
            Get
                Return _Key
            End Get
            Set(ByVal Value As String)
                _Key = Value
            End Set
        End Property

        '''<summary>
        '''The currency pair of the rate
        '''</summary>
        Public Property CurrencyPair() As String
            Get
                Return _CurrencyPair
            End Get
            Set(ByVal Value As String)
                _CurrencyPair = Value
            End Set
        End Property

        '''<summary>
        '''The ask value of the rate
        '''</summary>
        Public Property Ask() As Decimal
            Get
                Return _Ask
            End Get
            Set(ByVal Value As Decimal)
                _Ask = Value
            End Set
        End Property

        '''<summary>
        '''The bid value of the rate
        '''</summary>
        Public Property Bid() As Decimal
            Get
                Return _Bid
            End Get
            Set(ByVal Value As Decimal)
                _Bid = Value
            End Set
        End Property

        '''<summary>
        '''The high value of the rate
        '''</summary>
        Public Property High() As Decimal
            Get
                Return _High
            End Get
            Set(ByVal Value As Decimal)
                _High = Value
            End Set
        End Property

        '''<summary>
        '''The low value of the rate
        '''</summary>
        Public Property Low() As Decimal
            Get
                Return _Low
            End Get
            Set(ByVal Value As Decimal)
                _Low = Value
            End Set
        End Property

        '''<summary>
        '''The decimal places of the rate
        '''</summary>
        Public Property DecimalPlaces() As Integer
            Get
                Return _DecimalPlaces
            End Get
            Set(ByVal Value As Integer)
                _DecimalPlaces = Value
            End Set
        End Property

        '''<summary>
        '''The dealable status of the rate. D is dealable, R is Refered
        '''</summary>
        Public Property Dealable() As String
            Get
                Return _Dealable
            End Get
            Set(ByVal Value As String)
                _Dealable = Value
            End Set
        End Property

        '''<summary>
        '''The domain of the rate. A is America, E is Europe
        '''</summary>
        Public Property Domain() As String
            Get
                Return _Domain
            End Get
            Set(ByVal Value As String)
                _Domain = Value
            End Set
        End Property

        '''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
        ''' Methods
        '''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''

        '''<summary>Creates a string representation of the rate</summary>
        '''<returns>String representation of the current Rate</returns>
        Public Overrides Function ToString() As String

            ' Create a String Builder to build a string representation in a performant manner.
            Dim rateStr As New System.Text.StringBuilder(130)

            ' Append the fields descriptions and data
            rateStr.Append("Key: ")
            rateStr.Append(Key)
            rateStr.Append(" Currency Pair: ")
            rateStr.Append(CurrencyPair)
            rateStr.Append(" Bid: ")
            rateStr.Append(Bid)
            rateStr.Append(" Ask: ")
            rateStr.Append(Ask)
            rateStr.Append(" High: ")
            rateStr.Append(High)
            rateStr.Append(" Low: ")
            rateStr.Append(Low)
            rateStr.Append(" Dealable: ")
            rateStr.Append(Dealable)
            rateStr.Append(" Domain: ")
            rateStr.Append(Domain)
            rateStr.Append(" Decimal Places: ")
            rateStr.Append(DecimalPlaces)

            ' Return the string representation of the rate
            ToString = rateStr.ToString()
        End Function

    End Class



End Namespace
