<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<jsp:include page="top.jsp"/>

<div>
    <form:form method="post" action="/login" class="form-horizontal" role="form" modelAttribute="user">
        <div class="form-group">
            <label for="f_inputLogin" class="col-sm-2 control-label">Login</label>

            <div class="col-sm-10">
                <form:input path="login" id="f_inputLogin" class="form-control"/>
            </div>
        </div>

        <div class="form-group">
            <label for="f_inputPassword" class="col-sm-2 control-label">Hasło</label>

            <div class="col-sm-10">
                <form:input path="password" id="f_inputPassword" class="form-control"/>
            </div>
        </div>

        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <button type="submit" class="btn btn-default">Zaloguj</button>
            </div>
        </div>
    </form:form>
</div>
<jsp:include page="bot.jsp"/>