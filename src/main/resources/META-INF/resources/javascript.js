function alarm(id) {
    alert('Alarm! von ' + id);
}

function select(id) {
    var theElement = document.getElementById(id);
    if (theElement.classList.contains("selected")) {
        theElement.classList.remove("selected");
    } else {
        theElement.classList.add("selected");
        var data = "id=" + theElement.id;

        var xhr = new XMLHttpRequest();
        xhr.withCredentials = true;

        xhr.addEventListener("readystatechange", function () {
            if (this.readyState === this.DONE) {
                console.log(this.responseText);
            }
        });

        xhr.open("POST", "http://localhost:8080/selection");
        xhr.setRequestHeader("content-type", "application/x-www-form-urlencoded");

        xhr.send(data);
    }
}

function flip(id) {
    document.getElementById(id).classList.toggle('is-flipped');
}