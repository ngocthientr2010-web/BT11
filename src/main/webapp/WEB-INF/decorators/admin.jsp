<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>SiteMesh example: <sitemesh:write property="title"/></title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <sitemesh:write property="head"/>

    <style>
        body { display: flex; flex-direction: column; min-height: 100vh; }
        main { flex: 1; }
        footer { background-color: #212529; color: #fff; padding: 20px 0; margin-top: 40px; }
        .navbar-admin { background-color: #343a40; }
    </style>
</head>
<body>

<!-- ===== HEADER (ADMIN) ===== -->
<nav class="navbar navbar-expand-lg navbar-dark navbar-admin">
    <div class="container">
        <a class="navbar-brand" href="${pageContext.request.contextPath}/admin/dashboard">
            QLBH Admin Panel
        </a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navMenuAdmin">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navMenuAdmin">
            <ul class="navbar-nav ms-auto">
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/home">Trang Chủ</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/products">Sản phẩm</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link active" href="${pageContext.request.contextPath}/admin/dashboard">Trang quản trị</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/logout">Đăng xuất</a>
                </li>
            </ul>
        </div>
    </div>
</nav>

<!-- ===== NỘI DUNG TRANG ===== -->
<main class="container my-4">
    <sitemesh:write property="body"/>
</main>

<!-- ===== FOOTER ===== -->
<footer class="text-center">
    <div class="container">
        <p class="mb-1">Họ tên: <strong>Trần Ngọc Thiện</strong> &nbsp;|&nbsp; MSSV: <strong>24110336</strong> &nbsp;|&nbsp; Mã đề: <strong>01</strong></p>
        <p class="mb-0">&copy; 2026 QLBH Book Store - Admin</p>
    </div>
</footer>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>