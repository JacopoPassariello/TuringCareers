class Developer {
    constructor(firstName, lastName, mail, password,loc, skills, langs) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.devMail = mail;
        this.devPassword = password;
        this.loc = loc;
        this.skills = skills;
        this.langs = langs;
    }

    validate() {
        return true;
    }

    toJSON() {
        return {
            _Developer__f_name: this.firstName,
            _Developer__l_name: this.lastName,
            _Developer__mail: this.devMail,
            _Developer__psw: this.devPassword,
            _Developer__location: this.loc,
            _Developer__skills: this.skills,
            _Developer__languages: this.langs,
        };
    }
}