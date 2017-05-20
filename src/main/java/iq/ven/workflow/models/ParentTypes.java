package iq.ven.workflow.models;

import java.math.BigInteger;

public enum ParentTypes {
    MOTHER("Мать", BigInteger.valueOf(1L)),
    FATHER("Отец", BigInteger.valueOf(2L)),
    GRANDMOTHER("Бабушка", BigInteger.valueOf(3L)),
    GRANDFATHER("Дедушка", BigInteger.valueOf(4L)),
    UNCLE("Дядя", BigInteger.valueOf(5L)),
    AUNT("Тётя", BigInteger.valueOf(6L)),
    SISTER("Сестра", BigInteger.valueOf(17L)),
    BROTHER("Брат", BigInteger.valueOf(8L)),
    CUSTODIAN("Опекун", BigInteger.valueOf(9L));

    private final String russianName;
    private final BigInteger dbId;

    ParentTypes(String russianName, BigInteger dbId) {
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
