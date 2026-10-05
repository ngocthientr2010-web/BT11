<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Đăng ký</title>
</head>
<body>
    <div class="row justify-content-center mt-5">
        <div class="col-md-5">
            <div class="card shadow-sm">
                <div class="card-body p-4">
                    <h3 class="text-center mb-4">Đăng ký tài khoản</h3>

                    <% if (request.getAttribute("error") != null) { %>
                        <div class="alert alert-danger"><%= request.getAttribute("error") %></div>
                    <% } %>

                    <form action="${pageContext.request.contextPath}/register" method="post">
                        <div class="mb-3">
                            <label class="form-label">Họ tên</label>
                            <input type="text" name="fullname" class="form-control" required />
                        </div>
                        <div class="mb-3">
                            <label class="form-label">Email</label>
                            <input type="email" name="email" class="form-control" required />
                        </div>
                        <div class="mb-3">
                            <label class="form-label">Số điện thoại</label>
                            <input type="text" name="phone" class="form-control" pattern="[0-9]*" />
                        </div>
                        <div class="mb-3">
                            <label class="form-label">Mật khẩu</label>
                            <input type="password" name="password" class="form-control" required />
                        </div>
                        <div class="mb-3">
                            <label class="form-label">Xác nhận mật khẩu</label>
                            <input type="password" name="confirmPassword" class="form-control" required />
                        </div>
                        <button type="submit" class="btn btn-success w-100">Đăng ký</button>
                    </form>

                    <p class="text-center mt-3 mb-0">
                        Bạn đã có tài khoản?
                        <a href="${pageContext.request.contextPath}/login">Đăng nhập</a>
                    </p>
                </div>
            </div>
        </div>
    </div>
</body>
</html>