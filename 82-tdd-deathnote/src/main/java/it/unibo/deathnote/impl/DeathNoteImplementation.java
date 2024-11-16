package it.unibo.deathnote.impl;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import it.unibo.deathnote.api.DeathNote;

public  class DeathNoteImplementation implements DeathNote {

    private static final int FOURTY_MILLSEC = 40;
    int INVALID_CAUSE_TIME = 100;

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
        if (cause == null || deaths.isEmpty()) {
            throw new IllegalStateException("cause is null");
        }
        for (Death death : deaths) {
            if (death.getName() == lastName) {
                if (System.currentTimeMillis() - death.getNameTime() < FOURTY_MILLSEC) {
                    death.setCause(cause);
                    return true;
                } else {
                    return false;
                }
            }
        }
        return false;
    }

    @Override
    public boolean writeDetails(String details) {
        if (details == null || deaths.isEmpty() ) {
            throw new IllegalStateException("details is null");
        }
        // recupero l'oggetto death
        Death lasDeath = deaths.get(deaths.size() - 1); // oggetto lastDeath rappresenta l'ultimo decesso registrato, su cui verranno aggiunti i dettagli
        if (System.currentTimeMillis() - lasDeath.getTimeCause() < INVALID_CAUSE_TIME) {
            lasDeath.setDetail(details);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String getDeathCause(String name) {
        if(isNameWritten(name) == false){
            throw new IllegalStateException("name not written");
        }
        for(Death death : deaths){
            if(Objects.equals(death.getName(), name)){
                if(death.getCause() == null){
                    return "heart attack";
                }else {
                    return death.getCause();
                }
            }
        }
        return null;
    }

    @Override
    public String getDeathDetails(String name) {
        if (isNameWritten(name) == false) {
            throw new IllegalArgumentException("name not written");
        }
        for (Death death : deaths) {
            if (Objects.equals(death.getName(), name)) {
                if (death.getDetails() == null) {
                    return "no datails";
                } else {
                    return death.getDetails();
                }
            }
        }
                return null; // auto compialto
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