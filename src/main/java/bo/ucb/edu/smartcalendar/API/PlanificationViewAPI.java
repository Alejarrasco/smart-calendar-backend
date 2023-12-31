package bo.ucb.edu.smartcalendar.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import bo.ucb.edu.smartcalendar.bl.PlanificationViewBl;
import bo.ucb.edu.smartcalendar.dto.SmartcalResponse;

@RestController
@RequestMapping(path = "/api/v1/planification")
public class PlanificationViewAPI {

    Logger LOGGER = LoggerFactory.getLogger(PlanificationViewBl.class);
    
    @Autowired
    private PlanificationViewBl planificationViewBl;

    public PlanificationViewAPI(PlanificationViewBl planificationViewBl) {
        this.planificationViewBl = planificationViewBl;
    }

    @GetMapping(path = "/space/{id}")
    public SmartcalResponse ListAssignationsInCurrentWeek(@PathVariable Integer id){
        LOGGER.info("Called ListAssignationsInCurrentWeek");
        SmartcalResponse response = planificationViewBl.ListAssignationsInCurrentWeekBySpace(id);
        response.setCode("PLAN-0000");
        return response;
    }
}
