<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@include file="/WEB-INF/views/template/header.jsp"%>

<div class="container-wrapper">
    <div class="container">
        <section>
            <div class="jambotron">
                <div class="container">

                    <h1>Customer Registered Successfully</h1>


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




<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.0.1/angular.min.js"></script>
<script src="<c:url value="/resources/js/controller.js" /> "></script>
<%@include file="/WEB-INF/views/template/footer.jsp"%>