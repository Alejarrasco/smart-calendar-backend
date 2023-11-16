package bo.ucb.edu.smartcalendar.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import bo.ucb.edu.smartcalendar.entity.Period;
import bo.ucb.edu.smartcalendar.entity.Period.Weekday;

public interface PeriodRepository extends JpaRepository<Period, Integer> {
    
    public List<Period> findByWeekday(Weekday weekday);
}
