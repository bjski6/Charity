<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html lang="pl">
<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <meta http-equiv="X-UA-Compatible" content="ie=edge"/>
    <title>Document</title>
    <link rel="stylesheet" href="<c:url value="/resources/css/style.css"/>"/>
</head>
<body>

<section class="login-page">
    <h2>Wystąpił błąd </h2>

    <div class="form-group">
        <label for="name" style="font-size:x-large">Wróć na stronę główną</label>
    </div>

    <div class="form-group form-group--buttons">
        <a href="/admin/adminPage" class="btn btn--without-border">
            <button class="btn" type="submit">OK</button>
        </a>
    </div>

</section>
<script src="<c:url value="/resources/js/app.js"/>"></script>
</div>
</body>
</html>