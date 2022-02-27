<%@ page import="user.User" %>
<!DOCTYPE html>
<%
    String session_class = "";
    String username = "";
    session.invalidate();
%>
<html lang="de">
<head>
    <title>auctionary :: Logout</title>
    <!--Meta stuff-->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!--Favicon-->
    <link rel="icon" type="image/x-icon" href="misc/favicon.ico">
    <!--Style-->
    <link rel="stylesheet" href="css/style.css">
    <!--JavaScripts-->
    <script type="application/javascript" src="js/init.js"></script>
    <script type="application/javascript" src="js/script.js"></script>
</head>
<body>
<header>
    <nav>
        <div id="sidenav">
            <a href="javascript:void(0)" id="close-btn">&times;</a>
            <a id="nav-title" href="index.jsp">auctionary</a>
            <p><%=username %></p>
            <hr>
            <a href="login.jsp" id="login">Login</a>
            <a href="profil">Profil</a>
            <div class="<%=session_class%>"></div>
            <a href="about.jsp">&Uuml;ber</a>
        </div>
        <span id="menu-btn">&#9776; menu</span>
    </nav>
    <div class="logo"></div>
    <hr>
</header>
<main>
    <div class="logout-wrapper wrapper">
        <p id="title">Logout</p>
        <h4>Bis zum n&auml;chsten mal.</h4>
    </div>
</main>
<footer>
    <hr>
    <div class="footer">&copy; Alexander Wiltz, <div id="year"></div></div>
</footer>
</body>
</html>