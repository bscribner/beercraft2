<%@ include file="/common/taglibs.jsp"%>

<jsp:include page="header.jsp" />

<script>
   $(document).ready(function(){
         $("#contact").addClass("active");
   });
</script>

<link rel="stylesheet" type= "text/css" href="style/contact.css"/>
<p>Contact us for information or suggestions on new ales to add to
   our inventory!</p>
<form action="" method="get">
   Email: <br>
   <input type="text" name="contactEmail" /><br> Name: <br>
   <input type="text" name="contactName" /><br>
   <textarea rows="5" cols="50" name="contactContent"></textarea>
   <br> <input type="SUBMIT" value="submit" /> <input type="RESET"
      value="cancel" />

   <table align="center">
      <tr>
         <td align="center" class="contact">555 Parks Way</td>
      </tr>
      <tr>
         <td align="center" class="contact">New York, NY</td>
      </tr>
      <tr>
         <td align="center" class="contact">910.867.5309</td>
      </tr>
   </table>
</form>

<jsp:include page="footer.jsp" />