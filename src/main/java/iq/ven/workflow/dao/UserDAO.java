package iq.ven.workflow.dao;

import iq.ven.workflow.models.User;
import iq.ven.workflow.models.UserFile;

import java.math.BigInteger;
import java.util.List;

public interface UserDAO {

    User getUserById(BigInteger userId);
    User getUserByFullName(String fullName);
    User getUserByEmail(String email);
    List<User> getAllUsers();
    User createUser(String email, String firstName, String lastName, String password);
    boolean deleteUserById(BigInteger userId);
    boolean deleteUserByEmail(String email);
    boolean giveUserAccessToFile(UserFile userFile, User user);
    boolean removeAccessToFileFromUser(UserFile userFile, User user);
    User authorizeUser(String email, String password);
}
