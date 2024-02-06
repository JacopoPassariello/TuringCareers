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



