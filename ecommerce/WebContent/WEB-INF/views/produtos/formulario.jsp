<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<link rel = "stylesheet" href = "https://fonts.googleapis.com/icon?family=Material+Icons">
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/materialize/css/materialize.css"/>" /> 

<title>Produtos - Formulario</title>
</head>
<body>
<script type="text/javascript" src="https://code.jquery.com/jquery-2.1.4.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.97.5/js/materialize.min.js"></script>

<nav>
    <div class="nav-wrapper blue">
      <a href="#!" class="brand-logo"><i class="">${tituloHeader}</i></a>
      <ul class="right hide-on-med-and-down">
        <li><a href="<c:url value="/logout"/>"><i class="">Logout</i></a></li>
      </ul>
    </div>
</nav>


<div class="row">
	<form class="col s12" action="<c:url value="/produtos/salvar"/>" method="post">
		<div class="row">
			<div class="input-field col s1">
			 	<!-- <label class="active" for="ID">ID</label><br> -->    
				<input class="validate" type="hidden" name="produto.id" readonly="readonly" value="${produtoAtual.id}"></input>
			</div>
		</div>	

       <div class="row">
        	<div class="input-field col s1">
				<input class="validate" id="name" type="text" name="produto.nome" value="${produtoAtual.nome}"></input>
				<label for="name" >Name</label>
			</div>
		</div>

       <div class="row">
        	<div class="input-field col s12">
				<label class="active" for="desc">Description</label>
				<input Class="input-field col s1" type="text" name="produto.descricao" value="${produtoAtual.descricao}"></input>
			</div>
		</div>

		<div class="row">
        	<div class="input-field col s12">
				<label class="active" for="pric">Price</label>
				<input Class="input-field col s1" type="text" name="produto.preco" value="${produtoAtual.preco}"></input>
			</div>
		</div>

		<button type="submit" class="btn btn-default">Save</button>
		
	</form>
</div>



</body>

  <script type="text/javascript">
  Materialize.updateTextFields();
</script>
</html>