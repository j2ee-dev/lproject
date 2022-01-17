<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<c:set var="lang" value="${pageContext.response.locale}"/>



<div class="col-md-12">
  <div class="tbox tag-box-v3 margin-bottom-40">
        <div class="heading heading-v2 margin-bottom-40">
        <h2>
    <c:choose>
        <c:when test="${lang=='en'}">
            ${news.titleEnglish}
        </c:when>
        <c:otherwise>
            ${news.titleBangla}
        </c:otherwise>
    </c:choose>
</h2>
</div>
<div class="news">
    <c:choose>
        <c:when test="${lang=='en'}">
            ${news.descriptionEnglish}
        </c:when>
        <c:otherwise>
            ${news.descriptionBangla}
        </c:otherwise>
    </c:choose>
</div>
</div>
</div>