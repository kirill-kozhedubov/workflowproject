package iq.ven.workflow.models;

import java.math.BigInteger;
import java.util.Date;

public interface User {

    BigInteger getUserId();

    String getFirstName();

    String getLastName();

    String getFullName();

    String getEmail();

    String getPassword();

    Date getRegistrationDate();

    UserRoles getUsersRole();

}
