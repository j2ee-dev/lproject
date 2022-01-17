<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<div class="panel panel-success">
    <!-- Default panel contents -->
    <div class="panel-heading text-center text-primary">Act<div class="pull-right"><a href="#Act/create" role="button"><i class="fa fa-arrow-right"></i>Create New</a></div>&nbsp;&nbsp;&nbsp;<div class="pull-right"><a href="#Act/edit/${act.id}" role="button"><i class="fa fa-arrow-right"></i> Edit</a></div>
        <span class="pull-left">
    	 <a href="#Act/index" role="button"> <i class="fa fa-history" aria-hidden="true"></i>
Back to List</a>
    </span>
    </div>
    <div class="panel-body table-responsive">
        <table class="table table-condensed">

            <tr class="">
                <th style="white-space: nowrap;">Volume</th>
                <th style="white-space:nowrap;width:1px;"> :</th>
                <td>${act.volume.volumeName}</td>
            </tr>

            <tr class="active">
                <th style="white-space: nowrap;">Short Title</th>
                <th style="white-space:nowrap;width:1px;"> :</th>
                <td>${act.shortTitle}</td>
            </tr>
            <jsp:include page="/WEB-INF/views/admin/pages/Act/includes/shortTitleFootnoteDetails.jsp"/>
            <tr class="">
                <th style="white-space: nowrap;">Number</th>
                <th style="white-space:nowrap;width:1px;"> :</th>
                <td>${act.number}</td>
            </tr>

            <tr class="active">
                <th style="white-space: nowrap;">Year</th>
                <th style="white-space:nowrap;width:1px;"> :</th>
                <td>${act.year}</td>
            </tr>

            <tr class="">
                <th style="white-space: nowrap;">Page No</th>
                <th style="white-space:nowrap;width:1px;"> :</th>
                <td>${act.page}</td>
            </tr>

            <tr class="active">
                <th style="white-space: nowrap;">Publish Date</th>
                <th style="white-space:nowrap;width:1px;"> :</th>
                <td>${act.publishDate}</td>
            </tr>

            <tr class="">
                <th style="white-space: nowrap;">Act Role</th>
                <th style="white-space:nowrap;width:1px;"> :</th>
                <td>${act.actRole}</td>
            </tr>
            <jsp:include page="/WEB-INF/views/admin/pages/Act/includes/actRoleFootnoteDetails.jsp"/>
            <tr class="active">
                <th style="white-space: nowrap;">Preamble</th>
                <th style="white-space:nowrap;width:1px;"> :</th>
                <td>${act.preamble}</td>
            </tr>
            <jsp:include page="/WEB-INF/views/admin/pages/Act/includes/preambleFootnoteDetails.jsp"/>
            <tr class="">
                <th style="white-space: nowrap;">Preamble Attachment</th>
                <th style="white-space:nowrap;width:1px;"> :</th>
                <td>${act.preambleAttachment}</td>
            </tr>

            <tr class="active">
                <th style="white-space: nowrap;">Display Preamble Text</th>
                <th style="white-space:nowrap;width:1px;"> :</th>
                <td>${act.displayPreambleText}</td>
            </tr>

            <tr class="">
                <th style="white-space: nowrap;">Is Ordinance</th>
                <th style="white-space:nowrap;width:1px;"> :</th>
                <td>${act.isOrdinance}</td>
            </tr>

            <tr class="active">
                <th style="white-space: nowrap;">Is Bangla Act</th>
                <th style="white-space:nowrap;width:1px;"> :</th>
                <td>${act.isBanglaAct}</td>
            </tr>

            <tr class="">
                <th style="white-space: nowrap;">Order No</th>
                <th style="white-space:nowrap;width:1px;"> :</th>
                <td>${act.orderNo}</td>
            </tr>

            <tr class="active">
                <th style="white-space: nowrap;">Status</th>
                <th style="white-space:nowrap;width:1px;"> :</th>
                <td>${act.status}</td>
            </tr>
            <jsp:include page="/WEB-INF/views/admin/pages/Act/includes/actScheduleDetails.jsp"/>
            <jsp:include page="/WEB-INF/views/admin/pages/Act/includes/actGazzetedCopyDetails.jsp"/>
            <jsp:include page="/WEB-INF/views/admin/pages/Act/includes/actAttachmentDetails.jsp"/>

            <tr class="active">
                <th style="white-space: nowrap;">Created By</th>
                <th style="white-space:nowrap;width:1px;"> :</th>
                <td>${act.createdBy}</td>
            </tr>

            <tr class="">
                <th style="white-space: nowrap;width:1px;">Created At</th>
                <th style="white-space:nowrap;width:1px;"> :</th>
                <td><fmt:formatDate value="${act.createdAt}" pattern="dd/MM/yyyy hh:mm a"/></td>
            </tr>

            <tr class="active">
                <th style="white-space: nowrap;">Updated By</th>
                <th style="white-space:nowrap;width:1px;"> :</th>
                <td>${act.updatedBy}</td>
            </tr>

            <tr class="">
                <th style="white-space: nowrap;width:1px;">Updated At</th>
                <th style="white-space:nowrap;width:1px;"> :</th>
                <td><fmt:formatDate value="${act.updatedAt}" pattern="dd/MM/yyyy hh:mm a"/></td>
            </tr>

        </table>
    </div>
</div>