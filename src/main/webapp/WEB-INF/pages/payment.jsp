<%@ include file="/common/taglibs.jsp"%>

<jsp:include page="baseHeader.jsp" />

<script>
   $(document).ready(function(){
         $("#payment").addClass("active");
   });
   
   // Date function found here: http://stackoverflow.com/questions/2208480/jquery-ui-datepicker-to-show-month-year-only
   $(function() {
	   $('input[id="expirationDate"]').datepicker({
		   changeMonth: true,
	       changeYear: true,
	       showButtonPanel: true,
	       dateFormat: 'mm/yy',
	       onClose: function(dateText, inst) { 
	            var month = $("#ui-datepicker-div .ui-datepicker-month :selected").val();
	            var year = $("#ui-datepicker-div .ui-datepicker-year :selected").val();
	            $(this).datepicker('setDate', new Date(year, month, 1));
	       }
	   });
   });
</script>

<style>.ui-datepicker-calendar { display: none; }</style>

<div class="tabs">
   <ul>
      <li id="home"><a href="<c:url value='/main'/>">Home</a></li>
      <li id="payment"><a href="#payment">Payment</a></li>
   </ul>
</div>

<jsp:include page="messages.jsp" />

<div>
   <form:form modelAttribute="order" method="post" action="payment" class="paymentform">
      <form:hidden path="id" />
   
      <fieldset>
         <legend><h2>Credit Card Information</h2></legend>
         <ul class="formlist">
            
            <li>
               <div class="formLabel"><label for="type">Type</label></div>
               <form:select path="cardNumber.type" id="type">
                  <form:option value="0" label="Select" />
                  <form:options items="${cardTypes}" itemLabel="name" />
               </form:select>
               <form:errors path="cardNumber.type" cssClass="validationError"/>
            </li>
            <li>
               <div class="formLabel"><label for="number">Credit Card Number</label></div>
               <form:input path="cardNumber.number" id="number" />
               <form:errors path="cardNumber.number" cssClass="validationError"/>
            </li>
         
            <li>
               <div class="formLabel"><label for="expirationDate">Expiration Date</label></div>
               <form:input path="cardNumber.expirationDate" id="expirationDate" readonly="true" />
               <form:errors path="cardNumber.expirationDate" cssClass="validationError"/>
            </li>
         </ul>
      </fieldset>
      
      <fieldset>
         <legend><h2>Billing Address</h2></legend>
         <ul class="formlist">
            <li>
               <div class="formLabel"><label for="cardNumber.billingAddress.address1">Address</label></div>
               <form:input path="cardNumber.billingAddress.address1" id="billingAddress.address1" />
            </li>
            
            <li>
               <div class="formLabel">&nbsp;</div>
               <form:input path="cardNumber.billingAddress.address2" id="billingAddress.address2" />
            </li>
            
            <li>
               <div class="formLabel">&nbsp;</div>
               <form:input path="cardNumber.billingAddress.address3" id="billingAddress.address3" />
            </li>
            
            <li>
               <div class="formLabel"><label for="cardNumber.billingAddress.city">City</label></div>
               <form:input path="cardNumber.billingAddress.city" id="billingAddress.city" />
            </li>
            
            <li>
               <div class="formLabel"><label for="cardNumber.billingAddress.state">State</label></div>
               <form:input path="cardNumber.billingAddress.state" id="billingAddress.province" />
            </li>
            
            <li>
               <div class="formLabel"><label for="cardNumber.billingAddress.postalCode">Postal Code</label></div>
               <form:input path="cardNumber.billingAddress.postalCode" id="billingAddress.postalCode" />
            </li>
            
            <li>
               <div class="formLabel"><label for="cardNumber.billingAddress.country">Country</label></div>
               <form:input path="cardNumber.billingAddress.country" id="billingAddress.country" />
            </li>
         </ul>
      </fieldset>
      
      <fieldset>
         <legend><h2>Shipping Address</h2></legend>
         <ul class="formlist">
            <li>
               <div class="formLabel"><label for="shipping.address1">Address</label></div>
               <form:input path="shipping.address1" id="shipping.address1" />
            </li>
            
            <li>
               <div class="formLabel">&nbsp;</div>
               <form:input path="shipping.address2" id="shipping.address2" />
            </li>
            
            <li>
               <div class="formLabel">&nbsp;</div>
               <form:input path="shipping.address3" id="shipping.address3" />
            </li>
            
            <li>
               <div class="formLabel"><label for="shipping.city">City</label></div>
               <form:input path="shipping.city" id="shipping.city" />
            </li>
            
            <li>
               <div class="formLabel"><label for="shipping.state">State</label></div>
               <form:input path="shipping.state" id="shipping.province" />
            </li>
            
            <li>
               <div class="formLabel"><label for="shipping.postalCode">Postal Code</label></div>
               <form:input path="shipping.postalCode" id="shipping.postalCode" />
            </li>
            
            <li>
               <div class="formLabel"><label for="shipping.country">Country</label></div>
               <form:input path="shipping.country" id="shipping.country" />
            </li>
         </ul>
      </fieldset>
   
      <div class="form-actions">
         <input type="submit" name="save" value="Process Order" />
   
         <input type="submit" name="cancel" value="Cancel" />
      </div>
   </form:form>
</div>

<jsp:include page="baseFooter.jsp" />