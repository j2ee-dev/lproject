<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<%@ include file="/WEB-INF/views/admin/includes/create_security.jsp" %>
<div class="panel panel-success">

    <!-- Default panel contents -->
    <div class="panel-heading  text-center text-primary">Act<span class="pull-right"><a href="#Act/index" role="button"> <i
            class="fa fa-history" aria-hidden="true"></i>
Back to List</a></span></div>
    <div class="panel-body">
        <form:form method="post" role="form" modelAttribute="act"
                   action="${contextPath}/admin/act${action}?${_csrf.parameterName}=${_csrf.token}"
                   class="form-horizontal" data-validation="true" data-ajax-submit="true" novalidate="novalidate">
            <spring:bind path="volume.id">
                <div class="form-group ${(not empty status.errorMessage) ? ' has-error':''}">
                    <label for="inputvolume.id" class="col-sm-2 control-label">Volume</label>
                    <div class="col-sm-9" style="width: 973px;">
                        <form:select class="form-control selectpicker" data-selected="${act.volume.id}"
                                     data-itemLabel="volumeName"
                                     data-live-search="true" data-itemValue="id"
                                     data-validation="trim|is_exist[Volume.id]|integer"
                                     path="volume.id" id="inputvolume.id">
                            <form:option value="0"> --SELECT--</form:option>
                            <form:options items="${volumeList}" itemLabel="volumeName" itemValue="id"></form:options>
                        </form:select>
                        <form:errors path="volume.id" cssClass="help-block text-danger" element="span"/>
                    </div>
                </div>
            </spring:bind><spring:bind path="shortTitle">
            <div class="form-group ${(not empty status.errorMessage) ? ' has-error':''}">
                <label for="inputshortTitle" class="col-sm-2 control-label">Short Title</label>
                <div class="col-sm-9">
                    <form:input type="text" class="form-control"
                                data-validation="trim|required|min_length[1]|max_length[255]" id="inputshortTitle"
                                path="shortTitle" placeholder="Short Title"/>
                    <form:errors path="shortTitle" cssClass="help-block text-danger" element="span"/>
                </div>
            </div>
        </spring:bind>
            <jsp:include page="/WEB-INF/views/admin/pages/Act/includes/shortTitleFootnote.jsp"/>







            <spring:bind path="shortTitleType">
                <div class="form-group ${(not empty status.errorMessage) ? ' has-error':''}">
                    <label for="inputshortTitleType" class="col-sm-2 control-label">ShortTitle Type</label>
                    <div class="col-sm-9" style="width: 973px;">
                        <form:select class="form-control selectpicker"
                                     path="shortTitleType"
                                     data-validation="trim|is_exist[shortTitleType]|string"
                                     id="inputshortTitleType"
                                     data-selected="${act.shortTitleType}">
                            <form:option value="0"> --SELECT--</form:option>
                            <form:option value="CST" label="CONSTITUTION "/>
                            <form:option value="APO" label="ACTING PRESIDENT'S ORDER "/>
                            <form:option value="BNO" label="BANGLADESH ORDINANCE "/>
                            <form:option value="PSO" label="PRESIDENT'S ORDER "/>
                            <form:option value="BPSO" label="BANGLADESH PRESIDENT'S ORDER "/>
                            <form:option value="EPA" label="EAST PAKISTAN ACT "/>
                            <form:option value="EPO" label="EAST PAKISTAN ORDINANCE "/>
                            <form:option value="REG" label="REGULATION "/>
                            <form:option value="BENR" label="BENGAL REGULATION "/>
                            <form:option value="BENA" label="BENGAL ACT "/>
                            <form:option value="EBR" label="EAST BENGAL REGULATION "/>
                            <form:option value="EPNR" label="EAST PAKISTAN REGULATION "/>
                        </form:select>
                        <form:errors path="shortTitleType" cssClass="help-block text-danger" element="span"/>
                    </div>
                </div>
            </spring:bind>

           <%-- <spring:bind path="shortTitleType">
                <div class="form-group ${(not empty status.errorMessage) ? ' has-error':''}">
                    <label for="inputshortTitleType" class="col-sm-2 control-label">ShortTitle Type</label>
                    <div class="col-sm-9" style="width: 973px;">
                        <form:select class="form-control selectpicker" data-selected="${act.shortTitleType}"
                                     data-itemLabel="volumeName"
                                     data-live-search="true" data-itemValue="id"
                                     data-validation="trim|is_exist[Volume.id]|integer"
                                     path="volume.id" id="inputvolume.id">
                            <form:option value="0"> --SELECT--</form:option>
                            <form:options items="${volumeList}" itemLabel="volumeName" itemValue="id"></form:options>
                        </form:select>
                        <form:errors path="volume.id" cssClass="help-block text-danger" element="span"/>
                    </div>
                </div>
            </spring:bind>--%>
            <spring:bind path="number">
                <div class="form-group ${(not empty status.errorMessage) ? ' has-error':''}">
                    <label for="inputnumber" class="col-sm-2 control-label">Number</label>
                    <div class="col-sm-9">
                        <form:input type="text" class="form-control"
                                    data-validation="trim|min_length[1]|max_length[255]" id="inputnumber" path="number"
                                    placeholder="Number"/>
                        <form:errors path="number" cssClass="help-block text-danger" element="span"/>
                    </div>
                </div>
            </spring:bind><spring:bind path="year">
            <div class="form-group ${(not empty status.errorMessage) ? ' has-error':''}">
                <label for="inputyear" class="col-sm-2 control-label">Year</label>
                <div class="col-sm-9">
                    <form:input type="text" class="form-control" data-validation="trim|min_length[1]|max_length[255]"
                                id="inputyear" path="year" placeholder="Year"/>
                    <form:errors path="year" cssClass="help-block text-danger" element="span"/>
                </div>
            </div>
        </spring:bind><spring:bind path="page">
            <div class="form-group ${(not empty status.errorMessage) ? ' has-error':''}">
                <label for="inputpage" class="col-sm-2 control-label">Page No</label>
                <div class="col-sm-9">
                    <form:input type="text" class="form-control" data-validation="trim|min_length[1]|max_length[255]"
                                id="inputpage" path="page" placeholder="Page No"/>
                    <form:errors path="page" cssClass="help-block text-danger" element="span"/>
                </div>
            </div>
        </spring:bind><spring:bind path="publishDate">
            <div class="form-group ${(not empty status.errorMessage) ? ' has-error':''}">
                <label for="inputpublishDate" class="col-sm-2 control-label">Publish Date</label>
                <div class="col-sm-9">
                    <form:input type="text" class="form-control" data-validation="trim|min_length[1]|max_length[255]"
                                id="inputpublishDate" path="publishDate" placeholder="Publish Date"/>
                    <form:errors path="publishDate" cssClass="help-block text-danger" element="span"/>
                </div>
            </div>
        </spring:bind><spring:bind path="actRole">
            <div class="form-group ${(not empty status.errorMessage) ? ' has-error':''}">
                <label for="inputactRole" class="col-sm-2 control-label">Act Role</label>
                <div class="col-sm-8">
                    <form:textarea class="form-control richtext" data-validation="trim" id="inputactRole" path="actRole"
                                   placeholder="Act Role"/>
                    <form:errors path="actRole" cssClass="help-block text-danger" element="span"/>
                </div>
            </div>
        </spring:bind>
            <jsp:include page="/WEB-INF/views/admin/pages/Act/includes/actRoleFootnote.jsp"/>
            <spring:bind path="preamble">
                <div class="form-group ${(not empty status.errorMessage) ? ' has-error':''}">
                    <label for="inputpreamble" class="col-sm-2 control-label">Preamble</label>
                    <div class="col-sm-8">
                        <form:textarea class="form-control richtext" data-validation="trim" id="inputpreamble"
                                       path="preamble" placeholder="Preamble"/>
                        <form:errors path="preamble" cssClass="help-block text-danger" element="span"/>
                    </div>
                </div>
            </spring:bind>
            <jsp:include page="/WEB-INF/views/admin/pages/Act/includes/preambleFootnote.jsp"/>
            <spring:bind path="preambleAttachment">
                <div class="form-group ${(not empty status.errorMessage) ? ' has-error':''}">
                    <label for="inputpreambleAttachment" class="col-sm-2 control-label">Preamble Attachment</label>
                    <div class="col-sm-9">
                        <label class="btn btn-default btn-file">
                            Browse <input type="file" class="form-control"
                                          data-validation="trim|file_allowed_type[jpeg,jpg,png,doc,docx,pdf,ppt,pptx,csv,xls,xls,zip,mp3,mp4,flv]|min_length[1]|max_length[255]"
                                          id="inputpreambleAttachment" name="preambleAttachmentFile"
                                          placeholder="Preamble Attachment" multiple/>

                        </label>
                        <form:errors path="preambleAttachment" cssClass="help-block text-danger" element="span"/>

                    </div>
                </div>
            </spring:bind><spring:bind path="displayPreambleText">
            <div class="form-group ${(not empty status.errorMessage) ? ' has-error':''}">
                <label for="inputdisplayPreambleText" class="col-sm-2 control-label">Display Preamble Text</label>
                <div class="col-sm-9">
                    <div class="checkbox-inline">
                        <form:checkbox data-validation="trim|is_boolean" id="inputdisplayPreambleText"
                                       path="displayPreambleText" value="1"/> Display Preamble Text
                        <form:errors path="displayPreambleText" cssClass="help-block text-danger" element="span"/>
                    </div>
                </div>
            </div>
        </spring:bind><spring:bind path="isOrdinance">
            <div class="form-group ${(not empty status.errorMessage) ? ' has-error':''}">
                <label for="inputisOrdinance" class="col-sm-2 control-label">Is Ordinance</label>
                <div class="col-sm-9">
                    <div class="checkbox-inline">
                        <form:checkbox data-validation="trim|is_boolean" id="inputisOrdinance" path="isOrdinance"
                                       value="1"/> Is Ordinance
                        <form:errors path="isOrdinance" cssClass="help-block text-danger" element="span"/>
                    </div>
                </div>
            </div>
        </spring:bind><spring:bind path="isBanglaAct">
            <div class="form-group ${(not empty status.errorMessage) ? ' has-error':''}">
                <label for="inputisBanglaAct" class="col-sm-2 control-label">Is Bangla Act</label>
                <div class="col-sm-9">
                    <div class="checkbox-inline">
                        <form:checkbox data-validation="trim|is_boolean" id="inputisBanglaAct" path="isBanglaAct"
                                       value="1"/> Is Bangla Act
                        <form:errors path="isBanglaAct" cssClass="help-block text-danger" element="span"/>
                    </div>
                </div>
            </div>
        </spring:bind>
            <spring:bind path="orderNo">
                <div class="form-group ${(not empty status.errorMessage) ? ' has-error':''}">
                    <label for="inputorderNo" class="col-sm-2 control-label">Order No</label>
                    <div class="col-sm-9">
                        <form:input type="number" class="form-control" data-validation="trim|integer" id="inputorderNo"
                                    path="orderNo" placeholder="Order No"/>
                        <form:errors path="orderNo" cssClass="help-block text-danger" element="span"/>
                    </div>
                </div>
            </spring:bind>
            <spring:bind path="mappingAct">
                <div class="form-group ${(not empty status.errorMessage) ? ' has-error':''}">
                    <label for="inputmappingAct" class="col-sm-2 control-label">Mapping Act</label>
                    <div class="col-sm-9">
                        <form:input type="number" class="form-control" data-validation="trim|integer" id="inputmappingAct"
                                    path="mappingAct" placeholder="Mapping Act"/>
                        <form:errors path="mappingAct" cssClass="help-block text-danger" element="span"/>
                    </div>
                </div>
            </spring:bind>
            <spring:bind path="status">
                <div class="form-group ${(not empty status.errorMessage) ? ' has-error':''}">
                    <label for="inputstatus" class="col-sm-2 control-label">Status</label>
                    <div class="col-sm-9">
                        <div class="checkbox-inline">
                            <form:checkbox data-validation="trim|is_boolean" id="inputstatus" path="status" value="1"/>
                            Status
                            <form:errors path="status" cssClass="help-block text-danger" element="span"/>
                        </div>
                    </div>
                </div>
            </spring:bind>
            <jsp:include page="/WEB-INF/views/admin/pages/Act/includes/actSchedule.jsp"/>
            <jsp:include page="/WEB-INF/views/admin/pages/Act/includes/gazettedCopy.jsp"/>
            <jsp:include page="/WEB-INF/views/admin/pages/Act/includes/actRulesRegulation.jsp"/>
            <jsp:include page="/WEB-INF/views/admin/pages/Act/includes/actAttachment.jsp"/>
            <div class="form-group">
                <div class="col-sm-offset-4 col-sm-5">
                    <button type="submit" class="btn btn-primary"> &nbsp; Save &nbsp; </button>
                    &nbsp; &nbsp;
                    <a href="#Act/index" role="button" class="btn btn-warning">Cancel</a>
                </div>
            </div>
        </form:form>
    </div>
</div>

<script>
    $(function () {
        $('.selectpicker').selectpicker();
    });
</script>