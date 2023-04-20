import org.junit.Before;
import org.junit.Test;
import java.time.LocalTime;
import java.time.LocalDate;
import java.util.Arrays;
import static org.junit.Assert.*;

public class DiseaseChatBotTest {
    private DiseaseChatBot chatBot;

    @Before
    public void setUp() {
        chatBot = new DiseaseChatBot();
    }

    @Test
    public void testGetResponse() {
        // Test a valid disease name
        String response = chatBot.getResponse("Tell me about HIV");
        assertTrue(response.contains("human immunodeficiency virus"));

        // Test a misspelled disease name
        response = chatBot.getResponse("Tell me about auttism");
        assertTrue(response.contains("autism"));

        // Test getting all info for a disease
        response = chatBot.getResponse("Tell me everything about malaria");
        assertTrue(response.contains("malaria") && response.contains("caused by a parasite"));

        // Test getting all info for an unsupported disease
        response = chatBot.getResponse("Tell me everything about ebola");
        assertTrue(response.contains("don't have any information"));

        // Test small talk
        response = chatBot.getResponse("How are you?");
        assertTrue(response.contains("I'm a chat bot"));

        // Test past usage statistics
        response = chatBot.getResponse("How many users did you have yesterday?");
        assertTrue(response.contains("chat sessions"));

        // Test not mentioning a disease name and not asking small talk or past usage
        response = chatBot.getResponse("What's up?");
        assertTrue(response.contains("don't know what you're asking"));

        // Test not mentioning a disease name but asking for all info
        response = chatBot.getResponse("Tell me everything");
        assertTrue(response.contains("What disease do you want to learn everything about"));

        // Test asking for all info but not providing a disease name
        response = chatBot.getResponse("Actually, never mind");
        assertFalse(chatBot.getAllInfo);
    }

    @Test
    public void testCheckForDiseaseName() {
        // Test mentioning a valid disease name
        boolean result = chatBot.checkForDiseaseName("What is cholera?");
        assertTrue(result);
        assertEquals("cholera", chatBot.currentDisease);

        // Test mentioning a misspelled disease name
        result = chatBot.checkForDiseaseName("What is autism?");
        assertTrue(result);
        assertEquals("autism", chatBot.currentDisease);

        // Test not mentioning a disease name
        result = chatBot.checkForDiseaseName("What is the meaning of life?");
        assertFalse(result);
        assertNull(chatBot.currentDisease);
    }

    @Test
    public void testGetStatsResponse() {
        // Test asking for total number of chat sessions
        String response = chatBot.getStatsResponse("How many chat sessions have you had?");
        assertTrue(response.contains("chat sessions"));

        // Test asking for total number of chat sessions yesterday
        String date = LocalDate.now().minusDays(1).toString();
        String formattedDate = date.replace("-", "/");
        response = chatBot.getStatsResponse("How many chat sessions did you have on " + formattedDate + "?");
        assertTrue(response.contains("chat sessions") && response.contains(date));

        // Test asking for unsupported statistics
        response = chatBot.getStatsResponse("How many cats do you have?");
        assertNull(response);
    }

    @Test
    public void testGetSmallTalkResponse() {
        // Test asking about chat bot's age
        String response = chatBot.getSmallTalkResponse("How old are you?");
        assertTrue(response.contains("I'm a chat bot"));

        // Test asking how chat bot
    }
}