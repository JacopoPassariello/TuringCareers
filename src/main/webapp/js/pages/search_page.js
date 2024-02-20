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
     * Search Listeners
     * */
    const searchInput = $("#searchbar-input")
    const searchButton = $("#search-button")

    searchButton.click(() =>{
        let userInput = searchInput.val()
        if (userInput.length > 0) {
            $("#of-default-recommendations").empty()
            $("#of-list-wrap").removeClass('display-none')
            $("#search-out-header")
                .text('Results for: ')
                .append(
                    $("<p>")
                        .addClass('no-select inter-regular search-out-query pt-3')
                        .text(userInput)
                )
            searchOffers(userInput)
        }
    })

    /**
     *
     * Extract offer data from JSON response.
     * */
    function extractOffers(response) {
        let out = []
        for (let offer of response) {
            let o = new Offer(
                offer['_Offer__title'] ? offer['_Offer__title'].slice(2, -1) : '',
                offer['_Offer__description'] ? offer['_Offer__description'].slice(2, -1) : '',
                offer['_Offer__skills'] ? offer['_Offer__skills'] : '',
                offer['_Offer__location_type'] ? offer['_Offer__location_type'] : '',
                offer['_Offer__location'] ? offer['_Offer__location'] : '',
                offer['_Offer__languages'] ? offer['_Offer__languages'].slice(2, -1) : ''
            )
            o.setEmployer(offer['_Offer__employer'] ? offer['_Offer__employer']: '')
            console.log(o)
            out.push(o)
        }
        console.log(out)
        return out
    }

    /**
     *
     * Called by Search button listener when user makes a search query
     * */
    function searchOffers(query) {
        return $.ajax({
            url: 'http://localhost:8080/TuringCareers_war/search/offers?' +
                'query=' + encodeURIComponent(query),
            method: 'POST',
            success: function(response) {
                updateList(extractOffers(response), $("#item-cards-list"))
            },
            error: function(jqXHR, textStatus, errorThrown) {

            }
        });
    }

    /**
     *
     * Called on page load, request the recommended offers for developer
     * */
    function recommendOffers() {
        return $.ajax({
            url: 'http://localhost:8080/TuringCareers_war/search/offers?' +
                'query=' + encodeURIComponent('RECOMMEND'),
            method: 'POST',
            success: function(response) {
                updateList(extractOffers(response), $("#recommended-cards-list"))
            },
            error: function(jqXHR, textStatus, errorThrown) {

            }
        });
    }

    /**
     * Updates the offers list
     * */
    function updateList(items, wrapper) {
        wrapper.empty();
        if (items.length > 0) {
            items.forEach((item) => {
                let item_card = $("<div>")
                    .addClass('item-card fade-in');

                createOfferCard(item, item_card);
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
            .text(
                (item.employer ? item.employer['_Employer__f_name'] + item.employer['_Employer__l_name'] : '')
            )
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
            .text(item.locType === 'OnSite' ? item.location: item.locType)
        meta.append(location)

        let skills = $("<ul>")
            .addClass('dev-skills')

        let j = 0
        for (let skill of item.skills) {
            if (j <= 3) {
                let name = $("<li>")
                    .addClass('inter-light no-select font-small')
                    .text(skill['_Skill__name'])
                skills.append(name)
                j += 1
            }
            else
                break
        }
        skills.append($("<li>").addClass('inter-ligth no-select').text('...'))

        let content = $("<div>")
            .addClass('of-offer-card-content')

        let descriptionWrapper = $("<div>")
            .addClass('of-offer-desc-preview')
        let description = $("<p>")
            .addClass('inter-light no-select')
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

        card.append(headerWrapper, meta, skills, content)
    }

    recommendOffers()
})
