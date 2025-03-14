package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.NavigationMode;
import seedu.address.model.student.Student;
import seedu.address.model.tutorial.Tutorial;

/**
 * Adds a tutorial to the address book.
 */
public class AddStudentToTutorialCommand extends Command {

    public static final String COMMAND_WORD = "student_tutorial";

    public static final String MESSAGE_USAGE = "Usage: student_tutorial TUTORIAL_NAME STUDENT_INDEX";

    public static final String MESSAGE_SUCCESS = "Student added to tutorial: %1$s";
    public static final String MESSAGE_INVALID_NAME = """
                    The only valid characters are: letters (A-Z, a-z), digits (0-9), underscores (_), hyphens (-)""";

    public static final String MESSAGE_TUTORIAL_NOT_FOUND = "Tutorial not found";
    public static final String MESSAGE_STUDENT_NOT_FOUND = "Student not found";

    private final Index index;
    private final Tutorial tutorial;

    /**
     * Creates an {@link AddStudentToTutorialCommand} to add the specified
     * {@code Tutorial}
     */
    public AddStudentToTutorialCommand(Index index, Tutorial tutorial) {
        requireNonNull(index);
        requireNonNull(tutorial);

        this.index = index;
        this.tutorial = tutorial;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        if (!model.hasTutorial(tutorial)) {
            throw new CommandException(MESSAGE_TUTORIAL_NOT_FOUND);
        }

        List<Student> lastShownList = model.getFilteredStudentList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Student studentToEdit = lastShownList.get(index.getZeroBased());
        Student editedStudent = studentToEdit.clone();
        Set<Tutorial> tutorials = new HashSet<>(studentToEdit.getTutorials());
        tutorials.add(tutorial);
        editedStudent.setTutorials(tutorials);

        model.setStudent(studentToEdit, editedStudent);

        model.updateFilteredStudentList(PREDICATE_SHOW_ALL_PERSONS);

        return new CommandResult(MESSAGE_SUCCESS.formatted(editedStudent), NavigationMode.TUTORIAL);
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

        return index.equals(otherAddCommand.index) && tutorial.equals(otherAddCommand.tutorial);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("index", index).add("tutorial", tutorial).toString();
    }
}
