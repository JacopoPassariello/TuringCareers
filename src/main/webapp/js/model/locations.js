class Location {
    constructor(name) {
        this.name = name;
    }

    toJSON() {
        return {
            _Location__id: null,
            _Location__name: this.name,
        }
    }

}