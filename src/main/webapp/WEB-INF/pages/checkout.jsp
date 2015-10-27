<%@ include file="/common/taglibs.jsp"%>

<jsp:include page="baseHeader.jsp" />

<script>
   $(document).ready(function(){
         $("#checkout").addClass("active");
   });
</script>

<div class="tabs">
   <ul>
      <li id="home"><a href="<c:url value='/main'/>">Home</a></li>
      <li id="checkout"><a href="#checkout">Checkout</a></li>
   </ul>
</div>

<fieldset>
   <legend><h2>Items in Your Cart</h2></legend>

   <display:table name="cart.lineItems" class="list" id="cartItem" pagesize="25">
      <display:column property="beer.name" sortable="true" title="Name" style="width: 75%" url="/item" paramId="id"
         paramProperty="id" />
      <display:column property="quantity" title="Quantity" style="width: 15%; text-align: center;" />
      
      <display:column title="Cost" style="width: 10%; text-align: center;">
         <fmt:formatNumber value="${cartItem.totalCost}" type="currency"/>
      </display:column>
      
      <display:footer>
         <tr>
           <td colspan="2"><b>Total</b></td>
           <td align="center"><fmt:formatNumber value="${cart.totalCost}" type="currency"/></td>
         </tr>
      </display:footer>
   
      <display:setProperty name="paging.banner.item_name" value="item" />
      <display:setProperty name="paging.banner.items_name" value="items" />
   </display:table>
   
   <br/><br/>
   
   <c:if test="${fn:length(cart.lineItems) gt 0}">
      <input type="button" onclick="location.href='<c:url value='/payment' />'" value="Continue to Pay" />
   </c:if>
</fieldset>

<jsp:include page="baseFooter.jsp" />