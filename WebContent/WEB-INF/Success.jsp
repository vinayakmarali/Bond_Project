<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://www.springframework.org/tags/form" prefix ="f" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="default.css" rel="stylesheet" type="text/css" /> 
<title>Bond Trading System : Price Publisher</title>
</head>
<body>
<jsp:include page="/WEB-INF/header.html"></jsp:include>
<br><br><br><br>
<f:form method="POST" commandName="OTRIssues"> 
<center>
<h3> <b>PRICE PUBLISHER  </b></h3>
<hr>
<table>
<tr>
<td>  CUSIP : </td><td><f:input path="cusip"></f:input></td>
</tr>
<tr>
 <td>Bid Price : </td><td><f:input path="bid"></f:input></td>
 </tr>
 <tr>
 <td>Ask Price: </td><td><f:input path="ask"></f:input></td>
 </tr>
<tr>
<td></td><td><input type="submit" value="Update"></td>
</tr>
</table>
</center>
</br></br>
</f:form>
<br><br><br><br>
<jsp:include page="/WEB-INF/footer.html"></jsp:include>
</body>
</html>