package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Objects;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.NavigationMode;

/**
 * Represents the result of a command execution.
 */
public class CommandResult {

    public static final NavigationMode DEFAULT_RESULTING_MODE = NavigationMode.PERSON;

    private final String feedbackToUser;

    /** Help information should be shown to the user. */
    private final boolean showHelp;

    /** The application should exit. */
    private final boolean exit;

    /** Command results in a change in navigation mode. */
    private final NavigationMode resultingMode;

    /**
     * Constructs a {@code CommandResult} with the specified fields.
     */
    public CommandResult(String feedbackToUser, NavigationMode resultingMode, boolean showHelp, boolean exit) {
        this.feedbackToUser = requireNonNull(feedbackToUser);
        this.resultingMode = resultingMode;
        this.showHelp = showHelp;
        this.exit = exit;
    }

    /**
     * Constructs a {@code CommandResult} with the specified {@code feedbackToUser},
     * and other fields set to their default value.
     */
    public CommandResult(String feedbackToUser) {
        this(feedbackToUser, DEFAULT_RESULTING_MODE, false, false);
    }

    /**
     * Constructs a {@code CommandResult} with the specified {@code feedbackToUser}
     * and {@code resultingMode}, and other fields set to their default value.
     */
    public CommandResult(String feedbackToUser, NavigationMode resultingMode) {
        this(feedbackToUser, resultingMode, false, false);
    }

    public String getFeedbackToUser() {
        return feedbackToUser;
    }

    public boolean isShowHelp() {
        return showHelp;
    }

    public boolean isExit() {
        return exit;
    }

    public NavigationMode getResultingMode() {
        return resultingMode;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof CommandResult)) {
            return false;
        }

        CommandResult otherCommandResult = (CommandResult) other;
        return feedbackToUser.equals(otherCommandResult.feedbackToUser)
                        && resultingMode == otherCommandResult.resultingMode && showHelp == otherCommandResult.showHelp
                        && exit == otherCommandResult.exit;
    }

    @Override
    public int hashCode() {
        return Objects.hash(feedbackToUser, resultingMode, showHelp, exit);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("feedbackToUser", feedbackToUser).add("resultingMode", resultingMode)
                        .add("showHelp", showHelp).add("exit", exit).toString();
    }

}
