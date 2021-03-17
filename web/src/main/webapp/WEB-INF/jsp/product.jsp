   <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
   <jsp:include page="header.jsp"/>

   <h1>Product Details</h1>

    <table class="table">

         <tbody>

           <tr>

             <td>${product.productId}</td>
             <td>${product.productName}</td>
             <td>${product.description}</td>
           </tr>

         </tbody>
       </table>


   <jsp:include page="footer.jsp"/>