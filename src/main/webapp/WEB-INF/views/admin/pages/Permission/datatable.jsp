<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<c:set var="rand"><%= java.lang.Math.random() * java.lang.Math.random() %></c:set>
<div class="panel panel-success">
	<%@ include file="/WEB-INF/views/admin/includes/view_security.jsp" %>
    <!-- Default panel contents -->
 	<div class="panel-heading text-center text-primary">
 		Permission
 		<span class="pull-right">
 			<a href="#Permission/create" role="button">
 				<i class="fa fa-arrow-right"></i>Create New
 			</a>
 		</span>
 	</div>
    <div class="panel-body">
  		<div class="table-responsive">
			<table id="datatable_for_user" class="datatable table table-striped table-bordered dt-responsive nowrap compact stripe"
                   data-title="user List"
                   data-deferRender="true"
                   data-stateSave="false"
                   data-responsive="{&quot;details&quot;:false}"
                   data-page-length="10"
                   data-lengthMenu="[ [10, 20, 50, 100,10000], [10, 20, 50, 100, &quot;All&quot;] ]"
                   data-order="[[ 1, &quot;asc&quot; ]]"
                   data-info="true"
                   data-ordering="true"
                   data-paging="true"
                   data-scrollY="&quot;200px&quot;"
                   data-searching="true"
                   data-ajax-url="<c:url value="/admin/permission/data?${_csrf.parameterName}=${_csrf.token}"/>"
                   data-dom="<'row toolbar'><'row'<'col-sm-3'l><'col-sm-5 text-center'B><'col-sm-4'f>><'row'<'col-sm-12'tr>><'row'<'col-sm-5'i><'col-sm-7'p>>"
                   buttons="copy,csv,excel,pdf,print,reload,resetColReorder,colvis"
                   data-fixedHeader="true"
                   data-colReorder="true"
                   width="100%"
                   data-columns-sequence="index,username,views,created,action">
				<thead>
					<th name="index" value="" type="index" data-priority="1" data-visible="false" data-orderable="false" data-searchable="false">SL No</th>
						<th name="username" value="username" data-priority="3">Username</th>
					<th name="views" value="views" data-priority="4">Permitted Menues</th>
					<th name="created" value="created" data-priority="5">Created At</th>
                    <th name="action" value="id" data-edit="true" data-delete="true" data-view="true"
                        data-delete-url="admin/Permission/delete/:id" data-edit-url="admin#Permission/edit/:id"
                        data-view-url="admin#Permission/details/:id" data-view-button-class="btn btn-primary btn-sm"
                        data-edit-button-class="btn btn-info btn-sm" data-delete-button-class="btn btn-danger btn-sm"
                        data-view-icon-class="fa fa-info-circle" data-edit-icon-class=""
                        data-delete-icon-class="fa fa-trash" data-notexport="true" data-orderable="false"
                        data-searchable="false" data-priority="2">Action
                    </th>
				</thead>
			</table>
		</div>
		
	</div>
</div>