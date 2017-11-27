<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/DataHoraTag.tld" prefix="custom" %> 
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/bootstrap/css/bootstrap.min.css"/>" />
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/bootstrap/css/signin.css"/>" />
<script src="resources/bootstrap/js/bootstrap.min.js"></script>

<title>Produtos - Formulario Login</title>
</head>
<body>

<div class="container" >
 <form action="<c:url value="/login"/>" method="post" class="form-signin" >

	<div class="row">
		<div class="col-md-12">
			<c:if test="${not empty mensagemErro}">
				<p class="alert alert-danger">${mensagemErro}</p>
			</c:if>
		</div>
	</div>	

	<h2 class="form-signin-heading">Login</h2>
	<label for="inputEmail" class="sr-only">Email</label>
	<input class="form-control" type="email" name="login.email" placeholder="Digite email" required autofocus value="${login.email}"></input>
	<label for="inputPassword" class="sr-only">Senha</label><br>
	<input class="form-control" type="password" name="login.senha" placeholder="Digite senha" required value="${login.senha}"></input>
	<button class="btn btn-lg btn-primary btn-block" type="submit" >Salvar</button>
  </form>
</div>

<%
	session.removeAttribute("mensagemErro");
%>
</body>
</html>