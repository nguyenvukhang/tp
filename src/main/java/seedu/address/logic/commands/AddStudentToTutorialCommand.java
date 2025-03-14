package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import javafx.util.Pair;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.NavigationMode;
import seedu.address.model.student.StudentID;
import seedu.address.model.tutorial.Tutorial;

/**
 * Adds a tutorial to the address book.
 */
public class AddStudentToTutorialCommand extends Command {

    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE = "Usage: student_tutorial add TUTORIAL_NAME STUDENT_INDEX";

    public static final String MESSAGE_SUCCESS = "Student added to tutorial: %1$s";
    public static final String MESSAGE_INVALID_NAME = """
                    The only valid characters are: letters (A-Z, a-z), digits (0-9), underscores (_), hyphens (-)""";

    public static final String MESSAGE_TUTORIAL_NOT_FOUND = "Tutorial not found";
    public static final String MESSAGE_STUDENT_NOT_FOUND = "Student not found";

    private final Pair<StudentID, Tutorial> toAdd;

    /**
     * Creates an {@link AddStudentToTutorialCommand} to add the specified
     * {@code Tutorial}
     */
    public AddStudentToTutorialCommand(Pair<StudentID, Tutorial> tutorial) {
        requireNonNull(tutorial);
        toAdd = tutorial;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        StudentID studentId = toAdd.getKey();
        Tutorial tutorial = toAdd.getValue();

        if (!model.hasTutorial(tutorial)) {
            throw new CommandException(MESSAGE_TUTORIAL_NOT_FOUND);
        }

        if (!model.hasStudent(studentId)) {
            throw new CommandException(MESSAGE_TUTORIAL_NOT_FOUND);
        }

        return new CommandResult(MESSAGE_SUCCESS.formatted(toAdd), NavigationMode.TUTORIAL);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AddStudentToTutorialCommand otherAddCommand)) {
            return false;
        }

        return toAdd.equals(otherAddCommand.toAdd);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("toAdd", toAdd).toString();
    }
}
