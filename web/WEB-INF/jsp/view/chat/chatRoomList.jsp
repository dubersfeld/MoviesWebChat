<spring:message code="title.chatRoom.list" var="pageTitle" />
<template:menu htmlTitle="${pageTitle}" bodyTitle="${pageTitle}">

 
  <jsp:attribute name="navigationContent"><br/>
   
 	 <c:choose>
        <c:when test="${empty chatrooms}">
            <i><spring:message code="message.chatList.none" /></i>
        </c:when>
        <c:otherwise>
            <spring:message code="message.chatList.instruction" />:<br /><br />
            <c:forEach items="${chatrooms}" var="chatroom">              
                        
             	<a href="<c:url value="/chatRoom/join/${chatroom.key}"/>">${chatroom.value.name}</a> 
             	<br/>
            </c:forEach>
        </c:otherwise>
    </c:choose>
  
 
  </jsp:attribute>

</template:menu>




