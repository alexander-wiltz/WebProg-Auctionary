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
        <title>auctionary :: Profil</title>
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
                    <div class="<%=session_class%>"></div>
                    <a href="about.jsp">&Uuml;ber</a>
                </div>
                <span id="menu-btn">&#9776; menu</span>
            </nav>
            <div class="logo"></div>
            <hr>
        </header>
        <main>
            <div class="profil-wrapper wrapper">
                <p id="title">Willkommen ${user.firstname}</p>
                <!-- if not logged in, forward to login page -->
                <h4>Dein Profil <span id="logout"><a href="logout.jsp">(Logout)</a></span></h4>
                <table id="user">
                    <tr>
                        <td>Name:</td>
                        <td>${user.firstname} ${user.lastname}</td>
                    </tr>
                    <tr>
                        <td>Kd-Nr:</td>
                        <td>${user.id}</td>
                    </tr>
                    <tr>
                        <td>Geburtsdatum:</td>
                        <td>${user.birth}</td>
                    </tr>
                    <tr>
                        <td>Adresse:</td>
                        <td>${user.address}</td>
                    </tr>
                    <tr>
                        <td>Wohnort:</td>
                        <td>${user.zipcode} ${user.location}</td>
                    </tr>
                    <tr>
                        <td>Mail:</td>
                        <td>${user.email}</td>
                    </tr>
                </table>

                <h4>Angelegte B&uuml;cher</h4>
                <table id="implemented-books">
                    <tr>
                        <th>#</th>
                        <th>Titel</th>
                        <th>Verlag</th>
                        <th>Preis</th>
                        <th>Verkauft</th>
                    </tr>
                    <jsp:scriptlet>
                            int x = 1;
                        </jsp:scriptlet>
                    <c:forEach items= "${ books }" var= "b">
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
                            <td>
                                <c:if test="${ b.sold == true}">
                                    <c:out value="Verkauft"></c:out>
                                </c:if>
                            </td >
                        </tr >
                    </c:forEach >
                </table>
                <h4>Bereits versteigerte B&uuml;cher</h4>
                <table id="sold-books">
                    <tr>
                        <th>#</th>
                        <th>Titel</th>
                        <th>Verlag</th>
                        <th>Mindestpreis</th>
                        <th>Verkaufspreis</th>
                    </tr>
                    <jsp:scriptlet>
                            int y = 1;
                        </jsp:scriptlet>
                    <c:forEach items= "${ soldbooks }" var= "s">
                        <tr >
                            <td>
                                <jsp:scriptlet>
                                        out.print(y);
                                        y += 1;
                                    </jsp:scriptlet>
                            </td>
                            <td>
                                <input type="hidden" name="bookid=${b.id}" value="${s.id}">
                                    ${ s.title }
                            </td >
                            <td>${ s.publisher }</td >
                            <td>${ s.minPrice } &euro;</td >
                            <td>${ s.price } &euro;</td >
                        </tr >
                    </c:forEach >
                </table>
                <h4>Gesamterl&ouml;s aus Versteigerungen: ${ summary } &euro;</h4>
            </div>
        </main>
        <footer>
            <hr>
            <div class="footer">&copy; Alexander Wiltz, <div id="year"></div></div>
        </footer>
    </body>
</html>