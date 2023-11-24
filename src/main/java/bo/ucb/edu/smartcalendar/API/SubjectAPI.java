package bo.ucb.edu.smartcalendar.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import bo.ucb.edu.smartcalendar.bl.SubjectBl;
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
    public SmartcalResponse ListSubjects(){
        LOGGER.info("Called ListSubjects");
        SmartcalResponse response = subjectBl.ListSubjects();
        response.setCode("SUBJ-0000");
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
            response.setCode("SUBJ-0002");
            e.printStackTrace();
        }
        return response;
    }
}
