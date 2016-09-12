<%@page import="core.OTRIssues"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Map"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Bond Trading System : Home</title>

<link href="css/default.css" rel="stylesheet" type="text/css" />
<link href="css/table.css" rel="stylesheet" type="text/css" />
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
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
	xmlhttp.open("POST","updateOtrIndex.htm",true);
	xmlhttp.send(); 
	
	
}, 5000);	
</script>
</head>
<body>
<jsp:include page="newheader.html"></jsp:include>
<br><br>
<table width="100%">
<tr>
<td width="25%" bgcolor="CCFFCC">
<fieldset>  
<br>
<form method="POST" action="userlogin.htm">  
<b>
<table style="color:black">  
<tr>  
<td><label>User Name:</label></td><td><input type="text" size="10" name="username"></input></td>  
</tr>  
<tr>  
<td><label >Password:</label></td><td><input type="password" name="password" size="10"></password></td>  
</tr>  
<tr>  
<td class="submit"></td><td><input type="submit" value="Login" /></td>  
</tr>  
<tr>
<td colspan="2">
<a href="Register.htm">New User Registration</a>
</td>
</tr>
</table> 
</b>
</form>
</fieldset>
</td>
<td>
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
<br/><br/>
<center>
<div style="background: #FFD699"><b>OTRIssues(Offerings)</b></div>
<div id="div1">
<table id="table" border="2" cellspacing="2" cellpadding="2">
<tr>
<th>TICKER</th><th>BID</th><th>BID YIELD</th><th>ASK</th><th>ASK YIELD</th><th>LTP</th><th>CLOSE</th><th>%Change</th><th>OPEN</th><th>HIGH</th><th>LOW</th>
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
</tr>
<% 
}
%>
</table>
</div>
</center>
<br/><br/>
</td>
</tr>
</table>
<jsp:include page="/WEB-INF/footer.html"></jsp:include>
</body>
</html>