package bo.ucb.edu.smartcalendar.dto;

import java.sql.Date;

public class SpaceRequest {
    
    private String spaceName;
    private String spaceDescription;
    private String spaceType;
    private int capacity;
    private String[] periodTimes;
    private Date openDate;
    private Date closeDate;

    public SpaceRequest() {
    }

    public String getSpaceName() {
        return spaceName;
    }

    public void setSpaceName(String spaceName) {
        this.spaceName = spaceName;
    }

    public String getSpaceDescription() {
        return spaceDescription;
    }

    public void setSpaceDescription(String spaceDescription) {
        this.spaceDescription = spaceDescription;
    }

    public String getSpaceType() {
        return spaceType;
    }

    public void setSpaceType(String spaceType) {
        this.spaceType = spaceType;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String[] getPeriodTimes() {
        return periodTimes;
    }

    public void setPeriodTimes(String[] periodTimes) {
        this.periodTimes = periodTimes;
    }

    public Date getOpenDate() {
        return openDate;
    }

    public void setOpenDate(Date openDate) {
        this.openDate = openDate;
    }

    public Date getCloseDate() {
        return closeDate;
    }

    public void setCloseDate(Date closeDate) {
        this.closeDate = closeDate;
    }

    @Override
    public String toString() {
        return "SpaceRequest for "+spaceName+"{"+
            "spaceDescription='" + spaceDescription + '\'' +
            ", spaceType='" + spaceType + '\'' +
            ", capacity=" + capacity +
            ", periodTimes=" + periodTimes +
            ", openDate=" + openDate +
            ", closeDate=" + closeDate +
            '}';
    } 
    
}
