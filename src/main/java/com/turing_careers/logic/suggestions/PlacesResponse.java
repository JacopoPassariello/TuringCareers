package com.turing_careers.logic.suggestions;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.turing_careers.data.model.Location;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;


/**
 * Is used by LocationClient to parse JSON response from Google Places API.
 * @author Antonino Lorenzo
 * */
@Getter
@Setter
public class PlacesResponse {
    @JsonProperty("results")
    private List<PlacesResults> results;

    @Getter
    @Setter
    @NoArgsConstructor
    @ToString
    public static class PlacesResults {
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
            private Coordinates details;


            @Getter
            @Setter
            @NoArgsConstructor
            @ToString
            public static class Coordinates {

                @JsonProperty("lat")
                private Double lat;

                @JsonProperty("lng")
                private Double lon;
            }
        }
    }

    /**
     * Converts the List of PlacesResult to List of Locations.
     * */
    public List<Location> getLocations() {
        List<Location> locations = new ArrayList<>();

        for (PlacesResults loc : this.results) {
            locations.add(
                    new Location(
                            loc.getAddress(),
                            loc.getGeometry().getDetails().getLat(),
                            loc.getGeometry().getDetails().getLon()
                    )
            );
        }
        return locations;
    }
}