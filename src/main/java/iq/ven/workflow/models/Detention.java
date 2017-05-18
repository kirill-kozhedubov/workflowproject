package iq.ven.workflow.models;

import java.math.BigInteger;
import java.util.Date;

public interface Detention {

    BigInteger getDetentionId();

    BigInteger getChildId();

    String getDetentionAddress();

    Date getDetentionDate();

    String getDetentionDoneByWho();

}
