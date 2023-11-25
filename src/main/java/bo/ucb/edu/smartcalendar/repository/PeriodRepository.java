package bo.ucb.edu.smartcalendar.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import bo.ucb.edu.smartcalendar.entity.Period;
import bo.ucb.edu.smartcalendar.entity.Period.Weekday;

public interface PeriodRepository extends JpaRepository<Period, Integer> {
    
    public List<Period> findByWeekday(Weekday weekday);

    @Query(value = "SELECT * FROM period WHERE start_time = ?1", nativeQuery = true)
    public List<Period> findByStartTime(String startTime);
}
