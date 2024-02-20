$(document).ready(() => {
    /**
     * Menu Panel Setup
     * */
    const toggle = $("#mb-nav-hamburger-toggle")
    const menu = $("#mb-nav-menu")
    toggle.click(() => {
        menu.toggleClass('display-none flex-center')
    })

    /**
     * Navbar Position
     * */
    const nav = $("#tc-navbar")
    const mbButton = $("#nav-mb-button")
    navbarSetup()
    $(window).on('resize', () => {
        navbarSetup()
    });

    function navbarSetup() {
        if (window.innerWidth >= 992) {
            nav.addClass('sticky-top')
            nav.removeClass('fixed-bottom')
            mbButton.addClass('display-none')
        } else {
            nav.addClass('sticky-top')
        }
    }

    /**
     *
     * */
    let checked = false;
    mbButton.click(() => {
        if (!checked) {
            $(".navbarCollapse").addClass('show-nav')
            $(".navbarCollapse").removeClass('collapse')
            checked = true;
        } else {
            $(".navbarCollapse").removeClass('show-nav')
            $(".navbarCollapse").addClass('collapse')
            checked = false;
        }
    })

    /**
     *
     * */
    const navbarLinksLogged = $("div[type='Logged']")
    const navbarLinksNotLogged = $("div[type='Not Logged']")

    if (checkCookie('SSID')) {
        navbarLinksNotLogged.removeClass('navbar-collapse')
        navbarLinksNotLogged.addClass('display-none')

        navbarLinksLogged.addClass('navbar-collapse')
        navbarLinksLogged.removeClass('display-none')
    } else {
        navbarLinksNotLogged.addClass('navbar-collapse')
        navbarLinksNotLogged.removeClass('display-none')

        navbarLinksLogged.removeClass('navbar-collapse')
        navbarLinksLogged.addClass('display-none')
    }

    /**
     *
     * */
    const navbarLogged = $("#logged-navbar")
    if (checkCookie("TCUT") && getCookieValue("TCUT") === "developer") {
        let searchButton = $("<a>")
            .addClass('nav-item inter-bold nav-link')
            .prop('href', '/TuringCareers_war/search/offers')

        searchButton.append(
            $("<i>")
                .addClass('bi bi-search dsk-account-icon bi-icon-dev hover-dissolve')
        )

        searchButton.text('Search')

        navbarLogged.prepend(searchButton)
    }

    /**
     * Used to check if a cookie exists
     * */
    function checkCookie(cookieName) {
        const cookies = document.cookie.split(';');
        for (let i = 0; i < cookies.length; i++) {
            const cookie = cookies[i].trim();
            if (cookie.indexOf(cookieName + '=') === 0)
                return true;
        }
        return false;
    }

    /**
     * Used to get a cookie value given the name
     * */
    function getCookieValue(cookieName) {
        const cookies = document.cookie.split(';');

        for (let i = 0; i < cookies.length; i++) {
            const cookie = cookies[i].trim();
            if (cookie.startsWith(cookieName + '=')) {
                return cookie.substring(cookieName.length + 1);
            }
        }

        return null;
    }
})