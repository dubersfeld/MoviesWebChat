<spring:message code="title.numberOfMovies" var="pageTitle" />
<template:display htmlTitle="${pageTitle}" bodyTitle="${pageTitle}">
  
  <jsp:attribute name="displayContent"><br/>
  
 	<spring:message code="numberOfMovies.result">
		<spring:argument value="${number}"/>
	</spring:message>
  
  </jsp:attribute>
  
</template:display>




