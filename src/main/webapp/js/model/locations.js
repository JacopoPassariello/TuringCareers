class Location {
    constructor(name) {
        this.name = name;
    }

    toJSON() {
        return {
            _Location__id: 1,
            _Location__name: this.name,
        }
    }

}


$(document).ready(() => {
    /**
     * Location Text Input
     * */
    const locationInput = $("#locations-input-text");
    const locationAutocompleteSection = $("#locations-autocomplete-section");

    locationInput.on('input', () => {
        let userInput = locationInput.val();
        if (userInput.length >= 3) {
            locationAutocompleteSection.removeClass('display-none')

            getLocations(userInput).then(matchingLocations => {
                updateLocationSuggestions(matchingLocations);
            }).catch(error => {
                console.error("Error getting locations:", error);
            });

        } else
            locationAutocompleteSection.addClass('display-none')
    });

    function getLocations(query) {
        return $.ajax({
            url: 'http://localhost:8080/TuringCareers_war/suggest-locations',
            data: {locationQuery: query},
            dataType: 'json'
        });
    }

    function updateLocationSuggestions(locations) {
        const locationsSuggestionsContainer = $("#locations-autocomplete-list");
        locationsSuggestionsContainer.empty();

        if (locations.length > 0) {
            // remove suggestion error
            let i = 0;
            locations.forEach((item) => {
                if (i <= 5) {
                    let liElement = $("<li>")
                        .addClass('location-suggestion')

                    let icon = $("<i>")
                        .addClass('bi bi-geo-alt-fill')

                    let name = item['_Location__name']
                    let content = $("<p>")
                        .addClass('inter-light')
                        .text(name)

                    liElement.append(icon, content)
                    locationsSuggestionsContainer.append(liElement)
                }
                i += 1;
            })
        } else {
            noMatchLocationSuggestions()
        }
    }

    function noMatchLocationSuggestions() {
        // add suggestion-error class
    }
})