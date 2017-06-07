package iq.ven.workflow.models;

import java.math.BigInteger;
import java.util.Date;

public interface ClarifiedChild {

    BigInteger getClarifiedInfoId();

    BigInteger getChildId();

    String getFirstName();

    String getLastName();

    String getMiddleName();

    String getFullName();

    Date getBirthDate();

    String getAddress();

    String getBirthPlace();

    String getOccupation();

    String getWhereAndFromCame();

    String getWhenWhereWhyAndByWhoBrought();

    String getNotes();

    String getDutyOfficer();

    boolean wasPreviouslyJudgedOrDetained();

}
