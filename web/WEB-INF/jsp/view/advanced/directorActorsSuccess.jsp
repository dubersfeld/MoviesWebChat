<spring:message code="title.directorActorsSuccess" var="pageTitle" />
<template:displayPost htmlTitle="${pageTitle}" bodyTitle="${pageTitle}">
  
  <jsp:attribute name="displayContent"><br/>
	
	<spring:message code="advancedQueries.directorActorsSuccess">
		<spring:argument value="${firstName}"/>
		<spring:argument value="${lastName}"/>
	</spring:message>
	<br/><br/>

	<c:forEach items="${actors}" var="actor">
           <c:out value = "${actor.firstName} ${actor.lastName}"/><br />
                                    
    </c:forEach>

  </jsp:attribute>
  
</template:displayPost>