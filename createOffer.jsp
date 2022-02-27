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
        <title>auctionary :: Auktion erstellen</title>
        <!--Meta stuff-->
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!--Favicon-->
        <link rel="icon" type="image/x-icon" href="misc/favicon.ico">
        <!--Style-->
        <link rel="stylesheet" href="css/style.css">
        <!--JavaScripts-->
        <script type="application/javascript" src="js/init.js"></script>
        <script type="application/javascript" src="js/script.js"></script>
        <script type="application/javascript" src="js/auction-validation.js"></script>
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
                    <div class="<%=session_class%>">
                    </div>
                    <a href="about.jsp">&Uuml;ber</a>
                </div>
                <span id="menu-btn">&#9776; menu</span>
            </nav>
            <div class="logo"></div>
            <hr>
        </header>
        <main>
            <div class="create-wrapper wrapper">
                <p id="title">Auktion erstellen</p>
                <form action="createAuctions" method="post" id="offer">
                    <table id="create-auctions">
                        <tr>
                            <td><label for="isbn">ISBN:</label></td>
                            <td><input type="text" id="isbn" name="isbn" required></td>
                        </tr>
                        <tr>
                            <td><label for="booktitle">Buchtitel:</label></td>
                            <td><input type="text" id="booktitle" name="booktitle" required></td>
                        </tr>
                        <tr>
                            <td><label for="publisher">Verlag:</label></td>
                            <td><input type="text" id="publisher" name="publisher"></td>
                        </tr>
                        <tr>
                            <td><label for="publish_date">Ver&ouml;ffentlichungsdatum:</label></td>
                            <td><input type="text" id="publish_date" name="publish_date"></td>
                        </tr>
                        <tr>
                            <td><label for="status">Zustand:</label></td>
                            <td><select name="status" id="status" required>
                                <option value="1">Wie neu</option>
                                <option value="2">Gut</option>
                                <option value="3">Mit Gebrauchsspuren</option>
                              </select></td>
                        </tr>
                        <tr>
                            <td><label for="comment">Bemerkung:<br />(Optional, <br />max 400 Zeichen)</label></td>
                            <td><textarea id="comment" name="comment" rows="4" maxlength="400"></textarea></td>
                        </tr>
                        <tr>
                            <td><label for="price">Mindestpreis:</label></td>
                            <td><input type="text" id="price" name="price" required></td>
                        </tr>
                        <tr>
                            <td><label for="activate">Freigeben:</label></td>
                            <td><input type="checkbox" id="activate" name="activate"></td>
                        </tr>
                        <tr id="buttons">
                            <td><button type="reset" id="reset">Abbrechen</button></td>
                            <td><button type="submit" id="btn">Auktion speichern</button></td>
                        </tr>
                    </table>
                </form>
            </div>
        </main>
        <footer>
            <hr>
            <div class="footer">&copy; Alexander Wiltz, <div id="year"></div></div>
        </footer>
    </body>
</html>