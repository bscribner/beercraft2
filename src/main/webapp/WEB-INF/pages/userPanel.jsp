<%@ include file="/common/taglibs.jsp"%>

<security:authorize access="isAuthenticated()" var="authenticated" />

<c:choose>
   <c:when test="${not authenticated}">
      You are not logged in.<br/>
      <a href="<c:url value='/login' />">Login here</a><br/>
      or<br/>
      <a href="<c:url value='/account' />">Create an account</a>
   </c:when>
   <c:otherwise>
     Welcome <a href="<c:url value='/account' />"><c:out value="${pageContext.request.remoteUser}" /></a>!
     
     <br /><hr style="width: 80%;" />
     
     <a href="<c:url value='/checkout' />">checkout</a>
     
     <br /><hr style="width: 80%;" />
     
     <a href="<c:url value='/history' />">order history</a>
     
     <br /><hr style="width: 80%;" />
     
     <a href="<c:url value='/account' />">edit account info</a>
     
     <br /><hr style="width: 80%;" />
     
     <a href="<c:url value='/logout' />">logout</a>
     
   </c:otherwise>
</c:choose>