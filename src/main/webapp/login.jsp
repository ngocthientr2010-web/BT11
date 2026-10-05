<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Đăng nhập</title>
</head>
<body>
    <div class="row justify-content-center mt-5">
        <div class="col-md-5">
            <div class="card shadow-sm">
                <div class="card-body p-4">
                    <h3 class="text-center mb-4">Đăng nhập</h3>

                    <% if (request.getAttribute("error") != null) { %>
                        <div class="alert alert-danger"><%= request.getAttribute("error") %></div>
                    <% } %>

                    <% if (request.getAttribute("success") != null) { %>
                        <div class="alert alert-success"><%= request.getAttribute("success") %></div>
                    <% } %>

                    <form action="${pageContext.request.contextPath}/login" method="post">
                        <div class="mb-3">
                            <label class="form-label">Email</label>
                            <input type="email" name="email" class="form-control" required />
                        </div>
                        <div class="mb-3">
                            <label class="form-label">Mật khẩu</label>
                            <input type="password" name="password" class="form-control" required />
                        </div>
                        <button type="submit" class="btn btn-primary w-100">Đăng nhập</button>
                    </form>

                    <p class="text-center mt-3 mb-0">
                        Bạn chưa có tài khoản?
                        <a href="${pageContext.request.contextPath}/register">Đăng ký</a>
                    </p>
                </div>
            </div>
        </div>
    </div>
</body>
</html>