<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<style type="text/css">
    div {
        padding-left: 10px;

    }

    .user label {
        padding-left: 10px;
        margin-top: -29px;
    }
</style>

<script>
    $('#select-all-view').click(function (event) {
        if (this.checked) {
            // Iterate each checkbox
            $(':checkbox').each(function () {
                this.checked = true;
            });
        }
        else {
            $(':checkbox').each(function () {
                this.checked = false;
            });
        }
    });
</script>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<%@ include file="/WEB-INF/views/admin/includes/create_security.jsp" %>
<div class="panel panel-success">
    <!-- Default panel contents -->
    <div class="panel-heading  text-center text-primary">User<span class="pull-right"><a href="#User/index"
                                                                                         role="button"> <i
            class="fa fa-history" aria-hidden="true"></i>
Back to List</a></span></div>
    <div class="panel-body">

        <div class="row">
            <div class="col-md-12 ">

                <form:form method="post" role="form" modelAttribute="permission"
                           action="${contextPath}/admin/permission${action}?${_csrf.parameterName}=${_csrf.token}"
                           class="form-horizontal" data-validation="true" data-ajax-submit="true"
                           novalidate="novalidate">
                    <div class="row">
                        <div class="form-group col-md-4 col-md-offset-4 ${status.error ? 'has-error' : ''}">
                            <form:select class="form-control" path="username">
                                <form:options items="${users}" itemLabel="username"
                                              itemValue="username"></form:options>
                            </form:select>
                            </br>
                            <div class="row">
                                <div class="col-md-12">
                                    <button type="submit" class="btn btn-primary"> &nbsp; Save &nbsp; </button>
                                    &nbsp; &nbsp;
                                    <a href="#Permission/index" role="button" class="btn btn-warning">Cancel</a>
                                </div>
                            </div>
                        </div>
                    </div>
                    <h4 class="form-signin-heading"><input type="checkbox" name="select-all-view" id="select-all-view"/>&nbsp;Select
                        All(Views)</h4>
                    <div class="form-group col-md-4 user ${status.error ? 'has-error' : ''}">
                        <h4 class="form-signin-heading">&nbsp;View Permission</h4>
                        <form:checkboxes path="views" items="${views}" element="div" itemLabel="title"
                                         itemValue="url"/>
                    </div>
                    <div class="form-group col-md-4 ${status.error ? 'has-error' : ''}">
                        <h4 class="form-signin-heading">&nbsp;Create Permission</h4>
                        <form:checkboxes path="createViews" items="${createViews}" element="div" itemLabel="title"
                                         itemValue="url"/>
                    </div>
                    <div class="form-group col-md-4 ${status.error ? 'has-error' : ''}">
                        <h4 class="form-signin-heading">Edit Permission</h4>
                        <form:checkboxes path="editChecks" items="${editViews}" element="div" itemLabel="title"
                                         itemValue="url"/>
                    </div>
                </form:form>
            </div>
        </div>
    </div>
</div>


