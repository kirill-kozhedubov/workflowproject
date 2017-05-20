package iq.ven.workflow.dao.impl;

import iq.ven.workflow.dao.UserDAO;
import iq.ven.workflow.models.User;
import iq.ven.workflow.models.UserFile;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;


@Repository("userDaoImpl")
public class UserDaoImpl implements UserDAO {

    public User getUserById(BigInteger userId) {
        return null;
    }

    public User getUserByFullName(String fullName) {
        return null;
    }

    public User getUserByEmail(String email) {
        return null;
    }

    public List<User> getAllUsers() {
        return null;
    }

    public User createUser(String email, String firstName, String lastName, String password) {
        return null;
    }

    public boolean deleteUserById(BigInteger userId) {
        return false;
    }

    public boolean deleteUserByEmail(String email) {
        return false;
    }

    public boolean giveUserAccessToFile(UserFile userFile, User user) {
        return false;
    }

    public boolean removeAccessToFileFromUser(UserFile userFile, User user) {
        return false;
    }

    public User authorizeUser(String email, String password) {
        return null;
    }
}
