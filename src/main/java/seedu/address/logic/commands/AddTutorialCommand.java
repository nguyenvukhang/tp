package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.tutorial.Tutorial;

/**
 * Adds a person to the address book.
 */
public class AddTutorialCommand extends Command {

    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE = "Usage: tutorial add TUTORIAL_NAME";

    public static final String MESSAGE_SUCCESS = "New tutorial added: %1$s";
    public static final String MESSAGE_INVALID_NAME = """
            The only valid characters are: letters (A-Z, a-z), digits (0-9), underscores (_), hyphens (-)""";
    public static final String MESSAGE_DUPLICATE_TUTORIAL = "Tutorial slot already exists";

    private final Tutorial toAdd;

    /**
     * Creates an AddCommand to add the specified {@code Person}
     */
    public AddTutorialCommand(Tutorial tutorial) {
        requireNonNull(tutorial);
        toAdd = tutorial;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasTutorial(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_TUTORIAL);
        }

        model.addTutorial(toAdd);
        return new CommandResult(MESSAGE_SUCCESS.formatted(toAdd));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AddTutorialCommand otherAddCommand)) {
            return false;
        }

        return toAdd.equals(otherAddCommand.toAdd);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("toAdd", toAdd)
                .toString();
    }
}
