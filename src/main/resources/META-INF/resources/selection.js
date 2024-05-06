const flipSelectedButton = document.getElementById("flip-selected-button")
const flipDiscardedButton = document.getElementById("flip-discarded-button")
const selections = document.getElementById("selections")

const getApprovedSelections = () => Array.from(selections.children).filter(e => !e.children[0].classList.contains("is-flipped"))
const flip = (id, selected) => {
    const classList = document.getElementById(id).classList
    classList.add("is-flipped")
    if (selected) classList.add("selected")
    else classList.add("discarded")
}
const randomFlip = selected => flip(getApprovedSelections()[Math.floor(Math.random()*getApprovedSelections().length)].children[0].id, selected)

const summaries = document.getElementsByClassName("summary")
Array.from(summaries).forEach(summary => {
    summary.style.height = 150 - summary.parentElement.children[0].scrollHeight - summary.parentElement.children[1].scrollHeight - summary.parentElement.children[2].scrollHeight - 34 + "px"

    summary.addEventListener("mouseover", () => {
        summary.style.height = summary.scrollHeight + "px"
        summary.parentElement.parentElement.parentElement.parentElement.style.height = summary.scrollHeight + summary.parentElement.children[0].scrollHeight + summary.parentElement.children[1].scrollHeight + summary.parentElement.children[2].scrollHeight + 34 + "px"
    })

    summary.addEventListener("mouseout", () => {
        summary.style.height = 150 - summary.parentElement.children[0].scrollHeight - summary.parentElement.children[1].scrollHeight - summary.parentElement.children[2].scrollHeight - 34 + "px"
        summary.parentElement.parentElement.parentElement.parentElement.style.height = "150px"
    })
})

flipSelectedButton.addEventListener("click", () => randomFlip(true))
flipDiscardedButton.addEventListener("click", () => randomFlip(false))