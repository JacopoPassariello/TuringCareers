$(document).ready(() => {
    /**
     * Menu Panel Setup
     * */
    const toggle = $("#of-filter-toggle")
    const menu = $("#of-filter-list-wrap")
    toggle.click(() => {
        menu.toggleClass('display-none flex-center')
    })

    /**
     * Filters Setup
     * */
    const language_toggle = $("#of-filter-language-toggle")
    const language_arrow = $("#of-filter-language-toggle-icon")
    const language_list = $("#of-filter-language-ul")
    language_toggle.click(() => {
        language_list.toggleClass('display-none')

        if (language_toggle.is(':checked')) {
            language_arrow.text('expand_less')
        }
        else {
            language_arrow.text('expand_more')
        }
    })

})
