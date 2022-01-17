<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<tr class="info">
    <th colspan="3">Act Gazzeted Information</th>
</tr>
<c:choose>
    <c:when test="${empty act.actGazettedCopyList}">
        <tr class="danger">
            <td colspan="3">No Data Found!</td>
        </tr>
    </c:when>
    <c:otherwise>
        <tr>
            <td colspan="3">
                <table class="table table-responsive table-striped">
                    <thead>
                    <tr>
                        <th>Title</th>
                        <th>Attachment</th>
                        <th>Status</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${act.actGazettedCopyList}" varStatus="loop" var="GazzetedCopy">
                        <tr>
                            <td>${GazzetedCopy.title}</td>
                            <td>${GazzetedCopy.attachment}</td>
                            <td>${GazzetedCopy.status}</td>

                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </td>
        </tr>
    </c:otherwise>
</c:choose>