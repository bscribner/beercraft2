<%-- This file was derived from appfuse: https://github.com/appfuse/appfuse/blob/master/web/spring/src/main/webapp/common/messages.jsp --%>
<%@ include file="/common/taglibs.jsp"%>

<script>
   $(function() {	   
   		$("#messages a").click(function (event) {
      		event.preventDefault();
          	$(".alert").hide();
      	});
   });
</script>

<div id="messages">
<%-- Error Messages --%>
<c:if test="${not empty errorMessages}">
    <div class="alert alert-error">
        <a href="#" class="close">&times;</a>
        <c:forEach var="error" items="${errorMessages}">
            <c:out value="${error}"/><br />
        </c:forEach>
    </div>
    <c:remove var="errorMessages" scope="session"/>
</c:if>

<%-- Success Messages --%>
<c:if test="${not empty successMessages}">
    <div class="alert alert-success">
        <a href="#" class="close">&times;</a>
        <c:forEach var="msg" items="${successMessages}">
            <c:out value="${msg}"/><br />
        </c:forEach>
    </div>
    <c:remove var="successMessages" scope="session"/>
</c:if>
</div>