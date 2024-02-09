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
     * Switch Setup
     * */
    const userTypeForm = $("#user-type-form")
    const userDeveloperButton = $("#account-type-employer")
    const userEmployerButton = $("#account-type-developer")
    let userTypeDeveloper = false;
    let userTypeEmployer = false;

    userDeveloperButton.click(() => {
        userTypeForm.addClass('display-none')
        firstForm.removeClass('display-none')
        userTypeDeveloper = true;
    })

    userEmployerButton.click(() => {
        userTypeForm.addClass('display-none')
        firstForm.removeClass('display-none')
        userTypeEmployer = true;
    })


    const firstForm = $("#first-form")
    const secondForm = $("#second-form")
    let switchFormLeft = $(".switch-form-left")
    let switchFormRight = $(".switch-form-right")
    let current = 1
    switchFormRight.click(() => {
        if (current === 1) {
            current = 2
            firstForm.addClass('display-none')
            secondForm.removeClass('display-none')
        }
    })

    switchFormLeft.click(() => {
        if (current === 2) {
            current = 1
            secondForm.addClass('display-none')
            firstForm.removeClass('display-none')
        }
        else if (current === 1) {
            firstForm.addClass('display-none')
            userTypeForm.removeClass('display-none')
        }
    })

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
