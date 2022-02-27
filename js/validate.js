// set input-format by activating in order to the current field
function setFocus() {
     document.getElementById("birthvalid").innerHTML = "Mindestalter 18 Jahre!";
}

function getMaxDate() {
    const d = new Date();
    var frmtDay;
    var frmtMonth;
    if (d.getDate() < 10) {
        frmtDay = "0" + d.getDate();
    } else {
        frmtDay = d.getDate();
    }
    if ((d.getMonth() + 1)  < 10) {
        frmtMonth = "0" + (d.getMonth() + 1);
    } else {
        frmtMonth = d.getMonth() + 1;
    }

    return d.getFullYear() - 18 + "-" + frmtMonth+ "-" + frmtDay;
}

// date of birth validation
function setMaxBirth() {
    document.getElementById("birth").setAttribute("max", getMaxDate());
}

// email validation on client side
// Format: [a-zA-Z0-9]@hs-kl.de || [a-zA-Z0-9]@fh-kl.de
function validateEmail() {
    var mailformat = /(?=.*[a-zA-Z0-9])(@((hs-kl)|(fh-kl))\.de)/;
    var e = document.getElementById("emailvalid");
    var val = document.forms["registry"]["email"].value;

    if (mailformat.test(val)) {
        e.innerHTML = "OK!";
        e.style.color = "green";
    } else {
        e.innerHTML = "E-Mail hat falsches Format!";
        e.style.color = "red";
    }
}

// password validation on client side
// A minimum of 8 signs, letters, digits and special signs (!?;+)
function passwordvalidation() {
    var e = document.getElementById("passwordvalid");
    var field = document.forms["registry"]["password"].value;

    var safePassword = new RegExp(/(?=.{8,})(?=.*[a-zA-Z0-9])(?=.*[!?;+])/);

     if (safePassword.test(field)) {
        e.innerHTML = "sicher";
        e.style.color = "green";
    } else {
        e.innerHTML = "unsicher";
        e.style.color = "red";
    }
}

// ajax postcode script
function checknumeric() {
    var letterNumber = /^[0-9]{5}/;
    var e = document.getElementById("plzvalid");
    var field = document.forms["registry"]["zipcode"].value;

    if(field.match(letterNumber) && !(field.length > 5)) { 
        e.innerHTML = "OK!";
        e.style.color = "green";
        plzscript();
    } else { 
        e.innerHTML = "Postleitzahl &uuml;berpr&uuml;fen!";
        e.style.color = "red";
    }
}

function plzscript() {
    // AJAX Script
    var plz = document.getElementById("plz").value;
    var httpRequest = new XMLHttpRequest();

    httpRequest.onreadystatechange = function (e) {
        if (e.target.readyState == XMLHttpRequest.DONE) {
            var orte = e.target.responseText.split(";");
            var firstOrt = orte[0].substring(5, orte[0].length).trim();
            // Fill location field
            document.getElementById("location").value=firstOrt;
        }
    };

    var url = "./ort?plz=" + plz;
    httpRequest.open("GET", url);
    httpRequest.send();
}

