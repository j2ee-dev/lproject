
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

		 <div class="form-group">
		 <label class="col-sm-2 control-label">Short Title Footnote</label>
				    	<div class="col-sm-10 child_information">
	   						<button type="button" class="btn btn-info add_child_row" data-child-validation="required|min_row[1]|max_row[3]|exact_row[3]">Add New</button>
				    	<table class="table table-condensed table-bordered table-responsive table-striped child_list" style="display: none;">
				
				<thead>
	   					<tr class="childHead">
	   						<th>Footnote</th><th>Footnote Number</th><th>Footnote Sign</th><th>Status</th><th>Action</th>
	   					</tr>
	   			</thead>
			<tbody class="childs">
			<!--
			<tr class="child"><td>
				      		<form:textarea class="form-control " data-validation="trim|required" id="inputshortTitleFootnoteList[0].footnote" path="shortTitleFootnoteList[0].footnote"  placeholder="Footnote"/>
				      		<form:errors path="shortTitleFootnoteList[0].footnote" cssClass="help-block text-danger" element="span" />
				  	</td>
       	       <td>

				      		<form:input type="number" cssClass="form-control" cssErrorClass="has-error" data-validation="trim|required|integer" id="inputshortTitleFootnoteList[0].footnoteNumber" path="shortTitleFootnoteList[0].footnoteNumber"  placeholder="Footnote Number"/>
				      		<form:errors path="shortTitleFootnoteList[0].footnoteNumber" cssClass="help-block text-danger" element="span" />
				 
				 </td>
				<td>
				      		<form:input type="text" class="form-control" data-validation="trim|exact_length[1]" id="inputshortTitleFootnoteList[0].footnoteSign" path="shortTitleFootnoteList[0].footnoteSign"  placeholder="Footnote Sign"/>
				      		<form:errors path="shortTitleFootnoteList[0].footnoteSign" cssClass="help-block text-danger" element="span" />
				  	</td><td>
				    		<div class="checkbox-inline">
				      			<form:checkbox data-validation="trim|required|is_boolean" id="inputshortTitleFootnoteList[0].status" path="shortTitleFootnoteList[0].status"  value="1" /> Status
				      			<form:errors path="shortTitleFootnoteList[0].status" cssClass="help-block text-danger" element="span" />
							</div>
				  	</td><td>

						<button type="button" class="btn btn-danger remove_child_row"><i class="fa fa-trash"></i>
						</button>
				</td></tr>
			-->
			
  			
  			<c:forEach items="${act.shortTitleFootnoteList}" varStatus="loop" var="actShortTitleFootnote">
  			<c:if test="${actShortTitleFootnote.id!=null}">

  			
			<tr class="child preloaded" data-action="${pageContext.request.contextPath}/admin/ShortTitleFootnote/delete/${actShortTitleFootnote.id}">
			<form:hidden path="shortTitleFootnoteList[${loop.index}].id" />
			<td>
				      		<form:textarea class="form-control " data-validation="trim|required" id="inputshortTitleFootnoteList[${loop.index}].footnote" path="shortTitleFootnoteList[${loop.index}].footnote"  placeholder="Footnote"/>
				      		<form:errors path="shortTitleFootnoteList[${loop.index}].footnote" cssClass="help-block text-danger" element="span" />
				  	</td>
       	       <td>

				      		<form:input type="number" cssClass="form-control" cssErrorClass="has-error" data-validation="trim|required|integer" id="inputshortTitleFootnoteList[${loop.index}].footnoteNumber" path="shortTitleFootnoteList[${loop.index}].footnoteNumber"  placeholder="Footnote Number"/>
				      		<form:errors path="shortTitleFootnoteList[${loop.index}].footnoteNumber" cssClass="help-block text-danger" element="span" />
				 
				 </td>
				<td>
				      		<form:input type="text" class="form-control" data-validation="trim|exact_length[1]" id="inputshortTitleFootnoteList[${loop.index}].footnoteSign" path="shortTitleFootnoteList[${loop.index}].footnoteSign"  placeholder="Footnote Sign"/>
				      		<form:errors path="shortTitleFootnoteList[${loop.index}].footnoteSign" cssClass="help-block text-danger" element="span" />
				  	</td><td>
				    		<div class="checkbox-inline">
				      			<form:checkbox data-validation="trim|required|is_boolean" id="inputshortTitleFootnoteList[${loop.index}].status" path="shortTitleFootnoteList[${loop.index}].status"  value="1" /> Status
				      			<form:errors path="shortTitleFootnoteList[${loop.index}].status" cssClass="help-block text-danger" element="span" />
							</div>
				  	</td><td>

						<button type="button" class="btn btn-danger remove_child_row"><i class="fa fa-trash"></i>
						</button>
				</td>
			</tr>
			</c:if>
			
			</c:forEach>
		
			
  		</tbody>
</table>

</div>
		</div>