<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="top.jsp"/>

<div class="panel panel-default">
    <div class="panel-heading">
        <h3 class="panel-title">Lista dostępnych monitorów:</h3>
    </div>
    <div class="panel-body">
        <form:form modelAttribute="measurements" method="post" action="${pageContext.request.contextPath}/res/measurements/">
                <c:forEach var="item" items="${measurements.rscSet}" varStatus="loop">
                    <table class="table table-condensed">
                    <thead>
                    <tr>
                        <th style="width:550px;">Zasób</th>
                        <th>Wybierz</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <td>${item.sName}</td>
                        <td>
                            <form:checkbox path="rscSet[${loop.index}].selected" />
                            <form:hidden path="rscSet[${loop.index}].sName" />
                            <form:hidden path="rscSet[${loop.index}].measurements" />
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            <input type="submit" class="btn btn-default" value="Szczegóły" />
        </form:form>
    </div>
</div>

<jsp:include page="bot.jsp"/>