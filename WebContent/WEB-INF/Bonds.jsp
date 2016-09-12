<%@page import="java.util.Map"%>
<%@page import="java.util.ArrayList"%>
<%@page import="core.IndicativeData"%>
<%@page import="core.Issuer"%>
<%@page import="java.util.List"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://www.springframework.org/tags/form" prefix="f" %>
    
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<body>

<head>
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js"></script>

<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>BOND007</title>
<meta name="keywords" content="" />
<meta name="description" content="" />
<link href="css/default.css" rel="stylesheet" type="text/css" />
<script>
function showIndicative(issuerId)
{
	$("#"+issuerId).toggle();
}
</script>

</head>
<body>

<jsp:include page="header.html"></jsp:include>
<br />
<br />
<form name="form1">

<%
ArrayList<ArrayList<Map<String,String>>> list = (ArrayList<ArrayList<Map<String,String>>>)request.getAttribute("list");
ArrayList<Map<String, String>> list1 = list.get(0);
ArrayList<Map<String, String>> list2 = list.get(1);
for(Map<String, String> map2:list2)
{%>
<h2><a href="javascript:showIndicative(<%=map2.get("ISSUER_ID")%>)"><%=map2.get("ISSUER_NAME")%></a></h2>
<div id="<%=map2.get("ISSUER_ID")%>" style="display:none">
<table border="2" cellspacing="2" cellpadding="2">
<tr><th>CUSIP</th><th>TYPE</th><th>ISSUE DATE</th><th>MATURITY</th><th>TENOR</th><th>COUPON</th><th>MOODYS</th></tr>
	<%
	for(Map<String,String> map1:list1)
	{
		if(map2.get("ISSUER_ID").equals(map1.get("ISSUER_ID")))
		{
	%>
	<tr><td><%=map1.get("CUSIP")%></td><td><%=map1.get("TYPE")%></td><td><%=map1.get("ISSUE_DATE")%></td><td><%=map1.get("MATURITY")%></td><td><%=map1.get("TENOR")%></td><td><%=map1.get("COUPON")%></td><td><%=map1.get("MOODYS")%></td></tr>		
	<% }
	}%>
	</table>
	</div>
<%}%>

</form>
<jsp:include page="footer.html"></jsp:include>

</body>
</html>