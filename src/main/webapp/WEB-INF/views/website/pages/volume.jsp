<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<c:set var="lang" value="${pageContext.response.locale}"/>

<div class="col-md-12">
    <section class="bg-top">
        <div class="row">

            <div class="col-md-6 acts-top">
                <a class="pull-left" href="${contextPath}/laws-of-bangladesh.html"><i class="fa fa-arrow-left"
                                                                                      title="Back"
                                                                                      aria-hidden="true"></i></a>
                <%--<a href="#" class="pull-right"><spring:message code="website.actList.text"/></a>--%>
            </div>
            <div class=" acts-form col-md-6">

                    <span class="pull-right">
                        <span class="collapse navbar-collapse">

                            <ul class="dropdown-list nav navbar-nav navbar-right">
                                <li class="dropdown">
                                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button"
                                       aria-haspopup="true" aria-expanded="false">
                                  ${volume.volumeHead}
                                  <span class="caret"></span>
                                    </a>
                                <ul class="dropdown-menu">
                                   <li><a href="${contextPath}/chronological-index-acts.html"><spring:message
                                           code="website.chronologicalIndex.text"/></a></li>
                                   <c:forEach var="vol" varStatus="loop" items="${volumes}">
                                       <li><a href="${contextPath}/volume-${vol.volumeNumber}.html">${vol.volumeHead}</a></li>
                                   </c:forEach>
                                </ul>
                            </li>
                          </ul>
                        </span>
                    </span>
            </div>
        </div>
    </section>
    <section class="bg-act-section padding-bottom-20">
        <div class="row">
            <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                <div class="text-center">
                    <c:choose>
                        <c:when test="${volume.isBangla==true}">
                            <h3>লজ অব বাংলাদেশ</h3>
                        </c:when>
                        <c:otherwise>
                            <h3>Laws of Bangladesh</h3>
                        </c:otherwise>
                    </c:choose>

                    <%--<h4><spring:message code="website.volume.text"/> ${volume.volumeName}</h4>--%>
                    <p class="text-color-white">${volume.volumeHead}</p>
                </div>
            </div>
        </div>
    </section>
    <section class="bt-act-section-search">
        <div class="row">
            <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                <form class="form-inline part">
                    <div class="form-group">
                        <c:choose>
                            <c:when test="${volume.isBangla==true}">
                                <label>বছর :</label>
                            </c:when>
                            <c:otherwise>
                                <label>Year :</label>
                            </c:otherwise>
                        </c:choose>
                        <input class="form-control input-sm" name="year" data-td-index="0" type="text">
                    </div>
                    <div class="form-group">
                        <c:choose>
                            <c:when test="${volume.isBangla==true}">
                                <label>আইনের নাম:</label>
                            </c:when>
                            <c:otherwise>
                                <label>Act Name:</label>
                            </c:otherwise>
                        </c:choose>
                        <input class="form-control input-sm" name="actName" data-td-index="1" type="text">
                    </div>
                    <div class="form-group">
                        <c:choose>
                            <c:when test="${volume.isBangla==true}">
                                <label>আইন নম্বর:</label>
                            </c:when>
                            <c:otherwise>
                                <label>Act Number :</label>
                            </c:otherwise>
                        </c:choose>
                        <input class="form-control input-sm" name="actNo" data-td-index="2" type="text">
                    </div>

                    <div class="form-group">
                        <button class="btn btn-danger btn-u-sm" type="reset"><spring:message
                                code="website.reset.text"/></button>
                    </div>
                </form>
            </div>
        </div>
    </section>
    <div class="table-responsive">
        <table class="datatable table-search table table-striped table-bordered dt-responsive nowrap compact stripe"
               width="100%">
            <thead>
            <th hidden><spring:message code="website.year.text"/></th>
            <th><c:choose>
                <c:when test="${volume.isBangla==true}">
                    সংক্ষিপ্ত শিরোনাম
                </c:when>
                <c:otherwise>
                        Short Title
                </c:otherwise>
            </c:choose></th>
            <th><c:choose>
                <c:when test="${volume.isBangla==true}">
                    আইন নম্বর
                </c:when>
                <c:otherwise>
                    Act No
                </c:otherwise>
            </c:choose></th>
            </thead>
            <tbody>
            <c:forEach items="${acts}" varStatus="loop" var="act">
                <c:if test="${act.shortTitle!='গণপ্রজাতন্ত্রী বাংলাদেশের সংবিধান'}">
                    <tr>
                        <td hidden>
                            <a href="${contextPath}/act-${act.id}.html">
                                    ${act.year}
                            </a>
                        </td>
                        <td>
                            <c:if test="${act.repealedAct.status==true}">
                                <c:if test="${act.isBanglaAct==false}">
                                    <a class="text-style-repealed" href="${contextPath}/act-${act.id}.html">
                                            ${act.shortTitle} [Repealed]
                                    </a>
                                </c:if>
                            </c:if>
                            <c:if test="${act.repealedAct.status==true}">
                                <c:if test="${act.isBanglaAct==true}">
                                    <a class="text-style-repealed" href="${contextPath}/act-${act.id}.html">
                                            ${act.shortTitle} [রহিত]
                                    </a>
                                </c:if>
                            </c:if>
                            <c:if test="${act.repealedAct.status==null}">
                                <a href="${contextPath}/act-${act.id}.html">
                                        ${act.shortTitle}
                                </a>
                            </c:if>
                        </td>
                        <td>
                            <a href="${contextPath}/act-${act.id}.html">
                                    ${act.number}
                            </a>
                        </td>
                    </tr>
                </c:if>

            </c:forEach>
            </tbody>
        </table>
    </div>
</div>