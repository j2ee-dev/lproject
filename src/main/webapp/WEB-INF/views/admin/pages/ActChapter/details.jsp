<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<div class="panel panel-success">
    <!-- Default panel contents -->
    <div class="panel-heading text-center text-primary">Act Chapter<div class="pull-right"><a href="#ActChapter/create" role="button"><i class="fa fa-arrow-right"></i>Create New</a></div>&nbsp;&nbsp;&nbsp;<div class="pull-right"><a href="#ActChapter/edit/${actChapter.id}" role="button"><i class="fa fa-arrow-right"></i> Edit</a></div>
    <span class="pull-left">
    	 <a href="#ActChapter/index" role="button"> <i class="fa fa-history" aria-hidden="true"></i>
Back to List</a>
    </span>
    </div>
    <div class="panel-body table-responsive">
        <table class="table table-condensed">
            
					<tr class="">
					<th style="white-space: nowrap;">Volume</th>
					<th style="white-space:nowrap;width:1px;"> : </th>
					<td>${actChapter.volume.volumeName}</td>
					</tr>
				
					<tr class="active">
					<th style="white-space: nowrap;">Act</th>
					<th style="white-space:nowrap;width:1px;"> : </th>
					<td>${actChapter.actId.shortTitle}</td>
					</tr>
				
					<tr class="">
					<th style="white-space: nowrap;">Part</th>
					<th style="white-space:nowrap;width:1px;"> : </th>
					<td>${actChapter.partId.partNo}</td>
					</tr>
				
					<tr class="active">
					<th style="white-space: nowrap;">Chapter No</th>
					<th style="white-space:nowrap;width:1px;"> : </th>
					<td>${actChapter.chapterNo}</td>
					</tr>
				<jsp:include page="/WEB-INF/views/admin/pages/ActChapter/includes/chapterNoFootnoteDetails.jsp" />
					<tr class="">
					<th style="white-space: nowrap;">Chapter Name</th>
					<th style="white-space:nowrap;width:1px;"> : </th>
					<td>${actChapter.chapterName}</td>
					</tr>
				<jsp:include page="/WEB-INF/views/admin/pages/ActChapter/includes/chapterNameFootnoteDetails.jsp" />
					<tr class="active">
					<th style="white-space: nowrap;">Chapter Order</th>
					<th style="white-space:nowrap;width:1px;"> : </th>
					<td>${actChapter.chapterOrder}</td>
					</tr>
				
					<tr class="">
					<th style="white-space: nowrap;">Attachment</th>
					<th style="white-space:nowrap;width:1px;"> : </th>
					<td>${actChapter.attachment}</td>
					</tr>
				
					<tr class="active">
					<th style="white-space: nowrap;">Status</th>
					<th style="white-space:nowrap;width:1px;"> : </th>
					<td>${actChapter.status}</td>
					</tr>
				
					<tr class="">
					<th style="white-space: nowrap;">Created By</th>
					<th style="white-space:nowrap;width:1px;"> : </th>
					<td>${actChapter.createdBy}</td>
					</tr>
				
    				<tr class="active">
    				<th style="white-space: nowrap;width:1px;">Created At</th>
    				<th style="white-space:nowrap;width:1px;"> : </th>
    				<td><fmt:formatDate value="${actChapter.createdAt}" pattern="dd/MM/yyyy hh:mm a" /></td>
    				</tr>
				
					<tr class="">
					<th style="white-space: nowrap;">Updated By</th>
					<th style="white-space:nowrap;width:1px;"> : </th>
					<td>${actChapter.updatedBy}</td>
					</tr>
				
    				<tr class="active">
    				<th style="white-space: nowrap;width:1px;">Updated At</th>
    				<th style="white-space:nowrap;width:1px;"> : </th>
    				<td><fmt:formatDate value="${actChapter.updatedAt}" pattern="dd/MM/yyyy hh:mm a" /></td>
    				</tr>
				
        </table>
	</div>
</div>