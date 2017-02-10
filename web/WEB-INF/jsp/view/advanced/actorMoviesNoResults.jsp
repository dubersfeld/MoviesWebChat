<spring:message code="title.noResultActorMovies" var="pageTitle" />
<template:displayPost htmlTitle="${pageTitle}" bodyTitle="${pageTitle}">
  
  <jsp:attribute name="displayContent"><br/>
  
	<spring:message code="advancedQueries.actorMoviesNoResult">
		<spring:argument value="${firstName}"/>
		<spring:argument value="${lastName}"/>
	</spring:message>
	
  </jsp:attribute>
  
</template:displayPost>