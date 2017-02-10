<spring:message code="title.allPhotosByActorResult" var="pageTitle" />
<template:displayPost htmlTitle="${pageTitle}" bodyTitle="${pageTitle}">
  
  <jsp:attribute name="displayContent"><br/>
  	
  	<c:out value="All Photos of ${firstName} ${lastName}"/><br/><br/>
    
    <c:choose>
    <c:when test="${photoIds.size() > 0}">
	<c:forEach items="${photoIds}" var="photoId">
		<spring:message code="photo.id"/>:<c:out value="${photoId}"/><br/>
      	<img src="<c:url value="doGetActorPhoto/${photoId}"/>"><br/><br/>                   
    </c:forEach>
    </c:when>
    
    <c:otherwise>
    <c:out value="No photo found"/>
    </c:otherwise>
    
    </c:choose>
  	
  </jsp:attribute>
  
</template:displayPost>