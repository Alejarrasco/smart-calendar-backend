package bo.ucb.edu.smartcalendar.dto;

public class SmartcalResponse {
    
    private String code;
    private Object data;
    private String errormessage;

    public SmartcalResponse() {
    }

    public SmartcalResponse(String code, Object data, String errormessage) {
        this.code = code;
        this.data = data;
        this.errormessage = errormessage;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getErrormessage() {
        return errormessage;
    }

    public void setErrormessage(String errormessage) {
        this.errormessage = errormessage;
    }
}
