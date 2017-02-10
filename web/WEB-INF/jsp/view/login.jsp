<spring:message code="title.welcome" var="pageTitle" />
<template:login htmlTitle="${pageTitle}" bodyTitle="${pageTitle}">

  <jsp:attribute name="displayContent">
  	
  	<c:if test="${param.containsKey('loginFailed')}">
        <b class="errors"><spring:message code="error.login.failed" /></b><br />
    </c:if>
    
    <br/>
      	<a href="register"><spring:message code="register.link" /></a>
    <br/><br/>
    
    <form:form method="post" modelAttribute="loginForm" autocomplete="off" >
    <table>   
    <tr>
        <td><form:label path="username"><spring:message code="field.login.username" /></form:label></td>
        <td><form:input path="username" autocomplete="off" /></td>
        <td><form:errors path="username" class="errors" /></td>
    </tr>
    <tr>
        <td><form:label path="password"><spring:message code="field.login.password" /></form:label></td>
        <td><form:password path="password" autocomplete="off" /></td>
        <td><form:errors path="password" class="errors" /></td>
    </tr>
    <tr>
    	<td colspan = "2"><input type="submit" value="<spring:message code="field.login.submit" />" /></td>
    </tr>
    </table>
    </form:form>
    
	</jsp:attribute>
	
</template:login>