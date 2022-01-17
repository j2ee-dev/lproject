<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="ata" uri="http://ataur.me/jstl" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<c:set var="lang" value="${pageContext.response.locale}"/>
<c:set var="queryString">${pageContext.request.getAttribute("javax.servlet.forward.query_string")}</c:set>
<c:set var="queryString"><c:if test="${not empty queryString}">?</c:if>${queryString}</c:set>


<jsp:include page="/WEB-INF/views/website/pages/searchlaw.jsp"/>
<div class="panel panel-primary">
    <div class="panel-heading home-alp-h text-center"><spring:message code="website.alphabeticalIndex.text" /></div>
    <div class="panel-body home-alp-order">
        <a href="act-by-alphabet.html?alp=The A" target="_blank">A</a> |&nbsp;<a href="act-by-alphabet.html?alp=The B" target="_blank">B</a> |&nbsp;<a href="act-by-alphabet.html?alp=The C" target="_blank">C</a> |&nbsp;<a href="act-by-alphabet.html?alp=The D" target="_blank">D</a> |&nbsp;<a href="act-by-alphabet.html?alp=The E" target="_blank">E</a> |&nbsp;<a href="act-by-alphabet.html?alp=The F" target="_blank">F</a> |&nbsp;<a href="act-by-alphabet.html?alp=The G" target="_blank">G</a> |&nbsp;<a href="act-by-alphabet.html?alp=The H" target="_blank">H</a> |&nbsp;<a href="act-by-alphabet.html?alp=The I" target="_blank">I</a> |&nbsp;<a href="act-by-alphabet.html?alp=The J" target="_blank">J</a> |&nbsp;<a href="act-by-alphabet.html?alp=The K" target="_blank">K</a> |&nbsp;<a href="act-by-alphabet.html?alp=The L" target="_blank">L</a> |&nbsp;<a href="act-by-alphabet.html?alp=The M" target="_blank">M</a> |&nbsp;<a href="act-by-alphabet.html?alp=The N" target="_blank">N</a> |&nbsp;<a href="act-by-alphabet.html?alp=The O" target="_blank">O</a> |&nbsp;<a href="act-by-alphabet.html?alp=The P" target="_blank">P</a> |&nbsp;<a href="act-by-alphabet.html?alp=The Q" target="_blank">Q</a> |&nbsp;<a href="act-by-alphabet.html?alp=The R" target="_blank">R</a> |&nbsp;<a href="act-by-alphabet.html?alp=The S" target="_blank">S</a> |&nbsp;<a href="act-by-alphabet.html?alp=The T" target="_blank">T</a> |&nbsp;<a href="act-by-alphabet.html?alp=The U" target="_blank">U</a> |&nbsp;<a href="act-by-alphabet.html?alp=The V" target="_blank">V</a> |&nbsp;<a href="act-by-alphabet.html?alp=The W" target="_blank">W</a> |&nbsp;<a href="act-by-alphabet.html?alp=The X" target="_blank">X</a> |&nbsp;<a href="act-by-alphabet.html?alp=The Y" target="_blank">Y</a> |&nbsp;<a href="act-by-alphabet.html?alp=The Z" target="_blank">Z</a>
    </div>
</div>

<div class="panel panel-primary">
    <div class="panel-heading home-alp-order text-center"><spring:message code="website.chronologicalIndex.text"/></div>
</div>
    <ul class="divider-2 volumes">
        <c:forEach var="volume" items="${volumes}" varStatus="loop">
            <li class="volume">
                <a class="volume-head" href="${contextPath}/volume-${volume.volumeNumber}.html">${volume.volumeHead}</a>
            </li>
        </c:forEach>
    </ul>
