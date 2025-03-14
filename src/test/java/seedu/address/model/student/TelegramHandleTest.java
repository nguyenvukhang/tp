package seedu.address.model.student;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class TelegramHandleTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, (
        ) -> new TelegramHandle(null));
    }

    @Test
    public void constructor_invalidTelegramHandle_throwsIllegalArgumentException() {
        String invalidTelegramHandle = "";
        assertThrows(IllegalArgumentException.class, (
        ) -> new TelegramHandle(invalidTelegramHandle));
    }

    @Test
    public void isValidTelegramHandle() {
        // null handle
        assertThrows(NullPointerException.class, (
        ) -> TelegramHandle.isValidHandle(null));

        // invalid handles
        assertFalse(TelegramHandle.isValidHandle("")); // empty string
        assertFalse(TelegramHandle.isValidHandle(" ")); // spaces only
        assertFalse(TelegramHandle.isValidHandle("@joe")); // less than 4 characters
        assertFalse(TelegramHandle.isValidHandle("someone")); // no '@' prefix
        assertFalse(TelegramHandle.isValidHandle("@h@//o")); // special characters
        assertFalse(TelegramHandle.isValidHandle("@CAPITAL_LETTERS")); // capital letters
        assertFalse(TelegramHandle.isValidHandle("@more_than_32_characters_long_so_invalid")); // long handle

        // valid handles
        assertTrue(TelegramHandle.isValidHandle("@john_doe"));

    }

    @Test
    public void equals() {
        TelegramHandle handle = new TelegramHandle("@valid_telegram_handle");

        // same values -> ok
        assertEquals(handle, new TelegramHandle("@valid_telegram_handle"));

        // same object -> ok
        assertTrue(handle.equals(handle));

        // null -> fail
        assertNotNull(handle);

        // different types -> fail
        assertNotEquals(handle, 5.0f);

        // different values -> fail
        assertNotEquals(handle, new TelegramHandle("@other_valid_telegram_handle"));
    }
}
