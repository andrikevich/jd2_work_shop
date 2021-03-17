<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
   <jsp:include page="header.jsp"/>


    <h1> Please take a look at our promo</h1>
<h2>We have ${promoAndProduct.promoCount} available promo </h2>
<h2>We have ${promoAndProduct.productCount} available products </h2>
    <!--PromoProduct List -->

    <table class="table">
      <thead>
        <tr>
          <th scope="col">#</th>
          <th scope="col">Promo code</th>
          <th scope="col">Description</th>
        </tr>
      </thead>
      <tbody>
      <c:forEach var="promoItem" items="${promoList}">
        <tr>
          <th scope="row">#</th>
          <td>${promoItem.promoId}</td>
          <td>${promoItem.description}</td>
        </tr>
        </c:forEach>
      </tbody>
    </table>

   <jsp:include page="footer.jsp"/>