<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="context" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta charset="UTF-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>Lab Chemicals | Đăng Nhập</title>
    <link rel="icon" type="image/png" href="images/labchemicals-logo-icon.png"/>

    <!-- ===== STYLESHEET ===== -->
    <jsp:include page="../common/shop-css.jsp"/>
</head>

<body>
<%String error = (String) request.getAttribute("error");%>
<!-- ===== HEADER ===== -->
<header class="header-account">
    <div class="container">
        <div class="row py-3">
            <div class="col-lg-7 col-md-7 col-12">
                <div class="title-left d-flex justify-content-start h-100 align-items-center">
                    <a class="w-25 mr-4" href="${context}/shop/home"><img src="images/labchemicals-logo.png"
                                                                          alt=""/></a>
                    <span class="d-inline-block mt-1">Đăng Nhập</span>
                </div>
            </div>
            <div class="col-lg-5 col-md-5 col-12">
                <div class="title-right d-flex h-100 justify-content-end align-items-center">
                    <a href="${context}/admin/dang-nhap">Đăng nhập với tài khoản admin</a>
                </div>
            </div>
        </div>
    </div>
</header>

<!-- ===== BREADCRUMBS ===== -->
<div class="breadcrumbs py-4">
    <div class="container text-left">
        <ul class="bread-list d-inline-block">
            <li class="d-inline-block text-capitalize"><a href="${context}/shop/home">Trang chủ<i
                    class="ti-arrow-right mx-2"></i></a></li>
            <li class="d-inline-block text-capitalize"><a href="">Đăng nhập</a></li>
        </ul>
    </div>
</div>

<!-- ===== FORM INPUT ===== -->
<section class="form-input py-5">
    <div class="container">
        <div class="row">
            <div class="col-lg-7 col-md-7 col-12 text-center">
                <img width="80%" src="images/labs/login_start.jpg" alt=""/>
            </div>
            <div class="col-lg-5 col-md-5 col-12">
                <div class="h-100 d-flex align-items-center">
                    <form id="form-login" class="m-0 p-5 text-center" action="<%=request.getContextPath()%>/shop/login"
                          method="post"
                          name="form_login">
                        <h5 class="mb-4">Đăng Nhập</h5>
                        <%if (error != null) {%>
                        <div class="error-mess w-100 mb-3 alert alert-danger" role="alert">
                            <%=error%>
                        </div>
                        <%}%>
                        <span id="error-email" class="error-mess text-danger"></span>
                        <input id="email" class="w-100 mb-3" type="email" placeholder="Email" name="email"
                               value="<%=request.getParameter("email") != null ? request.getParameter("email"):""%>"/>
                        <span id="error-password" class="error-mess text-danger"></span>
                        <input id="password" class="w-100 mb-4" type="password" placeholder="Mật khẩu" name="password"/>
                        <button type="submit" class="btn next w-100">Đăng nhập</button>
                        <span class="or d-inline-block text-uppercase my-4 position-relative">Hoặc</span>
                        <a onclick="loginGg()" id="google-login-button"
                           class="google d-flex justify-content-center w-100 mb-3"><img
                                width="25px" class="mr-2" src="images/logo-google.png"
                                alt=""/>Google</a>
                        <a onclick="loginFB()" id="fb-login-button"
                           class="google d-flex justify-content-center w-100 mb-3"><img
                                width="30px" class="mr-2" src="images/logo-fb.png"
                                alt=""/>Facebook</a>
                        <span class="shotcut">
                                <a class="mr-3" href="${context}/shop/forgot-password">Quên mật khẩu?</a>
                                <a href="${context}/shop/register">Đăng ký?</a></span>
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

</body>
<script src="./js/processInputText.js"></script>
<%--xu ly inputText--%>
<script>
    var email = document.getElementById("email");
    var error_email = document.getElementById("error-email");
    notEmpty(email, error_email);

    var password = document.getElementById("password");
    var error_password = document.getElementById("error-password");
    notEmptyAndMinimum(password, error_password, "Mật khẩu");
</script>
<%--xu ly chuyen huong den trang danh nhap facebook, google--%>
<script>
    function loginGg() {
        let clientId = "<%=request.getAttribute("clientIdGg")%>"
        let redirectUri = "<%=request.getAttribute("redirectUrlGg")%>"
        let linkAuthentication = 'https://accounts.google.com/o/oauth2/auth?scope=profile%20email&redirect_uri='
            + redirectUri + '&response_type=code&client_id=' + clientId + '&approval_prompt=force&access_type=offline'
        window.location = linkAuthentication
    }

    function loginFB() {
        let clientId = "<%=request.getAttribute("clientIdFb")%>"
        let redirectUri = "<%=request.getAttribute("redirectUrlFb")%>"
        let linkAuthentication = 'https://www.facebook.com/dialog/oauth?client_id='
            + clientId + '&redirect_uri=' + redirectUri + '&scope=public_profile,email'
        window.location = linkAuthentication
    }
</script>
</html>