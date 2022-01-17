<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<div class="panel panel-success">
    <!-- Default panel contents -->
    <div class="panel-heading text-center text-primary">Repealed Act<span class="pull-right"><a href="#RepealedAct/create" role="button"><i class="fa fa-arrow-right"></i>Create New</a></span>&nbsp;&nbsp;&nbsp;<span class="pull-right"><a href="#RepealedAct/edit/${id}" role="button"><i class="fa fa-arrow-right"></i> Edit</a></span>
        <span class="pull-left">
    	 <a href="#RepealedAct/index" role="button"> <i class="fa fa-history" aria-hidden="true"></i>
Back to List</a>
    </span>
    </div>
    <div class="panel-body table-responsive">
        <table class="table table-condensed">

            <tr class="">
                <th style="white-space: nowrap;">Act</th>
                <th style="white-space:nowrap;width:1px;"> :</th>
                <td>${repealedAct.actId.shortTitle}</td>
            </tr>

            <tr class="active">
                <th style="white-space: nowrap;">Description</th>
                <th style="white-space:nowrap;width:1px;"> :</th>
                <td>${repealedAct.description}</td>
            </tr>

            <tr class="">
                <th style="white-space: nowrap;">Status</th>
                <th style="white-space:nowrap;width:1px;"> :</th>
                <td>${repealedAct.status}</td>
            </tr>

            <tr class="active">
                <th style="white-space: nowrap;">Created By</th>
                <th style="white-space:nowrap;width:1px;"> :</th>
                <td>${repealedAct.createdBy}</td>
            </tr>

            <tr class="">
                <th style="white-space: nowrap;width:1px;">Created At</th>
                <th style="white-space:nowrap;width:1px;"> :</th>
                <td><fmt:formatDate value="${repealedAct.createdAt}" pattern="dd/MM/yyyy hh:mm a"/></td>
            </tr>

            <tr class="active">
                <th style="white-space: nowrap;">Updated By</th>
                <th style="white-space:nowrap;width:1px;"> :</th>
                <td>${repealedAct.updatedBy}</td>
            </tr>

            <tr class="">
                <th style="white-space: nowrap;width:1px;">Updated At</th>
                <th style="white-space:nowrap;width:1px;"> :</th>
                <td><fmt:formatDate value="${repealedAct.updatedAt}" pattern="dd/MM/yyyy hh:mm a"/></td>
            </tr>

        </table>
    </div>
</div>