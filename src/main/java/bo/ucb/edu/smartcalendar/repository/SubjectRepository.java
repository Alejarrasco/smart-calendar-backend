package bo.ucb.edu.smartcalendar.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import bo.ucb.edu.smartcalendar.entity.Subject;

public interface SubjectRepository extends JpaRepository<Subject, Integer> {

    @Query(value = "SELECT * FROM subject WHERE subject_status = 1", nativeQuery = true)
    public List<Subject> findAll();
    
    @Query(value = "SELECT * FROM subject WHERE subject_code = ?1 AND subject_status = 1", nativeQuery = true)
    public Subject findByCode(String code);
}
