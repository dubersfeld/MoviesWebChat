<%@ tag body-content="scriptless" trimDirectiveWhitespaces="true" %>
<%@ attribute name="htmlTitle" type="java.lang.String" rtexprvalue="true"
              required="true" %>
<%@ attribute name="bodyTitle" type="java.lang.String" rtexprvalue="true"
              required="true" %>
<%@ include file="/WEB-INF/jsp/base.jspf" %>
<template:main htmlTitle="${htmlTitle}" bodyTitle="${bodyTitle}">

    <jsp:body>
    		
		
		 <br/>
    	<a href="<c:url value="/backHome" />"><spring:message code="index.backHome"/></a><br/>
		<br/>
        <jsp:doBody />
    </jsp:body>
    
</template:main>
