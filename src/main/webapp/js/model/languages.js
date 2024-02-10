class Languages {
    static ITALIAN = {id: 1, code: 'it_IT', full: 'italiano'}
    static ENGLISH = {id: 2, code: 'en_EN', full: 'inglese'}
    static FRENCH = {id: 4, code: 'fr_FR', full: 'francese'}
    static SPANISH = {id: 3, code: 'es_ES', full: 'spagnolo'}

    constructor(full) {
        this.lang = Languages.ITALIAN
    }

    toJSON() {
        return {
            _Language__id: 1,
            _Language__code: this.lang['code']
        }
    }
}

$(document).ready(() => {
    /**
     * Listener to text input
     * */
    const languageInput = $("#languages-input-text")
    languageInput.on('input', () => {
        // Suggest skills while input
        let userInput = languageInput.val();
        if (userInput.length > 2) {
            suggestLanguage(userInput)
        }

        // shrink/grow text field on input
        $(this).css({
            'width': 'auto',
            'width': ($(this)[0].scrollWidth) + 'px'
        });
    }).trigger('input');

    function addLanguageTag(language) {
        const locList = $("#language-tags")

        let locTag = $("<li>")
            .addClass('location-tag')
            .attr('name', language['code'])

        let name = $("<p>")
            .addClass('no-select inter-regular')
            .text(language['full'])

        let close = $("<h3>")
            .addClass('inter-regular')
            .text('X')
            .click(function() { // Use a regular function here
                $(this).parent().remove(); // 'this' now refers to the clicked element
            });

        locTag.append(name, close)
        locList.append(locTag)
    }

    function suggestLanguage(input) {
        if ('italiano'.includes(input.toLowerCase()))
            updateLocationSuggestion(Languages.ITALIAN)
        if ('inglese'.includes(input.toLowerCase()))
            updateLocationSuggestion(Languages.ENGLISH)
        if ('spagnolo'.includes(input.toLowerCase()))
            updateLocationSuggestion(Languages.SPANISH)
        if ('francese'.includes(input.toLowerCase()))
            updateLocationSuggestion(Languages.FRENCH)
    }

    function updateLocationSuggestion(lang) {
        addLanguageTag(lang);
    }
})