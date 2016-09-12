
<%@page import="core.Login"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page import="core.OTRIssues"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js"></script>
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
<link href="css/table.css" rel="stylesheet" type="text/css" />
<script>
setInterval(function(){
	var xmlhttp;
	if (window.XMLHttpRequest)
	  {// code for IE7+, Firefox, Chrome, Opera, Safari
	  xmlhttp=new XMLHttpRequest();
	  }
	else
	  {// code for IE6, IE5
	  xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
	  }
	xmlhttp.onreadystatechange=function()
	  {
	  if (xmlhttp.readyState==4 && xmlhttp.status==200)
	    {
	    document.getElementById("div1").innerHTML=xmlhttp.responseText;
	    }
	  }
	xmlhttp.open("POST","updateOtr.htm",true);
	xmlhttp.send(); 
	
	
}, 5000);	
</script>
<script>
setInterval(function(){
	var xmlhttp;
	if (window.XMLHttpRequest)
	  {// code for IE7+, Firefox, Chrome, Opera, Safari
	  xmlhttp=new XMLHttpRequest();
	  }
	else
	  {// code for IE6, IE5
	  xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
	  }
	xmlhttp.onreadystatechange=function()
	  {
	  if (xmlhttp.readyState==4 && xmlhttp.status==200)
	    {
	    document.getElementById("your_limit").innerHTML=xmlhttp.responseText;
	    }
	  }
	xmlhttp.open("POST","getLimit.htm",true);
	xmlhttp.send(); 
	
	
}, 3000);	
</script>
<script>
var count=0;
function popItUp(buttonId)
{
window.open('tradeBond.htm?cusip='+buttonId,'Popup_Window'+count,'toolbar=0,scrollbars=0,location=0,statusbar=0,menubar=0,resizable=0,width=600,height=800,left = 312,top = 234');
    count++;
}
</script>

</head>
<body>
<jsp:include page="header.html"></jsp:include>
<%
Login login = (Login)session.getAttribute("login");
String limitAmount = (String)session.getAttribute("limitAmount");
%>
<br/><br/>
<h2 align="left">Welcome <b><font size="6"><%=login.getUserId()%></font></b></h2>
<div id="your_limit"><h3 align="left">Your limit is <b><%=limitAmount %></b></h3></div>
<%
Map<String,Object> map = (Map<String,Object>)request.getAttribute("map");
List<OTRIssues> list = (List<OTRIssues>)map.get("OTRISSUES");
Double ltp = (Double)map.get("LTP");
Double close = (Double)map.get("CLOSE");
Double change = (ltp-close)*100/ltp;
Double open = (Double)map.get("OPEN");
Double high = (Double)map.get("HIGH");
Double low = (Double)map.get("LOW");
%>

<form id="formID" name="jform" method="get">
<div style="background: #FFD699;float:center;"><h2 align="center">OTRIssues(Offerings)</h2></div>
<div id="div1" style="float:center;margin:0% 15% 0%">
<table id="table" border="2" cellspacing="2" cellpadding="2">
<tr>
<th align='center'>TICKER</th><th align='center'>BID</th><th align='center'>BID YIELD</th><th align='center'>ASK</th><th align='center'>ASK YIELD</th><th align='center'>LTP</th><th align='center'>CLOSE</th><th align='center'>%Change</th><th align='center'>OPEN</th><th align='center'>HIGH</th><th align='center'>LOW</th><th align="center" colspan="2">ACTION</th>
</tr>
<% 
for(OTRIssues o:list)
{
	String symbol = o.getIssuerId()+"/"+o.getCoupon()+"/"+o.getMaturity();
	
	%>
	<tr>
	<td><%=symbol%></td>
	<% 
	if(o.getBid_prev()==0)
	{
		out.println("<td style='color: #000000;'><b>"+o.getBid()+"</b></td>");
		out.println("<td style='color: #000000;'><b>"+o.getBid_yield()+"</b></td>");
	}
	else if(o.getBid()>o.getBid_prev())
	{
		/* out.println("<td bgcolor='green' style='color: #000000;'><b>"+o.getBid()+"</b></td>");
		out.println("<td bgcolor='red' style='color: #000000;'><b>"+o.getBid_yield()+"</b></td>"); */
		 out.println("<td style='color: green;'><b>"+o.getBid()+"</b></td>");
			out.println("<td style='color: red'><b>"+o.getBid_yield()+"</b></td>"); 
	}
	else
	{
	/* 	out.println("<td bgcolor='red' style='color: #000000;'><b>"+o.getBid()+"</b></td>");
		out.println("<td bgcolor='green' style='color: #000000;'><b>"+o.getBid_yield()+"</b></td>");
	 */
		out.println("<td style='color: red'><b>"+o.getBid()+"</b></td>");
		out.println("<td style='color: green;'><b>"+o.getBid_yield()+"</b></td>");
	 
	
	}
	
	
	if(o.getAsk_prev()==0)
	{
		out.println("<td style='color: #000000;'><b>"+o.getAsk()+"</b></td>");
		out.println("<td style='color: #000000;'><b>"+o.getAsk_yield()+"</b></td>");
	}
	else if(o.getAsk()>o.getAsk_prev())
	{
		/* out.println("<td bgcolor='green' style='color: #000000;'><b>"+o.getAsk()+"</b></td>");
		out.println("<td bgcolor='red' style='color: #000000;'><b>"+o.getAsk_yield()+"</b></td>"); */
		
		 out.println("<td style='color: green'><b>"+o.getAsk()+"</b></td>");
		out.println("<td style='color:red'><b>"+o.getAsk_yield()+"</b></td>"); 
	}
	else
	{
		/* out.println("<td bgcolor='red' style='color: #000000;'><b>"+o.getAsk()+"</b></td>");
		out.println("<td bgcolor='green' style='color: #000000;'><b>"+o.getAsk_yield()+"</b></td>"); */
		out.println("<td style='color: red'><b>"+o.getAsk()+"</b></td>");
		out.println("<td style='color:green'><b>"+o.getAsk_yield()+"</b></td>");
	}
%>

<td><%=ltp%></td><td><%=close%></td><td><%=change%></td><td><%=open%></td><td><%=high%></td><td><%=low%></td>
<td><button type="button" name="click" value="buy1-<%=o.getCusip()%>-<%=o.getBid() %>" onclick="popItUp(this.value)"><font style='color: green'><b>Buy</b></font></button></td>
<td><button type="button" name="click" value="sell-<%=o.getCusip()%>-<%=o.getAsk() %>" onclick="popItUp(this.value)"><font style='color: red'><b>Sell</b></font></button></td>
</tr>
<% 
}
%>
</table>
</div>
<br/>

</form>
<br/><br/>
<jsp:include page="/WEB-INF/footer.html"></jsp:include>
</body>
</html>