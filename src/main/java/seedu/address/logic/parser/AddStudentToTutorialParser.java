package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.AddStudentToTutorialCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new AddStudentToTutorialCommand object
 */
public class AddStudentToTutorialParser implements Parser<AddStudentToTutorialCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the
     * AddStudentToTutorialCommand and returns an AddStudentToTutorialCommand object
     * for execution.
     *
     * @throws ParseException
     *             if the user input does not conform the expected format
     */
    public AddStudentToTutorialCommand parse(String args) throws ParseException {
        throw new ParseException(
                        String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddStudentToTutorialCommand.MESSAGE_USAGE));
    }
}
