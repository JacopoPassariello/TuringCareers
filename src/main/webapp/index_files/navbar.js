$(document).ready(() => {
    /**
     * Menu Panel Setup
     * */
    const toggle = $("#mb-nav-hamburger-toggle")
    const menu = $("#mb-nav-menu")
    toggle.click(() => {
        menu.toggleClass('display-none flex-center')
    })
})