package com.jasper.pigrakker.model;

public enum Status {
    PICKUP("Komt afhalen"),
    WANTAPPOINTMENT("Wil afspraak maken"),
    HASAPPOINTMENT("Heeft afspraak gemaakt");
    private final String displayValue;

    private Status(String displayValue) {
        this.displayValue = displayValue;
    }

    public String getDisplayValue() {
        return displayValue;
    }
}
