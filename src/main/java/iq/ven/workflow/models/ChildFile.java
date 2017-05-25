package iq.ven.workflow.models;

import java.math.BigInteger;

public interface ChildFile {

    BigInteger getFileId();

    byte[] getFile();

    String getFileName();

    BigInteger getChildId();

}
