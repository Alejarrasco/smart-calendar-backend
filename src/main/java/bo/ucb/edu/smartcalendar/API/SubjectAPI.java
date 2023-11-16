package bo.ucb.edu.smartcalendar.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import bo.ucb.edu.smartcalendar.bl.SubjectBl;
import bo.ucb.edu.smartcalendar.dto.SmartcalResponse;

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
}
