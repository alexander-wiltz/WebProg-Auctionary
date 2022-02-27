// the navbar
function openNav() {
    // get viewport and set in order to the screen width the overlay of the vert navbar
    var overlay = '100%';
    if (document.documentElement.clientWidth > 780) {
        overlay = '20%';
    } 
    document.getElementById("sidenav").style.width = overlay;
}
  
function closeNav() {
    document.getElementById("sidenav").style.width = "0";
}

function setYear() {
    document.getElementById("year").innerHTML = new Date().getFullYear();
}

function buildSession() {
    var toggle = document.getElementById("login");
    var session_class = document.getElementsByClassName("active")[0];

    session_class.innerHTML =
        "<a href=\"simulations\">Auktionen</a>\n" +
        "<a href=\"createOffer.jsp\">Auktion erstellen</a>\n" +
        "<a href=\"editOffer.jsp\">Auktion bearbeiten</a>";

    toggle.innerHTML = "Logout";
    toggle.setAttribute("href","logout.jsp");
}