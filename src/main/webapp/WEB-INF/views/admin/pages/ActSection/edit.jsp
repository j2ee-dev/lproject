<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<%@ include file="/WEB-INF/views/admin/includes/edit_security.jsp" %>
<div class="panel panel-success">
    <!-- Default panel contents -->
    <div class="panel-heading  text-center text-primary">Act Section<span class="pull-right"><a href="#ActSection/index"
                                                                                                role="button"> <i
            class="fa fa-history" aria-hidden="true"></i>
Back to List</a></span></div>
    <div class="panel-body">
        <form:form method="post" role="form" modelAttribute="actSection"
                   action="${contextPath}/admin/actSection${action}?${_csrf.parameterName}=${_csrf.token}"
                   class="form-horizontal" data-validation="true" data-ajax-submit="true" novalidate="novalidate">
            <spring:bind path="volume.id">
                <div class="form-group ${(not empty status.errorMessage) ? ' has-error':''}">
                    <label for="inputvolume.id" class="col-sm-2 control-label">Volume</label>
                    <div class="col-sm-9">
                        <form:select class="form-control"
                                     data-change-pull="actId.id|volume.id|${contextPath}/admin/actSection/act-list"
                                     data-selected="${actSection.volume.id}" data-itemLabel="volumeName"
                                     data-itemValue="id" data-validation="trim|is_exist[Volume.id]|integer"
                                     path="volume.id" id="inputvolume.id">
                            <form:option value="0"> --SELECT--</form:option>
                            <form:options items="${volumeList}" itemLabel="volumeName" itemValue="id"></form:options>
                        </form:select>
                        <form:errors path="volume.id" cssClass="help-block text-danger" element="span"/>
                    </div>
                </div>
            </spring:bind><spring:bind path="actId.id">
            <div class="form-group ${(not empty status.errorMessage) ? ' has-error':''}">
                <label for="inputactId.id" class="col-sm-2 control-label">Act</label>
                <div class="col-sm-9">
                    <form:select class="form-control" data-change-pull="partId.id|actId.id|${contextPath}/admin/actSection/actPart-list" data-change-pulls="chapterId.id|actId.id|${contextPath}/admin/actSection/actPart-lists" data-selected="${actSection.actId.id}" data-itemLabel="shortTitle"
                                 data-itemValue="id" data-validation="trim|is_exist[Act.id]|integer" path="actId.id"
                                 id="inputactId.id">
                        <form:option value="0"> --SELECT--</form:option>
                        <form:options items="${actList}" itemLabel="shortTitle" itemValue="id"></form:options>
                    </form:select>
                    <form:errors path="actId.id" cssClass="help-block text-danger" element="span"/>
                </div>
            </div>
        </spring:bind><spring:bind path="partId.id">
            <div class="form-group ${(not empty status.errorMessage) ? ' has-error':''}">
                <label for="inputpartId.id" class="col-sm-2 control-label">Part</label>
                <div class="col-sm-9">
                    <form:select class="form-control" data-null-accepted="true" data-selected="${actSection.partId.id}"
                                 data-itemLabel="id" data-itemValue="id"
                                 data-validation="trim|is_exist[ActPart.id]|integer" path="partId.id"
                                 id="inputpartId.id">
                        <form:option value="0"> --SELECT--</form:option>
                        <form:options items="${actPart}" itemLabel="id" itemValue="id"></form:options>
                    </form:select>
                    <form:errors path="partId.id" cssClass="help-block text-danger" element="span"/>
                </div>
            </div>
        </spring:bind><spring:bind path="chapterId.id">
            <div class="form-group ${(not empty status.errorMessage) ? ' has-error':''}">
                <label for="inputchapterId.id" class="col-sm-2 control-label">Chapter</label>
                <div class="col-sm-9">
                    <form:select class="form-control" data-selected="${actSection.chapterId.id}"
                                 data-itemLabel="id" data-itemValue="id"
                                 data-validation="trim|is_exist[ActChapter.id]|integer" path="chapterId.id"
                                 id="inputchapterId.id">
                        <form:option value="0"> --SELECT--</form:option>
                        <form:options items="${actChapter}" itemLabel="chapterName" itemValue="id"></form:options>
                    </form:select>
                    <form:errors path="chapterId.id" cssClass="help-block text-danger" element="span"/>
                </div>
            </div>
        </spring:bind><spring:bind path="sectionNo">
            <div class="form-group ${(not empty status.errorMessage) ? ' has-error':''}">
                <label for="inputsectionNo" class="col-sm-2 control-label">Section No(eng)</label>
                <div class="col-sm-9">
                    <form:input type="text" class="form-control" data-validation="trim|required|float"
                                id="inputsectionNo" path="sectionNo" placeholder="Section No"/>
                    <form:errors path="sectionNo" cssClass="help-block text-danger" element="span"/>
                </div>
            </div>
        </spring:bind><spring:bind path="sectionName">
            <div class="form-group ${(not empty status.errorMessage) ? ' has-error':''}">
                <label for="inputsectionName" class="col-sm-2 control-label">Section Name</label>
                <div class="col-sm-9">
                    <form:input type="text" class="form-control" data-validation="trim|min_length[1]|max_length[255]"
                                id="inputsectionName" path="sectionName" placeholder="Section Name"/>
                    <form:errors path="sectionName" cssClass="help-block text-danger" element="span"/>
                </div>
            </div>
        </spring:bind>
            <jsp:include page="/WEB-INF/views/admin/pages/ActSection/includes/sectionNameFootnote.jsp"/>
            <spring:bind path="sectionHead">
                <div class="form-group ${(not empty status.errorMessage) ? ' has-error':''}">
                    <label for="inputsectionHead" class="col-sm-2 control-label">Section Head</label>
                    <div class="col-sm-8">
                        <form:textarea class="form-control richtext" data-validation="trim" id="inputsectionHead"
                                       path="sectionHead" placeholder="Section Head"/>
                        <form:errors path="sectionHead" cssClass="help-block text-danger" element="span"/>
                    </div>
                </div>
            </spring:bind>
            <jsp:include page="/WEB-INF/views/admin/pages/ActSection/includes/sectionHeadFootnote.jsp"/>
            <spring:bind path="sectionDescription">
                <div class="form-group ${(not empty status.errorMessage) ? ' has-error':''}">
                    <label for="inputsectionDescription" class="col-sm-2 control-label">Section Description</label>
                    <div class="col-sm-8">
                        <form:textarea class="form-control richtext" data-validation="trim" id="inputsectionDescription"
                                       path="sectionDescription" placeholder="Section Description"/>
                        <form:errors path="sectionDescription" cssClass="help-block text-danger" element="span"/>
                    </div>
                </div>
            </spring:bind>
            <jsp:include page="/WEB-INF/views/admin/pages/ActSection/includes/sectionDescriptionFootnote.jsp"/>
            <spring:bind path="sectionTable">
                <div class="form-group ${(not empty status.errorMessage) ? ' has-error':''}">
                    <label for="inputsectionTable" class="col-sm-2 control-label">Section Table</label>
                    <div class="col-sm-8">
                        <form:textarea class="form-control richtext" data-validation="trim" id="inputsectionTable"
                                       path="sectionTable" placeholder="Section Table"/>
                        <form:errors path="sectionTable" cssClass="help-block text-danger" element="span"/>
                    </div>
                </div>
            </spring:bind>
            <jsp:include page="/WEB-INF/views/admin/pages/ActSection/includes/sectionTableFootnote.jsp"/>
            <spring:bind path="updateNote">
                <div class="form-group ${(not empty status.errorMessage) ? ' has-error':''}">
                    <label for="inputupdateNote" class="col-sm-2 control-label">Update Note</label>
                    <div class="col-sm-8">
                        <form:textarea class="form-control " data-validation="trim" id="inputupdateNote"
                                       path="updateNote" placeholder="Update Note"/>
                        <form:errors path="updateNote" cssClass="help-block text-danger" element="span"/>
                    </div>
                </div>
            </spring:bind><spring:bind path="attachment">
            <div class="form-group ${(not empty status.errorMessage) ? ' has-error':''}">
                <label for="inputattachment" class="col-sm-2 control-label">Attachment</label>
                <div class="col-sm-9">
                    <label class="btn btn-default btn-file">
                        Browse <input type="file" class="form-control"
                                      data-validation="trim|file_allowed_type[jpeg,jpg,png,doc,docx,pdf,ppt,pptx,csv,xls,xls,zip,mp3,mp4,flv]|min_length[1]|max_length[255]"
                                      id="inputattachment" name="attachmentFile" placeholder="Attachment" multiple/>

                    </label>
                    <form:errors path="attachment" cssClass="help-block text-danger" element="span"/>

                </div>
            </div>
        </spring:bind><spring:bind path="status">
            <div class="form-group ${(not empty status.errorMessage) ? ' has-error':''}">
                <label for="inputstatus" class="col-sm-2 control-label">Status</label>
                <div class="col-sm-9">
                    <div class="checkbox-inline">
                        <form:checkbox data-validation="trim|required|is_boolean" id="inputstatus" path="status"
                                       value="1"/> Status
                        <form:errors path="status" cssClass="help-block text-danger" element="span"/>
                    </div>
                </div>
            </div>
        </spring:bind>
            <div class="form-group">
                <div class="col-sm-offset-4 col-sm-5">
                    <button type="submit" class="btn btn-primary"> &nbsp; Save &nbsp; </button>
                    &nbsp; &nbsp;
                    <a href="#ActSection/index" role="button" class="btn btn-warning">Cancel</a>
                </div>
            </div>
        </form:form>
    </div>
</div>