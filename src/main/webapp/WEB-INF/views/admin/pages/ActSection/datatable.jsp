<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<c:set var="rand"><%= java.lang.Math.random() * java.lang.Math.random() %></c:set>
<div class="panel panel-success">
	<%@ include file="/WEB-INF/views/admin/includes/view_security.jsp" %>
    <!-- Default panel contents -->
 	<div class="panel-heading text-center text-primary">
 		Act Section
 		<span class="pull-right">
 			<a href="#ActSection/create" role="button">
 				<i class="fa fa-arrow-right"></i>Create New
 			</a>
 		</span>
 	</div>
    <div class="panel-body">
  		<div class="table-responsive">
			<table id="datatable_for_actSection" class="datatable table table-striped table-bordered dt-responsive nowrap compact stripe"
				data-title="actSection List"
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
				data-ajax-url="<c:url value="/admin/actSection/data?${_csrf.parameterName}=${_csrf.token}"/>"
				data-dom="<'row toolbar'><'row'<'col-sm-3'l><'col-sm-5 text-center'B><'col-sm-4'f>><'row'<'col-sm-12'tr>><'row'<'col-sm-5'i><'col-sm-7'p>>"
				buttons="copy,csv,excel,pdf,print,reload,resetColReorder,colvis"
				data-fixedHeader="true"
				data-colReorder="true"
				width="100%"
				data-columns-sequence="index,id,volume.volumeName,actId.shortTitle,partId.partNo,chapterId.chapterNo,sectionNo,sectionName,attachment,status,action"
			>
				<thead>
					<th name="index" value="" type="index" data-priority="1" data-visible="false" data-orderable="false" data-searchable="false">SL No</th>
					<th name="id" value="id" data-priority="3">Act Section ID</th>
						<th name="volume.volumeName" value="volume.volumeName" data-priority="4">Volume</th>
					<th name="actId.shortTitle" value="actId.shortTitle" data-priority="5">Act</th>
					<th name="partId.partNo" value="partId.partNo" data-priority="6">Part</th>
					<th name="chapterId.chapterNo" value="chapterId.chapterNo" data-priority="7">Chapter</th>
					<th name="sectionNo" value="sectionNo" data-priority="8">Section No</th>
					<th name="sectionName" value="sectionName" data-priority="9">Section Name</th>
					<th name="attachment" value="attachment" data-priority="10">Attachment</th>
					<th name="status" value="status" data-priority="11">Status</th>
				
					<th name="action" value="id" data-edit="true" data-delete="true" data-view="true" data-delete-url="admin/actSection/delete/:id" data-edit-url="admin#actSection/edit/:id" data-view-url="admin#actSection/details/:id" data-view-button-class="btn btn-primary btn-sm" data-edit-button-class="btn btn-info btn-sm" data-delete-button-class="btn btn-danger btn-sm" data-view-icon-class="fa fa-info-circle" data-edit-icon-class="" data-delete-icon-class="fa fa-trash" data-notexport="true" data-orderable="false" data-searchable="false" data-priority="2">Action</th>
				</thead>
			</table>
		</div>
		
	</div>
</div>