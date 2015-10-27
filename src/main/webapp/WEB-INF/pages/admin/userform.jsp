<%@ include file="/common/taglibs.jsp"%>

<jsp:include page="../header.jsp" />

<script>
   $(document).ready(function(){
         $("#admin_users").addClass("active");
   });
</script>

<form:form modelAttribute="user" method="post" action="userform" class="userform">
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
         
         <li>
            <div class="formLabel"><label for="enabled">Enabled</label></div>
            <form:checkbox path="enabled" id="enabled" />
            <form:errors path="enabled" cssClass="validationError"/>
         </li>
         
         <li>
            <div class="formLabel"><label for="userRoles">Roles</label></div>
            <select id="userRoles" name="userRoles" multiple="true">
               <c:forEach items="${roles}" var="role">
                  <option value="${role.id}" ${fn:contains(user.roles, role.name) ? 'selected' : ''}>${role.name}</option>
               </c:forEach>
            </select>
         </li>
      </ul>
   </fieldset>
   
   <fieldset>
      <legend><h2>Personal Information</h2></legend>
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
      <input type="submit" name="save" value="Save" />

      <c:if test="${not empty user.id}">
         <input type="submit" name="delete" value="Delete" />
      </c:if>

      <input type="submit" name="cancel" value="Cancel" />
   </div>
</form:form>

<jsp:include page="../footer.jsp" />
