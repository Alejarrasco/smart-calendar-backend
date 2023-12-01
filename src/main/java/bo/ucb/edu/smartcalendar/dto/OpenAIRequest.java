package bo.ucb.edu.smartcalendar.dto;

import java.util.ArrayList;
import java.util.List;

public class OpenAIRequest {
    
    private String model;
    private List<Message> messages;
    private int n = 1;
    private double temperature = 1.0;

    public OpenAIRequest(String model, String context, String prompt) {
        this.model = model;

        this.messages = new ArrayList<>();
        messages.add(new Message("system", context));
        messages.add(new Message("user", prompt));
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
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

    @Override
    public String toString(){
        return "OpenAIRequest{" +
            "model='" + model + '\'' +
            ", messages=" + messages +
            ", n=" + n +
            ", temperature=" + temperature +
            '}';
    }
}
