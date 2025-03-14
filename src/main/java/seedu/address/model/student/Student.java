package seedu.address.model.student;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.Objects;
import java.util.Set;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.tutorial.Tutorial;

/**
 * Represents a Student in the address book. Guarantees: details are present and
 * not null, field values are validated, immutable.
 */
public class Student {

    // Identity fields
    private final Name name;
    private final StudentID studentId;
    private final Phone phone;
    private final Email email;
    private final TelegramHandle handle;

    // Data fields
    private Set<Tutorial> tutorials;

    /**
     * Every field must be present and not null.
     */
    public Student(Name name, StudentID studentId, Phone phone, Email email, TelegramHandle handle,
                    Set<Tutorial> tutorials) {
        requireAllNonNull(name, studentId, phone, email, handle, tutorials);
        this.name = name;
        this.studentId = studentId;
        this.phone = phone;
        this.email = email;
        this.handle = handle;
        this.tutorials = tutorials;
    }

    public Name getName() {
        return name;
    }

    public StudentID getStudentId() {
        return studentId;
    }

    public Phone getPhone() {
        return phone;
    }

    public Email getEmail() {
        return email;
    }

    public TelegramHandle getHandle() {
        return handle;
    }

    /**
     * Returns an immutable tutorial set, which throws
     * {@code UnsupportedOperationException} if modification is attempted.
     */
    public Set<Tutorial> getTutorials() {
        return Collections.unmodifiableSet(tutorials);
    }

    /**
     * Returns true if both students have the same name. This defines a weaker
     * notion of equality between two students.
     */
    public boolean isSamePerson(Student otherStudent) {
        if (otherStudent == this) {
            return true;
        }

        return otherStudent != null && otherStudent.getName().equals(getName());
    }

    /**
     * Returns true if both students have the same identity and data fields. This
     * defines a stronger notion of equality between two students.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Student)) {
            return false;
        }

        Student otherStudent = (Student) other;
        return name.equals(otherStudent.name) && studentId.equals(otherStudent.studentId)
                        && phone.equals(otherStudent.phone) && email.equals(otherStudent.email)
                        && handle.equals(otherStudent.handle);
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, email, handle, tutorials);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("name", name).add("studentId", studentId).add("phone", phone)
                        .add("email", email).add("handle", handle).add("tutorials", tutorials).toString();
    }

}
