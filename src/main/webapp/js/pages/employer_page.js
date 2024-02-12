$(document).ready(() => {
    const createOfferButton = $("#create-offer-button")
    const offerForm = $("#create-offer-form-wrapper")
    createOfferButton.click(() => {
        offerForm.removeClass('display-none')
    })

    const undoButton = $("#offer-form-undo-button")
    const submitButton = $("#offer-form-submit-button")

    undoButton.click(() => {
        // TODO: clean up offer form
        // TODO: notify user
        offerForm.addClass('display-none')
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
})