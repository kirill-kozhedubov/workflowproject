package iq.ven.workflow.models;

import java.math.BigInteger;
import java.util.Date;

public interface Detention {


    BigInteger getDetentionId();

    String getDetentionAddress();

    Date getDetentionDate();

    String getDetainedBy();

    String getGotDetainedFor();


}
