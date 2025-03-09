package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Objects;
import java.util.stream.Stream;

import seedu.address.logic.commands.AddTutorialCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.tutorial.Tutorial;

/**
 * Parses input arguments and creates a new {@link AddTutorialCommand} object
 */
public class AddTutorialCommandParser implements Parser<AddTutorialCommand> {

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand
     * and returns an AddCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddTutorialCommand parse(String tutorialName) throws ParseException {
        Objects.requireNonNull(tutorialName);

        if (tutorialName.isEmpty()) {
            throw new ParseException(MESSAGE_INVALID_COMMAND_FORMAT.formatted(AddTutorialCommand.MESSAGE_USAGE));
        }

        if (!Tutorial.isValidName(tutorialName)) {
            throw new ParseException(MESSAGE_INVALID_COMMAND_FORMAT.formatted(AddTutorialCommand.MESSAGE_INVALID_NAME));
        }

        return new AddTutorialCommand(new Tutorial(tutorialName));
    }

}
