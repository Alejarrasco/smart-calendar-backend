package bo.ucb.edu.smartcalendar.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import bo.ucb.edu.smartcalendar.entity.Requirement;

public interface RequirementRepository extends JpaRepository<Requirement, Integer> {

    @Query(value = "SELECT * FROM requirement WHERE space_type = ?1 AND requirement_status = 1", nativeQuery = true)
    public List<Requirement> findBySpaceType(String spaceType);
    
    @Query(value = "SELECT MAX(max_alumni) FROM requirement WHERE space_type = ?1 AND requirement_status = 1", nativeQuery = true)
    public Integer findMaximumCapacityBySpaceType(String spaceType);

    @Query(
        value = "SELECT r.* FROM requirement r INNER JOIN subject s ON r.subject_id = s.subject_id WHERE r.requirement_status = 1",
        //s.subject_name IN (SELECT subject_name FROM planification WHERE space_id = ?1 AND start_date <= ?2 AND end_date >= DATE_ADD(?1, INTERVAL 7 DAY) AND assignation_status = 1 AND (solicitude_status = 'APPROVED' OR solicitude_status = 'PENDING')) AND r.requirement_status = 1",
        nativeQuery = true
    )
    public List<Requirement> findBySpaceAndDate(Integer spaceId, String date);
}
