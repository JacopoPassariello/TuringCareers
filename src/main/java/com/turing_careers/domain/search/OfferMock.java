package com.turing_careers.domain.search;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.turing_careers.data.model.Language;
import com.turing_careers.data.model.Location;
import com.turing_careers.data.model.Skill;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@ToString
@Getter
public class OfferMock {
    @JsonProperty("_Offer__id")
    private long offerId;

    @JsonProperty("_Offer__title")
    private String offerTitle;

    @JsonProperty("_Offer__state")
    private String offerState;

    @JsonProperty("_Offer__description")
    private String offerDescription;

    @JsonProperty("_Offer__location_type")
    private String offerLocationType;

    @JsonProperty("_Offer__location")
    private LocationMock offerLocation;

    @JsonProperty("_Offer__skills")
    private List<SkillMock> offerSkills;

    @JsonProperty("_Offer__languages")
    private  List<LanguageMock> offerLanguages;
}
