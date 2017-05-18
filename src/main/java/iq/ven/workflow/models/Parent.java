package iq.ven.workflow.models;

import java.math.BigInteger;

public interface Parent {

    BigInteger getParentId();

    BigInteger getChildId();

    ParentTypes getParentType();

    String getParentName();

    String getParentInfo();

}
