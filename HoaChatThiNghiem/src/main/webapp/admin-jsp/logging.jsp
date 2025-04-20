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
    <title>Quản lý logging</title>

    <!-- ===== STYLESHEET ===== -->
    <jsp:include page="../common/admin-css.jsp"/>
</head>

<body class="app sidebar-mini rtl">
<%--    Header--%>
<%--<jsp:include page="../common/admin-header.jsp"/>--%>

<!-- Sidebar Menu -->
<jsp:include page="../common/admin-sidebar-menu.jsp"/>

<main class="app-content" id="log-page">
    <div class="app-title">
        <ul class="app-breadcrumb breadcrumb side">
            <li class="breadcrumb-item active"><a href="#"><b>Quản lý logging</b></a></li>
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
                        <div class="col-sm-3">
                            <select class="custom-select mr-sm-2" id="sorted">
                                <option selected value="0">Sắp xếp</option>
                                <option value="1">Mức độ(Level)</option>
                            </select>
                        </div>
                    </div>
                    <table class="table table-hover table-bordered bill-table" id="sampleTable">
                        <thead>
                        <tr>
                            <th class="text-center">ID</th>
                            <th class="text-center">Cấp độ</th>
                            <th class="text-center">ID User</th>
                            <th class="text-center">Người thực hiện</th>
                            <th class="text-center">Nội dung</th>
                            <th class="text-center">IP</th>
                            <th class="text-center">WebBrowser</th>
                            <th class="text-center">Thời gian</th>
                            <th class="text-center">Trạng thái</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="b" items="${ListLog}">
                            <tr>
                                <td data-bbid="${b.id_log}" class="text-center">${b.id_log}</td>
                                <c:choose>
                                    <c:when test="${b.id_level == 1}">
                                        <c:set var="bg" value="bg-success"/>
                                    </c:when>
                                    <c:when test="${b.id_level == 2}">
                                        <c:set var="bg" value="bg-warning"/>
                                    </c:when>
                                    <c:when test="${b.id_level == 3}">
                                        <c:set var="bg" value="bg-info"/>
                                    </c:when>
                                    <c:when test="${b.id_level == 4}">
                                        <c:set var="bg" value="bg-danger"/>
                                    </c:when>
                                </c:choose>
                                <td><span class="badge ${bg}">${b.getLevelWithName()}</span></td>
                                <td>${b.user_id}</td>
                                <td>${b.src}</td>
                                <td>${b.content}</td>
                                <td>${b.ip_address}</td>
                                <td>${b.web_browser}</td>
                                <td>${b.create_at}</td>
                                <td>${b.status}</td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</main>


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

    // sort
    $('#sampleTable').dataTable({
        order: [[0, 'desc']]
    });
    $('#sorted').change(function () {
        if ($(this).val() == 0) {
            $('#sampleTable').dataTable({
                destroy: true,
                order: [[0, 'desc']]
            });
            } else if ($(this).val() == 1) {
                $('#sampleTable').dataTable({
                    destroy: true,
                    order: false
                });
            }
        }
    )

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
</body>

</html>