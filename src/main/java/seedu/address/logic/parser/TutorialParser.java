package seedu.address.logic.parser;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import seedu.address.logic.commands.AddTutorialCommand;
import seedu.address.logic.commands.Command;
import seedu.address.logic.parser.exceptions.ParseException;

public class TutorialParser implements Parser<Command> {

    private final Map<String, Parser<? extends Command>> subcmds;

    TutorialParser() {
        subcmds = new HashMap<>();
        subcmds.put(AddTutorialCommand.COMMAND_WORD, new AddTutorialCommandParser());
    }

    public Command parse(String arguments) throws ParseException {
        var cmd = arguments.trim().split(" ");
        var rest = Arrays.stream(cmd).skip(1).collect(Collectors.joining(" "));

        return subcmds.get(cmd[0]).parse(rest);
    }
}
