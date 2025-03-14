package seedu.address.testutil;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.logic.commands.EditCommand.EditStudentDescriptor;
import seedu.address.model.student.Email;
import seedu.address.model.student.Name;
import seedu.address.model.student.Phone;
import seedu.address.model.student.Student;
import seedu.address.model.student.StudentID;
import seedu.address.model.student.TelegramHandle;
import seedu.address.model.tutorial.Tutorial;

/**
 * A utility class to help with building EditStudentDescriptor objects.
 */
public class EditStudentDescriptorBuilder {

    private EditStudentDescriptor descriptor;

    public EditStudentDescriptorBuilder() {
        descriptor = new EditStudentDescriptor();
    }

    public EditStudentDescriptorBuilder(EditStudentDescriptor descriptor) {
        this.descriptor = new EditStudentDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditStudentDescriptor} with fields containing
     * {@code student}'s details
     */
    public EditStudentDescriptorBuilder(Student student) {
        descriptor = new EditStudentDescriptor();
        descriptor.setName(student.getName());
        descriptor.setStudentId(student.getStudentId());
        descriptor.setPhone(student.getPhone());
        descriptor.setEmail(student.getEmail());
        descriptor.setHandle(student.getHandle());
        descriptor.setTutorials(student.getTutorials());
    }

    /**
     * Sets the {@code Name} of the {@code EditStudentDescriptor} that we are
     * building.
     */
    public EditStudentDescriptorBuilder withName(String name) {
        descriptor.setName(new Name(name));
        return this;
    }

    /**
     * Sets the {@code StudentID} of the {@code EditStudentDescriptor} that we are
     * building.
     */
    public EditStudentDescriptorBuilder withStudentId(String studentId) {
        descriptor.setStudentId(new StudentID(studentId));
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code EditStudentDescriptor} that we are
     * building.
     */
    public EditStudentDescriptorBuilder withPhone(String phone) {
        descriptor.setPhone(new Phone(phone));
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code EditStudentDescriptor} that we are
     * building.
     */
    public EditStudentDescriptorBuilder withEmail(String email) {
        descriptor.setEmail(new Email(email));
        return this;
    }

    /**
     * Sets the {@code TelegramHandle} of the {@code EditStudentDescriptor} that we
     * are building.
     */
    public EditStudentDescriptorBuilder withHandle(String handle) {
        descriptor.setHandle(new TelegramHandle(handle));
        return this;
    }

    /**
     * Parses the {@code tutorials} into a {@code Set<Tutorial>} and set it to the
     * {@code EditStudentDescriptor} that we are building.
     */
    public EditStudentDescriptorBuilder withTutorials(String... tutorials) {
        Set<Tutorial> tutorialSet = Stream.of(tutorials).map(Tutorial::new).collect(Collectors.toSet());
        descriptor.setTutorials(tutorialSet);
        return this;
    }

    public EditStudentDescriptor build() {
        return descriptor;
    }
}
