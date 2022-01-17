<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<div class="panel panel-success">
    <!-- Default panel contents -->
    <div class="panel-heading  text-center text-primary">Act Schedule<span class="pull-right"><a
            href="#ActSchedule/index" role="button"> <i class="fa fa-history" aria-hidden="true"></i>
Back to List</a></span></div>
    <div class="panel-body">
        <form:form method="post" role="form" modelAttribute="actSchedule"
                   action="${contextPath}/admin/actSchedule${action}?${_csrf.parameterName}=${_csrf.token}"
                   class="form-horizontal" data-validation="true" data-ajax-submit="true" novalidate="novalidate">
            <spring:bind path="actId.id">
                <div class="form-group ${(not empty status.errorMessage) ? ' has-error':''}">
                    <label for="inputactId.id" class="col-sm-2 control-label">Act</label>
                    <div class="col-sm-9" style="width: 973px;">
                        <form:select class="form-control selectpicker" data-selected="${actSchedule.actId.id}" data-live-search="true"
                                     data-itemLabel="shortTitle" data-itemValue="id"
                                     data-validation="trim|is_exist[Act.id]|integer" path="actId.id" id="inputactId.id">
                            <form:option value="0"> --SELECT--</form:option>
                            <form:options items="${actList}" itemLabel="shortTitle" itemValue="id"></form:options>
                        </form:select>
                        <form:errors path="actId.id" cssClass="help-block text-danger" element="span"/>
                    </div>
                </div>
            </spring:bind><spring:bind path="title">
            <div class="form-group ${(not empty status.errorMessage) ? ' has-error':''}">
                <label for="inputtitle" class="col-sm-2 control-label">Title</label>
                <div class="col-sm-9">
                    <form:input type="text" class="form-control"
                                data-validation="trim|required|min_length[1]|max_length[255]" id="inputtitle"
                                path="title" placeholder="Title"/>
                    <form:errors path="title" cssClass="help-block text-danger" element="span"/>
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
                    <a href="#ActSchedule/index" role="button" class="btn btn-warning">Cancel</a>
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