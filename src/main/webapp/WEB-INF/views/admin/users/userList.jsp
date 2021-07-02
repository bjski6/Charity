<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE html>
<html lang="pl">
<%@include file="../../header.jsp"%>>
</header>

<section class="login-page">
    <h2>Użytkownicy</h2>
    <h1>

            <table border="2" align="center">
                <thead>
                <th width="200px">Username</th>
                <th width="800px">Uprawnienia</th>


                </thead>
                <tbody>
                <c:forEach items="${userList}" var="user">
                    <tr>
                        <td height="100px"><c:out value="${user.username}"/></td>
                        <td><c:out value="${user.getRole().getName().substring(5)}"/></td>
                        <td><a href="edit/${user.id}"><input type="button" value="Edytuj"></a></td>
                            <td><a href="delete/${user.id}"> <input type="button" value="Usuń"></a></td>
                        <c:if test="${(user.getRole().getName()).equals('ROLE_USER')}"><td>
                            <a href="/admin/users/upgrade/${user.id}"> <input type="button" value="Awansuj"></a></td>
                        </c:if>
                        </tr>
                </c:forEach>

                </tbody>
            </table>

                <a href="/admin/users/add"> <input type="button" value="Dodaj nowego użytkownika"></a></td>

        </h1>
</section>



<%@include file="../../footer.jsp" %>
</body>
</html>