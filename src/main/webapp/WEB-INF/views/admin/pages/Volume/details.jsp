<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<div class="panel panel-success">
    <!-- Default panel contents -->
    <div class="panel-heading text-center text-primary">Volume<div class="pull-right"><a href="#Volume/create" role="button"><i class="fa fa-arrow-right"></i>Create New</a></div>&nbsp;&nbsp;&nbsp;<div class="pull-right"><a href="#Volume/edit/${id}" role="button"><i class="fa fa-arrow-right"></i> Edit</a></div>
    <span class="pull-left">
    	 <a href="#Volume/index" role="button"> <i class="fa fa-history" aria-hidden="true"></i>
Back to List</a>
    </span>
    </div>
    <div class="panel-body table-responsive">
        <table class="table table-condensed">
            
					<tr class="">
					<th style="white-space: nowrap;">Volume Number</th>
					<th style="white-space:nowrap;width:1px;"> : </th>
					<td>${volume.volumeNumber}</td>
					</tr>
				
					<tr class="active">
					<th style="white-space: nowrap;">Volume Name</th>
					<th style="white-space:nowrap;width:1px;"> : </th>
					<td>${volume.volumeName}</td>
					</tr>
				
					<tr class="">
					<th style="white-space: nowrap;">Volume Head</th>
					<th style="white-space:nowrap;width:1px;"> : </th>
					<td>${volume.volumeHead}</td>
					</tr>
				
					<tr class="active">
					<th style="white-space: nowrap;">Description</th>
					<th style="white-space:nowrap;width:1px;"> : </th>
					<td>${volume.description}</td>
					</tr>
				
					<tr class="">
					<th style="white-space: nowrap;">Volume Year</th>
					<th style="white-space:nowrap;width:1px;"> : </th>
					<td>${volume.volumeYear}</td>
					</tr>
				
					<tr class="active">
					<th style="white-space: nowrap;">Status</th>
					<th style="white-space:nowrap;width:1px;"> : </th>
					<td>${volume.status}</td>
					</tr>
				
					<tr class="">
					<th style="white-space: nowrap;">Is Bangla</th>
					<th style="white-space:nowrap;width:1px;"> : </th>
					<td>${volume.isBangla}</td>
					</tr>
				
					<tr class="active">
					<th style="white-space: nowrap;">Created By</th>
					<th style="white-space:nowrap;width:1px;"> : </th>
					<td>${volume.createdBy}</td>
					</tr>
				
    				<tr class="">
    				<th style="white-space: nowrap;width:1px;">Created At</th>
    				<th style="white-space:nowrap;width:1px;"> : </th>
    				<td><fmt:formatDate value="${volume.createdAt}" pattern="dd/MM/yyyy hh:mm a" /></td>
    				</tr>
				
					<tr class="active">
					<th style="white-space: nowrap;">Updated By</th>
					<th style="white-space:nowrap;width:1px;"> : </th>
					<td>${volume.updatedBy}</td>
					</tr>
				
    				<tr class="">
    				<th style="white-space: nowrap;width:1px;">Updated At</th>
    				<th style="white-space:nowrap;width:1px;"> : </th>
    				<td><fmt:formatDate value="${volume.updatedAt}" pattern="dd/MM/yyyy hh:mm a" /></td>
    				</tr>
				
        </table>
	</div>
</div>