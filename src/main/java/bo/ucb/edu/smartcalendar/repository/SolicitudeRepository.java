package bo.ucb.edu.smartcalendar.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import bo.ucb.edu.smartcalendar.entity.Solicitude;

public interface SolicitudeRepository extends JpaRepository<Solicitude, Integer> {
    
}
