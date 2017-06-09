package iq.ven.workflow.models;

import java.math.BigInteger;
import java.util.Date;

public interface Parent {

    BigInteger getParentId();

    BigInteger getChildId();

    ParentTypes getParentType();

    String getParentName();

    String getParentInfo();

    Date getParentBirthDate();

}
