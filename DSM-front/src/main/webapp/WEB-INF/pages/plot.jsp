<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="top.jsp"/>

<div class="panel panel-default">
    <div class="panel-heading">
        <h3 class="panel-title">Wykresy wybranych pomiarow</h3>
    </div>
    <div class="panel-body">
        <c:forEach items="${rsc.rscSet}" var="item">
            <div class="plotter" data-source="${item.href}" data-title="${item.resource}" data-yaxis="${item.metric}" data-unit="${item.unit}"></div>
        </c:forEach>
    </div>
</div>

<jsp:include page="bot.jsp"/>