
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@include file="/WEB-INF/views/template/header.jsp"%>

<script>
    $(document).ready(function() {

        $('.table').DataTable({
            'lengthMenu':[[1,2,3,5,10,-1],[1,2,3,5,10,'All']]
        });
    });
</script>

<div class="container-wrapper">
    <div class="container">
        <div class="page-header">
            <h1>Product Inventory Page</h1>

            <p class="lead"> This is a product inventory page </p>
        </div>

        <table class="table table-striped table-hover">
            <thead>
            <tr class="bg-success">
                <th>Photo Thumb</th>
                <th>Product Name</th>
                <th>Category</th>
                <th>Condition</th>
                <th>Price</th>
                <th></th>
            </tr>
            </thead>
            <c:forEach items="${products}" var="product">
                <tr>
                    <td><img src="<c:url value="/resources/images/${product.productId}.png"/>"
                             alt="image" style="width: 100%"></td>
                    <td>${product.productName}</td>
                    <td>${product.productCategory}</td>
                    <td>${product.productCondition}</td>
                    <td>${product.productPrice} USD</td>
                    <td><a href="<spring:url value="/product/viewproduct/${product.productId}" />"
                    ><span class="glyphicon glyphicon-info-sign" /></a>
                        <a href="<spring:url value="/admin/product/deleteproduct/${product.productId}" />"
                        ><span class="glyphicon glyphicon-trash" /></a>
                        <a href="<spring:url value="/admin/product/editproduct/${product.productId}" />"
                        ><span class="glyphicon glyphicon-pencil" /></a>
                    </td>

                </tr>
            </c:forEach>
        </table>
        <a href="<spring:url value="/admin/product/addproduct" />" class="btn btn-primary">Add Product</a>
        <%@include file="/WEB-INF/views/template/footer.jsp"%>
