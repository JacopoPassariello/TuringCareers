class Skill {
    constructor(id, skill, type) {
        this.id = id;
        this.skill = skill;
        this.type = type;
    }

    toJSON() {
        return {
            _Skill__id: this.id,
            _Skill__name: this.skill,
            _Skill__type: this.type
        }
    }
}

$(document).ready(() => {

    const skillInput = $("#skill-input-text");
    const skillsAutocompleteSection = $("#skills-autocomplete-section");

    /**
     * Listener to text input
     * */
    skillInput.on('input', () => {
        // shrink/grow text field on input
        skillInput.css({
            'width': ($(this)[0].scrollWidth) + 'px'
        });

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
    function addSkillTag(skill) {
        const skillTags = $("#skill-tags")

        let skillTag = $("<li>")
            .addClass('skill-tag');

        let name = $("<p>")
            .addClass('no-select inter-regular')
            .text(skill)

        let close = $("<h3>")
            .addClass('inter-regular')
            .text('X')
            .click(function() {
                $(this).parent().remove();
            });

        skillTag.append(name, close)
        skillTags.append(skillTag)
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
                    .addClass('inter-regular')
                    .click(function() {
                        addSkillTag(item)
                    });

                skillsSuggestionsContainer.append(matchingSkill);
            })
        } else
            noMatchSkillsSuggestions()
    }

    function noMatchSkillsSuggestions() {
        // add suggestion-error class
    }

})