<%@ include file="/common/taglibs.jsp" %>

<jsp:include page="../header.jsp" />

<script>
   $(document).ready(function(){
   		$("#admin_breweries").addClass("active");
   });
</script>

<fieldset>
   <legend><h2>Breweries</h2></legend>
   <input type="button" onclick="location.href='<c:url value='/admin/breweryform' />'" value="Add" />
   <input type="button" onclick="location.href='<c:url value='/main' />'" value="Done" />
   
   <br/><br/>
   
   <display:table name="breweries" class="list" cellspacing="0" cellpadding="0" requestURI="" defaultsort="1" id="breweries" pagesize="25">
      <display:column property="name" escapeXml="true" sortable="true" title="Name" style="width: 25%" url="/admin/breweryform" paramId="id"
         paramProperty="id" />
      <display:column property="description" escapeXml="true" sortable="true" title="Description" maxLength="50" style="width: 50%" />
   
      <display:setProperty name="paging.banner.item_name" value="brewery" />
      <display:setProperty name="paging.banner.items_name" value="breweries" />
   </display:table>
</fieldset>

<jsp:include page="../footer.jsp" />