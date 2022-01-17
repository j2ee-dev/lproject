<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<div class="panel panel-success">
    <!-- Default panel contents -->
    <div class="panel-heading text-center text-primary">Act Section<div class="pull-right"><a href="#ActSection/create" role="button"><i class="fa fa-arrow-right"></i>Create New</a></div>&nbsp;&nbsp;&nbsp;<div class="pull-right"><a href="#ActSection/edit/${actSection.id}" role="button"><i class="fa fa-arrow-right"></i> Edit</a></div>
    <span class="pull-left">
    	 <a href="#ActSection/index" role="button"> <i class="fa fa-history" aria-hidden="true"></i>
Back to List</a>
    </span>
    </div>
    <div class="panel-body table-responsive">
        <table class="table table-condensed">
            
					<tr class="">
					<th style="white-space: nowrap;">Volume</th>
					<th style="white-space:nowrap;width:1px;"> : </th>
					<td>${actSection.volume.volumeName}</td>
					</tr>
				
					<tr class="active">
					<th style="white-space: nowrap;">Act</th>
					<th style="white-space:nowrap;width:1px;"> : </th>
					<td>${actSection.actId.shortTitle}</td>
					</tr>
				
					<tr class="">
					<th style="white-space: nowrap;">Part</th>
					<th style="white-space:nowrap;width:1px;"> : </th>
					<td>${actSection.partId.partNo}</td>
					</tr>
				
					<tr class="active">
					<th style="white-space: nowrap;">Chapter</th>
					<th style="white-space:nowrap;width:1px;"> : </th>
					<td>${actSection.chapterId.chapterNo}</td>
					</tr>
				
					<tr class="">
					<th style="white-space: nowrap;">Section No</th>
					<th style="white-space:nowrap;width:1px;"> : </th>
					<td>${actSection.sectionNo}</td>
					</tr>
				
					<tr class="active">
					<th style="white-space: nowrap;">Section Name</th>
					<th style="white-space:nowrap;width:1px;"> : </th>
					<td>${actSection.sectionName}</td>
					</tr>
				<jsp:include page="/WEB-INF/views/admin/pages/ActSection/includes/sectionNameFootnoteDetails.jsp" />
					<tr class="">
					<th style="white-space: nowrap;">Section Head</th>
					<th style="white-space:nowrap;width:1px;"> : </th>
					<td>${actSection.sectionHead}</td>
					</tr>
				<jsp:include page="/WEB-INF/views/admin/pages/ActSection/includes/sectionHeadFootnoteDetails.jsp" />
					<tr class="active">
					<th style="white-space: nowrap;">Section Description</th>
					<th style="white-space:nowrap;width:1px;"> : </th>
					<td>${actSection.sectionDescription}</td>
					</tr>
				<jsp:include page="/WEB-INF/views/admin/pages/ActSection/includes/sectionDescriptionFootnoteDetails.jsp" />
					<tr class="">
					<th style="white-space: nowrap;">Section Table</th>
					<th style="white-space:nowrap;width:1px;"> : </th>
					<td>${actSection.sectionTable}</td>
					</tr>
				<jsp:include page="/WEB-INF/views/admin/pages/ActSection/includes/sectionTableFootnoteDetails.jsp" />
					<tr class="active">
					<th style="white-space: nowrap;">Update Note</th>
					<th style="white-space:nowrap;width:1px;"> : </th>
					<td>${actSection.updateNote}</td>
					</tr>
				
					<tr class="">
					<th style="white-space: nowrap;">Attachment</th>
					<th style="white-space:nowrap;width:1px;"> : </th>
					<td>${actSection.attachment}</td>
					</tr>
				
					<tr class="active">
					<th style="white-space: nowrap;">Status</th>
					<th style="white-space:nowrap;width:1px;"> : </th>
					<td>${actSection.status}</td>
					</tr>
				
					<tr class="">
					<th style="white-space: nowrap;">Created By</th>
					<th style="white-space:nowrap;width:1px;"> : </th>
					<td>${actSection.createdBy}</td>
					</tr>
				
    				<tr class="active">
    				<th style="white-space: nowrap;width:1px;">Created At</th>
    				<th style="white-space:nowrap;width:1px;"> : </th>
    				<td><fmt:formatDate value="${actSection.createdAt}" pattern="dd/MM/yyyy hh:mm a" /></td>
    				</tr>
				
					<tr class="">
					<th style="white-space: nowrap;">Updated By</th>
					<th style="white-space:nowrap;width:1px;"> : </th>
					<td>${actSection.updatedBy}</td>
					</tr>
				
    				<tr class="active">
    				<th style="white-space: nowrap;width:1px;">Updated At</th>
    				<th style="white-space:nowrap;width:1px;"> : </th>
    				<td><fmt:formatDate value="${actSection.updatedAt}" pattern="dd/MM/yyyy hh:mm a" /></td>
    				</tr>
				
        </table>
	</div>
</div>