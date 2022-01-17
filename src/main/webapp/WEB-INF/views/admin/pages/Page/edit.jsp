<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<%@ include file="/WEB-INF/views/admin/includes/edit_security.jsp" %>
<div class="panel panel-success">
    <!-- Default panel contents -->
    <div class="panel-heading  text-center text-primary">Page<span class="pull-right"><a href="#Page/index" role="button"> <i class="fa fa-history" aria-hidden="true"></i>
Back to List</a></span></div>
    <div class="panel-body">
		<form:form method="post" role="form" modelAttribute="page" action="${contextPath}/admin/page${action}?${_csrf.parameterName}=${_csrf.token}" class="form-horizontal" data-validation="true" data-ajax-submit="true" novalidate="novalidate">
		    <spring:bind path="pageUrl">
       	        <div class="form-group ${(not empty status.errorMessage) ? ' has-error':''}">
				    	<label for="inputpageUrl" class="col-sm-2 control-label">Page Url</label>
				    	<div class="col-sm-9">
				      		<form:input type="url" class="form-control" data-validation="trim|required|min_length[1]|max_length[255]" id="inputpageUrl" path="pageUrl"  placeholder="Page Url"/>
				      		<form:errors path="pageUrl" cssClass="help-block text-danger" element="span" />
				    	</div>
				  	</div>
				  	</spring:bind><spring:bind path="pageTitle">
       	        <div class="form-group ${(not empty status.errorMessage) ? ' has-error':''}">
				    	<label for="inputpageTitle" class="col-sm-2 control-label">Page Title</label>
				    	<div class="col-sm-9">
				      		<form:input type="text" class="form-control" data-validation="trim|required|min_length[1]|max_length[255]" id="inputpageTitle" path="pageTitle"  placeholder="Page Title"/>
				      		<form:errors path="pageTitle" cssClass="help-block text-danger" element="span" />
				    	</div>
				  	</div>
				  	</spring:bind><spring:bind path="pageContent">
       	        <div class="form-group ${(not empty status.errorMessage) ? ' has-error':''}">
				    	<label for="inputpageContent" class="col-sm-2 control-label">Page Content</label>
				    	<div class="col-sm-8">
				      		<form:textarea class="form-control richtext" data-validation="trim|required" id="inputpageContent" path="pageContent"  placeholder="Page Content"/>
				      		<form:errors path="pageContent" cssClass="help-block text-danger" element="span" />
				    	</div>
				  	</div>
				  	</spring:bind>
		    <div class="form-group">
		        <div class="col-sm-offset-4 col-sm-5">
		            <button type="submit" class="btn btn-primary"> &nbsp; Save &nbsp; </button> &nbsp; &nbsp; 
		            <a href="#Page/index" role="button" class="btn btn-warning">Cancel</a> 
		        </div>
		    </div>
		</form:form>
	</div>
</div>