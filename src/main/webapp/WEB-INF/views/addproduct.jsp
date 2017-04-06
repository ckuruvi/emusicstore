
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@include file="/WEB-INF/views/template/header.jsp"%>


<div class="container-wrapper">
    <div class="container">
        <div class="page-header">
            <h1>Add Product</h1>

            <p class="lead"> Fill in the product information </p>
        </div>

        <form:form action="${pageContext.request.contextPath}/admin/productinventory/addproduct" method="post"
                   commandName="product" enctype="multipart/form-data">

        <div class="form-group">
            <label for="name">Name</label>
            <form:input path="productName" id="name" class="form-control" />

        </div>

        <div class="form-group">
            <label for="name">Category</label>
            <label class="checkbox-inline">
                <form:radiobutton path="productCategory" id="category"
                            value="instrument"/>Instrument</label>
            <label class="checkbox-inline">
                <form:radiobutton path="productCategory" id="category"
                                  value="record"/>Record</label>
            <label class="checkbox-inline">
                <form:radiobutton path="productCategory" id="category"
                                  value="accessary"/>Accessary</label>

        </div>

        <div class="form-group">
            <label for="description">Description</label>
            <form:textarea path="productDescription" id="description" class="form-control" />

        </div>
        <div class="form-group">
            <label for="price">Price</label>
            <form:input path="productPrice" id="price" class="form-control" />

        </div>

        <div class="form-group">
        <label for="condition">Condition</label>
        <label class="checkbox-inline">
            <form:radiobutton path="productCondition" id="condition"
                              value="new"/>New</label>
        <label class="checkbox-inline">
            <form:radiobutton path="productCondition" id="condition"
                              value="used"/>Used</label>

    </div>

        <div class="form-group">
            <label for="status">Status</label>
            <label class="checkbox-inline">
                <form:radiobutton path="productStatus" id="status"
                                  value="active"/>Active</label>
            <label class="checkbox-inline">
                <form:radiobutton path="productStatus" id="status"
                                  value="inactive"/>Inactive</label>

        </div>

        <div class="form-group">
            <label for="unitinstock">Unit in Stock</label>
            <form:input path="unitInStock" id="unitinstock" class="form-control" />

        </div>
        <div class="form-group">
            <label for="manufacturer">Manufacturer</label>
            <form:input path="productManufacturer" id="manufacturer" class="form-control" />

        </div>

        <div class="form-group">
            <label class="control-label" for="productimage">Upload Picture</label>
            <form:input path="productImage" id="productimage" type="file" class="form:input-large" />

        </div>

        <input type="submit" class="btn btn-default">
        <a href="<c:url value="/admin/productinventory" />" class="btn btn-default">Cancel</a>

        </form:form>


        <%@include file="/WEB-INF/views/template/footer.jsp"%>
