package seedu.address.model.tutorial;

/**
 * Represents a tutorial
 */
public record Tutorial(String name) {

    /**
     * Checks whether the given name is valid
     */
    public static boolean isValidName(String name) {
        final var pattern = "[a-zA-Z0-9_-]+";

        return name.matches(pattern);
    }
}
