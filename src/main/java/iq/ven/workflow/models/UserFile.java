package iq.ven.workflow.models;

import java.io.File;
import java.math.BigInteger;
import java.util.List;

public interface UserFile {

    BigInteger getFileId();

    File getFile();

    String getFileName();

    BigInteger getFileAuthor();

    List<BigInteger> getListOfUsersFileAccessibleTo();

}
