<spring:message code="title.actorDirectorsSuccess" var="pageTitle" />
<template:displayPost htmlTitle="${pageTitle}" bodyTitle="${pageTitle}">

  <jsp:attribute name="displayContent"><br/>
	
	<spring:message code="advancedQueries.actorDirectorsSuccess">
		<spring:argument value="${firstName}"/>
		<spring:argument value="${lastName}"/>
	</spring:message>
	<br/><br/>

	<c:forEach items="${directors}" var="director">
       <c:out value="${director.firstName} ${director.lastName}"/><br />
                                       
    </c:forEach>

  </jsp:attribute>
  
</template:displayPost>