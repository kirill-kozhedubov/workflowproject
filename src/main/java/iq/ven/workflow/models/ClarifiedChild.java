package iq.ven.workflow.models;

import java.math.BigInteger;
import java.util.Date;

public interface ClarifiedChild {

    boolean getBirthCertificatePresenceInfo();

    BigInteger getClarifiedInfoId(); //this id

    BigInteger getChildId();  //child id

    String getFirstName();

    String getLastName();

    String getMiddleName();

    String getFullName();

    Date getBirthDate();

    String getAddress();

    String getBirthPlace();

    String getOccupation();

}
