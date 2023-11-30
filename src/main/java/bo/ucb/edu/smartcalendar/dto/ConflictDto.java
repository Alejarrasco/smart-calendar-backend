package bo.ucb.edu.smartcalendar.dto;

import java.util.List;

import bo.ucb.edu.smartcalendar.entity.Planification;

public class ConflictDto {
    
    private List<Planification> planifications;
    private int conflictingSpaceId;
    private int conflictingPerdiodId;
    
    public ConflictDto() {
    }
    public List<Planification> getPlanifications() {
        return planifications;
    }
    public void setPlanifications(List<Planification> planifications) {
        this.planifications = planifications;
    }
    public int getConflictingSpaceId() {
        return conflictingSpaceId;
    }
    public void setConflictingSpaceId(int conflictingSpaceId) {
        this.conflictingSpaceId = conflictingSpaceId;
    }
    public int getConflictingPerdiodId() {
        return conflictingPerdiodId;
    }
    public void setConflictingPerdiodId(int conflictingPerdiodId) {
        this.conflictingPerdiodId = conflictingPerdiodId;
    }
}
