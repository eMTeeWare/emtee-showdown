const searchInput = document.getElementById("search")
const seasonCards = document.getElementById("seasons")

searchInput.addEventListener("input", e => {
    Object.values(seasonCards.children).forEach(seasonCard => {
        const isVisible = seasonCard.getElementsByClassName("title")[0].innerText.toLowerCase().includes(e.target.value.toLowerCase())
        seasonCard.classList.toggle("hide", !isVisible)
    })
})