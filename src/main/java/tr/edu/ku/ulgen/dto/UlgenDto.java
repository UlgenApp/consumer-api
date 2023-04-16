package tr.edu.ku.ulgen.dto;

import lombok.Data;
import tr.edu.ku.ulgen.util.Location;

/**
 * UlgenDto is a data transfer object class representing the data model
 * for Ulgen entities. It contains information such as user ID, location,
 * the number of active users, and the user's city.
 *
 * @author Kaan Turkmen
 */
@Data
public class UlgenDto {
    private Long userId;
    private Location location;
    private Integer activeUser;
    private String userCity;
}
