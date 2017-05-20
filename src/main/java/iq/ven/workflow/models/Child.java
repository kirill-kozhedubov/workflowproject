package iq.ven.workflow.models;

import java.io.File;
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

    Districts getDistrict();

    List<Parent> getParents();

    List<Detention> getDetentionList();

    String getPersonalRecordCode();

    Date getEntranceDate();

    List<File> getChildFiles();


}
