package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import seedu.address.model.NavigationMode;

public class CommandResultTest {
    @Test
    public void equals() {
        CommandResult commandResult = new CommandResult("feedback");

        // same values -> ok
        assertEquals(commandResult, new CommandResult("feedback"));
        assertEquals(commandResult, new CommandResult("feedback", NavigationMode.PERSON, false, false));

        // same object -> ok
        assertEquals(commandResult, commandResult);

        // null -> fail
        assertNotNull(commandResult);

        // different types -> fail
        assertNotEquals(commandResult, 0.5f);

        // different feedbackToUser value -> fail
        assertNotEquals(commandResult, new CommandResult("different"));

        // different showHelp value -> fail
        assertNotEquals(commandResult, new CommandResult("feedback", NavigationMode.PERSON, true, false));

        // different exit value -> fail
        assertNotEquals(commandResult, new CommandResult("feedback", NavigationMode.PERSON, false, true));

        // different resultingMode value -> fail
        assertNotEquals(commandResult, new CommandResult("feedback", NavigationMode.TUTORIAL, false, false));
    }

    @Test
    public void hashcode() {
        CommandResult commandResult = new CommandResult("feedback");

        // same values -> returns same hashcode
        assertEquals(commandResult.hashCode(), new CommandResult("feedback").hashCode());

        // different feedbackToUser value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandResult("different").hashCode());

        // different showHelp value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(),
                        new CommandResult("feedback", NavigationMode.PERSON, true, false).hashCode());

        // different exit value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(),
                        new CommandResult("feedback", NavigationMode.PERSON, false, true).hashCode());

        // different resultingMode value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(),
                        new CommandResult("feedback", NavigationMode.TUTORIAL, false, false).hashCode());
    }

    @Test
    public void toStringMethod() {
        CommandResult commandResult = new CommandResult("feedback");
        String expected = CommandResult.class.getCanonicalName() + "{feedbackToUser="
                        + commandResult.getFeedbackToUser() + ", resultingMode=" + commandResult.getResultingMode()
                        + ", showHelp=" + commandResult.isShowHelp() + ", exit=" + commandResult.isExit() + "}";
        assertEquals(expected, commandResult.toString());
    }
}
