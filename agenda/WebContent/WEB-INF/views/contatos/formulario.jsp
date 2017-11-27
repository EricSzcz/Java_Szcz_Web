<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<title>Agenda - Formulário</title>
</head>
<body>
<h1>Formulario contatos</h1>


<form action="<c:url value="/contatos/salvar"/>" method="post">

<label>Codigo</label>
<input type="text" name="contato.id" readonly="readonly" value="${contatoAtual.id}"><br>
<label>Nome</label>
<input type="text" name="contato.nome" placeholder="Digite seu Nome de contato" value="${contatoAtual.nome}"> <br>
<label>Email</label>
<input type="email" name="contato.email" placeholder="Digite seu Email de contato" value="${contatoAtual.email}"><br>
<label>Telefone</label>
<input type="tel" name="contato.telefone" placeholder="Digite seu Telefone de contato" value="${contatoAtual.telefone}"><br>
<button type="submit">Salvar</button>

</form>
</body>
</html>