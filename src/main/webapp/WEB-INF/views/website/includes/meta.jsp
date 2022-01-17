<%@ page import="org.jsoup.nodes.Document" %>
<%@ page import="org.jsoup.Jsoup" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>${metaTitle}</title>
<c:set var="metaDescription" value='${metaDescription}'/>
<%
    String string1 = (String) pageContext.getAttribute("metaDescription");
    Document doc = Jsoup.parse(string1);
    String text = doc.text();
    pageContext.setAttribute("description", text);
%>
<meta name="Description" content="${description}"/>
<meta name="Keywords" content="${metaKeywords}"/>
<!--for caching start-->
<meta http-equiv="Cache-control" content="public">           
<meta http-equiv="Pragma" content="public" />
<meta http-equiv="Expires" content="3600" /><!--30days (60sec * 60min * 24hours * 30days)-->
<!--for caching start-->
<link rel="icon" href="<c:url value="/resources/website/favicon.ico"/>"/>
