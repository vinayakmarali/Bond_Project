
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
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js">
</script>

<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>BOND007</title>
<meta name="keywords" content="" />
<meta name="description" content="" />
<link href="css/default.css" rel="stylesheet" type="text/css" />
<script>
function getIssuerId(issuerId)
{
	$.ajax({url:"getCusip8.htm?do="+issuerId,success:function(result){
	    $("#div1").html(result);
	  }}); 
}	
</script>


</head>
<body>

<jsp:include page="header.html"></jsp:include>

<br />
<br />
<%
ArrayList<ArrayList<Map<String,String>>> list = (ArrayList<ArrayList<Map<String,String>>>)request.getAttribute("list");
ArrayList<Map<String, String>> list1 = list.get(0);
ArrayList<Map<String, String>> list2 = list.get(1);
ArrayList<Map<String, String>> list3 = list.get(2);

session.setAttribute("cusip", list1);
session.setAttribute("otrIssues", list3);
%>
<br/><br/>
<center>
<form name="f1">
Select Issuer <select name="getIssuer" id="getIssuer" onchange="getIssuerId(this.value)">
<option value="select">Select</option>
<%
for(Map map1:list2)
{%>
<option value="<%=map1.get("ISSUER_ID") %>"><%=map1.get("ISSUER_NAME") %></option>	
<%
}
%>
</select>
</form>
<div id="div1">
</div>
</center>

<br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/>
<jsp:include page="footer.html"></jsp:include>

</body>
</html>