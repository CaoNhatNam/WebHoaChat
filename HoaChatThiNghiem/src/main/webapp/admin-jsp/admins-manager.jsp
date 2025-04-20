<%@ page import="model.admin.Admin" %>
<%@ page import="utils.CommonString" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="context" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Quản lý admin</title>

    <!-- ===== STYLESHEET ===== -->
    <jsp:include page="../common/admin-css.jsp"/>
</head>

<body class="app sidebar-mini rtl">

<%--<jsp:include page="../common/admin-header.jsp"/>--%>

<jsp:include page="../common/admin-sidebar-menu.jsp"/>

<main class="app-content">
    <div class="app-title">
        <ul class="app-breadcrumb breadcrumb side">
            <li class="breadcrumb-item active"><a href="#"><b>Quản lý admin</b></a></li>
        </ul>
        <div id="clock"></div>
    </div>

    <div class="row">
        <div class="col-md-12">
            <div class="tile">
                <div class="tile-body">
                    <!-- Action Buttons -->
                    <div class="row element-button">
                        <div class="col-sm-2">
                            <a class="btn btn-add btn-sm" href="#"
                               title="Thêm" data-toggle="modal" data-target="#form-add-admin"><i
                                    class="fas fa-plus"></i> Thêm admin mới</a>
                        </div>
                        <div class="col-sm-2">
                            <a class="btn btn-delete btn-sm print-file" type="button" title="In"><i
                                    class="fas fa-print"></i> In dữ liệu</a>
                        </div>
                        <div class="col-sm-2">
                            <a class="btn btn-excel btn-sm" title="In"><i class="fas fa-file-excel"></i> Xuất
                                Excel</a>
                        </div>
                        <div class="col-sm-2">
                            <a class="btn btn-delete btn-sm pdf-file" type="button" title="In"><i
                                    class="fas fa-file-pdf"></i> Xuất PDF</a>
                        </div>
                    </div>
                    <!-- Table -->
                    <table class="table table-hover table-bordered js-copytextarea" cellpadding="0" cellspacing="0"
                           id="sampleTable">
                        <thead>
                        <tr>
                            <th>Tài khoản</th>
                            <th>Trạng thái</th>
                            <th>Quyền</th>
                            <th>Họ tên</th>
                            <th>Chức năng</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="a" items="${requestScope.adminList}">
                            <c:choose>
                                <c:when test="${a.name_status_acc=='Bình thường'}"><c:set var="bg"
                                                                                          value="bg-success"></c:set></c:when>
                                <c:when test="${a.name_status_acc=='Tạm khóa'}"><c:set var="bg"
                                                                                       value="bg-warning"></c:set></c:when>
                                <c:when test="${a.name_status_acc=='Khóa vĩnh viễn'}"><c:set var="bg"
                                                                                             value="bg-danger"></c:set></c:when>
                            </c:choose>
                            <tr>
                                <td class="userName">${a.username}</td>
                                <td class="statusAd"><span class="badge ${bg}">${a.name_status_acc}</span></td>
                                <td class="roleAd">${a.name_role_admin}</td>
                                <td class="fullnameAd">${a.fullname}</td>
                                <td>
                                    <button class="btn btn-primary btn-sm trash" type="button" title="Xóa"
                                            onclick=""><i class="fas fa-trash-alt"></i></button>
                                    <button class="btn btn-primary btn-sm edit" type="button" title="Sửa" id="show-emp"
                                            data-toggle="modal" data-target="#modal-up"><i
                                            class="fas fa-edit"></i></button>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</main>

<div class="modal fade" id="modal-up" tabindex="-1" role="dialog" aria-hidden="true" data-backdrop="static"
     data-keyboard="false">
    <div class="modal-dialog modal-dialog-centered" role="document">
        <div class="modal-content">
            <div class="modal-body">
                <div class="row">
                    <div class="form-group  col-md-12">
                            <span class="thong-tin-thanh-toan">
                                <h5>Chỉnh sửa thông tin tài khoản của admin</h5>
                            </span>
                    </div>
                </div>
                <div class="row">
                    <input type="hidden" id="UsernameUpdate" value="">
                    <div class="form-group col-md-6">
                        <label class="control-label">Mật khẩu</label>
                        <input id="InputPassNew" class="form-control" type="password" placeholder="nhập mật khẩu">
                    </div>
                    <div class="form-group col-md-6">
                        <label class="control-label">Tên admin</label>
                        <input id="InputFullName" class="form-control" type="text">
                    </div>
                    <div class="form-group col-md-6">
                        <label for="SelectRole" class="control-label">Quyền</label>
                        <select class="form-control" id="SelectRole">
                            <option value="0">-- Chọn quyền tài khoản --</option>
                            <c:forEach var="r" items="${requestScope.allRoleAdmin}">
                                <option value="${r.id_role}">${r.name_role}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group col-md-6">
                        <label for="SelectStatus" class="control-label">Trạng thái tài khoản</label>
                        <select class="form-control" id="SelectStatus">
                            <option value="0">-- Chọn trạng thái tài khoản --</option>
                            <c:forEach var="stt" items="${requestScope.allStatusAcc}">
                                <option value="${stt.id_status}">${stt.name_status}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
                <div class="d-flex justify-content-end mt-3">
                    <button id="btUpdateAdmin" class="btn btn-save mr-3">Cập nhật</button>
                    <button id="btCancel" class="btn btn-cancel" data-dismiss="modal">Hủy bỏ</button>
                </div>
            </div>
        </div>
    </div>
</div>
</div>
<div class="modal fade" id="form-add-admin" tabindex="-1" role="dialog" aria-hidden="true" data-backdrop="static"
     data-keyboard="false">
    <div class="modal-dialog modal-dialog-centered" role="document">
        <div class="modal-content">
            <div class="modal-body">
                <div class="row">
                    <div class="form-group  col-md-12">
                            <span class="thong-tin-thanh-toan">
                                <h5>Thêm tài khoản Admin</h5>
                            </span>
                    </div>
                </div>
                <div class="row">
                    <div class="form-group col-md-6">
                        <label class="control-label">Tên đăng nhập<span class="text-danger">*</span></label>
                        <input id="InputUsername" class="form-control" type="text" placeholder="Nhập tên đăng nhập">
                    </div>
                    <div class="form-group col-md-6">
                        <label class="control-label">Mật khẩu<span class="text-danger">*</span></label>
                        <input id="InputPassword" class="form-control" type="password" placeholder="Nhập mật khẩu">
                    </div>
                    <div class="form-group col-md-6">
                        <label class="control-label">Xác nhận mật khẩu<span class="text-danger">*</span></label>
                        <input id="ReInputPassword" class="form-control" type="password"
                               placeholder="Nhập lại mật khẩu">
                    </div>
                    <div class="form-group col-md-6">
                        <label for="SelectRole" class="control-label">Quyền<span class="text-danger">*</span></label>
                        <select class="form-control" id="UserRole">
                            <option value="0">-- Chọn quyền tài khoản --</option>
                            <c:forEach var="r" items="${requestScope.allRoleAdmin}">
                                <option value="${r.id_role}">${r.name_role}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group col-md-6">
                        <label for="SelectStatus" class="control-label">Trạng thái tài khoản<span
                                class="text-danger">*</span></label>
                        <select class="form-control" id="UserStatus">
                            <option value="0">-- Chọn trạng thái tài khoản --</option>
                            <c:forEach var="stt" items="${requestScope.allStatusAcc}">
                                <option value="${stt.id_status}">${stt.name_status}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
                <div class="d-flex justify-content-end mt-3">
                    <button id="btnAdd" class="btn btn-save mr-3">Thêm</button>
                    <button id="btnCancel" class="btn btn-cancel" data-dismiss="modal">Hủy bỏ</button>
                </div>
            </div>
        </div>
    </div>
</div>
</div>

<!-- ===== JAVASCRIPT ===== -->
<jsp:include page="../common/admin-js.jsp"/>
<script>
    <%--  Hiển thị danh sách sản phẩm sử dụng DataTable  --%>
    $(document).ready(function () {
        $('#sampleTable').DataTable();
    });
    $('#sampleTable .trash').on('click', function () {
        swal({
            title: 'Cảnh báo',
            text: 'Bạn có chắc chắn là muốn xóa tài khoản này?',
            buttons: ['Hủy bỏ', 'Đồng ý']
        }).then((agree) => {
                if (agree) {
                    var rowDelete = $(this).closest('tr')                       // row can be deleted
                    var username = $(this).closest('tr').find('.userName').text()    // get data id of row after click in table
                    $.ajax({    // call Ajax for action delete product
                        url: '${context}/admin/delete-account',     //-- địa chỉ server
                        type: 'POST',                                   //-- phương thức truyền : GET,POST,PUT,DELETE,...
                        data: {Username: username},                          //-- tham số truyền đến server
                        data_type: 'text',                              //-- kiểu dữ liệu nhận về từ server text,xml,json,...
                        success: (function (resultData) {
                            if (resultData.toString() == "success") {
                                rowDelete.remove();
                                swal({
                                    text: 'Đã xóa thành công.',
                                    icon: 'success',
                                    timer: 1000,
                                    buttons: false
                                });
                            }
                            else if(resultData.toString() == "fail"){
                                swal({
                                    text: 'Xóa không thành công!',
                                    icon: 'error',
                                    timer: 1000,
                                    buttons: false
                                });
                            } else if(resultData.toString() == "permission"){
                                swal({
                                    text: 'Bạn không có quyền này!.',
                                    icon: 'error',
                                    timer: 1000,
                                    buttons: false
                                });
                            }
                            else {
                                swal({
                                    text: 'Xóa không thành công.',
                                    icon: 'error',
                                    timer: 1000,
                                    buttons: false
                                });
                            }
                        }),         //-- xử lí phản hồi từ server
                        error: (function () {
                            // error no call ajax
                        })
                    })
                }
            }
        )
    });
    $('#btnCancel').on('click', function () {
        $('#InputUsername').val('')
        $('#InputPassword').val('')
        $('#ReInputPassword').val('')
        $('#UserStatus').val('0')
        $('#UserRole').val('0')
        $('#InputPassword').nextAll('.text-danger').remove()
    })
    $('.btn-add').on('click', function () {

        $("#InputUsername").on("input", () => {
            let username = $("#InputUsername").val();
            if (username.includes(" ")) {
                $("#InputUsername").nextAll(".text-danger").remove();
                $("#InputUsername").after(
                    '<div class=" text-danger">Tên đăng nhập không được chứa khoảng trắng</div>'
                );
            } else if (username.trim().length < 3 || username.trim().length > 50) {
                $("#InputUsername").nextAll(".text-danger").remove();
                $("#InputUsername").after(
                    '<div class=" text-danger">Tên đăng nhập phải có 3 kí tự đến 50 kí tự</div>'
                );
            } else {
                $("#InputUsername").nextAll(".text-danger").remove();
            }
        });
        $('#InputPassword').on('input', function () {
            let pass_admin = $('#InputPassword').val().trim()
            if (pass_admin.includes(" ")) {
                $("#InputPassword").nextAll(".text-danger").remove();
                $("#InputPassword").after(
                    '<div class=" text-danger">Mật khẩu không được chứa khoảng trắng</div>'
                );
            } else if (pass_admin.length < 8) {
                // nếu đã có cảnh báo rồi thì không cảnh báo nữa
                let existingErrorMessages = $('#InputPassword').nextAll('.text-danger');
                if (existingErrorMessages.length == 0) {
                    $('#InputPassword').after('<div class="text-danger">Mật khẩu tối thiểu 8 kí tự!</div>')
                }
            } else $('#InputPassword').nextAll('.text-danger').remove()
        })
        // check nhập lại mật khẩu
        $("#ReInputPassword").on("input", () => {
            let repass = $("#ReInputPassword").val().trim();
            if ($('#InputPassword').val().trim() !== repass && repass.length > 0) {
                let existingErrorMessages = $("#ReInputPassword").nextAll(".text-danger");
                if (existingErrorMessages.length == 0) {
                    $("#ReInputPassword").after(
                        '<div class="form-text text-danger">Mật khẩu xác nhận không khớp</div>'
                    );
                }
            } else $("#ReInputPassword").nextAll(".text-danger").remove();
        });
    })
    // thêm tài khoản admin
    $('#btnAdd').on('click', function () {
        let user_name = $('#InputUsername').val().trim()
        let pass_admin = $('#InputPassword').val().trim()
        let repass = $("#ReInputPassword").val().trim()
        let id_role_admin = $('#UserRole').val()
        let id_status_acc = $('#UserStatus').val()
        if (user_name == '' && pass_admin == '' && id_role_admin == 0 && id_status_acc == 0) {
            swalAlert('', 'Chưa có gì để thêm', 'error', 2000)
        } else if (user_name == '' || pass_admin == '' || id_role_admin == 0 || id_status_acc == 0) {
            swalAlert('Cảnh báo !!!', 'Bạn hãy nhập đầy đủ thông tin cho tài khoản này', 'error', 3000)
        } else if (pass_admin.length < 8) {
            swalAlert('Cảnh báo !!!', 'Mật khẩu phải lớn hơn 8 ký tự!', 'error', 3000)
        } else {
            $.ajax({
                url: '${context}/admin/add-account',
                type: 'POST',
                data: {
                    UserName: user_name,
                    PassAd: pass_admin,
                    RePass: repass,
                    IdRole: id_role_admin,
                    IdStatus: id_status_acc
                },
                data_type: 'text',
                success: function (resultData) {
                    if (resultData.toString() == 'success') {
                        $('#btnAdd').on('click', function () {
                            $('#InputUsername').val('')
                            $('#InputPassword').val('')

                            $('#UserStatus').val('0')
                            $('#UserRole').val('0')
                        })
                        swalAlert('', 'Thêm thành công', 'success', 2000)
                        $('btnCancel').click()
                        setTimeout(function () {
                            location.replace(location.href);
                        }, 2000);
                    } else if (resultData.toString() == 'exists') {
                        swalAlert('', 'Thêm thất bại! Tên tài khoản này đã tồn tại.', 'error', 2000)
                        $('#InputUsername').val('')
                    } else if (resultData.toString() == 'fail') {
                        swalAlert('', 'Thêm thất bại', 'error', 2000)
                    } else if (resultData.toString() == 'invalid') {
                        swalAlert('', 'Lỗi nhập dữ liệu', 'error', 2000)
                    }else if(resultData.toString() == "permission"){
                        swalAlert('', 'Bạn không có quyền này', 'error', 2000)
                    }
                    else {
                        window.location = resultData;
                    }
                },
                error: function () {
                    // error no call ajax
                }
            })
        }
    })
    // hiện dialog update
    var fullname_edit;
    var status_edit;
    var role_edit;
    $('button.edit').on('click', function () {
        // Lấy dữ liệu từ các thẻ HTML trong hàng đang chọn
        let username = $(this).closest('tr').find('.userName').text();
        var fullname_edit = $(this).closest('tr').find('.fullnameAd').text();
        var status_edit = $(this).closest('tr').find('.statusAd').text();
        var role_edit = $(this).closest('tr').find('.roleAd').text();

        // Hiển thị dữ liệu lên các ô input hoặc dialog
        $('#UsernameUpdate').val(username);
        $('#SelectStatus').val('0');
        $('#SelectRole').val('0');
        $('#InputFullName').val(fullname_edit);

        $('#InputPassNew').on('input',function () {
            let passNewAd = $('#InputPassNew').val().trim()
            if (passNewAd.length > 0 && passNewAd.length < 8) {
                let existingErrorMessages = $('#InputPassNew').nextAll('.text-danger');
                if (existingErrorMessages.length == 0) {
                    $('#InputPassNew').after('<div class="text-danger">Mật khẩu tối thiểu 8 kí tự!</div>');
                }
            } else $('#InputPassNew').nextAll('.text-danger').remove()
        })
        $('#InputFullName').on('input',()=>{
            let fullname = $('#InputFullName').val().trim()
            if (fullname.length > 255) {
                let existingErrorMessages = $('#InputFullName').nextAll('.text-danger');
                if (existingErrorMessages.length == 0) {
                    $('#InputFullName').after('<div class="text-danger">Họ và tên tối đa chỉ được 255 kí tự!</div>');
                }
            } else $('#InputFullName').nextAll('.text-danger').remove()
        })

    });
    $('#btUpdateAdmin').on('click', function () {
        let username = $('#UsernameUpdate').val().trim()
        let pass_admin = $('#InputPassNew').val().trim()
        let id_role_admin = $('#SelectRole').val()
        let id_status_acc = $('#SelectStatus').val()
        let fullname = $('#InputFullName').val()

        if (pass_admin.length > 0 && pass_admin.length < 8) {
            swalAlert('Cảnh báo !!!', 'Vui lòng nhập lại mật khẩu mới đúng yêu cầu!', 'error', 3000)
        } else {

            $.ajax({
                url: '${context}/admin/update-account',
                type: 'POST',
                data: {
                    UserName: username,
                    PassAd: pass_admin,
                    IdRole: id_role_admin,
                    IdStatus: id_status_acc,
                    Fullname: fullname
                },
                data_type: 'text',
                success: function (resultData) {
                    if (resultData.toString() == 'success') {
                        swalAlert('', 'Cập nhật thành công.', 'success', 2000)
                        $('btnCancel').click()
                        setTimeout(function () {
                            location.replace(location.href);
                        }, 2000);

                    } else if (resultData.toString() == 'error') {
                        swalAlert('', 'Lỗi', 'error', 1500)
                    } else if (resultData.toString() == 'wrong') {
                        swalAlert('', 'Nhập sai dữ liệu', 'error', 1500)
                    } else if (resultData.toString() == 'seal') {
                        swalAlert('', 'Tài khoản này đã bị khóa vĩnh viễn!', 'error', 1500)
                    } else if (resultData.toString() == 'permission') {
                        swalAlert('', 'Bạn không có quyền cập nhật!', 'error', 1500)
                    } else {
                       window.location = resultData
                    }
                },
                error: function () {
                    // error no call ajax
                }
            })
        }
    })
    $('#modal-up #btCancel').on('click', function () {
        $('#InputUsername').val('')
        $('#InputPassNew').val('')
        $('#InputRole').val()
        $('#InputStatus').val()
        $('#SelectStatus').nextAll('.text-danger').remove()
        $('#SelectRole').nextAll('.text-danger').remove()
        $('#InputPassNew').nextAll('.text-danger').remove()
    });

    function swalAlert(title, text, icon, timer) {
        swal({
            title: title,
            text: text,
            icon: icon,
            timer: timer,
            buttons: false
        });
    }

    function checkCompete() {

    }
</script>
</body>
</html>