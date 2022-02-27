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
        <title>auctionary :: Registrieren</title>
        <!--Meta stuff-->
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!--Favicon-->
        <link rel="icon" type="image/x-icon" href="misc/favicon.ico">
        <!--Style-->
        <link rel="stylesheet" href="css/style.css">
        <link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
        <!--JavaScripts-->
        <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
        <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
        <script type="application/javascript" src="js/init.js"></script>
        <script type="application/javascript" src="js/script.js"></script>
        <script type="application/javascript" src="js/validate.js"></script>
        <script type="application/javascript" src="js/autocomplete.js"></script>
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
            <div class="registry-wrapper wrapper">
                <p id="title">Registrieren</p>
                <div class="error"> ${error.errorMessage} </div>
                <form action="register" method="post" id="registry">
                    <table id="register">
                        <tr>
                            <td><label for="vname">Vorname:</label></td>
                            <td><input type="text" id="vname" name="vname"></td>
                            <td><div></div></td>
                        </tr>
                        <tr>
                            <td><label for="nname">Nachname*:</label></td>
                            <td><input type="text" id="nname" name="nname" required></td>
                            <td><div id="nnamevalid"></div></td>
                        </tr>
                        <tr>
                            <td><label for="birth">Geburtsdatum*:</label></td>
                            <td><input type="date" id="birth" name="birth" length="10" required max=""></td>
                            <td><div id="birthvalid" class="hint">Mindestalter 18 Jahre.</div></td>
                        </tr>
                        <tr>
                            <td><label for="address">Adresse:</label></td>
                            <td><input type="text" id="address" name="address"></td>
                            <td><div></div></td>
                        </tr>
                        <tr>
                            <td><label for="zipcode">Postcode*:</label></td>
                            <td><input type="text" id="zipcode" name="zipcode" required></td>
                            <td><div id="plzvalid"></div></td>
                        </tr>
                        <tr>
                            <td><label for="location">Wohnort*:</label></td>
                            <td><input type="text" id="location" name="location" required></td>
                            <td><div></div></td>
                        </tr>
                        <tr>
                            <td><label for="email">Email*:</label></td>
                            <td><input type="email" id="email" name="email" required></td>
                            <td><div id="emailvalid"></div></td>
                        </tr>
                        <tr>
                            <td><label for="password">Passwort*:</label></td>
                            <td><input type="password" id="password" name="password" required></td>
                            <td><div id="passwordvalid" class="hint">8 Zeichen und Buchstaben inkl. Sonderzeichen (!?;+)</div></td>
                        </tr>
                        <tr>
                            <td></td>
                            <td>(*) Pflichtfelder</td>
                            <td><div></div></td>
                        </tr>
                        <tr>
                            <td></td>
                            <td><button type="submit" id="btn">Registrieren</button></td>
                            <td><div></div></td>
                        </tr>
                    </table>
                </form>
                <p>Du hast bereits ein Konto? <a href="login.jsp">Zum Login</a>.</p>
            </div>
        </main>
        <footer>
            <hr>
            <div class="footer">&copy; Alexander Wiltz, <div id="year"></div></div>
        </footer>
    </body>
</html>