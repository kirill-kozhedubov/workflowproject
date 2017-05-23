package iq.ven.workflow.models;

import iq.ven.workflow.common.IdList;

import java.math.BigInteger;

public enum Districts {
    MALINOVSKY("Малиновский", IdList.MALINOVSKY),
    PRIMORSKY("Приморский", IdList.PRIMORSKY),
    KIEVSKY("Киевский", IdList.KIEVSKY),
    SUVOROVSKY("Суворовский", IdList.SUVOROVSKY);


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

    @Override
    public String toString() {
        return russianName;
    }
}
