<spring:message code="index.basicDirectorQueries" var="pageTitle" />
<template:menu htmlTitle="${pageTitle}" bodyTitle="${pageTitle}">

 <jsp:attribute name="navigationContent"><br/>
   
  <sec:authorize access="hasAuthority('VIEW')">
 	<a href="<c:url value="/listAllMovies" />"><spring:message code="movieQueries.listAllMovies"/></a><br/>
	<a href="<c:url value="/numberOfMovies" />"><spring:message code="movieQueries.numberOfMovies"/></a><br/>
	<a href="<c:url value="/getMovie" />"><spring:message code="movieQueries.getMovie"/></a><br/>
	<a href="<c:url value="/getSingleMovie" />"><spring:message code="movieQueries.getSingleMovie"/></a><br/>
  </sec:authorize>
	
  <sec:authorize access="hasAuthority('CREATE')">
	<a href="<c:url value="/createMovie" />"><spring:message code="movieQueries.createMovie"/></a><br/>
  </sec:authorize>
	
  <sec:authorize access="hasAuthority('DELETE')">
	<a href="<c:url value="/deleteMovie" />"><spring:message code="movieQueries.deleteMovie"/></a><br/>
  </sec:authorize>
  
  <sec:authorize access="hasAuthority('UPDATE')">
	<a href="<c:url value="/updateMovie" />"><spring:message code="movieQueries.updateMovie"/></a><br/>
  </sec:authorize>
 	
 	
  </jsp:attribute>

</template:menu>