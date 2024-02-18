class Employer {
    constructor(firstName, lastName, mail, password, company) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.empMail = mail;
        this.empPassword = password;
        this.empCompany = company;
    }

    validate() {
        const emailPattern = /^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\.[a-zA-Z0-9-.]+$/;

        for (let key of Object.keys(this)) {
            if (!this[key])
                return false;
            if (key === 'empMail' && !emailPattern.test(this[key]))
                return false
            if (key === 'empPassword') {
                // TODO: test password
            }
        }
        return true;
    }

    toJSON() {
        return {
            _Employer__id: 1,
            _Employer__f_name: this.firstName,
            _Employer__l_name: this.lastName,
            _Employer__mail: this.empMail,
            _Employer__psw: this.empPassword,
            _Employer__company: this.empCompany,
        };
    }
}