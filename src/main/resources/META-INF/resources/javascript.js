function alarm(id) {
    alert('Alarm! von ' + id);
}

function select(id) {
    var theElement = document.getElementById(id);
    if (theElement.classList.contains("selected")) {
        theElement.classList.remove("selected");
    } else {
        theElement.classList.add("selected");
    }
}

function flip(id) {
    document.getElementById(id).classList.toggle('is-flipped');
}