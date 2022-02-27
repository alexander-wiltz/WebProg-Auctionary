<%@ page import="user.User" %>
<!DOCTYPE html>
<%
    String session_class = "";
    String username = "";
    User session_user = (User) session.getAttribute("user");
    if (session_user != null) {
        session_class = "active";
        username = session_user.toString();
    }
%>
<html lang="de">
    <head>
        <title>auctionary :: &Uuml;ber</title>
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
                    <p id="username"><%=username %></p>
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
            <div class="ueber-wrapper wrapper">
                <p id="title">&Uuml;ber</p>
                <p>Design und Code von Alexander Wiltz</p>
                <p>Entwickelt im Rahmen des Moduls Webprogrammierung, IT Analyst</p>
                <p><b>Navigation:</b><br />Idee von w3schools.com</p>
                <p><b>Responsive:</b><br />Optimiert f&uuml;r eine Breite von 1200px, Tablets um 769px und Smartphones unter 400px</p>
                <p><b>Steuerung:</b><br />Steuerung durch Sessiontoken. Erweiterung des Men&uuml;bands, wenn valide Session l&auml;uft. Weiterhin Toggle Login zu Logout, wenn Session aktiv.</p>
                <p><b>ISBN:</b><br />ISBN Eingabe als Zahlenfolge, als auch im Originalformat mit '-' als Trennzeichen m&ouml;glich. L&auml;ngenvalidierung erfolgt nach Parsing.</p>
                <p><b>Testuser:</b><br />frodo.beutlin@hobbiton.com (PW: gandalf), harry.potter@hogwards.co.uk (PW: Zitronensorbet)</p>
                <p><b>Fehlerbehandlung:</b><br />Login Passworteingabe, Login Uservalidierung, Registrierung keine doppelten Mailadresse, zus&auml;tzliche serverseitige Validierung</p>
                <p><b>Adminpanel:</b> <a href="admin">&Uuml;bersicht</a></p>
            </div>
        </main>
        <footer>
            <hr>
            <div class="footer">&copy; Alexander Wiltz, <div id="year"></div></div>
        </footer>
    </body>
</html>