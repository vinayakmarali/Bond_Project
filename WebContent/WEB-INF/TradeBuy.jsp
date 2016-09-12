<%@page import="core.Login"%>
<%@page import="java.util.Map"%>
<%@page import="core.IndicativeData"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<body>
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>Bond Trading System</title>
<meta name="keywords" content="" />
<meta name="description" content="" />
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js"></script>
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>

<script>
function checkOrderType(orderType,price)
{
	var marketPrice="";
	
	if(orderType=="Market Order")
		marketPrice = "<input type='text' id='price' name='price' value='"+price+"' readonly='readonly' style='border:0px solid #000000;'></input>";
		else
			if(orderType="Limit Order")
				{
					marketPrice = "<input type='text' id='price' name='price' value='"+price+"' required='required'></input>";
					
				}
	document.getElementById("market").innerHTML = marketPrice;	
	$("#price").focus();
	$("#price").select();
	$("input[id*='price']").keydown(function (event) {


        if (event.shiftKey == true) {
            event.preventDefault();
        }

        if ((event.keyCode >= 48 && event.keyCode <= 57) || 
            (event.keyCode >= 96 && event.keyCode <= 105) || 
            event.keyCode == 8 || event.keyCode == 9 || event.keyCode == 37 ||
            event.keyCode == 39 || event.keyCode == 46 || event.keyCode == 190) {

        } else {
            event.preventDefault();
        }

        if($(this).val().indexOf('.') !== -1 && event.keyCode == 190)
            event.preventDefault(); 
        //if a decimal has been added, disable the "."-button

    });


}

</script>

<script type="text/javascript">
function isNumber(evt)
{
  var e = evt || window.event; //window.event is safer, thanks @ThiefMaster
  var charCode = e.which || e.keyCode;                        
  if (charCode > 31 && ((charCode < 47) || charCode > 57))
  return false;
  if (e.shiftKey) return false;
  return true;
}
</script>

<script>
function checkGross()
{
	var price = document.forms["jform"]["price"].value;	
	var principal = document.forms["jform"]["principal"].value;
	var limitAmount = document.forms["jform"]["limitAmount"].value;
	var limit_hidden = document.forms["jform"]["limit_hidden"].value;
	var amt = (price*principal)/100;
	var direction = document.forms["jform"]["directionJs"].value;
	if(principal==0)
	{
		document.getElementById("limitDiv").innerHTML='<input type="text" id="limitAmount" name="limitAmount" value="'+(limit_hidden-amt)+'" readonly="readonly" style="border:0px solid #000000;color: #72A4D2;"></input>';
		document.getElementById("buttonPrint").innerHTML = '<input type="submit" name="'+direction+'" value="'+direction+'" disabled></input><input type="button" name="close" value="Close" onclick="window.close()"></input>';		
	}
	else
	if(limit_hidden<amt)
	{
		document.getElementById("limitDiv").innerHTML ='<input type="text" id="limitAmount" name="limitAmount" value="Limit is Exceeded" readonly="readonly" style="border:0px solid #000000;color: red;"></input>';
		document.getElementById("buttonPrint").innerHTML = '<input type="submit" name="'+direction+'" value="'+direction+'" disabled></input><input type="button" name="close" value="Close" onclick="window.close()"></input>';
	}
	else
	{
		document.getElementById("limitDiv").innerHTML='<input type="text" id="limitAmount" name="limitAmount" value="'+(limit_hidden-amt)+'" readonly="readonly" style="border:0px solid #000000;color: #72A4D2;"></input>';
		document.getElementById("buttonPrint").innerHTML = '<input type="submit" name="'+direction+'" value="'+direction+'"></input><input type="button" name="close" value="Close" onclick="window.close()"></input>';
	}
	document.getElementById("gross").value=amt;
}
</script>

</head>
<body>
<%
	Map<String,Object> map = (Map<String,Object>)request.getAttribute("map");
String price="";
	if(request.getAttribute("trade").equals("buy"))
		price = (String) request.getAttribute("bid");
	else
		if(request.getAttribute("trade").equals("sell"))
			price = (String) request.getAttribute("ask");
	String direction =(String) request.getAttribute("trade");
	Double coupon = Double.parseDouble(map.get("COUPON").toString());
	Double price1 = Double.parseDouble(price);
	Double yield = (coupon/price1)*100;
%>
<%
if(direction.equals("buy"))
{
	map.put("DIRECTION", "BUY");
	session.setAttribute("map", map);
%>
<h2>BUY ORDER</h2><hr>
<% } else if(direction.equals("sell")) 
{
	map.put("DIRECTION", "SELL");
	session.setAttribute("map", map);
%>
<h2>SELL ORDER</h2><hr>

<%} %>
<% 
Login login = (Login)session.getAttribute("login"); 
String limitAmount = (String)session.getAttribute("limitAmount");

double accruedInterest =(Double) request.getAttribute("accrued");
%>
<form name="jform" action="placeOrder.htm" method="post">
Welcome <b><font size="6"><%=login.getUserId()%></font></b><br/>
Your limit : <div id="limitDiv"><input type="text" id="limitAmount" name="limitAmount" value="<%=limitAmount %>" readonly="readonly" style="border:0px solid #000000;color: #72A4D2;"></input></div><br/><br/>
<input type="hidden" name="limit_hidden" value="<%=limitAmount %>"></input>
<input type="hidden" name="directionJs" value="Buy"></input>

<table>
<tr><td>ORDER ID :</td><td><input type="text" name="order_id" value="<%= map.get("ORDER_ID") %>" readonly="readonly" style="border:0px solid #000000;"></input></td></tr>
<tr><td>CUSIP :</td><td><%= map.get("CUSIP") %></td></tr>
<tr><td>Symbol :</td><td><%= map.get("TICKER") %>-<%=map.get("COUPON") %>-<%=map.get("MATURITY_DATE") %></td></tr>
<tr><td>Yield :</td><td><input type="text" name="yield" value="<%=yield %>" readonly="readonly" style="border:0px solid #000000;"></input></td></tr>
<tr><td>Type Of Order :</td><td><input type="radio" name="radio1" value="Market Order" checked="checked" onclick="checkOrderType(this.value,<%=price %>)">Market Order</input></td><td><input type="radio" name="radio1" value="Limit Order" onclick="checkOrderType(this.value,<%=price %>)">Limit Order</input></td>
<tr><td>Price :</td><td id="market"><input type="text" name="price" value="<%=price %>" readonly="readonly" style="border:0px solid #000000;"></input></td>
<tr><td>Principal :</td><td><input type="text" name="principal" onkeyup="javascript:checkGross()" onkeypress="return isNumber(event)"></input></td>
<tr><td>Accrued Interest :</td><td><input type="text" name="accrued" value="<%=accruedInterest %>" readonly="readonly" style="border:0px solid #000000;"></input></td><td>Clean Price :</td><td><input type="text" name="clean" value="<%=(price1-accruedInterest) %>" readonly="readonly" style="border:0px solid #000000;"></input></td>
<tr><td>Gross Amount :</td><td><input type="text" id="gross" name="gross" readonly="readonly" style="border:0px solid #000000;"></input></td>
<tr><td><input type="radio" name="radio2" value="vtt" checked="checked">Valid Till Today</td><td><input type="radio" name="radio2" value="vtc">Valid Till Cancel</td></tr>
<% if(direction.equals("buy"))
{
%>
	<tr><td colspan="2" align="center" id="buttonPrint"><input type="submit" name="buy" value="Buy" disabled></input><input type="button" name="close" value="Close" onclick="window.close()"></input></td>
<% }
else if(direction.equals("sell"))
{
%>
	<tr><td colspan="2" align="center" id="buttonPrint"><input type="submit" name="sell" value="Sell" disabled></input><input type="button" name="close" value="Close" onclick="window.close()"></input></td>
<%} %>
</table>
</form>


<br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/>
<%-- <jsp:include page="footer.html"></jsp:include> --%>

</body>
</html>