$(document).ready(() => {
    /**
     * Location Text Input
     * */
    const locationInput = $("#locations-input-text");
    const locationAutocompleteSection = $("#locations-autocomplete-section");

    locationInput.on('input', () => {
        let userInput = locationInput.val();
        if (userInput.length > 0) {
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
        return new Promise((resolve, reject) => {
            $.get(
                '/suggest-locations',
                {locationQuery: query},
                (response) => {
                    console.log(response)
                    return response
                }
            ).fail((jqXHR, textStatus, errorThrown) => {
                console.error("Request failed: " + textStatus + ", " + errorThrown);
            })
        });
    }

    function updateLocationSuggestions(locations) {
        const locationsSuggestionsContainer = $("#locations-autocomplete-list");
        locationsSuggestionsContainer.empty();

        if (locations.length > 0) {
            locations.forEach((item) => {
                console.log('Item: \n' + item)
            })
        }
    }
})