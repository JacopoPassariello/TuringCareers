$(document).ready(() => {
    function requestDeveloperInfo() {
        return $.ajax({
            url: 'http://localhost:8080/TuringCareers_war/user?requestType='
                + encodeURIComponent('info'),
        });
    }

    function getDeveloperInfo() {
        requestDeveloperInfo().then(developerData => {
            const nameElement = $("#developer-name")
            const emailElement = $("#developer-email-p")
            const bioElement = $("#dev-bio")

            let name = developerData['_Developer__f_name'] ?
                developerData['_Developer__f_name'] + ' ' + developerData['_Developer__l_name'] : ''
            let email = developerData['_Developer__mail'] ? developerData['_Developer__mail'] : ''
            let bio = developerData['_Developer__bio'] ? developerData['_Developer__bio'] : ''

            if (bio.length === 0) {
               $("#empty-bio-wrapper").removeClass('display-none')
            } else {
                bioElement.text(bio)
            }

            nameElement.text(name)
            emailElement.text(email)

        }).catch(error => {
            console.error("Error getting employer info:", error);
        });
    }

    getDeveloperInfo()

})