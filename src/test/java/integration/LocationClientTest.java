package integration;

import com.turing_careers.data.model.Location;
import com.turing_careers.logic.suggestions.LocationClient;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class LocationClientTest {

    @Test
    public void testGetSuggestions() throws Exception {
        List<Location> locations = LocationClient.getSuggestions("Rom");
        Assertions.assertFalse(locations.isEmpty());
    }
}
