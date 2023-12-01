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

    @Query(value = "SELECT s.* FROM subject s INNER JOIN responsible r ON s.subject_id = r.subject_id WHERE r.person_id = ?1 AND s.subject_status = 1 AND r.responsible_status = 1", nativeQuery = true)
    public List<Subject> findByResponsibles(Integer responsibleId);
}
