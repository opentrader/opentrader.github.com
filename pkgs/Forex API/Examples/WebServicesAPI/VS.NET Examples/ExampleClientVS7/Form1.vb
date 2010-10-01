Public Class Form1
    Inherits System.Windows.Forms.Form

#Region " Windows Form Designer generated code "

    Public Sub New()
        MyBase.New()

        'This call is required by the Windows Form Designer.
        InitializeComponent()

        'Add any initialization after the InitializeComponent() call

    End Sub

    'Form overrides dispose to clean up the component list.
    Protected Overloads Overrides Sub Dispose(ByVal disposing As Boolean)
        If disposing Then
            If Not (components Is Nothing) Then
                components.Dispose()
            End If
        End If
        MyBase.Dispose(disposing)
    End Sub

    'Required by the Windows Form Designer
    Private components As System.ComponentModel.IContainer

    'NOTE: The following procedure is required by the Windows Form Designer
    'It can be modified using the Windows Form Designer.  
    'Do not modify it using the code editor.
    Friend WithEvents txtResponse As System.Windows.Forms.TextBox
    Friend WithEvents txtAmount As System.Windows.Forms.TextBox
    Friend WithEvents ComboBoxCCY As System.Windows.Forms.ComboBox
    Friend WithEvents DataGridRates As System.Windows.Forms.DataGrid
    Friend WithEvents Label2 As System.Windows.Forms.Label
    Friend WithEvents Label3 As System.Windows.Forms.Label
    Friend WithEvents Label4 As System.Windows.Forms.Label
    Friend WithEvents Label5 As System.Windows.Forms.Label
    Friend WithEvents txtRate As System.Windows.Forms.TextBox
    Friend WithEvents Label6 As System.Windows.Forms.Label
    Friend WithEvents ComboBoxBuySell As System.Windows.Forms.ComboBox
    Friend WithEvents Label1 As System.Windows.Forms.Label
    Friend WithEvents btnDeal As System.Windows.Forms.Button
    Friend WithEvents lblStatus As System.Windows.Forms.Label
    <System.Diagnostics.DebuggerStepThrough()> Private Sub InitializeComponent()
        Me.txtResponse = New System.Windows.Forms.TextBox
        Me.txtAmount = New System.Windows.Forms.TextBox
        Me.ComboBoxCCY = New System.Windows.Forms.ComboBox
        Me.DataGridRates = New System.Windows.Forms.DataGrid
        Me.btnDeal = New System.Windows.Forms.Button
        Me.Label2 = New System.Windows.Forms.Label
        Me.Label3 = New System.Windows.Forms.Label
        Me.Label4 = New System.Windows.Forms.Label
        Me.Label5 = New System.Windows.Forms.Label
        Me.txtRate = New System.Windows.Forms.TextBox
        Me.Label6 = New System.Windows.Forms.Label
        Me.ComboBoxBuySell = New System.Windows.Forms.ComboBox
        Me.Label1 = New System.Windows.Forms.Label
        Me.lblStatus = New System.Windows.Forms.Label
        CType(Me.DataGridRates, System.ComponentModel.ISupportInitialize).BeginInit()
        Me.SuspendLayout()
        '
        'txtResponse
        '
        Me.txtResponse.Location = New System.Drawing.Point(432, 48)
        Me.txtResponse.Multiline = True
        Me.txtResponse.Name = "txtResponse"
        Me.txtResponse.ReadOnly = True
        Me.txtResponse.Size = New System.Drawing.Size(208, 104)
        Me.txtResponse.TabIndex = 19
        Me.txtResponse.Text = ""
        '
        'txtAmount
        '
        Me.txtAmount.Location = New System.Drawing.Point(520, 216)
        Me.txtAmount.Name = "txtAmount"
        Me.txtAmount.Size = New System.Drawing.Size(120, 20)
        Me.txtAmount.TabIndex = 11
        Me.txtAmount.Text = "100000"
        '
        'ComboBoxCCY
        '
        Me.ComboBoxCCY.Items.AddRange(New Object() {"AUD/JPY", "AUD/USD", "CHF/JPY", "EUR/AUD", "EUR/CHF", "EUR/GBP", "EUR/JPY", "EUR/USD", "GBP/CHF", "GBP/JPY", "GBP/USD", "USD/CAD", "USD/CHF", "USD/JPY"})
        Me.ComboBoxCCY.Location = New System.Drawing.Point(520, 168)
        Me.ComboBoxCCY.Name = "ComboBoxCCY"
        Me.ComboBoxCCY.Size = New System.Drawing.Size(121, 21)
        Me.ComboBoxCCY.TabIndex = 9
        Me.ComboBoxCCY.Text = "{Select}"
        '
        'DataGridRates
        '
        Me.DataGridRates.DataMember = ""
        Me.DataGridRates.HeaderForeColor = System.Drawing.SystemColors.ControlText
        Me.DataGridRates.Location = New System.Drawing.Point(8, 24)
        Me.DataGridRates.Name = "DataGridRates"
        Me.DataGridRates.Size = New System.Drawing.Size(416, 512)
        Me.DataGridRates.TabIndex = 8
        '
        'btnDeal
        '
        Me.btnDeal.Location = New System.Drawing.Point(432, 272)
        Me.btnDeal.Name = "btnDeal"
        Me.btnDeal.Size = New System.Drawing.Size(208, 23)
        Me.btnDeal.TabIndex = 18
        Me.btnDeal.Text = "Request Deal"
        '
        'Label2
        '
        Me.Label2.Location = New System.Drawing.Point(464, 216)
        Me.Label2.Name = "Label2"
        Me.Label2.Size = New System.Drawing.Size(56, 16)
        Me.Label2.TabIndex = 14
        Me.Label2.Text = "Amount:"
        '
        'Label3
        '
        Me.Label3.Location = New System.Drawing.Point(464, 168)
        Me.Label3.Name = "Label3"
        Me.Label3.Size = New System.Drawing.Size(56, 16)
        Me.Label3.TabIndex = 12
        Me.Label3.Text = "CCY:"
        '
        'Label4
        '
        Me.Label4.Location = New System.Drawing.Point(8, 8)
        Me.Label4.Name = "Label4"
        Me.Label4.Size = New System.Drawing.Size(56, 16)
        Me.Label4.TabIndex = 13
        Me.Label4.Text = "Rates:"
        '
        'Label5
        '
        Me.Label5.Location = New System.Drawing.Point(432, 8)
        Me.Label5.Name = "Label5"
        Me.Label5.Size = New System.Drawing.Size(208, 16)
        Me.Label5.TabIndex = 16
        Me.Label5.Text = "Dealing:"
        '
        'txtRate
        '
        Me.txtRate.Location = New System.Drawing.Point(520, 192)
        Me.txtRate.Name = "txtRate"
        Me.txtRate.Size = New System.Drawing.Size(120, 20)
        Me.txtRate.TabIndex = 10
        Me.txtRate.Text = ""
        '
        'Label6
        '
        Me.Label6.Location = New System.Drawing.Point(464, 192)
        Me.Label6.Name = "Label6"
        Me.Label6.Size = New System.Drawing.Size(56, 16)
        Me.Label6.TabIndex = 15
        Me.Label6.Text = "Rate:"
        '
        'ComboBoxBuySell
        '
        Me.ComboBoxBuySell.Items.AddRange(New Object() {"B", "S"})
        Me.ComboBoxBuySell.Location = New System.Drawing.Point(520, 240)
        Me.ComboBoxBuySell.Name = "ComboBoxBuySell"
        Me.ComboBoxBuySell.Size = New System.Drawing.Size(121, 21)
        Me.ComboBoxBuySell.TabIndex = 20
        Me.ComboBoxBuySell.Text = "{Select}"
        '
        'Label1
        '
        Me.Label1.Location = New System.Drawing.Point(464, 240)
        Me.Label1.Name = "Label1"
        Me.Label1.Size = New System.Drawing.Size(56, 16)
        Me.Label1.TabIndex = 21
        Me.Label1.Text = "Buy/Sell:"
        '
        'lblStatus
        '
        Me.lblStatus.Location = New System.Drawing.Point(64, 8)
        Me.lblStatus.Name = "lblStatus"
        Me.lblStatus.Size = New System.Drawing.Size(360, 16)
        Me.lblStatus.TabIndex = 22
        '
        'Form1
        '
        Me.AutoScaleBaseSize = New System.Drawing.Size(5, 13)
        Me.ClientSize = New System.Drawing.Size(648, 541)
        Me.Controls.Add(Me.lblStatus)
        Me.Controls.Add(Me.Label1)
        Me.Controls.Add(Me.ComboBoxBuySell)
        Me.Controls.Add(Me.txtResponse)
        Me.Controls.Add(Me.txtAmount)
        Me.Controls.Add(Me.ComboBoxCCY)
        Me.Controls.Add(Me.DataGridRates)
        Me.Controls.Add(Me.btnDeal)
        Me.Controls.Add(Me.Label2)
        Me.Controls.Add(Me.Label3)
        Me.Controls.Add(Me.Label4)
        Me.Controls.Add(Me.Label5)
        Me.Controls.Add(Me.txtRate)
        Me.Controls.Add(Me.Label6)
        Me.FormBorderStyle = System.Windows.Forms.FormBorderStyle.Fixed3D
        Me.Name = "Form1"
        Me.Text = "Example Web Services Client"
        CType(Me.DataGridRates, System.ComponentModel.ISupportInitialize).EndInit()
        Me.ResumeLayout(False)

    End Sub

#End Region
    Dim myWebService As New com.efxnow.api.Service()

    Private Sub Form1_Load(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles MyBase.Load
        'When we load, let's connect to the WebServices:
        Me.lblStatus.Text = "Connected, server time " & myWebService.GetTime & " (GMT)"
    End Sub

    Private Sub ComboBox1_SelectedIndexChanged(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles ComboBoxCCY.SelectedIndexChanged

        'Verbose code example of Rate history:
        'This code could be contracted to a single composite statement!

        ' Set up string variables in preparation for call:
        Dim Key As String = "9087310700"
        Dim Quote As String = Me.ComboBoxCCY.SelectedItem
        Dim StartDateTime As String = DateAdd(DateInterval.Second, -600, Now).ToShortDateString & " " & DateAdd(DateInterval.Second, -600, Now).ToLongTimeString
        Dim EndDateTime As String = ""

        'Create DataSet object as container for the result set
        Dim myResponse As New DataSet

        'Fill dataset:
        myResponse = myWebService.GetHistoricRatesDataSet(Key, Quote, StartDateTime, EndDateTime)

        'Bind the resulting DataSet table to a grid for display
        Me.DataGridRates.DataSource = myResponse.Tables(0)

    End Sub

    Private Sub btnDeal_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles btnDeal.Click

        'Verbose code example of Trade Execution:

        ' Set up string variables in preparation for call:
        Dim UserID As String = "Wireless"
        Dim PWD As String = "Demo"
        Dim Pair As String = Me.ComboBoxCCY.SelectedItem
        Dim BuySell As String = Me.ComboBoxBuySell.SelectedItem
        Dim Amount As String = txtAmount.Text
        Dim Rate As String = txtRate.Text

        'Create DealResponse object as container for the result set
        Dim myResponse As New com.efxnow.api.objDealResponse()

        'Fill Return Object:
        myResponse = myWebService.DealRequest(UserID, PWD, Pair, BuySell, Amount, Rate)

        'Pull items back from Object:
        Dim Message As String

        If myResponse.Success Then
            Message = myResponse.Confirmation
        Else
            Message = myResponse.ErrorDescription
        End If

        'Display Message
        Me.txtResponse.Text = Message

    End Sub

End Class
