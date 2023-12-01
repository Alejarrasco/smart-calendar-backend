package bo.ucb.edu.smartcalendar.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import bo.ucb.edu.smartcalendar.entity.Planification;

public interface PlanificationViewRepository extends JpaRepository<Planification, Integer> {
    
    @Query(
        value = "SELECT assignation_id, space_id, space_name, subject_name, first_name, last_name, solicitude_id, solicitude_status, recurrent, start_date, end_date, period_id, weekday, start_time, end_time FROM planification WHERE recurrent = 0 AND start_date BETWEEN ?1 AND DATE_ADD(?1, INTERVAL 7 DAY) AND space_id = ?2 AND assignation_status = 1 AND (solicitude_status = 'APPROVED' OR solicitude_status = 'PENDING')", 
        nativeQuery = true)
    public List<Planification> ListNonRecurrentAssignationsSinceLastMondayBySpace(String lastMondayDate, Integer spaceId);

    @Query(
        value = "SELECT assignation_id, space_id, space_name, subject_name, first_name, last_name, solicitude_id, solicitude_status, recurrent, start_date, end_date, period_id, weekday, start_time, end_time FROM planification WHERE recurrent = 1 AND start_date <= ?1 AND end_date >= DATE_ADD(?1, INTERVAL 7 DAY) AND space_id = ?2 AND assignation_status = 1 AND (solicitude_status = 'APPROVED' OR solicitude_status = 'PENDING')", 
        nativeQuery = true)
    public List<Planification> ListRecurrentAssignationsSinceLastMondayBySpace(String lastMondayDate, Integer spaceId);

    @Query(
        value = "SELECT assignation_id, space_id, space_name, subject_name, first_name, last_name, solicitude_id, solicitude_status, recurrent, start_date, end_date, period_id, weekday, start_time, end_time FROM planification WHERE recurrent = 1 AND start_date <= ?1 AND end_date >= DATE_ADD(?1, INTERVAL 7 DAY) AND space_id = ?2 AND weekday = ?3 AND period_id = ?4 AND assignation_status = 1 AND (solicitude_status = 'APPROVED' OR solicitude_status = 'PENDING')",
        nativeQuery = true
    )
    public List<Planification> ListAssignationsSinceLastMondayBySpaceAndWeekdayAndPeriod(String lastMondayDate, Integer spaceId, String weekday, Integer periodId);

    @Query(
        value = "SELECT assignation_id, space_id, space_name, subject_name, first_name, last_name, solicitude_id, solicitude_status, recurrent, start_date, end_date, period_id, weekday, start_time, end_time FROM planification WHERE start_date <= ?1 AND end_date >= DATE_ADD(?1, INTERVAL 7 DAY) AND space_id = ?2 AND assignation_status = 1 AND (solicitude_status = 'APPROVED' OR solicitude_status = 'PENDING')",
        nativeQuery = true
    )
    public List<Planification> getPlanificationView(String lastMondayDate, Integer spaceId);

    @Query(
        value = "SELECT assignation_id, space_id, space_name, subject_name, first_name, last_name, solicitude_id, solicitude_status, recurrent, start_date, end_date, period_id, weekday, start_time, end_time FROM planification WHERE start_date <= ?1 AND end_date >= DATE_ADD(?1, INTERVAL 7 DAY) AND space_id = ?2 AND weekday = ?3 AND period_id = ?4 AND assignation_status = 1 AND solicitude_status = 'APPROVED'",
        nativeQuery = true
    )
    public List<Planification> ListApprovedAssignationsSinceLastMondayBySpaceAndWeekdayAndPeriod(String lastMondayDate, Integer spaceId, String weekday, Integer periodId);
}
