<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="top.jsp"/>

<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/measurements.js"></script>

<div class="panel panel-default">
    <div class="panel-heading">
        <h3 class="panel-title">Lista dostępnych pomiarów dla wybranych monitorów:</h3>
    </div>
    <div class="panel-body">
        <form:form modelAttribute="measurements" method="post" action="${pageContext.request.contextPath}/graph/">
            <table class="table">
                <thead>
                <tr>
                    <th>Zasób</th>
                    <th>Pomiar</th>
                    <th>Aktualna wartość</th>
                    <th>Twórz wykres</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="item" items="${measurements.list}" varStatus="loop">
                    <tr>
                        <td>${item.resource}</td>
                        <td>${item.metric}</td>
                        <td>
                            <div class="measurements" data-url="${item.details}">0</div>
                        </td>
                        <td>
                            <form:checkbox path="list[${loop.index}].selected" />
                            <form:hidden path="list[${loop.index}].resource" />
                            <form:hidden path="list[${loop.index}].details" />
                            <form:hidden path="list[${loop.index}].self" />
                            <form:hidden path="list[${loop.index}].metric" />
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            <input type="submit" class="btn btn-default" value="Pokaż wykresy" />
        </form:form>
    </div>
</div>

<jsp:include page="bot.jsp"/>