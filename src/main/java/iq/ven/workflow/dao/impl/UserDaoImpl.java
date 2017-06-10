package iq.ven.workflow.dao.impl;

import iq.ven.workflow.dao.UserDAO;
import iq.ven.workflow.models.User;
import iq.ven.workflow.models.UserFile;
import iq.ven.workflow.models.UserTypes;
import iq.ven.workflow.models.impl.UserImpl;
import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;


@Repository("userDaoImpl")
public class UserDaoImpl implements UserDAO {

    private static final Logger LOGGER = Logger.getLogger(UserDaoImpl.class);

    @Autowired
    private JdbcTemplate generalTemplate;

    public User getUserById(BigInteger userId) {
        try {
            return generalTemplate.queryForObject(SELECT_USER_BY_ID, new Object[]{userId.longValue()}, new UserRowMapper());
        } catch (DataAccessException e) {
            LOGGER.error("User not fetched by id " + userId, e);
            return null;
        } catch (Exception e) {
            LOGGER.error("User not fetched by id " + userId, e);
            return null;
        }
    }

    @Override
    public User getUserByFullName(String fullName) {
        try {
            return generalTemplate.queryForObject(SELECT_USER_BY_FULL_NAME, new Object[]{fullName}, new UserRowMapper());
        } catch (DataAccessException e) {
            LOGGER.error("User not fetched by full name " + fullName, e);
            return null;
        } catch (Exception e) {
            LOGGER.error("User not fetched by full name " + fullName, e);
            return null;
        }
    }

    @Override
    public User getUserByEmail(String email) {
        try {
            return generalTemplate.queryForObject(SELECT_USER_BY_EMAIL, new Object[]{email}, new UserRowMapper());
        } catch (DataAccessException e) {
            LOGGER.error("User not fetched by email " + email, e);
            return null;
        } catch (Exception e) {
            LOGGER.error("User not fetched by email " + email, e);
            return null;
        }
    }

    @Override
    public List<User> getAllUsers() {
        try {
            return generalTemplate.query(SELECT_ALL_USERS, new UserRowMapper());
        } catch (DataAccessException e) {
            LOGGER.error("All Users not fetched", e);
            return null;
        } catch (Exception e) {
            LOGGER.error("All Users not fetched", e);
            return null;
        }
    }

    @Override
    public List<User> getAllUsersThatHaveAccessToFile(UserFile file) {
        //!TODO this method
        return null;
    }

    @Override
    public User createUser(User user) {
        try {
            generalTemplate.update(INSERT_USER, user.getEmail(), user.getFirstName(), user.getLastName(), user.getPassword(), DateTime.now().toDate(), user.getUsersRole().getRoleId().longValue());

            return getUserByEmail(user.getEmail());
        } catch (DataAccessException e) {
            LOGGER.error("Error in creating user with params Email:" + user.getEmail() +
                    " FirstName:" + user.getFirstName() + " LastName:" + user.getLastName() + " Password:" + user.getPassword(), e);
            return null;
        } catch (Exception e) {
            LOGGER.error("Error in creating user with params Email:" + user.getEmail() +
                    " FirstName:" + user.getFirstName() + " LastName:" + user.getLastName() + " Password:" + user.getPassword(), e);
            return null;
        }
    }

    @Override
    public boolean deleteUser(User user) {
        return deleteUserById(user.getUserId());
    }

    @Override
    public boolean deleteUserById(BigInteger userId) {
        return false;
    }

    @Override
    public boolean deleteUserByEmail(String email) {
        return false;
    }

    @Override
    public boolean giveUserAccessToFile(UserFile userFile, User user) {
        return false;
    }

    @Override
    public boolean removeAccessToFileFromUser(UserFile userFile, User user) {
        return false;
    }


    private static final String SELECT_USER_BY_ID = "SELECT " +
            " user_id, email, first_name, last_name, password, registration_date, rights " +
            " FROM users " +
            " WHERE user_id = ?";
    private static final String SELECT_USER_BY_FULL_NAME = "SELECT " +
            " user_id, email, first_name, last_name, password, registration_date, rights " +
            " FROM users " +
            " WHERE (last_name || ' ' || first_name) = ?";

    private static final String SELECT_USER_BY_EMAIL = "SELECT " +
            " user_id, email, first_name, last_name, password, registration_date, rights " +
            " FROM users " +
            " WHERE email = ?";

    private static final String SELECT_ALL_USERS = "SELECT " +
            " user_id, email, first_name, last_name, password, registration_date, rights " +
            " FROM users";


    private static final String INSERT_USER = "INSERT INTO " +
            " users (email, first_name, last_name, password, registration_date, rights) " +
            " VALUES (?,?,?,?,?,?)";


    private class UserRowMapper implements RowMapper<User> {
        public User mapRow(ResultSet resultSet, int i) throws SQLException {

            BigInteger userId = resultSet.getBigDecimal(UserColumnName.USER_ID.toString()).toBigInteger();
            String email = resultSet.getString(UserColumnName.EMAIL.toString());
            String firstName = resultSet.getString(UserColumnName.FIRST_NAME.toString());
            String lastName = resultSet.getString(UserColumnName.LAST_NAME.toString());
            String password = resultSet.getString(UserColumnName.PASSWORD.toString());
            Date registrationDate = resultSet.getDate(UserColumnName.REGISTRATION_DATE.toString());

            BigInteger rights = resultSet.getBigDecimal(UserColumnName.RIGHTS.toString()).toBigInteger();
            UserTypes role = UserTypes.getRoleById(rights);

            User user = new UserImpl.UserBuilder(firstName, lastName, email, password, role)
                    .buildRegistrationDate(registrationDate)
                    .buildUserId(userId)
                    .buildUser();
            return user;
        }
    }


    private enum UserColumnName {
        USER_ID("user_id"),
        EMAIL("email"),
        FIRST_NAME("first_name"),
        LAST_NAME("last_name"),
        PASSWORD("password"),
        REGISTRATION_DATE("registration_date"),
        RIGHTS("rights");

        final private String colName;

        UserColumnName(String colName) {
            this.colName = colName;
        }

        @Override
        public String toString() {
            return colName;
        }

    }
}
