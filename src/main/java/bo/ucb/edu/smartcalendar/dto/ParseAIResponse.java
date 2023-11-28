package bo.ucb.edu.smartcalendar.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ParseAIResponse {

    @JsonProperty
    public String space;


    public List<Schedule> schedules;


    public class Schedule {
        @JsonProperty
        public String subject_code;

        public List<AssignedHour> assigned_hours;
        
        public class AssignedHour {
            @JsonProperty
            public String day;
            @JsonProperty
            public String start_time;
            @JsonProperty
            public String end_time;
            public String getDay() {
                return day;
            }
            public void setDay(String day) {
                this.day = day;
            }
            public String getStart_time() {
                return start_time;
            }
            public void setStart_time(String start_time) {
                this.start_time = start_time;
            }
            public String getEnd_time() {
                return end_time;
            }
            public void setEnd_time(String end_time) {
                this.end_time = end_time;
            }
            
        }

        public String getSubject_code() {
            return subject_code;
        }

        public void setSubject_code(String subject_code) {
            this.subject_code = subject_code;
        }

        public List<AssignedHour> getAssigned_hours() {
            return assigned_hours;
        }

        public void setAssigned_hours(List<AssignedHour> assigned_hours) {
            this.assigned_hours = assigned_hours;
        }

        
    }


    public String getSpace() {
        return space;
    }


    public void setSpace(String space) {
        this.space = space;
    }


    public List<Schedule> getSchedules() {
        return schedules;
    }


    public void setSchedules(List<Schedule> schedules) {
        this.schedules = schedules;
    }

    
}
