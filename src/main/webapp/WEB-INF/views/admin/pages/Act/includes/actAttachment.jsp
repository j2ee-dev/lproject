<%--
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<div class="form-group">
    <label class="col-sm-2 control-label">Act Attachment</label>
    <div class="col-sm-10 child_information">
        <button type="button" class="btn btn-info add_child_row"
                data-child-validation="required|min_row[1]|max_row[3]|exact_row[3]">Add New
        </button>
        <table class="table table-condensed table-bordered table-responsive table-striped child_list"
               style="display: none;">

            <thead>
            <tr class="childHead">
                <th>Attachment</th>
                <th>Status</th>
                <th>Action</th>
            </tr>
            </thead>
            <tbody class="childs">
            <!--
			<tr class="child">
       	       <td>
                                <input type="file" cssClass="form-control" cssErrorClass="has-error"
                                              data-validation="trim|file_allowed_type[jpeg,jpg,png,doc,docx,pdf,ppt,pptx,csv,xls,xls,zip,mp3,mp4,flv]"
                                              id="inputactAttachment[0].attachment"
                                              name="actAttachmentFile" placeholder="Attachment"/>

				 </td>
				<td>
				    		<div class="checkbox-inline">
				      			<form:checkbox data-validation="trim" id="inputactAttachment[0].status" path="actAttachmentList[0].status"  value="1" /> Status
							</div>
				  	</td><td>

						<button type="button" class="btn btn-danger remove_child_row"><i class="fa fa-trash"></i>
						</button>
				</td></tr>
			-->


            <c:forEach items="${act.actAttachmentList}" varStatus="loop" var="actAttachment">
                <c:if test="${actAttachment.id!=null}">
                    <tr class="child preloaded"
                        data-action="${pageContext.request.contextPath}/admin/ActAttachment/delete/${actAttachment.id}">
                        <form:hidden path="actAttachmentList[${loop.index}].id"/>
                        <td>
                            <form:input type="file" cssClass="form-control" cssErrorClass="has-error"
                                        data-validation="trim"
                                        id="inputactAttachment[${loop.index}].attachment"
                                        path="actAttachmentList[${loop.index}].attachment"/>
                        </td>
                        <td>
                            <div class="checkbox-inline">
                                <form:checkbox data-validation="trim"
                                               id="inputactAttachment[${loop.index}].status"
                                               path="actAttachmentList[${loop.index}].status" value="1"/> Status
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
    <label class="col-sm-2 control-label">Authentic English Text</label>
    <div class="col-sm-10 child_information">
        <button type="button" class="btn btn-info add_child_row_attachment"
                data-child-validation="required|min_row[1]|max_row[3]|exact_row[3]">Add New
        </button>
        <table class="table table-condensed table-bordered table-responsive table-striped child_list"
               style="display: none;">

            <thead>
            <tr class="childHead">
                <th>Attachment</th>
                <th>Status</th>
                <th>Action</th>
            </tr>
            </thead>
            <tbody class="childs">
            <!--
			<tr class="child"><td class="row">
				    	    <label class="pull-left col-sm-6 btn btn-default btn-file">
							    Browse <input type="file"  cssClass="form-control" cssErrorClass="has-error"  data-validation="trim|file_allowed_type[jpeg,jpg,png,doc,docx,pdf,ppt,pptx,csv,xls,xls,zip,mp3,mp4,flv]|min_length[1]|max_length[255]" id="inputactAttachment[0].attachment" path="actAttachmentList[0].attachment"    name="actAttachmentFile"  placeholder="Attachment"/>

							</label>
							<div class="preview hide col-sm-6">
								<span class="child">
									<a href="" title="" target="_blank"><i class="fa fa fa-cloud-download" aria-hidden="true"></i></a>
								</span>
							</div>
							<form:errors path="actAttachmentList[0].attachment" cssClass="help-block text-danger" element="span" />
				  </td><td>
				    		<div class="checkbox-inline">
				      			<form:checkbox data-validation="trim|required|is_boolean" id="inputactAttachment[0].status" path="actAttachmentList[0].status"  value="1" /> Status
				      			<form:errors path="actAttachmentList[0].status" cssClass="help-block text-danger" element="span" />
							</div>
				  	</td><td>

						<button type="button" class="btn btn-danger remove_child_row"><i class="fa fa-trash"></i>
						</button>
				</td></tr>
			-->


            <c:forEach items="${act.actAttachmentList}" varStatus="loop" var="actAttachment">
                <c:if test="${actAttachment.id!=null}">


                    <tr class="child preloaded"
                        data-action="${pageContext.request.contextPath}/admin/ActAttachment/delete/${actAttachment.id}">
                        <form:hidden path="actAttachmentList[${loop.index}].id"/>
                        <td class="row">
                            <label class="pull-left col-sm-6 btn btn-default btn-file">
                                Browse <input type="file" cssClass="form-control" cssErrorClass="has-error"
                                              data-validation="trim|file_allowed_type[jpeg,jpg,png,doc,docx,pdf,ppt,pptx,csv,xls,xls,zip,mp3,mp4,flv]|min_length[1]|max_length[255]"
                                              id="inputactAttachment[${loop.index}].attachment"
                                              path="actAttachmentList[${loop.index}].attachment"
                                              name="actAttachmentFile" placeholder="Attachment"/>

                            </label>
                            <div class="preview col-sm-6">
								<span class="child">
									<a href="${contextPath}/upload/${actAttachment.attachment}" <%--href="${pageContext.request.contextPath}/${actSchedule.attachment}"--%>
                                       title="${actAttachment.attachment}" target="_blank"><i
                                            class="fa fa fa-cloud-download" aria-hidden="true"></i></a>
								</span>
                            </div>
                            <form:errors path="actAttachmentList[${loop.index}].attachment"
                                         cssClass="help-block text-danger" element="span"/>
                        </td>
                        <td>
                            <div class="checkbox-inline">
                                <form:checkbox data-validation="trim|required|is_boolean"
                                               id="inputactAttachment[${loop.index}].status"
                                               path="actAttachmentList[${loop.index}].status" value="1"/> Status
                                <form:errors path="actAttachmentList[${loop.index}].status"
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

