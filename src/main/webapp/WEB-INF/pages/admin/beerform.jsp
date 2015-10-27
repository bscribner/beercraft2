<%@ include file="/common/taglibs.jsp"%>

<jsp:include page="../header.jsp" />

<script>
   $(document).ready(function(){
         $("#admin_beer").addClass("active");
   });
</script>

<fieldset>
   <legend><h2>Beer Form</h2></legend>
   <div>
      <form:form modelAttribute="beer" method="post" action="beerform" id="beerForm">
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
            
            <li>
               <div class="formLabel"><label for="alcoholContent">ABV</label></div>
               <form:input path="alcoholContent" id="alcoholContent" />
               <form:errors path="alcoholContent" cssClass="validationError"/>
            </li>
            
            <li>
               <div class="formLabel"><label for="price">Price</label></div>
               <form:input path="price" id="price" />
               <form:errors path="price" cssClass="validationError"/>
            </li>
            
            <li>
               <div class="formLabel"><label for="category">Category</label></div>
               <form:select path="category" id="category">
                  <form:option value="0" label="Select" />
                  <form:options items="${categories}" itemValue="id" itemLabel="name" />
               </form:select>
               <form:errors path="category" cssClass="validationError"/>
            </li>
            
            <li>
               <div class="formLabel"><label for="brewery">Brewery</label></div>
               <form:select path="brewery" id="brewery">
                  <form:option value="0" label="Select" />
                  <form:options items="${breweries}" itemValue="id" itemLabel="name" />
               </form:select>
               <form:errors path="brewery" cssClass="validationError"/>
            </li>
            
            <li>
               <div class="formLabel"><label for="origin">Origin</label></div>
               <form:select path="origin" id="origin">
                  <form:option value="0" label="Select" />
                  <form:options items="${origins}" itemValue="id" itemLabel="name" />
               </form:select>
               <form:errors path="origin" cssClass="validationError"/>
            </li>
         </ul>
   
   
         <div class="form-actions">
            <input type="submit" name="save" value="Save" />
   
            <c:if test="${not empty beer.id}">
               <input type="submit" name="delete" value="Delete" />
            </c:if>
   
            <input type="submit" name="cancel" value="Cancel" />
         </div>
      </form:form>
   </div>
</fieldset>

<jsp:include page="../footer.jsp" />