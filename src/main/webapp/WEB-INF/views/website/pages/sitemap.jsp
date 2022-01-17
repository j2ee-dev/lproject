<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<div class="col-md-12">
    <div class="tbox tag-box-v3 margin-bottom-40">
        <div class="heading heading-v2 margin-bottom-40">
            <h2><spring:message code="website.sitemap.text"/></h2>
        </div>
        <div class="sitemap margin-bottom-40">
            <div class="row">
                <div class="col-sm-4">
                    <h4><spring:message code="website.topMenu.text"/></h4>
                    <ul>
                        <li><a href="${contextPath}/"><strong><spring:message code="website.home.text"/></strong></a></li>
                        <li><a href="#"><strong><spring:message code="website.lawsOfBangladesh.text"/></strong></a></li>
                        <ul>
                            <li><a href="${contextPath}/laws-of-bangladesh-chronological-index.html"><spring:message code="website.chronological.text"/></a></li>
                            <li><a href="${contextPath}/laws-of-bangladesh-alphabetical-index.html"><spring:message code="website.alphabeticalIndex.text"/></a></li>
                            <li><a href="${contextPath}/search.html"><spring:message code="website.search.text"/></a></li>
                        </ul>
                        <li><a target="_blank"  href="http://bdcode.gov.bd/"><strong><spring:message code="website.bangladeshcode.text"/></strong></a></li>
                        <li><a href="${contextPath}/news-and-notice.html"><strong><spring:message code="website.newsAndNotice.text"/></strong></a></li>
                        <li><a href="${contextPath}/related-links.html"><strong><spring:message code="website.relatedLinks.text"/></strong></a></li>
                        <li><a href="${contextPath}/sitemap.html"><strong><spring:message code="website.sitemap.text"/></strong></a></li>
                        <li><a href="mailto:israil@legislativediv.gov.bd"><strong><spring:message code="website.email.text"/></strong></a></li>
                        <li><a href="?page=feedback"><strong><spring:message code="website.contactus.text"/></strong></a></li>
                        <li><strong><a target="_blank"  href="http://old.bdlaws.minlaw.gov.bd/"><spring:message code="website.oldwebsite.text"/></a></strong></li>
                    </ul>
                </div>
                <div class="col-sm-4">
                    <h4><spring:message code="website.helpMenu.text"/></h4>
                    <ul>
                        <li><a href="?page=faq"><strong><spring:message code="website.faq.text"/></strong></a></li>
                        <li><a href="?page=how-to-search"><strong><spring:message code="website.howToSearch.text"/></strong></a></li>
                        <li><a href="?page=how-to-print"><strong><spring:message code="website.howToPrint.text"/></strong></a></li>
                        <li><a href="?page=roman-numbers"><strong><spring:message code="website.romanNumber.text"/></strong></a></li>
                        <li><a href="?page=glossary"><strong><spring:message code="website.glossary.text"/></strong></a></li>
                        <li><a href="?page=news-and-notice"><strong><spring:message code="website.newsAndNotice.text"/></strong></a></li>
                        <li><a href="?page=feedback"><strong><spring:message code="website.suggetion.text"/> </strong></a></li>
                    </ul>   
                </div>
                <div class="col-sm-4">
                    <h4><spring:message code="website.relatedLinks.text"/></h4>
                    <ul>
                        <li><a target="_blank" href="http://www.bangladesh.gov.bd/"><spring:message code="website.bangladeshGovernmentOfficialWebSite.text"/></a></li>
                        <li><a target="_blank" href="http://www.legislativediv.gov.bd/"><spring:message code="website.legislativeAndParliamentaryAffairsDivision.text"/></a></li>
                        <li><a target="_blank" href="http://www.parliament.gov.bd/"><spring:message code="website.parliamentSecretariat.text"/></a></li>
                    </ul>   
                </div>
            </div>
        </div>
    </div>
</div>