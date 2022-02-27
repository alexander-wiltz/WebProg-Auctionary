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
        <title>auctionary :: Bearbeiten</title>
        <!--Meta stuff-->
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!--Favicon-->
        <link rel="icon" type="image/x-icon" href="misc/favicon.ico">
        <!--Style-->
        <link rel="stylesheet" href="css/style.css">
        <!--JavaScripts-->
        <script type="application/javascript" src="js/init.js"></script>
        <script type="application/javascript" src="js/script.js"></script>
        <!-- JSP Tags -->
        <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix= "c" %>
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
            <div class="edit-wrapper wrapper">
                <p id="title">Eigene B&uuml;cher</p>
                <h4>Angelegte B&uuml;cher</h4>
                <form action="editAuctions" method="post">
                    <table id="booklist">
                        <tr>
                            <th>#</th>
                            <th>Titel</th>
                            <th>Verlag</th>
                            <th>Mindespreis</th>
                            <th>Aktiv</th>
                            <th></th>
                        </tr>
                        <jsp:scriptlet>
                            int x = 1;
                        </jsp:scriptlet>
                        <c:forEach items= "${ editablebooks }" var= "b">
                            <tr >
                                <td>
                                    <jsp:scriptlet>
                                        out.print(x);
                                        x += 1;
                                    </jsp:scriptlet>
                                </td>
                                <td>
                                    <input type="hidden" name="bookid=${b.id}" value="${b.id}">
                                        ${ b.title }
                                </td >
                                <td>${ b.publisher }</td >
                                <td>${ b.minPrice } &euro;</td >
                                <td>${ b.active }</td >
                                <td><input type="checkbox" id="${b.id}" name="${b.id}"></td>
                            </tr >
                        </c:forEach >
                    </table>

                    <table id="control">
                        <tr>
                            <td>
                                <select name="controlaction" id="controlaction" required>
                                <option value="nothing">---</option>
                                <option value="commit">Markierte freigeben</option>
                                <option value="delete">Markierte l&ouml;schen</option>
                                </select>
                            </td>
                            <td><button type="submit" id="btn">Durchf&uuml;hren</button></td>
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