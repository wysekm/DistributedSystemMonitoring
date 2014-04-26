<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>DSM Web Interface</title>
    <jsp:include page="head.jsp"/>
</head>
<body>
<input type="hidden" id="contextPath" value="${pageContext.request.contextPath}" />
<div class="main">
<jsp:include page="menu.jsp"/>
<div style="margin-top: 40px;"></div>