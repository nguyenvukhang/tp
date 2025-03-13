package seedu.address.logic.commands;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.student.Student;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ID_STUDENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TUTORIAL_SESSION;

public class AddStudentCommand extends AddCommand{

    public static final String COMMAND_WORD = "student";

    public static final String MESSAGE_USAGE = "Usage: add student " + PREFIX_ID_STUDENT + "STUDENT_ID "
            + PREFIX_NAME + "NAME " + PREFIX_PHONE + "PHONE "
            + PREFIX_EMAIL + "EMAIL " + PREFIX_TUTORIAL_SESSION
            + "TUTORIAL " + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_NAME + "John Doe " + PREFIX_ID_STUDENT + "A0276354B "
            + PREFIX_PHONE + "98765432 " + PREFIX_EMAIL + "e1234567@u.nus.edu " + PREFIX_TUTORIAL_SESSION
            + "T03 " + PREFIX_TAG + "hasQuestion " + PREFIX_TAG + "wantsConsultation";

    public static final String MESSAGE_SUCCESS = "New student added: %1$s";

    private final Student toAdd;

    /**
     * Creates an AddStudentCommand to add the specified {@code Student}
     */
    public AddStudentCommand(Student student) {
        super(student);
        toAdd = student;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasPerson(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_PERSON);
        }

        model.addPerson(toAdd);
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

        AddStudentCommand otherAddStudentCommand = (AddStudentCommand) other;
        return toAdd.equals(otherAddStudentCommand.toAdd);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("toAdd", toAdd).toString();
    }

}
