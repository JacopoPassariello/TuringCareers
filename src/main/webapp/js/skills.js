$(document).ready(() => {
    /**
     * Skill Text Input
     * */
    const skillInput = $("#skill-input-text");
    const skillsAutocompleteSection = $("#skills-autocomplete-section");

    skillInput.on('input', () => {
        // Suggest skills while input
        let userInput = skillInput.val();
        if (userInput.length > 0) {
            skillsAutocompleteSection.removeClass('display-none')
            // TODO: query servlet to get matching skills
            let matchingSkills = ['Java', 'Javascript'];
            updateSkillsSuggestions(matchingSkills);
        } else
            skillsAutocompleteSection.addClass('display-none')

        // shrink/grow text field on input
        $(this).css({
            'width': 'auto',
            'width': ($(this)[0].scrollWidth) + 'px'
        });
    }).trigger('input');

    /**
     * Shows available skills
     * @param skills: a not empty list of skills
     * */
    function updateSkillsSuggestions(skills) {
        const skillsSuggestionsContainer = $("#skills-autocomplete-list");
        skillsSuggestionsContainer.empty();

        if (skills.length > 0) {
            skills.forEach((item, index) => {
                let matchingSkill = $("<p>")
                    .text(item)
                    .addClass('inter-regular');
                skillsSuggestionsContainer.append(matchingSkill);
            })
        }
    }

    /**
     * Location Text Input
     * */
})