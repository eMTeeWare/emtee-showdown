const user = Math.random().toString(36).slice(2, 7)

const searchInput = document.getElementById("search")
const hideSelectedCheckbox = document.getElementById("hideSelectedCheckbox")
const hideNotSelectedCheckbox = document.getElementById("hideNotSelectedCheckbox")
const seasonCards = document.getElementById("seasons")

const updateCardHiddenState = () => {
    Object.values(seasonCards.children).forEach(seasonCard => {
        let isVisible = seasonCard.getElementsByClassName("title")[0].innerText.toLowerCase().includes(searchInput.value.toLowerCase())
            | seasonCard.getElementsByClassName("info")[0].innerText.toLowerCase().includes(searchInput.value.toLowerCase())
            | seasonCard.getElementsByClassName("summary")[0].innerText.toLowerCase().includes(searchInput.value.toLowerCase())
        if (hideSelectedCheckbox.checked === true && seasonCard.classList.contains("selected")) {
            isVisible = false
        }
        if (hideNotSelectedCheckbox.checked === true && !seasonCard.classList.contains("selected")) {
            isVisible = false
        }
        seasonCard.classList.toggle("hide", !isVisible)
    })
}

const select = id => {
    const element = document.getElementById(id)
    const data = `id=${element.id}&user=${user}`
    if (element.classList.contains("selected")) {
        element.classList.remove("selected")
        fetch("/selection", {
            method: "DELETE",
            body: data,
            headers: {
                "Content-type": "application/x-www-form-urlencoded"
            }
        }).then(response => console.log(response))
    } else {
        element.classList.add("selected")
        fetch("/selection", {
            method: "POST",
            body: data,
            headers: {
                "Content-type": "application/x-www-form-urlencoded"
            }
        }).then(response => console.log(response))
    }
}

searchInput.addEventListener("input", updateCardHiddenState)
hideSelectedCheckbox.addEventListener("input", updateCardHiddenState)
hideNotSelectedCheckbox.addEventListener("input",updateCardHiddenState)