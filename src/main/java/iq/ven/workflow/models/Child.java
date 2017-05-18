package iq.ven.workflow.models;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

public interface Child {

    BigInteger getChildId();

    BigInteger getClarifiedInfoId();

    String getFirstName();

    String getLastName();

    String getMiddleName();

    String getFullName();

    Date getBirthDate();

    Districts getDistrict();

    List<Parent> getParents();

    String getPersonalRecordCode();

    Date getEntranceDate();

}
