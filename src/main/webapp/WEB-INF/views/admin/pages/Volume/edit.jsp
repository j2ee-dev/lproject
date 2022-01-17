<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<div class="panel panel-success">
	<%@ include file="/WEB-INF/views/admin/includes/edit_security.jsp" %>
    <!-- Default panel contents -->
    <div class="panel-heading  text-center text-primary">Volume<span class="pull-right"><a href="#Volume/index" role="button"> <i class="fa fa-history" aria-hidden="true"></i>
Back to List</a></span></div>
    <div class="panel-body">
		<form:form method="post" role="form" modelAttribute="volume" action="${contextPath}/admin/volume${action}?${_csrf.parameterName}=${_csrf.token}" class="form-horizontal" data-validation="true" data-ajax-submit="true" novalidate="novalidate">
		    
       	        <spring:bind path="volumeNumber">
       	        <div class="form-group ${(not empty status.errorMessage) ? ' has-error':''}">
				    	<label for="inputvolumeNumber" class="col-sm-2 control-label">Volume Number</label>
				    	<div class="col-sm-9">
				      		<form:input type="number" class="form-control" data-validation="trim|required|is_unique[Volume.volumeNumber]|integer" id="inputvolumeNumber" path="volumeNumber"  placeholder="Volume Number"/>
				      		<form:errors path="volumeNumber" cssClass="help-block text-danger" element="span" />
				    	</div>
				  	</div>
				 </spring:bind>
				<spring:bind path="volumeName">
       	        <div class="form-group ${(not empty status.errorMessage) ? ' has-error':''}">
				    	<label for="inputvolumeName" class="col-sm-2 control-label">Volume Name</label>
				    	<div class="col-sm-9">
				      		<form:input type="text" class="form-control" data-validation="trim|required|min_length[1]|max_length[100]" id="inputvolumeName" path="volumeName"  placeholder="Volume Name"/>
				      		<form:errors path="volumeName" cssClass="help-block text-danger" element="span" />
				    	</div>
				  	</div>
				  	</spring:bind><spring:bind path="volumeHead">
       	        <div class="form-group ${(not empty status.errorMessage) ? ' has-error':''}">
				    	<label for="inputvolumeHead" class="col-sm-2 control-label">Volume Head</label>
				    	<div class="col-sm-9">
				      		<form:input type="text" class="form-control" data-validation="trim|required|min_length[1]|max_length[255]" id="inputvolumeHead" path="volumeHead"  placeholder="Volume Head"/>
				      		<form:errors path="volumeHead" cssClass="help-block text-danger" element="span" />
				    	</div>
				  	</div>
				  	</spring:bind><spring:bind path="description">
       	        <div class="form-group ${(not empty status.errorMessage) ? ' has-error':''}">
				    	<label for="inputdescription" class="col-sm-2 control-label">Description</label>
				    	<div class="col-sm-8">
				      		<form:textarea class="form-control richtext" data-validation="trim" id="inputdescription" path="description"  placeholder="Description"/>
				      		<form:errors path="description" cssClass="help-block text-danger" element="span" />
				    	</div>
				  	</div>
				  	</spring:bind><spring:bind path="volumeYear">
       	        <div class="form-group ${(not empty status.errorMessage) ? ' has-error':''}">
				    	<label for="inputvolumeYear" class="col-sm-2 control-label">Volume Year</label>
				    	<div class="col-sm-9">
				      		<form:input type="text" class="form-control" data-validation="trim|min_length[1]|max_length[255]" id="inputvolumeYear" path="volumeYear"  placeholder="Volume Year"/>
				      		<form:errors path="volumeYear" cssClass="help-block text-danger" element="span" />
				    	</div>
				  	</div>
				  	</spring:bind><spring:bind path="status">
       	        	<div class="form-group ${(not empty status.errorMessage) ? ' has-error':''}">
       	   	    	<label for="inputstatus" class="col-sm-2 control-label">Status</label>
				    	<div class="col-sm-9">
				    		<div class="checkbox-inline">
				      			<form:checkbox data-validation="trim|required|is_boolean" id="inputstatus" path="status"  value="1" /> Status
				      			<form:errors path="status" cssClass="help-block text-danger" element="span" />
							</div>
				    	</div>
				  	</div>
				  	</spring:bind><spring:bind path="isBangla">
       	        	<div class="form-group ${(not empty status.errorMessage) ? ' has-error':''}">
       	   	    	<label for="inputisBangla" class="col-sm-2 control-label">Is Bangla</label>
				    	<div class="col-sm-9">
				    		<div class="checkbox-inline">
				      			<form:checkbox data-validation="trim|required|is_boolean" id="inputisBangla" path="isBangla"  value="1" /> Is Bangla
				      			<form:errors path="isBangla" cssClass="help-block text-danger" element="span" />
							</div>
				    	</div>
				  	</div>
				  	</spring:bind>
		    <div class="form-group">
		        <div class="col-sm-offset-4 col-sm-5">
		            <button type="submit" class="btn btn-primary"> &nbsp; Save &nbsp; </button> &nbsp; &nbsp; 
		            <a href="#Volume/index" role="button" class="btn btn-warning">Cancel</a> 
		        </div>
		    </div>
		</form:form>
	</div>
</div>