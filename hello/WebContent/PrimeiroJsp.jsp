<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Primeiro JSP</title>
</head>
<body>

<% String mensagem = "Bem Vindo ao Java";
	int num1 = 2;
	int num2 = 2;
	int total = num1+num2;
	String nome = request.getParameter("nome");
%>
<h1><%=nome %></h1>
<h1><%=mensagem %></h1>
<h1>A soma de  <%=num1%> + <%=num2%> é <%=total %> </h1>

</body>
</html>


