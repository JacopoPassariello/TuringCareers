package com.turing_careers.logic.validator;

import com.turing_careers.data.model.Location;
import com.turing_careers.logic.suggestions.LocationClient;

import java.util.List;

public class LocationValidator {
    public static void validateLocation(Location location) throws ValidationException {
        try {
            List<Location> mostLikely = LocationClient.getSuggestions(location.getName());
            for (Location l : mostLikely) {
                System.out.println(l.getName());
            }

            //    throw new ValidationException("Invalid location name!");
        } catch (Exception e) {
            throw new ValidationException(e);
        }
    }
}
