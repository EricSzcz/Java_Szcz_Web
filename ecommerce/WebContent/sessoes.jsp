<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

 <link rel="stylesheet" type="text/css" href="<c:url value="/resources/bootstrap/css/bootstrap.min.css"/>" />
 <link rel="stylesheet" type="text/css" href="<c:url value="/resources/bootstrap/css/bootstrap-theme.min.css"/>" />
 <script src="resources/bootstrap/js/bootstrap.min.js"></script>

<title>Lista - Produtos</title>
</head>
<body>

	<div class="jumbotron">
		<h3>Dados das Seções</h3>
	</div>
	
<p> Total Seçoes Criadas: <strong> ${totalSecoes} </strong></p>	

</body>
</html>