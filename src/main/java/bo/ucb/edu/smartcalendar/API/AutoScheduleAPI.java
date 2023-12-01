package bo.ucb.edu.smartcalendar.api;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import bo.ucb.edu.smartcalendar.bl.OpenAIBl;
import bo.ucb.edu.smartcalendar.dto.SmartcalResponse;

@RestController
@RequestMapping(value = "/api/v1/autoschedule")
public class AutoScheduleAPI {
    
    Logger LOGGER = org.slf4j.LoggerFactory.getLogger(AutoScheduleAPI.class);

    @Autowired
    private OpenAIBl openAIBl;

    public AutoScheduleAPI(OpenAIBl openAIBl) {
        this.openAIBl = openAIBl;
    }

    @PostMapping(path = "/generate")
    public SmartcalResponse GenerateSchedule(@RequestBody String spaceType){
        LOGGER.info("Called GenerateSchedule "+ spaceType);
        SmartcalResponse smartcalResponse = new SmartcalResponse();
        try {
            smartcalResponse = openAIBl.generatePlanificationsAutomatically(spaceType);
            smartcalResponse.setCode("AUTO-3001");
            return smartcalResponse;
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            smartcalResponse.setCode("AUTO-6001");
            smartcalResponse.setErrormessage(e.getMessage());
            return smartcalResponse;
        }
    }
}
