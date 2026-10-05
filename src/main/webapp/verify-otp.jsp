<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Xác nhận OTP</title>
</head>
<body>
    <div class="row justify-content-center mt-5">
        <div class="col-md-5">
            <div class="card shadow-sm">
                <div class="card-body p-4">
                    <h3 class="text-center mb-3">Xác nhận đăng ký</h3>
                    <p class="text-center text-muted">
                        Mã OTP đã được gửi tới email: <strong>${email}</strong>
                    </p>

                    <% if (request.getAttribute("error") != null) { %>
                        <div class="alert alert-danger"><%= request.getAttribute("error") %></div>
                    <% } %>

                    <form action="${pageContext.request.contextPath}/verify-otp" method="post">
                        <div class="mb-3">
                            <label class="form-label">Nhập mã OTP</label>
                            <input type="text" name="otp" class="form-control" maxlength="6" required />
                        </div>
                        <button type="submit" class="btn btn-primary w-100">Xác nhận</button>
                    </form>

                    <p class="text-center mt-3 mb-0">
                        <a href="${pageContext.request.contextPath}/register">Quay lại đăng ký</a>
                    </p>
                </div>
            </div>
        </div>
    </div>
</body>
</html>