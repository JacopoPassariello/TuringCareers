package com.turing_careers.logic.suggestions;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class LocationMock {
    @JsonProperty("formatted_address")
    private String address;

    @JsonProperty("geometry")
    private Geometry geometry;

    @Getter
    @Setter
    @NoArgsConstructor
    @ToString
    public static class Geometry {
        @JsonProperty("location")
        private LocationDetails location;

        @Getter
        @Setter
        @NoArgsConstructor
        @ToString
        public static class LocationDetails {
            @JsonProperty("lat")
            private Double latitude;

            @JsonProperty("lng")
            private Double longitude;
        }
    }
}
