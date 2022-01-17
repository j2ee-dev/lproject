<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<c:set var="lang" value="${pageContext.response.locale}"/>
<div class="col-md-12">

    <section class="bg-act-section padding-bottom-20">
        <div class="row">
            <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                <div class="text-center">
                    <h3 ><spring:message code="website.indexOfLawsOfBangladesh.textbn"/></h3>
                    <h5 style="color: #ffffff">(<spring:message code="website.alphabetical.textbn"/>)</h5>
                    <h3 ><spring:message code="website.indexOfLawsOfBangladesh.text"/></h3>
                    <h5 style="color: #ffffff">(<spring:message code="website.alphabetical.text"/>)</h5>
                </div>
            </div>
        </div>
    </section>
    <section class="bt-act-section-search no-print">
        <div class="row">
            <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                <form class="form-inline part">

                    <div class="form-group">
                        <label>Short Title :</label>
                        <input class="form-control input-sm" name="actName" data-td-index="0"  type="text">
                    </div>
                    <div class="form-group">
                        <label>Act No:</label>
                        <input class="form-control input-sm"  name="actNo" data-td-index="1"  type="text">
                    </div>
                    <div class="form-group">
                        <label>Year :</label>
                        <input class="form-control input-sm" name="year" data-td-index="2"  type="text">
                    </div>
                    <div class="form-group">
                        <button class="btn btn-danger btn-u-sm" type="reset"><spring:message code="website.reset.text" /> </button>
                    </div>
                </form>
            </div>
        </div>
    </section>
    <div class="table-responsive">
        <table class="datatable removableRowTable table-search table table-striped table-bordered dt-responsive nowrap compact stripe"
               width="100%">
            <thead>
            <th><spring:message code="website.shortTitle.text"/> </th>
            <th><spring:message code="website.actno.text"/> </th>
           <th><spring:message code="website.year.text"/> </th>
            </thead>


            <c:forTokens items = "A,B,C,D,E,F,G,H,I,J,K,L,M,N,O,P,Q,R,S,T,U,V,W,X,Y,Z,1,2,3,4,5,6,7,8,9,অ,আ,ই,ঈ,উ,ঊ,ঋ,এ,ঐ,ও,ঔ,ক,খ,গ,ঘ,চ,ছ,জ,ঝ,ট,ঠ,ড,ঢ,ত,থ,দ,ধ,ন,প,ফ,ব,ভ,ম,য,র,ল,শ,ষ,স,হ,১,২,৩,৪,৫,৬,৭,৮,৯" delims = "," var = "alp">
                <c:set var="theAlp">The ${alp}</c:set>
                <c:set var="theBangladeshAlp">The Bangladesh ${alp}</c:set>
            <tbody>
                <tr class="removable"><th colspan="3"><strong>${alp}</strong></th></tr>
                <c:forEach items="${acts}" varStatus="loop" var="act">
                    <%--<c:if test="${fn:startsWith(act.shortTitle,alp) or fn:startsWith(act.shortTitle,theAlp) or fn:startsWith(act.shortTitle,theBangladeshAlp)}">--%>
                    <c:choose>
                        <c:when test = "${alp =='T' and (fn:startsWith(act.shortTitle,theAlp) or fn:startsWith(act.shortTitle,theBangladeshAlp)) }">
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
                                <td>
                                    <a href="${contextPath}/act-${act.id}.html">
                                            ${act.year}
                                    </a>
                                </td>
                            </tr>
                        </c:when>
                        <c:when test = "${alp !='T' and (fn:startsWith(act.shortTitle,alp) or fn:startsWith(act.shortTitle,theAlp) or fn:startsWith(act.shortTitle,theBangladeshAlp))}">
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
                                <td>
                                    <a href="${contextPath}/act-${act.id}.html">
                                            ${act.year}
                                    </a>
                                </td>
                            </tr>
                        </c:when>
                        <c:otherwise>

                        </c:otherwise>
                    </c:choose>
                </c:forEach>
            </tbody>
            </c:forTokens>


        </table>
    </div>
</div>