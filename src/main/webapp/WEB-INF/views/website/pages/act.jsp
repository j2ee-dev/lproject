<%@ page import="org.jsoup.nodes.Document" %>
<%@ page import="org.jsoup.Jsoup" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://ataur.me/jstl" prefix="ata" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<c:set var="lang" value="${pageContext.response.locale}"/>
<c:set var="footnoteNo" value="1" scope="page"/>
<c:set var="sectionHead" value=""/>
<div class="col-md-12 boxed-layout  <c:if test="${hl!=null}">searchable</c:if>">
    <section class="bg-top no-print">
        <div class="row">
            <div class="col-md-6 acts-top">
                <a class="pull-left" href="${contextPath}/volume-${volume.volumeNumber}.html"><i class="fa fa-arrow-left"
                                                                                       title="Back"
                                                                                       aria-hidden="true"></i></a>
            </div>
            <div class=" acts-form col-md-6">
                <div class="input-group pull-right">
					<span class="collapse navbar-collapse">
					  <ul class="dropdown-list nav navbar-nav navbar-right">
						<li class="dropdown">
							<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button"
                               aria-haspopup="true" aria-expanded="false">
							  ${volume.volumeHead}
							  <span class="caret"></span>
							</a>
							<ul class="dropdown-menu">
							   <li>
								   <a href="${contextPath}/chronological-index-acts.html">
										<spring:message code="website.chronologicalIndex.text"/>
								   </a>
							   </li>
							   <c:forEach var="vol" varStatus="loop" items="${volumes}">
                                   <li><a href="${contextPath}/volume-${vol.volumeNumber}.html">${vol.volumeHead}</a></li>
                               </c:forEach>
							</ul>
						</li>
					  </ul>
                	</span>
                </div>
            </div>
        </div>
    </section>
    <section class="bg-act-section padding-bottom-20">
        <div class="row">
            <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                <div class="text-center">
                    <h3>
                        <ata:footnote text="${act.shortTitle}" footnoteNo="${footnoteNo}" count="count"
                                      locale='${act.isBanglaAct==true? "bn":"en"}' footnoteTotal="footnoteTotal"
                                      footnotes="${act.shortTitleFootnoteList}"/>
                        <c:set var="footnoteNo" value="${count}"/>
                        <c:set var="footnoteList">${footnoteList} ${footnoteTotal}</c:set>
                    </h3>
                    <c:choose>
                    <c:when test="${fn:containsIgnoreCase(act.shortTitleType, 'CST')}">

                    </c:when>
                    <c:when test="${fn:containsIgnoreCase(act.shortTitleType, 'APO')}">
                        <h4 style="color: #fff;"> (
                            <c:choose>
                                <c:when test="${act.isBanglaAct==true}">

                                    ${act.year} সনের ${act.number} নং
                                    <c:choose>
                                        <c:when test="${act.isOrdinance==true}">অধ্যাদেশ</c:when>
                                        <c:otherwise>আইন</c:otherwise>
                                    </c:choose>

                                </c:when>
                                <c:otherwise>
                                    <c:choose>
                                        <c:when test="${act.isOrdinance==true}">Ordinance</c:when>
                                        <c:otherwise>ACTING PRESIDENT'S ORDER</c:otherwise>
                                    </c:choose>
                                    NO. ${act.number} OF ${act.year}
                                </c:otherwise>
                            </c:choose>

                            )
                        </h4>
                    </c:when>
                    <c:when test="${fn:containsIgnoreCase(act.shortTitleType, 'BNO')}">
                        <h4 style="color: #fff;"> (
                            <c:choose>
                                <c:when test="${act.isBanglaAct==true}">

                                    ${act.year} সনের ${act.number} নং
                                    <c:choose>
                                        <c:when test="${act.isOrdinance==true}">অধ্যাদেশ</c:when>
                                        <c:otherwise>আইন</c:otherwise>
                                    </c:choose>

                                </c:when>
                                <c:otherwise>
                                    <c:choose>
                                        <c:when test="${act.isOrdinance==true}">Ordinance</c:when>
                                        <c:otherwise>ACTING PRESIDENT'S ORDER</c:otherwise>
                                    </c:choose>
                                    NO. ${act.number} OF ${act.year}
                                </c:otherwise>
                            </c:choose>

                            )
                        </h4>
                    </c:when>
                    <c:when test="${fn:containsIgnoreCase(act.shortTitleType, 'PSO')}">
                        <h4 style="color: #fff;"> (
                            <c:choose>
                                <c:when test="${act.isBanglaAct==true}">

                                    ${act.year} সনের ${act.number} নং
                                    <c:choose>
                                        <c:when test="${act.isOrdinance==true}">অধ্যাদেশ</c:when>
                                        <c:otherwise>আইন</c:otherwise>
                                    </c:choose>

                                </c:when>
                                <c:otherwise>
                                    <c:choose>
                                        <c:when test="${act.isOrdinance==true}">Ordinance</c:when>
                                        <c:otherwise>PRESIDENT'S ORDER</c:otherwise>
                                    </c:choose>
                                    NO. ${act.number} OF ${act.year}
                                </c:otherwise>
                            </c:choose>

                            )
                        </h4>

                    </c:when>
                    <c:when test="${fn:containsIgnoreCase(act.shortTitleType, 'BPSO')}">
                        <h4 style="color: #fff;"> (
                            <c:choose>
                                <c:when test="${act.isBanglaAct==true}">

                                    ${act.year} সনের ${act.number} নং
                                    <c:choose>
                                        <c:when test="${act.isOrdinance==true}">অধ্যাদেশ</c:when>
                                        <c:otherwise>আইন</c:otherwise>
                                    </c:choose>

                                </c:when>
                                <c:otherwise>
                                    <c:choose>
                                        <c:when test="${act.isOrdinance==true}">Ordinance</c:when>
                                        <c:otherwise>BANGLADESH PRESIDENT'S ORDER</c:otherwise>
                                    </c:choose>
                                    NO. ${act.number} OF ${act.year}
                                </c:otherwise>
                            </c:choose>

                            )
                        </h4>
                    </c:when>
                    <c:when test="${fn:containsIgnoreCase(act.shortTitleType, 'EPA')}">
                        <h4 style="color: #fff;"> (
                            <c:choose>
                                <c:when test="${act.isBanglaAct==true}">

                                    ${act.year} সনের ${act.number} নং
                                    <c:choose>
                                        <c:when test="${act.isOrdinance==true}">অধ্যাদেশ</c:when>
                                        <c:otherwise>আইন</c:otherwise>
                                    </c:choose>

                                </c:when>
                                <c:otherwise>
                                    <c:choose>
                                        <c:when test="${act.isOrdinance==true}">Ordinance</c:when>
                                        <c:otherwise>EAST PAKISTAN ACT</c:otherwise>
                                    </c:choose>
                                    NO. ${act.number} OF ${act.year}
                                </c:otherwise>
                            </c:choose>

                            )
                        </h4>
                    </c:when>
                    <c:when test="${fn:containsIgnoreCase(act.shortTitleType, 'EPO')}">
                        <h4 style="color: #fff;"> (EAST PAKISTAN ORDINANCE NO. ${act.number} OF ${act.year})</h4>
                    </c:when>
                    <c:when test="${fn:containsIgnoreCase(act.shortTitleType, 'BENR')}">
                        <h4 style="color: #fff;"> (BENGAL REGULATION ${act.number} OF ${act.year})
                        </h4>
                    </c:when>
                    <c:when test="${fn:containsIgnoreCase(act.shortTitleType, 'BENA')}">
                        <h4 style="color: #fff;"> (BENGAL ACT NO. ${act.number} OF ${act.year})
                        </h4>
                    </c:when>
                    <c:when test="${fn:containsIgnoreCase(act.shortTitleType, 'EBR')}">
                        <h4 style="color: #fff;"> (EAST BENGAL REGULATION NO. ${act.number} OF ${act.year})
                        </h4>
                    </c:when>
                    <c:when test="${fn:containsIgnoreCase(act.shortTitleType, 'EPNR')}">
                        <h4 style="color: #fff;"> (EAST PAKISTAN REGULATION NO. ${act.number} OF ${act.year})
                        </h4>
                    </c:when>
                    <c:when test="${fn:containsIgnoreCase(act.shortTitleType, 'REG')}">
                        <h4 style="color: #fff;"> ( REGULATION ${act.number} OF ${act.year})
                        </h4>
                    </c:when>
                    <c:otherwise>
                        <h4 style="color: #fff;"> (
                            <c:choose>
                                <c:when test="${act.isBanglaAct==true}">

                                    ${act.year} সনের ${act.number} নং
                                    <c:choose>
                                        <c:when test="${act.isOrdinance==true}">অধ্যাদেশ</c:when>
                                        <c:otherwise>আইন</c:otherwise>
                                    </c:choose>

                                </c:when>
                                <c:otherwise>
                                    <c:choose>
                                        <c:when test="${act.isOrdinance==true}">Ordinance</c:when>
                                        <c:otherwise>ACT</c:otherwise>
                                    </c:choose>
                                    NO. ${act.number} OF ${act.year}
                                </c:otherwise>
                            </c:choose>

                            )
                        </h4>
                    </c:otherwise>
                </c:choose>
                </div>
            </div>
        </div>
    </section>
    <section class="bt-act-section-search no-print">
        <div class="row">
            <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                <form class="form-inline part">

                    <div class="form-group">
                        <input class="form-control input-sm" name="actName" data-search=".search-here p" type="text"/>
                        <i class="fa fa-search" style="color: #ffffff"></i>
                    </div>
                        <div class="form-group pull-right text-info publish-date">
                            <c:if test="${not empty act.publishDate}">[ ${act.publishDate}   ]</c:if>
                        </div>
                </form>
            </div>
        </div>
    </section>
    <c:if test="${act.repealedAct.status==true}">
        <section class="bt-act-repealed margin-top-20 margin-bottom-20">
            <strong class="text-danger text-center text-style-repealed">${act.repealedAct.description}</strong>
        </section>
    </c:if>
    <section
            class="padding-bottom-20 <c:if test="${act.repealedAct.status==true}"><c:out value="${act.isBanglaAct==true? 'bn-repealed':'repealed'}"/></c:if>">
        <c:if test="${act.mappingAct!=null}">
            <c:if test="${act.isBanglaAct==true}">
                <a class="text-success pull-right no-print open-text-size" href="${contextPath}/act-${act.mappingAct}.html">
                    <%--[ ইংরেজি ভার্সন ]--%>
                    [Open English Version]
                </a>
            </c:if>
            <c:if test="${act.isBanglaAct==false}">
                <a class="text-success pull-right no-print open-text-size" href="${contextPath}/act-${act.mappingAct}.html">
                    [ Open Bangla Version ]
                </a>
            </c:if>
        </c:if>
        <span class="clearfix"></span>
        <a class="btn no-print view-full-law-button" href="${contextPath}/act-details-${act.id}.html">
            <%--<spring:message code="website.seeFullAct.${act.isBanglaAct==true? \"bn\": \"en\"}"/>--%>
                <c:choose>
                <c:when test="${act.isBanglaAct==false}">
                    <img src="/resources/website/assets/img/full_act_english.jpg"/>
                </c:when>
                <c:otherwise>
                    <img src="/resources/website/assets/img/full_act_bangla.gif" />
                </c:otherwise>
                </c:choose>

        </a>
        <div class="row">
            <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 lineremove">
                <c:choose>
                    <c:when test="${act.isBanglaAct==false && act.displayPreambleText==true}">

                        <div class="row">
                            <div class="col-md-2"></div>
                            <div class="col-md-10 text-center act-role-style">
                                <ata:actLink text="${act.actRole}" actShortList="${actShortList}"
                                             contextPath="${contextPath}"
                                             output="output"/>
                                <strong>
                                    <ata:footnote text="${output}" footnoteNo="${footnoteNo}" count="count"
                                                  locale='${act.isBanglaAct==true? "bn":"en"}'
                                                  footnoteTotal="footnoteTotal"
                                                  footnotes="${act.actRoleFootnoteList}"/>
                                    <c:set var="footnoteNo" value="${count}"/>
                                    <c:set var="footnoteList">${footnoteList} ${footnoteTotal}</c:set>
                                </strong>
                            </div>
                        </div>
                        <div class="row">
                            <c:set var="preambleForTest" value='${act.preamble}'/>
                            <%
                                String strTest  = (String) pageContext.getAttribute("preambleForTest");
                                Document doc = Jsoup.parse(strTest);
                                String text = doc.text();
                                String textTrim=text.trim();
                                pageContext.setAttribute("textTrim", textTrim);
                            %>
                            <c:if test="${not empty textTrim}">
                                <div class="col-md-2">&nbsp;&nbsp;Preamble</div>
                            </c:if>

                            <div class="col-md-10">

                                <ata:actLink text="${act.preamble}"
                                             actShortList="${actShortList}"
                                             contextPath="${contextPath}"
                                             output="output"/>
                                <ata:footnote text="${output}" footnoteNo="${footnoteNo}" count="count"
                                              locale='${act.isBanglaAct==true? "bn":"en"}'
                                              footnoteTotal="footnoteTotal"
                                              footnotes="${act.preambleFootnoteList}"/>
                                <c:set var="footnoteNo" value="${count}"/>
                                <c:set var="footnoteList">${footnoteList} ${footnoteTotal}</c:set>
                            </div>
                        </div>
                    </c:when>
                    <c:when test="${act.isBanglaAct==false && act.displayPreambleText==false}">
                        <div class="row">
                            <div class="col-md-12 ">
                                <div class="text-center act-role-style">
                                    <ata:actLink text="${act.actRole}" actShortList="${actShortList}"
                                                 contextPath="${contextPath}"
                                                 output="output"/>
                                    <strong>
                                        <ata:footnote text="${output}" footnoteNo="${footnoteNo}" count="count"
                                                      locale='${act.isBanglaAct==true? "bn":"en"}'
                                                      footnoteTotal="footnoteTotal"
                                                      footnotes="${act.actRoleFootnoteList}"/>
                                        <c:set var="footnoteNo" value="${count}"/>
                                        <c:set var="footnoteList">${footnoteList} ${footnoteTotal}</c:set>
                                    </strong>
                                </div>
                            </div>

                        </div>
                        <div class="row" >
                            <%--<div class="col-md-1" style="width: 0.5%;"></div>--%>
                                <div class="col-md-12 ">
                                    <div class="col-md-12 pad-right">
                                        <p>
                                            <ata:actLink text="${act.preamble}"
                                                         actShortList="${actShortList}"
                                                         contextPath="${contextPath}"
                                                         output="output"/>
                                            <ata:footnote text="${output}" footnoteNo="${footnoteNo}" count="count"
                                                          locale='${act.isBanglaAct==true? "bn":"en"}'
                                                          footnoteTotal="footnoteTotal"
                                                          footnotes="${act.preambleFootnoteList}"/>
                                            <c:set var="footnoteNo" value="${count}"/>
                                            <c:set var="footnoteList">${footnoteList} ${footnoteTotal}</c:set>
                                        </p>
                                    </div>
                                </div>

                        </div>
                    </c:when>
                    <c:otherwise>
                        <div class="row">
                            <div class="col-md-12 text-center act-role-style">
                                <ata:actLink text="${act.actRole}" actShortList="${actShortList}"
                                             contextPath="${contextPath}"
                                             output="output"/>
                                <strong>
                                    <ata:footnote text="${output}" footnoteNo="${footnoteNo}" count="count"
                                                  locale='${act.isBanglaAct==true? "bn":"en"}'
                                                  footnoteTotal="footnoteTotal"
                                                  footnotes="${act.actRoleFootnoteList}"/>
                                    <c:set var="footnoteNo" value="${count}"/>
                                    <c:set var="footnoteList">${footnoteList} ${footnoteTotal}</c:set>
                                </strong>
                            </div>
                        </div>
                        <div class="row">
                            <%--<div class="col-md-1" style="width: 0.5%;"></div>--%>
                            <div class="col-md-12 ">
                                <div class="col-md-12 pad-right">

                                        <ata:actLink text="${act.preamble}"
                                                     actShortList="${actShortList}"
                                                     contextPath="${contextPath}"
                                                     output="output"/>
                                        <ata:footnote text="${output}" footnoteNo="${footnoteNo}" count="count"
                                                      locale='${act.isBanglaAct==true? "bn":"en"}'
                                                      footnoteTotal="footnoteTotal"
                                                      footnotes="${act.preambleFootnoteList}"/>
                                        <c:set var="footnoteNo" value="${count}"/>
                                        <c:set var="footnoteList">${footnoteList} ${footnoteTotal}</c:set>
                                    <%--</p>--%>
                                </div>

                            </div>
                        </div>
                    </c:otherwise>
                </c:choose>

                <%--<p class="content-head">
                    <c:choose>
                        <c:when test="${act.isBanglaAct==true}">
                            সূচি
                        </c:when>
                        <c:otherwise>
                            CONTENTS
                        </c:otherwise>
                    </c:choose>
                </p>
                <p class="section-head">
                    <c:choose>
                        <c:when test="${act.isBanglaAct==true}">
                            ধারাসমূহ
                        </c:when>
                        <c:otherwise>
                            SECTIONS
                        </c:otherwise>
                    </c:choose>
                </p>--%>
            </div>
        </div>
    </section>
    <section
            class="padding-bottom-20 <c:if test="${act.repealedAct.status==true}"><c:out value="${act.isBanglaAct==true? 'bn-repealed':'repealed'}"/></c:if>">

        <p class="content-head">
            <c:choose>
                <c:when test="${act.isBanglaAct==true}">
                    সূচি
                </c:when>
                <c:otherwise>
                    CONTENTS
                </c:otherwise>
            </c:choose>
        </p>
        <p class="section-head">
            <c:choose>
                <c:when test="${act.isBanglaAct==true}">
                    ধারাসমূহ
                </c:when>
                <c:otherwise>
                    SECTIONS
                </c:otherwise>
            </c:choose>
        </p>
    </section>
    <section class=" search-here">
        <div class="row <c:if test="${act.repealedAct.status==true}"><c:out value="${act.isBanglaAct==true? 'bn-repealed':'repealed'}"/></c:if>">
            <c:set var="partId" value="0"/>
            <c:set var="chapterId" value="0"/>
            <c:forEach items="${sections}" var="section" varStatus="loop">

                <c:if test="${section.partId!= null && partId!=section.partId.id}">
                    <div class="act-part-group">
                        <c:if test="${ fn:trim(section.partId.partNo)!=''}">
                            <p class="act-part-no">

                                <c:set var="partNo" value="${section.partId.partNo}"/>
                                <c:if test="${act.isBanglaAct==false}">
                                    <c:set var="partNo">
                                        <spring:message code="website.part.text"/> ${section.partId.partNo}
                                    </c:set>
                                </c:if>
                                <a href="${contextPath}/act-${section.actId.id}/part-${section.partId.id}.html">
                                    <ata:footnote text="${partNo}" footnoteNo="${footnoteNo}" count="count"
                                                  locale='${act.isBanglaAct==true? "bn":"en"}'
                                                  footnoteTotal="footnoteTotal"
                                                  footnotes="${section.partId.partNoFootnoteList}"/>
                                </a>
                                <c:set var="footnoteNo" value="${count}"/>
                                <c:set var="footnoteList">${footnoteList} ${footnoteTotal}</c:set>

                            </p>
                        </c:if>
                        <c:if test="${ fn:trim(section.partId.partName)!=''}">
                            <p class="act-part-name">
                                <c:set var="partName" value="${section.partId.partName}"/>
                                <a href="${contextPath}/act-${section.actId.id}/part-${section.partId.id}.html">
                                    <ata:footnote text="${section.partId.partName}" footnoteNo="${footnoteNo}"
                                                  count="count" locale='${act.isBanglaAct==true? "bn":"en"}'
                                                  footnoteTotal="footnoteTotal"
                                                  footnotes="${section.partId.partNameFootnoteList}"/>
                                </a>
                                <c:set var="footnoteNo" value="${count}"/>
                                <c:set var="footnoteList">${footnoteList} ${footnoteTotal}</c:set>
                            </p>
                        </c:if>
                    </div>
                </c:if>

                <c:if test="${section.chapterId!=null && chapterId!=section.chapterId.id}">

                    <div class="act-chapter-group">
                        <c:if test="${section.actId.id!=section.chapterId.actId.id}">
                            <c:if test="${fn:trim(section.chapterId.chapterNo)!=''}">

                                <p class="act-chapter-no">
                                    <c:set var="chapterNo" value="${section.chapterId.chapterNo}"/>
                                    <c:if test="${act.isBanglaAct==false}">
                                        <c:set var="chapterNo">
                                            <spring:message code="website.chapter.text"/> ${section.chapterId.chapterNo}
                                        </c:set>
                                    </c:if>
                                    <%--<a href="${contextPath}/act-${section.actId.id}/chapter-${section.chapterId.id}.html">--%>
                                        <ata:footnote text="${chapterNo}" footnoteNo="${footnoteNo}" count="count"
                                                      locale='${act.isBanglaAct==true? "bn":"en"}'
                                                      footnoteTotal="footnoteTotal"
                                                      footnotes="${section.chapterId.chapterNoFootnoteList}"/>
                                   <%-- </a>--%>
                                    <c:set var="footnoteNo" value="${count}"/>
                                    <c:set var="footnoteList">${footnoteList} ${footnoteTotal}</c:set>
                                </p>
                            </c:if>
                            <c:if test="${fn:trim(section.chapterId.chapterName)!=''}">
                                <p class="act-chapter-name">

                                    <c:set var="chapterName" value="${section.chapterId.chapterName}"/>
                                    <%--<a href="${contextPath}/act-${section.actId.id}/chapter-${section.chapterId.id}.html">--%>

                                        <ata:footnote text="${chapterName}" footnoteNo="${footnoteNo}" count="count"
                                                      locale='${act.isBanglaAct==true? "bn":"en"}'
                                                      footnoteTotal="footnoteTotal"
                                                      footnotes="${section.chapterId.chapterNameFootnoteList}"/>
                                   <%-- </a>--%>
                                    <c:set var="footnoteNo" value="${count}"/>
                                    <c:set var="footnoteList">${footnoteList} ${footnoteTotal}</c:set>
                                </p>
                            </c:if>
                        </c:if>
                             <c:if test="${section.actId.id==section.chapterId.actId.id}">
                                 <c:if test="${fn:trim(section.chapterId.chapterNo)!=''}">

                                     <p class="act-chapter-no">
                                         <c:set var="chapterNo" value="${section.chapterId.chapterNo}"/>
                                         <c:if test="${act.isBanglaAct==false}">
                                             <c:set var="chapterNo">
                                                 <spring:message code="website.chapter.text"/> ${section.chapterId.chapterNo}
                                             </c:set>
                                         </c:if>
                                         <a href="${contextPath}/act-${section.actId.id}/chapter-${section.chapterId.id}.html">
                                             <ata:footnote text="${chapterNo}" footnoteNo="${footnoteNo}" count="count"
                                                           locale='${act.isBanglaAct==true? "bn":"en"}'
                                                           footnoteTotal="footnoteTotal"
                                                           footnotes="${section.chapterId.chapterNoFootnoteList}"/>
                                         </a>
                                         <c:set var="footnoteNo" value="${count}"/>
                                         <c:set var="footnoteList">${footnoteList} ${footnoteTotal}</c:set>
                                     </p>
                                 </c:if>
                                 <c:if test="${fn:trim(section.chapterId.chapterName)!=''}">
                                     <p class="act-chapter-name" style="font-weight: bold">

                                         <c:set var="chapterName" value="${section.chapterId.chapterName}"/>
                                         <a href="${contextPath}/act-${section.actId.id}/chapter-${section.chapterId.id}.html">

                                             <ata:footnote text="${chapterName}" footnoteNo="${footnoteNo}" count="count"
                                                           locale='${act.isBanglaAct==true? "bn":"en"}'
                                                           footnoteTotal="footnoteTotal"
                                                           footnotes="${section.chapterId.chapterNameFootnoteList}"/>
                                         </a>
                                         <c:set var="footnoteNo" value="${count}"/>
                                         <c:set var="footnoteList">${footnoteList} ${footnoteTotal}</c:set>
                                     </p>
                                 </c:if>
                               </c:if>

                    </div>
                </c:if>


                <c:if test="${section.sectionHead!=null || fn:trim(section.sectionHead)!=''}">

                    <c:if test="${section.sectionHead!=sectionHead}">
                        <p class="act-section-head text-center bd-info">
                            <ata:footnote text="${section.sectionHead}" footnoteNo="${footnoteNo}" count="count"
                                          footnotes="${section.sectionHeadFootnoteList}"
                                          locale='${act.isBanglaAct==true? "bn":"en"}' footnoteTotal="footnoteTotal"/>
                            <c:set var="footnoteNo" value="${count}"/>
                            <c:set var="footnoteList">${footnoteList} ${footnoteTotal}</c:set>
                        </p>
                    </c:if>
                    <c:set var="sectionHead" value="${section.sectionHead}"/>
                </c:if>
                <c:if test='${not empty section.sectionName}'>
                    <p class="act-section-name <c:if test="${act.repealedAct.status==true}"><c:out value="${act.isBanglaAct==true? 'bn-repealedchoto':'repealedchoto'}"/></c:if>">
                        <a href="${contextPath}/act-${section.actId.id}/section-${section.id}.html">
                                <%-- <ata:footnote text="${section.sectionName}" footnoteNo="${footnoteNo}" count="count"
                                               locale='${act.isBanglaAct==true? "bn":"en"}' footnoteTotal="footnoteTotal"
                                               footnotes="${section.sectionNameFootnoteList}"/>
                                 <c:set var="footnoteNo" value="${count}"/>
                                 <c:set var="footnoteList">${footnoteList} ${footnoteTotal}</c:set>
                                  ${section.sectionName}
                                 --%>
                            <c:set var="string1" value='${section.sectionName}'/>
                            <%
                                String getSectionName = (String) pageContext.getAttribute("string1");
                                String removeBreakSectionName = getSectionName.replaceAll("(<br\\s*/>)", "");
                                pageContext.setAttribute("removeBreakSectionName", removeBreakSectionName);
                            %>
                                ${removeBreakSectionName}
                        </a>
                    </p>
                </c:if>
                <c:if test='${empty section.sectionName}'>
                    <p class="act-section-name <c:if test="${act.repealedAct.status==true}"><c:out value="${act.isBanglaAct==true? 'bn-repealedchoto':'repealedchoto'}"/></c:if>">
                        <a href="${contextPath}/act-${section.actId.id}/section-${section.id}.html">
                                <%-- <ata:footnote text="${section.sectionName}" footnoteNo="${footnoteNo}" count="count"
                                               locale='${act.isBanglaAct==true? "bn":"en"}' footnoteTotal="footnoteTotal"
                                               footnotes="${section.sectionNameFootnoteList}"/>
                                 <c:set var="footnoteNo" value="${count}"/>
                                 <c:set var="footnoteList">${footnoteList} ${footnoteTotal}</c:set>
                                  ${section.sectionName}
                                 --%>
                            <c:set var="string1" value='${section.sectionDescription}'/>
                                    <%
                                        String string1 = (String) pageContext.getAttribute("string1");
                                        Document doc = Jsoup.parse(string1);
                                        String text = doc.text();
                                        pageContext.setAttribute("description", text);
                                    %>
                                ${fn:substring(description,0,50)}
                        </a>
                    </p>
                </c:if>

                <c:if test="${section.partId!= null}">
                    <c:set var="partId" value="${section.partId.id}"/>
                </c:if>
                <c:if test="${section.chapterId!= null}">
                    <c:set var="chapterId" value="${section.chapterId.id}"/>
                </c:if>

            </c:forEach>
            <c:if test="${not empty act.actScheduleList}">
                <div class="col-md-3 act-upload-file">
                    <div class="panel panel-primary">
                        <div class="panel-heading">
                            <c:if test="${act.isBanglaAct==false}">
                                <h3 class="panel-title">Schedule</h3>
                            </c:if>
                            <c:if test="${act.isBanglaAct==true}">
                                <h3 class="panel-title">তফসিল</h3>
                            </c:if>
                        </div>
                    </div>
                    <table>
                        <tbody >
                        <c:forEach items="${act.actScheduleList}" var="actSchedule">
                        <tr>
                            <td class="act-up-files"><span class="act-schedule-title"><a
                                    href="${contextPath}/upload/${actSchedule.attachment}" style="padding-left: 6px" target="_blank">${actSchedule.title}</a></span></td>
                            </c:forEach>
                        </tr>
                        </tbody>
                    </table>
                </div>
            </c:if>
            <c:if test="${not empty act.actGazettedCopyList}">
                <div class="col-md-3 act-upload-file">
                    <div class="panel panel-primary">
                        <div class="panel-heading">
                            <c:if test="${act.isBanglaAct==false}">
                                <h3 class="panel-title">Gazzeted Copy</h3>
                            </c:if>
                            <c:if test="${act.isBanglaAct==true}">
                                <h3 class="panel-title">গেজেটেড অনুলিপি</h3>
                            </c:if>
                        </div>
                    </div>
                    <table >
                        <tbody>
                        <c:forEach items="${act.actGazettedCopyList}" var="actGazettedCopy">
                        <tr>
                            <td class="act-up-files"><span class="act-schedule-title"><a
                                    href="${contextPath}/upload/${actGazettedCopy.attachment}" style="padding-left: 6px" target="_blank">${actGazettedCopy.title}</a></span></td>
                            </c:forEach>
                        </tr>
                        </tbody>
                    </table>
                </div>
            </c:if>
            <c:if test="${not empty act.actRulesRegulationList}">
                <div class="col-md-3 act-upload-file">
                    <div class="panel panel-primary">
                        <div class="panel-heading">
                            <c:if test="${act.isBanglaAct==false}">
                                <h3 class="panel-title">Rules & Regulations</h3>
                            </c:if>
                            <c:if test="${act.isBanglaAct==true}">
                                <h3 class="panel-title">বিধি ও প্রবিধান</h3>
                            </c:if>
                        </div>
                    </div>
                    <table >
                        <tbody>
                        <c:forEach items="${act.actRulesRegulationList}" var="actRulesRegulation">
                        <tr>
                            <td class="act-up-files"><span class="act-schedule-title"><a
                                    href="${contextPath}/upload/${actRulesRegulation.attachment}" style="padding-left: 6px" target="_blank">${actRulesRegulation.title}</a></span></td>
                            </c:forEach>
                        </tr>
                        </tbody>
                    </table>
                </div>
            </c:if>
            <c:if test="${not empty act.actAttachmentList}">
                <div class="col-md-3">
                    <div class="panel panel-primary act-upload-file">
                        <div class="panel-heading">
                            <c:if test="${act.isBanglaAct==false}">
                                <h3 class="panel-title">Authentic Bangla Text</h3>
                            </c:if>
                            <c:if test="${act.isBanglaAct==true}">
                                <h3 class="panel-title">Authentic English Text</h3>
                            </c:if>
                        </div>
                    </div>
                    <table >
                        <tbody >

                        <c:if test="${act.isBanglaAct==false}">
                            <c:forEach items="${act.actAttachmentList}" var="actAttachment">
                                <tr>
                                <td class="act-up-files"><span class="act-schedule-title"><a
                                        href="${contextPath}/upload/${actAttachment.attachment}" target="_blank">Authentic Bangla Text</a></span></td>
                            </c:forEach>
                            </tr>
                        </c:if>
                        <c:if test="${act.isBanglaAct==true}">
                            <c:forEach items="${act.actAttachmentList}" var="actAttachment">
                                <tr>
                                <td class="act-up-files"><span class="act-schedule-title"><a
                                        href="${contextPath}/upload/${actAttachment.attachment}" target="_blank">Authentic English Text</a></span></td>
                            </c:forEach>
                            </tr>
                        </c:if>

                        </tbody>
                    </table>
                </div>
            </c:if>


        </div>
    </section>
</div>


