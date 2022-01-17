<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<div class="panel panel-success">
    <!-- Default panel contents -->
    <div class="panel-heading text-center text-primary">Act Schedule<%--<span class="pull-right"><a href="#ActSchedule/edit/${id}" role="button"><i class="fa fa-arrow-right"></i> Edit</a></span>--%>
    <span class="pull-left">
    	 <a href="#ActSchedule/index" role="button"> <i class="fa fa-history" aria-hidden="true"></i>
Back to List</a>
    </span>
    </div>
    <div class="panel-body table-responsive">
        <table class="table table-condensed">
            
					<tr class="">
					<th style="white-space: nowrap;">Act</th>
					<th style="white-space:nowrap;width:1px;"> : </th>
					<td>${actSchedule.actId.shortTitle}</td>
					</tr>
				
					<tr class="active">
					<th style="white-space: nowrap;">Title</th>
					<th style="white-space:nowrap;width:1px;"> : </th>
					<td>${actSchedule.title}</td>
					</tr>
				
					<tr class="">
					<th style="white-space: nowrap;">Attachment</th>
					<th style="white-space:nowrap;width:1px;"> : </th>
					<td>${actSchedule.attachment}</td>
					</tr>
				
					<tr class="active">
					<th style="white-space: nowrap;">Status</th>
					<th style="white-space:nowrap;width:1px;"> : </th>
					<td>${actSchedule.status}</td>
					</tr>
				
					<tr class="">
					<th style="white-space: nowrap;">Created By</th>
					<th style="white-space:nowrap;width:1px;"> : </th>
					<td>${actSchedule.createdBy}</td>
					</tr>
				
    				<tr class="active">
    				<th style="white-space: nowrap;width:1px;">Created At</th>
    				<th style="white-space:nowrap;width:1px;"> : </th>
    				<td><fmt:formatDate value="${actSchedule.createdAt}" pattern="dd/MM/yyyy hh:mm a" /></td>
    				</tr>
				
					<tr class="">
					<th style="white-space: nowrap;">Updated By</th>
					<th style="white-space:nowrap;width:1px;"> : </th>
					<td>${actSchedule.updatedBy}</td>
					</tr>
				
    				<tr class="active">
    				<th style="white-space: nowrap;width:1px;">Updated At</th>
    				<th style="white-space:nowrap;width:1px;"> : </th>
    				<td><fmt:formatDate value="${actSchedule.updatedAt}" pattern="dd/MM/yyyy hh:mm a" /></td>
    				</tr>
				
        </table>
	</div>
</div>