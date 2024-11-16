package it.unibo.deathnote;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
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
            assertEquals("rule number is smaller than 1 or larger than the number of rules", e.getMessage());
            assertNotNull(e.getMessage(), "message of exceptions it is not null");
            assertFalse(e.getMessage().isBlank(), "il messaggio di eccezzione non può essere vuoto o composto da spazi");
        }
    }
    
}