package bo.ucb.edu.smartcalendar.bl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bo.ucb.edu.smartcalendar.dto.RequirementRequest;
import bo.ucb.edu.smartcalendar.dto.ResponsibleRequest;
import bo.ucb.edu.smartcalendar.dto.SmartcalResponse;
import bo.ucb.edu.smartcalendar.dto.SubjectRequest;
import bo.ucb.edu.smartcalendar.dto.SubjectResponse;
import bo.ucb.edu.smartcalendar.entity.Responsible;
import bo.ucb.edu.smartcalendar.entity.Subject;
import bo.ucb.edu.smartcalendar.repository.FacultyRepository;
import bo.ucb.edu.smartcalendar.repository.SubjectRepository;

@Service
public class SubjectBl {
    
    Logger LOGGER = LoggerFactory.getLogger(SubjectBl.class);

    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private FacultyRepository facultyRepository;

    @Autowired
    private PersonBl personBl;

    @Autowired
    private RequirementBl requirementBl;

    public SubjectBl(SubjectRepository subjectRepository, FacultyRepository facultyRepository, PersonBl personBl, RequirementBl requirementBl) {
        this.subjectRepository = subjectRepository;
        this.facultyRepository = facultyRepository;
        this.personBl = personBl;
        this.requirementBl = requirementBl;
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
            List<Responsible> responsibles = personBl.ListResponsibles(subject.getSubjectId());
            Set<Integer> responsiblesIds = new HashSet<Integer>();
            for (Responsible responsible : responsibles) {
                responsiblesIds.add(responsible.getPerson().getPersonId());
            }
            subjectResponse.setResponsiblesIds(responsiblesIds);
            subjectsResponse.add(subjectResponse);
        }

        response.setData(subjectsResponse);
        return response;
    }

    public List<Subject> ListSubjectsByResponsible(Integer responsibleId){
        LOGGER.info("Called ListSubjectsByResponsible");
        List<Subject> subjects;
        try {
            subjects = subjectRepository.findByResponsibles(responsibleId);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error listing subjects by responsible: " + e.getMessage());
        }
        return subjects;
    }

    public SmartcalResponse CreateSubject(SubjectRequest subjectRequest){
        LOGGER.info("Called CreateSubject");
        Subject subject = new Subject();
        SmartcalResponse response = new SmartcalResponse();

        subject.setSubjectName(subjectRequest.getSubjectName());
        subject.setSubjectCode(subjectRequest.getSubjectCode());
        subject.setFaculty(facultyRepository.findByFacultyName(subjectRequest.getFacultyName()));
        subject.setSubjectDescription(subjectRequest.getSubjectDescription());
        subjectRepository.save(subject);
        try {
            personBl.CreateResponsibles(subjectRequest.getResponsiblesIds(), subject);
        } catch (Exception e) {
            // TODO: handle exception
        }
        if (subjectRequest.getRequirements() != null) {
            try {
                requirementBl.CreateRequirements(subjectRequest.getRequirements(), subject);
            } catch (Exception e) {
                // TODO: handle exception
            }
        }

        
        response.setData(subject);
        return response;
    }

    public SmartcalResponse AddRequirement(RequirementRequest requirementRequest){
        return requirementBl.AddRequirement(requirementRequest);
    }

    public SmartcalResponse AssignResponsibles(ResponsibleRequest responsibleRequest){
        LOGGER.info("Called AssignResponsibles");
        SmartcalResponse response = new SmartcalResponse();
        try {
            personBl.CreateResponsibles(responsibleRequest.getPersonsIds(), subjectRepository.findByCode(responsibleRequest.getSubjectCode()));
            response.setData(subjectRepository.findByCode(responsibleRequest.getSubjectCode()));
        } catch (RuntimeException e) {
            LOGGER.error("Error assigning responsibles: " + e.getMessage());
            throw new RuntimeException("Error assigning responsibles: " + e.getMessage());
        }
        return response;
    }


        
}
