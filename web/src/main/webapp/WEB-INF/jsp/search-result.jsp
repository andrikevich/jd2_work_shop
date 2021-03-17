<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
   <jsp:include page="header.jsp"/>


    <h1> Founded products</h1>

    <!--PromoProduct List -->

    <table class="table">
      <thead>
        <tr>
          <th scope="col">#</th>
          <th scope="col">Product Id</th>
          <th scope="col">Product Name</th>
          <th scope="col">Description</th>
        </tr>
      </thead>
      <tbody>
      <c:forEach var="productItem" items="${searchResult}">
        <tr>
          <th scope="row"><a href="${pageContext.request.contextPath}/product/${productItem.productId}">Open</a></th>
          <td>${productItem.productId}</td>
          <td>${productItem.productName}</td>
          <td>${productItem.description}</td>
        </tr>
        </c:forEach>
      </tbody>
    </table>

       <jsp:include page="footer.jsp"/>