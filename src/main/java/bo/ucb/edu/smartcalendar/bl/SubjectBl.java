package bo.ucb.edu.smartcalendar.bl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bo.ucb.edu.smartcalendar.api.SubjectAPI;
import bo.ucb.edu.smartcalendar.dto.SmartcalResponse;
import bo.ucb.edu.smartcalendar.dto.SubjectResponse;
import bo.ucb.edu.smartcalendar.entity.Responsible;
import bo.ucb.edu.smartcalendar.entity.Subject;
import bo.ucb.edu.smartcalendar.repository.FacultyRepository;
import bo.ucb.edu.smartcalendar.repository.SubjectRepository;

@Service
public class SubjectBl {
    
    Logger LOGGER = LoggerFactory.getLogger(SubjectAPI.class);

    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private FacultyRepository facultyRepository;

    public SubjectBl(SubjectRepository subjectRepository, FacultyRepository facultyRepository) {
        this.subjectRepository = subjectRepository;
        this.facultyRepository = facultyRepository;
    }

    public SmartcalResponse ListSubjects(){
        LOGGER.info("Called ListSubjects");
        List<Subject> subjects = subjectRepository.findAll();
        List<SubjectResponse> subjectsResponse = new ArrayList<SubjectResponse>();
        SmartcalResponse response = new SmartcalResponse();

        for (Subject subject : subjects) {
            SubjectResponse subjectResponse = new SubjectResponse();
            subjectResponse.setFacultyName(subject.getFaculty().getFacultyName());
            subjectResponse.setSubjectName(subject.getSubjectName());
            subjectResponse.setSubjectCode(subject.getSubjectCode());
            Set<Integer> responsiblesIds = new HashSet<Integer>();
            for (Responsible responsible : subject.getResponsibles()) {
                responsiblesIds.add(responsible.getPerson().getPersonId());
            }
            subjectResponse.setResponsiblesIds(responsiblesIds);
            subjectsResponse.add(subjectResponse);
        }

        response.setData(subjectsResponse);
        return response;
    }
        
}
