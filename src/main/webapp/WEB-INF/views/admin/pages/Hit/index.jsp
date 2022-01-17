<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<c:set var="rand"><%= java.lang.Math.random() * java.lang.Math.random() %></c:set>
<div class="panel panel-success">
	<%@ include file="/WEB-INF/views/admin/includes/view_security.jsp" %>
	<!-- Default panel contents -->
	<div class="panel-heading text-center text-primary">
		Hit
	</div>
	<div class="panel-body">
		<div class="table-responsive">
			<table id="datatable_for_hit" class="datatable table table-striped table-bordered dt-responsive nowrap compact stripe"
				   data-title="hit List"
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
				   data-ajax-url="<c:url value="/admin/hit/data?${_csrf.parameterName}=${_csrf.token}"/>"
				   data-dom="<'row toolbar'><'row'<'col-sm-3'l><'col-sm-5 text-center'B><'col-sm-4'f>><'row'<'col-sm-12'tr>><'row'<'col-sm-5'i><'col-sm-7'p>>"
				   buttons="copy,csv,excel,pdf,print,reload,resetColReorder,colvis"
				   data-fixedHeader="true"
				   data-colReorder="true"
				   width="100%"
				   data-columns-sequence="index,hitDate,hitNumbers,refreshHits,action"
			>
				<thead>
				<th name="index" value="" type="index" data-priority="1" data-visible="false" data-orderable="false" data-searchable="false">SL No</th>
				<th name="hitDate" value="hitDate" data-priority="3">HitDate</th>
				<th name="hitNumbers" value="hitNumbers" data-priority="4">HitNumber</th>
				<th name="refreshHits" value="refreshHits" data-priority="5">RefreshHit</th>
				<th name="action" value="id" data-delete="true" data-view="true" data-delete-url="admin/hit/delete/:id" data-edit-url="admin#hit/edit/:id" data-view-url="admin#hit/details/:id" data-view-button-class="btn btn-primary btn-sm" data-edit-button-class="btn btn-info btn-sm" data-delete-button-class="btn btn-danger btn-sm" data-view-icon-class="fa fa-info-circle" data-edit-icon-class="" data-delete-icon-class="fa fa-trash" data-notexport="true" data-orderable="false" data-searchable="false" data-priority="2">Action</th>
				</thead>
			</table>
		</div>

	</div>
</div>