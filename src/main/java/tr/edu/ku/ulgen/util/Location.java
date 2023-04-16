package tr.edu.ku.ulgen.util;

import lombok.Data;

/**
 * Location is a utility class that represents a geographic location
 * with latitude and longitude coordinates.
 *
 * @author Kaan Turkmen
 */
@Data
public class Location {
    private Double latitude;
    private Double longitude;
}
