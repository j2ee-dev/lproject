<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<div class="form-group">
    <label class="col-sm-2 control-label">Act Rules & Regulations</label>
    <div class="col-sm-10 child_information">
        <button type="button" class="btn btn-info add_child_row_attachment"
                data-child-validation="required|min_row[1]|max_row[3]|exact_row[3]">Add New
        </button>
        <table class="table table-condensed table-bordered table-responsive table-striped child_list"
               style="display: none;">

            <thead>
            <tr class="childHead">
                <th>Title</th>
                <th>Attachment</th>
                <th>Status</th>
                <th>Action</th>
            </tr>
            </thead>
            <tbody class="childs">
            <!--
			<tr class="child"><td>
				      		<form:input type="text" class="form-control" data-validation="trim|required|min_length[1]|max_length[255]" id="inputactRulesRegulationList[0].title" path="actRulesRegulationList[0].title"  placeholder="Title"/>
				      		<form:errors path="actRulesRegulationList[0].title" cssClass="help-block text-danger" element="span" />
				  	</td><td class="row">
				    	    <label class="pull-left col-sm-6 btn btn-default btn-file">
							    Browse <input type="file"  cssClass="form-control" cssErrorClass="has-error"  data-validation="trim|file_allowed_type[jpeg,jpg,png,doc,docx,pdf,ppt,pptx,csv,xls,xls,zip,mp3,mp4,flv]|min_length[1]|max_length[255]" id="inputactRulesRegulationList[0].attachment" path="actRulesRegulationList[0].attachment"    name="actRulesRegulatonAttachmentFile"  placeholder="Attachment"/>

							</label>
							<div class="preview hide col-sm-6">
								<span class="child">
									<a href="" title="" target="_blank"><i class="fa fa fa-cloud-download" aria-hidden="true"></i></a>
								</span>
							</div>
							<form:errors path="actRulesRegulationList[0].attachment" cssClass="help-block text-danger" element="span" />
				  </td><td>
				    		<div class="checkbox-inline">
				      			<form:checkbox data-validation="trim|required|is_boolean" id="inputactRulesRegulationList[0].status" path="actRulesRegulationList[0].status"  value="1" /> Status
				      			<form:errors path="actRulesRegulationList[0].status" cssClass="help-block text-danger" element="span" />
							</div>
				  	</td><td>

						<button type="button" class="btn btn-danger remove_child_row"><i class="fa fa-trash"></i>
						</button>
				</td></tr>
			-->


            <c:forEach items="${act.actRulesRegulationList}" varStatus="loop" var="RulesRegulation">
                <c:if test="${RulesRegulation.id!=null}">


                    <tr class="child preloaded"
                        data-action="${pageContext.request.contextPath}/admin/ActRulesRegulation/delete/${RulesRegulation.id}">
                        <form:hidden path="actRulesRegulationList[${loop.index}].id"/>
                        <td>
                            <form:input type="text" class="form-control"
                                        data-validation="trim|required|min_length[1]|max_length[255]"
                                        id="inputactRulesRegulationList[${loop.index}].title"
                                        path="actRulesRegulationList[${loop.index}].title" placeholder="Title"/>
                            <form:errors path="actRulesRegulationList[${loop.index}].title" cssClass="help-block text-danger"
                                         element="span"/>
                        </td>
                        <td class="row">
                            <label class="pull-left col-sm-6 btn btn-default btn-file">
                                Browse <input type="file" cssClass="form-control" cssErrorClass="has-error"
                                              data-validation="trim|file_allowed_type[jpeg,jpg,png,doc,docx,pdf,ppt,pptx,csv,xls,xls,zip,mp3,mp4,flv]|min_length[1]|max_length[255]"
                                              id="inputactRulesRegulationList[${loop.index}].attachment"
                                              path="actRulesRegulationList[${loop.index}].attachment"
                                              name="actRulesRegulatonAttachmentFile" placeholder="Attachment"/>

                            </label>
                            <div class="preview col-sm-6">
								<span class="child">
									<a href="${contextPath}/upload/${RulesRegulation.attachment}" <%--href="${pageContext.request.contextPath}/${actSchedule.attachment}"--%>
                                       title="${RulesRegulation.attachment}" target="_blank"><i
                                            class="fa fa fa-cloud-download" aria-hidden="true"></i></a>
								</span>
                            </div>
                            <form:errors path="actRulesRegulationList[${loop.index}].attachment"
                                         cssClass="help-block text-danger" element="span"/>
                        </td>
                        <td>
                            <div class="checkbox-inline">
                                <form:checkbox data-validation="trim|required|is_boolean"
                                               id="inputactRulesRegulationList[${loop.index}].status"
                                               path="actRulesRegulationList[${loop.index}].status" value="1"/> Status
                                <form:errors path="actRulesRegulationList[${loop.index}].status"
                                             cssClass="help-block text-danger" element="span"/>
                            </div>
                        </td>
                        <td>

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

