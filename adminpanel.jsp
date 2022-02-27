<!DOCTYPE html>
<html lang="de">
<head>
    <title>auctionary :: admin</title>
    <!--Meta stuff-->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!--Favicon-->
    <link rel="icon" type="image/x-icon" href="misc/favicon.ico">
    <!--Style-->
    <link rel="stylesheet" href="css/style.css">
    <!-- JSP Tags -->
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix= "c" %>
</head>
<body>
<header>
    <nav>
        <a href="index.jsp">Home</a>
    </nav>
    <hr>
</header>
<main>
    <div class="wrapper panel-wrapper">
        <h4>Alle Benutzer</h4>
        <table id="usertable">
            <tr>
                <th>User-ID</th>
                <th>Nachname</th>
                <th>Vorname</th>
                <th>E-Mail</th>
            </tr>
            <c:forEach items= "${ user }" var= "u">
                <tr >
                    <td>${u.id}</td>
                    <td>${u.lastname}</td>
                    <td>${u.firstname}</td>
                    <td>${u.email}</td>
                </tr>
            </c:forEach >
        </table>
        <h4>Alle angelegten B&uuml;cher</h4>
        <table>
            <tr>
                <th>Buch-ID</th>
                <th>Eingestellt</th>
                <th>Titel</th>
                <th>Mindestpreis</th>
                <th>Verkaufspreis</th>
                <th>Frei zur Auktion</th>
                <th>Verkauft</th>
            </tr>
            <c:forEach items= "${ books }" var= "b">
                <tr >
                    <td>${b.id}</td>
                    <td>${b.userAsString}</td>
                    <td>${b.title}</td>
                    <td>${b.minPrice}</td>
                    <td>${b.price}</td>
                    <td>${b.active}</td>
                    <td>${b.sold}</td>
                </tr>
            </c:forEach >
        </table>
    </div>
</main>
<footer>
</footer>
</body>
</html>