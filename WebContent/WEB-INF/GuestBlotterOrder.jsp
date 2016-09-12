<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="java.util.ArrayList" %>
    <%@ page import="java.util.Map" %>
    <%--     <%@ taglib uri="http://www.springframework.org/tags/form" prefix ="f" %> --%>
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
<title>Bond Trading System : Order Blotter</title>
</head>
<body>
<jsp:include page="/WEB-INF/newheader.html"></jsp:include>
<br><br>
<form method="POST" name="guestblotterorder"> 
<h3> <b> Order Blotter </b></h3>
<hr>
<form>
<b>
<table style="color:black">
<tr>
 <td>Start Date: </td><td><input type="text" name="sdate" id="datepicker" style="background:#F2EBDA;width:75px;"></input></td>
 <td>End Date: </td><td><input type="text" name="edate" id="datepicker1"  style="background:#F2EBDA;width:75px;"></input></td>
<td>
Trade Type:
<select name="direction" style="background:#F2EBDA">
<option></option>
<option value="buy">Buy</option>
<option value="sell">Sell</option>
</select>
</td>
 <td>Coupon: </td><td><input type="text" name="coupon" style="background:#F2EBDA;width:30px;" maxlength="5"></input></td>
 
 <td>Maturity: </td><td><input type="text" name="maturity" style="background:#F2EBDA;width:30px;" maxlength="5"></input></td>
 
<td></td><td><input type="submit" value="Search"></td>
</tr>
</table>
</b>
<br>
<%
ArrayList<Map<String,String>> list1 = (ArrayList<Map<String,String>>)request.getAttribute("currentblotterorder");
if(list1!=null)
{	%>
	<table border="2" cellpadding="10">
	<tr bgcolor="#1E3851">
	<font color="white">
	<b>
	<td>ORDER ID</td>
	<td>BROKER ID</td>
	<td>CUSIP</td>
	<td>TYPE</td>
	<td>COUPON</td>
	<td>MATURITY</td>
	<td>PAR VALUE</td>
	<td>PRICE</td>
	<td>YIELD</td>
	<td>STATUS</td>
	<td>DATE</td>
	</b>
	</font>
	</tr>
	</b>
<% for(Map<String,String> m:list1)
{%><tr>
	<%String orderId=m.get("ORDER_ID");%>
	<td><%=orderId %></td>
	<%String brokerId=m.get("BROKER_ID");%>
	<td><%=brokerId %></td>
	<%String cusip=m.get("CUSIP");%>
	<td><%=cusip %></td>
	<%String direction=m.get("DIRECTION");%>
	<td><%=direction %></td>
	<%String coupon=m.get("COUPON");%>
	<td><%=coupon %></td>
	<%String maturity=m.get("MATURITY");%>
	<td><%=maturity %></td>
	<%String notional=m.get("NOTIONAL");%>
	<td><%=notional %></td>
	<%String price=m.get("PRICE");%>
	<td><%=price %></td>
	<%String yield=m.get("YIELD");%>
	<td><%=yield %></td>
	<%String status=m.get("STATUS");%>
	<td><%=status %></td>
	<%String date_time=m.get("DATE_TIME");%>
	<td><%=date_time %></td>
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
	<td>ORDER ID</td>
	<td>BROKER ID</td>
	<td>CUSIP</td>
	<td>TYPE</td>
	<td>COUPON</td>
	<td>MATURITY</td>
	<td>PAR VALUE</td>
	<td>PRICE</td>
	<td>YIELD</td>
	<td>STATUS</td>
	<td>DATE</td>
	</b>
	</font>
	</tr>
	</b>
<% for(Map<String,String> m:list)
{%><tr>
	<%String orderId=m.get("ORDER_ID");%>
	<td><%=orderId %></td>
	<%String brokerId=m.get("BROKER_ID");%>
	<td><%=brokerId %></td>
	<%String cusip=m.get("CUSIP");%>
	<td><%=cusip %></td>
	<%String direction=m.get("DIRECTION");%>
	<td><%=direction %></td>
	<%String coupon=m.get("COUPON");%>
	<td><%=coupon %></td>
	<%String maturity=m.get("MATURITY");%>
	<td><%=maturity %></td>
	<%String notional=m.get("NOTIONAL");%>
	<td><%=notional %></td>
	<%String price=m.get("PRICE");%>
	<td><%=price %></td>
	<%String yield=m.get("YIELD");%>
	<td><%=yield %></td>
	<%String status=m.get("STATUS");%>
	<td><%=status %></td>
	<%String date_time=m.get("DATE_TIME");%>
	<td><%=date_time %></td>
	</tr>
<% }}%>
</td>
</tr>
</table>
</br></br>
</form>
<br><br>
<jsp:include page="/WEB-INF/footer.html"></jsp:include>
</body>
</html>