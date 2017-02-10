<spring:message code="title.welcome" var="pageTitle" />
<template:menu htmlTitle="${pageTitle}" bodyTitle="${pageTitle}">
  
  <jsp:attribute name="navigationContent">
	<sec:authorize access="hasAuthority('VIEW')">
  		<a href="<c:url value="/actorQueries" />"><spring:message code="index.basicActorQueries"/></a><br/>		
		<a href="<c:url value="/directorQueries" />"><spring:message code="index.basicDirectorQueries"/></a><br/>
		<a href="<c:url value="/movieQueries" />"><spring:message code="index.basicMovieQueries"/></a><br/>
		<a href="<c:url value="/advancedQueries" />"><spring:message code="index.advancedQueries"/></a>
 		<br/>     
    </sec:authorize>
 
 	
 	<sec:authorize access="hasAuthority('CHAT')">
 		<br/>
 		<a href="<c:url value="/chatRoom/list" />">View Chat Rooms</a><br />
    </sec:authorize>
 			  </jsp:attribute>
  
</template:menu>