<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<div class="panel panel-success">
    <!-- Default panel contents -->
    <div class="panel-heading text-center text-primary">User<span class="pull-right"><a href="#User/create" role="button"><i class="fa fa-arrow-right"></i>Create New</a></span>&nbsp;&nbsp;&nbsp;<span class="pull-right"><a href="#User/edit/${id}" role="button"><i class="fa fa-arrow-right"></i> Edit</a></span>
    <span class="pull-left">
    	 <a href="#User/index" role="button"> <i class="fa fa-history" aria-hidden="true"></i>
Back to List</a>
    </span>
    </div>
    <div class="panel-body table-responsive">
        <table class="table table-condensed">
					<tr class="">
					<th style="white-space: nowrap;">Username</th>
					<th style="white-space:nowrap;width:1px;"> : </th>
					<td>${user.username}</td>
					</tr>
					<tr class="active">
					<th style="white-space: nowrap;">Full Name</th>
					<th style="white-space:nowrap;width:1px;"> : </th>
					<td>${user.fullName}</td>
					</tr>

					<tr class="active">
					<th style="white-space: nowrap;">Designation</th>
					<th style="white-space:nowrap;width:1px;"> : </th>
					<td>${user.designation}</td>
					</tr>
				

					<tr class="active">
					<th style="white-space: nowrap;">Phone</th>
					<th style="white-space:nowrap;width:1px;"> : </th>
					<td>${user.phone}</td>
					</tr>
				
					<tr class="">
					<th style="white-space: nowrap;">Email</th>
					<th style="white-space:nowrap;width:1px;"> : </th>
					<td>${user.email}</td>
					</tr>
				

				
					<tr class="active">
					<th style="white-space: nowrap;">Created By</th>
					<th style="white-space:nowrap;width:1px;"> : </th>
					<td>${user.createdBy}</td>
					</tr>
				
    				<tr class="">
    				<th style="white-space: nowrap;width:1px;">Created At</th>
    				<th style="white-space:nowrap;width:1px;"> : </th>
    				<td><fmt:formatDate value="${user.createdAt}" pattern="dd/MM/yyyy hh:mm a" /></td>
    				</tr>
				
					<tr class="active">
					<th style="white-space: nowrap;">Updated By</th>
					<th style="white-space:nowrap;width:1px;"> : </th>
					<td>${user.updatedBy}</td>
					</tr>
				
    				<tr class="">
    				<th style="white-space: nowrap;width:1px;">Updated At</th>
    				<th style="white-space:nowrap;width:1px;"> : </th>
    				<td><fmt:formatDate value="${user.updatedAt}" pattern="dd/MM/yyyy hh:mm a" /></td>
    				</tr>
				
        </table>
	</div>
</div>