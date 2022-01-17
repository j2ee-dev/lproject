
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

		 <div class="form-group">
		 <label class="col-sm-2 control-label">Section Name Footnote</label>
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
				      		<form:textarea class="form-control " data-validation="trim|required" id="inputsectionNameFootnoteList[0].footnote" path="sectionNameFootnoteList[0].footnote"  placeholder="Footnote"/>
				      		<form:errors path="sectionNameFootnoteList[0].footnote" cssClass="help-block text-danger" element="span" />
				  	</td>
       	       <td>

				      		<form:input type="number" cssClass="form-control" cssErrorClass="has-error" data-validation="trim|required|integer" id="inputsectionNameFootnoteList[0].footnoteNumber" path="sectionNameFootnoteList[0].footnoteNumber"  placeholder="Footnote Number"/>
				      		<form:errors path="sectionNameFootnoteList[0].footnoteNumber" cssClass="help-block text-danger" element="span" />
				 
				 </td>
				<td>
				      		<form:input type="text" class="form-control" data-validation="trim|exact_length[1]" id="inputsectionNameFootnoteList[0].footnoteSign" path="sectionNameFootnoteList[0].footnoteSign"  placeholder="Footnote Sign"/>
				      		<form:errors path="sectionNameFootnoteList[0].footnoteSign" cssClass="help-block text-danger" element="span" />
				  	</td><td>
				    		<div class="checkbox-inline">
				      			<form:checkbox data-validation="trim|required|is_boolean" id="inputsectionNameFootnoteList[0].status" path="sectionNameFootnoteList[0].status"  value="1" /> Status
				      			<form:errors path="sectionNameFootnoteList[0].status" cssClass="help-block text-danger" element="span" />
							</div>
				  	</td><td>

						<button type="button" class="btn btn-danger remove_child_row"><i class="fa fa-trash"></i>
						</button>
				</td></tr>
			-->
			
  			
  			<c:forEach items="${actSection.sectionNameFootnoteList}" varStatus="loop" var="actSectionSectionNameFootnote">
  			<c:if test="${actSectionSectionNameFootnote.id!=null}">

  			
			<tr class="child preloaded" data-action="${pageContext.request.contextPath}/admin/SectionNameFootnote/delete/${actSectionSectionNameFootnote.id}">
			<form:hidden path="sectionNameFootnoteList[${loop.index}].id" />
			<td>
				      		<form:textarea class="form-control " data-validation="trim|required" id="inputsectionNameFootnoteList[${loop.index}].footnote" path="sectionNameFootnoteList[${loop.index}].footnote"  placeholder="Footnote"/>
				      		<form:errors path="sectionNameFootnoteList[${loop.index}].footnote" cssClass="help-block text-danger" element="span" />
				  	</td>
       	       <td>

				      		<form:input type="number" cssClass="form-control" cssErrorClass="has-error" data-validation="trim|required|integer" id="inputsectionNameFootnoteList[${loop.index}].footnoteNumber" path="sectionNameFootnoteList[${loop.index}].footnoteNumber"  placeholder="Footnote Number"/>
				      		<form:errors path="sectionNameFootnoteList[${loop.index}].footnoteNumber" cssClass="help-block text-danger" element="span" />
				 
				 </td>
				<td>
				      		<form:input type="text" class="form-control" data-validation="trim|exact_length[1]" id="inputsectionNameFootnoteList[${loop.index}].footnoteSign" path="sectionNameFootnoteList[${loop.index}].footnoteSign"  placeholder="Footnote Sign"/>
				      		<form:errors path="sectionNameFootnoteList[${loop.index}].footnoteSign" cssClass="help-block text-danger" element="span" />
				  	</td><td>
				    		<div class="checkbox-inline">
				      			<form:checkbox data-validation="trim|required|is_boolean" id="inputsectionNameFootnoteList[${loop.index}].status" path="sectionNameFootnoteList[${loop.index}].status"  value="1" /> Status
				      			<form:errors path="sectionNameFootnoteList[${loop.index}].status" cssClass="help-block text-danger" element="span" />
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