<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <meta http-equiv="X-UA-Compatible" content="ie=edge"/>
    <title>Document</title>
    <link rel="stylesheet" href="<c:url value="/resources/css/style.css"/>"/>
</head>
<body>
        <sec:authorize access="isAuthenticated()">
        <header class="header--form-page">
            <nav class="container container--70">
                <ul class="nav--actions">
                    <li class="logged-user">
                        <sec:authentication property="name"/>
                        <ul class="dropdown">
                            <li><a href="/user/profile">Profil</a></li>
                            <li><a href="/user/userDonationList">Moje zbiórki</a></li>
                            <li><a href="/logout">Wyloguj</a></li>
                        </ul>
                    </li>
                </ul>
                </sec:authorize>

                <sec:authorize access="hasAnyRole('USER')">
                    <ul>

                        <li><a href="#" class="btn btn--without-border active">Start</a></li>
                        <li><a href="#" class="btn btn--without-border">O co chodzi?</a></li>
                        <li><a href="#" class="btn btn--without-border">O nas</a></li>
                        <li><a href="/institutionList" class="btn btn--without-border">Fundacje i organizacje</a></li>
                        <li><a href="/user/addDonation" class="btn btn--without-border">Przekaż dary</a></li>
                        <li><a href="#" class="btn btn--without-border">Kontakt</a></li>
                    </ul>
                </sec:authorize>
                <sec:authorize access="hasRole('ADMIN')">
                    <ul>

                        <li><a href="/institutionList" class="btn btn--without-border">Fundacje i organizacje</a></li>
                        <li><a href="/admin/users/userList" class="btn btn--without-border">Użytkownicy</a></li>
                        <li><a href="/donationList" class="btn btn--without-border">Przekazane dary</a></li>

                    </ul>
                </sec:authorize>
            </nav>
    <div class="slogan container container--90">
        <h2>
            Dziękujemy za przesłanie formularza. Na maila prześlemy wszelkie
            informacje o odbiorze.
        </h2>
    </div>
</header>
<%@include file="../footer.jsp" %>
</body>
</html>