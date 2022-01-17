<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<c:set var="lang" value="${pageContext.response.locale}"/>
<script src='https://www.google.com/recaptcha/api.js'></script>

<script>
    function onSubmit(token) {
        document.getElementById("contactUsForm").submit();
    }
</script>


<div class="row well well-sm" id="pageContent">
    <div class="heading heading-v2 margin-bottom-40">
        <h2><spring:message code="website.contactus.text"/></h2>
    </div>
       <div class="row">
           <div class="col-sm-12">

                <div class="address md-margin-bottom-40" style="color: #000000">
                   <spring:message code="website.lapad.text"/> <br>
                   <spring:message code="website.minlaw.text"/> <br>
                   <spring:message code="website.address.text"/> <br>
                   <spring:message code="website.phone.text"/> : <spring:message code="website.phone.no"/> <br>
                   <%--<spring:message code="website.fax.text"/> : <spring:message code="website.fax.no"/>  <br>--%>
                   <spring:message code="website.email.text"/> : <a href="ap@legislativediv.gov.bd" class=""><spring:message code="website.email.address"/> </a>
               </div>

           </div>
       </div>
    <div class="heading heading-v2 margin-bottom-40">
        <h2><spring:message code="website.feedback.text"/></h2>
    </div>
    <div class="row">
        <div class="col-sm-12">
            <form:form cssClass="form-horizontal" name="contactUsForm" id="contactUsForm" method="post" role="form" modelAttribute="feedBack" action="${contextPath}/contact-us.html?${_csrf.parameterName}=${_csrf.token}" onsubmit="return validateForm();"  data-ajax-submit="true" novalidate="novalidate">
                <spring:bind path="name">
                    <div class="form-group ${(not empty status.errorMessage) ? ' has-error':''}">
                        <label for="inputname" class="col-sm-2 control-label"><spring:message code="website.nanem.text"/></label>
                        <div class="col-sm-5">
                            <form:input type="text" class="form-control" data-validation="trim|required|min_length[1]|max_length[255]" id="inputname" value="${fn:escapeXml(name)}" path="name" name="name"  placeholder="Name"/>
                            <form:errors path="name" cssClass="help-block text-danger" element="span" />
                        </div>
                    </div>
                </spring:bind><spring:bind path="designation">
                <div class="form-group ${(not empty status.errorMessage) ? ' has-error':''}">
                    <label for="inputdesignation" class="col-sm-2 control-label"><spring:message code="website.designation.text"/></label>
                    <div class="col-sm-5">
                        <form:input type="text" class="form-control" data-validation="trim|min_length[1]|max_length[255]" id="inputdesignation" value="${fn:escapeXml(designation)}" path="designation" name="designation"  placeholder="Designation"/>
                        <form:errors path="designation" cssClass="help-block text-danger" element="span" />
                    </div>
                </div>
            </spring:bind><spring:bind path="email">
                <div class="form-group ${(not empty status.errorMessage) ? ' has-error':''}">
                    <label for="inputemail" class="col-sm-2 control-label"><spring:message code="website.email.text"/></label>
                    <div class="col-sm-5">
                        <form:input type="email" class="form-control" data-validation="trim|required|valid_email|min_length[1]|max_length[255]" id="inputemail" value="${fn:escapeXml(email)}" path="email" name="email" placeholder="Email"/>
                        <form:errors path="email" cssClass="help-block text-danger" element="span" />
                    </div>
                </div>
            </spring:bind><spring:bind path="phone">
                <div class="form-group ${(not empty status.errorMessage) ? ' has-error':''}">
                    <label for="inputphone" class="col-sm-2 control-label"><spring:message code="website.phone.text"/></label>
                    <div class="col-sm-5">
                        <form:input type="text" class="form-control" data-validation="trim|valid_bd_phone_no|min_length[1]|max_length[255]" id="inputphone" value="${fn:escapeXml(phone)}" path="phone" name="phone" placeholder="Phone"/>
                        <form:errors path="phone" cssClass="help-block text-danger" element="span" />
                    </div>
                </div>
            </spring:bind><spring:bind path="message">
                <div class="form-group ${(not empty status.errorMessage) ? ' has-error':''}">
                    <label for="inputmessage" class="col-sm-2 control-label"><spring:message code="website.message.text"/></label>
                    <div class="col-sm-9">
                        <form:textarea rows="5" class="form-control " data-validation="trim|required|min_length[1]|max_length[255]" id="inputmessage" value="${fn:escapeXml(message)}" path="message" name="message" placeholder="Message"/>
                        <form:errors path="message" cssClass="help-block text-danger" element="span" />
                    </div>
                </div>
            </spring:bind>
                <div class="form-group">
                    <label class="col-sm-2"></label>
                    <div class="col-sm-9">
                        <div class="g-recaptcha" data-sitekey="<spring:eval expression="@propertyConfigurer.getProperty('recaptcha.validation.siteKey')" />"></div>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-offset-4 col-sm-5">
                        <button type="submit" class="btn btn-primary"> &nbsp; <spring:message code="website.submit.text"/> &nbsp; </button> &nbsp; &nbsp;
                        <button type="reset" class="btn btn-warning"><spring:message code="website.reset.text"/></button>
                    </div>
                </div>
            </form:form>
        </div>
    </div>
</div>