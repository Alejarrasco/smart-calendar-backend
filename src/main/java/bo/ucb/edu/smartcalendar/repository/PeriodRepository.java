package bo.ucb.edu.smartcalendar.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import bo.ucb.edu.smartcalendar.entity.Period;

public interface PeriodRepository extends JpaRepository<Period, Integer> {
    
}
