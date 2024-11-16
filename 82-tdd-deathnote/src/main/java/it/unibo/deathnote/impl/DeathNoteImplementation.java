package it.unibo.deathnote.impl;
import java.util.ArrayList;
import java.util.List; 

import it.unibo.deathnote.api.DeathNote;

public  class DeathNoteImplementation implements DeathNote {

    private final List<Death> deaths;

    private String lastName;

    public DeathNoteImplementation() {
        deaths = new ArrayList<>();
    }
    
    @Override
    public String getRule(int ruleNumber) {
        if (ruleNumber < 1 || ruleNumber > RULES.size()) {
            throw new IllegalArgumentException("rule number is smaller than 1 or larger than the number of rules");
        } 
        return RULES.get(ruleNumber - 1);
    }

    @Override
    public void writeName(String name) {
        if (name == null) {
            throw new NullPointerException("name is equals null");
        } else {
            Death newDeathPers = new Death(name, "", "");
            deaths.add(newDeathPers);
            newDeathPers.setNameTime(System.currentTimeMillis());
            newDeathPers.setCause("");
            lastName = name;
        }
    }

    @Override
    public boolean writeDeathCause(String cause) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'writeDeathCause'");
    }

    @Override
    public boolean writeDetails(String details) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'writeDetails'");
    }

    @Override
    public String getDeathCause(String name) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getDeathCause'");
    }

    @Override
    public String getDeathDetails(String name) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getDeathDetails'");
    }

    @Override
    public boolean isNameWritten(String name) {
        for (Death death : deaths) {
            if (death.getName()== name) {
                return true;
            }
        }
                return false;
    }

}