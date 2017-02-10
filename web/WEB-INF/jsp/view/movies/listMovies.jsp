<spring:message code="title.moviesList" var="pageTitle" />
<spring:message code="date.pattern" var="datePattern" />
<template:display htmlTitle="${pageTitle}" bodyTitle="${pageTitle}">
  
  <jsp:attribute name="displayContent"><br/>
  	
	<c:forEach items="${movies}" var="movie">
				<spring:message code="movie.id"/>: ${movie.id}<br />
                <spring:message code="movie.title"/>: ${movie.title}<br />
                <spring:message code="movie.releaseDateDisplay"/>: <fmt:formatDate value="${movie.releaseDate}" pattern="${datePattern}" type="date" /><br />
                <spring:message code="movie.director"/>: ${movie.directorName}<br />
                
                <spring:message code="movie.runningTime"/>:        
        		<spring:message code="movie.runningTimeDisplay">
        			<spring:argument value="${movie.runningTime}"/>
        		</spring:message>
                     
                <br /><br />                        
    </c:forEach>
	
  </jsp:attribute>
  
</template:display>