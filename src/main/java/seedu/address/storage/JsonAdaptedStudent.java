package seedu.address.storage;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.student.Student;
import seedu.address.model.person.student.StudentID;
import seedu.address.model.tutorial.Tutorial;

/**
 * Jackson-friendly version of {@link Student}.
 */
public class JsonAdaptedStudent extends JsonAdaptedPerson {

    private final String id;
    private final String tutorial;

    /**
     * Converts a given {@code Person} into this class for Jackson use.
     */
    public JsonAdaptedStudent(Student source) {
        super(source);
        id = source.getStudentId().id;
        tutorial = source.getTutorial().name();
    }

    /**
     * Converts this Jackson-friendly adapted student object into the model's
     * {@code Student} object.
     *
     * @throws IllegalValueException
     *             if there were any data constraints violated in the adapted
     *             person.
     */
    @Override
    public Student toModelType() throws IllegalValueException {

        if (id == null) {
            throw new IllegalValueException(
                            String.format(MISSING_FIELD_MESSAGE_FORMAT, StudentID.class.getSimpleName()));
        }
        if (!StudentID.isValidID(id)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final StudentID modelStudentId = new StudentID(id);

        if (tutorial == null) {
            throw new IllegalValueException(
                            String.format(MISSING_FIELD_MESSAGE_FORMAT, Tutorial.class.getSimpleName()));
        }
        if (!Tutorial.isValidName(tutorial)) {
            throw new IllegalValueException(Phone.MESSAGE_CONSTRAINTS);
        }
        final Tutorial modelTutorial = new Tutorial(tutorial);

        Person person = super.toModelType();
        return new Student(person, modelStudentId, modelTutorial);
    }

}
