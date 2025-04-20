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
    <title>Lab Chemicals | Thanh Toán</title>
    <link rel="icon" type="image/png" href="images/labchemicals-logo-icon.png"/>

    <!-- ===== STYLESHEET ===== -->
    <jsp:include page="../common/shop-css.jsp"/>

</head>

<body>
<style>
    .nice-select .list {
        max-height: 250px;
        overflow-y: auto !important;
    }
</style>
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
            <li class="d-inline-block text-capitalize"><a href="${context}/shop/home">Trang chủ<i
                    class="ti-arrow-right mx-2"></i></a></li>
            <li class="d-inline-block text-capitalize"><a href="${context}/shop/cart">Giỏ hàng<i
                    class="ti-arrow-right mx-2"></i></a></li>
            <li class="d-inline-block text-capitalize"><a href="">Thanh toán</a></li>
        </ul>
    </div>
</div>

<!-- ===== CHECKOUT ===== -->
<section class="checkout">
    <div class="container">
        <div class="row">
            <div class="col-lg-8 col-12">
                <div class="checkout-form mt-4">
                    <div class="checkout-title">
                        <h3>Thông tin giao hàng</h3>
                        <p class="my-2">
                            Yêu cầu quý khách cần điền đầy đủ thông tin để việc giao hàng
                            được thuận lợi
                        </p>
                    </div>
                    <%--                    <form class="form" id="checkout_form" method="POST" action="${context}/shop/checkout">--%>
                    <form class="form" id="checkout_form">
                        <div class="row">
                            <div class="col-lg-12 col-12">
                                <div class="form-group mb-4">
                                    <label>Họ và tên</label>
                                    <input id="nameCustomer" name="nameCustomer" type="text"
                                           value="${sessionScope.auth_customer.fullname}"/>
                                </div>
                            </div>
                            <div class="col-lg-6 col-12">
                                <div class="form-group mb-4">
                                    <label>Số điện thoại<span>*</span></label>
                                    <input id="phoneCustomer" name="phone" type="text"
                                           value="${sessionScope.auth_customer.phone}"/>
                                </div>
                            </div>
                            <div class="col-lg-6 col-12">
                                <div class="form-group mb-4">
                                    <label>Email<span>*</span></label>
                                    <input id="emailCustomer" name="email" type="email"
                                           value="${sessionScope.auth_customer.email_customer}"/>
                                </div>
                            </div>
                            <div class="col-lg-4 col-12">
                                <div class="form-group">
                                    <label>Tỉnh / Thành<span>*</span></label>
                                    <div class="select-wrapper">
                                        <select name="cities" size="10" class="form-select form-select-sm mb-3"
                                                id="city"
                                                aria-label=".form-select-sm" style="display:block">
                                            <option value="" selected>Chọn tỉnh thành</option>
                                        </select>
                                    </div>
                                </div>
                            </div>
                            <div class="col-lg-4 col-12">
                                <div class="form-group">
                                    <label>Quận / Huyện<span>*</span></label>
                                    <div class="select-wrapper">
                                        <select name="districts" class="form-select form-select-sm mb-3" id="district"
                                                aria-label=".form-select-sm" style="display: block">
                                            <option value="" selected>Chọn quận huyện</option>
                                        </select>
                                    </div>
                                </div>
                            </div>
                            <div class="col-lg-4 col-12">
                                <div class="form-group">
                                    <label>Phường / Xã<span>*</span></label>
                                    <div class="select-wrapper">
                                        <select name="wards" class="form-select form-select-sm" id="ward"
                                                aria-label=".form-select-sm"
                                                style="display: block">
                                            <option value="" selected>Chọn phường xã</option>
                                        </select>
                                    </div>
                                </div>
                            </div>
                            <div class="col-lg-12 col-12">
                                <div class="form-group">
                                    <label>Địa chỉ<span>*</span></label>
                                    <input id="address" name="address" type="text"/>
                                </div>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
            <div class="col-lg-4 col-12">
                <div class="order-details">
                    <!-- Order Widget -->
                    <div class="single-widget mb-4">
                        <h2>TỔNG CỘNG</h2>
                        <div class="content mt-3">
                            <ul>
                                <li>Hóa đơn<span id="billPrice">0</span>
                                </li>
                                <li>(+) Vận chuyển<span id="feeShip">0</span></li>
                                <li>Tổng<span
                                        class="total" id="totalBill">0</span>
                                </li>
                            </ul>
                        </div>
                    </div>
                    <!-- Order Widget -->
                    <div class="single-widget mb-5">
                        <h2>PHƯƠNG THỨC THANH TOÁN</h2>
                        <div class="content mt-3">
                            <div class="radio">
                                <input type="radio" id="2" checked>
                                <label class="checkbox-inline" for="2">Thanh toán trực tiếp</label>
                            </div>
                        </div>
                    </div>
                    <!-- Button Widget -->
                    <div class="single-widget get-button">
                        <div class="content">
                            <div class="button">
                                <button class="btn" id="btn-checkout">Thanh toán</button>
                            </div>
                        </div>
                    </div>
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

<%--Thư viện dùng để hiển thị cửa sổ thông báo--%>
<script src="https://cdnjs.cloudflare.com/ajax/libs/sweetalert/2.1.2/sweetalert.min.js"></script>

<script>
    const bill_price_before = ${requestScope['bill_price']};
    let billPrice = document.getElementById('billPrice');
    let costShip = document.getElementById('feeShip');
    let totalBill = document.getElementById('totalBill');

    $(document).ready(function () {

        billPrice.innerHTML = formatCurrency(bill_price_before)
        costShip.innerHTML = formatCurrency(0)
        totalBill.innerHTML = formatCurrency(bill_price_before)

    })

    // format theo định dạng tiền tệ VN
    function formatCurrency(currency) {
        return currency.toLocaleString('vi-VN', {
            minimumFractionDigits: 0,
            maximumFractionDigits: 0,
            useGrouping: true,
            currency: 'VND',
            style: 'currency'
        })
    }
</script>

<%--Thư viện Axios là một thư viện HTTP Client dựa trên Promise--%>
<script src="https://cdnjs.cloudflare.com/ajax/libs/axios/0.21.1/axios.min.js"></script>

<!-- Gọi API để lấy danh sách tỉnh/thành, quận/huyện, phường/xã -->
<script>

    let cities = document.getElementById('city');
    let districts = document.getElementById('district');
    let wards = document.getElementById('ward');

    // các url của api logistic
    const urls = {
        api_login: 'http://140.238.54.136/api/auth/login',
        api_province: 'http://140.238.54.136/api/province',
        api_district: 'http://140.238.54.136/api/district',
        api_ward: 'http://140.238.54.136/api/ward',
        api_feeShip: 'http://140.238.54.136/api/calculateFee',
    }

    // thông tin tài khoản đã đăng ký dùng để gọi api logistic
    const accountAPI = {
        email: 'tuyen@1234',
        password: '123456789'
    }

    // thông tin vận chuyển đơn hàng
    let infor_transport = {
        from_district_id: 3695, // Thành phố Thủ Đức
        from_ward_id: 90737, // Phường Linh Trung
        height: 100,
        length: 100,
        width: 100,
        weight: 100
    }

    let countGetListProvince = 0;
    let access_token = getToken(urls.api_login, accountAPI.email, accountAPI.password); // gọi api để lấy access_token sau khi vừa load trang
    let listProvince;// danh sách tỉnh/thành
    let listDistrictOfProvince; // danh sách quận/huyện thuộc một tỉnh/thành bất kì nào đó
    let listWardOfDistrict; // danh sách phường/xã thuộc một quận/huyện bất kì nào đó
    let feeShip; // phí ship ước lượng

    //  Gọi api POST để lấy access_token <=> đăng nhập vào hệ thống của bên logistic bằng tài khoản đã đăng ký trước đó
    function getToken(url, email, password) {

        console.log("Access_Token:")

        axios.post(url, {
            email: email,
            password: password
        })

            .then((response) => {

                access_token = response.data.access_token
                console.log(access_token)

                // đảm bảo chỉ gọi hàm getListProvince 1 lần duy nhất
                if (countGetListProvince === 0) {
                    getListProvince(urls.api_province, access_token) // gọi api lấy danh sách tỉnh/thành dựa vào access_token vừa lấy được
                    countGetListProvince++;
                }

            }) // => được gọi khi Promise được giải quyết thành công và trả về dữ liệu JSON từ API endpoint.

            .catch((error) => {

                // access_token = null;
                console.error(error);
            }); // => được gọi khi Promise bị từ chối và trả về lỗi từ API endpoint.
    }

    // Gọi api GET để lấy ra danh sách các tỉnh/thành
    function getListProvince(url, access_token) {

        console.log('Danh sách các tỉnh/thành')
        axios.get(url, {
            headers: {
                Authorization: 'Bearer ' + access_token
            }
        })
            .then((response) => {

                listProvince = response.data.original.data
                console.log(listProvince)

                renderProvince(listProvince) // hiển thị danh sách tỉnh/thành lên giao diện

            })  // => được gọi khi Promise được giải quyết thành công và trả về dữ liệu JSON từ API endpoint.
            .catch((error) => {
                console.error(error);
            })  // => được gọi khi Promise bị từ chối và trả về lỗi từ API endpoint.

    }

    // Gọi api GET lấy ra danh sách quận/huyện của một tỉnh/thành bằng api GET
    function getListDistrictOfProvince(url, provinceID, access_token) {

        console.log('Danh sách các quận/huyện của một tỉnh/thành')

        axios.get(url, {
            headers: {
                Authorization: 'Bearer ' + access_token
            },
            params: {
                provinceID: provinceID
            }
        })
            .then((response) => {
                listDistrictOfProvince = response.data.original.data
                console.log(listDistrictOfProvince)

                renderDistrict(listDistrictOfProvince) // hiển thị danh sách quận/huyện vừa được lấy về lên giao diện

            })
            .catch((error) => {
                console.error(error);
            })
    }

    // Gọi api GET lấy ra danh sách phường/xã của một quận/huyện bằng api GET
    function getListWardOfDistrict(url, districtID, access_token) {

        console.log('Danh sách các phường/xã của một quận/huyện')

        axios.get(url, {
            headers: {
                Authorization: 'Bearer ' + access_token
            },
            params: {
                districtID: districtID
            }
        })
            .then((response) => {
                listWardOfDistrict = response.data.original.data
                console.log(listWardOfDistrict)

                renderWard(listWardOfDistrict) // hiển thị danh sách phường/xã vừa được lấy về lên giao diện
            })
            .catch((error) => {
                console.error(error);
            })
    }

    // Gọi api POST ước lượng chi phí vận chuyển
    function getFeeShip(url, from_district_id, from_ward_id, to_district_id, to_ward_id, height, length, width, weight, access_token) {

        console.log("đây là hàm getFeeShip()")
        axios.post(url, {
            from_district_id: from_district_id,
            from_ward_id: from_ward_id,
            to_district_id: to_district_id,
            to_ward_id: to_ward_id,
            height: height,
            length: length,
            width: width,
            weight: weight
        }, {
            headers: {
                Authorization: 'Bearer ' + access_token
            }

        })
            .then((response) => {
                const data = response.data
                feeShip = data.data[0].service_fee

                costShip.innerHTML = formatCurrency(feeShip);
                totalBill.innerHTML = formatCurrency(bill_price_before + feeShip)
                console.log(feeShip)
            })
            .catch((error) => {
                console.log(error.message)
                console.error(error);
            })

    }

    // gọi lại hàm getToken để lấy access_token mới sau 300s
    setInterval(() => {
        getToken(urls.api_login, accountAPI.email, accountAPI.password);
    }, 300000);

    function renderProvince(listProvince) {

        console.log("Đây là hàm renderProvince()")
        // console.log(listProvince)

        listProvince.forEach((province) => {
            const option = document.createElement('option');
            option.value = province.ProvinceID;
            option.textContent = province.ProvinceName;
            cities.appendChild(option);
        });

        // Cập nhật plugin nice-select
        $('#city').niceSelect('update');

        // xứ lý khi thay đổi tỉnh/thành thì sẽ hiển thị ra quận/huyện thuộc tỉnh thành đó
        cities.onchange = function () {

            let provinceID = this.value;
            if (provinceID != "") {
                resetSelectDistrict();
                resetSelectWard();
                getListDistrictOfProvince(urls.api_district, provinceID, access_token) // gọi api lấy danh sách quận/huyện của tỉnh/thành dựa vào id tỉnh/thành
            } else {

                resetSelectDistrict();
                resetSelectWard();
                resetFeeShip();
            }

        }

    }

    function renderDistrict(listDistrictOfProvince) {

        console.log("Đây là hàm renderDistrict()")

        resetSelectDistrict();

        listDistrictOfProvince.forEach((district) => {
            const option = document.createElement('option');
            option.value = district.DistrictID;
            option.textContent = district.DistrictName;
            districts.appendChild(option);
        })

        // Cập nhật plugin nice-select
        $('#district').niceSelect('update');

        // xứ lý khi thay đổi quận/huyện thì sẽ hiển thị ra phường/xã thuộc quận/huyện đó
        districts.onchange = function () {
            let districtID = this.value;
            if (districtID != "") {
                getListWardOfDistrict(urls.api_ward, districtID, access_token) // gọi api lấy danh sách phường/xã của quận/huyện dựa vào id quận/huyện
            } else {
                resetSelectWard();
                resetFeeShip();
            }
        }
    }

    function renderWard(listWardOfDistrict) {


        console.log("Đây là hàm renderWard()")

        resetSelectWard();

        listWardOfDistrict.forEach((ward) => {
            const option = document.createElement('option');
            option.value = ward.WardCode;
            option.textContent = ward.WardName;
            wards.appendChild(option);
        })

        // Cập nhật plugin nice-select
        $('#ward').niceSelect('update');

        wards.onchange = function () {
            let to_district_id = districts.value;
            let to_ward_id = this.value;

            getFeeShip(urls.api_feeShip, infor_transport.from_district_id, infor_transport.from_ward_id,
                to_district_id, to_ward_id,
                infor_transport.height, infor_transport.length,
                infor_transport.width, infor_transport.weight, access_token)

        }

    }

    function resetSelectDistrict() {

        // Reset danh sách các phần tử option của select quận/huyện
        districts.innerHTML = '';
        const defaultOptionD = document.createElement('option');
        defaultOptionD.value = '';
        defaultOptionD.textContent = 'Chọn quận huyện';
        districts.appendChild(defaultOptionD);

        // Cập nhật plugin nice-select
        $('#district').niceSelect('update');

    }

    function resetSelectWard() {

        // Reset danh sách các phần tử option của select phường/xã
        wards.innerHTML = '';
        const defaultOptionW = document.createElement('option');
        defaultOptionW.value = '';
        defaultOptionW.textContent = 'Chọn phường xã';
        wards.appendChild(defaultOptionW);

        // Cập nhật plugin nice-select
        $('#ward').niceSelect('update');

    }

    function resetFeeShip() {
        feeShip = 0;
        costShip.innerHTML = formatCurrency(feeShip);
        totalBill.innerHTML = formatCurrency(bill_price_before);
    }

</script>

<%-- Kiểm tra sự hợp lệ của form thông tin giao hàng dùng thư viện jquery.validate --%>
<script src="https://cdn.jsdelivr.net/jquery.validation/1.16.0/jquery.validate.min.js"></script>

<%--Kiểm tra hợp lệ của form và gọi Ajax khi đặt hàng --%>
<script>

    $(document).ready(function () {

        // Kiểm tra validate form
        $('#checkout_form').validate({
            rules: {
                // tên khách hàng
                nameCustomer: {
                    required: true,
                },
                // số điện thoại khách hàng
                phone: {
                    required: true,
                    minlength: 10,
                    maxlength: 10,
                    number: true,
                },
                // địa chỉ email
                email: {
                    required: true,
                    email: true,
                },
                // địa chỉ chi tiết đơn hàng sẽ được giao tới
                address: {
                    required: true,
                },
            },
            messages: {
                nameCustomer: {
                    required: "Họ và tên không được để trống",
                },
                phone: {
                    required: "Số điện thoại không được để trống",
                    minlength: "Số điện thoại phải có 10 số",
                    maxlength: "Số điện thoại phải có 10 số",
                    number: "Số điện thoại không đúng định dạng",
                },
                email: {
                    required: "Email không được để trống",
                    email: "Email không đúng định dạng",
                },
                address: {
                    required: "Địa chỉ không được để trống",
                },
            },
            // Hiển thị thông báo lỗi
            errorPlacement: function (error, element) {
                error.insertAfter(element);
                error.css("color", "red");
            }
        })
    })

    $('#btn-checkout').on('click', function () {

        let nameCustomer = $('#nameCustomer').val()
        let phoneCustomer = $('#phoneCustomer').val()
        let emailCustomer = $('#emailCustomer').val()

        let provinceId = $('#city').val()
        let provinceName = $('#city').find(':selected').text()

        let districtId = $('#district').val()
        let districtName = $('#district').find(':selected').text()

        let wardId = $('#ward').val()
        let wardName = $('#ward').find(':selected').text()

        let address = $('#address').val()

        if (nameCustomer == "" || phoneCustomer == "" ||
            emailCustomer == "" || provinceId == "" ||
            districtId == "" || wardId == "" || address == "") {

            swal({
                title: 'Thông báo',
                text: 'Bạn hãy nhập đầy đủ thông tin !!!',
                icon: 'warning',
                timer: 3000,
                buttons: false
            })

        } else {

            if (feeShip == null || feeShip == undefined) feeShip = 0

            $.ajax({
                url: '${context}/AjaxCheckoutServlet', // -- địa chỉ của server
                type: 'POST', // -- phương thức truyền : GET, POST, PUT, DELETE
                data: {

                    NameCustomer: nameCustomer,
                    PhoneCustomer: phoneCustomer,
                    EmailCustomer: emailCustomer,
                    ProvinceId: provinceId,
                    ProvinceName: provinceName,
                    DistrictId: districtId,
                    DistrictName: districtName,
                    WardId: wardId,
                    WardName: wardName,
                    Address: address,
                    BillPriceBefore: bill_price_before,
                    FeeShip: feeShip,
                    BillPriceAfter: bill_price_before + feeShip


                }, // -- tham số truyền đến server
                data_type: 'text', // -- kiểu dữ liệu nhận về từ server
                success: function (resultData) {

                    if (resultData.toString() == "true") {
                        swal({
                            title: 'Thông báo',
                            text: 'Bạn đã đặt hàng thành công ^.^',
                            icon: 'success',
                            timer: 3000,
                            buttons: false
                        }).then(() => {
                            window.location.href = "<%=request.getContextPath()%>/shop/home";
                        }).onClose(() => {
                            // Xử lý tại đây nếu người dùng tắt cửa sổ thông báo trước khi nó đóng tự động
                            window.location.href = "<%=request.getContextPath()%>/shop/home";
                        });
                    } else {
                        swal({
                            title: 'Thông báo',
                            text: 'Đơn hàng của bạn không được đặt !!!',
                            icon: 'error',
                            timer: 3000,
                            buttons: false
                        })
                    }


                },
                error: function () {
                    // error no call ajax
                }
            })

        }
    })
</script>

</body>

</html>