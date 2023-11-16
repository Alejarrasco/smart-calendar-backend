package bo.ucb.edu.smartcalendar.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import bo.ucb.edu.smartcalendar.entity.Planification;

public interface PlanificationViewRepository extends JpaRepository<Planification, Integer> {
    
    @Query(
        value = "SELECT assignation_id, space_id, space_name, subject_name, first_name, last_name, recurrent, start_date, end_date, period_id, weekday, start_time, end_time FROM planification WHERE recurrent = 0 AND start_date BETWEEN ?1 AND DATE_ADD(?1, INTERVAL 7 DAY) AND space_id = ?2", 
        nativeQuery = true)
    public List<Planification> ListNonRecurrentAssignationsSinceLastMondayBySpace(String lastMondayDate, Integer spaceId);

    @Query(
        value = "SELECT assignation_id, space_id, space_name, subject_name, first_name, last_name, recurrent, start_date, end_date, period_id, weekday, start_time, end_time FROM planification WHERE recurrent = 1 AND start_date <= ?1 AND end_date >= DATE_ADD(?1, INTERVAL 7 DAY) AND space_id = ?2", 
        nativeQuery = true)
    public List<Planification> ListRecurrentAssignationsSinceLastMondayBySpace(String lastMondayDate, Integer spaceId);
}
