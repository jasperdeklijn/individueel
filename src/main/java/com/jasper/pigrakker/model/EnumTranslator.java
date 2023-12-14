package com.jasper.pigrakker.model;

import com.jasper.pigrakker.model.Status;

public class EnumTranslator {

    public static String translateStatus(Status status) {
        switch (status) {
            case NEEDSPAYREQUEST:
                return "Moet betaalverzoek hebben";
            case NEEDSTOPAY:
                return "Moet betalen";
            case HASPAYED:
                return "Heeft betaald";
            default:
                return status.toString(); // Default to the original enum name
        }
    }
}
