package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.tutorial.Tutorial;
import seedu.address.testutil.TypicalStudents;

public class DeleteTutorialCommandTest {

    private static final Model modelStub = new ModelManager(TypicalStudents.getTypicalAddressBookInclTutorials(),
                    new UserPrefs());

    @Test
    public void execute_tutorialDeletedByModel_deleteSuccessful() throws Exception {
        var t = new Tutorial("Tutorial-2");
        CommandResult commandResult = new DeleteTutorialCommand(t).execute(modelStub);
        var tutorials = modelStub.getAddressBook().getTutorialList();

        assertEquals(DeleteTutorialCommand.MESSAGE_SUCCESS.formatted(t), commandResult.getFeedbackToUser());
        assertFalse(tutorials.contains(t));
    }

    @Test
    public void execute_tutorialDoesNotExist_throwsCommandException() {
        var cmd = new DeleteTutorialCommand(new Tutorial("Tutorial_3"));

        assertThrows(CommandException.class,
                        DeleteTutorialCommand.MESSAGE_TUTORIAL_DOES_NOT_EXIST.formatted("Tutorial[name=Tutorial_3]"), (
                        ) -> cmd.execute(modelStub));
    }
}
