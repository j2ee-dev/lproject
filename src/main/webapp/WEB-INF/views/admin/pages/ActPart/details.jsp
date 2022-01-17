<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<div class="panel panel-success">
    <!-- Default panel contents -->
    <div class="panel-heading text-center text-primary">Act Part<div class="pull-right"><a href="#ActPart/create" role="button"><i class="fa fa-arrow-right"></i>Create New</a></div>&nbsp;&nbsp;&nbsp;<div class="pull-right"><a href="#ActPart/edit/${actPart.id}" role="button"><i class="fa fa-arrow-right"></i> Edit</a></div>
    <span class="pull-left">
    	 <a href="#ActPart/index" role="button"> <i class="fa fa-history" aria-hidden="true"></i>
Back to List</a>
    </span>
    </div>
    <div class="panel-body table-responsive">
        <table class="table table-condensed">
            
					<tr class="">
					<th style="white-space: nowrap;">Volume</th>
					<th style="white-space:nowrap;width:1px;"> : </th>
					<td>${actPart.volume.volumeName}</td>
					</tr>
				
					<tr class="active">
					<th style="white-space: nowrap;">Act</th>
					<th style="white-space:nowrap;width:1px;"> : </th>
					<td>${actPart.actId.shortTitle}</td>
					</tr>
				
					<tr class="">
					<th style="white-space: nowrap;">Part No</th>
					<th style="white-space:nowrap;width:1px;"> : </th>
					<td>${actPart.partNo}</td>
					</tr>
				<jsp:include page="/WEB-INF/views/admin/pages/ActPart/includes/partNoFootnoteDetails.jsp" />
					<tr class="active">
					<th style="white-space: nowrap;">Part Name</th>
					<th style="white-space:nowrap;width:1px;"> : </th>
					<td>${actPart.partName}</td>
					</tr>
				<jsp:include page="/WEB-INF/views/admin/pages/ActPart/includes/partNameFootnoteDetails.jsp" />
					<tr class="">
					<th style="white-space: nowrap;">Part Order</th>
					<th style="white-space:nowrap;width:1px;"> : </th>
					<td>${actPart.partOrder}</td>
					</tr>
				
					<tr class="active">
					<th style="white-space: nowrap;">Attachment</th>
					<th style="white-space:nowrap;width:1px;"> : </th>
					<td>${actPart.attachment}</td>
					</tr>
				
					<tr class="">
					<th style="white-space: nowrap;">Status</th>
					<th style="white-space:nowrap;width:1px;"> : </th>
					<td>${actPart.status}</td>
					</tr>
				
					<tr class="active">
					<th style="white-space: nowrap;">Created By</th>
					<th style="white-space:nowrap;width:1px;"> : </th>
					<td>${actPart.createdBy}</td>
					</tr>
				
    				<tr class="">
    				<th style="white-space: nowrap;width:1px;">Created At</th>
    				<th style="white-space:nowrap;width:1px;"> : </th>
    				<td><fmt:formatDate value="${actPart.createdAt}" pattern="dd/MM/yyyy hh:mm a" /></td>
    				</tr>
				
					<tr class="active">
					<th style="white-space: nowrap;">Updated By</th>
					<th style="white-space:nowrap;width:1px;"> : </th>
					<td>${actPart.updatedBy}</td>
					</tr>
				
    				<tr class="">
    				<th style="white-space: nowrap;width:1px;">Updated At</th>
    				<th style="white-space:nowrap;width:1px;"> : </th>
    				<td><fmt:formatDate value="${actPart.updatedAt}" pattern="dd/MM/yyyy hh:mm a" /></td>
    				</tr>
				
        </table>
	</div>
</div>