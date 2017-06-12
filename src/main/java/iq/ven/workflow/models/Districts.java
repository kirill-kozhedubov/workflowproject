package iq.ven.workflow.models;

import iq.ven.workflow.common.IdList;

import java.math.BigInteger;

public enum Districts {
    MALINOVSKY("Малиновський", IdList.MALINOVSKY),
    PRIMORSKY("Приморський", IdList.PRIMORSKY),
    KIEVSKY("Київський", IdList.KIEVSKY),
    SUVOROVSKY("Суворовський", IdList.SUVOROVSKY),
    ODESSA_REGION("Одеська область", IdList.ODESSA_REGION),
    UKRAINE("Україна", IdList.UKRAINE),
    ANOTHER_COUNTRY("Інша країна", IdList.ANOTHER_COUNTRY),
    MOLDOVA("Молдова", IdList.MOLDOVA);


    private final String russianName;
    private final BigInteger dbId;

    Districts(String russianName, BigInteger dbId) {
        this.russianName = russianName;
        this.dbId = dbId;
    }

    public static Districts getDistrictByName(String russianName) {
        for (Districts e : Districts.values()) {
            if (e.russianName.equals(russianName)) {
                return e;
            }
        }
        return null;// not found
    }

    public static Districts getDistrictById(BigInteger dbId) {
        for (Districts e : Districts.values()) {
            if (e.dbId.equals(dbId)) {
                return e;
            }
        }
        return null;// not found
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

