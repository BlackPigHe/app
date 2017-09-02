<%@page import="java.io.Writer"%>
<%@page import="javax.swing.text.html.HTML"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>

<body>
	hahah
	<%
		String value=request.getParameterValues("ll").toString();
		out.print(value);
	%>
</body>
</html>