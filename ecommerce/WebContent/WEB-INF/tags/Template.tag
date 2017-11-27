<%@ tag language="java" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ attribute name="title" required="true" %>
<%@ attribute name="scripts" fragment="true" %>
<!doctype html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<link rel="stylesheet" type="text/css" href="<c:url value="/resources/bootstrap/css/bootstrap.min.css"/>" />
		<link rel="stylesheet" type="text/css" href="<c:url value="/resources/bootstrap/css/bootstrap-theme.min.css"/>" />
	
		<title> ${title} </title>
	</head>
	<body>
	<%@ include file="/WEB-INF/views/header.jsp" %>
	<c:if test="${ not empty usuarioLogado }">
		<p class="pull-right"> <a href="<c:url value="/logout"/>" class="btn btn-primary">Sair</a></p>
	</c:if>
		<jsp:doBody/>
			
	<%@ include file="/WEB-INF/views/footer.jsp" %>
	
	<jsp:invoke fragment="scripts"></jsp:invoke>
	</body>
</html>