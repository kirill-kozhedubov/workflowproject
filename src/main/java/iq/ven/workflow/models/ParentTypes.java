package iq.ven.workflow.models;

import iq.ven.workflow.common.IdList;

import java.math.BigInteger;

public enum ParentTypes {
    MOTHER("Мати", IdList.MOTHER),
    FATHER("Батько", IdList.FATHER),
    CUSTODIAN("Опікун", IdList.CUSTODIAN),
    FIDUCIARY("Піклувальник", IdList.FIDUCIARY),
    ADOPTIVE_PARENTS("Прийомні батьки", IdList.ADOPTIVE_PARENTS),
    ADOPTER("Усиновлювач", IdList.ADOPTER),
    OTHERS("Ініш", IdList.OTHERS);


    private final String russianName;
    private final BigInteger dbId;

    ParentTypes(String russianName, BigInteger dbId) {
        this.russianName = russianName;
        this.dbId = dbId;
    }

    public static ParentTypes getParentTypeById(BigInteger dbId) {
        for (ParentTypes e : ParentTypes.values()) {
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
