<spring:message code="title.numberOfActors" var="pageTitle" />
<template:display htmlTitle="${pageTitle}" bodyTitle="${pageTitle}">

  <jsp:attribute name="displayContent"><br/>
  
 	<spring:message code="numberOfActors.result">
		<spring:argument value="${number}"/>
	</spring:message>
  
  </jsp:attribute>
  
</template:display>