<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<div class="panel panel-success">
    <!-- Default panel contents -->
    <div class="panel-heading text-center text-primary">Section Head Footnote<span class="pull-right"><a href="#SectionHeadFootnote/edit/${id}" role="button"><i class="fa fa-arrow-right"></i> Edit</a></span>
    <span class="pull-left">
    	 <a href="#SectionHeadFootnote/index" role="button"> <i class="fa fa-history" aria-hidden="true"></i>
Back to List</a>
    </span>
    </div>
    <div class="panel-body table-responsive">
        <table class="table table-condensed">
            
					<tr class="">
					<th style="white-space: nowrap;">Section</th>
					<th style="white-space:nowrap;width:1px;"> : </th>
					<td>${sectionHeadFootnote.sectionId.sectionName}</td>
					</tr>
				
					<tr class="active">
					<th style="white-space: nowrap;">Footnote</th>
					<th style="white-space:nowrap;width:1px;"> : </th>
					<td>${sectionHeadFootnote.footnote}</td>
					</tr>
				
					<tr class="">
					<th style="white-space: nowrap;">Footnote Number</th>
					<th style="white-space:nowrap;width:1px;"> : </th>
					<td>${sectionHeadFootnote.footnoteNumber}</td>
					</tr>
				
					<tr class="active">
					<th style="white-space: nowrap;">Footnote Sign</th>
					<th style="white-space:nowrap;width:1px;"> : </th>
					<td>${sectionHeadFootnote.footnoteSign}</td>
					</tr>
				
					<tr class="">
					<th style="white-space: nowrap;">Status</th>
					<th style="white-space:nowrap;width:1px;"> : </th>
					<td>${sectionHeadFootnote.status}</td>
					</tr>
				
					<tr class="active">
					<th style="white-space: nowrap;">Created By</th>
					<th style="white-space:nowrap;width:1px;"> : </th>
					<td>${sectionHeadFootnote.createdBy}</td>
					</tr>
				
    				<tr class="">
    				<th style="white-space: nowrap;width:1px;">Created At</th>
    				<th style="white-space:nowrap;width:1px;"> : </th>
    				<td><fmt:formatDate value="${sectionHeadFootnote.createdAt}" pattern="dd/MM/yyyy hh:mm a" /></td>
    				</tr>
				
					<tr class="active">
					<th style="white-space: nowrap;">Updated By</th>
					<th style="white-space:nowrap;width:1px;"> : </th>
					<td>${sectionHeadFootnote.updatedBy}</td>
					</tr>
				
    				<tr class="">
    				<th style="white-space: nowrap;width:1px;">Updated At</th>
    				<th style="white-space:nowrap;width:1px;"> : </th>
    				<td><fmt:formatDate value="${sectionHeadFootnote.updatedAt}" pattern="dd/MM/yyyy hh:mm a" /></td>
    				</tr>
				
        </table>
	</div>
</div>