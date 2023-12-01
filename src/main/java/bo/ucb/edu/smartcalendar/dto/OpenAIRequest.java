package bo.ucb.edu.smartcalendar.dto;

import java.util.ArrayList;
import java.util.List;

public class OpenAIRequest {
    
    private String model;
    private String responseFormat;
    private List<Message> messages;
    private int n;
    private double temperature;

    public OpenAIRequest(String model, String context, String prompt) {
        this.model = model;
        this.responseFormat = "json_object";

        List<Message> messages = new ArrayList<>();
        messages.add(new Message("system", context));
        messages.add(new Message("user", prompt));
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getResponseFormat() {
        return responseFormat;
    }

    public void setResponseFormat(String responseFormat) {
        this.responseFormat = responseFormat;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    public int getN() {
        return n;
    }

    public void setN(int n) {
        this.n = n;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }
}
