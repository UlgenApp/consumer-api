package tr.edu.ku.ulgen.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tr.edu.ku.ulgen.entity.UlgenData;

@Repository
public interface UlgenDataRepository extends JpaRepository<UlgenData, Long> {
    UlgenData findByUserId(Long userId);

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

