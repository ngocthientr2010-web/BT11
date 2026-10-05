<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head>
    <title>Chi tiết sách</title>
</head>
<body>

    <c:if test="${empty book}">
        <p>Không tìm thấy sách.</p>
    </c:if>

    <c:if test="${not empty book}">

        <!-- Bảng thông tin sách -->
        <div class="row border rounded mb-4 p-3">
            <div class="col-md-4 text-center">
                <c:choose>
                    <c:when test="${not empty book.coverImage}">
                        <img src="${pageContext.request.contextPath}/images/${book.coverImage}"
                             class="img-fluid" alt="${book.title}"
                             style="max-height:300px; object-fit:cover;"
                             onerror="this.src='https://via.placeholder.com/250x300?text=No+Image';" />
                    </c:when>
                    <c:otherwise>
                        <img src="https://via.placeholder.com/250x300?text=No+Image"
                             class="img-fluid" alt="no image" />
                    </c:otherwise>
                </c:choose>
            </div>
            <div class="col-md-8">
                <p><strong>Tiêu đề:</strong> ${book.title}</p>
                <p><strong>Mã ISBN:</strong> ${book.isbn}</p>
                <p>
                    <strong>Tác giả:</strong>
                    <c:if test="${empty book.authors}">(chưa cập nhật)</c:if>
                    <c:forEach var="a" items="${book.authors}" varStatus="st">
                        ${a.authorName}<c:if test="${!st.last}">, </c:if>
                    </c:forEach>
                </p>
                <p><strong>Publisher:</strong> ${book.publisher}</p>
                <p><strong>Publisher_date:</strong> ${book.publishDate}</p>
                <p><strong>Quantity:</strong> ${book.quantity}</p>
                <p><strong>Reviews:</strong> ${reviews.size()}</p>
            </div>
        </div>

        <!-- Danh sách review -->
        <div class="border rounded mb-4 p-3">
            <h5>Reviews</h5>

            <c:if test="${empty reviews}">
                <p>Chưa có đánh giá nào cho sách này.</p>
            </c:if>

            <c:forEach var="rv" items="${reviews}">
                <div class="border-bottom py-2">
                    <p class="mb-1">
                        <strong>${rv.user.fullname}</strong>
                        <c:if test="${not empty rv.rating}">
                            — ${rv.rating}/5 ⭐
                        </c:if>
                    </p>
                    <p class="mb-0">${rv.reviewText}</p>
                </div>
            </c:forEach>
        </div>

        <!-- Form thêm review -->
        <div class="border rounded p-3">
            <h5>Review của bạn</h5>

            <c:choose>
                <c:when test="${sessionScope.user == null}">
                    <p>Bạn cần <a href="${pageContext.request.contextPath}/login">đăng nhập</a> để gửi đánh giá.</p>
                </c:when>
                <c:otherwise>
                    <form action="${pageContext.request.contextPath}/book-detail" method="post">
                        <input type="hidden" name="bookid" value="${book.bookid}" />

                        <div class="mb-3">
                            <label class="form-label">Đánh giá (1-5 sao)</label>
                            <select name="rating" class="form-select" style="max-width:150px;">
                                <option value="">-- Chọn --</option>
                                <option value="1">1</option>
                                <option value="2">2</option>
                                <option value="3">3</option>
                                <option value="4">4</option>
                                <option value="5">5</option>
                            </select>
                        </div>

                        <div class="mb-3">
                            <label class="form-label">Nội dung đánh giá</label>
                            <textarea name="reviewText" class="form-control" rows="3" required></textarea>
                        </div>

                        <button type="submit" class="btn btn-primary">Submit</button>
                    </form>
                </c:otherwise>
            </c:choose>
        </div>

    </c:if>

</body>
</html>