<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Trang Quản Trị</title>
</head>
<body>
    <h2>Dashboard Quản Trị</h2>
    <p>Chào mừng đến khu vực quản trị hệ thống QLBH.</p>

    <div class="row mt-4">
        <div class="col-md-4 mb-3">
            <div class="card">
                <div class="card-body">
                    <h5 class="card-title">Quản lý tác giả</h5>
                    <p class="card-text">Thêm, sửa, xóa thông tin tác giả.</p>
                    <a href="${pageContext.request.contextPath}/admin/authors" class="btn btn-primary">
                        Vào quản lý
                    </a>
                </div>
            </div>
        </div>

        <div class="col-md-4 mb-3">
            <div class="card">
                <div class="card-body">
                    <h5 class="card-title">Quản lý sách</h5>
                    <p class="card-text">Thêm, sửa, xóa thông tin sách.</p>
                    <a href="${pageContext.request.contextPath}/admin/books" class="btn btn-primary">
                        Vào quản lý
                    </a>
                </div>
            </div>
        </div>
    </div>
</body>
</html>