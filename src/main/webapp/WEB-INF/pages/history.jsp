<%@ include file="/common/taglibs.jsp"%>

<jsp:include page="baseHeader.jsp" />

<script>
   $(document).ready(function(){
         $("#history").addClass("active");
   });
</script>

<head>
    <title>Account Information</title>
</head>

<div class="tabs">
   <ul>
      <li id="home"><a href="<c:url value='/main'/>">Home</a></li>
      <li id="history"><a href="#history">Order History</a></li>
   </ul>
</div>

<div class="content">
   <fieldset>
      <legend><h2>Your Orders</h2></legend>
   
      <display:table name="orders" id="order" class="list" pagesize="25">
         <display:column property="orderNumber" title="Order Number" style="width: 50%; text-align: center;" url="/summary" paramId="orderId"
            paramProperty="id" />
         <display:column title="Total Cost" style="width: 50%; text-align: center;">
            <fmt:formatNumber value="${order.totalCost}" type="currency"/>
         </display:column>
      
         <display:setProperty name="paging.banner.item_name" value="order" />
         <display:setProperty name="paging.banner.items_name" value="orders" />
      </display:table>
   </fieldset>
</div>

<div class="userPanel">
   <jsp:include page="userPanel.jsp" />
</div>
<div class="footer"></div>

<jsp:include page="baseFooter.jsp" />