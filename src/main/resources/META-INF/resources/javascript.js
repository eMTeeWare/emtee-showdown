let user = Math.random().toString(36).substr(2, 5);

function alarm(id) {
    alert('Alarm! von ' + id);
}

function select(id) {
    var theElement = document.getElementById(id);
    var data = "id=" + theElement.id + "&user=" + user;
    var xhr = new XMLHttpRequest();
    xhr.withCredentials = true;

    xhr.addEventListener("readystatechange", function () {
        if (this.readyState === this.DONE) {
            console.log(this.responseText);
        }
    });
    if (theElement.classList.contains("selected")) {

        theElement.classList.remove("selected");

        xhr.open("DELETE", "http://localhost:8080/selection");
        xhr.setRequestHeader("content-type", "application/x-www-form-urlencoded");

        xhr.send(data);
    } else {
        theElement.classList.add("selected");

        xhr.open("POST", "http://localhost:8080/selection");
        xhr.setRequestHeader("content-type", "application/x-www-form-urlencoded");

        xhr.send(data);
    }
}

function flip(id) {
    document.getElementById(id).classList.toggle('is-flipped');
}