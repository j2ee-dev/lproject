<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>


<c:forEach items="${pviews}" var="pview">
    <c:forTokens items="${pview.views}" delims="," var="mySplit">
        <c:set var = "string1" value = '${mySplit}'/>
        <c:set var = "string2" value = "${fn:replace(string1, '/admin/', '#')}" />
        <li class="treeview">
            <c:set var = "string3" value = '${string2}'/>
            <c:set var = "string4" value = "${fn:replace(string3, '#', '')}" />

            <c:set var = "string5" value = '${string4}'/>
            <c:set var = "string6" value = "${fn:replace(string5, '/index', '')}" />
            <a href="<c:out value="${contextPath}${string2}"/>">
                <i class="fa fa-columns"></i> <span><c:out
                    value="${string6}" /></span>
                <span class="pull-right-container">
            <i class="fa fa-angle-left pull-right"></i>
        </span>
            </a>
        </li>
    </c:forTokens>
</c:forEach>
