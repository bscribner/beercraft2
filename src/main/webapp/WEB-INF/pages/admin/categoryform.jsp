<%@ include file="/common/taglibs.jsp"%>

<jsp:include page="../header.jsp" />

<script>
   $(document).ready(function(){
         $("#admin_categories").addClass("active");
   });
</script>

<fieldset>
   <legend><h2>Category Form</h2></legend>
   <div>
      <form:form modelAttribute="category" method="post" action="categoryform" id="categoryForm">
         <form:hidden path="id" />
         
         <ul class="formlist">
            <li>
               <div class="formLabel"><label for="name">Name</label></div>
               <form:input path="name" id="name" />
               <form:errors path="name" cssClass="validationError"/>
            </li>
            
            <li>
               <div class="formLabel"><label for="description">Description</label></div>
               <form:textarea path="description" id="description" />
               <form:errors path="description" cssClass="validationError"/>
            </li>
         </ul>
   
         <div class="form-actions">
            <input type="submit" name="save" value="Save" />
   
            <c:if test="${not empty category.id}">
               <input type="submit" name="delete" value="Delete" />
            </c:if>
   
            <input type="submit" name="cancel" value="Cancel" />
         </div>
      </form:form>
   </div>
</fieldset>

<jsp:include page="../footer.jsp" />