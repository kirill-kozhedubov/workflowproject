package iq.ven.workflow.models;

import java.math.BigInteger;

public enum Districts {
    MALINOVSKY("Малиновский", BigInteger.valueOf(1L)),
    PRIMORSKY("Приморский", BigInteger.valueOf(2L)),
    KIEVSKY("Киевский", BigInteger.valueOf(3L)),
    SUVOROVSKY("Суворовский", BigInteger.valueOf(4L));


    private final String russianName;
    private final BigInteger dbId;

    Districts(String russianName, BigInteger dbId) {
        this.russianName = russianName;
        this.dbId = dbId;
    }

    public BigInteger getDbId() {
        return dbId;
    }

    public String getRussianName() {
        return russianName;
    }
}
