<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="top.jsp"/>
Wyświetlam listę monitorów zawierająca zasoby ze słowem kluczowym: ${search.phrase}

<table>
    <c:forEach items="${rsc}" var="r">
        <tr>
            <td>${r.name}</td>
        </tr>
    </c:forEach>
</table>

<jsp:include page="bot.jsp"/>