<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<div class="panel panel-success">
    <!-- Default panel contents -->
    <div class="panel-heading text-center text-primary">Related Link<span class="pull-right"><a href="#RelatedLink/create" role="button"><i class="fa fa-arrow-right"></i>Create New</a></span>&nbsp;&nbsp;&nbsp;<span class="pull-right"><a href="#RelatedLink/edit/${id}" role="button"><i class="fa fa-arrow-right"></i> Edit</a></span>
    <span class="pull-left">
    	 <a href="#RelatedLink/index" role="button"> <i class="fa fa-history" aria-hidden="true"></i>
Back to List</a>
    </span>
    </div>
    <div class="panel-body table-responsive">
        <table class="table table-condensed">
            
					<tr class="">
					<th style="white-space: nowrap;">Title English</th>
					<th style="white-space:nowrap;width:1px;"> : </th>
					<td>${relatedLink.titleEnglish}</td>
					</tr>
				
					<tr class="active">
					<th style="white-space: nowrap;">Title Bangla</th>
					<th style="white-space:nowrap;width:1px;"> : </th>
					<td>${relatedLink.titleBangla}</td>
					</tr>
				
					<tr class="">
					<th style="white-space: nowrap;">Link</th>
					<th style="white-space:nowrap;width:1px;"> : </th>
					<td>${relatedLink.link}</td>
					</tr>
				
					<tr class="active">
					<th style="white-space: nowrap;">Status</th>
					<th style="white-space:nowrap;width:1px;"> : </th>
					<td>${relatedLink.status}</td>
					</tr>
				
					<tr class="">
					<th style="white-space: nowrap;">Created By</th>
					<th style="white-space:nowrap;width:1px;"> : </th>
					<td>${relatedLink.createdBy}</td>
					</tr>
				
    				<tr class="active">
    				<th style="white-space: nowrap;width:1px;">Created At</th>
    				<th style="white-space:nowrap;width:1px;"> : </th>
    				<td><fmt:formatDate value="${relatedLink.createdAt}" pattern="dd/MM/yyyy hh:mm a" /></td>
    				</tr>
				
					<tr class="">
					<th style="white-space: nowrap;">Updated By</th>
					<th style="white-space:nowrap;width:1px;"> : </th>
					<td>${relatedLink.updatedBy}</td>
					</tr>
				
    				<tr class="active">
    				<th style="white-space: nowrap;width:1px;">Updated At</th>
    				<th style="white-space:nowrap;width:1px;"> : </th>
    				<td><fmt:formatDate value="${relatedLink.updatedAt}" pattern="dd/MM/yyyy hh:mm a" /></td>
    				</tr>
				
        </table>
	</div>
</div>