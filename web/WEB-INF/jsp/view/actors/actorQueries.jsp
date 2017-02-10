<spring:message code="index.basicActorQueries" var="pageTitle" />
<template:menu htmlTitle="${pageTitle}" bodyTitle="${pageTitle}">


  <jsp:attribute name="navigationContent"><br/>
   
  <sec:authorize access="hasAuthority('VIEW')">
 	<a href="<c:url value="/listAllActors" />"><spring:message code="actorQueries.listAllActors" /></a><br />
 	<a href="<c:url value="/numberOfActors" />"><spring:message code="actorQueries.numberOfActors" /></a><br />
 	<a href="<c:url value="/getActor" />"><spring:message code="actorQueries.getActor" /></a><br />
 	<a href="<c:url value="/getActorByName" />"><spring:message code="actorQueries.getActorByName" /></a><br />
  	<br/>
  	<a href="<c:url value="/listAllActorsWithPhotos" />"><spring:message code="actorQueries.listAllActorsWithPhoto"/></a><br/>
	<a href="<c:url value="/getAllPhotosByActor" />"><spring:message code="actorQueries.getAllPhotosByActor"/></a><br/>
	<a href="<c:url value="/getActorWithPhotoByName" />"><spring:message code="actorQueries.getActorWithPhotoByName"/></a><br/>
  </sec:authorize>
  	
  <br/>	
  <sec:authorize access="hasAuthority('CREATE')">
	<a href="<c:url value="/addActor" />"><spring:message code="actorQueries.addActor"/></a><br/>
  	<a href="<c:url value="/createActorPhotoMulti" />"><spring:message code="actorQueries.createActorPhoto"/></a><br/>
  </sec:authorize>
  
  <br/>
  <sec:authorize access="hasAuthority('DELETE')">
	<a href="<c:url value="/deleteActor" />"><spring:message code="actorQueries.deleteActor" /></a><br/>
	<a href="<c:url value="/deleteActorPhoto" />"><spring:message code="actorQueries.deleteActorPhoto"/></a><br/>
  </sec:authorize>
	
  <br/>	
  <sec:authorize access="hasAuthority('UPDATE')">
	<a href="<c:url value="/updateActor" />"><spring:message code="actorQueries.updateActor" /></a><br/>
  </sec:authorize>
	
  </jsp:attribute>

</template:menu>

