// requests via ajax
//'3551551677'


function validateISBN() {
    var isbn = document.getElementById("isbn").value;
    try {
        isbn = isbn.replace("-","");
    } catch (error) {
        return;
    }

    if(isbn.length === 10 || isbn.length === 13) {
        getBook();
        getBookFromGoogle();
    }

}

function getBook() {
    var isbn = document.getElementById("isbn").value;
    try {
        isbn = isbn.replace("-","");
    } catch (error) {
        return;
    }
    var xmlhttp = new XMLHttpRequest();

    xmlhttp.onreadystatechange = function() {
        if(xmlhttp.readyState == 4 && xmlhttp.status == 200) {
            var myBook = JSON.parse(xmlhttp.responseText);

            document.getElementById("booktitle").value = myBook.title;
            document.getElementById("publisher").value = myBook.publishers[0];
            document.getElementById("publish_date").value = myBook.publish_date;

            // CoverLink: 'https://covers.openlibrary.org/b/id/' mybook.covers[0] + '-M.jpg'
        } else {
            // Cleanup on ERROR
            document.getElementById("booktitle").value = "";
            document.getElementById("publisher").value = "";
            document.getElementById("publish_date").value = "";
        }
    } 

    xmlhttp.open("GET", 'https://openlibrary.org/isbn/' + isbn + '.json', true);
    xmlhttp.send();
}

function getBookFromGoogle() {
    var isbn = document.getElementById("isbn").value;
    try {
        isbn = isbn.replace("-","");
    } catch (error) {
        return;
    }
    var xmlhttp = new XMLHttpRequest();

    xmlhttp.onreadystatechange = function() {
        if(xmlhttp.readyState == 4 && xmlhttp.status == 200) {
            var myBook = JSON.parse(xmlhttp.responseText);

            //document.getElementById("booktitle").value = myBook.items[0];
            console.log(myBook.items[0]);

        } else {
            // Cleanup on ERROR

        }
    }

    xmlhttp.open("GET", 'https://www.googleapis.com/books/v1/volumes?q=' + isbn, true);
    xmlhttp.send();
}

function roundPrice() {
    var pricing = document.getElementById("price").value;
    try {
        
    } catch (error) {
        
    }
}