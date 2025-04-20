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
    <title>Lab Chemicals | Tin Tức</title>
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
            <li class="d-inline-block text-capitalize"><a href="">Tin tức</a></li>
        </ul>
    </div>
</div>

<!-- ===== BLOG SINGLE ===== -->
<section class="blog-single">
    <div class="container">
        <div class="row">
            <div class="col-lg-8 col-12">
                <div class="blog-single-main mt-4 bg-white">
                    <div class="row">
                        <div class="col-12">
                            <div class="image position-relative">
                                <img src="../images/tin_tuc/blog-img-950x460.jpg" alt="#"/>
                            </div>
                            <div class="blog-detail">
                                <h2 class="blog-title">Giảm Giá Sâu Cho Đơn Hàng Hóa Chất Merck Và Sigma Aldrich.</h2>
                                <div class="blog-meta">
                                        <span class="author"><a href="#" class="pl-0"><i class="fa fa-user"></i>Bởi Admin</a><a href="#"><i
                                                class="fa fa-calendar"></i>Ngày 24/12/2022</a><a href="#"
                                                                                                 class="pr-0 border-0"><i class="fa fa-comments"></i>Bình luận (15)</a></span>
                                </div>
                                <div class="content">
                                    <p>
                                        Từ ngày 20/9 - 20/10, Lab Chemicals giảm giá sâu cho đơn
                                        hàng hóa chất Merck hoặc Sigma Aldrich được nhập khẩu từ Mỹ, áp dụng như sau:
                                    </p>
                                    <blockquote>
                                        <i class="fa fa-quote-left"></i> Thời sự Thứ bảy,
                                        14/3/2020 15:17, BẾN TRE - 700 bao nylon chứa
                                        chất lỏng màu đen, đặc sệt, mùi hôi như nhựa đường trôi
                                        dạt vào bờ biển Bình Đại đang được thu gom. Chiều 13/3,
                                        người dân xã Thừa Đức, Thới Thuận lúc đi đánh cá, phát
                                        hiện hàng trăm bao tải màu đen, bị sóng đánh dạt vào bãi
                                        biển nằm rải rác hàng chục cây số, nên trình báo chính
                                        quyền địa phương.
                                    </blockquote>
                                    <p>
                                        Ông Võ Văn Quân, Phó chủ tịch UBND huyện Bình Đại cho
                                        biết, do một số túi đã bị rách, hóa chất tràn ra ngoài,
                                        có mùi hôi giống dầu. "Chất này nhiều khả năng là dầu
                                        thô hoặc nhựa đường, tổng cộng 700 bao, mỗi bao nặng 25
                                        kg, đã được thu gom", ông Quân nói.
                                    </p>
                                    <p class="m-0">
                                        Do trên địa bàn có nhiều diện tích nuôi nghêu, lo ngại
                                        bị ảnh hưởng, hiện chính quyền địa phương đã trình báo
                                        Sở Tài nguyên và Môi trường tỉnh lấy mẫu xét nghiệm xem
                                        là chất gì, để có hướng xử lý thích hợp.
                                    </p>
                                </div>
                            </div>
                            <div class="share-social">
                                <div class="content-tags position-relative mt-4">
                                    <h4>Tags:</h4>
                                    <ul class="tag-inner">
                                        <li><a href="#">thủy tinh</a></li>
                                        <li><a href="#">thực vật</a></li>
                                        <li><a href="#">acid</a></li>
                                        <li class="mr-0 mb-0"><a href="#">nước</a></li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                        <div class="col-12">
                            <div class="comments mt-4">
                                <h3 class="comment-title">Bình luận (3)</h3>

                                <!-- Single Comment -->
                                <div class="single-comment">
                                    <img src="../images/nguoi_dung/user_02_80x80.jpg" alt="#"/>
                                    <div class="content">
                                        <h4>Nguyễn Thành Trung <span>Vào lúc 8:59 ngày 28/2/2018</span></h4>
                                        <p>
                                            Đây chưa chắc là nhựa đường, mà là dầu F.O chạy tàu
                                            thủy vệ sinh các két dầu xử lý trên cạn rất tốn kém
                                            nên mang ra biển thả. Điều tra đc cần chế tài mạnh mẽ
                                            truy tố hình sự mấy tội cố ý làm ô nhiễm môi trường.
                                        </p>
                                    </div>
                                </div>

                                <!-- Single Comment -->
                                <div class="single-comment">
                                    <img src="../images/nguoi_dung/user_01_80x80.jpg" alt="#"/>
                                    <div class="content">
                                        <h4>Lê Thị Trâm Anh <span>Vào lúc 8:59 ngày 28/2/2018</span></h4>
                                        <p>
                                            Công nghệ sản xuất lạc hậu, không có công nghệ xử lý
                                            chất thải độc hại hoặc chi phí xử lý quá đắt nên họ
                                            thải ra biển. Thật đáng báo động khi Kiểm soát môi
                                            trường quá lỏng lẻo. Chúng ta không thể đánh đổi phát
                                            triển kinh tế lấy môi trường theo kiểu này!
                                        </p>
                                    </div>
                                </div>

                                <!-- Single Comment -->
                                <div class="single-comment">
                                    <img src="../images/nguoi_dung/user_03_80x80.png" alt="#"/>
                                    <div class="content">
                                        <h4>Phan Tâm Như <span>Vào lúc 8:59 ngày 28/2/2018</span></h4>
                                        <p>
                                            Nhựa đường đó rồi. Nhìn là biết rồi. Vấn đề là ai thả
                                            ra biển, sao họ ko bán, vừa kiếm tiền mà vừa có nhựa
                                            làm đường cho bà con đi.
                                        </p>
                                    </div>
                                </div>

                            </div>
                        </div>
                        <div class="col-12">
                            <div class="reply">
                                <div class="reply-head">
                                    <h2 class="reply-title">Để lại bình luận</h2>
                                    <!-- Comment Form -->
                                    <form class="form" action="#">
                                        <div class="row">
                                            <div class="col-lg-6 col-md-6 col-12">
                                                <div class="form-group mb-4">
                                                    <label>Họ và tên<span>*</span></label>
                                                    <input type="text" name="name" placeholder="" required="required"/>
                                                </div>
                                            </div>
                                            <div class="col-lg-6 col-md-6 col-12">
                                                <div class="form-group mb-4">
                                                    <label>Email<span>*</span></label>
                                                    <input type="email" name="email" placeholder="" required="required"/>
                                                </div>
                                            </div>
                                            <div class="col-12">
                                                <div class="form-group mb-4">
                                                    <label>Bình luận<span>*</span></label>
                                                    <textarea name="message" placeholder=""></textarea>
                                                </div>
                                            </div>
                                            <div class="col-12">
                                                <div class="form-group button text-left mb-0">
                                                    <button type="submit" class="btn">
                                                        Đăng bình luận
                                                    </button>
                                                </div>
                                            </div>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-lg-4 col-12">
                <div class="main-sidebar mt-4">
                    <!-- Single Widget -->
                    <div class="single-widget search mb-5 position-relative">
                        <div class="form">
                            <input type="email" placeholder="Tìm kiếm..."/>
                            <a class="button" href="#"><i class="fa fa-search"></i></a>
                        </div>
                    </div>

                    <!-- Single Widget -->
                    <div class="single-widget category mb-5">
                        <h3 class="title">Danh mục tin tức</h3>
                        <ul class="category-list">
                            <li><a href="#">Hóa Trị 1</a></li>
                            <li><a href="#">Sulfuric Đặc Hiệu</a></li>
                            <li><a href="#">Vôi Phân Hủy</a></li>
                            <li><a href="#">Cho Giáo Dục</a></li>
                            <li><a href="#">Doanh Nghiệp</a></li>
                        </ul>
                    </div>

                    <!-- Single Widget -->
                    <div class="single-widget recent-post mb-5">
                        <h3 class="title">Bài viết gần đây</h3>
                        <!-- Single Post -->
                        <div class="single-post">
                            <div class="image position-relative">
                                <img src="../images/tin_tuc/100x100/tin_01.jpg" alt="#"/>
                            </div>
                            <div class="content">
                                <h5><a href="#">Top 10 Hóa Chất Được Dùng Nhiều Nhất</a></h5>
                                <ul class="comment">
                                    <li><i class="fa fa-calendar" aria-hidden="true"></i>11/01/2022</li>
                                    <li class="mr-0"><i class="fa fa-commenting-o" aria-hidden="true"></i>35</li>
                                </ul>
                            </div>
                        </div>

                        <!-- Single Post -->
                        <div class="single-post">
                            <div class="image position-relative">
                                <img src="../images/tin_tuc/100x100/tin_02.jpg" alt="#"/>
                            </div>
                            <div class="content">
                                <h5><a href="#">Axit Mạnh Nhất Thế Giới, Phá Hủy...</a></h5>
                                <ul class="comment">
                                    <li><i class="fa fa-calendar" aria-hidden="true"></i>05/03/2021</li>
                                    <li class="mr-0"><i class="fa fa-commenting-o" aria-hidden="true"></i>59</li>
                                </ul>
                            </div>
                        </div>

                        <!-- Single Post -->
                        <div class="single-post pb-0 border-0">
                            <div class="image position-relative">
                                <img src="../images/tin_tuc/100x100/tin_03.png" alt="#"/>
                            </div>
                            <div class="content">
                                <h5><a href="#">Bộ Hóa Công Bố Chương Trình Khuyến Mãi...</a></h5>
                                <ul class="comment">
                                    <li><i class="fa fa-calendar" aria-hidden="true"></i>09/06/2022</li>
                                    <li class="mr-0"><i class="fa fa-commenting-o" aria-hidden="true"></i>44</li>
                                </ul>
                            </div>
                        </div>
                    </div>

                    <!-- Single Widget -->
                    <div class="single-widget side-tags">
                        <h3 class="title">Từ khóa</h3>
                        <ul class="tag">
                            <li><a href="#">doanh nghiệp</a></li>
                            <li><a href="#">wordpress</a></li>
                            <li><a href="#">html</a></li>
                            <li><a href="#">đa nền tảng</a></li>
                            <li><a href="#">giáo dục</a></li>
                            <li><a href="#">kinh doanh</a></li>
                            <li><a href="#">an toàn</a></li>
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