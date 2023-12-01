package bo.ucb.edu.smartcalendar.dto;

import java.util.List;

public class OpenAIResponse {

    private List<Choice> choices;

    public OpenAIResponse(List<Choice> choices) {
        this.choices = choices;
    }

    public OpenAIResponse() {
    }

    public List<Choice> getChoices() {
        return choices;
    }

    public void setChoices(List<Choice> choices) {
        this.choices = choices;
    }

    public static class Choice {

        private int index;
        private Message message;
        
        public Choice(int index, Message message) {
            this.index = index;
            this.message = message;
        }

        public Choice() {
        }
        public int getIndex() {
            return index;
        }
        public void setIndex(int index) {
            this.index = index;
        }
        public Message getMessage() {
            return message;
        }
        public void setMessage(Message message) {
            this.message = message;
        }

        @Override
        public String toString(){
            return "Choice{" +
                "index=" + index +
                ", message=" + message +
                '}';
        }

        
    }

    @Override
    public String toString(){
        return "OpenAIResponse{" +
            "choices=" + choices +
            '}';
    }
    
}
