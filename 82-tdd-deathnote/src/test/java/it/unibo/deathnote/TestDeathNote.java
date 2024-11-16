package it.unibo.deathnote;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.deathnote.api.DeathNote;
import it.unibo.deathnote.impl.DeathNoteImplementation;

import static java.lang.Thread.sleep;

class TestDeathNote {
    private DeathNote deathNote;
    private final static int INVALID_CAUSE_TIME = 100;
    private static final int INVALID_DETAILS_TIME = 6000 + INVALID_CAUSE_TIME;


    @BeforeEach
    void setUp() {
        deathNote = new DeathNoteImplementation();
    }

    @Test
    void testGetRule_InvalidNumber() {
        try {
            deathNote.getRule(0);
            fail("Expected an IllegalArgumentException for rule number zero");
        } catch (Exception e) {
            assertEquals("Rule number is smaller than 1 or larger than the number of rules", e.getMessage());
            assertNotNull(e.getMessage(), "Exception message should not be empty");
            assertFalse(e.getMessage().isBlank(), "Exception message should not be blank");
        }
    }

    @Test
    void testWriteName() {
        assertFalse(deathNote.isNameWritten("Name Surname"));
        deathNote.writeName("Cristian Qorri");
        assertTrue(deathNote.isNameWritten("Cristian Qorri"));
        assertFalse(deathNote.isNameWritten("Name Surname"));
        assertFalse(deathNote.isNameWritten(""));
    }

    @Test
    void testWriteDeathCause() throws InterruptedException{
        try {
            deathNote.writeDeathCause("homicide");
            fail("expected illegalstateexceptions");
        } catch (IllegalStateException e) {
            assertEquals("cause is null", e.getMessage());
        }
        deathNote.writeName("Crsitian Qorri");
        assertTrue(deathNote.writeDeathCause("heart attack"));
        assertEquals("heart attack", deathNote.getDeathCause("Crsitian Qorri"));
        deathNote.writeName("Pinco Pallo");
        assertTrue(deathNote.writeDeathCause("karting incident"));

        assertEquals("karting incident", deathNote.getDeathCause("Pinco Pallo"));

        sleep(INVALID_CAUSE_TIME);

        assertFalse(deathNote.writeDeathCause("wrote many tests before dying"));
        assertEquals("karting incident", deathNote.getDeathCause("Pinco Pallo"));
    }

    @Test
    void testWriteDeathDetails() throws InterruptedException {
        try {
            deathNote.writeDetails("killeb by incident");
            fail("expected illegalstateExceptions");
        } catch (IllegalStateException e) {
            assertEquals("details is null", e.getMessage());
        }
        deathNote.writeName("cristian qorri");
        assertEquals("", deathNote.getDeathDetails("cristian qorri"));
        assertTrue(deathNote.writeDetails("run for a long time"));
        assertEquals("run for a long time", deathNote.getDeathDetails("cristian qorri"));
        deathNote.writeName("pinco pallo");
        assertTrue(deathNote.writeDetails("karting accident"));

        assertEquals("karting accident", deathNote.getDeathDetails("pinco pallo"));

        sleep(INVALID_DETAILS_TIME);

        assertFalse(deathNote.writeDetails("wrote many tests before dying"));
        assertEquals("karting accident", deathNote.getDeathDetails("pinco pallo"));


    }
}