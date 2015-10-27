<%@ include file="/common/taglibs.jsp"%>

<jsp:include page="baseHeader.jsp" />

<script>
   $(document).ready(function(){
         $("#login").addClass("active");
   });
</script>

<div class="tabs">
   <ul>
      <li id="home"><a href="<c:url value='/main'/>">Home</a></li>
      <li id="login"><a href="#login">Login</a></li>
   </ul>
</div>

<c:if test="${param.error != null}">
   <div class="alert alert-error">
      The user name or password you entered was incorrect, please try again.
   </div>
</c:if>

<div class="loginForm">
   <security:authorize access="isAuthenticated()" var="authenticated" />
    
   <c:choose>
      <c:when test="${not authenticated}">
         <form method="post" action="<c:url value='/j_security_check'/>">
            <label for="j_username">Username</label>
            <div>
               <input type="text" name="j_username" id="j_username" class="loginText" />
            </div>
            <label for="j_password">Password</label>
            <div>
               <input type="password" name="j_password" id="j_password" class="loginText" />
            </div>
            <input type="submit" name="login" value="login" />
            <br/>
            <a href="<c:url value='/account' />">create an account</a>
         </form>
      </c:when>
      <c:otherwise>
        You are already logged in. Would you like to <a href="<c:url value='/logout' />">logout</a>?
      </c:otherwise>
   </c:choose>
</div>

<jsp:include page="baseFooter.jsp" />