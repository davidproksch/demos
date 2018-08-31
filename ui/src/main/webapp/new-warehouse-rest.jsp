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
StringBuffer sb = new StringBuffer();
List<Map<String,String>> l = nwi.getAllWarehouses();
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
