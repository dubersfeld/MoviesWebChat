<spring:message code="title.noResultMovieActors" var="pageTitle" />
<spring:message code="date.pattern" var="datePattern" />
<template:displayPost htmlTitle="${pageTitle}" bodyTitle="${pageTitle}">
  
  <jsp:attribute name="formContent"><br/>

	<spring:message code="advancedQueries.movieActorsNoResult">
		<spring:argument value="${title}"/>
	</spring:message>
	<fmt:formatDate value="${releaseDate}" pattern="${datePattern}"/>
	
  </jsp:attribute>
  
</template:displayPost>