<%@page import="com.redhat.proksch.demo.ui.NewWarehouseRest"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Map"%>
<%
NewWarehouseRest nwi = new NewWarehouseRest();
%>
<html>
<head>
<title>New Warehouse - Using REST Page</title>
</head>
<body>
<center><H1>New Warehouse Information - REST</H1></center>
<hr></hr>
<center>
<table>
<tr>
<td>Name</td><td>City</td><td>State</td>
</tr>
<%
nwi.getAllWarehouses();
%>
</table>
</center>
</body>
</html>
