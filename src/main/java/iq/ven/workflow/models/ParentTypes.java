package iq.ven.workflow.models;

import iq.ven.workflow.common.IdList;

import java.math.BigInteger;

public enum ParentTypes {
    MOTHER("Мать", IdList.MOTHER),
    FATHER("Отец", IdList.FATHER),
    GRANDMOTHER("Бабушка", IdList.GRANDMOTHER),
    GRANDFATHER("Дедушка", IdList.GRANDFATHER),
    UNCLE("Дядя", IdList.UNCLE),
    AUNT("Тётя", IdList.AUNT),
    SISTER("Сестра", IdList.SISTER),
    BROTHER("Брат", IdList.BROTHER),
    CUSTODIAN("Опекун", IdList.CUSTODIAN),
    UNKNOWN("Неизвестно",IdList.UNKNOWN);

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
