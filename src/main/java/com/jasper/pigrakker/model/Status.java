package com.jasper.pigrakker.model;

public enum Status {
    NEEDSPAYREQUEST("Heeft betaalverzoek nodig"),
    NEEDSTOPAY("Heeft nog niet betaald"),
    HASPAYED("Heeft betaald");
    private final String displayValue;

    private Status(String displayValue) {
        this.displayValue = displayValue;
    }

    public String getDisplayValue() {
        return displayValue;
    }
}
