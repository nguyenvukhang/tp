package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_HANDLE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_HANDLE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TUTORIAL_1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TUTORIAL_2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.student.Student;
import seedu.address.model.tutorial.Tutorial;

/**
 * A utility class containing a list of {@code Student} objects to be used in
 * tests.
 */
public class TypicalStudents {

    public static final Student ALICE = new StudentBuilder().withName("Alice Pauline")
                    .withHandle("123, Jurong West Ave 6, #08-111").withEmail("alice@example.com").withPhone("94351253")
                    .withTags("friends").build();
    public static final Student BENSON = new StudentBuilder().withName("Benson Meier")
                    .withHandle("311, Clementi Ave 2, #02-25").withEmail("johnd@example.com").withPhone("98765432")
                    .withTags("owesMoney", "friends").build();
    public static final Student CARL = new StudentBuilder().withName("Carl Kurz").withPhone("95352563")
                    .withEmail("heinz@example.com").withHandle("wall street").build();
    public static final Student DANIEL = new StudentBuilder().withName("Daniel Meier").withPhone("87652533")
                    .withEmail("cornelia@example.com").withHandle("10th street").withTags("friends").build();
    public static final Student ELLE = new StudentBuilder().withName("Elle Meyer").withPhone("9482224")
                    .withEmail("werner@example.com").withHandle("michegan ave").build();
    public static final Student FIONA = new StudentBuilder().withName("Fiona Kunz").withPhone("9482427")
                    .withEmail("lydia@example.com").withHandle("little tokyo").build();
    public static final Student GEORGE = new StudentBuilder().withName("George Best").withPhone("9482442")
                    .withEmail("anna@example.com").withHandle("4th street").build();

    // Manually added
    public static final Student HOON = new StudentBuilder().withName("Hoon Meier").withPhone("8482424")
                    .withEmail("stefan@example.com").withHandle("little india").build();
    public static final Student IDA = new StudentBuilder().withName("Ida Mueller").withPhone("8482131")
                    .withEmail("hans@example.com").withHandle("chicago ave").build();

    // Manually added - Student's details found in {@code CommandTestUtil}
    public static final Student AMY = new StudentBuilder().withName(VALID_NAME_AMY).withPhone(VALID_PHONE_AMY)
                    .withEmail(VALID_EMAIL_AMY).withHandle(VALID_HANDLE_AMY).withTags(VALID_TUTORIAL_1).build();
    public static final Student BOB = new StudentBuilder().withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
                    .withEmail(VALID_EMAIL_BOB).withHandle(VALID_HANDLE_BOB)
                    .withTags(VALID_TUTORIAL_2, VALID_TUTORIAL_1).build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalStudents() {
    } // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical students.
     */
    public static AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();
        for (Student student : getTypicalStudents()) {
            ab.addStudent(student);
        }
        return ab;
    }

    /**
     * Returns an {@code AddressBook} with all the typical students and tutorials.
     */
    public static AddressBook getTypicalAddressBookInclTutorials() {
        AddressBook ab = new AddressBook();
        for (Student student : getTypicalStudents()) {
            ab.addStudent(student);
        }
        for (var t : getTypicalTutorials()) {
            ab.addTutorial(t);
        }
        return ab;
    }

    public static List<Student> getTypicalStudents() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }

    public static List<Tutorial> getTypicalTutorials() {
        return List.of(new Tutorial("Tutorial_1"), new Tutorial("Tutorial-2"));
    }
}
