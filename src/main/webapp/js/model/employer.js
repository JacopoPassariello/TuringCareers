class Employer {
    constructor(firstName, lastName, mail, password, company) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.empMail = mail;
        this.empPassword = password;
        this.empCompany = company;
    }

    validate() {
        return true;
    }

    toJSON() {
        return {
            _Employer__f_name: this.firstName,
            _Employer__l_name: this.lastName,
            _Employer__mail: this.empMail,
            _Employer__psw: this.empPassword,
            _Employer__company: this.empCompany,
        };
    }
}