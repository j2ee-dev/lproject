<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<div class="panel panel-success">
    <!-- Default panel contents -->
    <div class="panel-heading  text-center text-primary">Part Name Footnote<span class="pull-right"><a href="#PartNameFootnote/index" role="button"> <i class="fa fa-history" aria-hidden="true"></i>
Back to List</a></span></div>
    <div class="panel-body">
		<form:form method="post" role="form" modelAttribute="partNameFootnote" action="${contextPath}/admin/partNameFootnote${action}?${_csrf.parameterName}=${_csrf.token}" class="form-horizontal" data-validation="true" data-ajax-submit="true" novalidate="novalidate">
		    <spring:bind path="partId.id">
       	        	<div class="form-group ${(not empty status.errorMessage) ? ' has-error':''}">
				    	<label for="inputpartId.id" class="col-sm-2 control-label">Part</label>
				    	<div class="col-sm-9">
				      		<form:select class="form-control"  data-selected="${partNameFootnote.partId.id}" data-itemLabel="partNo" data-itemValue="id" data-validation="trim|is_exist[ActPart.id]|integer" path="partId.id" id="inputpartId.id">
    							<form:option value="0"> --SELECT--</form:option>
    							<form:options items="${actPartList}" itemLabel="partNo" itemValue="id"></form:options>
  							</form:select>
  							<form:errors path="partId.id" cssClass="help-block text-danger" element="span" />
				    	</div>
				  	</div>
				  	</spring:bind><spring:bind path="footnote">
       	        <div class="form-group ${(not empty status.errorMessage) ? ' has-error':''}">
				    	<label for="inputfootnote" class="col-sm-2 control-label">Footnote</label>
				    	<div class="col-sm-8">
				      		<form:textarea class="form-control " data-validation="trim|required" id="inputfootnote" path="footnote"  placeholder="Footnote"/>
				      		<form:errors path="footnote" cssClass="help-block text-danger" element="span" />
				    	</div>
				  	</div>
				  	</spring:bind>
       	        <spring:bind path="footnoteNumber">
       	        <div class="form-group ${(not empty status.errorMessage) ? ' has-error':''}">
				    	<label for="inputfootnoteNumber" class="col-sm-2 control-label">Footnote Number</label>
				    	<div class="col-sm-9">
				      		<form:input type="number" class="form-control" data-validation="trim|required|integer" id="inputfootnoteNumber" path="footnoteNumber"  placeholder="Footnote Number"/>
				      		<form:errors path="footnoteNumber" cssClass="help-block text-danger" element="span" />
				    	</div>
				  	</div>
				 </spring:bind>
				<spring:bind path="footnoteSign">
       	        <div class="form-group ${(not empty status.errorMessage) ? ' has-error':''}">
				    	<label for="inputfootnoteSign" class="col-sm-2 control-label">Footnote Sign</label>
				    	<div class="col-sm-9">
				      		<form:input type="text" class="form-control" data-validation="trim|exact_length[1]" id="inputfootnoteSign" path="footnoteSign"  placeholder="Footnote Sign"/>
				      		<form:errors path="footnoteSign" cssClass="help-block text-danger" element="span" />
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
				  	</spring:bind>
		    <div class="form-group">
		        <div class="col-sm-offset-4 col-sm-5">
		            <button type="submit" class="btn btn-primary"> &nbsp; Save &nbsp; </button> &nbsp; &nbsp; 
		            <a href="#PartNameFootnote/index" role="button" class="btn btn-warning">Cancel</a> 
		        </div>
		    </div>
		</form:form>
	</div>
</div>