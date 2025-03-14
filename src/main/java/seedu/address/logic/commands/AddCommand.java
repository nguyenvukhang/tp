package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_HANDLE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ID_STUDENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TUTORIAL_NAME;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.student.Student;
import seedu.address.model.tutorial.Tutorial;

/**
 * Adds a student to the address book.
 */
public class AddCommand extends Command {

    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a student to the address book. " + "Parameters: "
                    + PREFIX_NAME + "NAME " + PREFIX_ID_STUDENT + "ID Student " + PREFIX_PHONE + "PHONE " + PREFIX_EMAIL
                    + "EMAIL " + "[" + PREFIX_TUTORIAL_NAME + "TUTORIAL]...\n" + "Example: " + COMMAND_WORD + " "
                    + PREFIX_NAME + "John Doe " + PREFIX_ID_STUDENT + "A0123456Z " + PREFIX_PHONE + "98765432 "
                    + PREFIX_EMAIL + "johnd@example.com " + PREFIX_HANDLE + "@john_doe " + PREFIX_TUTORIAL_NAME
                    + "CS2103_T01 " + PREFIX_TUTORIAL_NAME + "CS2106_T02";

    public static final String MESSAGE_SUCCESS = "New student added: %1$s";
    public static final String MESSAGE_DUPLICATE_PERSON = "This student already exists in the address book";
    public static final String MESSAGE_TUTORIAL_NOT_FOUND = "One of the tutorial groups the student is added to does not exist";

    private final Student toAdd;

    /**
     * Creates an AddCommand to add the specified {@code Student}
     */
    public AddCommand(Student student) {
        requireNonNull(student);
        toAdd = student;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasStudent(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_PERSON);
        }

        for (Tutorial tutorial : toAdd.getTutorials()) {
            if (!model.hasTutorial(tutorial)) {
                throw new CommandException(MESSAGE_TUTORIAL_NOT_FOUND);
            }
        }

        model.addStudent(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, Messages.format(toAdd)));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AddCommand)) {
            return false;
        }

        AddCommand otherAddCommand = (AddCommand) other;
        return toAdd.equals(otherAddCommand.toAdd);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("toAdd", toAdd).toString();
    }
}
