<spring:message code="index.advancedQueries" var="pageTitle" />
<template:menu htmlTitle="${pageTitle}" bodyTitle="${pageTitle}">

   	<jsp:attribute name="navigationContent"><br/>
   
      <sec:authorize access="hasAuthority('VIEW')">
		<a href="<c:url value="/movieActors" />"><spring:message code="advancedQueries.movieActors"/></a><br/>
		<a href="<c:url value="/directorActors" />"><spring:message code="advancedQueries.directorActors"/></a><br/>
 	  </sec:authorize>
		
	  <sec:authorize access="hasAuthority('VIEW')">
		<a href="<c:url value="/actorDirectors" />"><spring:message code="advancedQueries.actorDirectors"/></a><br/>
 	  </sec:authorize>
		
	  <sec:authorize access="hasAuthority('VIEW')">
 		<a href=<c:url value="/actorMovies" />><spring:message code="advancedQueries.actorMovies"/></a><br/>
		<a href="<c:url value="/directorMovies" />""><spring:message code="advancedQueries.directorMovies"/></a><br/>
 	  </sec:authorize>
		
	  <sec:authorize access="hasAuthority('CREATE')">
		<a href="<c:url value="/createActorMovie" />"><spring:message code="advancedQueries.createActorMovie"/></a><br/>
		<a href="<c:url value="/createActorMovieTrans" />">Transaction</a><br/>
 	  </sec:authorize>
		
	</jsp:attribute>

</template:menu>




