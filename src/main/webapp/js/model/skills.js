class Skill {
    // TODO: implement if required
}

$(document).ready(() => {

    const skillInput = $("#skill-input-text");
    const skillsAutocompleteSection = $("#skills-autocomplete-section");

    /**
     * Listener to text input
     * */
    skillInput.on('input', () => {
        // Suggest skills while input
        let userInput = skillInput.val();
        if (userInput.length > 2) {
            skillsAutocompleteSection.removeClass('display-none')
            console.log('input: ' + userInput)
            getSkills(userInput).then(matchingSkills => {
                console.log('Matching skills: ')
                console.log(matchingSkills)
                if (matchingSkills)
                    updateSkillsSuggestions(matchingSkills);
                else
                    console.log("Empty skills list")
            }).catch(error => {
                console.error("Error getting skills:", error);
            });
        } else
            skillsAutocompleteSection.addClass('display-none')

        // shrink/grow text field on input
        $(this).css({
            'width': 'auto',
            'width': ($(this)[0].scrollWidth) + 'px'
        });
    }).trigger('input');

    /**
     * Listener to suggestion list items
     * */

    /**
     * Listener to tag close button
     * */


    /**
     * Triggered on item click in suggestions
     * */
    function addSkillTag() {

    }

    /**
     * Triggered on close click in tag
     * */
    function removeSkillTag() {

    }

    /**
     * Triggered on input of Skill
     * @param query: user input
     * */
    function getSkills(query) {
        return $.ajax({
            url: 'http://localhost:8080/TuringCareers_war/suggest-skills',
            data: {skillsQuery: query},
            dataType: 'json'
        });
    }


    /**
     * Shows available skills
     * @param skills: a not empty list of skills
     * */
    function updateSkillsSuggestions(skills) {
        const skillsSuggestionsContainer = $("#skills-autocomplete-list");
        skillsSuggestionsContainer.empty();

        if (skills.length > 0) {
            // remove suggestion error

            skills.forEach((item, index) => {
                let matchingSkill = $("<p>")
                    .text(item)
                    .addClass('inter-regular');
                skillsSuggestionsContainer.append(matchingSkill);
            })
        } else
            noMatchSkillsSuggestions()
    }

    function noMatchSkillsSuggestions() {
        // add suggestion-error class
    }

})