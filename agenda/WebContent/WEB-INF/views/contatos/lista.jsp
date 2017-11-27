<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="css/style.css"> 
<title>Agenda - Lista Contatos</title>
</head>
<body>
<h1>Lista de Contatos</h1>

<p>	
	<a href="<c:url value="/contatos/novo"/>">Novo</a>
</p>


<table>
	<thead>
		<tr>
			<th>ID</th>
			<th>Nome</th>
			<th>Telefone</th>
			<th>Email</th>
			<th>Opções</th>
		</tr>
	</thead>
	<tbody>
	<c:forEach items="${lista}" var= "contato">
		<tr>
			<td>${contato.id}</td>
			<td>${contato.nome}</td>
			<td>${contato.telefone}</td>
			<td>${contato.email}</td>
			<td> 
			<a href="<c:url value="/contatos/editar?id=${contato.id}"/>">Editar/</a>
			<a href="<c:url value="/contatos/remover?id=${contato.id}"/>">Remover</a>
			</td>
		</tr>
	</c:forEach>
	</tbody>
</table>

</body>
</html>