<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

</head>
<body>
<jsp:include page="header.html"></jsp:include>
<br/><br/><br/>
<%
String cusip = (String)request.getAttribute("cusip"); 
String isin = (String)request.getAttribute("isin");
String ticker = (String)request.getAttribute("ticker");
String type = (String)request.getAttribute("type");
String issue_date = (String)request.getAttribute("issue_date");
String maturity_date = (String)request.getAttribute("maturity_date");
String tenor = (String)request.getAttribute("tenor");
String coupon = (String)request.getAttribute("coupon");
String moodys = (String)request.getAttribute("moodys");
String snp = (String)request.getAttribute("snp");
String bid = (String)request.getAttribute("bid");
String callable = (String)request.getAttribute("callable");
String putable = (String)request.getAttribute("putable");
String calldate1 = (String)request.getAttribute("calldate1");
String calldate2 = (String)request.getAttribute("calldate2");
String calldate3 = (String)request.getAttribute("calldate3");
String putdate = (String)request.getAttribute("putdate");
String firstcoupon_date = (String)request.getAttribute("firstcoupon_date");
String nextcoupon_date = (String)request.getAttribute("nextcoupon_date");
String lastcoupon_date = (String)request.getAttribute("lastcoupon_date");
String ytm = (String)request.getAttribute("ytm");
String ytw = (String)request.getAttribute("ytw");
String notional = (String)request.getAttribute("notional");
%>
<%
/* request.setAttribute("cusip", cusip);
request.setAttribute("isin", isin);
request.setAttribute("ticker", ticker);
request.setAttribute("type", type);
request.setAttribute("issue_date", issue_date);
request.setAttribute("maturity_date", maturity_date);
request.setAttribute("tenor", tenor);
request.setAttribute("coupon", coupon);
request.setAttribute("moodys", moodys);
request.setAttribute("snp", snp);
request.setAttribute("bid", bid);
request.setAttribute("callable", callable);
request.setAttribute("putable", putable);
request.setAttribute("calldate1", calldate1);
request.setAttribute("calldate2", calldate2);
request.setAttribute("calldate3", calldate3);
request.setAttribute("putdate", putdate);
request.setAttribute("firstcoupon_date", firstcoupon_date);
request.setAttribute("nextcoupon_date", nextcoupon_date);
request.setAttribute("lastcoupon_date", lastcoupon_date);
request.setAttribute("ytm", ytm);
request.setAttribute("ytw", ytw);
request.setAttribute("notional", notional);
 */
%>
<center>
<form method="post" action="placeOrder.htm">
<table>
<tr>
<td>
<table border="2" cellspacing="2" cellpadding="2">
<tr><td>CUSIP</td><td><input style="border:0px solid #000000;" type="text" name="cusip" id="cusip" readonly="readonly" value="<%=cusip%>"></input></td></tr>
<tr><td>ISIN</td><td><input style="border:0px solid #000000;" type="text" name="isin" id="isin" readonly="readonly" value="<%=isin%>"></input></td></tr>
<tr><td>TICKER</td><td><input style="border:0px solid #000000;" type="text" name="ticker" id="ticker" readonly="readonly" value="<%=ticker%>"></input></td></tr>
 <tr><td>TYPE</td><td><input style="border:0px solid #000000;" type="text" name="type" id="type" readonly="readonly" value="<%=type%>"></input></td></tr>
 <tr><td>ISSUE_DATE</td><td><input style="border:0px solid #000000;" type="text" name="issue_date" id="issue_date" readonly="readonly" value="<%=issue_date%>"></input></td></tr>
<tr><td>MATURITY_DATE</td><td><input style="border:0px solid #000000;" type="text" name="maturity_date" id="maturity_date" readonly="readonly" value="<%=maturity_date%>"></input></td></tr>
<tr><td>TENOR</td><td><input style="border:0px solid #000000;" type="text" name="tenor" id="tenor" readonly="readonly" value="<%=tenor%>"></input></td></tr>
</table>
</td>
<td></td><td></td>
<td>
<table border="2" cellspacing="2" cellpadding="2">

<tr><td>COUPON</td><td><input style="border:0px solid #000000;" type="text" name="coupon" id="coupon" readonly="readonly" value="<%=coupon%>"></input></td></tr>
<tr><td>MOODYS</td><td><input style="border:0px solid #000000;" type="text" name="moodys" id="moodys" readonly="readonly" value="<%=moodys%>"></input></td></tr>
<tr><td>S&P</td><td><input style="border:0px solid #000000;" type="text" name="snp" id="snp" readonly="readonly" value="<%=snp%>"></input></td></tr>
<tr><td>MARKET PRICE</td><td><input style="border:0px solid #000000;" type="text" name="bid" id="bid" readonly="readonly" value="<%=bid%>"></input></td></tr>
<%-- <tr><td>YTW</td><td><input style="border:0px solid #000000;" type="text" name="ytw" id="ytw" readonly="readonly" value="<%=ytw%>"></input></td></tr> --%>
<tr><td>Principal Value</td><td><input type="text" style="border:0px solid #000000;" name="notional" id="notional" value="<%=notional%>"></input></td></tr>
<tr><td>Gross Amount</td><td><input type="text" style="border:0px solid #000000;" name="gross_amount" id="gross_amount" value="<%=(Double.parseDouble(bid)*Double.parseDouble(notional)/100) %>"></input></td></tr>
<tr><td colspan="2" align="center"><input type="submit" onclick="javascript:alert('Order Successfully Placed');" value="Confirm" name="common"></input><input type="button" name="common" value="Cancel" onclick="history.go(-1);"></input></td></tr>

</table>
</td>
</tr>
</table>
</form>
</center>
</body>
</html>