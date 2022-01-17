<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<div class="form-group">
    <label class="col-sm-2 control-label">Act Attachment</label>
    <div class="col-sm-10 child_information">
        <button type="button" class="btn btn-info add_child_row"
                data-child-validation="required|min_row[1]|max_row[3]|exact_row[3]">Edit Act Attachment
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
            <tr class="child">
                <td>
                    <input type="file" cssClass="form-control" cssErrorClass="has-error"
                           name="actScheduleAttachmentFile2" placeholder="Attachment"/>
                </td>
                <td>
                    <div class="checkbox-inline">
                        <form:checkbox data-validation="trim"
                                       path="actScheduleList2[0].status" value="1"/> Status
                    </div>
                </td>
                <td>
                    <button type="button" class="btn btn-danger remove_child_row"><i class="fa fa-trash"></i>
                    </button>
                </td>
            </tr>
            </tbody>
        </table>
    </div>
</div>