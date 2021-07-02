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
    <h2>Nowa instytucja charytatywna</h2>
    <form:form method="post" action="/admin/institution/add" modelAttribute="institution">
        <div class="form-group">
            <label for="name" style="font-size: xx-large">Nazwa instytucji</label>
            <form:textarea path="name" type="text" rows="1"/>
        </div>
        <div class="form-group">
            <label for="name" style="font-size: xx-large">Cel i misja</label>
            <form:textarea path="description" type="text" rows="2"/>
        </div>

        <div class="form-group form-group--buttons">
            <button class="btn" type="submit">Zapisz</button>
        </div>
    </form:form>
</section>


<%@include file="../../footer.jsp" %>
</body>
</html>