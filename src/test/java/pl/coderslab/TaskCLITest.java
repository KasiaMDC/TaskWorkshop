package pl.coderslab;

import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import static org.junit.Assert.*;

public class TaskCLITest {
    private TaskCLI underTest;

    @Before
    public void setUp() {
        TaskManager taskManager = new TaskManager();
        underTest = new TaskCLI(taskManager);
    }

    @Test
    public void shouldRejectCommandsInUserInput() {
        // when
        boolean result = underTest.validateCommasFromUserInput("sdfsdf,sdfsdf");

        // then
        assertFalse(result);
    }

    @Test
    public void shouldAcceptUserInputWithoutCommas() {
        // when
        boolean result = underTest.validateCommasFromUserInput("sdfsdfxxsdfsdf");

        // then
        assertTrue(result);
    }

    @Test
    public void shouldAcceptUserCorrectDate() {
        // given
        Map<String, Boolean> userInputs = new HashMap<>();
        userInputs.put("2022-12-23", true);
        userInputs.put("20232-12-23", false);
        userInputs.put("2023-13-23", false);
        userInputs.put("2023-12-45", false);
        userInputs.put("2023-12-0", false);
        userInputs.put("2023-0-23", false);
        userInputs.put("20231210", false);

        for (String input : userInputs.keySet()) {
            // when
            boolean result = underTest.validateDateFromUserInput(input);

            // then
            assertEquals("For input: " + input, userInputs.get(input), result);
        }
    }

    public void shouldNotAcceptUserIncorrectDate() {
        // when
        boolean result = underTest.validateDateFromUserInput("20232-13-44");

        // then
        assertFalse(result);
    }
}