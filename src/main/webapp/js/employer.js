$(document).ready(() => {
    const createOfferButton = $("#create-offer-button")
    const offerForm = $("#create-offer-form-wrapper")
    createOfferButton.click(() => {
        offerForm.removeClass('display-none')
    })

    /**
     * Submit
     * */
    const titleInput = $("#offer-title")
    const descriptionInput = $("#offer-description")
    const locationInput = $("#locations-input-text")
    const undoButton = $("#offer-form-undo-button")
    const skillTags = $("#skill-tags .skill-tag")
    const languagesTags = $("#language-tags")

    undoButton.click(() => {
        // TODO: clean up offer form
        // TODO: notify user
        offerForm.addClass('display-none')
    })

    const submitButton = $("#offer-form-submit-button")
    submitButton.click(() => {
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

        // TODO: make ajax post
    })
})