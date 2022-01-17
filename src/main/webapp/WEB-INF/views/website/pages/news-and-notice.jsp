<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<c:set var="lang" value="${pageContext.response.locale}"/>

<div class="heading heading-v2 margin-bottom-40">
    <h2><spring:message code="website.newsAndNotice.text"/></h2>
</div>

<c:choose>
    <c:when test="${newsList.hasContent()}">
        <div class="row well well-sm">
            <c:forEach items="${newsList.getContent()}" var="news" varStatus="loop">

                <div class="result ">
                    <h3 class="r">
                        <a href="${contextPath}/news-and-notice/${news.id}.html">
                            <c:choose>
                                <c:when test="${lang=='en'}">
                                    ${news.titleEnglish}
                                </c:when>
                                <c:otherwise>
                                    ${news.titleBangla}
                                </c:otherwise>
                            </c:choose>
                        </a>
                    </h3>
                    <div class="s">
                        <c:choose>
                            <c:when test="${lang=='en'}">
                                ${fn:substring(news.descriptionEnglish,0,300)}
                            </c:when>
                            <c:otherwise>
                                ${fn:substring(news.descriptionBangla,0,300)}
                            </c:otherwise>
                        </c:choose>
                    </div>
                </div>

            </c:forEach>
        </div>
        <div class="row well well-sm">
            <div class="col-sm-4">
                <div class="text-info text-center pagination">
                        ${(p-1)*s+1} to ${((p-1)*s+s)> newsList.getTotalElements()? newsList.getTotalElements() : ((p-1)*s+s)} of ${newsList.getTotalElements()}
                </div>
            </div>
            <div class="col-sm-8">
                <nav aria-label="Page navigation">
                    <ul class="pagination">
                        <li Class="${newsList.hasPrevious()? '':'disabled hide'}"><a href="${contextPath}/search.html${queryString}&page=${newsList.previousPageable().getPageNumber()+1}">&laquo;</a></li>

                        <c:set var="initialPage" value="${(p-5)<1 ? 1 : (p-5)}"/>

                        <c:set var="endingPage" value="${9+initialPage}"/>
                        <c:if test="${newsList.getTotalPages()< endingPage}">
                            <c:set var="endingPage" value="${newsList.getTotalPages()}"/>
                            <c:set var="initialPage" value="${(endingPage -9)<1? 1: (endingPage -9)}"/>
                        </c:if>
                        <c:forEach begin="${initialPage}" end="${endingPage}" varStatus="loop">

                            <c:choose>
                                <c:when test="${p==loop.index}">
                                    <li class="active">
                                        <span>${loop.index} <span class="sr-only">(current)</span></span>
                                    </li>
                                </c:when>
                                <c:otherwise>
                                    <li>
                                        <a href="${contextPath}/search.html${queryString}&page=${loop.index}">${loop.index}</a>
                                    </li>
                                </c:otherwise>
                            </c:choose>

                        </c:forEach>
                        <li class="${newsList.hasNext()? '':'disabled hide'}"><a href="${contextPath}/search.html${queryString}&page=${newsList.nextPageable().getPageNumber()+1}">&raquo;</a></li>
                    </ul>
                </nav>
            </div>
        </div>
    </c:when>
    <c:otherwise>
        <%--<h3 class="text-danger text-center"><spring:message code="website.noResult.txt"/></h3>--%>
    </c:otherwise>
</c:choose>
