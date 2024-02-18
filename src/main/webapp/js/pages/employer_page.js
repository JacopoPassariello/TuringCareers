$(document).ready(() => {
    /**
     * Offer Form
     * */
    const offerMain = $("#emp-offers-wrap")
    const createOfferButton = $("#offer-form-open-button")
    const offerForm = $("#emp-offer-form-wrap")
    createOfferButton.click(() => {
        offerForm.removeClass('display-none')
        offerMain.addClass('display-none')
    })

    const undoButton = $("#create-offer-submit")
    const submitButton = $("#create-offer-delete")

    undoButton.click(() => {
        // TODO: clean up offer form
        // TODO: notify user
        offerForm.addClass('display-none')
        offerMain.removeClass('display-none')
    })

    submitButton.click(() => {
        // Make POST request to create offer
        $.ajax({
            url: 'http://localhost:8080/TuringCareers_war/offers',
            method: 'POST',
            data: { offer: getOfferForm() },
            dataType: 'json',
            success: function(response) {
                console.log(response);
            },
            error: function(jqXHR, textStatus, errorThrown) {
                console.error('Error:', textStatus, errorThrown);
            }
        });
    })

    function getOfferForm() {
        const titleInput = $("#offer-title")
        const descriptionInput = $("#offer-description")
        const locationInput = $("#locations-input-text")
        const skillTags = $("#skill-tags .skill-tag")
        const languagesTags = $("#language-tags")

        let title = titleInput.val()
        let description = descriptionInput.val()
        let locType = 'IN_PLACE'
        let location = locationInput.val()

        let skills = []
        skillTags.each(function() {
            let skillText = $(this).find('p').text().trim();
            skills.push(skillText);
        });
        let languages = []
        languagesTags.each(function() {
            let languageText = $(this).find('p').text().trim();
            languages.push(languageText);
        });

        let offer = new Offer(title, description, skills, locType, location, languages)
        console.log(JSON.stringify(offer))
        return offer;
    }


    /**
     * Developer Recommendation
     * */

    function getRecommendedDevelopers(showDevelopers, offer, listWrapper) {
        if ($(showDevelopers).prop('checked')) {
            listWrapper.removeClass('display-none');
            recommendDevelopers(offer, listWrapper)
        } else {
            $(".of-offer-matching-developers").addClass('display-none');
        }
    }

    /**
     * Extracts a list of Developers from a JSON response
     * @param response
     * */
    function extractDeveloper(response) {
        let out = []
        for (let item of response) {
            out.push(new Developer(
                item['_Developer__id'],
                item['_Developer__f_name'] ? item['_Developer__f_name'] : '',
                item['_Developer__l_name'] ? item['_Developer__l_name'] : '',
                item['_Developer__bio'],
                item['_Developer__location'] ? item['_Developer__location']: '',
                '',
                item['_Developer__location'] ? item['_Developer__location'] : '',
                item['_Developer__skills'],
                item['_Developer__languages'] ? item['_Developer__languages'] : ''
            ))
        }
        return out
    }

    /**
     *
     * @param items a list of developers
     * @param wrapper the offer associated with the developers
     * */
    function updateOfferList(items, wrapper) {
        if (items.length == 0) {
            let card = $("<div>")
                .addClass('item-card')
                .append($("<h3>")
                    .addClass('no-select inter-light')
                    .text('No Results Found')
                )
            wrapper.append(card)
        }
        let i = 0
        for (let item of items) {
            if (i < 3) {
                let card = $("<div>")
                    .addClass('item-card')

                let generals = $("<div>")
                    .addClass('dev-generals')
                let locations = $("<h3>")
                    .addClass('dev-location inter-medium')
                    .text(item.loc ? item.loc : '')
                let name = $("<h2>")
                    .addClass('dev-name inter-bold no-select')
                    .text(item.firstName + ' ' + item.lastName)
                generals.append(locations, name)


                let skills = $("<ul>")
                    .addClass('dev-skills')

                let j = 0
                for (skill of item.skills) {
                    if (j <= 3) {
                        let name = $("<li>")
                            .addClass('inter-light no-select')
                            .text(skill['_Skill__name'])
                        skills.append(name)
                        j += 1
                    }
                    else
                        break
                }
                skills.append($("<li>").addClass('inter-ligth no-select').text('...'))

                let content = $("<div>")
                    .addClass('dev-content')

                let descWrapper = $("<div>")
                    .addClass('dev-desc-preview"')
                let desc = $("<p>")
                    .addClass('inter-light no-select')
                    .text(item.bio.slice(0, 40) + '...')
                descWrapper.append(desc)

                let buttonWrapper = $("<div>")
                    .addClass('dev-details-button')
                let btnWrap = $("<div>")
                    .addClass('flex-center')
                let button = $("<a>")
                    .addClass('inter-bold no-select')
                    .text('Dettagli')
                btnWrap.append(button)
                buttonWrapper.append(btnWrap)

                content.append(descWrapper, buttonWrapper)

                card.append(generals, skills, content)
                wrapper.append(card)
                i += 1
            }
            else
                break
        }
    }

    /**
     * Called when Employer asks for recommendation on a specific offer
     * @param offer the offer that must be matched with a list of developers
     * */
    function recommendDevelopers(offer, listWrapper) {
        return $.ajax({
            url: 'http://localhost:8080/TuringCareers_war/...',
            method: 'POST',
            contentType: 'application/json',
            data: JSON.stringify(offer),
            dataType: 'json',
            success: function(response) {
                devs = extractDeveloper(response)
                updateOfferList(devs, listWrapper)
            },
            error: function(jqXHR, textStatus, errorThrown) {

            }
        });
    }

    function addEmployerOffer(offer, wrapper) {
        let card = $("<div>")
            .addClass('item-card fade-in')

        let cardHeaderWrapper = $("<div>")
            .addClass('of-offer-card-header')

        let cardHeader = $("<div>")
            .addClass('of-offer-head no-select')
        let headEmployer = $("<h3>")
            .addClass('of-offer-employer-name inter-medium')
            .text(offer.employer.firstName + ' ' + offer.employer.lastName)
        let headTitle = $("<h2>")
            .addClass('of-offer-tile inter-bold no-select')
            .text(offer.title)
        cardHeader.append(headEmployer, headTitle)

        let developersListWrapper = $("<div>")
            .addClass('of-offer-matching-developers display-none')
        let developerListHeader = $("<h2>")
            .addClass('inter-bold no-select')
            .text('Matching Developers')
        let developerList = $("<div>")
            .addClass('offer-matching-developers-list')
        developersListWrapper.append(developerListHeader, developerList)

        let recommendDevelopersWrap = $("<div>")
            .addClass('show-developers-wrap')
        let recommendDevelopersIcon = $("<p>")
            .addClass('show-dev-button inter-bold')
            .text('+')
        let recommendDevelopersCheckbox = $("<input>")
            .attr('type', 'checkbox')
            .addClass('show-dev-checkbox')
            .on('change', function() {
                getRecommendedDevelopers(this, offer, developersListWrapper)
            })
        recommendDevelopersWrap.append(recommendDevelopersIcon, recommendDevelopersCheckbox)

        cardHeaderWrapper.append(cardHeader, recommendDevelopersWrap)

        let offerMeta = $("<div>")
            .addClass('of-offer-card-meta inter-regular no-select')
        let offerLocation = $("<h3>")
            .text(offer.locType === 'OnSite' ? offer.location.name: offer.locType)
        offerMeta.append(offerLocation)


        let offerContentWrapper = $("<div>")
            .addClass('of-offer-card-content')

        let offerDescPreview = $("<div>")
            .addClass('of-offer-desc-preview')
        let offerDesc = $("<p>")
            .addClass('inter-light no-select')
            .text(offer.description.slice(0, 40) + '...')
        offerDescPreview.append(offerDesc)

        let offerButtonWrapper = $("<div>")
            .addClass('of-offer-button')
        let offerButtonDiv = $("<div>")
            .addClass('flex-center')
        let offerButton = $("<a>")
            .addClass('inter-bold no-select')
            .text('Dettagli')
        offerButtonDiv.append(offerButton)
        offerButtonWrapper.append(offerButtonDiv)
        offerContentWrapper.append(offerDescPreview, offerButtonWrapper)


        card.append(cardHeaderWrapper, offerMeta, offerContentWrapper, developersListWrapper)
        wrapper.append(card)
    }

    /**
     * Corresponds to createOfferCard in search_page.js, loads the Employer offers
     * given an Offers list, in real system this is more complex and require an endpoint
     * @param items a list of offers
     * */
    function showEmployerOffer(items) {
        if (items.length > 0) {
            $("#empty-offer-list").addClass('display-none')
            for (let offer of items) {
                addEmployerOffer(offer, $("#emp-offers-list"))
            }
        }
    }


    // showEmployerOffer()
});