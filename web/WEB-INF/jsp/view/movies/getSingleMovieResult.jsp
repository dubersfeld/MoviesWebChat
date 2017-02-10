<spring:message code="title.getMovieQueryResult" var="pageTitle" />
<spring:message code="date.pattern" var="datePattern" />
<template:displayPost htmlTitle="${pageTitle}" bodyTitle="${pageTitle}">
  
  <jsp:attribute name="displayContent"><br/>
  
    <spring:message code="movie.title"/>: ${movie.title}<br />
    <spring:message code="movie.releaseDateDisplay"/>: <fmt:formatDate value="${movie.releaseDate}" pattern="${datePattern}" type="date" /><br />
    <spring:message code="movie.director"/>: ${movie.directorName}<br />
                
    <spring:message code="movie.runningTime"/>:        
    <spring:message code="movie.runningTimeDisplay">
        <spring:argument value="${movie.runningTime}"/>
    </spring:message>
   
  </jsp:attribute>
  
</template:displayPost>