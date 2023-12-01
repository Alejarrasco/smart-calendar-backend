package bo.ucb.edu.smartcalendar.dto;

public class Message {
    
    private String role;
    private String content;

    public Message(String role, String content) {
        this.role = role;
        this.content = content;
    }

    public Message() {
    }

    public String getRole() {
        return role;
    }

    public String getContent() {
        return content;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString(){
        return "Message{" +
            "role='" + role + '\'' +
            ", content='" + content + '\'' +
            '}';
    }
}
