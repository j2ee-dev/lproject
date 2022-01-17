<%--<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<footer class="no-print">
            <div class="footer">
                <div class="container-fluid">
                    <div class="row">

                        <!-- Link List -->
                        <div class="col-sm-4 md-margin-bottom-40">
                            <div class="headline"><h2><spring:message code="website.relatedLinks.text"/> </h2></div>

                            <ul class="fa-ul blog-trending">
                                <li>
                                    <i class="fa-li fa fa-check-square"></i><a target="_blank" href="http://www.bangladesh.gov.bd/"><spring:message code="website.bangladeshGovernmentOfficialWebSite.text"/></a>
                                </li>
                                <li>
                                    <i class="fa-li fa fa-check-square"></i><a target="_blank" href="http://www.legislativediv.gov.bd/"><spring:message code="website.legislativeAndParliamentaryAffairsDivision.text"/> </a>
                                </li>
                                <li>
                                    <i class="fa-li fa fa-check-square"></i><a target="_blank" href="http://www.parliament.gov.bd/"><spring:message code="website.parliamentSecretariat.text"/> </a>
                                </li>
                            </ul>
                        </div>

                        <div class="col-sm-4 md-margin-bottom-40">
                            <div class="hit-counter">
                                <div class="headline"><h2> Total Hits : </h2></div>
                                &lt;%&ndash;<div class="address md-margin-bottom-40">
                                    <div class="incremental-counter" data-value="${totalHits}"></div>
                                </div>&ndash;%&gt;
                                &lt;%&ndash;<a href="https://www.freecounterstat.com" title="<spring:message code="website.visitorCount.text"/> ">&ndash;%&gt;
                                    &lt;%&ndash;<img src="https://counter9.freecounter.ovh/private/freecounterstat.php?c=jenemjfacwz16qbtw7d51jtuy71fdhug" border="0" title="website counter" alt="website counter">&ndash;%&gt;
                                &lt;%&ndash;</a>&ndash;%&gt;
                                <div class="row">
                                        <div class="col-md-12 min-incremental-counter" data-value="${totalHits}"></div>
                                </div>
                            </div>

                        </div>
                        <!-- Address -->
                        <div class="col-sm-4 map-img md-margin-bottom-40">
                            <div class="headline"><h2><spring:message code="website.contactus.text"/> </h2></div>
                            <div class="address md-margin-bottom-40">
                                <spring:message code="website.lapad.text"/> <br>
                                <spring:message code="website.minlaw.text"/> <br>
                                <spring:message code="website.address.text"/> <br>
                                <spring:message code="website.phone.text"/> : <spring:message code="website.phone.no"/> <br>
                                <spring:message code="website.fax.text"/> : <spring:message code="website.fax.no"/>  <br>
                                <spring:message code="website.email.text"/> : <a href="mailto:israil@legislativediv.gov.bd" class=""><spring:message code="website.email.address"/> </a>
                            </div>
                        </div><!--/col-md-3-->
                        <!-- End Address -->
                    </div>
                </div>
            </div>
            <div class="copyright">
                <div class="container-fluid">
                    <div class="row">
                        <div class="col-sm-9">
                            <p style="font-size: smaller;">
                                <spring:message code="website.copyright.text"/>
                            </p>
                        </div>
                        <!-- Social Links -->
                        <div class="col-sm-3">
                            <ul class="footer-socials list-inline" style="font-size: smaller;">
                                <li>
                                    <spring:message code="website.designAndDevelopedBy.text"/>
                                    <a target="_blank" href="http://www.baseltd.com">
                                        <spring:message code="website.baseLimited.text"/>
                                    </a>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </footer>
&lt;%&ndash;<script src="<c:out value="/resources/website/assets/js/jquery-1.11.1.min.js"/>"></script>
<script src="<c:out value="/resources/website/assets/js/bootstrap.min.js"/>"></script>&ndash;%&gt;
<script src="/resources/website/assets/js/jquery.incremental-counter.js" type="text/javascript"></script>
<script>
    $(".min-incremental-counter").incrementalCounter();
</script>--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<footer class="padding-top-20 no-print">
    <div class="footer">
        <div class="container-fluid">
            <div class="row">

                <!-- Link List -->
                <div class="col-sm-6 md-margin-bottom-40">

                    <div class="headline"><h2><spring:message code="website.relatedLinks.text"/> </h2></div>

                    <ul class="fa-ul blog-trending">
                        <li>
                            <i class="fa-li fa fa-check-square"></i><a target="_blank" href="http://www.bangladesh.gov.bd/"><spring:message code="website.bangladeshGovernmentOfficialWebSite.text"/></a>
                        </li>
                        <li>
                            <i class="fa-li fa fa-check-square"></i><a target="_blank" href="http://www.legislativediv.gov.bd/"><spring:message code="website.legislativeAndParliamentaryAffairsDivision.text"/> </a>
                        </li>
                        <li>
                            <i class="fa-li fa fa-check-square"></i><a target="_blank" href="http://www.parliament.gov.bd/"><spring:message code="website.parliamentSecretariat.text"/> </a>
                        </li>
                        <li>
                            <i class="fa-li fa fa-check-square"></i><a target="_blank" href="http://www.lawcommissionbangladesh.org/"><spring:message code="website.lawcommision.text"/> </a>
                        </li>
                        <li>
                            <i class="fa-li fa fa-check-square"></i><a target="_blank" href="http://www.nhrc.org.bd/"><spring:message code="website.humenright.text"/> </a>
                        </li>

                    </ul>

                </div>

                <%--    <div class="col-sm-4 md-margin-bottom-40">
                        <div class="hit-counter">
                            <div class="headline"><h2> Total Hits : </h2></div>
                            &lt;%&ndash;<div class="address md-margin-bottom-40">
                                <div class="incremental-counter" data-value="${totalHits}"></div>
                            </div>&ndash;%&gt;
                            &lt;%&ndash;<a href="https://www.freecounterstat.com" title="<spring:message code="website.visitorCount.text"/> ">&ndash;%&gt;
                                &lt;%&ndash;<img src="https://counter9.freecounter.ovh/private/freecounterstat.php?c=jenemjfacwz16qbtw7d51jtuy71fdhug" border="0" title="website counter" alt="website counter">&ndash;%&gt;
                            &lt;%&ndash;</a>&ndash;%&gt;
                            <div class="row">
                                    <div class="col-md-12 min-incremental-counter" data-value="${totalHits}"></div>
                            </div>
                        </div>

                    </div>--%>
                <!-- Address -->
                <div class="col-sm-4 md-margin-bottom-40">


                </div>
                <div class="col-sm-6 map-img md-margin-bottom-40">

                    <div class="headline"><h2><spring:message code="website.contactus.text"/> </h2></div>
                    <div class="address md-margin-bottom-40">
                        <spring:message code="website.lapad.text"/> <br>
                        <spring:message code="website.minlaw.text"/> <br>
                        <spring:message code="website.address.text"/> <br>
                         <br>
                         <br>
                        <%--<spring:message code="website.phone.text"/> : <spring:message code="website.phone.no"/> <br>--%>
                        <%--<spring:message code="website.fax.text"/> : <spring:message code="website.fax.no"/>  <br>--%>
                        <%--<spring:message code="website.email.text"/> : <a href="ap@legislativediv.gov.bd" class=""><spring:message code="website.email.address"/> </a>--%>
                    </div>


                </div><!--/col-md-3-->
                <!-- End Address -->
            </div>
        </div>
    </div>
    <div class="copyright">
        <div class="container-fluid">
            <div class="row">
                <div class="col-sm-9">
                    <p style="font-size: smaller;">
                        <spring:message code="website.copyright.text"/>
                    </p>
                </div>
                <!-- Social Links -->
                <div class="col-sm-3">
                    <ul class="footer-socials list-inline" style="font-size: smaller;">
                        <li>
                            <spring:message code="website.designAndDevelopedBy.text"/>
                            <a target="_blank" href="http://www.baseltd.com">
                                <spring:message code="website.baseLimited.text"/>
                            </a>
                        </li>
                    </ul>
                </div>
            </div>
        </div>
    </div>
</footer>
<%--<script src="<c:out value="/resources/website/assets/js/jquery-1.11.1.min.js"/>"></script>--%>
<%--<script src="<c:out value="/resources/website/assets/js/bootstrap.min.js"/>"></script>
<script src="/resources/website/assets/js/jquery.incremental-counter.js" type="text/javascript"></script>
<script>
    $(".min-incremental-counter").incrementalCounter();
</script>--%>



