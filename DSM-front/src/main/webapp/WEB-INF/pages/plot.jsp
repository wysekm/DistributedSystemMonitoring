<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="top.jsp"/>

<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/plotter.js"></script>

<div class="panel panel-default">
    <div class="panel-heading">
        <h3 class="panel-title">Wykresy wybranych pomiarow</h3>
    </div>
    <div class="panel-body">
        <c:forEach var="item" items="${rsc.list}">
            <div class="panel-body plotter" data-source="${item.details}" data-title="${item.resource}"></div>
        </c:forEach>
    </div>
</div>

<jsp:include page="bot.jsp"/>