<spring:message code="index.basicDirectorQueries" var="pageTitle" />
<template:menu htmlTitle="${pageTitle}" bodyTitle="${pageTitle}">

 
 <jsp:attribute name="navigationContent"><br/>
   
  <sec:authorize access="hasAuthority('VIEW')">
 	<a href="<c:url value="/listAllDirectors" />"><spring:message code="directorQueries.listAllDirectors"/></a><br/>
	<a href="<c:url value="/numberOfDirectors" />"><spring:message code="directorQueries.numberOfDirectors"/></a><br/>
	<a href="<c:url value="/getDirector" />"><spring:message code="directorQueries.getDirector"/></a><br/>
	<a href="<c:url value="/getDirectorByName" />"><spring:message code="directorQueries.getDirectorByName"/></a><br/>
  </sec:authorize>	
  
  <sec:authorize access="hasAuthority('UPDATE')">
  	<a href="<c:url value="/updateDirector" />"><spring:message code="directorQueries.updateDirector"/></a><br/>
  </sec:authorize>
    
  <sec:authorize access="hasAuthority('DELETE')">
  	<a href="<c:url value="/deleteDirector" />"><spring:message code="directorQueries.deleteDirector"/></a><br/>
  </sec:authorize>
  
  <sec:authorize access="hasAuthority('CREATE')">
   	<a href="<c:url value="/addDirector" />"><spring:message code="directorQueries.addDirector"/></a><br/>	
  </sec:authorize>
 
  </jsp:attribute>

</template:menu>