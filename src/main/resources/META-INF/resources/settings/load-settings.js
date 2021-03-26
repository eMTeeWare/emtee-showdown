function loadDarkmode(settings) {
    const isDarkModeActive = settings.dark_mode;
    if(isDarkModeActive) {
        document.body.classList.add("dark-mode");
    } else {
        document.body.classList.remove("dark-mode");
    }
}

function loadSettings() {
    const settings = JSON.parse(window.localStorage.getItem('settings'));
    loadDarkmode(settings);
}