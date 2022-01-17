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
            <!-- <div class="heading heading-v2 margin-bottom-40">
                <h2>Volume List</h2>
            </div> -->
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
                                       aria-haspopup="true" aria-expanded="false" style="color: white;padding-right: 5px">
                                       <spring:message
                                               code="website.chronologicalIndex.text"/>
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
                    <h1 style="color: white;font-weight: bold">${fn:substring(alphabet,3, 5)}</h1>
                    <%--<h4><spring:message code="website.volume.text"/> ${volume.volumeName}</h4>--%>
                    <%--<p class="text-color-white">${volume.volumeHead}</p>--%>
                </div>
            </div>
        </div>
    </section>
    <section class="bt-act-section-search">
        <div class="row">
            <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                <form class="form-inline part">
                    <div class="form-group">
                        <label>Year :</label>
                        <input class="form-control input-sm" name="year" data-td-index="2" type="text">
                    </div>
                    <div class="form-group">
                        <label>Act Name :</label>
                        <input class="form-control input-sm" name="actName" data-td-index="0" type="text">
                    </div>
                    <div class="form-group">
                        <label>Act Number:</label>
                        <input class="form-control input-sm" name="actNo" data-td-index="1" type="text">
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
            <th><spring:message code="website.shortTitle.text"/></th>
            <th><spring:message code="website.actno.text"/></th>
            <th hidden><spring:message code="website.year.text"/></th>
            </thead>
            <tbody>
            <c:forEach items="${acts}" varStatus="loop" var="act">
                <tr>
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
                    <td hidden>
                        <a href="${contextPath}/act-${act.id}.html">
                                ${act.year}
                        </a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
