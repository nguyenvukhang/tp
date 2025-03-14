package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_HANDLE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_HANDLE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ID_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ID_BOB;
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

    public static final Student ALICE = new StudentBuilder().withName("Alice Pauline").withStudentId("A0743062E")
                    .withHandle("@alice_pauline").withEmail("alice@example.com").withPhone("94351253")
                    .withTutorials("CS2103-T23").build();
    public static final Student BENSON = new StudentBuilder().withName("Benson Meier").withStudentId("A0179034R")
                    .withHandle("@benson_meier").withEmail("johnd@example.com").withPhone("98765432")
                    .withTutorials("CS2103-T23", "CS2106-T02").build();
    public static final Student CARL = new StudentBuilder().withName("Carl Kurz").withPhone("95352563")
                    .withStudentId("A0388094Q").withEmail("heinz@example.com").withHandle("@carl_kurz").build();
    public static final Student DANIEL = new StudentBuilder().withName("Daniel Meier").withPhone("87652533")
                    .withStudentId("A0833488W").withEmail("cornelia@example.com").withHandle("@dannn_mayor")
                    .withTutorials("CS2103-T23").build();
    public static final Student ELLE = new StudentBuilder().withName("Elle Meyer").withPhone("9482224")
                    .withStudentId("A0172925M").withEmail("werner@example.com").withHandle("@ellem").build();
    public static final Student FIONA = new StudentBuilder().withName("Fiona Kunz").withPhone("9482427")
                    .withStudentId("A0005984F").withEmail("lydia@example.com").withHandle("@kunz_fiona").build();
    public static final Student GEORGE = new StudentBuilder().withName("George Best").withPhone("9482442")
                    .withStudentId("A0443446N").withEmail("anna@example.com").withHandle("@the_best_george").build();

    // Manually added
    public static final Student HOON = new StudentBuilder().withName("Hoon Meier").withPhone("8482424")
                    .withStudentId("A0478925C").withEmail("stefan@example.com").withHandle("@bee_hoon").build();
    public static final Student IDA = new StudentBuilder().withName("Ida Mueller").withPhone("8482131")
                    .withStudentId("A0840139C").withEmail("hans@example.com").withHandle("@ida_pro").build();

    // Manually added - Student's details found in {@code CommandTestUtil}
    public static final Student AMY = new StudentBuilder().withName(VALID_NAME_AMY).withPhone(VALID_PHONE_AMY)
                    .withStudentId(VALID_ID_AMY).withEmail(VALID_EMAIL_AMY).withHandle(VALID_HANDLE_AMY)
                    .withTutorials(VALID_TUTORIAL_1).build();
    public static final Student BOB = new StudentBuilder().withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
                    .withStudentId(VALID_ID_BOB).withEmail(VALID_EMAIL_BOB).withHandle(VALID_HANDLE_BOB)
                    .withTutorials(VALID_TUTORIAL_2, VALID_TUTORIAL_1).build();

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
