package seedu.address.model.person.student;

import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;
import seedu.address.model.tutorial.Tutorial;

import java.util.Set;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

/**
 * Represents a student
 */
public class Student extends Person {

    private final StudentID studentId;
    private Tutorial tutorial;

    public Student(Name name, StudentID studentId, Phone phone, Email email, Tutorial tutorial, Set<Tag> tags) {
        super(name, phone, email, new Address("University"), tags);
        this.studentId = studentId;
        this.tutorial = tutorial;
    }

    public Student(Person person, StudentID studentId, Tutorial tutorial) {
        super(person.getName(), person.getPhone(), person.getEmail(), person.getAddress(), person.getTags());
        this.studentId = studentId;
        this.tutorial = tutorial;
    }

    public StudentID getStudentId() {
        return studentId;
    }

    public Tutorial getTutorial() {
        return tutorial;
    }

}
