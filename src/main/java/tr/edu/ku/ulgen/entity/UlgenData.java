package tr.edu.ku.ulgen.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * UlgenData is an entity class representing the data model
 * for Ulgen entities stored in the database. It contains information
 * such as user ID, number of active users, latitude, longitude, and user's city.
 *
 * @author Kaan Turkmen
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ulgen-data")
public class UlgenData {

    @Id
    private Long userId;
    private Integer activeUsers;
    private Double latitude;
    private Double longitude;
    private String userCity;
}
