package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.function.Predicate;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.student.Student;
import seedu.address.model.student.UniqueStudentList;
import seedu.address.model.tutorial.Tutorial;

/**
 * Wraps all data at the address-book level Duplicates are not allowed (by
 * .isSamePerson comparison)
 */
public class AddressBook implements ReadOnlyAddressBook {

    private final UniqueStudentList students;
    private final ObservableList<Tutorial> tutorials;

    /*
     * The 'unusual' code block below is a non-static initialization block,
     * sometimes used to avoid duplication between constructors. See
     * https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other
     * ways to avoid duplication among constructors.
     */
    {
        students = new UniqueStudentList();
        tutorials = FXCollections.observableArrayList();
    }

    public AddressBook() {
    }

    /**
     * Creates an AddressBook using the students in the {@code toBeCopied}
     */
    public AddressBook(ReadOnlyAddressBook toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the student list with {@code students}.
     * {@code students} must not contain duplicate students.
     */
    public void setStudents(List<Student> students) {
        this.students.setStudents(students);
    }

    /**
     * Resets the existing data of this {@code AddressBook} with {@code newData}.
     */
    public void resetData(ReadOnlyAddressBook newData) {
        requireNonNull(newData);

        setStudents(newData.getStudentList());
        setTutorials(newData.getTutorialList());
    }

    //// student-level operations

    /**
     * Returns true if a student with the same identity as {@code student} exists in
     * the address book.
     */
    public boolean hasStudent(Student student) {
        requireNonNull(student);
        return students.contains(student);
    }

    /**
     * Adds a student to the address book. The student must not already exist in the
     * address book.
     */
    public void addStudent(Student p) {
        students.add(p);
    }

    /**
     * Replaces the given student {@code target} in the list with
     * {@code editedstudent}. {@code target} must exist in the address book. The
     * student identity of {@code editedstudent} must not be the same as another
     * existing student in the address book.
     */
    public void setStudent(Student target, Student editedstudent) {
        requireNonNull(editedstudent);

        students.setStudent(target, editedstudent);
    }

    /**
     * Removes {@code key} from this {@code AddressBook}. {@code key} must exist in
     * the address book.
     */
    public void removeStudent(Student key) {
        students.remove(key);
    }

    //// Tutorial operations

    /**
     * Adds a tutorial slot
     */
    public void addTutorial(Tutorial tutorial) {
        tutorials.add(tutorial);
    }

    /**
     * Deletes a tutorial slot
     */
    public void deleteTutorial(Tutorial tutorial) {
        tutorials.remove(tutorial);
    }

    /**
     * Checks whether tutorial exists in the address book
     */
    public boolean hasTutorial(Tutorial tutorial) {
        requireNonNull(tutorial);
        return tutorials.contains(tutorial);
    }

    /**
     * Replaces the contents of the tutorial list with {@code tutorials}.
     * {@code tutorials} must not contain duplicate tutorials.
     */
    public void setTutorials(List<Tutorial> tutorials) {
        requireNonNull(tutorials);

        tutorials.stream().filter(Predicate.not(this::hasTutorial)).forEach(this::addTutorial);
    }

    //// util methods

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("students", students).add("tutorials", tutorials).toString();
    }

    @Override
    public ObservableList<Student> getStudentList() {
        return students.asUnmodifiableObservableList();
    }

    @Override
    public ObservableList<Tutorial> getTutorialList() {
        return FXCollections.unmodifiableObservableList(tutorials);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AddressBook)) {
            return false;
        }

        AddressBook otherAddressBook = (AddressBook) other;
        return students.equals(otherAddressBook.students) && tutorials.equals(otherAddressBook.tutorials);
    }

    @Override
    public int hashCode() {
        return students.hashCode();
    }
}
