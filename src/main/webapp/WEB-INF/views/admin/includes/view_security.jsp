<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:choose>
    <c:when test="${empty view_checks}">
        <c:redirect url="http://localhost:8080/admin" />
    </c:when>
</c:choose>