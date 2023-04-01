package tr.edu.ku.ulgen.dto;

import lombok.Data;

@Data
public class UlgenDto {
    private Long userId;
    private Long[] location;
    private Integer activeUser;
    private String userCity;
}
