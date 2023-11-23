package bo.ucb.edu.smartcalendar.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import bo.ucb.edu.smartcalendar.entity.Schedule;

public interface ScheduleRepository extends JpaRepository<Schedule, Integer> {

    @Query(value = "SELECT * FROM schedule WHERE period_id = ?1 AND space_id = ?2", nativeQuery = true)
    public List<Schedule> findByPeriodIdAndSpaceId(Integer periodId, Integer spaceId);

    public Schedule findByScheduleId(Integer scheduleId);

    @Query(value = "SELECT * FROM schedule WHERE space_id = ?1", nativeQuery = true)
    public Schedule findBySpaceId(Integer spaceId);
}
