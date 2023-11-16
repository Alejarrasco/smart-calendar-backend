package bo.ucb.edu.smartcalendar.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import bo.ucb.edu.smartcalendar.entity.CalendarAccess;

public interface CalendarAccessRepository extends JpaRepository<CalendarAccess, Integer> {
    
}
