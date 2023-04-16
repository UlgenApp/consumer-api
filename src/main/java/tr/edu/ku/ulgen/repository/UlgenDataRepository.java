package tr.edu.ku.ulgen.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tr.edu.ku.ulgen.entity.UlgenData;

/**
 * UlgenDataRepository is an interface for performing CRUD operations
 * on the UlgenData entities. It extends JpaRepository, inheriting its
 * default methods.
 *
 * @author Kaan Turkmen
 */
@Repository
public interface UlgenDataRepository extends JpaRepository<UlgenData, Long> {

    /**
     * Retrieves an UlgenData entity by user ID.
     *
     * @param userId the user ID for which the UlgenData entity should be retrieved.
     * @return an UlgenData instance matching the given user ID, or null if not found.
     */
    UlgenData findByUserId(Long userId);

    /**
     * Saves a new UlgenData entity or updates an existing one based on the user ID.
     * If an UlgenData entity with the same user ID exists, it updates the existing
     * entity. Otherwise, it saves a new entity with the provided data.
     *
     * @param ulgenData the UlgenData instance to be saved or updated.
     */
    default void saveOrUpdate(UlgenData ulgenData) {
        UlgenData existingData = findByUserId(ulgenData.getUserId());
        if (existingData == null) {
            save(ulgenData);
            System.out.println(ulgenData + " saved as new entry.");
        } else {
            existingData.setActiveUsers(ulgenData.getActiveUsers());
            existingData.setLatitude(ulgenData.getLatitude());
            existingData.setLongitude(ulgenData.getLongitude());
            save(existingData);
            System.out.println(ulgenData + " is updated.");
        }
    }
}

