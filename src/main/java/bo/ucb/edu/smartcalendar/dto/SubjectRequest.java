package bo.ucb.edu.smartcalendar.dto;

public class SubjectRequest {
    
    private String subjectName;
    private String subjectCode;
    private String facultyName;
    private String subjectDescription;
    private Integer[] responsiblesIds;
    private RequirementRequest[] requirements;

    public SubjectRequest() {
    }

    public SubjectRequest(String subjectName, String subjectCode, String facultyName, String subjectDescription,Integer[] responsiblesIds, RequirementRequest[] requirements) {
        this.subjectName = subjectName;
        this.subjectCode = subjectCode;
        this.facultyName = facultyName;
        this.subjectDescription = subjectDescription;
        this.responsiblesIds = responsiblesIds;
        this.requirements = requirements;
    }

    public String getSubjectName() {
        return this.subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }
    
    public String getSubjectCode() {
        return this.subjectCode;
    }

    public void setSubjectCode(String subjectCode) {
        this.subjectCode = subjectCode;
    }
    
    public String getFacultyName() {
        return this.facultyName;
    }

    public void setFacultyName(String facultyName) {
        this.facultyName = facultyName;
    }

    public String getSubjectDescription() {
        return this.subjectDescription;
    }

    public void setSubjectDescription(String subjectDescription) {
        this.subjectDescription = subjectDescription;
    }
    
    public Integer[] getResponsiblesIds() {
        return this.responsiblesIds;
    }

    public void setResponsiblesIds(Integer[] responsiblesIds) {
        this.responsiblesIds = responsiblesIds;
    }

    public SubjectRequest subjectName(String subjectName) {
        this.subjectName = subjectName;
        return this;
    }

    public SubjectRequest subjectCode(String subjectCode) {
        this.subjectCode = subjectCode;
        return this;
    }

    public SubjectRequest facultyName(String facultyName) {
        this.facultyName = facultyName;
        return this;
    }

    public SubjectRequest responsiblesIds(Integer[] responsiblesIds) {
        this.responsiblesIds = responsiblesIds;
        return this;
    }

    public RequirementRequest[] getRequirements() {
        return this.requirements;
    }

    @Override
    public String toString() {
        return "{" +
            " subjectName='" + getSubjectName() + "'" +
            ", subjectCode='" + getSubjectCode() + "'" +
            ", facultyName='" + getFacultyName() + "'" +
            ", responsiblesIds='" + getResponsiblesIds() + "'" +
            "}";
    }
}
