<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<div class="panel panel-success">
    <!-- Default panel contents -->
    <div class="panel-heading  text-center text-primary">Dashboard</div>
    <div class="panel-body">

            <table class="table table-striped table-bordered">
                <tr>
                    <th>Total Volumes</th>
                    <td>:</td>
                    <td>${total_active_volume}</td>
                </tr>
                <tr>
                    <th>Total Acts</th>
                    <td>:</td>
                    <td>${total_active_act}</td>
                </tr>
                <tr>
                    <th>Total Sections</th>
                    <td>:</td>
                    <td>${total_active_section}</td>
                </tr>
                <tr>
                    <th>Total Visitors</th>
                    <td>:</td>
                    <td>${total_visitor}</td>
                </tr>
            </table>

    </div>
</div>

<%--
<div class="panel panel-success">
    <!-- Default panel contents -->
    <div class="panel-heading  text-center text-primary">Hit Search</div>
    <div class="panel-body">

        <form:form method="post" role="form" modelAttribute="hit"
                   action="${contextPath}/admin/hit/generatereport?${_csrf.parameterName}=${_csrf.token}"
                   class="form-horizontal" data-validation="true" data-ajax-submit="true" novalidate="novalidate">
            <spring:bind path="fromDate">
                <div class="form-group ${(not empty status.errorMessage) ? ' has-error':''}">
                    <label for="inputfromDate" class="col-sm-4 control-label">From Date</label>
                    <div class="col-sm-3 controls" id="inputinputfromDate">
                        <div class="input-group date" data-provide="datepicker">
                            <form:input type="text" class="form-control" data-validation="trim|valid_date[dd/mm/yyyyy]" id="inputfromDate" path="fromDate"  placeholder="From Date" data-date-format="dd/mm/yyyy"/>
                            <span class="input-group-addon">
				                    <span class="glyphicon glyphicon-calendar">
				                    </span>
				                </span>
                        </div>
                        <form:errors path="fromDate" cssClass="help-block text-danger" element="span" />
                    </div>
                </div>
            </spring:bind>

            <spring:bind path="toDate">
                <div class="form-group ${(not empty status.errorMessage) ? ' has-error':''}">
                    <label for="inputtoDate" class="col-sm-4 control-label">To Date</label>
                    <div class="col-sm-3 controls" id="inputtoDate">
                        <div class="input-group date" data-provide="datepicker">
                            <form:input type="text" class="form-control" data-validation="trim|valid_date[dd/mm/yyyyy]" id="inputtoDate" path="toDate"  placeholder="To Date" data-date-format="dd/mm/yyyy"/>
                            <span class="input-group-addon">
				                    <span class="glyphicon glyphicon-calendar">
				                    </span>
				                </span>
                        </div>
                        <form:errors path="toDate" cssClass="help-block text-danger" element="span" />
                    </div>
                </div>
            </spring:bind>


            <div class="form-group">
                <div class="col-sm-offset-4 col-sm-5">
                    <button type="submit" class="btn btn-primary"> &nbsp; Save &nbsp; </button>

                </div>
            </div>
        </form:form>



    </div>
</div>--%>
