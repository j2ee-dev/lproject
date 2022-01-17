
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

		 <div class="form-group">
		 <label class="col-sm-2 control-label">Act Role Footnote</label>
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
				      		<form:textarea class="form-control " data-validation="trim|required" id="inputactRoleFootnoteList[0].footnote" path="actRoleFootnoteList[0].footnote"  placeholder="Footnote"/>
				      		<form:errors path="actRoleFootnoteList[0].footnote" cssClass="help-block text-danger" element="span" />
				  	</td>
       	       <td>

				      		<form:input type="number" cssClass="form-control" cssErrorClass="has-error" data-validation="trim|required|integer" id="inputactRoleFootnoteList[0].footnoteNumber" path="actRoleFootnoteList[0].footnoteNumber"  placeholder="Footnote Number"/>
				      		<form:errors path="actRoleFootnoteList[0].footnoteNumber" cssClass="help-block text-danger" element="span" />
				 
				 </td>
				<td>
				      		<form:input type="text" class="form-control" data-validation="trim|exact_length[1]" id="inputactRoleFootnoteList[0].footnoteSign" path="actRoleFootnoteList[0].footnoteSign"  placeholder="Footnote Sign"/>
				      		<form:errors path="actRoleFootnoteList[0].footnoteSign" cssClass="help-block text-danger" element="span" />
				  	</td><td>
				    		<div class="checkbox-inline">
				      			<form:checkbox data-validation="trim|required|is_boolean" id="inputactRoleFootnoteList[0].status" path="actRoleFootnoteList[0].status"  value="1" /> Status
				      			<form:errors path="actRoleFootnoteList[0].status" cssClass="help-block text-danger" element="span" />
							</div>
				  	</td><td>

						<button type="button" class="btn btn-danger remove_child_row"><i class="fa fa-trash"></i>
						</button>
				</td></tr>
			-->
			
  			
  			<c:forEach items="${act.actRoleFootnoteList}" varStatus="loop" var="actActRoleFootnote">
  			<c:if test="${actActRoleFootnote.id!=null}">

  			
			<tr class="child preloaded" data-action="${pageContext.request.contextPath}/admin/ActRoleFootnote/delete/${actActRoleFootnote.id}">
			<form:hidden path="actRoleFootnoteList[${loop.index}].id" />
			<td>
				      		<form:textarea class="form-control " data-validation="trim|required" id="inputactRoleFootnoteList[${loop.index}].footnote" path="actRoleFootnoteList[${loop.index}].footnote"  placeholder="Footnote"/>
				      		<form:errors path="actRoleFootnoteList[${loop.index}].footnote" cssClass="help-block text-danger" element="span" />
				  	</td>
       	       <td>

				      		<form:input type="number" cssClass="form-control" cssErrorClass="has-error" data-validation="trim|required|integer" id="inputactRoleFootnoteList[${loop.index}].footnoteNumber" path="actRoleFootnoteList[${loop.index}].footnoteNumber"  placeholder="Footnote Number"/>
				      		<form:errors path="actRoleFootnoteList[${loop.index}].footnoteNumber" cssClass="help-block text-danger" element="span" />
				 
				 </td>
				<td>
				      		<form:input type="text" class="form-control" data-validation="trim|exact_length[1]" id="inputactRoleFootnoteList[${loop.index}].footnoteSign" path="actRoleFootnoteList[${loop.index}].footnoteSign"  placeholder="Footnote Sign"/>
				      		<form:errors path="actRoleFootnoteList[${loop.index}].footnoteSign" cssClass="help-block text-danger" element="span" />
				  	</td><td>
				    		<div class="checkbox-inline">
				      			<form:checkbox data-validation="trim|required|is_boolean" id="inputactRoleFootnoteList[${loop.index}].status" path="actRoleFootnoteList[${loop.index}].status"  value="1" /> Status
				      			<form:errors path="actRoleFootnoteList[${loop.index}].status" cssClass="help-block text-danger" element="span" />
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