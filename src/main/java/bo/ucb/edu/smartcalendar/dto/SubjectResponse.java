package bo.ucb.edu.smartcalendar.dto;

import java.util.Set;

public class SubjectResponse {
    
    private String facultyName;
    private String subjectName;
    private String subjectDescription;
    private String subjectCode;
    private Set<Integer> responsiblesIds;

    
    public SubjectResponse() {
    }


    public String getFacultyName() {
        return facultyName;
    }


    public void setFacultyName(String facultyName) {
        this.facultyName = facultyName;
    }


    public String getSubjectName() {
        return subjectName;
    }


    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }


    public String getSubjectDescription() {
        return subjectDescription;
    }


    public void setSubjectDescription(String subjectDescription) {
        this.subjectDescription = subjectDescription;
    }


    public String getSubjectCode() {
        return subjectCode;
    }


    public void setSubjectCode(String subjectCode) {
        this.subjectCode = subjectCode;
    }


    public Set<Integer> getResponsiblesIds() {
        return responsiblesIds;
    }


    public void setResponsiblesIds(Set<Integer> responsiblesIds) {
        this.responsiblesIds = responsiblesIds;
    }

    
}
