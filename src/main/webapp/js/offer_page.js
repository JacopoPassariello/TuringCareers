$(document).ready(() => {
    /**
     * Menu Panel Setup
     * */
    const toggle = $("#of-filter-toggle")
    const menu = $("#of-filter-list-wrap")
    toggle.click(() => {
        menu.toggleClass('display-none flex-center')
    })
})
