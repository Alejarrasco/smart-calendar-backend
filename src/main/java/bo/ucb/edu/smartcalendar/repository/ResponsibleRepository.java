package bo.ucb.edu.smartcalendar.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import bo.ucb.edu.smartcalendar.entity.Responsible;

public interface ResponsibleRepository extends JpaRepository<Responsible, Integer> {
    
}
