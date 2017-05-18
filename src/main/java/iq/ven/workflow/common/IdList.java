package iq.ven.workflow.common;

import java.math.BigInteger;

public interface IdList {
    /*  Parent types section  */
    BigInteger MOTHER = BigInteger.valueOf(1L);
    BigInteger FATHER = BigInteger.valueOf(2L);
    BigInteger GRANDMOTHER = BigInteger.valueOf(3L);
    BigInteger GRANDFATHER = BigInteger.valueOf(4L);
    BigInteger UNCLE = BigInteger.valueOf(5L);
    BigInteger AUNT = BigInteger.valueOf(6L);
    BigInteger SISTER = BigInteger.valueOf(17L);
    BigInteger BROTHER = BigInteger.valueOf(8L);
    BigInteger CUSTODIAN = BigInteger.valueOf(9L);

    /*  District id section  */
    BigInteger MALINOVSKY = BigInteger.valueOf(1L);
    BigInteger PRIMORSKY = BigInteger.valueOf(2L);
    BigInteger KIEVSKY = BigInteger.valueOf(3L);
    BigInteger SUVOROVSKY = BigInteger.valueOf(4L);


}
