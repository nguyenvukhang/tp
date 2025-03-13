package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.student.Email;
import seedu.address.model.student.Name;
import seedu.address.model.student.Phone;
import seedu.address.model.student.TelegramHandle;
import seedu.address.model.tutorial.Tutorial;

public class ParserUtilTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_HANDLE = " ";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_TUTORIAL = "#TU+OR!A/";

    private static final String VALID_NAME = "Rachel Walker";
    private static final String VALID_PHONE = "123456";
    private static final String VALID_HANDLE = "@some_student";
    private static final String VALID_EMAIL = "rachel@example.com";
    private static final String VALID_TUTORIAL_1 = "T-23";
    private static final String VALID_TUTORIAL_2 = "CS2103_T06";

    private static final String WHITESPACE = " \t\r\n";

    @Test
    public void parseIndex_invalidInput_throwsParseException() {
        assertThrows(ParseException.class, (
        ) -> ParserUtil.parseIndex("10 a"));
    }

    @Test
    public void parseIndex_outOfRangeInput_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_INVALID_INDEX, (
        ) -> ParserUtil.parseIndex(Long.toString(Integer.MAX_VALUE + 1)));
    }

    @Test
    public void parseIndex_validInput_success() throws Exception {
        // No whitespaces
        assertEquals(INDEX_FIRST_PERSON, ParserUtil.parseIndex("1"));

        // Leading and trailing whitespaces
        assertEquals(INDEX_FIRST_PERSON, ParserUtil.parseIndex("  1  "));
    }

    @Test
    public void parseName_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, (
        ) -> ParserUtil.parseName((String) null));
    }

    @Test
    public void parseName_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, (
        ) -> ParserUtil.parseName(INVALID_NAME));
    }

    @Test
    public void parseName_validValueWithoutWhitespace_returnsName() throws Exception {
        Name expectedName = new Name(VALID_NAME);
        assertEquals(expectedName, ParserUtil.parseName(VALID_NAME));
    }

    @Test
    public void parseName_validValueWithWhitespace_returnsTrimmedName() throws Exception {
        String nameWithWhitespace = WHITESPACE + VALID_NAME + WHITESPACE;
        Name expectedName = new Name(VALID_NAME);
        assertEquals(expectedName, ParserUtil.parseName(nameWithWhitespace));
    }

    @Test
    public void parsePhone_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, (
        ) -> ParserUtil.parsePhone((String) null));
    }

    @Test
    public void parsePhone_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, (
        ) -> ParserUtil.parsePhone(INVALID_PHONE));
    }

    @Test
    public void parsePhone_validValueWithoutWhitespace_returnsPhone() throws Exception {
        Phone expectedPhone = new Phone(VALID_PHONE);
        assertEquals(expectedPhone, ParserUtil.parsePhone(VALID_PHONE));
    }

    @Test
    public void parsePhone_validValueWithWhitespace_returnsTrimmedPhone() throws Exception {
        String phoneWithWhitespace = WHITESPACE + VALID_PHONE + WHITESPACE;
        Phone expectedPhone = new Phone(VALID_PHONE);
        assertEquals(expectedPhone, ParserUtil.parsePhone(phoneWithWhitespace));
    }

    @Test
    public void parseTelegramHandle_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, (
        ) -> ParserUtil.parseTelegramHandle((String) null));
    }

    @Test
    public void parseTelegramHandle_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, (
        ) -> ParserUtil.parseTelegramHandle(INVALID_HANDLE));
    }

    @Test
    public void parseTelegramHandle_validValueWithoutWhitespace_returnsAddress() throws Exception {
        TelegramHandle expectedAddress = new TelegramHandle(VALID_HANDLE);
        assertEquals(expectedAddress, ParserUtil.parseTelegramHandle(VALID_HANDLE));
    }

    @Test
    public void parseTelegramHandle_validValueWithWhitespace_returnsTrimmedAddress() throws Exception {
        String addressWithWhitespace = WHITESPACE + VALID_HANDLE + WHITESPACE;
        TelegramHandle expectedAddress = new TelegramHandle(VALID_HANDLE);
        assertEquals(expectedAddress, ParserUtil.parseTelegramHandle(addressWithWhitespace));
    }

    @Test
    public void parseEmail_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, (
        ) -> ParserUtil.parseEmail((String) null));
    }

    @Test
    public void parseEmail_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, (
        ) -> ParserUtil.parseEmail(INVALID_EMAIL));
    }

    @Test
    public void parseEmail_validValueWithoutWhitespace_returnsEmail() throws Exception {
        Email expectedEmail = new Email(VALID_EMAIL);
        assertEquals(expectedEmail, ParserUtil.parseEmail(VALID_EMAIL));
    }

    @Test
    public void parseEmail_validValueWithWhitespace_returnsTrimmedEmail() throws Exception {
        String emailWithWhitespace = WHITESPACE + VALID_EMAIL + WHITESPACE;
        Email expectedEmail = new Email(VALID_EMAIL);
        assertEquals(expectedEmail, ParserUtil.parseEmail(emailWithWhitespace));
    }

    @Test
    public void parseTutorial_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, (
        ) -> ParserUtil.parseTutorial(null));
    }

    @Test
    public void parseTutorial_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, (
        ) -> ParserUtil.parseTutorial(INVALID_TUTORIAL));
    }

    @Test
    public void parseTutorial_validValueWithoutWhitespace_returnsTag() throws Exception {
        Tutorial expectedTag = new Tutorial(VALID_TUTORIAL_1);
        assertEquals(expectedTag, ParserUtil.parseTutorial(VALID_TUTORIAL_1));
    }

    @Test
    public void parseTutorial_validValueWithWhitespace_returnsTrimmedTag() throws Exception {
        String tagWithWhitespace = WHITESPACE + VALID_TUTORIAL_1 + WHITESPACE;
        Tutorial expectedTag = new Tutorial(VALID_TUTORIAL_1);
        assertEquals(expectedTag, ParserUtil.parseTutorial(tagWithWhitespace));
    }

    @Test
    public void parseTutorials_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, (
        ) -> ParserUtil.parseTutorials(null));
    }

    @Test
    public void parseTutorials_collectionWithInvalidTags_throwsParseException() {
        assertThrows(ParseException.class, (
        ) -> ParserUtil.parseTutorials(Arrays.asList(VALID_TUTORIAL_1, INVALID_TUTORIAL)));
    }

    @Test
    public void parseTutorials_emptyCollection_returnsEmptySet() throws Exception {
        assertTrue(ParserUtil.parseTutorials(Collections.emptyList()).isEmpty());
    }

    @Test
    public void parseTutorials_collectionWithValidTags_returnsTagSet() throws Exception {
        Set<Tutorial> actualTutorialSet = ParserUtil.parseTutorials(Arrays.asList(VALID_TUTORIAL_1, VALID_TUTORIAL_2));
        Set<Tutorial> expectedTutorialSet = new HashSet<Tutorial>(
                        Arrays.asList(new Tutorial(VALID_TUTORIAL_1), new Tutorial(VALID_TUTORIAL_2)));

        assertEquals(expectedTutorialSet, actualTutorialSet);
    }
}
