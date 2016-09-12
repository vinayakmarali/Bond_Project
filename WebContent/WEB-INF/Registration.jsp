<%@page import="core.Book"%>
<%@page import="core.Bank"%>
<%@page import="core.Firm"%>
<%@page import="core.DepositoryBank"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.ArrayList"%>



<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://www.springframework.org/tags/form" prefix="f" %>
    
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<head>
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js"></script>
<script>
$(document).ready(function(){
  $("#topic1").click(function(){
    $("#content1").toggle();
  });
});
</script>
<script>
$(document).ready(function(){
  $("#topic2").click(function(){
    $("#content2").toggle();
  });
});
</script>

<script>
$(document).ready(function(){
  $("#topic3").click(function(){
    $("#content3").toggle();
  });
});
</script>

<script>
$(document).ready(function(){
  $("#topic4").click(function(){
    $("#content4").toggle();
  });
});
</script>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>BOND007</title>
<meta name="keywords" content="" />
<meta name="description" content="" />
<link href="css/default.css" rel="stylesheet" type="text/css" />
<script src="js/jquery-min.js"></script>

</head>
<body>

<jsp:include page="newheader.html"></jsp:include>
<br>
<br>
<br>


<form name="form5" method="POST">


<%
ArrayList<ArrayList<Map<String,String>>> list = (ArrayList<ArrayList<Map<String,String>>>)request.getAttribute("list");
ArrayList<Map<String, String>> list1 = list.get(0);
ArrayList<Map<String, String>> list2 = list.get(1);
ArrayList<Map<String, String>> list3 = list.get(2);
ArrayList<Map<String, String>> list4 = list.get(3);
ArrayList<Map<String, String>> list5 = list.get(4);
ArrayList<Map<String, String>> list6 = list.get(5);%>

<%-- <%=list1.size() %>
<%=list2.size() %>
<%=list3.size() %>
<%=list4.size() %>
<%=list5.size() %>
<%=list6.size() %> --%>


 

<table  border="2"    align="center" >
<tr bgcolor="#203C54" color="#FFFFFF"><td><h3 style="color:white">Personal Details</td>
    <td><h3 style="color:white">Login Details</td> </tr>

<tr><td><table>
    <tr><td>first name</td>
        <td><input type="text" name="firstname"></td>

</tr>

<tr><td>last name</td>
        <td><input type="text" name="lastname"></td>
</tr></table>
    

<td><table>
    <tr><td>username</td>
        <td><input type="text" name="username"></td>
</tr>
<tr><td>password</td>
        <td><input type="password" name="password"></td>
</tr>

<tr><td>Select Firm Name</td>
<td><select name="firmname">

<%
for(Map<String, String> f:list1)
{
	String temp = f.get("NAME");
	%><option value='<%=f.get("FIRM_ID") %>'><%=temp %></option><%
}
	%>

</select>
</td>
</tr>

<tr><td>Select book name</td>
<td><select name="bookname">
<%
for(Map<String,String> bk:list2)
{

	%><option value='<%=bk.get("BOOKID") %>'><%=bk.get("NAME")%></option><%
}
	%>



</select>
</td>
</tr>
<tr><td>&nbsp;</td></tr>




</table>
</tr>

<tr bgcolor="#203C54" color="#FFFFFF"><td><h3 style="color:white">Depository details</td><td><h3 style="color:white">Bank Account Details</td></tr>

<tr><td><table>
<br>
<tr><td>Enter your Depository Name</td>
<td>
<select name="dpname">
<%
for(Map<String,String> b:list5)
{
	String temp = b.get("DP_NAME")+", "+b.get("LOCATION");
	%><option value='<%=b.get("DP_ID") %>'><%=temp %></option><%
}
	%>


</select>
</td>



</tr>

<tr><td>Enter your DPAccount Number</td>
<td><input type="text" name="dpaccountno">
</td>
</tr>
<tr><td>&nbsp;</td></tr>
<br>
</table></td>
<br>

<td>
<table>
<tr><td>Account No:</td>
<td><input type="text" name="accountno">
</td>
</tr>


<tr>
<br>
<td>Bank Name:</td><td>
<select name="bank_name">
<%
for(Map<String,String> b:list3)
{
	String temp = b.get("NAME")+", "+b.get("STREETNAME")+","+b.get("CITY")+","+b.get("STATE");
	%><option value='<%=b.get("BANK_ID") %>'><%=temp %></option><%
}
	%>


</select>
</td>
</tr>






</table></td>
</tr>

</table>

<center>
<input type="submit" value="submit">
 </center>







</form>
<jsp:include page="footer.html"></jsp:include>

</body>
</html>