package bo.ucb.edu.smartcalendar.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import bo.ucb.edu.smartcalendar.bl.SubjectBl;
import bo.ucb.edu.smartcalendar.dto.RequirementRequest;
import bo.ucb.edu.smartcalendar.dto.ResponsibleRequest;
import bo.ucb.edu.smartcalendar.dto.SmartcalResponse;
import bo.ucb.edu.smartcalendar.dto.SubjectRequest;

@RestController
@RequestMapping(value = "api/v1/subject")
public class SubjectAPI {
    
    Logger LOGGER = LoggerFactory.getLogger(SubjectAPI.class);

    @Autowired
    private SubjectBl subjectBl;

    public SubjectAPI(SubjectBl subjectBl) {
        this.subjectBl = subjectBl;
    }

    @GetMapping
    public SmartcalResponse ListSubjects(@RequestParam(value = "responsible", required = false) Integer responsibleId){
        LOGGER.info("Called ListSubjects");
        SmartcalResponse response = new SmartcalResponse();
        if (responsibleId != null) {
            LOGGER.info("Called ListSubjects by responsible");
            response.setData(subjectBl.ListSubjectsByResponsible(responsibleId));
            response.setCode("SUBJ-0002");
            
        } else {
            response = subjectBl.ListSubjects();
            response.setCode("SUBJ-0000");
        }
        return response;
    }

    @PostMapping
    public SmartcalResponse CreateSubject(@RequestBody SubjectRequest subjectRequest){
        LOGGER.info("Called CreateSubject");
        SmartcalResponse response = new SmartcalResponse();
        try {
            response = subjectBl.CreateSubject(subjectRequest);
            response.setCode("SUBJ-0001");
        } catch (Exception e) {
            LOGGER.error("Error creating subject: " + e.getMessage());
            response.setCode("SUBJ-6001");
            response.setErrormessage("Error creating subject\n" + e.getMessage());
            e.printStackTrace();
        }
        return response;
    }

    @PostMapping(path = "/requirement")
    public SmartcalResponse AddRequirement(@RequestBody RequirementRequest requirementRequest){
        LOGGER.info("Called AddRequirement");
        SmartcalResponse response = new SmartcalResponse();
        try {
            response = subjectBl.AddRequirement(requirementRequest);
            response.setCode("SUBJ-0003");
        } catch (Exception e) {
            LOGGER.error("Error adding requirement: " + e.getMessage());
            response.setCode("SUBJ-6003");
            response.setErrormessage("Error adding requirement\n" + e.getMessage());
        }
        return response;
    }

    @PostMapping(path = "/responsibles")
    public SmartcalResponse AssignResponsibles(@RequestBody ResponsibleRequest responsibleRequest){
        LOGGER.info("Called CreateResponsibles");
        SmartcalResponse response = new SmartcalResponse();
        try {
            response = subjectBl.AssignResponsibles(responsibleRequest);
            response.setCode("SUBJ-0004");
        } catch (RuntimeException e) {
            response.setCode("SUBJ-6004");
            response.setErrormessage(e.getMessage());
        }
        return response;
    }

    @GetMapping(path = "/faculty")
    public SmartcalResponse ListFaculties(){
        LOGGER.info("Called ListFaculties");
        SmartcalResponse response = new SmartcalResponse();
        try {
            response = subjectBl.ListFaculties();
            response.setCode("SUBJ-0005");
        } catch (RuntimeException e) {
            response.setCode("SUBJ-6005");
            response.setErrormessage(e.getMessage());
        }
        return response;
    }

    @GetMapping(path = "/{subject_id}/responsible")
    public SmartcalResponse ListResponsibles(@PathVariable(value = "subject_id") Integer subjectId){
        LOGGER.info("Called ListResponsibles");
        SmartcalResponse response = new SmartcalResponse();
        try {
            response = subjectBl.ListResponsibles(subjectId);
            response.setCode("SUBJ-0006");
        } catch (RuntimeException e) {
            response.setCode("SUBJ-6006");
            response.setErrormessage(e.getMessage());
        }
        return response;
    }

}
