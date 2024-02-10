class Offer {
    constructor(title, description, skills, locType, loc, languages) {
        this.title = title;
        this.description = description;
        this.skills = skills;
        this.languages = languages;

        this.locType = locType;
        if (locType === 'IN_PLACE')
            this.location = loc;
        else
            this.location = undefined;
    }

    validate() {
        // TODO: implement client side validation of offers
        return true;
    }

    toJSON() {
        return {
            _Offer__title: this.title,
            _Offer__description: this.description,
            _Offer__state: 'ACTIVE',
            _Offer_location_type: this.locType,
            _Offer_location: this.location,
            _Offer_employer: '',
            _Offer_languages: this.languages,
            _Offer__skills: this.skills,
        };
    }

}