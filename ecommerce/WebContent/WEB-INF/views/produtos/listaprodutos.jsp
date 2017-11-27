<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/materialize/css/materialize.css"/>" /> 
<link rel = "stylesheet" href = "https://fonts.googleapis.com/icon?family=Material+Icons">


<title>Lista - Produtos</title>
</head>
<body>

<nav>
    <div class="nav-wrapper blue">
      <a href="#!" class="brand-logo"><i class="">${tituloHeader}</i></a>
      <ul class="right hide-on-med-and-down">
        <li><a href="<c:url value="/logout"/>"><i class="">Logout</i></a></li>
      </ul>
    </div>
</nav>

<table class="responsive-table dataTable bordered highlight centered">
	<thead>
		<tr>
			<th>ID</th>
			<th>Name</th>
			<th>Description</th>
			<th>Price</th>
			<th>Options</th>
		</tr>
	</thead>
	<tbody>
	<c:forEach items="${lista}" var="produto">
		<tr>
			<td>${produto.id}</td>
			<td>${produto.nome}</td>
			<td>${produto.descricao}</td>
			<td>${produto.preco}</td>
			<td> 
			<a href="<c:url value="/produtos/editar?id=${produto.id}"/>"><i class="material-icons">edit</i></a>
			<a href="<c:url value="/produtos/remover?id=${produto.id}"/>"><i class="material-icons">remove_circle</i></a>
			</td>
		</tr>
	</c:forEach>
	</tbody>
</table><br>

<a class="btn-floating btn-large waves-effect waves-light blue" href="<c:url value="/produtos/novo"/>" ><i class="material-icons">add</i></a>


<p>Developed by: <%= getServletContext().getInitParameter("desenvolvedor") %> </p>


</body>

</html>