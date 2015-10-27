<%@ include file="/common/taglibs.jsp"%>

<ul>
   <li id="main"><a href="<c:url value='/main' />">Home</a></li>
   <li id="shop"><a href="<c:url value='/shop' />">Shop</a></li>
   <li id="cart"><a href="<c:url value='/cart' />">Cart</a></li>
   <li id="about"><a href="<c:url value='/about' />">About</a></li>
   <li id="contact"><a href="<c:url value='/contact' />">Contact</a></li>
   <security:authorize access="hasRole('ROLE_ADMIN')">
      <li id="admin_beer"><a href="<c:url value='/admin/beerlist' />">Beer</a></li>
      <li id="admin_breweries"><a href="<c:url value='/admin/breweries' />">Breweries</a></li>
      <li id="admin_categories"><a href="<c:url value='/admin/categories' />">Categories</a></li>
      <li id="admin_origins"><a href="<c:url value='/admin/origins' />">Origins</a></li>
      <li id="admin_users"><a href="<c:url value='/admin/users' />">Users</a></li>
   </security:authorize>
</ul>