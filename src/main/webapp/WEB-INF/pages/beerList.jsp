<%@ include file="/common/taglibs.jsp"%>

<jsp:include page="header.jsp" />

<script>
   $(document).ready(function(){
         $("#shop").addClass("active");
   });
</script>

<fieldset>
   <legend><h2>Beer List</h2></legend>

   <display:table name="beerList" class="list" pagesize="25">
      <display:column property="name" escapeXml="true" sortable="true" title="Name" style="width: 25%" url="/beer" paramId="id"
         paramProperty="id" />
      <display:column property="description" escapeXml="true" sortable="true" title="Description" maxLength="50" style="width: 50%" />
   
      <display:setProperty name="paging.banner.item_name" value="beer" />
      <display:setProperty name="paging.banner.items_name" value="beer" />
   </display:table>
</fieldset>

<jsp:include page="footer.jsp" />