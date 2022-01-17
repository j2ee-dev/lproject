<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<c:set var="rand"><%= java.lang.Math.random() * java.lang.Math.random() %></c:set>
<div class="panel panel-success">
    <!-- Default panel contents -->
    <div class="panel-heading text-center text-primary">Short Title Footnote<span class="pull-right"><a href="#ShortTitleFootnote/create/${rand}" role="button"><i class="fa fa-arrow-right"></i> Create New</a></span></div>
    <div class="panel-body">
    <c:choose> 
  <c:when test="${shortTitleFootnotes.hasContent()}">
  <div class="table-responsive">
<table id="datatable_for_shortTitleFootnote" class="datatable table table-striped table-bordered dt-responsive nowrap compact stripe"
			data-title="shortTitleFootnote List"
			data-deferRender="true"
			data-stateSave="false"
			data-responsive="{&quot;details&quot;:false}"
			data-page-length="10"
			data-lengthMenu="[ [10, 20, 50, 100,-1], [10, 20, 50, 100, &quot;All&quot;] ]"
			data-order="[[ 1, &quot;asc&quot; ]]"
			data-info="true"
			data-ordering="true"
			data-paging="true"
			data-scrollY="&quot;200px&quot;"
			data-searching="true"
			data-dom="<'row toolbar'><'row'<'col-sm-3'l><'col-sm-5 text-center'B><'col-sm-4'f>><'row'<'col-sm-12'tr>><'row'<'col-sm-5'i><'col-sm-7'p>>"
			buttons="copy,csv,excel,pdf,print,reload,resetColReorder,colvis"
			data-fixedHeader="true"
			data-colReorder="true"
			width="100%"
			>
				<thead>
					<th data-visible="false" data-priority="1" data-orderable="false" data-searchable="false">SL No</th>
					<th data-priority="3">Act</th>
				<th data-priority="4">Footnote Number</th>
				<th data-priority="5">Footnote Sign</th>
				<th data-priority="6">Status</th>
				
					<th data-edit="true" data-delete="true" data-view="true" data-delete-url="admin/shortTitleFootnote/delete/:id" data-edit-url="admin/shortTitleFootnote/edit/:id" data-view-url="admin/shortTitleFootnote/details/:id" data-view-button-class="btn btn-primary btn-sm" data-edit-button-class="btn btn-info btn-sm" data-delete-button-class="btn btn-danger btn-sm" data-view-icon-class="fa fa-info-circle" data-edit-icon-class="" data-delete-icon-class="fa fa-trash" data-notexport="true" data-orderable="false" data-searchable="false" data-priority="2">Action</th>
				</thead>
				<tbody>

                <c:forEach items="${shortTitleFootnotes.getContent()}" var="shortTitleFootnote" varStatus="loop">
                    <tr id="index_${loop.count+page*size}">
                    	<td>${loop.count+page*size}</td>
                        <td>${shortTitleFootnote.actId.shortTitle}</td>
				<td>${shortTitleFootnote.footnoteNumber}</td>
				<td>${shortTitleFootnote.footnoteSign}</td>
				<td>${shortTitleFootnote.status}</td>
				
                        <td>
                        	<a href="#ShortTitleFootnote/edit/${shortTitleFootnote.id}" class="data-edit-action btn btn-info btn-sm"><i class="fa fa-pencil"></i></a>
                        	<a href="${contextPath}/admin/ShortTitleFootnote/delete/${shortTitleFootnote.id}" class="data-delete-action btn btn-danger btn-sm"><i class="fa fa-trash-o"></i></a>
                        	<a href="#ShortTitleFootnote/details/${shortTitleFootnote.id}" class="data-details-action btn btn-primary btn-sm"><i class="fa fa-info-circle"></i></a>
                        </td>
                    </tr>
                </c:forEach>    
				</tbody>

			</table>
		</div>
		<div class="row">
		  <div class="col-sm-4">
		  	<div class="text-info text-center pagination">
			  	${page*size+1} to ${(page*size+size)>shortTitleFootnotes.getTotalElements()? shortTitleFootnotes.getTotalElements() : (page*size+size)} of ${shortTitleFootnotes.getTotalElements()}
		  	</div>
		  </div>
		  <div class="col-sm-4">
		  	<ul class="pagination">
			  <li Class="${shortTitleFootnotes.hasPrevious()? '':'disabled'}"><a href="#ShortTitleFootnote/index?page=${shortTitleFootnotes.previousPageable().getPageNumber()}">Previous</a></li>
			  <li Class="${shortTitleFootnotes.hasNext()? '':'disabled'}"><a href="#ShortTitleFootnote/index?page=${shortTitleFootnotes.nextPageable().getPageNumber()}">Next</a></li>
		  </ul>
		  </div>
		  
		</div>
	   </c:when>
	  <c:otherwise>
	    <h3 class="text-danger text-center">No data found!!</h3>
	  </c:otherwise>
	</c:choose>

	</div>
</div>