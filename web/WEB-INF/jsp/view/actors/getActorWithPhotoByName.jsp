<spring:message code="title.getActorWithPhotoByName" var="pageTitle" />
<template:displayPost htmlTitle="${pageTitle}" bodyTitle="${pageTitle}">

  <jsp:attribute name="displayContent"><br/>
	<form:form method="POST" action="getActorWithPhotoByName" modelAttribute="actorName">
   	<table> 
    <tr>
        <td><form:label path="firstName"><spring:message code="personal.firstName"/>:</form:label></td>
        <td><form:input path="firstName" /></td>
        <td><form:errors path="firstName" class="errors"/></td>
    </tr>  
    <tr>
        <td><form:label path="lastName"><spring:message code="personal.lastName"/>:</form:label></td>
        <td><form:input path="lastName" /></td>
        <td><form:errors path="lastName" class="errors"/></td>
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