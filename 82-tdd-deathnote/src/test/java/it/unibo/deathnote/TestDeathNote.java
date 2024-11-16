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

class TestDeathNote {
    private DeathNote deathNote;

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
    
}