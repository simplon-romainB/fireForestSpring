<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<!--  <meta http-equiv="refresh" content="1; url=/">-->
<meta charset="UTF-8">
<title>FireForest</title>
<link rel="stylesheet" href="style/style.css" type="text/css">
<style>
body {
	letter-spacing: 0;
	margin: 0;
	padding: 0;
}
span {
	margin-right: -4px;
}

* {
	letter-spacing: 0;
}
</style>
</head>
<body>
<form action="next" method="post">
        <input type="submit" value="next">
    </form>
	<c:set var="count" value="${0}" />
	<c:forEach var="cell" begin="0" end="${ cells.size()-1}" step="1">


		<c:choose>
			<c:when test="${count%sqrtCellsSize eq 0}">

				<br>

			</c:when>
			<c:when
				test="${cells.get(count).getCellEtat().toString() eq 'INTACT'}">

				<span style="color: green">&block;</span>

			</c:when>
			<c:when
				test="${cells.get(count).getCellEtat().toString() eq 'EN_FEU'}">

				<span style="color: red">&block;</span>

			</c:when>
			<c:otherwise>

				<span style="color: grey">&block;</span>
				<!-- ajouter des images ? -->
			</c:otherwise>
		</c:choose>
		<c:set var="count" value="${count+1}" />
	</c:forEach>

	<fieldset>
		<form:form modelAttribute="config" action="configuration"
			method="post">
    choisissez un materiau
    <form:select path="materiau">
				<form:options items="${materiaux}" itemValue="nom" itemLabel="nom" />
			</form:select>
    choisissez une definition
    <form:select path="definition">
				<option value="${40}">40</option>
				<option value="${60}">60</option>
				<option value="${90}">90</option>
			</form:select>
    choisissez un nombre de foyers
    <form:select path="nbFoyer">
				<option value="${1}">1</option>
				<option value="${3}">3</option>
				<option value="${5}">5</option>
			</form:select>
    choisissez une propagation
    <form:select path="pbPropagation">
				<option value="${40}">40</option>
				<option value="${60}">60</option>
				<option value="${70}">70</option>
			</form:select>
			<form:input path="nom" />

			<button type="submit">sauvegarder configuration</button>
		</form:form>
	</fieldset>
	<form:form modelAttribute="configs" action="loadconfiguration"
		method="post">
		<select name="selectedConfiguration">
			<c:forEach items="${configs}" var="configuration">
				<option value="${configuration.nom}">${configuration.nom}
				<!-- mettre un id en plus du nom -->
			</c:forEach>
		</select>
		<button type="submit">charger configuration</button>
	</form:form>
</body>
</html>