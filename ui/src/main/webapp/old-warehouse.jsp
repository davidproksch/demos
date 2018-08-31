<%@page import="com.redhat.proksch.demo.ui.OldWarehouseInfo"%>
<%
OldWarehouseInfo owi = new OldWarehouseInfo();
%>
<html>
<head>
<title>Old Warehouse Page</title>
</head>
<body>
<center><H1>Old Warehouse Information</H1></center>
<hr></hr>
<H1> <%= owi.getAllWarehouses() %> </H1>
</body>
</html>
