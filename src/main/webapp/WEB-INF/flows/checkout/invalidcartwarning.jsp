<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@include file="/WEB-INF/views/template/header.jsp"%>

<div class="container-wrapper">
    <div class="container">
        <section>
            <div class="jambotron">
                <div class="container">

                    <h1>Invalid Cart</h1>


                </div>
            </div>
        </section>

        <section class="container" >

            <p>
                <a href="<spring:url value="/product/productlist"/>" class="btn btn-default">Products</a>
            </p>
        </section>

    </div>
</div>

<%@include file="/WEB-INF/views/template/footer.jsp"%>