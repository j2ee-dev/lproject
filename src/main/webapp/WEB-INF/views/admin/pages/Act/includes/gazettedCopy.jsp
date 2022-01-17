<%--
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<div class="form-group">
    <label class="col-sm-2 control-label">Act Gazzeted Copy</label>
    <div class="col-sm-10 child_information">
        <button type="button" class="btn btn-info add_child_row"
                data-child-validation="required|min_row[1]|max_row[3]|exact_row[3]">Add New
        </button>
        <table class="table table-condensed table-bordered table-responsive table-striped child_list"
               style="display: none;">

            <thead>
            <tr class="childHead">
                <th>Title</th>
                <th>Browse</th>
                <th>Browse</th>
                <th>Action</th>
            </tr>
            </thead>
            <tbody class="childs">
            <!--
			<tr class="child"><td>
				      		<form:textarea class="form-control " data-validation="trim" id="inputpreambleFootnoteList1[0].footnote" path="actScheduleList1[0].title"  placeholder="Title"/>
				  	</td>
       	       <td>


                                <input type="file" cssClass="form-control" cssErrorClass="has-error"
                                              data-validation="trim|file_allowed_type[jpeg,jpg,png,doc,docx,pdf,ppt,pptx,csv,xls,xls,zip,mp3,mp4,flv]"
                                              id="inputpreambleFootnoteList1[${loop.index}].attachment"
                                              name="actScheduleAttachmentFile1" placeholder="Attachment"/>



				 </td>
				<td>
				    		<div class="checkbox-inline">
				      			<form:checkbox data-validation="trim" id="inputpreambleFootnoteList1[0].status" path="actScheduleList1[0].status"  value="1" /> Status
							</div>
				  	</td><td>

						<button type="button" class="btn btn-danger remove_child_row"><i class="fa fa-trash"></i>
						</button>
				</td></tr>
			-->


            <c:forEach items="${act.actScheduleList1}" varStatus="loop" var="actPreambleFootnote1">
                <c:if test="${actPreambleFootnote1.id!=null}">
                    <tr class="child preloaded"
                        data-action="${pageContext.request.contextPath}/admin/ActGazetedCopy/delete/${actPreambleFootnote1.id}">
                        <form:hidden path="actScheduleList1[${loop.index}].id"/>
                        <td>
                            <form:textarea class="form-control " data-validation="trim"
                                           id="inputpreambleFootnoteList1[${loop.index}].title"
                                           path="actScheduleList1[${loop.index}].title" placeholder="Footnote"/>
                        </td>
                        <td>
                            <form:input type="file" cssClass="form-control" cssErrorClass="has-error"
                                        data-validation="trim"
                                        id="inputpreambleFootnoteList1[${loop.index}].attachment"
                                        path="actScheduleList1[${loop.index}].attachment"/>
                        </td>
                        <td>
                            <div class="checkbox-inline">
                                <form:checkbox data-validation="trim"
                                               id="inputpreambleFootnoteList1[${loop.index}].status"
                                               path="actScheduleList1[${loop.index}].status" value="1"/> Status
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
</div>--%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<div class="form-group">
    <label class="col-sm-2 control-label">Act GazettedCopy</label>
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
				      		<form:input type="text" class="form-control" data-validation="trim|required|min_length[1]|max_length[255]" id="inputActGazettedCopyList[0].title" path="actGazettedCopyList[0].title"  placeholder="Title"/>
				      		<form:errors path="actGazettedCopyList[0].title" cssClass="help-block text-danger" element="span" />
				  	</td><td class="row">
				    	    <label class="pull-left col-sm-6 btn btn-default btn-file">
							    Browse <input type="file"  cssClass="form-control" cssErrorClass="has-error"  data-validation="trim|file_allowed_type[jpeg,jpg,png,doc,docx,pdf,ppt,pptx,csv,xls,xls,zip,mp3,mp4,flv]|min_length[1]|max_length[255]" id="actGazettedCopyList[0].attachment" path="actGazettedCopyList[0].attachment"    name="actGazettedCopyListFile"  placeholder="Attachment"/>

							</label>
							<div class="preview hide col-sm-6">
								<span class="child">
									<a href="" title="" target="_blank"><i class="fa fa fa-cloud-download" aria-hidden="true"></i></a>
								</span>
							</div>
							<form:errors path="actGazettedCopyList[0].attachment" cssClass="help-block text-danger" element="span" />
				  </td><td>
				    		<div class="checkbox-inline">
				      			<form:checkbox data-validation="trim|required|is_boolean" id="inputActGazettedCopyList[0]
				      			.status" path="actGazettedCopyList[0].status"  value="1" /> Status
				      			<form:errors path="actGazettedCopyList[0].status" cssClass="help-block text-danger" element="span" />
							</div>
				  	</td><td>

						<button type="button" class="btn btn-danger remove_child_row"><i class="fa fa-trash"></i>
						</button>
				</td></tr>
			-->


            <c:forEach items="${act.actGazettedCopyList}" varStatus="loop" var="actGazettedCopy">
                <c:if test="${actGazettedCopy.id!=null}">


                    <tr class="child preloaded"
                        data-action="${pageContext.request.contextPath}/admin/ActGazettedCopy/delete/${actGazettedCopy.id}">
                        <form:hidden path="actGazettedCopyList[${loop.index}].id"/>
                        <td>
                            <form:input type="text" class="form-control"
                                        data-validation="trim|required|min_length[1]|max_length[255]"
                                        id="inputActGazettedCopyList[${loop.index}].title"
                                        path="actGazettedCopyList[${loop.index}].title" placeholder="Title"/>
                            <form:errors path="actGazettedCopyList[${loop.index}].title" cssClass="help-block text-danger"
                                         element="span"/>
                        </td>
                        <td class="row">
                            <label class="pull-left col-sm-6 btn btn-default btn-file">
                                Browse <input type="file" cssClass="form-control" cssErrorClass="has-error"
                                              data-validation="trim|file_allowed_type[jpeg,jpg,png,doc,docx,pdf,ppt,pptx,csv,xls,xls,zip,mp3,mp4,flv]|min_length[1]|max_length[255]"
                                              id="inputActGazettedCopyList[${loop.index}].attachment"
                                              path="actGazettedCopyList[${loop.index}].attachment"
                                              name="actGazettedCopyListFile" placeholder="Attachment"/>

                            </label>
                            <div class="preview col-sm-6">
								<span class="child">
									<a href="${contextPath}/upload/${actGazettedCopy.attachment}" <%--href="${pageContext.request.contextPath}/${actSchedule.attachment}"--%>
                                       title="${actGazettedCopy.attachment}" target="_blank"><i
                                            class="fa fa fa-cloud-download" aria-hidden="true"></i></a>
								</span>
                            </div>
                            <form:errors path="actGazettedCopyList[${loop.index}].attachment"
                                         cssClass="help-block text-danger" element="span"/>
                        </td>
                        <td>
                            <div class="checkbox-inline">
                                <form:checkbox data-validation="trim|required|is_boolean"
                                               id="inputActGazettedCopyList[${loop.index}].status"
                                               path="actGazettedCopyList[${loop.index}].status" value="1"/> Status
                                <form:errors path="actGazettedCopyList[${loop.index}].status"
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


