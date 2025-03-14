package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static seedu.address.logic.commands.CommandTestUtil.DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_HANDLE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ID_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.EditCommand.EditStudentDescriptor;
import seedu.address.testutil.EditStudentDescriptorBuilder;

public class EditStudentDescriptorTest {

    @Test
    public void equals() {
        // same values -> ok
        EditStudentDescriptor descriptorWithSameValues = new EditStudentDescriptor(DESC_AMY);
        assertEquals(DESC_AMY, descriptorWithSameValues);

        // same object -> ok
        assertEquals(DESC_AMY, DESC_AMY);

        // null -> fail
        assertNotNull(DESC_AMY);

        // different types -> fail
        assertNotEquals(DESC_AMY, 5);

        // different values -> fail
        assertNotEquals(DESC_AMY, DESC_BOB);

        // different name -> fail
        EditStudentDescriptor editedAmy = new EditStudentDescriptorBuilder(DESC_AMY).withName(VALID_NAME_BOB).build();
        assertNotEquals(DESC_AMY, editedAmy);

        // different student id -> fail
        editedAmy = new EditStudentDescriptorBuilder(DESC_AMY).withStudentId(VALID_ID_BOB).build();
        assertNotEquals(DESC_AMY, editedAmy);

        // different phone -> fail
        editedAmy = new EditStudentDescriptorBuilder(DESC_AMY).withPhone(VALID_PHONE_BOB).build();
        assertNotEquals(DESC_AMY, editedAmy);

        // different email -> fail
        editedAmy = new EditStudentDescriptorBuilder(DESC_AMY).withEmail(VALID_EMAIL_BOB).build();
        assertNotEquals(DESC_AMY, editedAmy);

        // different handle -> fail
        editedAmy = new EditStudentDescriptorBuilder(DESC_AMY).withHandle(VALID_HANDLE_BOB).build();
        assertNotEquals(DESC_AMY, editedAmy);
    }

    @Test
    public void toStringMethod() {
        EditStudentDescriptor editStudentDescriptor = new EditStudentDescriptor();
        String expected = EditStudentDescriptor.class.getCanonicalName() + "{name="
                        + editStudentDescriptor.getName().orElse(null) + ", studentId="
                        + editStudentDescriptor.getStudentId().orElse(null) + ", phone="
                        + editStudentDescriptor.getPhone().orElse(null) + ", email="
                        + editStudentDescriptor.getEmail().orElse(null) + ", handle="
                        + editStudentDescriptor.getHandle().orElse(null) + ", tutorials="
                        + editStudentDescriptor.getTutorials().orElse(null) + "}";
        assertEquals(expected, editStudentDescriptor.toString());
    }
}
