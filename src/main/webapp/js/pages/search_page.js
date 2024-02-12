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
            console.log('Query: ' + userInput)
            queryOffers(userInput)
        }
    })

    function queryOffers(query) {
        // TODO: ajax query
        return $.ajax({
            url: 'http://localhost:8080/TuringCareers_war/search/offers?' +
                'query=' + encodeURIComponent(query),
            method: 'POST',
            contentType: 'application/json',
            data: JSON.stringify(''),
            dataType: 'json',
            success: function(response) {
                let devs = []
                for (let item of response) {

                    devs.push(new Offer(
                        item['_Offer__title'] ? item['_Offer__title'].slice(2, -1) : '',
                        item['_Offer__description'] ? item['_Offer__description'].slice(2, -1) : '',
                        item['_Offer__skills'] ? item['_Offer__skills'].slice(2, -1) : '',
                        item['_Offer__location_type'] ? item['_Offer__location_type'].slice(2, -1) : '',
                        item['_Offer__location'],
                        item['_Offer__languages'] ? item['_Offer__languages'].slice(2, -1) : ''
                    ))
                }
                updateList(devs, 'offer')
            },
            error: function(jqXHR, textStatus, errorThrown) {

            }
        });
    }


    function updateList(items, type) {
        const wrapper = $("#item-cards-list");
        wrapper.empty();

        if (items.length > 0) {
            items.forEach((item) => {
               let item_card = $("<div>")
                   .addClass('item-card');

               if (type === 'offer')
                   createOfferCard(item, item_card);
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
            .text(item.title)
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
            .text(item.loc)
        meta.append(location)

        let content = $("<div>")
            .addClass('of-offer-card-content')

        let descriptionWrapper = $("<div>")
            .addClass('of-offer-desc-preview')
        let description = $("<p>")
            .addClass('inter-light')
            .text(item.description.slice(0, 40) + '...')
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
