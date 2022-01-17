<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<%@ include file="/WEB-INF/views/admin/includes/edit_security.jsp" %>
<div class="panel panel-success">
    <!-- Default panel contents -->
    <div class="panel-heading  text-center text-primary">Message<span class="pull-right"><a href="#Message/index" role="button"> <i class="fa fa-history" aria-hidden="true"></i>
Back to List</a></span></div>
    <div class="panel-body">
		<form:form method="post" role="form" modelAttribute="message" action="${contextPath}/admin/message${action}?${_csrf.parameterName}=${_csrf.token}" class="form-horizontal" data-validation="true" data-ajax-submit="true" novalidate="novalidate">
		    <spring:bind path="code">
       	        <div class="form-group ${(not empty status.errorMessage) ? ' has-error':''}">
				    	<label for="inputcode" class="col-sm-2 control-label">Code</label>
				    	<div class="col-sm-9">
				      		<form:input type="text" class="form-control" data-validation="trim|required|group_unique[Message.code,locale]|min_length[1]|max_length[50]" id="inputcode" path="code"  placeholder="Code"/>
				      		<form:errors path="code" cssClass="help-block text-danger" element="span" />
				    	</div>
				  	</div>
				  	</spring:bind><spring:bind path="locale">
       	        <div class="form-group ${(not empty status.errorMessage) ? ' has-error':''}">
				    	<label for="inputlocale" class="col-sm-2 control-label">Locale</label>
				    	<div class="col-sm-9">
				      		<form:input type="text" class="form-control" data-validation="trim|required|group_unique[Message.code,locale]|min_length[1]|max_length[10]" id="inputlocale" path="locale"  placeholder="Locale"/>
				      		<form:errors path="locale" cssClass="help-block text-danger" element="span" />
				    	</div>
				  	</div>
				  	</spring:bind><spring:bind path="value">
       	        <div class="form-group ${(not empty status.errorMessage) ? ' has-error':''}">
				    	<label for="inputvalue" class="col-sm-2 control-label">Value</label>
				    	<div class="col-sm-9">
				      		<form:input type="text" class="form-control" data-validation="trim|required" id="inputvalue" path="value"  placeholder="Value"/>
				      		<form:errors path="value" cssClass="help-block text-danger" element="span" />
				    	</div>
				  	</div>
				  	</spring:bind>
		    <div class="form-group">
		        <div class="col-sm-offset-4 col-sm-5">
		            <button type="submit" class="btn btn-primary"> &nbsp; Save &nbsp; </button> &nbsp; &nbsp; 
		            <a href="#Message/index" role="button" class="btn btn-warning">Cancel</a> 
		        </div>
		    </div>
		</form:form>
	</div>
</div>