package com.turing_careers.domain.search;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class LanguageMock {
    @JsonProperty("_Language__id")
    private Long id;

    @JsonProperty("_Language__code")
    private String languageCode;
}
