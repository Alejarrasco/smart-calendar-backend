package bo.ucb.edu.smartcalendar.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import bo.ucb.edu.smartcalendar.bl.ScheduleBl;
import bo.ucb.edu.smartcalendar.dto.SmartcalResponse;

@RestController
@RequestMapping(path = "api/v1/period")
public class PeriodAPI {
    
    Logger LOGGER = LoggerFactory.getLogger(PeriodAPI.class);

    @Autowired
    private ScheduleBl scheduleBl;

    public PeriodAPI(ScheduleBl scheduleBl){
        this.scheduleBl = scheduleBl;
    }

    @GetMapping
    public SmartcalResponse ListAllPeriods(){
        SmartcalResponse response = scheduleBl.ListAllPeriodsGroupedByDay();
        response.setCode("SCHE-0000");
        return response;
    }
}
