<%@ include file="/common/taglibs.jsp"%>

<jsp:include page="header.jsp" />

<script>
   $(document).ready(function(){
         $("#shop").addClass("active");
   });
   
   $(function() {
	   $('input[id="quantity"]').spinner({ min: 0, max: 10});
   });
</script>

<fieldset>
   <legend><h2><c:out value="${beer.name}" /></h2></legend>
   <div class="beer">
      <ul class="formlist">
   
         <li>
            <div class="formLabel">
               <label for="description">Description:</label>
            </div>
            <div class="formValue">&nbsp;<c:out value="${beer.description}" /></div>
         </li>
   
         <li>
            <div class="formLabel">
               <label for="description">Price:</label>
            </div>
            <div class="formValue">&nbsp;$<c:out value="${beer.price}" /></div>
         </li>
   
         <li>
            <div class="formLabel">
               <label for="category">Category:</label>
            </div>
            <div class="formValue">&nbsp;<c:out value="${beer.category.name}" /></div>
         </li>
   
         <li>
            <div class="formLabel">
               <label for="brewery">Brewery:</label>
            </div>
            <div class="formValue">&nbsp;<c:out value="${beer.brewery.name}" /></div>
         </li>
   
         <li>
            <div class="formLabel">
               <label for="origin">Origin:</label>
            </div>
            <div class="formValue">&nbsp;<c:out value="${beer.origin.name}" /></div>
         </li>
      </ul>
   </div>
   <div>
      <form:form modelAttribute="lineItem" method="post" action="beer" id="lineItemForm">
         <form:hidden path="beer.id" />
   
         <ul class="formlist">
            <li>
               <div class="formLabel">
                  <label for="quantity">Quantity</label>
               </div>
               <form:input path="quantity" id="quantity" />
            </li>
         </ul>
   
         <div class="form-actions">
            <input type="submit" name="save" value="Add to Cart" />
         </div>
      </form:form>
   </div>
</fieldset>

<jsp:include page="footer.jsp" />