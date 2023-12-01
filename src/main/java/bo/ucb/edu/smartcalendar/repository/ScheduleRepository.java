package bo.ucb.edu.smartcalendar.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import bo.ucb.edu.smartcalendar.entity.Schedule;

public interface ScheduleRepository extends JpaRepository<Schedule, Integer> {

    @Query(value = "SELECT * FROM schedule WHERE period_id = ?1 AND space_id = ?2  AND schedule_status = 1", nativeQuery = true)
    public List<Schedule> findByPeriodIdAndSpaceId(Integer periodId, Integer spaceId);

    @Query(value = "SELECT * FROM schedule WHERE schedule_id = ?1", nativeQuery = true)
    public Schedule findByScheduleId(Integer scheduleId);

    @Query(value = "SELECT * FROM schedule WHERE space_id = ?1 AND schedule_status = 1", nativeQuery = true)
    public List<Schedule> findBySpaceId(Integer spaceId);

    @Query(value = "SELECT MIN(p.start_time) FROM schedule s INNER JOIN period p ON s.period_id = p.period_id WHERE s.space_id = ?1 AND s.schedule_status = 1", nativeQuery = true)
    public String findMinStartTimeBySpaceId(Integer spaceId);

    @Query(value = "SELECT MAX(p.end_time) FROM schedule s INNER JOIN period p ON s.period_id = p.period_id WHERE s.space_id = ?1 AND s.schedule_status = 1", nativeQuery = true)
    public String findMaxEndTimeBySpaceId(Integer spaceId);
}
