<%@ include file="/common/taglibs.jsp"%>

<jsp:include page="header.jsp" />

<script>
   $(document).ready(function(){
         $("#shop").addClass("active");
   });
   
   $(function() {
       $("#shopAccordian").accordion({
    	   collapsible: true,
    	   heightStyle: "content" 
       });
   });
</script>

<div id="shopAccordian" class="shop">
   <h3>Find Beer by Brewery</h3>
   <div id="byBrewery">
      <div>
         <display:table name="breweries" id="breweries" class="list" pagesize="25" style="border: none;">
            <display:column property="name" title="Name" url="/beerlist" paramId="brewery" paramProperty="id" />
         
            <display:setProperty name="basic.show.header" value="false" />
            <display:setProperty name="paging.banner.item_name" value="brewery" />
            <display:setProperty name="paging.banner.items_name" value="breweries" />
         </display:table>
      </div>
   </div>

   <h3>Find Beer by Category</h3>
   <div id="byCategory">
      <div>
         <display:table name="categories" id="categories" class="list" pagesize="25" style="border: none;">
            <display:column property="name" title="Name" url="/beerlist" paramId="category" paramProperty="id" />
         
            <display:setProperty name="basic.show.header" value="false" />
            <display:setProperty name="paging.banner.item_name" value="category" />
            <display:setProperty name="paging.banner.items_name" value="categories" />
         </display:table>
      </div>
   </div>

   <h3>Find Beer by Origin</h3>
   <div id="byOrigin">
      <div>
         <display:table name="origins" id="origins" class="list" pagesize="25" style="border: none;">
            <display:column property="name" title="Name" url="/beerlist" paramId="origin" paramProperty="id" />
         
            <display:setProperty name="basic.show.header" value="false" />
            <display:setProperty name="paging.banner.item_name" value="origin" />
            <display:setProperty name="paging.banner.items_name" value="origins" />
         </display:table>
      </div>
   </div>
</div>

<jsp:include page="footer.jsp" />