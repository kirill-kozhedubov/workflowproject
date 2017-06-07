package iq.ven.workflow.models;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

public interface User {

    BigInteger getUserId();

    String getFirstName();

    String getLastName();

    String getFullName();

    String getEmail();

    String getPassword();

    Date getRegistrationDate();

    UserTypes getUsersRole();

    List<UserFile> getFilesUserHaveAccessTo();

}
