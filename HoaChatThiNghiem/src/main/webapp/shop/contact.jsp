<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="pu" uri="https://com.labchemicals.functions" %>
<c:set var="context" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>Lab Chemicals | Liên Hệ</title>
    <link rel="icon" type="image/png" href="images/labchemicals-logo-icon.png"/>

    <!-- ===== STYLESHEET ===== -->
    <jsp:include page="../common/shop-css.jsp"/>
</head>

<body>
<!-- ===== PRELOADER ===== -->
<div class="preloader">
    <div class="preloader-inner">
        <div class="preloader-icon">
            <span></span>
            <span></span>
        </div>
    </div>
</div>

<!-- ===== HEADER ===== -->
<jsp:include page="../common/shop-header.jsp"/>

<!-- ===== BREADCRUMBS ===== -->
<div class="breadcrumbs py-4">
    <div class="container text-left">
        <ul class="bread-list d-inline-block">
            <li class="d-inline-block text-capitalize"><a href="${context}/shop/home">Trang chủ<i class="ti-arrow-right mx-2"></i></a></li>
            <li class="d-inline-block text-capitalize"><a href="">Liên hệ</a></li>
        </ul>
    </div>
</div>

<!-- ===== CONTACT ===== -->
<section class="contact-us">
    <div class="container">
        <div class="row">
            <div class="col-lg-8 col-12">
                <div class="form-main">
                    <div class="title mb-5">
                        <h4>Liên lạc</h4>
                        <h3>Gửi phản hồi về cho chúng tôi</h3>
                    </div>
                    <form class="form" method="post">
                        <div class="row">
                            <div class="col-lg-6 col-12">
                                <div class="form-group">
                                    <label>Họ và tên<span>*</span></label>
                                    <input name="name" type="text" placeholder=""/>
                                </div>
                            </div>
                            <div class="col-lg-6 col-12">
                                <div class="form-group">
                                    <label>Vấn đề<span>*</span></label>
                                    <input name="subject" type="text" placeholder=""/>
                                </div>
                            </div>
                            <div class="col-lg-6 col-12">
                                <div class="form-group">
                                    <label>Email<span>*</span></label>
                                    <input name="email" type="email" placeholder=""/>
                                </div>
                            </div>
                            <div class="col-lg-6 col-12">
                                <div class="form-group">
                                    <label>Số điện thoại<span>*</span></label>
                                    <input name="company_name" type="text" placeholder=""/>
                                </div>
                            </div>
                            <div class="col-12">
                                <div class="form-group message">
                                    <label>Bình luận<span>*</span></label>
                                    <textarea name="message" placeholder=""></textarea>
                                </div>
                            </div>
                            <div class="col-12">
                                <div class="form-group button">
                                    <button type="submit" class="btn">Gửi</button>
                                </div>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
            <div class="col-lg-4 col-12">
                <div class="single-head">
                    <div class="single-info text-left mb-5">
                        <i class="fa fa-phone"></i>
                        <h4 class="title">Số điện thoại liên lạc</h4>
                        <ul>
                            <li>0956 789 121</li>
                            <li>0672 452 230</li>
                        </ul>
                    </div>
                    <div class="single-info text-left mb-5">
                        <i class="fa fa-envelope-open"></i>
                        <h4 class="title">Email:</h4>
                        <ul>
                            <li>
                                <a href="mailto:info@labchemicals.com">info@labchemicals.com</a>
                            </li>
                            <li>
                                <a href="mailto:info@labchemicals.com">support@labchemicals.com</a>
                            </li>
                        </ul>
                    </div>
                    <div class="single-info text-left">
                        <i class="fa fa-location-arrow"></i>
                        <h4 class="title">Địa chỉ</h4>
                        <ul>
                            <li>
                                123, phường Linh Trung, Tp. Thủ Đức, Tp. Hồ Chí Minh.
                            </li>
                            <li class="mt-2">
                                35 Đinh Tiên Hoàng, phường 5, quận 5, Tp. Hồ Chí Minh.
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>

<!-- ===== FOOTER ===== -->
<jsp:include page="../common/shop-footer.jsp"/>

<!-- ===== JAVASCRIPT ===== -->
<jsp:include page="../common/shop-js.jsp"/>

</body>

</html>