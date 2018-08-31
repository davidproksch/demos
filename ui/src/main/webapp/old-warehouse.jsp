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
<center>
<table>
<tr>
<td>Name</td><td>City</td><td>State</td>
</tr>
<%
StringBuffer sb = new StringBuffer();
List<Map<String,String>> l = owi.getAllWarehouses(); 
l.forEach(item -> {
	//System.err.println(item.get("name"));
	sb.append("<tr>")
		.append("<td>")
		.append(item.get("name"))
		.append("</td>")
		.append("<td>")
		.append(item.get("city"))
		.append("</td>")
		.append("<td>")
		.append(item.get("state"))
		.append("</td>")
		.append("</tr>");
});
%>
<%= sb.toString() %>
</table>
</center>
</body>
</html>
