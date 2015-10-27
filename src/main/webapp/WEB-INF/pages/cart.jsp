<%@ include file="/common/taglibs.jsp"%>

<jsp:include page="header.jsp" />

<script>
   $(document).ready(function(){
         $("#cart").addClass("active");
   });
</script>

<fieldset>
   <legend><h2>Your Cart</h2></legend>

   <display:table name="cart.lineItems" class="list" id="item" pagesize="25">
      <display:column property="beer.name" sortable="true" title="Name" style="width: 65%" url="/item" paramId="id"
         paramProperty="id" />
      <display:column property="quantity" title="Quantity" style="width: 15%; text-align: center;" />
      
      <display:column title="Cost" style="width: 10%; text-align: center;">
         <fmt:formatNumber value="${item.totalCost}" type="currency"/>
      </display:column>
      
      <display:column style="width: 10%; text-align: center;" url="/cart" paramId="lineItemId" paramProperty="id">
         remove
      </display:column>
      
      <display:footer>
         <tr>
           <td colspan="2"><b>Total</b></td>
           <td align="center"><fmt:formatNumber value="${cart.totalCost}" type="currency"/></td>
           <td></td>
         </tr>
      </display:footer>
   
      <display:setProperty name="paging.banner.item_name" value="item" />
      <display:setProperty name="paging.banner.items_name" value="items" />
   </display:table>
   
   <br/><br/>
   
   <input type="button" onclick="location.href='<c:url value='/checkout' />'" value="Checkout" />
</fieldset>

<jsp:include page="footer.jsp" />