package iq.ven.workflow.common;

import java.math.BigInteger;

public interface IdList {
    /*  Parent types section  */
    BigInteger MOTHER = BigInteger.valueOf(1L);
    BigInteger FATHER = BigInteger.valueOf(2L);
    BigInteger CUSTODIAN = BigInteger.valueOf(3L);
    BigInteger FIDUCIARY = BigInteger.valueOf(4L);
    BigInteger ADOPTIVE_PARENTS = BigInteger.valueOf(5L);
    BigInteger ADOPTER = BigInteger.valueOf(6L);
    BigInteger OTHERS = BigInteger.valueOf(7L);

    /*  District id section  */
    BigInteger MALINOVSKY = BigInteger.valueOf(1L);
    BigInteger PRIMORSKY = BigInteger.valueOf(2L);
    BigInteger KIEVSKY = BigInteger.valueOf(3L);
    BigInteger SUVOROVSKY = BigInteger.valueOf(4L);
    BigInteger ODESSA_REGION = BigInteger.valueOf(5L);
    BigInteger UKRAINE = BigInteger.valueOf(6L);
    BigInteger ANOTHER_COUNTRY = BigInteger.valueOf(7L);
    BigInteger MOLDOVA = BigInteger.valueOf(8L);


    /*  User roles id section  */
    BigInteger ADMIN = BigInteger.valueOf(1L);
    BigInteger REGULAR_USER = BigInteger.valueOf(2L);

}
