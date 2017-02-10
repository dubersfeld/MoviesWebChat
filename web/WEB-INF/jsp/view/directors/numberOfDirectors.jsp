<spring:message code="title.numberOfDirectors" var="pageTitle" />
<template:display htmlTitle="${pageTitle}" bodyTitle="${pageTitle}">

  <jsp:attribute name="displayContent"><br/>
  
 	<spring:message code="numberOfDirectors.result">
		<spring:argument value="${number}"/>
	</spring:message>
  
  </jsp:attribute>
  
</template:display>




