package bo.ucb.edu.smartcalendar.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import bo.ucb.edu.smartcalendar.entity.Space;

public interface SpaceRepository extends JpaRepository<Space, Integer> {
    
}
