package bo.ucb.edu.smartcalendar.dto;

public class ResponsibleRequest {
    
    private Integer[] personsIds;
    private String subjectCode;

    public ResponsibleRequest() {
    }

    public Integer[] getPersonsIds() {
        return personsIds;
    }

    public void setPersonsIds(Integer[] personId) {
        this.personsIds = personId;
    }

    public String getSubjectCode() {
        return subjectCode;
    }

    public void setSubjectCode(String subjectCode) {
        this.subjectCode = subjectCode;
    }
    
}
