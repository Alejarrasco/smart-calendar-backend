package bo.ucb.edu.smartcalendar.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import bo.ucb.edu.smartcalendar.entity.Requirement;

public interface RequirementRepository extends JpaRepository<Requirement, Integer> {

    @Query(value = "SELECT * FROM requirement WHERE space_type = ?1 AND requirement_status = 1", nativeQuery = true)
    List<Requirement> findBySpaceType(String spaceType);
    
    @Query(value = "SELECT MAX(max_alumni) FROM requirement WHERE space_type = ?1 AND requirement_status = 1", nativeQuery = true)
    Integer findMaximumCapacityBySpaceType(String spaceType);
}
