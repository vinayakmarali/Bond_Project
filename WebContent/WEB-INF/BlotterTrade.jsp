<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
        <%@ page import="java.util.ArrayList" %>
    <%@ page import="java.util.Map" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet"
	href="http://code.jquery.com/ui/1.10.1/themes/base/jquery-ui.css" />
<script src="http://code.jquery.com/jquery-1.9.1.js"></script>
<script src="http://code.jquery.com/ui/1.10.1/jquery-ui.js"></script>
<link rel="stylesheet" href="/resources/demos/style.css" />
<script type="text/javascript">
	$(function() {
		$("#datepicker").datepicker({ dateFormat: 'yy-mm-dd' }).val();
	});
	$(function() {
		$("#datepicker1").datepicker({ dateFormat: 'yy-mm-dd' }).val();
	});
</script>
<link href="default.css" rel="stylesheet" type="text/css" /> 
<title>Bond Trading System : Trade Blotter</title>
</head>
<body>
<jsp:include page="/WEB-INF/header.html"></jsp:include>
<form method="POST" name="blottertrade"> 
<br><br>
<h3> <b> Trade Blotter</b></h3>
<hr>
<b>
<table style="color:black" cellpadding="10">
<tr>
 <td>Start Date: </td><td><input type="text" name="sdate" id="datepicker" style="background:#F2EBDA;width:75px;"></input></td>
 <td>End Date: </td><td><input type="text" name="edate" id="datepicker1" style="background:#F2EBDA;width:75px;"></input></td>
<!--  <td>Direction : </td><td><input type="text" name="direction"></input></td> -->
<td>
Trade Type:
<select name="direction" style="background:#F2EBDA">
<option></option>
<option value="buy">Buy</option>
<option value="sell">Sell</option>
</select>
</td>
 <td>Yield: </td><td><input type="text" name="yield" style="background:#F2EBDA;width:30px;" maxlength="5"></input></td>
<td></td><td><input type="submit" value="Search"></td>
</tr>
</table>
</b>
<%
ArrayList<Map<String,String>> list1 = (ArrayList<Map<String,String>>)request.getAttribute("currentblottertrade");
if(list1!=null)
{	%>
	<table border="2"  cellpadding="10">
	<tr bgcolor="#1E3851">
	<font color="white">
	<b>
	<td>TRADE ID</td>
	<td>ORDER ID</td>
	<td>TICKER</td>
	<td>CUSIP</td>
	<td>PRICE</td>
	<td>YIELD</td>
	<td>TYPE</td>
	<td>GROSS AMOUNT</td>
	<td>ACCRUED INTEREST</td>
	<td>COMMISSION</td>
	<td>TAX</td>
	<td>NET AMOUNT</td>
	<td>TRADE DATE</td>
	<!-- <td>SETTLEMENT DATE</td> -->
	</b>
	</font>
	</tr>
	</b>
<% for(Map<String,String> m:list1)
{%><tr>
	<%String orderId=m.get("TRADE_ID");%>
	<td><%=orderId %></td>
	<%-- <%String brokerId=m.get("ORDER_ID");%>
	<td><%=brokerId %></td> --%>
	<%-- <%String cusip=m.get("FIRM_ID");%>
	<td><%=cusip %></td> --%>
	<%String direction=m.get("CUSIP");%>
	<td><%=direction %></td>
	<%String coupon=m.get("PRICE");%>
	<td><%=coupon %></td>
	<%String maturity=m.get("YIELD");%>
	<td><%=maturity %></td>
	<%String notional=m.get("DIRECTION");%>
	<td><%=notional %></td>
	<%String price=m.get("GROSS_AMT");%>
	<td><%=price %></td>
	<%String yield=m.get("ACCRUED_INTEREST");%>
	<td><%=yield %></td>
	<%String status=m.get("COMMISSION");%>
	<td><%=status %></td>
	<%String date_time=m.get("TAX");%>
	<td><%=date_time %></td>
	<%String net_amt=m.get("NET_AMT");%>
	<td><%=net_amt %></td>
	<%String trade_date=m.get("TRADE_DATE");%>
	<td><%=trade_date %></td>
	<%String settlement_date=m.get("SETTLEMENT_DATE");%>
	<td><%=settlement_date %></td>
</tr>
<% }}%>
</td>
</tr>
</table>
<%
ArrayList<Map<String,String>> list = (ArrayList<Map<String,String>>)request.getAttribute("blotterlist");
if(list!=null)
{	%>
	<table border="2" cellpadding="10">
	<tr bgcolor="#1E3851">
	<font color="white">
	<b>
	<td>TRADE ID</td>
	<!-- <td>ORDER ID</td> -->
	<!-- <td>FIRM ID</td> -->
	<td>CUSIP</td>
	<td>PRICE</td>
	<td>YIELD</td>
	<td>TYPE</td>
	<td>GROSS AMOUNT</td>
	<td>ACCRUED INTEREST</td>
	<td>COMMISSION</td>
	<td>TAX</td>
	<td>NET AMOUNT</td>
	<td>TRADE DATE</td>
	<td>SETTLEMENT DATE</td>
	</tr>
	</b>
<% for(Map<String,String> m:list)
{%><tr>
	<%String orderId=m.get("TRADE_ID");%>
	<td><%=orderId %></td>
	<%-- <%String brokerId=m.get("ORDER_ID");%>
	<td><%=brokerId %></td> --%>
	<%-- <%String cusip=m.get("FIRM_ID");%>
	<td><%=cusip %></td> --%>
	<%String direction=m.get("CUSIP");%>
	<td><%=direction %></td>
	<%String coupon=m.get("PRICE");%>
	<td><%=coupon %></td>
	<%String maturity=m.get("YIELD");%>
	<td><%=maturity %></td>
	<%String notional=m.get("DIRECTION");%>
	<td><%=notional %></td>
	<%String price=m.get("GROSS_AMT");%>
	<td><%=price %></td>
	<%String yield=m.get("ACCRUED_INTEREST");%>
	<td><%=yield %></td>
	<%String status=m.get("COMMISSION");%>
	<td><%=status %></td>
	<%String date_time=m.get("TAX");%>
	<td><%=date_time %></td>
	<%String net_amt=m.get("NET_AMT");%>
	<td><%=net_amt %></td>
	<%String trade_date=m.get("TRADE_DATE");%>
	<td><%=trade_date %></td>
	<%String settlement_date=m.get("SETTLEMENT_DATE");%>
	<td><%=settlement_date %></td>
	</tr>
	
<% }}%>
</td>
</tr>
</table>
</center>
</br></br>
</form>
<br><br>
<jsp:include page="/WEB-INF/footer.html"></jsp:include>
</body>
</html>