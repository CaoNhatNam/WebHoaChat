<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="pu" uri="https://com.labchemicals.functions" %>
<c:set var="context" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Quản lý đơn hàng</title>

    <!-- ===== STYLESHEET ===== -->
    <jsp:include page="../common/admin-css.jsp"/>
</head>

<body class="app sidebar-mini rtl">
<%--    Header--%>
<%--<jsp:include page="../common/admin-header.jsp"/>--%>

<!-- Sidebar Menu -->
<jsp:include page="../common/admin-sidebar-menu.jsp"/>

<main class="app-content" id="bill-page">
    <div class="app-title">
        <ul class="app-breadcrumb breadcrumb side">
            <li class="breadcrumb-item active"><a href="#"><b>Quản lý đơn hàng</b></a></li>
        </ul>
        <div id="clock"></div>
    </div>
    <div class="row">
        <div class="col-md-12">
            <div class="tile">
                <div class="tile-body">
                    <div class="row element-button">
                        <div class="col-sm-2">
                            <a class="btn btn-delete btn-sm print-file" type="button" title="In"
                               onclick="myApp.printTable()"><i class="fas fa-print"></i> In dữ liệu</a>
                        </div>
                        <div class="col-sm-2">
                            <a class="btn btn-excel btn-sm" href="" title="In"><i class="fas fa-file-excel"></i> Xuất
                                Excel</a>
                        </div>
                        <div class="col-sm-2">
                            <a class="btn btn-delete btn-sm pdf-file" type="button" title="In"
                               onclick="myApp.printTable()"><i class="fas fa-file-pdf"></i> Xuất PDF</a>
                        </div>
                    </div>
                    <table class="table table-hover table-bordered bill-table" id="sampleTable">
                        <thead>
                        <tr>
                            <th class="text-center">ID đơn hàng</th>
                            <th class="text-center">Khách hàng</th>
                            <th class="text-center">SĐT</th>
                            <th class="text-center">Tình trạng</th>
                            <th class="text-center">Địa chỉ giao hàng</th>
                            <th class="text-center">Tiền ship</th>
                            <th class="text-center">Trị giá đơn hàng</th>
                            <th class="text-center">Thời gian đặt hàng</th>
                            <th class="text-center">Cập nhật</th>
                            <th></th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="b" items="${requestScope.bills}">
                            <c:choose>
                                <c:when test="${b.getNameStatus() == 'Đã giao'}"><c:set var="bg"
                                                                                        value="bg-success"/></c:when>
                                <c:when test="${b.getNameStatus() == 'Đang giao hàng'}"><c:set var="bg"
                                                                                               value="bg-warning"/></c:when>
                                <c:when test="${b.getNameStatus() == 'Hủy đơn hàng'}"><c:set var="bg"
                                                                                             value="bg-danger"/></c:when>
                                <c:when test="${b.getNameStatus() == 'Chờ xác nhận'}"><c:set var="bg" value="bg-info"/></c:when>
                                <c:when test="${b.getNameStatus() == ''}"><c:set var="bg" value="bg-info"/></c:when>
                            </c:choose>
                            <tr>
                                <td><a href="${context}/admin/chi-tiet-don-hang?id_bill=${b.id_bill}">#${b.id_bill}</a>
                                </td>
                                <td>${b.nameCustomer}</td>
                                <td>${b.phoneCustomer}</td>
                                <td data-ss="${bg}" class="text-center"><span
                                        class="badge ${bg}">${b.getNameStatus()}</span></td>
                                <td>${b.address}, ${b.to_ward_name}, ${b.to_district_name}, ${b.to_province_name}</td>
                                <td>${pu:format(b.ship_price)} đ</td>
                                <td>${pu:format(b.bill_price_before)} đ</td>
                                <td>${b.time_order}</td>
                                <td class="text-center">
                                    <button class="btn btn-primary btn-sm edit" type="button" title="Sửa"><i
                                            class="fas fa-edit"></i></button>
                                </td>
                                <td>${b.id_status_bill}</td>
                                    <%--cột số 9 trong DataTable,được ẩn đi--%>
                                <td>${b.to_district_id}</td>
                                    <%--cột số 10 trong DataTable,được ẩn đi--%>
                                <td>${b.to_ward_id}</td>
                                    <%--cột số 11 trong DataTable,được ẩn đi--%>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</main>

<div class="modal fade modal-bill" id="modal-up" tabindex="-1" role="dialog" aria-hidden="true" data-backdrop="static"
     data-keyboard="false">
    <div class="modal-dialog modal-dialog-centered" role="document">
        <div class="modal-content mt-5">
            <div class="modal-body p-4">
                <div class="row">
                    <div class="form-group col-md-12 ">
                        <label for="select-status" class="control-label">Tình trạng đơn hàng</label>
                        <select class="form-control" id="select-status" name="bill-status">
                            <option value="1">Chờ xác nhận</option>
                            <option value="2">Đang giao hàng</option>
                            <option value="3">Đã giao</option>
                            <option value="4">Hủy đơn hàng</option>
                        </select>
                    </div>
                </div>
                <div class="d-flex justify-content-end mt-3">
                    <button class="btn btn-save mr-3" type="submit">Cập nhật</button>
                    <button class="btn btn-cancel" data-dismiss="modal">Hủy bỏ</button>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- ===== JAVASCRIPT ===== -->
<jsp:include page="../common/admin-js.jsp"/>
<!-- ================================================================================================== -->
<script>

    const myApp = new function () {
        this.printTable = function () {
            const tab = document.getElementById('sampleTable');
            const win = window.open('', '', 'height=700,width=700');
            win.document.write(tab.outerHTML);
            win.document.close();
            win.print();
        }
    }

    $('#sampleTable').dataTable({
        // order: false,
        order: [[0, 'asc']],
        "columnDefs": [
            {
                "targets": [9, 10, 11], // áp dụng cho cột số 9,10,11
                "visible": false, // ẩn cột
                "searchable": false
            }
        ]
    });

    $('.btn-excel').on('click', function () {
        TableToExcel.convert(document.getElementById('sampleTable'), {
            name: `bill_management.xlsx`
        });
        return false
    })

    $('.pdf-file').on('click', function () {
        const pdf = new jsPDF('p', 'pt', 'letter');
        const source = $('#sampleTable');

        const margins = {
            top: 80,
            bottom: 60,
            left: 40,
            width: 522
        };

        pdf.fromHTML(
            source,
            margins.left,
            margins.top, {
                'width': margins.width
            },

            function () {
                pdf.save('Test.pdf');
            }, margins);
    })

</script>

<%--Thư viện dùng để hiển thị cửa sổ thông báo--%>
<script src="https://cdnjs.cloudflare.com/ajax/libs/sweetalert/2.1.2/sweetalert.min.js"></script>

<%--Thư viện Axios là một thư viện HTTP Client dựa trên Promise--%>
<script src="https://cdnjs.cloudflare.com/ajax/libs/axios/0.21.1/axios.min.js"></script>

<script>
    // các url của api logistic
    const urls = {
        api_login: 'http://140.238.54.136/api/auth/login',
        api_register_transport: 'http://140.238.54.136/api/registerTransport'
    }

    // thông tin tài khoản đã đăng ký dùng để gọi api logistic
    const accountAPI = {
        email: 'tuyen@1234',
        password: '123456789'
    }

    let access_token_admin = getToken(urls.api_login, accountAPI.email, accountAPI.password);

    // thông tin vận chuyển đơn hàng
    let infor_transport = {
        id_bill: '-1',
        id_status_bill: '-1',
        from_district_id: '3695', // Thành phố Thủ Đức
        from_ward_id: '90737', // Phường Linh Trung
        height: 100,
        length: 100,
        width: 100,
        weight: 100,
        to_district_id: '0',
        to_ward_id: '0',
        fee: '-1',
        active: '-1',
        lead_time: '-1',
        updated_at: '-1',
        created_at: '-1',
        id_logistic: '-1'
    }

    //  Gọi api POST để lấy access_token <=> đăng nhập vào hệ thống của bên logistic bằng tài khoản đã đăng ký trước đó
    function getToken(url, email, password) {

        console.log("Access_Token:")

        axios.post(url, {
            email: email,
            password: password
        })

            .then((response) => {

                access_token_admin = response.data.access_token
                console.log(access_token_admin)


            }) // => được gọi khi Promise được giải quyết thành công và trả về dữ liệu JSON từ API endpoint.

            .catch((error) => {

                // access_token = null;
                console.error(error);
            }); // => được gọi khi Promise bị từ chối và trả về lỗi từ API endpoint.
    }

    // gọi lại hàm getToken để lấy access_token mới sau 300s
    setInterval(() => {
        getToken(urls.api_login, accountAPI.email, accountAPI.password);
    }, 300000);

    // Gọi API POST để đăng kí vận chuyển đơn hàng
    function registerTransport(url, infor_transport, access_token) {

        console.log("Đây là hàm registerTransport()")
        console.log("DistrictId :" + infor_transport.to_district_id)
        console.log("WardId :" + infor_transport.to_ward_id)

        axios.post(url, {
            from_district_id: infor_transport.from_district_id,
            from_ward_id: infor_transport.from_ward_id,
            to_district_id: infor_transport.to_district_id,
            to_ward_id: infor_transport.to_ward_id,
            height: infor_transport.height,
            length: infor_transport.length,
            width: infor_transport.width,
            weight: infor_transport.weight
        }, {
            headers: {
                Authorization: 'Bearer ' + access_token
            }
        })
            .then((response) => {

                let checkUpdateInforBill = AsyncUpdateInforBill(infor_transport)
                    .then((result) => {
                        console.log("Day la ham AsyncUpdateInforBill() trong ham registerTransport() :" + result)
                        if (result == 'true') checkUpdateInforBill = true
                        else checkUpdateInforBill = false
                        console.log("Ket qua cua bien checkUpdateInforBill :" + checkUpdateInforBill)
                    })
                    .catch((error) => {
                        checkUpdateInforBill = false
                        console.log("Ket qua cua bien checkUpdateInforBill :" + checkUpdateInforBill)
                    })

                if (response.data.message == 'Registered Transport successfully') {
                    swal({
                        title: 'Thông báo',
                        text: 'Đơn hàng đã được đăng kí vận chuyển ^.^',
                        icon: 'success',
                        timer: 2000,
                        buttons: false
                    }).then(() => {
                        window.location.href = "<%=request.getContextPath()%>/admin/quan-ly-don-hang-root";
                    }).onClose(() => {
                        // Xử lý tại đây nếu người dùng tắt cửa sổ thông báo trước khi nó đóng tự động
                        window.location.href = "<%=request.getContextPath()%>/admin/quan-ly-don-hang-root";
                    });

                }
                console.log(response.data)
            }) // => được gọi khi Promise được giải quyết thành công và trả về dữ liệu JSON

            .catch((error) => {
                console.error(error)
                swal({
                    title: 'Thông báo',
                    text: 'Đơn hàng không được đăng kí vận chuyển do lỗi API của bên thứ 3 !!!',
                    icon: 'error',
                    timer: 4000,
                    buttons: false
                }).then(() => {
                    window.location.href = "<%=request.getContextPath()%>/admin/quan-ly-don-hang-root";
                }).onClose(() => {
                    // Xử lý tại đây nếu người dùng tắt cửa sổ thông báo trước khi nó đóng tự động
                    window.location.href = "<%=request.getContextPath()%>/admin/quan-ly-don-hang-root";
                });
            }) // => được gọi khi Promise bị từ chối và trả về lỗi từ API endpoint
    }

</script>

<script>

    let select_status_bill = document.getElementById('select-status') // thẻ Select để Chọn trạng thái đơn hàng
    const modal = $('#modal-up') // cửa sổ cập nhật trạng thái đơn hàng
    let table = $('#sampleTable').DataTable(); // => DataTable
    let value_status_bill; // giá trị trạng thái của đơn hàng trong cửa sổ cập nhật trạng thái
    let selectedRow;// dòng được chọn
    let columnIdx;// cột chứa giá trị trong DataTable
    let cellValue;// giá trị ô tương ứng theo dòng và cột

    // Khi click vào icon edit
    $('.edit').on('click', function () {

        modal.modal('show')// => show ra cửa sổ model

        selectedRow = $(this).closest('tr'); // Lấy ra phần tử tr đang được chọn
        columnIdx = table.column(9).index(); // cột chứa giá trị id_status_bill đã được ẩn đi bởi DataTable
        cellValue = table.cell(selectedRow, columnIdx).data(); // Lấy giá trị ô tương ứng với vị trí hàng và cột đã chọn

        value_status_bill = cellValue
        select_status_bill.value = value_status_bill // thay đổi thẻ select trong cửa sổ Cập nhật trạng thái theo giá trị tương ứng

        console.log("value status bill: " + value_status_bill);

        select_status_bill.onchange = function () {
            value_status_bill = this.value
            console.log("value status bill: " + value_status_bill)

        }// => khi thay đổi trạng thái của đơn hàng

        // Khi click vào button Lưu lại
        $('.btn-save').unbind('click').on('click', function () {

            if (cellValue == value_status_bill) {

                // không cập nhật do giá trị trạng thái đơn hàng không thay đổi

            } else {

                infor_transport.id_bill = getNumberFromString(table.cell(selectedRow, table.column(0).index()).data())

                // console.log("Cell: " + table.cell(selectedRow, table.column(0).index()).data())

                infor_transport.id_status_bill = value_status_bill

                console.log("IdBill : " + infor_transport.id_bill)
                console.log("IdStatusBill : " + infor_transport.id_status_bill)

                switch (value_status_bill) {
                    // Đơn hàng đang ở trạng thái "Chờ xác nhận"
                    case '1':
                        swal({
                            title: 'Thông báo',
                            text: 'Không thể cập nhật trạng thái đơn hàng về "Chờ xác nhận"',
                            icon: 'warning',
                            timer: 4000,
                            buttons: false
                        })
                        break;
                    // ... trạng thái "Đang giao hàng"
                    case '2':
                        // chỉ khi đơn hàng đang ở trạng thái "Chờ xác nhận : 1" mới được cập nhật thành "Đang giao hàng"
                        if (cellValue == '1') {
                            infor_transport.to_district_id = table.cell(selectedRow, table.column(10).index()).data()
                            infor_transport.to_ward_id = table.cell(selectedRow, table.column(11).index()).data()
                            registerTransport(urls.api_register_transport, infor_transport, access_token_admin)
                        } else {
                            swal({
                                title: 'Thông báo',
                                text: 'Không thể cập nhật trạng thái đơn hàng về "Đang giao hàng"',
                                icon: 'warning',
                                timer: 4000,
                                buttons: false
                            })
                        }
                        break;
                    // ... trạng thái "Đã giao"
                    case '3':

                        // chỉ khi đơn hàng đang ở trạng thái "Chờ xác nhận : 1" or "Đang giao hàng : 2" mới có thể cập nhật thành "Đã giao : 3"
                        if (cellValue == '1' || cellValue == '2') {

                            AsyncUpdateInforBill(infor_transport).then((result) => {
                                console.log("Day la ham AsyncUpdateInforBill() :" + result)
                                if (result == 'true') {
                                    swal({
                                        title: 'Thông báo',
                                        text: 'Cập nhật thành công ^.^',
                                        icon: 'success',
                                        timer: 2000,
                                        buttons: false
                                    }).then(() => {
                                        window.location.href = "<%=request.getContextPath()%>/admin/quan-ly-don-hang-root";
                                    }).onClose(() => {
                                        // Xử lý tại đây nếu người dùng tắt cửa sổ thông báo trước khi nó đóng tự động
                                        window.location.href = "<%=request.getContextPath()%>/admin/quan-ly-don-hang-root";
                                    });
                                }
                            }).catch((error) => {
                                console.log(error);
                            });


                        } else {
                            swal({
                                title: 'Thông báo',
                                text: 'Không thể cập nhật trạng thái đơn hàng về "Đã giao"',
                                icon: 'warning',
                                timer: 4000,
                                buttons: false
                            })
                        }
                        break;
                    //... trạng thái "Hủy đơn hàng"
                    case '4':

                        // chỉ khi đơn hàng đang ở trạng thái "Chờ xác nhận : 1" or "Đang giao hàng : 2" mới có thể cập nhật thành "Hủy đơn hàng : 4"
                        if (cellValue == '1' || cellValue == '2') {

                            AsyncUpdateInforBill(infor_transport).then((result) => {
                                console.log("Day la ham AsyncUpdateInforBill() :" + result)
                                if (result == 'true') {
                                    swal({
                                        title: 'Thông báo',
                                        text: 'Cập nhật thành công ^.^',
                                        icon: 'success',
                                        timer: 2000,
                                        buttons: false
                                    }).then(() => {
                                        window.location.href = "<%=request.getContextPath()%>/admin/quan-ly-don-hang-root";
                                    }).onClose(() => {
                                        // Xử lý tại đây nếu người dùng tắt cửa sổ thông báo trước khi nó đóng tự động
                                        window.location.href = "<%=request.getContextPath()%>/admin/quan-ly-don-hang-root";
                                    });
                                }
                            }).catch((error) => {
                                console.log(error);
                            });

                        } else {
                            swal({
                                title: 'Thông báo',
                                text: 'Không thể cập nhật trạng thái đơn hàng về "Hủy đơn hàng"',
                                icon: 'warning',
                                timer: 4000,
                                buttons: false
                            })
                        }
                        break;
                    default:
                        "Welcome to bug";
                }
            }
        })
    })

    // Update thông tin đơn hàng bằng Ajax
    async function updateInforBill(infor_transport) {

        return $.ajax({
            url: '${context}/AjaxBillUpdateServlet', // -- địa chỉ của server
            type: 'POST', // -- phương thức truyền : GET, POST, PUT, DELETE
            data: {
                IdBill: infor_transport.id_bill,
                IdStatusBill: infor_transport.id_status_bill
            }, // -- tham số truyền đến server
            data_type: 'text',// -- kiểu dữ liệu nhận về từ server
            success: function (response) {

                console.log("Update Infor Bill :" + response)
                if (response.toString() == 'true') return true

            },
            error: function () {
                return false;
            }
        })

    }

    // dùng kĩ thuật async/await để xử lí bất đồng bộ => nhận kết quả trả về từ Ajax function updateInforBill()
    async function AsyncUpdateInforBill(infor_transport) {
        try {
            let result = await updateInforBill(infor_transport);
            console.log("Day la ham AsyncUpdateInforBill()")
            console.log("Result function updateInforBill() :" + result)
            return result;
        } catch (error) {
            console.log(error);
            return false;
        }
    }

    function getNumberFromString(str) {

        const regex = /#(\d+)/; // sử dụng regex để tìm chuỗi con chứa số
        const match = str.match(regex); // tìm chuỗi con trong chuỗi ban đầu
        if (match && match.length > 1) { // kiểm tra nếu có chuỗi con chứa số và số lượng phần tử trong mảng match lớn hơn 1
            return match[1]; // trả về số đầu tiên trong chuỗi con
        }
        return null; // trả về null nếu không tìm thấy số

    }

</script>

</body>

</html>