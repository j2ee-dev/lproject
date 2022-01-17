<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="ata" uri="http://ataur.me/jstl" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<c:set var="lang" value="${pageContext.response.locale}"/>
<c:set var="footnoteNo" value="1" scope="page"/>
<c:set var="footnoteList" value="" scope="page"/>
<c:set var="sectionHead" value=""/>

<style>
    .print-only {
        display: none;
    }

    @media print {
        .no-print {
            display: none;
        }

        .print-only {
            display: block;
        }
    }
</style>
<c:forEach items="${sections}" var="section" begin="0" end="0" varStatus="loop">
    <a target="_blank" href="${contextPath}/act-${act.id}/act-part-print-${section.partId.id}<%--/section-print-${section.id}--%>.html" class="pull-right no-print" style="width: 72px;height: 80px;margin-right: -65px;
margin-bottom: 7px;"> <img src="/resources/website/assets/img/printer.jpg" style="margin: 15px;width: 80px"/>

        <c:choose>
            <c:when test="${act.isBanglaAct==true}">
                <p class="pull-right" style="margin-right: -40px;
margin-bottom: 7px;color:#FF3399;">প্রিন্ট ভিউ</p>
            </c:when>
            <c:otherwise>
                <p class="pull-right"  style="margin-right: -40px;
margin-bottom: 7px;color:#FF3399;">Print View</p>
            </c:otherwise>
        </c:choose>
            <%--</button>--%></a>
</c:forEach>

<div class="col-md-12 boxed-layout" style="padding: 10px;" id="masterContent">
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
    <section class="bt-act-section-search ">
        <div class="row">
            <div class="col-xs-12 col-sm-bottom12 col-md-12 col-lg-12">
                <p class="pull-left no-print">
                    <a href="#" onclick="window.history.back();return false;"><i class="fa fa-arrow-left" title="Back"
                                                                                 aria-hidden="true"></i></a></p>
                <p class="pull-right print-center publish-date">
                    <c:if test="${not empty act.publishDate}">[ ${act.publishDate}    ]</c:if>
                </p>
            </div>
        </div>
    </section>
    <button class="printIco print-only pull-right" onclick="window.print();return false;"
            style="width: 72px;height: 80px;"><i
            class="fa fa-print" style="font-size: 40px;"></i></button>
    <c:if test="${act.repealedAct.status==true}">
        <section class="bt-act-repealed margin-top-20 margin-bottom-20">
            <strong class="text-danger text-center">${act.repealedAct.description}</strong>
        </section>
    </c:if>

    <section class="padding-bottom-20 padding-top-20 bg-striped">
        <c:set var="partId" value="0"/>
        <c:set var="chapterId" value="0"/>
        <c:forEach items="${sections}" var="section" varStatus="loop">

            <div class="row <c:if test="${act.repealedAct.status==true}"><c:out value="${act.isBanglaAct==true? 'bn-repealed':'repealed'}"/></c:if>">
                <c:if test="${section.partId!= null && partId!=section.partId.id}">
                    <div class="act-part-group head">
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
                        <c:if test="${fn:trim(section.chapterId.chapterNo)!=''}">
                            <p class="act-chapter-no">
                                <c:set var="chapterNo" value="${section.chapterId.chapterNo}"/>
                                <c:if test="${act.isBanglaAct==false}">
                                    <c:set var="chapterNo">
                                        <spring:message code="website.chapter.text"/> ${section.chapterId.chapterNo}
                                    </c:set>
                                </c:if>
                                <ata:footnote text="${chapterNo}" footnoteNo="${footnoteNo}" count="count"
                                              locale='${act.isBanglaAct==true? "bn":"en"}' footnoteTotal="footnoteTotal"
                                              footnotes="${section.chapterId.chapterNoFootnoteList}"/>
                                <c:set var="footnoteNo" value="${count}"/>
                                <c:set var="footnoteList">${footnoteList} ${footnoteTotal}</c:set>
                            </p>
                        </c:if>
                        <c:if test="${fn:trim(section.chapterId.chapterName)!=''}">
                            <p class="act-chapter-name" style="font-weight: bold">
                                <c:set var="chapterName" value="${section.chapterId.chapterName}"/>
                                <ata:footnote text="${section.chapterId.chapterName}" footnoteNo="${footnoteNo}"
                                              count="count" locale='${act.isBanglaAct==true? "bn":"en"}'
                                              footnoteTotal="footnoteTotal"
                                              footnotes="${section.chapterId.chapterNameFootnoteList}"/>
                                <c:set var="footnoteNo" value="${count}"/>
                                <c:set var="footnoteList">${footnoteList} ${footnoteTotal}</c:set>
                            </p>
                        </c:if>
                    </div>
                </c:if>

                <c:if test="${section.sectionHead!=null || fn:trim(section.sectionHead)!=''}">

                    <c:if test="${section.sectionHead!=sectionHead}">
                        <p class="act-section-head text-center bd-info margin-top-20">
                            <ata:footnote text="${section.sectionHead}" footnoteNo="${footnoteNo}" count="count"
                                          footnotes="${section.sectionHeadFootnoteList}"
                                          locale='${act.isBanglaAct==true? "bn":"en"}' footnoteTotal="footnoteTotal"/>
                            <c:set var="footnoteNo" value="${count}"/>
                            <c:set var="footnoteList">${footnoteList} ${footnoteTotal}</c:set>
                        </p>
                    </c:if>
                    <c:set var="sectionHead" value="${section.sectionHead}"/>
                </c:if>

                <div class="col-sm-3 txt-head">

                       <c:choose>
                           <c:when test="${fn:containsIgnoreCase(section.sectionName, 'Ordinance No')  }">
                               <c:set var="string1" value='${section.sectionName}'/>
                               <c:set var="string2" value='${fn:indexOf(string1,"।" )}'/>
                               <c:set var="string3" value='${fn:substring(string1,string2+1 , 20000)}'/>
                               <ata:footnote text="${string3}" footnoteNo="${footnoteNo}" count="count"
                                             locale='${act.isBanglaAct==true? "bn":"en"}' footnoteTotal="footnoteTotal"
                                             footnotes="${section.sectionNameFootnoteList}"/>
                               <c:set var="footnoteNo" value="${count}"/>
                               <c:set var="footnoteList">${footnoteList} ${footnoteTotal}</c:set>
                           </c:when>
                           <c:when test="${fn:containsIgnoreCase(section.sectionName, 'Act No')  }">
                               <c:set var="string1" value='${section.sectionName}'/>
                               <c:set var="string2" value='${fn:indexOf(string1,"।" )}'/>
                               <c:set var="string3" value='${fn:substring(string1,string2+1 , 20000)}'/>
                               <ata:footnote text="${string3}" footnoteNo="${footnoteNo}" count="count"
                                             locale='${act.isBanglaAct==true? "bn":"en"}' footnoteTotal="footnoteTotal"
                                             footnotes="${section.sectionNameFootnoteList}"/>
                               <c:set var="footnoteNo" value="${count}"/>
                               <c:set var="footnoteList">${footnoteList} ${footnoteTotal}</c:set>
                           </c:when>
                           <c:when test="${fn:containsIgnoreCase(section.sectionName, '.') && fn:containsIgnoreCase(section.sectionName, '৷') }">
                               <c:set var="string1" value='${section.sectionName}'/>
                               <c:set var="string2" value='${fn:indexOf(string1,"৷" )}'/>
                               <c:set var="string3" value='${fn:substring(string1,string2+1 , 20000)}'/>
                               <ata:footnote text="${string3}" footnoteNo="${footnoteNo}" count="count"
                                             locale='${act.isBanglaAct==true? "bn":"en"}' footnoteTotal="footnoteTotal"
                                             footnotes="${section.sectionNameFootnoteList}"/>
                               <c:set var="footnoteNo" value="${count}"/>
                               <c:set var="footnoteList">${footnoteList} ${footnoteTotal}</c:set>
                           </c:when>

                           <c:when test="${fn:containsIgnoreCase(section.sectionName, '.')}">
                               <c:set var="string1" value='${section.sectionName}'/>
                               <c:set var="string2" value='${fn:indexOf(string1,"." )}'/>
                               <c:set var="string3" value='${fn:substring(string1,string2+1 , 20000)}'/>
                               <c:set var="string4" value='${string3}'/>
                               <%
                                   String getSectionName = (String) pageContext.getAttribute("string4");
                                   String removeBreakSectionName = getSectionName.replaceAll("(<br\\s*/>)", "");
                                   pageContext.setAttribute("removeBreakSectionName", removeBreakSectionName);
                               %>
                               <ata:footnote text="${removeBreakSectionName}" footnoteNo="${footnoteNo}" count="count"
                                             locale='${act.isBanglaAct==true? "bn":"en"}' footnoteTotal="footnoteTotal"
                                             footnotes="${section.sectionNameFootnoteList}"/>
                               <c:set var="footnoteNo" value="${count}"/>
                               <c:set var="footnoteList">${footnoteList} ${footnoteTotal}</c:set>
                           </c:when>
                           <c:when test="${fn:contains(section.sectionName, '৷')}">
                               <c:set var="string1" value='${section.sectionName}'/>
                               <c:set var="string2" value='${fn:indexOf(string1,"৷" )}'/>
                               <c:set var="string3" value='${fn:substring(string1,string2+1 , 20000)}'/>
                               <c:set var="string4" value='${string3}'/>
                               <%
                                   String getSectionName = (String) pageContext.getAttribute("string4");
                                   String removeBreakSectionName = getSectionName.replaceAll("(<br\\s*/>)", "");
                                   pageContext.setAttribute("removeBreakSectionName", removeBreakSectionName);
                               %>
                               <ata:footnote text="${removeBreakSectionName}" footnoteNo="${footnoteNo}" count="count"
                                             locale='${act.isBanglaAct==true? "bn":"en"}' footnoteTotal="footnoteTotal"
                                             footnotes="${section.sectionNameFootnoteList}"/>
                               <c:set var="footnoteNo" value="${count}"/>
                               <c:set var="footnoteList">${footnoteList} ${footnoteTotal}</c:set>
                           </c:when>
                           <c:when test="${fn:contains(section.sectionName, '।')}">
                               <c:set var="string1" value='${section.sectionName}'/>
                               <c:set var="string2" value='${fn:indexOf(string1,"।" )}'/>
                               <c:set var="string3" value='${fn:substring(string1,string2+1 , 20000)}'/>
                               <c:set var="string4" value='${string3}'/>
                               <%
                                   String getSectionName = (String) pageContext.getAttribute("string4");
                                   String removeBreakSectionName = getSectionName.replaceAll("(<br\\s*/>)", "");
                                   pageContext.setAttribute("removeBreakSectionName", removeBreakSectionName);
                               %>
                               <ata:footnote text="${removeBreakSectionName}" footnoteNo="${footnoteNo}" count="count"
                                             locale='${act.isBanglaAct==true? "bn":"en"}' footnoteTotal="footnoteTotal"
                                             footnotes="${section.sectionNameFootnoteList}"/>
                               <c:set var="footnoteNo" value="${count}"/>
                               <c:set var="footnoteList">${footnoteList} ${footnoteTotal}</c:set>
                           </c:when>
                           <c:otherwise>
                               <c:set var="string1" value='${section.sectionName}'/>
                               <%
                                   String getSectionName = (String) pageContext.getAttribute("string1");
                                   String removeBreakSectionName = getSectionName.replaceAll("(<br\\s*/>)", "");
                                   pageContext.setAttribute("removeBreakSectionName", removeBreakSectionName);
                               %>
                               <ata:footnote text="${removeBreakSectionName}" footnoteNo="${footnoteNo}" count="count"
                                             locale='${act.isBanglaAct==true? "bn":"en"}' footnoteTotal="footnoteTotal"
                                             footnotes="${section.sectionNameFootnoteList}"/>
                               <c:set var="footnoteNo" value="${count}"/>
                               <c:set var="footnoteList">${footnoteList} ${footnoteTotal}</c:set>
                           </c:otherwise>
                       </c:choose>
                </div>
                <div class="col-sm-9 txt-details act-part-details" id="sec-dec">
                    <ata:actLink text="${section.sectionDescription}" contextPath="${contextPath}" output="output"
                                 actShortList="${actShortList}"/>
                    <ata:footnote text="${output}" footnoteNo="${footnoteNo}" count="count"
                                  footnotes="${section.sectionDescriptionFootnoteList}"
                                  locale='${act.isBanglaAct==true? "bn":"en"}' footnoteTotal="footnoteTotal"/>
                    <c:set var="footnoteNo" value="${count}"/>
                    <c:set var="footnoteList">${footnoteList} ${footnoteTotal}</c:set>

                    <ata:actLink text="${section.sectionTable}" contextPath="${contextPath}" output="output"
                                 actShortList="${actShortList}"/>
                    <ata:footnote text="${output}" footnoteNo="${footnoteNo}" count="count"
                                  footnotes="${section.sectionTableFootnoteList}"
                                  locale='${act.isBanglaAct==true? "bn":"en"}' footnoteTotal="footnoteTotal"/>
                    <c:set var="footnoteNo" value="${count}"/>
                    <c:set var="footnoteList">${footnoteList} ${footnoteTotal}</c:set>
                </div>
            </div>
            <c:if test="${section.partId!= null}">
                <c:set var="partId" value="${section.partId.id}"/>
            </c:if>
            <c:if test="${section.chapterId!= null}">
                <c:set var="chapterId" value="${section.chapterId.id}"/>
            </c:if>
        </c:forEach>
        <hr/>
        <div>
            <img src="/resources/website/assets/img/line2.jpg">
        </div>
        <div class="footnoteListAll">
            <ul class="list-unstyled">
                <ata:actLink text="${footnoteList}" contextPath="${contextPath}" output=""
                             actShortList="${actShortList}"/>
            </ul>
        </div>
    </section>
    <section class="copy-right text-center">
        <div><img src="/resources/website/assets/img/line.jpg" width="100" height="18"></div>
        <div style="color:#CC9966; font-size: 14px;font-style: normal;">Copyright <span style="color: black">©</span> 2019, Legislative and Parliamentary Affairs Division </div>
        <div style="color:#CC9966; font-size: 14px;font-style: normal;">Ministry of Law, Justice and Parliamentary Affairs</div>
    </section>
</div>