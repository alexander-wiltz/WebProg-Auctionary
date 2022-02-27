// autocomplete for location by parsing a source-list

function autocomplete() {
    var httpRequest = new XMLHttpRequest();

    httpRequest.onreadystatechange = function(event) {
        if (event.target.readyState === XMLHttpRequest.DONE) {
            var location = event.target.responseText.split("\n");
            $("#location").autocomplete({
                source: location
            });
        }
    };

    var url = "./location";
    httpRequest.open("GET", url);
    httpRequest.send();
}

