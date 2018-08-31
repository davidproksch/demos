<%@page import="com.redhat.proksch.demo.ui.OldWarehouseInfo"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Map"%>
<%
OldWarehouseInfo owi = new OldWarehouseInfo();
owi.connectToDB();
%>
<html>
<head>
<title>Old Warehouse Page</title>
</head>
<body>
<center><H1>Old Warehouse Information</H1></center>
<hr></hr>
<table>
<th>
<td>Name</td><td>City</td><td>State</td>
</th>
<%
List<Map<String,String>> l = owi.getAllWarehouses(); 
l.forEach(item -> {
	System.err.println(item);
};
%>
</table>
</body>
</html>
