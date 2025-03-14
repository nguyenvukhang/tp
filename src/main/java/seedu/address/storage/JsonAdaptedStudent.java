package seedu.address.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.student.Email;
import seedu.address.model.student.Name;
import seedu.address.model.student.Phone;
import seedu.address.model.student.Student;
import seedu.address.model.student.StudentID;
import seedu.address.model.student.TelegramHandle;
import seedu.address.model.tutorial.Tutorial;

/**
 * Jackson-friendly version of {@link Student}.
 */
class JsonAdaptedStudent {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Student's %s field is missing!";

    private final String name;
    private final String id;
    private final String phone;
    private final String email;
    private final String handle;
    private final List<JsonAdaptedTutorial> tutorials = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedStudent} with the given student details.
     */
    @JsonCreator
    public JsonAdaptedStudent(@JsonProperty("name") String name, @JsonProperty("id") String studentId,
                    @JsonProperty("phone") String phone, @JsonProperty("email") String email,
                    @JsonProperty("handle") String handle,
                    @JsonProperty("tutorials") List<JsonAdaptedTutorial> tutorials) {
        this.name = name;
        this.id = studentId;
        this.phone = phone;
        this.email = email;
        this.handle = handle;
        if (tutorials != null) {
            this.tutorials.addAll(tutorials);
        }
    }

    /**
     * Converts a given {@code Student} into this class for Jackson use.
     */
    public JsonAdaptedStudent(Student source) {
        name = source.getName().fullName;
        id = source.getStudentId().id;
        phone = source.getPhone().value;
        email = source.getEmail().value;
        handle = source.getHandle().handle;
        tutorials.addAll(source.getTutorials().stream().map(JsonAdaptedTutorial::new).collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted student object into the model's
     * {@code Student} object.
     *
     * @throws IllegalValueException
     *             if there were any data constraints violated in the adapted
     *             student.
     */
    public Student toModelType() throws IllegalValueException {
        final List<Tutorial> studentTutorials = new ArrayList<>();
        for (JsonAdaptedTutorial tutorial : tutorials) {
            studentTutorials.add(tutorial.toModelType());
        }

        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(name)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final Name modelName = new Name(name);

        if (id == null) {
            throw new IllegalValueException(
                            String.format(MISSING_FIELD_MESSAGE_FORMAT, StudentID.class.getSimpleName()));
        }
        if (!StudentID.isValidID(id)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final StudentID modelStudentId = new StudentID(id);

        if (phone == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName()));
        }
        if (!Phone.isValidPhone(phone)) {
            throw new IllegalValueException(Phone.MESSAGE_CONSTRAINTS);
        }
        final Phone modelPhone = new Phone(phone);

        if (email == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName()));
        }
        if (!Email.isValidEmail(email)) {
            throw new IllegalValueException(Email.MESSAGE_CONSTRAINTS);
        }
        final Email modelEmail = new Email(email);

        if (handle == null) {
            throw new IllegalValueException(
                            String.format(MISSING_FIELD_MESSAGE_FORMAT, TelegramHandle.class.getSimpleName()));
        }
        if (!TelegramHandle.isValidHandle(handle)) {
            throw new IllegalValueException(TelegramHandle.MESSAGE_CONSTRAINTS);
        }
        final TelegramHandle modelHandle = new TelegramHandle(handle);

        final Set<Tutorial> modelTutorials = new HashSet<>(studentTutorials);
        return new Student(modelName, modelStudentId, modelPhone, modelEmail, modelHandle, modelTutorials);
    }

}
