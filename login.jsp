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
        <title>auctionary :: Login</title>
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
            <div class="login-wrapper wrapper">
                <p id="title">Login</p>
                <div class="error"> ${error.errorMessage} </div>
                <form action="login" method="post">
                    <table id="loginform">
                        <tr>
                            <td><label for="user">Emailadresse:</label></td>
                            <td><input type="text" id="user" name="user"></td>
                        </tr>
                        <tr>
                            <td><label for="password">Passwort:</label></td>
                            <td><input type="password" id="password" name="password"></td>
                        </tr>
                        <tr>
                            <td></td>
                            <td><button type="submit" id="btn">Login</button></td>
                        </tr>
                    </table>
                </form>
                <p>Noch kein Konto? <a href="register.jsp">Registrieren</a>.</p>
            </div>
        </main>
        <footer>
            <hr>
            <div class="footer">&copy; Alexander Wiltz, <div id="year"></div></div>
        </footer>
    </body>
</html>