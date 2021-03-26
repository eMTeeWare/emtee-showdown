function changeDarkMode() {
    const inputElement = document.getElementById("dark-mode-input-element");
    const isDarkModeActive = inputElement.checked;
    const settingsValue = {
        dark_mode: isDarkModeActive
    }
    window.localStorage.setItem('settings', JSON.stringify(settingsValue));
    loadSettings();
}

function load() {
    const inputElement = document.getElementById("dark-mode-input-element");
    inputElement.checked = JSON.parse(window.localStorage.getItem("settings")).dark_mode
    loadSettings()
}