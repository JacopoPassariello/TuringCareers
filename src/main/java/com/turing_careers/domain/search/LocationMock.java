package com.turing_careers.domain.search;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class LocationMock {

    @JsonProperty("_Location__id")
    private Long id;

    @JsonProperty("_Location__name")
    private String name;

    @JsonProperty("_Location__lat")
    private String lat;

    @JsonProperty("_Location__lon")
    private String lon;
}
