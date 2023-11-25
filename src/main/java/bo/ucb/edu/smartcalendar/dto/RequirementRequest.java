package bo.ucb.edu.smartcalendar.dto;

public class RequirementRequest {
    
    private String subjectCode;
    private Integer periodsPerClass;
    private Integer classesPerWeek;
    private String spaceType;
    private Integer maxAlumni;
    private String semester;


    public RequirementRequest() {
    }


    public String getSubjectCode() {
        return subjectCode;
    }


    public void setSubjectCode(String subjectCode) {
        this.subjectCode = subjectCode;
    }


    public Integer getPeriodsPerClass() {
        return periodsPerClass;
    }


    public void setPeriodsPerClass(Integer periodsPerClass) {
        this.periodsPerClass = periodsPerClass;
    }


    public Integer getClassesPerWeek() {
        return classesPerWeek;
    }


    public void setClassesPerWeek(Integer classesPerWeek) {
        this.classesPerWeek = classesPerWeek;
    }


    public String getSpaceType() {
        return spaceType;
    }


    public void setSpaceType(String spaceType) {
        this.spaceType = spaceType;
    }


    public Integer getMaxAlumni() {
        return maxAlumni;
    }


    public void setMaxAlumni(Integer maxAlumni) {
        this.maxAlumni = maxAlumni;
    }


    public String getSemester() {
        return semester;
    }


    public void setSemester(String semester) {
        this.semester = semester;
    }
    
    @Override
    public String toString() {
        return "Requirement for "+ subjectCode +": \n"+
        "-> Periods per class: "+periodsPerClass+"\n"+
        "-> Classes per week: "+classesPerWeek+"\n"+
        "-> Space type: "+spaceType+"\n"+
        "-> Max alumni: "+maxAlumni;
    }
}
