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

flipSelectedButton.addEventListener("click", () => randomFlip(true))
flipDiscardedButton.addEventListener("click", () => randomFlip(false))