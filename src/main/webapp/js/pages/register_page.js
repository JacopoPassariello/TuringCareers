$(document).ready(() => {
    /**
     * Password Setup
     * */
    const pswToggle = $("#see-psw-toggle")
    const pswIcon = $("#see-psw-icon")
    const pswInput = $("#password-input")
    pswToggle.click(() => {
        if (pswIcon.hasClass('bi-eye-fill')) {
            pswIcon.addClass('bi-eye-slash')
            pswIcon.removeClass('bi-eye-fill')
            pswInput.attr('type', 'text')
        } else {
            pswIcon.addClass('bi-eye-fill')
            pswIcon.removeClass('bi-eye-slash')
            pswInput.attr('type', 'password')
        }
    })

    /**
     * User Type Setup
     * */
    const firstForm = $("#first-form")
    const userTypeForm = $("#user-type-form")
    const userDeveloperButton = $("#account-type-developer")
    const userEmployerButton = $("#account-type-employer")
    let userTypeDeveloper = false;
    let userTypeEmployer = false;

    const secondFormDeveloper = $("#second-form")
    const secondFormEmployer = $("#second-form-employer")
    let secondForm;

    userDeveloperButton.click(() => {
        userTypeForm.addClass('display-none')
        firstForm.removeClass('display-none')
        userTypeDeveloper = true;
        secondForm = secondFormDeveloper;
    })

    userEmployerButton.click(() => {
        userTypeForm.addClass('display-none')
        firstForm.removeClass('display-none')
        userTypeEmployer = true;
        secondForm = secondFormEmployer;
    })


    /**
     * Switch Setup
     * */
    const submitButton = $("#register-submit")
    let switchFormLeft = $(".switch-form-left")
    let switchFormRight = $(".switch-form-right")
    let current = 1
    switchFormRight.click(() => {
        if (current === 1) {
            current = 2
            firstForm.addClass('display-none')
            secondForm.removeClass('display-none')
            switchFormRight.text('')
            submitButton.removeClass('display-none')
        }
    })

    switchFormLeft.click(() => {
        if (current === 2) {
            current = 1
            secondForm.addClass('display-none')
            firstForm.removeClass('display-none')
            switchFormRight.text('chevron_right')
            submitButton.addClass('display-none')
        }
        else if (current === 1) {
            firstForm.addClass('display-none')
            userTypeForm.removeClass('display-none')
        }
    })

    /**
     * Form Submit
     * */
    const developerSubmit = $("#dev-register-submit")
    developerSubmit.click(() => {
        const firstName = $("#firstname").val()
        const lastName = $("#lastname").val()
        const mail = $("#email").val()
        const psw = $("#password-input").val()
        const location = $("#locations-input-text").val()
        const skillTags = $("#skill-tags")

        let skills = []
        skillTags.each(function() {
            let skillText = $(this).find('p').text().trim();
            skills.push(new Skill(skillText));
        });

        let dev = new Developer(firstName, lastName, '', mail, psw, new Location(location), skills, [new Languages('italiano')])
        // console.log('Input Developer: ' + JSON.stringify(dev))

        // TODO: validation should be done inside Developer validate method
        if (!dev.validate()) {
            // handle error
        } else {
            return $.ajax({
                url: 'http://localhost:8080/TuringCareers_war/AuthenticationServlet?' +
                    'userType=' + encodeURIComponent('developer') +
                    '&authType=' + encodeURIComponent('register'),
                method: 'POST',
                contentType: 'application/json',
                data: JSON.stringify(dev),
                dataType: 'json',
                success: function(data, textStatus, jqXHR) {
                    if (jqXHR.getResponseHeader('Location')) {
                        // window.location.href = jqXHR.getResponseHeader('Location');
                    } else {
                        //
                    }
                },
                error: function(jqXHR, textStatus, errorThrown) {
                    console.error('There was a problem with the AJAX request:', errorThrown);
                    // TODO: reload page with error
                }
            });
        }
    });

    /**
     * TODO: cleanup
     * */

    function validateSubForm() {
        let mail = document.forms["form"]["email"].value;
        let password = document.forms["form"]["password"].value;
        let nome = document.forms["form"]["firstname"].value;
        let cognome = document.forms["form"]["lastname"].value;
        if (mail == "" || password == "" || nome == "" || cognome == "") {
            alert("Campi non completi");
            document.getElementById('error').style.display = "block";
            return false;
        }else{
            const emailPattern = /^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\.[a-zA-Z0-9-.]+$/;

            if(!(emailPattern.test(mail))) {
                alert("Mail non valida.");
                return false;
            }
            if(password.toString().length < 6) {
                alert("Password troppo corta (min 6).");
                return false;
            }
            if(password.toString().length > 20) {
                alert("Password troppo lunga (max 20).");
                return false;
            }

            if(nome.toString().length() > 30) {
                alert("Nome troppo lungo (max 30).");
                return false;
            }
            else if(cognome.toString().length() > 30) {
                alert("Cognome troppo lungo (max 30).");
                return false;
            }
            else return CheckPassword(password.toString());
        }

    }

    function validateLoginForm() {
        let x = document.forms["form"]["email"].value;
        let y = document.forms["form"]["password"].value;
        if (x == "" || y == "") {
            alert("Invalid parameters");
            document.getElementById('error').style.display = "block";
            return false;
        }
    }

    function CheckPassword(pass) {
        var check = /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{6,20}$/;
        if(pass.match(check)) return true;
        else {
            alert("Password invalida: deve essere tra i 6 e i 20 caratteri e deve contenere:" +
                "\n  - almeno una minuscola [a-z]" +
                "\n  - almeno una maiuscola [A-Z]" +
                "\n  - almeno un numero [1-9]");
            document.getElementById('error').style.display = "block";
            return false;
        }
    }

})
