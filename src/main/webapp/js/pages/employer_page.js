$(document).ready(() => {
    /**
     * Offer Form Management
     * */
    // Form Container
    const offerMain = $("#emp-offers-wrap")
    const createOfferButton = $("#offer-form-open-button")
    const offerForm = $("#emp-offer-form-wrap")
    // Input fields
    const titleInput = $("input[name='title']")
    const descriptionInput = $("textarea[name='description']")
    const formSkillsList = $("#form-skills-list")
    const formSkillsInput = $("input[name='skill']")
    const locationRemoteRadio = $("input[value='remote']")
    const locationOnsiteRadio = $("input[value='onsite']")
    let locationType;
    const locationOnsiteInput = $("input[name='location']")
    const formLanguageList = $("#form-languages-list")
    const formLanguageInput = $("input[name='language']")
    const languageInputButton = $("#language-input-button")
    // Form Buttons
    const undoButton = $("#create-offer-delete")
    const submitButton = $("#create-offer-submit")

    // Show create offer form
    createOfferButton.click(() => {
        offerForm.removeClass('display-none')
        offerMain.addClass('display-none')
    })

    // Skill input handling
    function addSkillTag(skillName) {
        formSkillsList.prepend($("<li>").text(skillName))
    }

    let requiredSkills = []
    function requestSkills(query) {
        return $.ajax({
            url: 'http://localhost:8080/TuringCareers_war/suggest-skills',
            data: {skillsQuery: query},
            dataType: 'json'
        });
    }

    formSkillsInput.on('input', () => {
        let skillInput = formSkillsInput.val()
        let proposedSkillsList = $("#proposed-skills-list")
        proposedSkillsList.empty()
        proposedSkillsList.removeClass('display-none')

        requestSkills(skillInput).then(matchingSkills => {
            if (matchingSkills) {
                for (let s of matchingSkills) {
                    proposedSkillsList.append(
                        $("<li>")
                            .text(s['_Skill__name'])
                            .click(() => {
                                if (!requiredSkills.includes(s)) {
                                    addSkillTag(s['_Skill__name'])
                                    requiredSkills.push(s)
                                }
                            })
                    )
                }
            } else
                console.log("Empty skills list")
        }).catch(error => {
            console.error("Error getting skills:", error);
        });

    })

    // Location input switch
    locationRemoteRadio.click(() => {
        if (locationRemoteRadio.is(':checked')) {
            locationType = 'Remote'
            locationOnsiteInput.addClass('input-disabled')
            locationOnsiteInput.prop('disabled', true)
        }
    })
    locationOnsiteRadio.click(() => {
        if (locationOnsiteRadio.is(':checked')) {
            locationType = 'OnSite'
            locationOnsiteInput.removeClass('input-disabled')
            locationOnsiteInput.prop('disabled', false)
        }
    });

    // Language input handling
    function addLanguageTag(languageName) {
        formLanguageList.prepend($("<li>").text(languageName))
        getLanguages()

    }

    function getLanguages() {
        let languages = formLanguageList.find('li')
        let output = []
        languages.each(function() {
            output.push($(this).text().trim())
        });
        return output
    }

    languageInputButton.click(() => {
        let languageInput = formLanguageInput.val()
        if (languageInput.length >= 3) {
            addLanguageTag(languageInput)
        }
    })

    // Handle Form Submit
    undoButton.click(() => {
        // TODO: clean up offer form
        // TODO: notify user
        offerForm.addClass('display-none')
        offerMain.removeClass('display-none')
    })

    submitButton.click(() =>{
        let offerJSON = JSON.stringify(getOfferForm())
        $.ajax({
            url: 'http://localhost:8080/TuringCareers_war/offers',
            method: 'POST',
            data: offerJSON,
            contentType: 'application/json',
            dataType: 'json',
            success: function(response) {
                offerForm.addClass('display-none')
                offerMain.removeClass('display-none')
                let newOffer = new Offer(
                    response['_Offer__title'],
                    response['_Offer__description'],
                    response['_Offer__skills'],
                    response['_Offer__location_type'],
                    response['_Offer__location'],
                    response['_Offer__languages']
                )
                addEmployerOffer(newOffer, $("#emp-offers-list"))
            },
            error: function(jqXHR, textStatus, errorThrown) {
                console.error('Error:', textStatus, errorThrown);
            }
        });
    })

    function getOfferForm() {
        let title = titleInput.val()
        let description = descriptionInput.val()
        let languages = getLanguages()
        let languagesFull = []
        for (let l of languages) {
            languagesFull.push(new Languages(l))
        }

        let location = null;
        if (locationType === 'ONSITE') {
            location = locationOnsiteInput.val()
        }

        return new Offer(title, description, requiredSkills, locationType, new Location(location), languagesFull);
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
            let dev = new Developer(
                item['_Developer__f_name'] ? item['_Developer__f_name'] : '',
                item['_Developer__l_name'] ? item['_Developer__l_name'] : '',
                item['_Developer__bio'],
                item['_Developer__mail'] ? item['_Developer__mail']: '',
                '',
                item['_Developer__location'] ? item['_Developer__location'] : '',
                item['_Developer__skills'],
                item['_Developer__languages'] ? item['_Developer__languages'] : ''
            )
            out.push(dev)
        }
        return out
    }

    /**
     *
     * @param items a list of developers
     * @param wrapper the offer associated with the developers
     * */
    function updateOfferList(items, wrapper) {
        if (items.length === 0) {
            let card = $("<div>")
                .addClass('item-card')
                .append($("<h3>")
                    .addClass('no-select inter-light font-small')
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
                    .addClass('dev-content')

                let descWrapper = $("<div>")
                    .addClass('dev-desc-preview"')
                let desc = $("<p>")
                    .addClass('inter-light no-select font-small')
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
     * @param listWrapper
     * */
    function recommendDevelopers(offer, listWrapper) {
        return $.ajax({
            url: 'http://localhost:8080/TuringCareers_war/recommend-developers',
            method: 'POST',
            contentType: 'application/json',
            data: JSON.stringify(offer),
            dataType: 'json',
            success: function(response) {
                let devs = extractDeveloper(response)
                updateOfferList(devs, listWrapper)
            },
            error: function(jqXHR, textStatus, errorThrown) {

            }
        });
    }

    function addEmployerOffer(offer, wrapper) {
        $("#empty-offer-list").addClass('display-none')

        let card = $("<div>")
            .addClass('item-card fade-in')

        let cardHeaderWrapper = $("<div>")
            .addClass('of-offer-card-header')

        let cardHeader = $("<div>")
            .addClass('of-offer-head no-select')
        // TODO: get employer info
        // let headEmployer = $("<h3>")
        //    .addClass('of-offer-employer-name inter-medium')
        //    .text(offer.employer.firstName + ' ' + offer.employer.lastName)
        let headTitle = $("<h2>")
            .addClass('of-offer-tile inter-bold no-select')
            .text(offer.title)
        cardHeader.append(/*headEmployer,*/ headTitle)

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

    function requestEmployerInfo() {
        return $.ajax({
            url: 'http://localhost:8080/TuringCareers_war/user?requestType='
                + encodeURIComponent('info'),
        });
    }
    function getEmployerInfo() {
        requestEmployerInfo().then(employerData => {
            const nameElement = $("#employer-name")
            const emailElement = $("#employer-email-p")
            const companyElement = $("#employer-company-p")

            nameElement.text(employerData['_Employer__f_name'] + ' ' + employerData['_Employer__l_name'])
            emailElement.text(employerData['_Employer__mail'])
            companyElement.text(employerData['_Employer__company'])

            // TODO: add employer offers

        }).catch(error => {
            console.error("Error getting employer info:", error);
        });
    }

    getEmployerInfo()
});