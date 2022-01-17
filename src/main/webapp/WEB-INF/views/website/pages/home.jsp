<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<c:set var="lang" value="${pageContext.response.locale}"/>


<div class="tbox tag-box-v3">
    <div class="heading margin-bottom-40">

        <h2><spring:message code="website.lawsOfBangladesh.text"/> </h2>
        <p><spring:message code="website.welcome.message"/> </p>

    </div>
</div>
<div class="link-effects padding-top-40 padding-bottom-40">
    <a rel="outline-outward" class="btn-u btn-u-red outline-outward" href="${contextPath}/laws-of-bangladesh.html"><i class="fa fa-mouse-pointer"></i>
        <spring:message code="website.lawsOfBangladesh.text"/>
    </a>

</div>