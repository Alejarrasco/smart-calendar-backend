package bo.ucb.edu.smartcalendar.bl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bo.ucb.edu.smartcalendar.dto.SmartcalResponse;
import bo.ucb.edu.smartcalendar.entity.Assignation;
import bo.ucb.edu.smartcalendar.entity.Period;
import bo.ucb.edu.smartcalendar.entity.Schedule;
import bo.ucb.edu.smartcalendar.entity.Period.Weekday;
import bo.ucb.edu.smartcalendar.repository.AssignationRepository;
import bo.ucb.edu.smartcalendar.repository.PeriodRepository;
import bo.ucb.edu.smartcalendar.repository.ScheduleRepository;

@Service
public class ScheduleBl {
    
    Logger LOGGER = LoggerFactory.getLogger(ScheduleBl.class);


    @Autowired
    private PeriodRepository periodRepository;

    @Autowired
    private ScheduleRepository scheduleRepository;

    @Autowired
    private AssignationRepository assignationRepository;

    public ScheduleBl(PeriodRepository periodRepository, ScheduleRepository scheduleRepository, AssignationRepository assignationRepository) {
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

    public SmartcalResponse CloseScheduleBySpaceId(Integer space_id){
        LOGGER.info("Closing schedule of space with id " + space_id);
        Schedule schedule = scheduleRepository.findBySpaceId(space_id);
        Set<Assignation> assignations = schedule.getAssignations();
        for(Assignation assignation : assignations){
            //Set status of assignation to false
            assignation.setAssignationStatus(false);
            assignationRepository.save(assignation);
        }
        schedule.setScheduleStatus(false);
        SmartcalResponse response = new SmartcalResponse();
        response.setData("Schedule closed");
        return response;
    }

    
}
