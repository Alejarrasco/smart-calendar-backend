package bo.ucb.edu.smartcalendar.bl;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import bo.ucb.edu.smartcalendar.dto.SmartcalResponse;
import bo.ucb.edu.smartcalendar.entity.Planification;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bo.ucb.edu.smartcalendar.repository.PlanificationViewRepository;

@Service
public class PlanificationViewBl {
    
    @Autowired
    private PlanificationViewRepository planificationViewRepository;

    public PlanificationViewBl(PlanificationViewRepository planificationViewRepository) {
        this.planificationViewRepository = planificationViewRepository;
    }

    public SmartcalResponse ListAssignationsInCurrentWeekBySpace(Integer spaceId){
        //TODO: Manage exceptions
        //TODO: Validate results
        String lastMonday = getLastMonday();

        List<Planification> nonRecurrentAssignations = planificationViewRepository.ListNonRecurrentAssignationsSinceLastMondayBySpace(lastMonday, spaceId);
        List<Planification> recurrentAssignations = planificationViewRepository.ListRecurrentAssignationsSinceLastMondayBySpace(lastMonday, spaceId);
        List<Planification> thisWeekAssignations = new ArrayList<Planification>();

        thisWeekAssignations.addAll(nonRecurrentAssignations);
        thisWeekAssignations.addAll(recurrentAssignations);

        SmartcalResponse response = new SmartcalResponse();
        response.setData(thisWeekAssignations);
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

        return formattedDate;
    }
}
