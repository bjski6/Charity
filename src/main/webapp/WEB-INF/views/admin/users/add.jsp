<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE html>
<html lang="pl">

<%@include file="../../header.jsp" %>


</header>

<section class="login-page">
    <h2>Dodaj użytkownika</h2>
    <form:form method="post" action="/admin/users/add" modelAttribute="user">
        <div class="form-group">
            <label for="username" style="font-size: xx-large">Nazwa użytkownika</label>
            <form:input path="username" type="text" rows="1" placeholder="Username"/>
        </div>
        <div class="form-group">
            <label for="role" style="font-size: xx-large">Uprawnienia</label>
            <form:select path="role" items="${roles}" itemValue="id" itemLabel="name"/>
        </div>
        <div class="form-group">
            <label for="password" style="font-size: xx-large">Hasło użytkownika</label>
            <form:input path="password"  type="password" rows="2" placeholder="Hasło"/>
        </div>

        <div class="form-group form-group--buttons">
            <button class="btn" type="submit">Zapisz</button>
        </div>
    </form:form>
</section>


<%@include file="../../footer.jsp" %>
</body>
</html>