
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<tr class="info"><th colspan="3">Section Head Footnote Information </th></tr>
	    	<c:choose>
  <c:when test="${empty actSection.sectionHeadFootnoteList}">
  	<tr class="danger"><td colspan="3">No Data Found!</td></tr>
  </c:when>
  <c:otherwise>
  	<tr><td colspan="3">
	    	<table class="table table-responsive table-striped">
	    	<thead>
	    	<tr>
	    		<th>Footnote</th>
						<th>Footnote Number</th>
						<th>Footnote Sign</th>
						<th>Status</th>
						</tr>
	    	</thead>
	    	<tbody>
	    	<c:forEach items="${actSection.sectionHeadFootnoteList}" varStatus="loop" var="sectionHeadFootnote">
	    	  <tr>
	    	   <td>${sectionHeadFootnote.footnote}</td>
					<td>${sectionHeadFootnote.footnoteNumber}</td>
					<td>${sectionHeadFootnote.footnoteSign}</td>
					<td>${sectionHeadFootnote.status}</td>
					
	    	  </tr>
	    	  </c:forEach>
	    	</tbody>
	    	</table>
	    </td>
	</tr>
	    	</c:otherwise>
</c:choose>