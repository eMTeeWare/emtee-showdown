const searchInput = document.getElementById("search")
const hideSelectedCheckbox = document.getElementById("hideSelectedCheckbox")
const hideNotSelectedCheckbox = document.getElementById("hideNotSelectedCheckbox")
const seasonCards = document.getElementById("seasons")

function updateCardHiddenState() {
    Object.values(seasonCards.children).forEach(seasonCard => {
        let isVisible = seasonCard.getElementsByClassName("title")[0].innerText.toLowerCase().includes(searchInput.value.toLowerCase())
        if (hideSelectedCheckbox.checked === true && seasonCard.classList.contains("selected")) {
            isVisible = false
        }
        if (hideNotSelectedCheckbox.checked === true && !seasonCard.classList.contains("selected")) {
            isVisible = false
        }
        seasonCard.classList.toggle("hide", !isVisible)
    })
}

searchInput.addEventListener("input", updateCardHiddenState)
hideSelectedCheckbox.addEventListener("input", updateCardHiddenState)
hideNotSelectedCheckbox.addEventListener("input",updateCardHiddenState)