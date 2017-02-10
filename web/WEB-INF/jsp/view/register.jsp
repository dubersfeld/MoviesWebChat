<spring:message code="title.registration" var="pageTitle" />
<template:login htmlTitle="${pageTitle}" bodyTitle="${pageTitle}">


  <jsp:attribute name="displayContent"><br/>
    <form:form method="post" action="register" modelAttribute="registerForm" autocomplete="off" >
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
         <td colspan="2">
        	<input type="submit" value="<spring:message code="field.register.submit" />" />
       	 </td>	
    </tr>
    </table>
    </form:form>
  </jsp:attribute>
 
</template:login>