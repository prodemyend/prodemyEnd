// DTO/ChatResponse.java
package za.ac.cput.DTO;

import java.util.List;

public class DeepSeekResponse {
    private List<Choice> choices;

    public static class Choice {
        private Message message;
        public Message getMessage() { return message; }
    }

    public static class Message {
        private String role;
        private String content;
        public String getRole() { return role; }
        public String getContent() { return content; }
    }

    public List<Choice> getChoices() { return choices; }
}
