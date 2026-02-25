package com.examination;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ExamTest {

    Exam exam = new Exam();

    @Test
    void emptyString_shouldReturnEmpty() {
        assertEquals("", exam.emptyString());
    }

    @Test
    void multiply_shouldReturnCorrectResult() {
        assertEquals(6, exam.multiply(2, 3));
    }

    @Test
    void divide_shouldReturnCorrectResult() {
        assertEquals(2, exam.divide(6, 3));
    }

    @Test
    void divide_shouldThrowException_whenDividingByZero() {
        assertThrows(IllegalArgumentException.class, () -> {
            exam.divide(5, 0);
        });
    }

    @Test
    void isPassed_shouldReturnTrue_whenScoreAbove50() {
        assertTrue(exam.isPassed(80));
    }

    @Test
    void isPassed_shouldReturnFalse_whenBelow50() {
        assertFalse(exam.isPassed(30));
    }

    @Test
    void isPassed_shouldThrowException_whenScoreInvalid() {
        assertThrows(IllegalArgumentException.class, () -> {
            exam.isPassed(150);
        });
    }
}