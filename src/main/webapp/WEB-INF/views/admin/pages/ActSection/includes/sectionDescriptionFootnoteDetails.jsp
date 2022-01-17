
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<tr class="info"><th colspan="3">Section Description Footnote Information </th></tr>
	    	<c:choose>
  <c:when test="${empty actSection.sectionDescriptionFootnoteList}">
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
	    	<c:forEach items="${actSection.sectionDescriptionFootnoteList}" varStatus="loop" var="sectionDescriptionFootnote">
	    	  <tr>
	    	   <td>${sectionDescriptionFootnote.footnote}</td>
					<td>${sectionDescriptionFootnote.footnoteNumber}</td>
					<td>${sectionDescriptionFootnote.footnoteSign}</td>
					<td>${sectionDescriptionFootnote.status}</td>
					
	    	  </tr>
	    	  </c:forEach>
	    	</tbody>
	    	</table>
	    </td>
	</tr>
	    	</c:otherwise>
</c:choose>