package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_HANDLE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ID_STUDENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TUTORIAL_NAME;

import java.util.Set;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.student.Email;
import seedu.address.model.student.Name;
import seedu.address.model.student.Phone;
import seedu.address.model.student.Student;
import seedu.address.model.student.StudentID;
import seedu.address.model.student.TelegramHandle;
import seedu.address.model.tutorial.Tutorial;

/**
 * Parses input arguments and creates a new AddCommand object
 */
public class AddCommandParser implements Parser<AddCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand
     * and returns an AddCommand object for execution.
     *
     * @throws ParseException
     *             if the user input does not conform the expected format
     */
    public AddCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_ID_STUDENT, PREFIX_PHONE,
                        PREFIX_EMAIL, PREFIX_HANDLE, PREFIX_TUTORIAL_NAME);

        if (!argMultimap.allPresent(PREFIX_NAME, PREFIX_ID_STUDENT, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_HANDLE)
                        || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_NAME, PREFIX_ID_STUDENT, PREFIX_PHONE, PREFIX_EMAIL,
                        PREFIX_HANDLE);
        Name name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
        StudentID studentId = ParserUtil.parseStudentId(argMultimap.getValue(PREFIX_ID_STUDENT).get());
        Phone phone = ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get());
        Email email = ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get());
        TelegramHandle handle = ParserUtil.parseTelegramHandle(argMultimap.getValue(PREFIX_HANDLE).get());
        Set<Tutorial> tutorialList = ParserUtil.parseTutorials(argMultimap.getAllValues(PREFIX_TUTORIAL_NAME));

        Student student = new Student(name, studentId, phone, email, handle, tutorialList);

        return new AddCommand(student);
    }
}
