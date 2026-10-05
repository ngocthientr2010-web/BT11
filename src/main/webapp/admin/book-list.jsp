<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head>
    <title>Quản lý sách</title>
</head>
<body>
    <div class="d-flex justify-content-between align-items-center mb-3">
        <h2>Danh sách sách</h2>
        <a href="${pageContext.request.contextPath}/admin/books?action=new" class="btn btn-success">
            + Thêm sách
        </a>
    </div>

    <c:if test="${empty books}">
        <p>Chưa có sách nào.</p>
    </c:if>

    <c:if test="${not empty books}">
        <table class="table table-bordered table-hover align-middle">
            <thead class="table-dark">
                <tr>
                    <th>ID</th>
                    <th>Tiêu đề</th>
                    <th>ISBN</th>
                    <th>NXB</th>
                    <th>Giá</th>
                    <th>SL</th>
                    <th style="width:180px;">Hành động</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="book" items="${books}">
                    <tr>
                        <td>${book.bookid}</td>
                        <td>${book.title}</td>
                        <td>${book.isbn}</td>
                        <td>${book.publisher}</td>
                        <td>${book.price}</td>
                        <td>${book.quantity}</td>
                        <td>
                            <a href="${pageContext.request.contextPath}/admin/books?action=edit&id=${book.bookid}"
                               class="btn btn-sm btn-primary">Sửa</a>
                            <a href="${pageContext.request.contextPath}/admin/books?action=delete&id=${book.bookid}"
                               class="btn btn-sm btn-danger"
                               onclick="return confirm('Xác nhận xóa sách này?');">Xóa</a>
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