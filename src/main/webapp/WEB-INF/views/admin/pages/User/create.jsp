<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<%@ include file="/WEB-INF/views/admin/includes/create_security.jsp" %>
<div class="panel panel-success">
    <!-- Default panel contents -->
    <div class="panel-heading  text-center text-primary">User<span class="pull-right"><a href="#User/index" role="button"> <i class="fa fa-history" aria-hidden="true"></i>
Back to List</a></span></div>
    <div class="panel-body">
            <%--<form:form method="POST" commandName="user" class="form-signin"
                       action="${contextPath}/admin/user${action}?${_csrf.parameterName}=${_csrf.token}">--%>
            <form:form method="post" role="form" modelAttribute="user" action="${contextPath}/admin/user${action}?${_csrf.parameterName}=${_csrf.token}" class="form-horizontal" data-validation="true" data-ajax-submit="true" novalidate="novalidate">
                <div class="form-group">
                    <label class="col-sm-2 control-label">Username</label>
                    <div class="col-sm-9">
                        <form:input type="text" path="username" class="form-control"
                                    placeholder="Username" autofocus="true"></form:input>
                    </div>
                </div>
            <div class="form-group">
                <label class="col-sm-2 control-label">Password</label>
                <div class="col-sm-9">
                    <form:input type="password" path="password" class="form-control"
                                placeholder="Password"></form:input>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 control-label">Full Name</label>
                <div class="col-sm-9">
                    <form:input type="text" path="fullName" class="form-control"
                                placeholder="Username" autofocus="true"></form:input>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 control-label">Designation</label>
                <div class="col-sm-9">
                    <form:input type="text" path="designation" class="form-control"
                                placeholder="Username" autofocus="true"></form:input>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 control-label">Phone</label>
                <div class="col-sm-9">
                    <form:input type="text" path="phone" class="form-control"
                                placeholder="Username" autofocus="true"></form:input>
                </div>
            </div>
            <%--<div class="form-group">
                <label class="col-sm-2 control-label">Email</label>
                <div class="col-sm-9">
                    <form:input type="text" path="email" class="form-control"
                                placeholder="Username" autofocus="true"></form:input>
                </div>
            </div>--%>
            <div class="form-group">
                <div class="col-sm-offset-4 col-sm-5">
                    <button type="submit" class="btn btn-primary"> &nbsp; Save &nbsp; </button> &nbsp; &nbsp;
                    <a href="#User/index" role="button" class="btn btn-warning">Cancel</a>
                </div>
            </div>
        </form:form>
    </div>
</div>