package seedu.address.model.student;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Student's Telegram handle in the address book. Guarantees:
 * immutable; is valid as declared in {@link #isValidHandle(String)}
 */
public class TelegramHandle {

    public static final String VALIDATION_REGEX = "^@[a-z0-9_]{4,31}$";
    public static final String MESSAGE_CONSTRAINTS = "Telegram handles should be in the form @telegramhandle, "
                    + "and it should not be blank";

    public final String handle;

    /**
     * Constructs a {@code TelegramHandle}.
     *
     * @param handle
     *            A valid Telegram handle.
     */
    public TelegramHandle(String handle) {
        requireNonNull(handle);
        checkArgument(isValidHandle(handle), MESSAGE_CONSTRAINTS);
        this.handle = handle.toLowerCase().trim();
    }

    /**
     * Returns true if a given string is a valid name.
     */
    public static boolean isValidHandle(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof TelegramHandle)) {
            return false;
        }

        TelegramHandle otherTelegramHandle = (TelegramHandle) other;
        return handle.equals(otherTelegramHandle.handle);
    }

    @Override
    public String toString() {
        return handle;
    }

}
