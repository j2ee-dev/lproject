<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<c:set var="currentPath">${pageContext.request.getAttribute("javax.servlet.forward.request_uri")}</c:set>
<c:set var="queryString">${pageContext.request.getAttribute("javax.servlet.forward.query_string")}</c:set>
<c:set var="queryString"><c:if test="${not empty queryString}">?</c:if>${queryString}</c:set>
<c:set var="linkPath" value=""/>
<style>
    .navbar-left ul
    {
      padding-left: 0px;
    }
</style>
<header class="no-print">
    <div class="container-fluid">
        <div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
            <a href="${contextpath}/"><img class="img-responsive logo"
                                           src="<spring:message code="website.logo.text"/>"
                                           alt="bangladesh -logo"></a>


        </div>
        <div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
            <c:if test="${page!='search'}">
                <div class="search-box">
                    <form cssClass="form-inline" action="${contextPath}/search.html">
                        <div class="input-group">
                            <c:set var="q">${pageContext.request.getParameter("q")}</c:set>
                            <input id="search-click" name="q"
                                   value="<c:out escapeXml="true" value="${fn:escapeXml(q)}"/>" class="form-control"
                                   placeholder="<spring:message code="website.searchby.text"/>"/>
                            <span class="input-group-btn">
                        <button class="btn-u btn-u-lg" onclick="highlightSearch()" type="submit"><i
                                class="fa fa-search"></i></button>
                    </span>
                        </div>
                    </form>
                </div>
            </c:if>

        </div>
    </div>
</header>
<!-- /header -->
<nav class="navbar navbar-inverse no-print">
    <div class="container-fluid">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse"
                    data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
        </div>
        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav navbar-left">
                <li><a href="${contextpath}/"><spring:message code="website.home.text"/> </a></li>

                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true"
                       aria-expanded="false"><spring:message code="website.lawsOfBangladesh.text"></spring:message>
                        <span class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <li><a href="${contextPath}/laws-of-bangladesh-chronological-index.html"><spring:message
                                code="website.chronologicalIndex.text"/> </a></li>
                        <li><a href="${contextPath}/laws-of-bangladesh-alphabetical-index.html"><spring:message
                                code="website.alphabeticalIndex.text"/> </a></li>
                        <li><a href="${contextPath}/search.html"><spring:message code="website.lawSearch.text"/> </a>
                        </li>
                    </ul>
                </li>

                <%--<li><a target="_blank"  href="http://bdcode.gov.bd/"><spring:message code="website.bangladeshcode.text"/></a></li>--%>





         <%--       <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true"
                       aria-expanded="false"><spring:message code="website.bangladeshcode.text"/> <span
                            class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <li><a href="${contextPath}/about-bangladesh-code.html"><spring:message
                                code="website.aboutBangladeshCode.text"/> </a></li>
                        <li><a href="${contextPath}/bangladesh-code-chronological-index.html"><spring:message
                                code="website.chronologicalIndex.text"/> </a></li>
                        <li><a href="${contextPath}/bangladesh-code-alphabetical-index.html"><spring:message
                                code="website.alphabeticalIndex.text"/> </a></li>
                    </ul>
                </li>--%>
              <%--  <li><a href="${contextPath}/news-and-notice.html"><spring:message
                        code="website.newsAndNotice.text"/></a></li>--%>
                <li><a href="${contextPath}/related-links.html"><spring:message code="website.relatedLinks.text"/></a>
                </li>

               <%-- <li><a href="${ccontextPath}/sitemap.html"><spring:message code="website.sitemap.text"/> </a></li>--%>

                <%--<li><a href="mailto:ap@legislativediv.gov.bd"><strong><spring:message code="website.email.text"/> </strong></a></li>--%>

                <li><a href="${contextPath}/contact-us.html"><spring:message code="website.contactus.text"/> </a></li>



                <li><a target="_blank"  href="http://old.bdlaws.minlaw.gov.bd/"><spring:message code="website.oldwebsite.text"/></a></li>
                <%--<li><a target="_blank"  href="${contextPath}/laws-of-bangladesh.html"><spring:message code="website.testwebsite.text"/></a></li>--%>

                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true"
                       aria-expanded="false"><spring:message code="website.help.text"/> <span class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <%--<li><a href="${contextpath}/faq.html"><spring:message code="website.faq.text"/></a></li>--%>
                        <li><a href="${contextPath}/how-to-search.html"><spring:message code="website.howToSearch.text"/></a></li>
                        <li><a href="${contextPath}/how-to-print.html"><spring:message code="website.howToPrint.text"/></a></li>
                        <li><a href="${contextPath}/glossary.html"><spring:message code="website.glossary.text"/></a></li>
                        <li><a href="${contextPath}/roman-number.html"><spring:message code="website.romanNumber.text"/></a></li>
                        <li><a href="${contextPath}/feedback-suggestion.html"><spring:message code="website.contactus.text"/></a></li>
                    </ul>
                </li>

            </ul>
            <ul class="nav navbar-nav navbar-right">
               <%-- <li class="pull-right"><a href="<spring:message code="website.locale.url"/>${currentPath}${queryString}"><spring:message htmlEscape="true" code="website.locale.text"/> </a></li>--%>
                   <button><li class="btn btn-outline-info" ><a href="${contextPath}?lang=bn"><spring:message htmlEscape="true" code="website.locale-bn.text"/> </a></li>|<li class="btn btn-outline-info"><a href="${contextPath}?lang=en"><spring:message htmlEscape="true" code="website.locale-en.text"/> </a></li></button>

                  <%-- <c:choose>
                       <c:when test = "${lang eq 'bn' }">
                           <li class="pull-right"><a href="${contextPath}?lang=en"><spring:message htmlEscape="true" code="website.locale.text"/> </a></li>
                       </c:when>
                       <c:otherwise>
                           <li class="pull-right"><a href="${contextPath}?lang=bn"><spring:message htmlEscape="true" code="website.locale.text"/> </a></li>
                       </c:otherwise>
                   </c:choose>--%>
            </ul>
        </div><!-- /.navbar-collapse -->
    </div><!-- /.container-fluid -->
</nav>


<%--
<button class="printIco no-print pull-right" onclick="window.print();return false;" style="width: 72px;height: 80px;"><i
        class="fa fa-print" style="font-size: 40px;"></i></button>--%>
