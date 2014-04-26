<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<jsp:include page="top.jsp" />

<form:form method="post" action="${pageContext.request.contextPath}/rsc/" class="form-horizontal" role="form" modelAttribute="search">
    <div class="form-group">
        <label for="f_inputSearch" class="col-sm-2 control-label">Fraza wyszukiwania zasobu</label>

        <div class="col-sm-10">
            <form:input path="phrase" id="f_inputSearch" class="form-control"/>
        </div>
    </div>
    <div class="form-group">
        <div class="col-sm-offset-2 col-sm-10">
            <button type="submit" class="btn btn-default">Wyszukaj</button>
        </div>
    </div>
</form:form>

<jsp:include page="bot.jsp" />