<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head>
    <title>${empty author ? 'Thêm tác giả' : 'Sửa tác giả'}</title>
</head>
<body>
    <h2>${empty author ? 'Thêm tác giả mới' : 'Cập nhật tác giả'}</h2>

    <form action="${pageContext.request.contextPath}/admin/authors" method="post">

        <!-- Nếu đang sửa, giữ lại authorId để service biết là update -->
        <c:if test="${not empty author}">
            <input type="hidden" name="authorId" value="${author.authorId}" />
        </c:if>

        <div class="mb-3">
            <label class="form-label">Họ tên tác giả</label>
            <input type="text" name="authorName" class="form-control"
                   value="${author.authorName}" required />
        </div>

        <div class="mb-3">
            <label class="form-label">Ngày sinh</label>
            <input type="date" name="dateOfBirth" class="form-control"
                   value="${author.dateOfBirth}" />
        </div>

        <button type="submit" class="btn btn-primary">Lưu</button>
        <a href="${pageContext.request.contextPath}/admin/authors" class="btn btn-secondary">Hủy</a>
    </form>
</body>
</html>