<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<div>
    <div class="btn-group">
        <a href="${pageContext.request.contextPath}/monitors/" class="btn btn-default">Lista Monitorów</a>
        <a href="${pageContext.request.contextPath}/rsc/" class="btn btn-default">Wyszukiwanie zasobów</a>
        <a href="${pageContext.request.contextPath}/login/" class="btn btn-default">Logowanie</a>
    </div>
</div>