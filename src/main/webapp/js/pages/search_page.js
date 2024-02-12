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

    /**
     * Search Setup
     * */
    const searchInputDesktop = $("#dsk-search-input")
    const searchButtonDesktop = $("#dsk-search-button")
    const searchInputMobile = $("#mb-searchbar-input")
    const searchButtonMobile = $("#mb-search-button")

    searchButtonMobile.click(() =>{
        let userInput = searchInputMobile.val()
        if (userInput.length > 0) {
            queryOffers(userInput)
        }
    })

    searchButtonDesktop.click(() =>{
        let userInput = searchInputDesktop.val()
        if (userInput.length > 0) {
            queryOffers(userInput)
        }
    })

    function queryOffers(query) {
        // TODO: get developer to send
        // TODO: ajax query
        updateList([1, 2, 3, 4], 'offer')
    }


    function updateList(items, type) {
        const wrapper = $("#item-cards-list");
        wrapper.empty();

        if (items.length > 0) {
            items.forEach((item) => {
               let item_card = $("<div>")
                   .addClass('item-card');

               if (type === 'offer')
                   item_card.append(createOfferCard(item, item_card));
               else if (type === 'developer')
                   item_card.append(createDeveloperCard(item, item_card));

               wrapper.append(item_card)
            });
        } else
            console.log("Empty Item List");
    }

    function createOfferCard(item, card) {
        let headerWrapper = $("<div>")
            .addClass('of-offer-card-header')

        let header = $("<div>")
            .addClass('of-offer-head no-select')
        let employer = $("<h3>")
            .addClass('of-offer-employer-name inter-medium')
            .text('Azienda X')
        let title = $("<h1>")
            .addClass('of-offer-tile inter-bold no-select')
            .text('Software Developer')
        header.append(employer, title)
        headerWrapper.append(header)

        let bookmark = $("<div>")
            .addClass('of-offer-bookmark')
        let saveIcon = $("<i>")
            .addClass('bi bi-bookmark')
        bookmark.append(saveIcon)

        let meta = $("<div>")
            .addClass('of-offer-card-meta inter-regular no-select')
        let location = $("<h3>")
            .text()
        meta.append(location)

        let content = $("<div>")
            .addClass('of-offer-card-content')

        let descriptionWrapper = $("<div>")
            .addClass('of-offer-desc-preview')
        let description = $("<p>")
            .addClass('inter-light')
            .text('Hello World')
        descriptionWrapper.append(description)

        let buttonSection = $("<div>")
            .addClass('of-offer-button')
        let buttonWrapper = $("<div>")
            .addClass('flex-center')
        let button = $("<a>")
            .addClass('inter-bold no-select')
            .text('Dettagli')
        buttonWrapper.append(button)
        buttonSection.append(buttonWrapper)

        content.append(description, buttonSection)

        card.append(headerWrapper, meta, content)
    }

    function createDeveloperCard() {

    }
})
