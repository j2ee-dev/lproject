
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<tr class="info"><th colspan="3">Act Schedule Information </th></tr>
	    	<c:choose>
  <c:when test="${empty act.actScheduleList}">
  	<tr class="danger"><td colspan="3">No Data Found!</td></tr>
  </c:when>
  <c:otherwise>
  	<tr><td colspan="3">
	    	<table class="table table-responsive table-striped">
	    	<thead>
	    	<tr>
	    		<th>Title</th>
						<th>Attachment</th>
						<th>Status</th>
						</tr>
	    	</thead>
	    	<tbody>
	    	<c:forEach items="${act.actScheduleList}" varStatus="loop" var="actSchedule">
	    	  <tr>
	    	   <td>${actSchedule.title}</td>
					<td>${actSchedule.attachment}</td>
					<td>${actSchedule.status}</td>
					
	    	  </tr>
	    	  </c:forEach>
	    	</tbody>
	    	</table>
	    </td>
	</tr>
	    	</c:otherwise>
</c:choose>