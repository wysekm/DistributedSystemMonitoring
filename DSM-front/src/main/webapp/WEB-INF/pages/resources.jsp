<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="top.jsp"/>

<div class="panel panel-default">
    <div class="panel-heading">
        <h3 class="panel-title">Lista dostępnych monitorów:</h3>
    </div>
    <div class="panel-body">
        <form:form modelAttribute="rsc" method="post" action="${pageContext.request.contextPath}/graph/">

                <c:forEach var="item" items="${rsc.rscSet}" varStatus="loop">
                    <table class="table">
                    <thead>
                    <tr>
                        <th>Zasób</th>
                        <th>Pomiar</th>
                        <th>Twórz wykres</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <td>${item.resource}</td>
                        <td>${item.metric} [${item.unit}]</td>
                        <td>
                            <form:checkbox path="rscSet[${loop.index}].selected" />
                            <form:hidden path="rscSet[${loop.index}].resource" />
                            <form:hidden path="rscSet[${loop.index}].href" />
                            <form:hidden path="rscSet[${loop.index}].self" />
                            <form:hidden path="rscSet[${loop.index}].unit" />
                            <form:hidden path="rscSet[${loop.index}].metric" />
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