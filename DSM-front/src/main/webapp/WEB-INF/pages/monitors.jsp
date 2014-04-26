<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<jsp:include page="top.jsp"/>

<div class="panel panel-default">
    <div class="panel-heading">
        <h3 class="panel-title">Lista dostępnych monitorów:</h3>
    </div>
    <div class="panel-body">
        <form:form action="${pageContext.request.contextPath}/res/" method="post" modelAttribute="measurements">
            <table class="table">
                <tr>
                    <td>Monitor</td>
                    <td>Metryka</td>
                    <td>Wybór</td>
                </tr>
                <c:forEach var="item" items="${measurements.modelSet}" varStatus="loop">
                    <tr>
                        <td>${item.name}</td>
                        <td>${item.metric}</td>
                        <td>
                            <form:checkbox path="modelSet[${loop.index}].selected" />
                            <form:hidden path="modelSet[${loop.index}].self" />
                            <form:hidden path="modelSet[${loop.index}].details" />
                            <form:hidden path="modelSet[${loop.index}].name" />
                            <form:hidden path="modelSet[${loop.index}].metric" />
                        </td>
                    </tr>
                </c:forEach>
            </table>
            <button type="submit" class="btn btn-default">Przejdź</button>
        </form:form>
    </div>
</div>


<jsp:include page="bot.jsp"/>