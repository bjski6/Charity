<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE html>
<html lang="pl">
<%@include file="header.jsp"%>>
</header>

<section class="login-page">
    <h2>Lista Przekazanych darów </h2>
    <h1>

            <table border="2" align="center">
                <thead>
                <th width="200px">Nazwa instytucji</th>
                <th width="200px">Kategoria</th>
                <th width="200px">Ilość przekazanych worków</th>
                <th width="200px">Przekazujący</th>


                </thead>
                <tbody>
                <c:forEach items="${donations}" var="donation">
                    <tr>
                        <td height="100px"><c:out value="${donation.institution.getName()}"/></td>
                        <td height="100px"><c:out value="${donation.quantity}"/></td>
                        <td height="100px"><c:out value="${donation.user.getUsername()}"/></td>

                        <sec:authorize access="hasRole('ADMIN')">
                            <td><a href="admin/donation/edit/${institution.id}"><input type="button"
                                                                                          value="Edytuj"></a></td>
                            <td><a href="admin/donation/delete/${institution.id}"> <input type="button"
                                                                                             value="Usuń"></a></td>
                        </sec:authorize>
                    </tr>

                </c:forEach>



                </tbody>
            </table>
            <sec:authorize access="hasRole('ADMIN')">
                <a href="/user/addDonation"> <input type="button" value="Dodaj"></a></td>
            </sec:authorize>
        </h1>
</section>



<%@include file="footer.jsp" %>
</body>
</html>