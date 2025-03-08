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

    public Tutorial toModelType() throws IllegalValueException {
        return new Tutorial(name);
    }
}
