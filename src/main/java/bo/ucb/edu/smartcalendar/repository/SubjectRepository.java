package bo.ucb.edu.smartcalendar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import bo.ucb.edu.smartcalendar.entity.Subject;

public interface SubjectRepository extends JpaRepository<Subject, Integer> {
    
    @Query(value = "SELECT * FROM subject_code WHERE code = ?1", nativeQuery = true)
    public Subject findByCode(String code);
}
