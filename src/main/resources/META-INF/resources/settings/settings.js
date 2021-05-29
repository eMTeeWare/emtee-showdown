const darkmodeSystemInputElement = document.getElementById('darkmodeSystem');
const darkmodeOnInputElement = document.getElementById('darkmodeOn');
const darkmodeOffInputElement = document.getElementById('darkmodeOff');

/*function changeDarkMode() {
    const inputElement = document.getElementById("dark-mode-input-element");
    const isDarkModeActive = inputElement.checked;
    const settingsValue = {
        dark_mode: isDarkModeActive
    }
    window.localStorage.setItem('settings', JSON.stringify(settingsValue));
}*/
function changeDarkmode() {
    let darkmode = null;
    if (darkmodeSystemInputElement.checked) {
        darkmode = 'auto';
    }
    if (darkmodeOnInputElement.checked) {
        darkmode = 'on'
    }
    if (darkmodeOffInputElement.checked) {
        darkmode = 'off'
    }
    const settingsValue = {
        dark_mode: darkmode
    };
    window.localStorage.setItem('settings', JSON.stringify(settingsValue));
    loadSettings();
}

function load() {
    const darkmode = JSON.parse(window.localStorage.getItem('settings'))?.dark_mode || null;
    if(darkmode === 'auto' || darkmode === null) {
        darkmodeSystemInputElement.click();
    }
    if(darkmode === 'on') {
        darkmodeOnInputElement.click();
    }
    if(darkmode === 'off') {
        darkmodeOffInputElement.click();
    }
}

window.addEventListener('storage', () => {
    load();
});

load();