let user = Math.random().toString(36).substr(2, 5);

function select(id) {
    const theElement = document.getElementById(id);
    const data = "id=" + theElement.id + "&user=" + user;
    const xhr = new XMLHttpRequest();
    xhr.withCredentials = true;

    xhr.addEventListener("readystatechange", function () {
        if (this.readyState === this.DONE) {
            console.log(this.responseText);
        }
    });
    if (theElement.classList.contains("selected")) {

        theElement.classList.remove("selected");

        xhr.open("DELETE", "/selection");
        xhr.setRequestHeader("content-type", "application/x-www-form-urlencoded");

        xhr.send(data);
    } else {
        theElement.classList.add("selected");

        xhr.open("POST", "/selection");
        xhr.setRequestHeader("content-type", "application/x-www-form-urlencoded");

        xhr.send(data);
    }
}

function flip(id, selected) {
    const classList = document.getElementById(id).classList;
    classList.add('is-flipped');
    if(selected) {
        classList.add('selected');
    } else {
        classList.add('discarded');
    }
}