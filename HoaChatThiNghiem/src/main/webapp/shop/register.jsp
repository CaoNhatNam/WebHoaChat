<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="context" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>Lab Chemicals | Đăng Ký Ngay</title>
    <link rel="icon" type="image/png" href="images/labchemicals-logo-icon.png"/>

    <!-- ===== STYLESHEET ===== -->
    <jsp:include page="../common/shop-css.jsp"/>
</head>

<body>
<!-- ===== HEADER ===== -->
<header class="header-account">
    <div class="container">
        <div class="row py-3">
            <div class="col-lg-7 col-md-7 col-12">
                <div class="title-left d-flex justify-content-start h-100 align-items-center">
                    <a class="w-25 mr-4" href="${context}/shop/home"><img src="images/labchemicals-logo.png" alt=""/></a>
                    <span class="d-inline-block mt-1">Đăng Ký</span>
                </div>
            </div>
            <div class="col-lg-5 col-md-5 col-12">
                <div class="title-right d-flex h-100 justify-content-end align-items-center">
                    <a href="#">Bạn cần giúp đỡ?</a>
                </div>
            </div>
        </div>
    </div>
</header>

<!-- ===== BREADCRUMBS ===== -->
<div class="breadcrumbs py-4">
    <div class="container text-left">
        <ul class="bread-list d-inline-block">
            <li class="d-inline-block text-capitalize"><a href="${context}/shop/home">Trang chủ<i class="ti-arrow-right mx-2 mx-2"></i></a></li>
            <li class="d-inline-block text-capitalize"><a href="">Đăng ký</a></li>
        </ul>
    </div>
</div>

<!-- ===== FORM INPUT ===== -->
<%String text = (String) request.getAttribute("text_register");%>
<%String success = (String) request.getAttribute("success_register");%>
<section class="form-input py-5">
    <div class="container">
        <div class="row">
            <div class="col-lg-7 col-md-7 col-12 text-center">
                <img width="80%" src="images/labs/login_start.jpg" alt=""/>
            </div>
            <div class="col-lg-5 col-md-5 col-12">
                <div class="h-100 d-flex align-items-center">
                    <form id="form-register" class="m-0 p-5 text-center" action="${context}/shop/register" method="post">
                        <h5 class="mb-4">Đăng Ký</h5>
                        <%if(text != null){%>
                        <div class="w-100 mb-3 alert alert-danger notification" role="alert">
                            <%=text%>
                        </div>
                        <%}%>
                        <%if(success != null){%>
                        <div class="w-100 mb-3 alert alert-success notification" role="alert">
                            <%=success%>
                        </div>
                        <%}%>
                        <span id="error-email" class="text-danger"></span>
                        <input id="email" class="w-100 mb-3" type="text" placeholder="Email" name="email"
                               value="<%=request.getParameter("email") != null ? request.getParameter("email") : ""%>"/>
                        <span id="error-password" class="text-danger"></span>
                        <input id="password" class="w-100 mb-3" type="password" placeholder="Mật khẩu" name="password"/>
                        <span id="error-confirm-pass" class="text-danger"></span>
                        <input id="confirm-pass" class="w-100 mb-4" type="password" placeholder="Nhập lại mật khẩu" name="confirm-pass"/>
                        <button class="btn next w-100 mb-3">Tiếp theo</button>
                        <span class="shotcut">Bạn đã có tài khoản? <a href="${context}/shop/login">Đăng nhập</a></span>
                    </form>
                </div>
            </div>
        </div>
    </div>
</section>

<!-- ===== SHOP SERVICES ===== -->
<jsp:include page="../common/shop-services.jsp"/>

<!-- ===== FOOTER ===== -->
<jsp:include page="../common/shop-footer.jsp"/>

<!-- ===== JAVASCRIPT ===== -->
<jsp:include page="../common/shop-js.jsp"/>
<script src="./js/processInputText.js"></script>
<script>
    var email = document.getElementById("email");
    var error_email = document.getElementById("error-email");
    notEmpty(email, error_email);

    var password = document.getElementById("password");
    var error_password = document.getElementById("error-password");
    notEmpty(password, error_password);
    notEmptyAndMinimum(password, error_password, "Mật khẩu")

    var confirm_pass = document.getElementById("confirm-pass");
    var error_confirm_pass = document.getElementById("error-confirm-pass");
    notEmpty(confirm_pass, error_confirm_pass);
    notEmptyAndMinimum(confirm_pass, error_confirm_pass, "Mật khẩu nhập lại")

    var form_register = document.getElementById("form-register");
    preventSubmitForRegister(form_register, email, password, confirm_pass)
</script>
</body>

</html>