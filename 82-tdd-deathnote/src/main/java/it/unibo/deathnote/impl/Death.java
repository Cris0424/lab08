package it.unibo.deathnote.impl;

public class Death {

    String name;
    String cause; 
    String details;

    private long timeName;
    private long tCause;

    public Death(String name, String string, String string2) {
        this.name = name;
        this.cause = string;
        this.details = string2;
    }

    public String getName() {
        return name;
    }

    public void setNameTime(long timeMillis) {
        this.timeName = timeMillis;
    }

    public void setCause(String cause) {
        this.cause = cause;
        this.tCause = System.currentTimeMillis();
    }

}
