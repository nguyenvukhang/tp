package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.student.Email;
import seedu.address.model.student.Name;
import seedu.address.model.student.Phone;
import seedu.address.model.student.Student;
import seedu.address.model.student.StudentID;
import seedu.address.model.student.TelegramHandle;
import seedu.address.model.tutorial.Tutorial;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Student[] getSampleStudents() {
        return new Student[]{
            new Student(new Name("Alex Yeoh"), new StudentID("A0545648B"), new Phone("87438807"),
                            new Email("alexyeoh@example.com"), new TelegramHandle("@alex_yeoh"),
                            getTutorialSet("CS2103-T1")),
            new Student(new Name("Bernice Yu"), new StudentID("A0209557P"), new Phone("99272758"),
                            new Email("berniceyu@example.com"), new TelegramHandle("@bernice_yu"),
                            getTutorialSet("CS2103-T2", "CS2106-T45")),
            new Student(new Name("Charlotte Oliveiro"), new StudentID("A0185783R"), new Phone("93210283"),
                            new Email("charlotte@example.com"), new TelegramHandle("@charlotte_oliveiro"),
                            getTutorialSet("CS2103_T2")),
            new Student(new Name("David Li"), new StudentID("A0209380W"), new Phone("91031282"),
                            new Email("lidavid@example.com"), new TelegramHandle("@li_david"),
                            getTutorialSet("CS2106-T37")),
            new Student(new Name("Irfan Ibrahim"), new StudentID("A0345681Q"), new Phone("92492021"),
                            new Email("irfan@example.com"), new TelegramHandle("@irfaaan"),
                            getTutorialSet("CS2106-T37")),
            new Student(new Name("Roy Balakrishnan"), new StudentID("A0805307V"), new Phone("92624417"),
                            new Email("royb@example.com"), new TelegramHandle("@royb"), getTutorialSet("CS2106-T37"))};
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Student sampleStudent : getSampleStudents()) {
            sampleAb.addStudent(sampleStudent);
        }
        return sampleAb;
    }

    /**
     * Returns a tutorial set containing the list of strings given.
     */
    public static Set<Tutorial> getTutorialSet(String... strings) {
        return Arrays.stream(strings).map(Tutorial::new).collect(Collectors.toSet());
    }

}
