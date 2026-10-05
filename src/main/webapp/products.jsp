<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<html>
<head>
    <title>Sản phẩm</title>
</head>
<body>
    <h2 class="mb-4">Danh sách sách</h2>

    <c:if test="${empty books}">
        <p>Hiện chưa có sách nào trong hệ thống.</p>
    </c:if>

    <c:if test="${not empty books}">
        <div class="row">
            <c:forEach var="item" items="${books}">
                <div class="col-md-4 mb-4">
                    <div class="card h-100">
                        <c:choose>
                            <c:when test="${not empty item.book.coverImage}">
                                <img src="${pageContext.request.contextPath}/images/${item.book.coverImage}"
                                     class="card-img-top" alt="${item.book.title}"
                                     style="height:250px; object-fit:cover;"
                                     onerror="this.src='https://via.placeholder.com/300x250?text=No+Image';" />
                            </c:when>
                            <c:otherwise>
                                <img src="https://via.placeholder.com/300x250?text=No+Image"
                                     class="card-img-top" alt="no image" style="height:250px; object-fit:cover;" />
                            </c:otherwise>
                        </c:choose>

                        <div class="card-body">
                            <h5 class="card-title">
                                Tiêu đề:
                                <a href="${pageContext.request.contextPath}/book-detail?id=${item.book.bookid}">
                                    ${item.book.title}
                                </a>
                            </h5>
                            <p class="card-text mb-1">Mã ISBN: ${item.book.isbn}</p>
                            <p class="card-text mb-1">
                                Tác giả:
                                <c:choose>
                                    <c:when test="${not empty item.authorNames}">${item.authorNames}</c:when>
                                    <c:otherwise>(chưa cập nhật)</c:otherwise>
                                </c:choose>
                            </p>
                            <p class="card-text mb-1">Publisher: ${item.book.publisher}</p>
                            <p class="card-text mb-1">
                                Publisher_date:
                                <c:if test="${not empty item.book.publishDate}">
                                    ${item.book.publishDate}
                                </c:if>
                            </p>
                            <p class="card-text mb-1">Quantity: ${item.book.quantity}</p>
                            <p class="card-text">Review (${item.reviewCount})</p>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>

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