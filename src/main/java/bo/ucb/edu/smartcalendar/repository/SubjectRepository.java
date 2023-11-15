package bo.ucb.edu.smartcalendar.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import bo.ucb.edu.smartcalendar.entity.Subject;

public interface SubjectRepository extends JpaRepository<Subject, Integer> {
    
}
