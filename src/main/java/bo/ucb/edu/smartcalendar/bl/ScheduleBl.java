package bo.ucb.edu.smartcalendar.bl;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bo.ucb.edu.smartcalendar.dto.SmartcalResponse;
import bo.ucb.edu.smartcalendar.entity.Assignation;
import bo.ucb.edu.smartcalendar.entity.Period;
import bo.ucb.edu.smartcalendar.entity.Schedule;
import bo.ucb.edu.smartcalendar.entity.Space;
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

    public void CreateSchedule(String[] period_times, Space space, Date open_date, Date close_date){
        LOGGER.info("Creating schedule");
        for(String period_start_time : period_times){
            List<Period> periods = periodRepository.findByStartTime(period_start_time);
            if (periods == null) {
                LOGGER.error("Error: Periods not found");
                throw new RuntimeException("Periods not found");
            }
            for(Period period : periods){
                Schedule schedule = new Schedule();
                schedule.setSpace(space);
                schedule.setPeriod(period);
                schedule.setOpenDate(open_date);
                schedule.setCloseDate(close_date);
                schedule.setScheduleStatus(true);
                scheduleRepository.save(schedule);
            }
        }
    }

        

    public SmartcalResponse CloseScheduleBySpaceId(Integer space_id){
        LOGGER.info("Closing schedule of space with id " + space_id);
        List<Schedule> schedules = scheduleRepository.findBySpaceId(space_id);
        for(Schedule schedule : schedules){
            List<Assignation> assignations = assignationRepository.findByScheduleId(schedule.getScheduleId());
            for(Assignation assignation : assignations){
                //Set status of assignation to false
                assignation.setAssignationStatus(false);
                assignationRepository.save(assignation);
            }
            schedule.setScheduleStatus(false);
            scheduleRepository.save(schedule);
        }
        SmartcalResponse response = new SmartcalResponse();
        response.setData(schedules.size() + " schedules closed");
        return response;
    }

    public String earliestOpenTime(Space space){
        LOGGER.info("Getting earliest open time of space " + space.getSpaceName());
        return scheduleRepository.findMinStartTimeBySpaceId(space.getSpaceId());
    }

    public String latestCloseTime(Space space){
        LOGGER.info("Getting latest close time of space " + space.getSpaceName());
        return scheduleRepository.findMaxEndTimeBySpaceId(space.getSpaceId());
    }
}
