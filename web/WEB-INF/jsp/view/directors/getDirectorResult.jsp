<spring:message code="title.getDirectorQueryResult" var="pageTitle" />
<spring:message code="date.pattern" var="datePattern" />
<template:displayPost htmlTitle="${pageTitle}" bodyTitle="${pageTitle}">
  
  <jsp:attribute name="displayContent"><br/>
  
  	<h2><spring:message code="director.directorData"/></h2>
         
    <spring:message code="personal.id"/>: ${director.id}<br />
    <spring:message code="personal.firstName"/>: ${director.firstName}<br />
    <spring:message code="personal.lastName"/>: ${director.lastName}<br />
    <spring:message code="personal.birthDateDisplay"/>: <fmt:formatDate value="${director.birthDate}" pattern="${datePattern}" type="date" /><br /><br />              
   
  </jsp:attribute>
  
</template:displayPost>