package bo.ucb.edu.smartcalendar.dto;

import java.util.List;

public class SolicitudeRequest {
    
    private String subjectCode;
    private Integer personId;
    private boolean recurrent;
    private String startDate;
    private String endDate;
    private List<Integer> periods;
    private Integer spaceId;

    public SolicitudeRequest() {
    }

    public String getSubjectCode() {
        return subjectCode;
    }

    public void setSubjectCode(String subjectCode) {
        this.subjectCode = subjectCode;
    }

    public Integer getPersonId() {
        return personId;
    }

    public void setPersonId(Integer personId) {
        this.personId = personId;
    }

    public boolean isRecurrent() {
        return recurrent;
    }

    public void setRecurrent(boolean recurrent) {
        this.recurrent = recurrent;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public List<Integer> getPeriods() {
        return periods;
    }

    public void setPeriods(List<Integer> periods) {
        this.periods = periods;
    }

    public Integer getSpaceId() {
        return spaceId;
    }

    public void setSpaceId(Integer spaceId) {
        this.spaceId = spaceId;
    }

    @Override
    public String toString() {
        if (recurrent){
            return "Requesting solicitude for subject "+subjectCode+" by person "+personId+" from "+startDate+" to "+endDate+" in space "+spaceId+" for periods "+periods;
        } else {
            return "Requesting solicitude for subject "+subjectCode+" by person "+personId+" on "+startDate+" in space "+spaceId+" for periods "+periods;
        }
    }
}
