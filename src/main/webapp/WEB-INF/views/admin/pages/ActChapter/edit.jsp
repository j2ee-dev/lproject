<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<%@ include file="/WEB-INF/views/admin/includes/edit_security.jsp" %>
<div class="panel panel-success">
    <!-- Default panel contents -->
    <div class="panel-heading  text-center text-primary">Act Chapter<span class="pull-right"><a href="#ActChapter/index" role="button"> <i class="fa fa-history" aria-hidden="true"></i>
Back to List</a></span></div>
    <div class="panel-body">
		<form:form method="post" role="form" modelAttribute="actChapter" action="${contextPath}/admin/actChapter${action}?${_csrf.parameterName}=${_csrf.token}" class="form-horizontal" data-validation="true" data-ajax-submit="true" novalidate="novalidate">
		    <spring:bind path="volume.id">
       	        	<div class="form-group ${(not empty status.errorMessage) ? ' has-error':''}">
				    	<label for="inputvolume.id" class="col-sm-2 control-label">Volume</label>
				    	<div class="col-sm-9">
				      		<form:select class="form-control" data-change-pull="actId.id|volume.id|${contextPath}/admin/actChapter/act-list" data-selected="${actChapter.volume.id}" data-itemLabel="volumeName" data-itemValue="id" data-validation="trim|is_exist[Volume.id]|integer" path="volume.id" id="inputvolume.id">
    							<form:option value="0"> --SELECT--</form:option>
    							<form:options items="${volumeList}" itemLabel="volumeName" itemValue="id"></form:options>
  							</form:select>
  							<form:errors path="volume.id" cssClass="help-block text-danger" element="span" />
				    	</div>

				  	</div>
				  	</spring:bind><spring:bind path="actId.id">
       	        	<div class="form-group ${(not empty status.errorMessage) ? ' has-error':''}">
				    	<label for="inputactId.id" class="col-sm-2 control-label">Act</label>
				    	<div class="col-sm-9">
				      		<form:select class="form-control" data-change-pull="partId.id|actId.id|${contextPath}/admin/actChapter/actPart-list" data-selected="${actChapter.actId.id}" data-itemLabel="shortTitle" data-itemValue="id" data-validation="trim|is_exist[Act.id]|integer" path="actId.id" id="inputactId.id">
    							<form:option value="0"> --SELECT--</form:option>
    							<form:options items="${actList}" itemLabel="shortTitle" itemValue="id"></form:options>
  							</form:select>
  							<form:errors path="actId.id" cssClass="help-block text-danger" element="span" />
				    	</div>
				  	</div>
				  	</spring:bind><spring:bind path="partId.id">
       	        	<div class="form-group ${(not empty status.errorMessage) ? ' has-error':''}">
				    	<label for="inputpartId.id" class="col-sm-2 control-label">Part</label>
				    	<div class="col-sm-9">
				      		<form:select class="form-control"  data-selected="${actChapter.partId.id}" data-itemLabel="partNo" data-itemValue="id" data-validation="trim|is_exist[ActPart.id]|integer" path="partId.id" id="inputpartId.id">
    							<form:option value="0"> --SELECT--</form:option>
    							<form:options items="${actPartList}" itemLabel="partNo" itemValue="id"></form:options>
  							</form:select>
  							<form:errors path="partId.id" cssClass="help-block text-danger" element="span" />
				    	</div>
				  	</div>
				  	</spring:bind><spring:bind path="chapterNo">
       	        <div class="form-group ${(not empty status.errorMessage) ? ' has-error':''}">
				    	<label for="inputchapterNo" class="col-sm-2 control-label">Chapter No</label>
				    	<div class="col-sm-9">
				      		<form:input type="text" class="form-control" data-validation="trim|required|min_length[1]|max_length[255]" id="inputchapterNo" path="chapterNo"  placeholder="Chapter No"/>
				      		<form:errors path="chapterNo" cssClass="help-block text-danger" element="span" />
				    	</div>
				  	</div>
				  	</spring:bind><jsp:include page="/WEB-INF/views/admin/pages/ActChapter/includes/chapterNoFootnote.jsp" /><spring:bind path="chapterName">
       	        <div class="form-group ${(not empty status.errorMessage) ? ' has-error':''}">
				    	<label for="inputchapterName" class="col-sm-2 control-label">Chapter Name</label>
				    	<div class="col-sm-9">
				      		<form:input type="text" class="form-control" data-validation="trim|required|min_length[1]|max_length[255]" id="inputchapterName" path="chapterName"  placeholder="Chapter Name"/>
				      		<form:errors path="chapterName" cssClass="help-block text-danger" element="span" />
				    	</div>
				  	</div>
				  	</spring:bind><jsp:include page="/WEB-INF/views/admin/pages/ActChapter/includes/chapterNameFootnote.jsp" />
       	        <spring:bind path="chapterOrder">
       	        <div class="form-group ${(not empty status.errorMessage) ? ' has-error':''}">
				    	<label for="inputchapterOrder" class="col-sm-2 control-label">Chapter Order</label>
				    	<div class="col-sm-9">
				      		<form:input type="number" class="form-control" data-validation="trim|required|integer" id="inputchapterOrder" path="chapterOrder"  placeholder="Chapter Order"/>
				      		<form:errors path="chapterOrder" cssClass="help-block text-danger" element="span" />
				    	</div>
				  	</div>
				 </spring:bind>
				<spring:bind path="attachment">
       	        	<div class="form-group ${(not empty status.errorMessage) ? ' has-error':''}">
				    	<label for="inputattachment" class="col-sm-2 control-label">Attachment</label>
				    	<div class="col-sm-9">
				    	    <label class="btn btn-default btn-file">
							    Browse <input type="file" class="form-control" data-validation="trim|file_allowed_type[jpeg,jpg,png,doc,docx,pdf,ppt,pptx,csv,xls,xls,zip,mp3,mp4,flv]|min_length[1]|max_length[255]" id="inputattachment" name="attachmentFile"  placeholder="Attachment" multiple/>
				      		
							</label>
							<form:errors path="attachment" cssClass="help-block text-danger" element="span" />
				      		
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
		            <a href="#ActChapter/index" role="button" class="btn btn-warning">Cancel</a> 
		        </div>
		    </div>
		</form:form>
	</div>
</div>