<spring:message code="title.createActorMovie" var="pageTitle" />
<template:displayPost htmlTitle="${pageTitle}" bodyTitle="${pageTitle}">
  
  <jsp:attribute name="displayContent"><br/>
  
	<form:form method="POST" action="createActorMovie" 
									modelAttribute="actorMovie">     
    <table>
    <tr>
        <td><form:label path="actorId"><spring:message code="createActorMovie.actorId"/></form:label></td>
        <td><form:input path="actorId" /></td>
        <td><form:errors path="actorId" class="errors" /></td>
    </tr>
    <tr>
        <td><form:label path="movieId"><spring:message code="createActorMovie.filmId"/></form:label></td>
        <td><form:input path="movieId" /></td>
        <td><form:errors path="movieId" class="errors" /></td>
    </tr>
    <tr>
        <td colspan="2">
            <input type="submit" value="<spring:message code="form.valid"/>"/>
        </td>
    </tr>
    </table>
	</form:form>

  </jsp:attribute>
  
</template:displayPost>