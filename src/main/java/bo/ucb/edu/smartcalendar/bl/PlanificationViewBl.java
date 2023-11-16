package bo.ucb.edu.smartcalendar.bl;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import bo.ucb.edu.smartcalendar.dto.SmartcalResponse;
import bo.ucb.edu.smartcalendar.entity.Period.Weekday;
import bo.ucb.edu.smartcalendar.entity.Period;
import bo.ucb.edu.smartcalendar.entity.Planification;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bo.ucb.edu.smartcalendar.repository.PlanificationViewRepository;
import bo.ucb.edu.smartcalendar.repository.PeriodRepository;

@Service
public class PlanificationViewBl {

    Logger LOGGER = LoggerFactory.getLogger(PlanificationViewBl.class);
    
    @Autowired
    private PlanificationViewRepository planificationViewRepository;

    @Autowired
    private PeriodRepository periodRepository;

    public PlanificationViewBl(PlanificationViewRepository planificationViewRepository, PeriodRepository periodRepository) {
        this.planificationViewRepository = planificationViewRepository;
        this.periodRepository = periodRepository;
    }

    public SmartcalResponse ListAssignationsInCurrentWeekBySpace(Integer spaceId){
        //TODO: Manage exceptions
        //TODO: Validate results
        String lastMonday = getLastMonday();

        List<Planification> nonRecurrentAssignations = planificationViewRepository.ListNonRecurrentAssignationsSinceLastMondayBySpace(lastMonday, spaceId);
        LOGGER.info("Non recurrent assignations: " + nonRecurrentAssignations);
        List<Planification> recurrentAssignations = planificationViewRepository.ListRecurrentAssignationsSinceLastMondayBySpace(lastMonday, spaceId);
        LOGGER.info("Recurrent assignations: " + recurrentAssignations);
        List<Planification> thisWeekAssignations = new ArrayList<Planification>();

        thisWeekAssignations.addAll(nonRecurrentAssignations);
        thisWeekAssignations.addAll(recurrentAssignations);

        //Group by weekday, then period_id
        Weekday[] weekdays = Weekday.values();
        List<Period> periods = periodRepository.findAll();
        HashMap<Weekday, HashMap<Integer, Planification>> assignationsGroupedByDayAndPeriod = new HashMap<Weekday, HashMap<Integer, Planification>>();
        for(Weekday weekday : weekdays){
            HashMap<Integer, Planification> thisDayAssignations = new HashMap<Integer, Planification>();
            for(Period period : periods){
                for(Planification assignation : thisWeekAssignations){
                    if(assignation.getWeekDay() == weekday && assignation.getPeriodId() == period.getPeriodId()){
                        thisDayAssignations.put(period.getPeriodId(), assignation);
                        //TODO si hay choque de horarios este valor se sobrescribe, manejar esta excepción
                    }
                }
            }
            assignationsGroupedByDayAndPeriod.put(weekday, thisDayAssignations);
        }


        SmartcalResponse response = new SmartcalResponse();
        response.setData(assignationsGroupedByDayAndPeriod);
        return response;
    }

    private String getLastMonday(){
        LocalDate currentDate = LocalDate.now();

        LocalDate lastMonday = currentDate;
        while(lastMonday.getDayOfWeek() != DayOfWeek.MONDAY){
            lastMonday = lastMonday.minusDays(1);
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDate = lastMonday.format(formatter);
        LOGGER.info("Last monday: " + formattedDate);
        return formattedDate;
    }
}
