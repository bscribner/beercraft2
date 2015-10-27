<%@ include file="/common/taglibs.jsp"%>

<jsp:include page="baseHeader.jsp" />

<script>
   $(document).ready(function(){
         $("#summary").addClass("active");
   });
</script>

<div class="tabs">
   <ul>
      <li id="home"><a href="<c:url value='/main'/>">Home</a></li>
      <li id="summary"><a href="#summary">Order Summary</a></li>
   </ul>
</div>

<jsp:include page="messages.jsp" />

<div class="content">
   <fieldset>
      <legend><h2>Order# <c:out value="${order.orderNumber}"/></h2></legend>
   
      <display:table name="order.lineItems" class="list" id="orderItem" pagesize="25">
         <display:column property="beer.name" sortable="true" title="Name" style="width: 75%" url="/item" paramId="id"
            paramProperty="id" />
         <display:column property="quantity" title="Quantity" style="width: 15%; text-align: center;" />
         
         <display:column title="Cost" style="width: 10%; text-align: center;">
            <fmt:formatNumber value="${orderItem.totalCost}" type="currency"/>
         </display:column>
         
         <display:footer>
            <tr>
              <td colspan="2"><b>Total</b></td>
              <td align="center"><fmt:formatNumber value="${order.totalCost}" type="currency"/></td>
            </tr>
         </display:footer>
      
         <display:setProperty name="paging.banner.item_name" value="item" />
         <display:setProperty name="paging.banner.items_name" value="items" />
      </display:table>
   </fieldset>
</div>

<div class="userPanel">
   <jsp:include page="userPanel.jsp" />
</div>
<div class="footer"></div>

<jsp:include page="baseFooter.jsp" />