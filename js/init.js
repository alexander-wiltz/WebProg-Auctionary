// init global added functions onload
window.onload = function() {
    // interaction with the sidebar
    document.getElementById("menu-btn").onclick = openNav;
    document.getElementById("close-btn").onclick = closeNav;

    // set current year for footer
    setYear();

    // check for page
    var path = window.location.pathname;
    var page = path.split("/").pop();

    // build menu in order to the session state
    var session = document.getElementById("username").innerHTML;
    if (session !== "") {
        buildSession();
    }

    if (page == 'register.jsp' || page == 'register') {
        // registry form
        // autocomplete
        document.getElementById("location").onkeyup = autocomplete;
        // set focus infos
        document.getElementById("birth").onfocus = setFocus;
        // set maxBirth for registration
        setMaxBirth();
        // set value for date field
        document.getElementById("birth").value = getMaxDate();

        // validate zipcode length
        document.getElementById("zipcode").onblur = checknumeric;
        // autocompletition

        // email validation
        document.getElementById("email").onblur = validateEmail;
        // validate password
        document.getElementById("password").onkeyup = passwordvalidation;
        document.getElementById("password").onblur = passwordvalidation;

    } else if (page == 'createOffer.jsp') {
        // ISBN request
        document.getElementById("isbn").onblur = validateISBN;
    }
}