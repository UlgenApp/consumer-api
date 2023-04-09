package tr.edu.ku.ulgen.dto;

import lombok.Data;
import tr.edu.ku.ulgen.util.Location;

@Data
public class UlgenDto {
    private Long userId;
    private Location location;
    private Integer activeUser;
    private String userCity;
}
