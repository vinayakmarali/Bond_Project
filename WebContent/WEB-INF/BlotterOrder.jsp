<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.sql.ResultSet"%>
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
function cancelfunc()
{
	//var cusip=document.forms["blotterorder"]["r1"].value;
	var cusip = $('input[name=r1]:checked').val();
	var status=document.forms["blotterorder"]["S_"+cusip].value;
	if(status=="EXECUTED" || status=="CANCELLED")
		{
	alert("Trade cannot be cancelled");
	return false;
		}
	else
		{
		alert("Your order has been cancelled");
		}
}
function isNumberKey(evt)
{
   var charCode = (evt.which) ? evt.which : event.keyCode;
   if (charCode != 46 && charCode > 31 
     && (charCode < 48 || charCode > 57))
      return false;

   return true;
}
function enableTextbox(orderID,status) {
	if(status=="OPEN")
		{
	$(".radio1").attr("disabled", "disabled");
	
     document.getElementById("notional."+orderID).disabled=false;
     document.getElementById("price."+orderID).disabled=false; 
		}
	else
		{
		$(".radio1").attr("disabled", "disabled");
		alert("Notional & Price cannot be changed for Executed/Cancelled trades");
		}
}
 function validate()
{
	var sd = document.forms["blotterorder"]["sdate"].value;	
	var ed = document.forms["blotterorder"]["edate"].value;	
	var coupon1 = document.forms["blotterorder"]["coupon1"].value;	
	var coupon2 = document.forms["blotterorder"]["coupon2"].value;	
	var tenor1 = document.forms["blotterorder"]["maturity1"].value;	
	var tenor2 = document.forms["blotterorder"]["maturity2"].value;	
	if(ed=="" && sd!="")
	{
		alert("Enter End Date");
		return false;
	} 
	if(sd=="" && ed!="")
	{
		alert("Enter Start Date");
		return false;
	} 
	if((coupon2=="" && coupon1!=""))
	{
		alert("Enter a valid coupon range");
		return false;
	} 
	if(coupon1=="" && coupon2!="")
	{
		alert("Enter a valid coupon range");
		return false;
	}
	if(coupon1!="" && coupon2!="" && coupon1>=coupon2)
	{
		alert("Enter a valid coupon range");
		return false;
	} 
	if(tenor1!="" && tenor2!="" && tenor1>=tenor2)
	{
		alert("Enter a valid tenor range");
		return false;
	} 
	if(tenor1=="" && tenor2!="")
	{
		alert("Enter a valid tenor range");
		return false;
	}
	if(tenor2=="" && tenor1!="")
	{
		alert("Enter a valid tenor range");
		return false;
	}
} 

	$(function() {
		$("#datepicker").datepicker({ dateFormat: 'dd-M-y' }).val();
	});
	$(function() {
		$("#datepicker1").datepicker({ dateFormat: 'dd-M-y' }).val();
	});
</script>
<title>Bond Trading System : Order Blotter</title>
</head>
<body>
<jsp:include page="/WEB-INF/header.html"></jsp:include>
<br><br>
<form method="POST" name="blotterorder"> 
<h3> <b> Order Blotter </b></h3>
<hr>
<b>
<table style="color:black">
<tr>
 <td>Start Date: </td><td><input type="text" name="sdate" id="datepicker" style="background:#F2EBDA;width:75px;"></input></td>
 <% %>
 <td>End Date: </td><td><input type="text" name="edate" id="datepicker1"  style="background:#F2EBDA;width:75px;"></input></td>
<td>&nbsp;&nbsp;&nbsp;</td>
<td>
Trade Type:
<select name="direction" style="background:#F2EBDA">
<option value="BUY">BUY</option>
<option value="SELL">SELL</option>
<option value="BOTH">BOTH</option>
</select>
</td>
<td>&nbsp;&nbsp;&nbsp;</td>
 <td>Coupon: </td><td><input type="text" name="coupon1" style="background:#F2EBDA;width:30px;" maxlength="5"></input></td>
 <td>to</td>
 <td><input type="text" name="coupon2" style="background:#F2EBDA;width:30px;" maxlength="5"></input></td>
 <td>&nbsp;&nbsp;&nbsp;</td>
 <td>Tenor: </td><td><input type="text" name="maturity1" style="background:#F2EBDA;width:30px;"></input></td>
 <td>to</td>
 <td><input type="text" name="maturity2" style="background:#F2EBDA;width:30px;"></input></td>
<td></td><td><input type="submit" value="Search" name="btn" onclick="return validate()"></td>
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
	<td>&nbsp;</td>
	<td>ORDER ID</td>
	<td>TICKER</td>
	<td>BROKER ID</td>
	<td>CUSIP</td>
	<td>TYPE</td>
	<td>PRINCIPAL</td>
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
<%
		String issuer=m.get("ISSUER");
		String coupon=m.get("COUPON");
		String maturity[]=m.get("MATURITY").toString().split("-");
		String ticker=issuer+" - "+coupon+" - "+maturity[1]+"/"+maturity[0];
		String cusip=m.get("CUSIP");
		String orderId=m.get("ORDER_ID");
		String status=m.get("STATUS");
		%>
		
		<td><input type="radio" name="r1" value="<%=cusip%>" onclick="return enableTextbox('<%=cusip%>','<%=status%>')"></td>
		<td><%=orderId %></td>
		<td><%=ticker %></td>
		<%String brokerId=m.get("BROKER_ID");%>
		<td><%=brokerId %></td>
		<td><%=cusip %></td>
		<%String direction=m.get("DIRECTION");%>
		<td><%=direction %></td>
		<%String notional=m.get("NOTIONAL");%>
        <td><input type="text" disabled="true" class="radio1" name="notional.<%=cusip%>" id="notional.<%=cusip%>" value="<%=notional%>" onkeypress="return isNumberKey(event)"></td>
		<%String price=m.get("PRICE");%>
        <td><input type="text" disabled="true" class="radio1" name="price.<%=cusip%>" id="price.<%=cusip%>" value="<%=price%>" onkeypress="return isNumberKey(event)"></td>
		<%String yield=m.get("YIELD");%>
		<td><%=yield %></td>
 		<td><input type="text" name="S_<%=cusip%>" value="<%=status%>" readonly="readonly" style="border:0;"></td>
 <%String date_time[]=m.get("ORDER_DATE").toString().split("-");%>
		<td><%=date_time[1]+"/"+date_time[2].substring(0,2)+"/"+date_time[0]%></td> 
		</tr>
<% }}%>
</table>

<% 
ArrayList<Map<String,String>> list = (ArrayList<Map<String,String>>)request.getAttribute("blotterlist");

if(list!=null)
{	%>
	<table border="2" cellpadding="10">
	<tr bgcolor="#1E3851">
	<font color="white">
	<b>
	<td>&nbsp;</td>
	<td>ORDER ID</td>
	<td>TICKER</td>
	<td>BROKER ID</td>
	<td>CUSIP</td>
	<td>TYPE</td>
	<td>PRINCIPAL</td>
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
<%
		String issuer=m.get("ISSUER");
		String coupon=m.get("COUPON");
		String maturity[]=m.get("MATURITY").toString().split("-");
		String ticker=issuer+" - "+coupon+" - "+maturity[1]+"/"+maturity[0];
		String cusip=m.get("CUSIP");
		String status=m.get("STATUS");
		%>
		<% String orderId=m.get("ORDER_ID");%>
		<td><input type="radio" name="r1" value="<%=cusip%>" onclick="return enableTextbox('<%=cusip%>','<%=status%>')"></td>
		<td><%=orderId %></td>
		<td><%=ticker %></td>
		<%String brokerId=m.get("BROKER_ID");%>
		<td><%=brokerId %></td>
		<td><%=cusip %></td>
		<%String direction=m.get("DIRECTION");%>
		<td><%=direction %></td>
		<%String notional=m.get("NOTIONAL");%>
		<td><input type="text" disabled="true" class="radio1" name="notional.<%=cusip%>" id="notional.<%=cusip%>" value="<%=notional%>"></td>
		<%String price=m.get("PRICE");%>
		<td><input type="text" disabled="true" class="radio1" name="price.<%=cusip%>" id="price.<%=cusip%>" value="<%=price%>"></td>
		<%String yield=m.get("YIELD");%>
		<td><%=yield %></td>
<td><input type="text" name="S_<%=cusip%>" value="<%=status %>"readonly="readonly" style="border:0;"></td>
 <%String date_time[]=m.get("ORDER_DATE").toString().split("-");%>
		<td><%=date_time[1]+"/"+date_time[2].substring(0,2)+"/"+date_time[0]%></td> 
		</tr>
<% }}%>
</table>
<br><br>
<center>
<input type="submit" value="Save" name="btn" onclick="alert('Values Updated')">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<input type="submit" value="Cancel Order" name="btn" onclick="return cancelfunc()">
</center>
</form>
<br><br>
<jsp:include page="/WEB-INF/footer.html"></jsp:include>
</body>
</html>