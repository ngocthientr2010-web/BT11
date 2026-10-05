<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head>
    <title>${empty book ? 'Thêm sách' : 'Sửa sách'}</title>
</head>
<body>
    <h2>${empty book ? 'Thêm sách mới' : 'Cập nhật sách'}</h2>

    <form action="${pageContext.request.contextPath}/admin/books" method="post">

        <c:if test="${not empty book}">
            <input type="hidden" name="bookid" value="${book.bookid}" />
        </c:if>

        <div class="mb-3">
            <label class="form-label">Tiêu đề</label>
            <input type="text" name="title" class="form-control" value="${book.title}" required />
        </div>

        <div class="mb-3">
            <label class="form-label">Mã ISBN</label>
            <input type="number" name="isbn" class="form-control" value="${book.isbn}" />
        </div>

        <div class="mb-3">
            <label class="form-label">Nhà xuất bản</label>
            <input type="text" name="publisher" class="form-control" value="${book.publisher}" />
        </div>

        <div class="mb-3">
            <label class="form-label">Giá (VNĐ)</label>
            <input type="number" step="0.01" name="price" class="form-control" value="${book.price}" />
        </div>

        <div class="mb-3">
            <label class="form-label">Mô tả</label>
            <textarea name="description" class="form-control" rows="3">${book.description}</textarea>
        </div>

        <div class="mb-3">
            <label class="form-label">Ngày xuất bản</label>
            <input type="date" name="publishDate" class="form-control" value="${book.publishDate}" />
        </div>

        <div class="mb-3">
            <label class="form-label">Tên file ảnh bìa (ví dụ: mat-biec.jpg)</label>
            <input type="text" name="coverImage" class="form-control" value="${book.coverImage}" />
        </div>

        <div class="mb-3">
            <label class="form-label">Số lượng</label>
            <input type="number" name="quantity" class="form-control" value="${book.quantity}" />
        </div>

        <div class="mb-3">
            <label class="form-label">Tác giả</label>
            <div class="border rounded p-2" style="max-height:200px; overflow-y:auto;">
                <c:forEach var="a" items="${allAuthors}">
                    <div class="form-check">
                        <input class="form-check-input" type="checkbox" name="authorIds"
                               value="${a.authorId}" id="author${a.authorId}"
                               <c:forEach var="ba" items="${book.authors}">
                                   <c:if test="${ba.authorId == a.authorId}">checked</c:if>
                               </c:forEach>
                        />
                        <label class="form-check-label" for="author${a.authorId}">
                            ${a.authorName}
                        </label>
                    </div>
                </c:forEach>
            </div>
        </div>

        <button type="submit" class="btn btn-primary">Lưu</button>
        <a href="${pageContext.request.contextPath}/admin/books" class="btn btn-secondary">Hủy</a>
    </form>
</body>
</html>