class Skill {
    constructor(skill, type) {
        this.skill = skill;
        this.type = type;
    }

    toJSON() {
        return {
            _Skill__id: null,
            _Skill__name: this.skill,
            _Skill__type: this.type
        }
    }
}
/*
$(document).ready(() => {

    const skillInput = $("input[name='skill']");
    const skillsAutocompleteSection = $("#proposed-skills-list");

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
    function getSkills(query) {
        return $.ajax({
            url: 'http://localhost:8080/TuringCareers_war/suggest-skills',
            data: {skillsQuery: query},
            dataType: 'json'
        });
    }

    function updateSkillsSuggestions(skills) {
        const skillsSuggestionsContainer = $("#proposed-skills-list");
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
*/