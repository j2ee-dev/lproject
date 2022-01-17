<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<div class="panel panel-success">
    <div class="panel-heading text-center text-primary">
        Permission <span class="pull-right"><a href="#Permission/create" role="button"><i class="fa fa-arrow-right"></i>Create New</a></span>
        </br>
        <span class="pull-left">
    	 <a href="#Permission/index" role="button"> <i class="fa fa-history" aria-hidden="true"></i>
Back to List</a>
    </span>
    </div>

    <%--Spliting View Menues Starts--%>
    <c:set var="string1" value='${permission.views}'/>
    <c:set var="string2" value="${fn:replace(string1, '/admin/', '#')}"/>

    <c:set var="string3" value='${string2}'/>
    <c:set var="string4" value="${fn:replace(string3, '#', '')}"/>

    <c:set var="string5" value='${string4}'/>
    <c:set var="string6" value="${fn:replace(string5, '/index', '')}"/>
    <%--Spliting View Menues Ends--%>

    <%--Spliting Create Menues Starts--%>
    <c:set var="createViews_string1" value='${permission.createViews}'/>
    <c:set var="createViews_string2" value="${fn:replace(createViews_string1, '/admin/', '#')}"/>

    <c:set var="createViews_string3" value='${createViews_string2}'/>
    <c:set var="createViews_string4" value="${fn:replace(createViews_string3, '#', '')}"/>

    <c:set var="createViews_string5" value='${createViews_string4}'/>
    <c:set var="createViews_string6" value="${fn:replace(createViews_string5, '/create', '')}"/>
    <%--Spliting Create Menues Ends--%>

    <%--Spliting Create Menues Starts--%>
    <c:set var="editChecks_string1" value='${permission.editChecks}'/>
    <c:set var="editChecks_string2" value="${fn:replace(editChecks_string1, '/admin/', '#')}"/>

    <c:set var="editChecks_string3" value='${editChecks_string2}'/>
    <c:set var="editChecks_string4" value="${fn:replace(editChecks_string3, '#', '')}"/>

    <c:set var="editChecks_string5" value='${editChecks_string4}'/>
    <c:set var="editChecks_string6" value="${fn:replace(editChecks_string5, '/edit/', '')}"/>
    <%--Spliting Create Menues Ends--%>


    <div class="panel-body table-responsive">
        <table class="table table-condensed">

            <tr class="">
                <th style="white-space: nowrap;">Username</th>
                <th style="white-space:nowrap;width:1px;"> :</th>
                <td>${permission.username}</td>
            </tr>
            <tr class="active">
                <th style="white-space: nowrap;">View Menues</th>
                <th style="white-space:nowrap;width:1px;"> :</th>
                <td><c:out value="${string6}"/></td>
            </tr>
            <tr class="">
                <th style="white-space: nowrap;">Create Menues</th>
                <th style="white-space:nowrap;width:1px;"> :</th>
                <td><c:out value="${createViews_string6}"/></td>
            </tr>
            <tr class="active">
                <th style="white-space: nowrap;">Edit Menues</th>
                <th style="white-space:nowrap;width:1px;"> :</th>
                <td><c:out value="${editChecks_string6}"/></td>
            </tr>
        </table>
    </div>
</div>