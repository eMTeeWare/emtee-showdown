function loadDarkmode(settings) {
    const darkmode = settings?.dark_mode ? settings?.dark_mode : 'auto';

    const loadDarkmode = (isDarkmodeOn) => {
        if (isDarkmodeOn) {
            document.body.classList.add('dark-mode');
        } else {
            document.body.classList.remove('dark-mode');
        }
    }

    if(darkmode === 'auto') {
        window.matchMedia('(prefers-color-scheme: dark)').addEventListener('change', event => loadDarkmode(event.matches));
        loadDarkmode(window.matchMedia('(prefers-color-scheme: dark)').matches);
    } else {
        loadDarkmode(darkmode === 'on');
    }
}

function loadSettings() {
    const settings = JSON.parse(window.localStorage.getItem('settings'));
    loadDarkmode(settings);
}

window.addEventListener('storage', () => {
   loadSettings();
});

loadSettings();