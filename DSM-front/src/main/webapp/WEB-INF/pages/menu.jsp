<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<div>
    <div class="btn-group">
        <a href="${pageContext.request.contextPath}/res/" class="btn btn-default">Dostępne zasoby</a>
        <a href="${pageContext.request.contextPath}/res/measurements/" class="btn btn-default">Wszystkie pomiary</a>
        <a href="${pageContext.request.contextPath}/res/measurements/search/" class="btn btn-default">Wyszukiwanie pomiarów</a>
    </div>
</div>