<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<%@ include file="/WEB-INF/views/admin/includes/edit_security.jsp" %>
<div class="panel panel-success">
    <!-- Default panel contents -->
    <div class="panel-heading  text-center text-primary">Faq<span class="pull-right"><a href="#Faq/index" role="button"> <i class="fa fa-history" aria-hidden="true"></i>
Back to List</a></span></div>
    <div class="panel-body">
		<form:form method="post" role="form" modelAttribute="faq" action="${contextPath}/admin/faq${action}?${_csrf.parameterName}=${_csrf.token}" class="form-horizontal" data-validation="true" data-ajax-submit="true" novalidate="novalidate">
		    <spring:bind path="questionEnglish">
       	        <div class="form-group ${(not empty status.errorMessage) ? ' has-error':''}">
				    	<label for="inputquestionEnglish" class="col-sm-2 control-label">Question English</label>
				    	<div class="col-sm-9">
				      		<form:input type="text" class="form-control" data-validation="trim|required|min_length[1]|max_length[255]" id="inputquestionEnglish" path="questionEnglish"  placeholder="Question English"/>
				      		<form:errors path="questionEnglish" cssClass="help-block text-danger" element="span" />
				    	</div>
				  	</div>
				  	</spring:bind><spring:bind path="questionBangla">
       	        <div class="form-group ${(not empty status.errorMessage) ? ' has-error':''}">
				    	<label for="inputquestionBangla" class="col-sm-2 control-label">Question Bangla</label>
				    	<div class="col-sm-9">
				      		<form:input type="text" class="form-control" data-validation="trim|required|min_length[1]|max_length[255]" id="inputquestionBangla" path="questionBangla"  placeholder="Question Bangla"/>
				      		<form:errors path="questionBangla" cssClass="help-block text-danger" element="span" />
				    	</div>
				  	</div>
				  	</spring:bind><spring:bind path="answerEnglish">
       	        <div class="form-group ${(not empty status.errorMessage) ? ' has-error':''}">
				    	<label for="inputanswerEnglish" class="col-sm-2 control-label">Answer English</label>
				    	<div class="col-sm-8">
				      		<form:textarea class="form-control " data-validation="trim|required" id="inputanswerEnglish" path="answerEnglish"  placeholder="Answer English"/>
				      		<form:errors path="answerEnglish" cssClass="help-block text-danger" element="span" />
				    	</div>
				  	</div>
				  	</spring:bind><spring:bind path="answerBangla">
       	        <div class="form-group ${(not empty status.errorMessage) ? ' has-error':''}">
				    	<label for="inputanswerBangla" class="col-sm-2 control-label">Answer Bangla</label>
				    	<div class="col-sm-8">
				      		<form:textarea class="form-control " data-validation="trim|required" id="inputanswerBangla" path="answerBangla"  placeholder="Answer Bangla"/>
				      		<form:errors path="answerBangla" cssClass="help-block text-danger" element="span" />
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
		            <a href="#Faq/index" role="button" class="btn btn-warning">Cancel</a> 
		        </div>
		    </div>
		</form:form>
	</div>
</div>