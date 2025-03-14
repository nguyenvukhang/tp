package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TUTORIAL_NAME;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.AddStudentToTutorialCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.tutorial.Tutorial;

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
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_TUTORIAL_NAME);

        if (!argMultimap.allPresent(PREFIX_TUTORIAL_NAME)) {
            throw new ParseException(
                            String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddStudentToTutorialCommand.MESSAGE_USAGE));
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_TUTORIAL_NAME);

        Tutorial tutorial = ParserUtil.parseTutorial(argMultimap.getValue(PREFIX_TUTORIAL_NAME).get());

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(
                            String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddStudentToTutorialCommand.MESSAGE_USAGE),
                            pe);
        }

        return new AddStudentToTutorialCommand(index, tutorial);
    }
}
