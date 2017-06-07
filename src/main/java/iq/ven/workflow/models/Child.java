package iq.ven.workflow.models;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

public interface Child {

    BigInteger getChildId();

    ClarifiedChild getClarifiedInfo();

    String getFirstName();

    String getLastName();

    String getMiddleName();

    String getFullName();

    Date getBirthDate();

    List<Parent> getParents();

    String getPersonalRecordCode();

    Date getEntranceDate();

    Date getRetiredDate();

    List<ChildFile> getChildFiles();

    byte[] getPhoto();

}
