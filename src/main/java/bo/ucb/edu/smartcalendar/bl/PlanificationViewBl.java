package bo.ucb.edu.smartcalendar.bl;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
        String lastMonday = getLastMonday();
        LOGGER.info("Last monday: " + lastMonday);

        Weekday[] weekdays = Weekday.values();
        HashMap<Weekday, HashMap<String, Planification>> assignationsGroupedByDayAndPeriod = new HashMap<Weekday, HashMap<String, Planification>>();
        String conflicts = "";
        for(Weekday weekday : weekdays){
            HashMap<String, Planification> thisDayAssignations = new HashMap<String, Planification>();
            List<Period> periodsInThisDay = periodRepository.findByWeekday(weekday);
            for(Period period : periodsInThisDay){
                try {
                    List<Planification> assignations = planificationViewRepository.ListAssignationsSinceLastMondayBySpaceAndWeekdayAndPeriod(lastMonday, spaceId, weekday.name(), period.getPeriodId());
                    if (assignations.size() > 1) {
                        // Caso en el que hay más de una asignación en el mismo periodo
                        try {
                            List<Planification> approvedAssignations = planificationViewRepository.ListApprovedAssignationsSinceLastMondayBySpaceAndWeekdayAndPeriod(lastMonday, spaceId, weekday.name(), period.getPeriodId());
                            if (approvedAssignations.size() == 1) {
                                // Caso en el que hay más de una asignación en el mismo periodo pero solo una aprobada
                                thisDayAssignations.put(period.getStartTime().toString(), approvedAssignations.get(0));
                            } else if (approvedAssignations.size() > 1) {
                                // Caso en el que hay más de una asignación en el mismo periodo y más de una aprobada
                                LOGGER.error("Choque de horarios aprobados en "+weekday+" de "+period.getStartTime()+" a "+period.getEndTime());
                                conflicts += "Choque de horarios aprobados en "+weekday+" de "+period.getStartTime()+" a "+period.getEndTime()+ "\n";
                                thisDayAssignations.put(period.getStartTime().toString(), null);
                            } else {
                                // Caso en el que hay más de una asignación en el mismo periodo pero ninguna aprobada
                                LOGGER.warn("Choque de horarios no aprobados en "+weekday+" de "+period.getStartTime()+" a "+period.getEndTime());
                                conflicts += "Choque de horarios no aprobados en "+weekday+" de "+period.getStartTime()+" a "+period.getEndTime()+ "\n";
                                thisDayAssignations.put(period.getStartTime().toString(), null);
                            }

                        } catch (Exception e) {
                            // Caso en el que hay más de una asignación en el mismo periodo pero ninguna aprobada
                            LOGGER.warn("Choque de horarios no aprobados en "+weekday+" de "+period.getStartTime()+" a "+period.getEndTime());
                            conflicts += "Choque de horarios no aprobados en "+weekday+" de "+period.getStartTime()+" a "+period.getEndTime()+ "\n";
                            thisDayAssignations.put(period.getStartTime().toString(), null);
                        }
                        
                    } else if (assignations.size() == 1) {
                        // Caso en el que hay una sola asignación en el mismo periodo
                        thisDayAssignations.put(period.getStartTime().toString(), assignations.get(0));
                    } else {
                        // Caso en el que no hay asignaciones en el mismo periodo
                        thisDayAssignations.put(period.getStartTime().toString(), null);
                    }
                } catch (Exception e) {
                    // Caso en el que no hay ninguna asignación
                    LOGGER.info("No existen asignaciones para el espacio "+spaceId+" en "+weekday+" de "+period.getStartTime()+" a "+period.getEndTime());
                    thisDayAssignations.put(period.getStartTime().toString(), null);
                }
            }
            assignationsGroupedByDayAndPeriod.put(weekday, thisDayAssignations);
        }

        SmartcalResponse response = new SmartcalResponse();
        response.setData(assignationsGroupedByDayAndPeriod);
        if(!conflicts.isEmpty()){
            response.setErrormessage(conflicts);
        }
        return response;
    }

   /*  public SmartcalResponse ListAssignationsInCurrentWeekBySpace(Integer spaceId){
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
        HashMap<Weekday, HashMap<String, Planification>> assignationsGroupedByDayAndPeriod = new HashMap<Weekday, HashMap<String, Planification>>();
        String conflicts = "";
        for(Weekday weekday : weekdays){
            HashMap<String, Planification> thisDayAssignations = new HashMap<String, Planification>();
            List<Period> periodsInThisDay = periodRepository.findByWeekday(weekday);
            for(Period period : periodsInThisDay){
                boolean found = false;
                for(Planification assignation : thisWeekAssignations){
                    //LOGGER.info("assignation weekday: " + assignation.getWeekDay().toString());
                    //LOGGER.info("assignation period: " + assignation.getPeriodId());
                    //LOGGER.info(weekday.toString() + " " + period.getPeriodId());
                    if(assignation.getWeekDay() == weekday && assignation.getPeriodId() == period.getPeriodId()){
                        LOGGER.info("placing assignation: " + assignation.getWeekDay().toString()+ assignation.getPeriodId() + " in weekday: " + weekday + " and period: " + period.getPeriodId());
                        if (!thisDayAssignations.containsKey(period.getStartTime().toString())) {
                            thisDayAssignations.put(period.getStartTime().toString(), assignation);
                        } else {
                            LOGGER.error("Choque de horarios en "+assignation.getWeekDay()+" de "+assignation.getStartTime()+" a "+assignation.getEndTime());
                            conflicts += "Choque de horarios en "+assignation.getWeekDay()+" de "+assignation.getStartTime()+" a "+assignation.getEndTime()+ "\n";
                        }
                        found = true;
                        break;
                        //TODO si hay choque de horarios este valor se sobrescribe, manejar esta excepción
                        //TODO hay alguna manera más simple de hacer esto?
                    } 
                }
                if(!found){
                    //LOGGER.info("placing null in weekday: " + weekday + " and period: " + period.getPeriodId());
                    thisDayAssignations.put(period.getStartTime().toString(), null);
                }
            }
            assignationsGroupedByDayAndPeriod.put(weekday, thisDayAssignations);
        }


        SmartcalResponse response = new SmartcalResponse();
        response.setData(assignationsGroupedByDayAndPeriod);
        if(!conflicts.isEmpty()){
            response.setErrormessage(conflicts);
        }
        return response;
    }

 */    

    public List<Planification> getPlanificationView(Integer spaceId){
        List<Planification> planificationView = planificationViewRepository.getPlanificationView(getLastMonday(),spaceId);
        return planificationView;
    }

    public String getLastMonday(){
        LocalDate currentDate = LocalDate.now();

        LocalDate lastMonday = currentDate;
        while(lastMonday.getDayOfWeek() != DayOfWeek.MONDAY){
            lastMonday = lastMonday.minusDays(1);
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDate = lastMonday.format(formatter);

        return formattedDate;
    }
}
