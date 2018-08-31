<%@page import="com.redhat.proksch.demo.ui.A"%>
<%
A aa = new A();
%>
<html>
<head>
<title>Old Warehouse Page</title>
</head>
<body>
<center><H1>Old Warehouse Information</H1></center>
<hr></hr>
<H1> <%= aa.getMessage() %> </H1>
</body>
</html>
