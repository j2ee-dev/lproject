<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<style>
	@media print {
		.no-print {
			display: none;
		}
		@page {
			/*size: 297mm 210mm;*/ /* landscape */
			/* you can also specify margins here: */
			size: auto;
			margin: 5mm;
			margin-bottom: 40px;
			counter-increment: page;
		}
	}

</style>

<div class="panel panel-success no-print">
	<!-- Default panel contents -->
	<div class="panel-heading  text-center text-primary">Hit Search</div>
	<div class="panel-body">

		<form:form method="post" role="form" modelAttribute="hit"
				   action="${contextPath}/admin/hit${action}?${_csrf.parameterName}=${_csrf.token}"
				   class="form-horizontal" data-validation="true" data-ajax-submit="true" novalidate="novalidate">
			<spring:bind path="fromDate">
				<div class="form-group ${(not empty status.errorMessage) ? ' has-error':''}">
					<label for="inputfromDate" class="col-sm-4 control-label">From Date</label>
					<div class="col-sm-3 controls" id="inputinputfromDate">
						<div class="input-group date" data-provide="datepicker">
							<form:input type="text" class="form-control" data-validation="trim|valid_date[dd/mm/yyyyy]" id="inputfromDate" path="fromDate"  placeholder="From Date" data-date-format="dd/mm/yyyy"/>
							<span class="input-group-addon">
				                    <span class="glyphicon glyphicon-calendar">
				                    </span>
				                </span>
						</div>
						<form:errors path="fromDate" cssClass="help-block text-danger" element="span" />
					</div>
				</div>
			</spring:bind>

			<spring:bind path="toDate">
				<div class="form-group ${(not empty status.errorMessage) ? ' has-error':''}">
					<label for="inputtoDate" class="col-sm-4 control-label">To Date</label>
					<div class="col-sm-3 controls" id="inputtoDate">
						<div class="input-group date" data-provide="datepicker">
							<form:input type="text" class="form-control" data-validation="trim|valid_date[dd/mm/yyyyy]" id="inputtoDate" path="toDate"  placeholder="To Date" data-date-format="dd/mm/yyyy"/>
							<span class="input-group-addon">
				                    <span class="glyphicon glyphicon-calendar">
				                    </span>
				                </span>
						</div>
						<form:errors path="toDate" cssClass="help-block text-danger" element="span" />
					</div>
				</div>
			</spring:bind>


			<div class="form-group">
				<div class="col-sm-offset-4 col-sm-5">
					<button type="submit" class="btn btn-primary"> &nbsp; Search &nbsp; </button>

				</div>
			</div>
		</form:form>



	</div>
</div>

<%--<div class="panel panel-success">
	<!-- Default panel contents -->
	<div class="panel-heading text-center text-primary">
		Hit Report
	<div class="no-print pull-right"  onclick="window.print();return false;" style="width: 72px;height: 80px;margin-right: 30px;cursor: pointer;" ><img style="widht:80px; height:70px;"   src="/resources/website/assets/img/printer.jpg" &lt;%&ndash;style="margin: 15px;"&ndash;%&gt;/>&lt;%&ndash;</button>&ndash;%&gt;

	</div>
		</div>
&lt;%&ndash;	<div class="panel-heading text-center text-primary">
		Hit Report
		<c:if test="${hitReport.hasContent()}">
			<div class="pull-right">
				<a href="admin/Hit/pdf/${fromDate1}/${toDate1}">
				<i class="fa fa-file-pdf-o" aria-hidden="true">
				</i>
				</a>
			</div>
		</c:if>

	</div>&ndash;%&gt;
	<div class="panel-body">
		<c:choose>
			<c:when test="${hitReport.hasContent()}">
				<div class="table-responsive">
					<table id="datatable_for_hitReport" class="datatable table table-striped table-bordered dt-responsive nowrap compact stripe"
						   data-title="Hit Report"
						   data-deferRender="true"
						   data-stateSave="false"
						   data-responsive="{&quot;details&quot;:false}"
						   data-page-length="5"
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
						   data-columns-sequence="index,hitDate,hitNumbers,refreshHits"
					>
						<thead>
						<th data-visible="false" data-priority="1" data-orderable="false" data-searchable="false">SL No</th>
						<th data-priority="3">Hit Date</th>
						<th data-priority="4">Hit Numbers</th>
						<th data-priority="5">Refresh Hit</th>

						&lt;%&ndash;<th data-edit="true" data-delete="true" data-view="true" data-delete-url="admin/Hit/delete/:id" data-edit-url="admin/Hit/edit/:id" data-view-url="admin/Hit/details/:id" data-view-button-class="btn btn-primary btn-sm" data-edit-button-class="btn btn-info btn-sm" data-delete-button-class="btn btn-danger btn-sm" data-view-icon-class="fa fa-info-circle" data-edit-icon-class="" data-delete-icon-class="fa fa-trash" data-notexport="true" data-orderable="false" data-searchable="false" data-priority="2">Action</th>&ndash;%&gt;
						</thead>
						<tbody>

						<c:forEach items="${hitReport.getContent()}" var="hitReport" varStatus="loop">
							<tr id="index_${loop.count+page*size}">
								<td>${loop.count+page*size}</td>
								<td> <fmt:formatDate value="${hitReport.hitDate}" pattern="dd/MM/yyyy" /></td>
								<td>${hitReport.hitNumbers}</td>
								<td>${hitReport.refreshHits}</td>
								&lt;%&ndash;<td>
									<a href="#Hit/edit/${hitReport.id}" class="data-edit-action btn btn-info btn-sm"><i class="fa fa-pencil"></i></a>
									<a href="${contextPath}/admin/Hit/delete/${hitReport.id}" class="data-delete-action btn btn-danger btn-sm"><i class="fa fa-trash-o"></i></a>
									<a href="#Hit/details/${hitReport.id}" class="data-details-action btn btn-primary btn-sm"><i class="fa fa-info-circle"></i></a>
								</td>&ndash;%&gt;
							</tr>
						</c:forEach>
						</tbody>

					</table>
				</div>
				<div class="row">
					<div class="col-sm-4">
						<div class="text-info text-center pagination">
								${page*size+1} to ${(page*size+size)>hitReport.getTotalElements()? hitReport.getTotalElements() : (page*size+size)} of ${hitReport.getTotalElements()}
						</div>
					</div>
					<div class="col-sm-4">
						<ul class="pagination">
							<li Class="${hitReport.hasPrevious()? '':'disabled'}"><a href="#Hit/hit?page=${hitReport.previousPageable().getPageNumber()}">Previous</a></li>
							<li Class="${hitReport.hasNext()? '':'disabled'}"><a href="#Hit/generatereport?page=${hitReport.nextPageable().getPageNumber()}">Next</a></li>
						</ul>
					</div>

				</div>
			</c:when>
			<c:otherwise>
				<h3 class="text-danger text-center">No data found!!</h3>
			</c:otherwise>
		</c:choose>

	</div>
</div>--%>

<div class="panel panel-success">
	<!-- Default panel contents -->
	<div class="panel-heading text-center text-primary">
		Hit Report
		<div class="no-print pull-right"  onclick="window.print();return false;" style="width: 72px;height: 80px;margin-right: 30px;cursor: pointer;" ><img style="widht:80px; height:70px;"   src="/resources/website/assets/img/printer.jpg" <%--style="margin: 15px;"--%>/><%--</button>--%>

		</div>
	</div>

	<div class="panel-body">
		<c:choose>
			<c:when test="${hitReport!=null}">
				<div class="table-responsive">
					<table class="table table-striped table-bordered dt-responsive nowrap compact">
						<thead>
						<th data-priority="3">Hit Date</th>&nbsp;&nbsp;
						<th data-priority="4">Hit Numbers</th>
						<th data-priority="5">Refresh Hit</th>
						</thead>
						<tbody>

						<c:forEach items="${hitReport}" var="hitReport" varStatus="loop">
                           <tr>
								<td> <fmt:formatDate value="${hitReport.hitDate}" pattern="dd/MM/yyyy" /></td>&nbsp;&nbsp;
								<td>${hitReport.hitNumbers}</td>
								<td>${hitReport.refreshHits}</td>

							</tr>
						</c:forEach>
						</tbody>

					</table>
				</div>
			</c:when>
		</c:choose>

	</div>
</div>