<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<div class="panel panel-success">
    <!-- Default panel contents -->
    <div class="panel-heading text-center text-primary">News<span class="pull-right"><a href="#News/create" role="button"><i class="fa fa-arrow-right"></i>Create New</a></span>&nbsp;&nbsp;&nbsp;<span class="pull-right"><a href="#News/edit/${id}" role="button"><i class="fa fa-arrow-right"></i> Edit</a></span>
    <span class="pull-left">
    	 <a href="#News/index" role="button"> <i class="fa fa-history" aria-hidden="true"></i>
Back to List</a>
    </span>
    </div>
    <div class="panel-body table-responsive">
        <table class="table table-condensed">
            
					<tr class="">
					<th style="white-space: nowrap;">Title English</th>
					<th style="white-space:nowrap;width:1px;"> : </th>
					<td>${news.titleEnglish}</td>
					</tr>
				
					<tr class="active">
					<th style="white-space: nowrap;">Title Bangla</th>
					<th style="white-space:nowrap;width:1px;"> : </th>
					<td>${news.titleBangla}</td>
					</tr>
				
					<tr class="">
					<th style="white-space: nowrap;">Description English</th>
					<th style="white-space:nowrap;width:1px;"> : </th>
					<td>${news.descriptionEnglish}</td>
					</tr>
				
					<tr class="active">
					<th style="white-space: nowrap;">Description Bangla</th>
					<th style="white-space:nowrap;width:1px;"> : </th>
					<td>${news.descriptionBangla}</td>
					</tr>
				
					<tr class="">
					<th style="white-space: nowrap;">Status</th>
					<th style="white-space:nowrap;width:1px;"> : </th>
					<td>${news.status}</td>
					</tr>
				
					<tr class="active">
					<th style="white-space: nowrap;">Created By</th>
					<th style="white-space:nowrap;width:1px;"> : </th>
					<td>${news.createdBy}</td>
					</tr>
				
    				<tr class="">
    				<th style="white-space: nowrap;width:1px;">Created At</th>
    				<th style="white-space:nowrap;width:1px;"> : </th>
    				<td><fmt:formatDate value="${news.createdAt}" pattern="dd/MM/yyyy hh:mm a" /></td>
    				</tr>
				
					<tr class="active">
					<th style="white-space: nowrap;">Updated By</th>
					<th style="white-space:nowrap;width:1px;"> : </th>
					<td>${news.updatedBy}</td>
					</tr>
				
    				<tr class="">
    				<th style="white-space: nowrap;width:1px;">Updated At</th>
    				<th style="white-space:nowrap;width:1px;"> : </th>
    				<td><fmt:formatDate value="${news.updatedAt}" pattern="dd/MM/yyyy hh:mm a" /></td>
    				</tr>
				
        </table>
	</div>
</div>