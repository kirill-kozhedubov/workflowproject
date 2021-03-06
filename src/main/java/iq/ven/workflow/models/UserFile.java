package iq.ven.workflow.models;

import java.math.BigInteger;
import java.util.List;

public interface UserFile {

    BigInteger getFileId();

    byte[] getFile();

    String getFileName();

    BigInteger getFileAuthor();

    List<BigInteger> getListOfUsersFileAccessibleTo();

}
