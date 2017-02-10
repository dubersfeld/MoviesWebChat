<spring:message code="title.successUpdateMovie" var="pageTitle" />
<spring:message code="date.pattern" var="datePattern" />
<template:displayPost htmlTitle="${pageTitle}" bodyTitle="${pageTitle}">
  
  <jsp:attribute name="displayContent"><br/>
 
   	<table>
    <tr>
        <td><spring:message code="movie.title"/>:</td>
        <td>${movie.title}</td>
    </tr>
    <tr>
        <td><spring:message code="movie.releaseDateDisplay"/>:</td>
        <td><fmt:formatDate value="${movie.releaseDate}" pattern="${datePattern}" type="date" /></td>
    </tr>
    <tr>
        <td><spring:message code="movie.directorId"/>:</td>
        <td>${movie.directorId}</td>
    </tr>   
    <tr>
        <td><spring:message code="movie.runningTime"/>:</td>
        <td>
        <spring:message code="movie.runningTimeDisplay">
        	<spring:argument value="${movie.runningTime}"/>
        </spring:message>
        </td> 
    </tr>
	</table>  

  </jsp:attribute>
  
</template:displayPost>