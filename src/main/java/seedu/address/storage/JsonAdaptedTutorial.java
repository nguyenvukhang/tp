package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.tutorial.Tutorial;

/**
 * Jackson-friendly version of {@link Tutorial}.
 */
public class JsonAdaptedTutorial {
    private final String name;

    JsonAdaptedTutorial(Tutorial tutorial) {
        name = tutorial.name();
    }

    @JsonCreator
    public JsonAdaptedTutorial(@JsonProperty("name") String name) {
        this.name = name;
    }

    /**
     * Converts this Jackson-friendly adapted tutorial object into the model's
     * {@code Tutorial} object.
     *
     * @throws IllegalValueException
     *             if there were any data constraints violated in the adapted
     *             student.
     */
    public Tutorial toModelType() throws IllegalValueException {
        if (!Tutorial.isValidName(name)) {
            throw new IllegalValueException("Tutorial name is not valid.");
        } ;
        return new Tutorial(name);
    }
}
