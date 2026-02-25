package com.lab;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Every test runs against every implementation.
 * No parameterized tests. No if/else.
 * When a bug class is passed a test it should FAIL, it fails.
 * When it passes a test it should PASS, it passes.
 *
 * Naming: <ClassName>_<whatIsBeingTested>
 */
public class PasswordTest {

    //  T1 – trimming whitespace → accepts "  ValidPassword1  "
    //  FAILS on: BugDoesNotTrim

    @Test
    void BugDoesNotTrim_trimmedPasswordMatchesCorrectHash() throws Exception {
        BugDoesNotTrim p1 = new BugDoesNotTrim(" Abcdefghij12 ");
        Password       p2 = new Password("Abcdefghij12");
        assertTrue(p1.isPasswordSame(p2)); //  FAILS – hash includes spaces because it does not trim
    }

    @Test
    void BugDoesNotTrim_differentPasswordsAreNotSame() throws Exception {
        BugDoesNotTrim p1 = new BugDoesNotTrim("Abcdefghij12");
        Password       p2 = new Password("Abcdefghij99");
        assertFalse(p1.isPasswordSame(p2)); //  PASSES
    }

    @Test
    void BugDoesNotTrim_noNumberThrowsException() {
        assertThrows(Exception.class, () -> new BugDoesNotTrim("Abcdefghijjj")); //  PASSES
    }

    //  T2 – isPasswordSame should return false for different passwords
    //  FAILS on: BugIsPasswordSameAlwaysTrue

    @Test
    void BugIsPasswordSameAlwaysTrue_differentPasswordsAreNotSame() throws Exception {
        BugIsPasswordSameAlwaysTrue p1 = new BugIsPasswordSameAlwaysTrue("Abcdefghij12");
        Password                    p2 = new Password("Abcdefghij99");
        assertFalse(p1.isPasswordSame(p2)); // FAILS – bug always returns true
    }

    @Test
    void BugIsPasswordSameAlwaysTrue_noNumberThrowsException() {
        assertThrows(Exception.class, () -> new BugIsPasswordSameAlwaysTrue("Abcdefghijjj")); //  PASSES
    }

    @Test
    void BugIsPasswordSameAlwaysTrue_shortPasswordThrowsException() {
        assertThrows(Exception.class, () -> new BugIsPasswordSameAlwaysTrue("Short1")); //  PASSES
    }


    //  T3 – password without a number should throw
    //  FAILS on: BugMissingNumberCheck
    @Test
    void BugMissingNumberCheck_noNumberThrowsException() {
        assertThrows(Exception.class, () -> new BugMissingNumberCheck("Abcdefghijjj")); // FAILS – no number check
    }

    @Test
    void BugMissingNumberCheck_differentPasswordsAreNotSame() throws Exception {
        BugMissingNumberCheck p1 = new BugMissingNumberCheck("Abcdefghij12");
        Password              p2 = new Password("Abcdefghij99");
        assertFalse(p1.isPasswordSame(p2)); // PASSES
    }

    @Test
    void BugMissingNumberCheck_shortPasswordThrowsException() {
        assertThrows(Exception.class, () -> new BugMissingNumberCheck("Short1")); // PASSES
    }

    //  T4 – short password (< 12 chars) should throw
    //  FAILS on: BugMissingPasswordLengthCheck

    @Test
    void BugMissingPasswordLengthCheck_shortPasswordThrowsException() {
        assertThrows(Exception.class, () -> new BugMissingPasswordLengthCheck("Short1")); // FAILS – no length check
    }

    @Test
    void BugMissingPasswordLengthCheck_differentPasswordsAreNotSame() throws Exception {
        BugMissingPasswordLengthCheck p1 = new BugMissingPasswordLengthCheck("Abcdefghij12");
        Password                      p2 = new Password("Abcdefghij99");
        assertFalse(p1.isPasswordSame(p2)); // PASSES
    }

    @Test
    void BugMissingPasswordLengthCheck_noNumberThrowsException() {
        assertThrows(Exception.class, () -> new BugMissingPasswordLengthCheck("Abcdefghijjj")); //  PASSES
    }

    //  T5 – 11-char password should throw (boundary: < 12)
    //  FAILS on: BugToShortPassword

    @Test
    void BugToShortPassword_11charPasswordThrowsException() {
        assertThrows(Exception.class, () -> new BugToShortPassword("Abcdefghi11")); //  FAILS – allows 11 chars (bug: < 11 instead of < 12)
    }

    @Test
    void BugToShortPassword_differentPasswordsAreNotSame() throws Exception {
        BugToShortPassword p1 = new BugToShortPassword("Abcdefghij12");
        Password           p2 = new Password("Abcdefghij99");
        assertFalse(p1.isPasswordSame(p2)); // ✅ PASSES
    }

    @Test
    void BugToShortPassword_noNumberThrowsException() {
        assertThrows(Exception.class, () -> new BugToShortPassword("Abcdefghijjj")); //  PASSES
    }

    //  T6 – 7-char password should throw (boundary: < 12)
    //  FAILS on: BugVeryShort

    @Test
    void BugVeryShort_7charPasswordThrowsException() {
        assertThrows(Exception.class, () -> new BugVeryShort("Abcdef1")); //  FAILS – allows 7 chars (bug: < 6 instead of < 12)
    }

    @Test
    void BugVeryShort_differentPasswordsAreNotSame() throws Exception {
        BugVeryShort p1 = new BugVeryShort("Abcdefghij12");
        Password     p2 = new Password("Abcdefghij99");
        assertFalse(p1.isPasswordSame(p2)); //  PASSES
    }

    @Test
    void BugVeryShort_noNumberThrowsException() {
        assertThrows(Exception.class, () -> new BugVeryShort("Abcdefghijjj")); //  PASSES
    }

    //  T7 – exception message for short password should be "To short password"
    //  FAILS on: BugWrongExceptionMessage

    @Test
    void BugWrongExceptionMessage_shortPasswordHasCorrectMessage() {
        Exception ex = assertThrows(Exception.class, () -> new BugWrongExceptionMessage("Short1"));
        assertEquals("To short password", ex.getMessage()); //  FAILS – bug throws "Wrong message"
    }

    @Test
    void BugWrongExceptionMessage_differentPasswordsAreNotSame() throws Exception {
        BugWrongExceptionMessage p1 = new BugWrongExceptionMessage("Abcdefghij12");
        Password                 p2 = new Password("Abcdefghij99");
        assertFalse(p1.isPasswordSame(p2)); // PASSES
    }

    @Test
    void BugWrongExceptionMessage_noNumberThrowsException() {
        assertThrows(Exception.class, () -> new BugWrongExceptionMessage("Abcdefghijjj")); // PASSES
    }

    //  T8 – same password string should produce same hash
    //  FAILS on: BugWrongHashingAlgorithm

    @Test
    void BugWrongHashingAlgorithm_samePasswordIsSame() throws Exception {
        BugWrongHashingAlgorithm p1 = new BugWrongHashingAlgorithm("Abcdefghij12");
        Password                 p2 = new Password("Abcdefghij12");
        assertTrue(p1.isPasswordSame(p2)); //  FAILS – different hashing algorithm produces different hash
    }

    @Test
    void BugWrongHashingAlgorithm_differentPasswordsAreNotSame() throws Exception {
        BugWrongHashingAlgorithm p1 = new BugWrongHashingAlgorithm("Abcdefghij12");
        Password                 p2 = new Password("Abcdefghij99");
        assertFalse(p1.isPasswordSame(p2)); //  PASSES
    }

    @Test
    void BugWrongHashingAlgorithm_noNumberThrowsException() {
        assertThrows(Exception.class, () -> new BugWrongHashingAlgorithm("Abcdefghijjj")); //  PASSES
    }

    // =============================================================
    //  BugPassesAllTests – should PASS all of these
    // =============================================================

    @Test
    void BugPassesAllTests_trimmedPasswordMatchesCorrectHash() throws Exception {
        BugPassesAllTests p1 = new BugPassesAllTests(" Abcdefghij12 ");
        BugPassesAllTests p2 = new BugPassesAllTests("Abcdefghij12");
        assertTrue(p1.isPasswordSame(p2)); //  PASSES
    }

    @Test
    void BugPassesAllTests_differentPasswordsAreNotSame() throws Exception {
        BugPassesAllTests p1 = new BugPassesAllTests("Abcdefghij12");
        BugPassesAllTests p2 = new BugPassesAllTests("Abcdefghij99");
        assertFalse(p1.isPasswordSame(p2)); //  PASSES
    }

    @Test
    void BugPassesAllTests_noNumberThrowsException() {
        assertThrows(Exception.class, () -> new BugPassesAllTests("Abcdefghijjj")); //  PASSES
    }

    @Test
    void BugPassesAllTests_shortPasswordThrowsException() {
        assertThrows(Exception.class, () -> new BugPassesAllTests("Short1")); //  PASSES
    }

    @Test
    void BugPassesAllTests_11charPasswordThrowsException() {
        assertThrows(Exception.class, () -> new BugPassesAllTests("Abcdefghi11")); //  PASSES
    }

    @Test
    void BugPassesAllTests_7charPasswordThrowsException() {
        assertThrows(Exception.class, () -> new BugPassesAllTests("Abcdef1")); //  PASSES
    }

    @Test
    void BugPassesAllTests_shortPasswordHasCorrectMessage() {
        Exception ex = assertThrows(Exception.class, () -> new BugPassesAllTests("Short1"));
        assertEquals("Password must be at least 12 characters long", ex.getMessage()); //  PASSES
    }

    @Test
    void BugPassesAllTests_samePasswordIsSame() throws Exception {
        BugPassesAllTests p1 = new BugPassesAllTests("Abcdefghij12");
        BugPassesAllTests p2 = new BugPassesAllTests("Abcdefghij12");
        assertTrue(p1.isPasswordSame(p2)); //  PASSES
    }
}

