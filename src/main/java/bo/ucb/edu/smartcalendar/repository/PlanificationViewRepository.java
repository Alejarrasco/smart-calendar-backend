package bo.ucb.edu.smartcalendar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import bo.ucb.edu.smartcalendar.entity.Planification;

@NoRepositoryBean
public interface PlanificationViewRepository extends JpaRepository<Planification, Integer> {
    
}
