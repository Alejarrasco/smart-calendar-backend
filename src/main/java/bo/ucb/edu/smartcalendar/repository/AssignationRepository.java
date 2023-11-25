package bo.ucb.edu.smartcalendar.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import bo.ucb.edu.smartcalendar.entity.Assignation;

public interface AssignationRepository extends JpaRepository<Assignation, Integer> {

    @Query(value = "SELECT * FROM assignation WHERE solicitude_id = ?1 AND assignation_status = 1", nativeQuery = true)
    public List<Assignation> findBySolicitudeId(Integer solicitude_id);

    @Query(value = "SELECT * FROM assignation WHERE schedule_id = ?1 AND assignation_status = 1", nativeQuery = true)
    public List<Assignation> findByScheduleId(Integer schedule_id);
}
