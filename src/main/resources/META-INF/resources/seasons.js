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

const summaries = document.getElementsByClassName("summary")
Array.from(summaries).forEach(summary => {
    const seasonBoxHeight = 100
    const paddingHeight = 14
    summary.style.height = seasonBoxHeight - summary.parentElement.children[0].scrollHeight - summary.parentElement.children[1].scrollHeight - summary.parentElement.children[2].scrollHeight - paddingHeight + "px"

    summary.addEventListener("mouseover", () => {
        summary.style.height = summary.scrollHeight + "px"
    })

    summary.addEventListener("mouseout", () => {
        summary.style.height = seasonBoxHeight - summary.parentElement.children[0].scrollHeight - summary.parentElement.children[1].scrollHeight - summary.parentElement.children[2].scrollHeight - paddingHeight + "px"
    })
})

searchInput.addEventListener("input", updateCardHiddenState)
hideSelectedCheckbox.addEventListener("input", updateCardHiddenState)
hideNotSelectedCheckbox.addEventListener("input",updateCardHiddenState)