import java.time.LocalTime;
import java.time.LocalDate;
import java.util.Arrays;

public class DiseaseChatBotTest {
    private static DiseaseChatBot chatBot;

    public static void main(String args[]) {
        chatBot = new DiseaseChatBot();

        // Test a valid disease name
        String response = chatBot.getResponse("Tell me about HIV");
        System.out.println(response.contains("human immunodeficiency virus"));

        // Test a misspelled disease name
        response = chatBot.getResponse("Tell me about auttism");
        System.out.println(response.contains("autism"));

        // Test getting all info for a disease
        response = chatBot.getResponse("Tell me everything about malaria");
        System.out.println(response.contains("malaria") && response.contains("caused by a parasite"));

        // Test getting all info for an unsupported disease
        response = chatBot.getResponse("Tell me everything about ebola");
        System.out.println(response.contains("don't have any information"));

        // Test small talk
        response = chatBot.getResponse("How are you?");
        System.out.println(response.contains("I'm a chat bot"));

        // Test past usage statistics
        response = chatBot.getResponse("How many users did you have yesterday?");
        System.out.println(response.contains("chat sessions"));

        // Test not mentioning a disease name and not asking small talk or past usage
        response = chatBot.getResponse("What's up?");
        System.out.println(response.contains("don't know what you're asking"));

        // Test not mentioning a disease name but asking for all info
        response = chatBot.getResponse("Tell me everything");
        System.out.println(response.contains("What disease do you want to learn everything about"));


    }
}
