<%@ include file="/common/taglibs.jsp"%>

<jsp:include page="../header.jsp" />

<script>
   $(document).ready(function(){
         $("#admin_users").addClass("active");
   });
</script>

<fieldset>
   <legend><h2>Users</h2></legend>
   <input type="button" onclick="location.href='<c:url value='/admin/userform' />'" value="Add" />
   <input type="button" onclick="location.href='<c:url value='/main' />'" value="Done" />
   
   <br/><br/>
   
   <display:table name="users" class="list" cellspacing="0" cellpadding="0"
                      defaultsort="1" id="users" pagesize="25">
        <display:column property="username" escapeXml="true" sortable="true" title="Username" style="width: 25%"
                        url="/admin/userform" paramId="id" paramProperty="id"/>
        <display:column property="person.fullName" escapeXml="true" sortable="true" title="Full Name"
                        style="width: 34%"/>
        <display:column property="person.email" sortable="true" title="Email" style="width: 25%" autolink="true"/>
        <display:column sortProperty="enabled" sortable="true" title="Enabled"
                        style="width: 16%; padding-left: 15px">
            <input type="checkbox" disabled="disabled" <c:if test="${users.enabled}">checked="checked"</c:if>/>
        </display:column>
   
        <display:setProperty name="paging.banner.item_name" value="user"/>
        <display:setProperty name="paging.banner.items_name" value="users"/>
   </display:table>
</fieldset>

<jsp:include page="../footer.jsp" />
