
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<tr class="info"><th colspan="3">Chapter No Footnote Information </th></tr>
	    	<c:choose>
  <c:when test="${empty actChapter.chapterNoFootnoteList}">
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
	    	<c:forEach items="${actChapter.chapterNoFootnoteList}" varStatus="loop" var="chapterNoFootnote">
	    	  <tr>
	    	   <td>${chapterNoFootnote.footnote}</td>
					<td>${chapterNoFootnote.footnoteNumber}</td>
					<td>${chapterNoFootnote.footnoteSign}</td>
					<td>${chapterNoFootnote.status}</td>
					
	    	  </tr>
	    	  </c:forEach>
	    	</tbody>
	    	</table>
	    </td>
	</tr>
	    	</c:otherwise>
</c:choose>