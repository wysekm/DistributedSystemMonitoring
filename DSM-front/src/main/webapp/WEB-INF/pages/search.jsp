<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<jsp:include page="top.jsp"/>

<div class="panel panel-default">
    <div class="panel-heading">
        <h3 class="panel-title">Wyszukaj pomiary o podanej nazwie zasobu i/lub nazwie metryki</h3>
    </div>
    <div class="panel-body">
        <form method="post" action="${pageContext.request.contextPath}/res/measurements/search/" class="form-horizontal">
            <table class="table table-hover">
                <thead>
                <tr>
                    <th>Nazwa Metryki:</th>
                    <th>Nazwa zasobu:</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td>
                        <input name="metric" type="text" class="form-control" />
                    </td>
                    <td>
                        <input name="resource" type="text" class="form-control" />
                    </td>
                </tr>
                </tbody>
            </table>
            <input type="submit" class="btn btn-default" value="Wyszukaj" />
        </form>
    </div>
</div>

<jsp:include page="bot.jsp"/>