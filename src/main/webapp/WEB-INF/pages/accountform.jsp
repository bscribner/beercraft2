<%@ include file="/common/taglibs.jsp"%>

<jsp:include page="baseHeader.jsp" />

<script>
   $(document).ready(function(){
         $("#account").addClass("active");
   });
</script>

<head>
    <title>Account Information</title>
</head>

<div class="tabs">
   <ul>
      <li id="home"><a href="<c:url value='/main'/>">Home</a></li>
      <li id="account"><a href="#account">Account</a></li>
   </ul>
</div>

<div class="content">

<jsp:include page="messages.jsp" />

<form:form modelAttribute="user" method="post" action="account" class="userform">
   <form:hidden path="id" />

   <fieldset>
      <legend><h2>User Information</h2></legend>
      <ul class="formlist">
         <li>
            <div class="formLabel"><label for="username">Username</label></div>
            <form:input path="username" id="username" />
            <form:errors path="username" cssClass="validationError"/>
         </li>
      
         <li>
            <div class="formLabel"><label for="person.email">E-mail</label></div>
            <form:input path="person.email" id="email" />
            <form:errors path="person.email" cssClass="validationError"/>
         </li>
         
         <li>
            <div class="formLabel"><label for=password>Password</label></div>
            <form:password path="password" id="password" showPassword="true" />
            <form:errors path="password" cssClass="validationError"/>
         </li>
         
         <li>
            <div class="formLabel"><label for="confirmPassword">Confirm Password</label></div>
            <form:password path="confirmPassword" id="confirmPassword" showPassword="true" />
            <form:errors path="confirmPassword" cssClass="validationError"/>
         </li>
      </ul>
   </fieldset>
   
   <fieldset>
      <legend><h2>About You</h2></legend>
      <ul class="formlist">
         <li>
            <div class="formLabel"><label for="person.firstName">First Name</label></div>
            <form:input path="person.firstName" id="firstName" maxlength="50" />
            <form:errors path="person.firstName" cssClass="validationError"/>
         </li>
         
         <li>
            <div class="formLabel"><label for="person.lastName">Last Name</label></div>
            <form:input path="person.lastName" id="lastName" maxlength="50" />
            <form:errors path="person.lastName" cssClass="validationError"/>
         </li>
         
         <li>
            <div class="formLabel"><label for="person.phoneNumber">Phone Number</label></div>
            <form:input path="person.phoneNumber" id="phoneNumber" />
            <form:errors path="person.phoneNumber" cssClass="validationError"/>
         </li>
      </ul>
   </fieldset>
   
   <fieldset>
      <legend><h2>Address</h2></legend>
      <ul class="formlist">
         <li>
            <div class="formLabel"><label for="person.address.address1">Address</label></div>
            <form:input path="person.address.address1" id="address.address1" />
            <form:errors path="person.address.address1" cssClass="validationError"/>
         </li>
         
         <li>
            <div class="formLabel">&nbsp;</div>
            <form:input path="person.address.address2" id="address.address2" />
            <form:errors path="person.address.address2" cssClass="validationError"/>
         </li>
         
         <li>
            <div class="formLabel">&nbsp;</div>
            <form:input path="person.address.address3" id="address.address3" />
            <form:errors path="person.address.address3" cssClass="validationError"/>
         </li>
         
         <li>
            <div class="formLabel"><label for="person.address.city">City</label></div>
            <form:input path="person.address.city" id="address.city" />
            <form:errors path="person.address.city" cssClass="validationError"/>
         </li>
         
         <li>
            <div class="formLabel"><label for="person.address.state">State</label></div>
            <form:input path="person.address.state" id="address.province" />
            <form:errors path="person.address.state" cssClass="validationError"/>
         </li>
         
         <li>
            <div class="formLabel"><label for="person.address.postalCode">Postal Code</label></div>
            <form:input path="person.address.postalCode" id="address.postalCode" />
            <form:errors path="person.address.postalCode" cssClass="validationError"/>
         </li>
         
         <li>
            <div class="formLabel"><label for="person.address.country">Country</label></div>
            <form:input path="person.address.country" id="address.country" />
            <form:errors path="person.address.country" cssClass="validationError"/>
         </li>
      </ul>
   </fieldset>

   <div class="form-actions">
      <c:choose>
         <c:when test="${empty user.id}">
            <input type="submit" name="save" value="Create Account and Log In" />
         </c:when>
         <c:otherwise>
            <input type="submit" name="save" value="Save Changes" />
         </c:otherwise>
      </c:choose>

      <input type="submit" name="cancel" value="Cancel" />
   </div>
</form:form>
</div>

<div class="userPanel">
   <jsp:include page="userPanel.jsp" />
</div>
<div class="footer"></div>

<jsp:include page="baseFooter.jsp" />