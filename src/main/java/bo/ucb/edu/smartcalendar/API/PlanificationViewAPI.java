package bo.ucb.edu.smartcalendar.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import bo.ucb.edu.smartcalendar.bl.PlanificationViewBl;
import bo.ucb.edu.smartcalendar.dto.SmartcalResponse;

@RestController
@RequestMapping(path = "/api/v1/planification")
public class PlanificationViewAPI {
    
    @Autowired
    private PlanificationViewBl planificationViewBl;

    public PlanificationViewAPI(PlanificationViewBl planificationViewBl) {
        this.planificationViewBl = planificationViewBl;
    }

    @GetMapping(path = "/current-week")
    public SmartcalResponse ListAssignationsInCurrentWeek(){
        SmartcalResponse response = planificationViewBl.ListAssignationsInCurrentWeek();
        response.setCode("PLAN-0000");
        return response;
    }
}