<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<%@ include file="/WEB-INF/views/admin/includes/create_security.jsp" %>
<div class="panel panel-success">
    <!-- Default panel contents -->
    <div class="panel-heading  text-center text-primary">Repealed Act<span class="pull-right"><a
            href="#RepealedAct/index" role="button"> <i class="fa fa-history" aria-hidden="true"></i>
Back to List</a></span></div>
    <div class="panel-body">
        <form:form method="post" role="form" modelAttribute="repealedAct"
                   action="${contextPath}/admin/repealedAct${action}?${_csrf.parameterName}=${_csrf.token}"
                   class="form-horizontal" data-validation="true" data-ajax-submit="true" novalidate="novalidate">
            <spring:bind path="actId.id">
                <div class="form-group ${(not empty status.errorMessage) ? ' has-error':''}">
                    <label for="inputactId.id" class="col-sm-2 control-label">Act No</label>
                    <div class="col-sm-9" style="width: 973px;">
                        <form:select data-live-search="true" class="form-control selectpicker searchable"
                                     data-selected="${repealedAct.actId.id}"
                                     data-itemLabel="id" data-itemValue="id"
                                     data-validation="trim|is_exist[Act.id]|integer" path="actId.id" id="inputactId.id">
                            <form:option value="0"> --SELECT--</form:option>
                            <form:options items="${actList}" itemLabel="id" itemValue="id"></form:options>
                        </form:select>
                        <form:errors path="actId.id" cssClass="help-block text-danger" element="span"/>
                    </div>
                </div>
            </spring:bind><spring:bind path="description">
            <div class="form-group ${(not empty status.errorMessage) ? ' has-error':''}">
                <label for="inputdescription" class="col-sm-2 control-label">Description</label>
                <div class="col-sm-8">
                    <form:textarea class="form-control richtext" data-validation="trim|required" id="inputdescription"
                                   path="description" placeholder="Description"/>
                    <form:errors path="description" cssClass="help-block text-danger" element="span"/>
                </div>
            </div>
        </spring:bind><spring:bind path="status">
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
            <div class="form-group">
                <div class="col-sm-offset-4 col-sm-5">
                    <button type="submit" class="btn btn-primary"> &nbsp; Save &nbsp; </button>
                    &nbsp; &nbsp;
                    <a href="#RepealedAct/index" role="button" class="btn btn-warning">Cancel</a>
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