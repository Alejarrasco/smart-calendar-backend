package bo.ucb.edu.smartcalendar.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import bo.ucb.edu.smartcalendar.entity.Schedule;

public interface ScheduleRepository extends JpaRepository<Schedule, Integer> {
    
}
