package bo.ucb.edu.smartcalendar.bl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bo.ucb.edu.smartcalendar.dto.SmartcalResponse;
import bo.ucb.edu.smartcalendar.entity.Period;
import bo.ucb.edu.smartcalendar.entity.Period.Weekday;
import bo.ucb.edu.smartcalendar.repository.PeriodRepository;
import bo.ucb.edu.smartcalendar.repository.ScheduleRepository;

@Service
public class ScheduleBl {
    
    Logger LOGGER = LoggerFactory.getLogger(ScheduleBl.class);


    @Autowired
    private PeriodRepository periodRepository;

    @Autowired
    private ScheduleRepository scheduleRepository;

    public ScheduleBl(PeriodRepository periodRepository, ScheduleRepository scheduleRepository) {
        this.periodRepository = periodRepository;
        this.scheduleRepository = scheduleRepository;
    }    

    public SmartcalResponse ListAllPeriodsGroupedByDay(){
        Weekday[] weekdays = Weekday.values();
        Map<Weekday, List<Period>> periodsGroupedByDay = new HashMap<Weekday, List<Period>>();
        LOGGER.info("Weekdays: " + weekdays);

        for(Weekday weekday : weekdays){
            List<Period> periodsOfDay = periodRepository.findByWeekday(weekday);
            LOGGER.info("Periods of day " + weekday.getWeekday() + ": " + periodsOfDay);
            periodsGroupedByDay.put(weekday, periodsOfDay);
        }

        SmartcalResponse response = new SmartcalResponse();
        response.setData(periodsGroupedByDay);
        return response;
        
    }

    
}
