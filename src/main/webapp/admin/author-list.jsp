<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<html>
<head>
    <title>Quản lý tác giả</title>
</head>
<body>
    <div class="d-flex justify-content-between align-items-center mb-3">
        <h2>Danh sách tác giả</h2>
        <a href="${pageContext.request.contextPath}/admin/authors?action=new" class="btn btn-success">
            + Thêm tác giả
        </a>
    </div>

    <c:if test="${empty authors}">
        <p>Chưa có tác giả nào.</p>
    </c:if>

    <c:if test="${not empty authors}">
        <table class="table table-bordered table-hover">
            <thead class="table-dark">
                <tr>
                    <th>ID</th>
                    <th>Họ tên</th>
                    <th>Ngày sinh</th>
                    <th style="width:180px;">Hành động</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="author" items="${authors}">
                    <tr>
                        <td>${author.authorId}</td>
                        <td>${author.authorName}</td>
                        <td>
                            ${author.dateOfBirth}
                        </td>
                        <td>
                            <a href="${pageContext.request.contextPath}/admin/authors?action=edit&id=${author.authorId}"
                               class="btn btn-sm btn-primary">Sửa</a>
                            <a href="${pageContext.request.contextPath}/admin/authors?action=delete&id=${author.authorId}"
                               class="btn btn-sm btn-danger"
                               onclick="return confirm('Xác nhận xóa tác giả này?');">Xóa</a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

        <!-- Phân trang -->
        <nav>
            <ul class="pagination">
                <c:if test="${currentPage > 1}">
                    <li class="page-item">
                        <a class="page-link" href="?page=${currentPage - 1}">&laquo; Trước</a>
                    </li>
                </c:if>

                <c:forEach begin="1" end="${totalPages}" var="i">
                    <li class="page-item ${i == currentPage ? 'active' : ''}">
                        <a class="page-link" href="?page=${i}">${i}</a>
                    </li>
                </c:forEach>

                <c:if test="${currentPage < totalPages}">
                    <li class="page-item">
                        <a class="page-link" href="?page=${currentPage + 1}">Sau &raquo;</a>
                    </li>
                </c:if>
            </ul>
        </nav>
    </c:if>
</body>
</html>