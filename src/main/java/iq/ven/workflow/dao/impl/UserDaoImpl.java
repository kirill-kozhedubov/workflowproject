package iq.ven.workflow.dao.impl;

import iq.ven.workflow.dao.UserDAO;
import iq.ven.workflow.models.User;
import iq.ven.workflow.models.UserFile;
import iq.ven.workflow.models.UserRoles;
import iq.ven.workflow.models.impl.UserImpl;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
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
    private SimpleJdbcCall simpleCallTemplate;

    @Autowired
    private JdbcTemplate generalTemplate;

    public User getUserById(BigInteger userId) {
        return null;
    }

    public User getUserByFullName(String fullName) {
        try {
            return generalTemplate.queryForObject(SELECT_USER_BY_FULL_NAME, new Object[]{fullName}, new UserRowMapper());
        } catch (DataAccessException e) {
            LOGGER.error("User not fetched", e);
            return null;
        }
    }

    public User getUserByEmail(String email) {
        try {
            return generalTemplate.queryForObject(SELECT_USER_BY_EMAIL, new Object[]{email}, new UserRowMapper());
        } catch (DataAccessException e) {
            LOGGER.error("User not fetched", e);
            return null;
        }
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
        try {
            return generalTemplate.queryForObject(SELECT_USER_FOR_AUTHORIZATION, new Object[]{email, password}, new UserRowMapper());
        } catch (DataAccessException e) {
            LOGGER.error("User not fetched", e);
            return null;
        }
    }

    private static final String SELECT_USER_FOR_AUTHORIZATION = "select " +
            " user_id, email, first_name, last_name, password, registration_date, rights " +
            " from users " +
            " where email = ? " +
            " and password = ? ";


    private static final String SELECT_USER_BY_ID = "select " +
            " user_id, email, first_name, last_name, password, registration_date, rights " +
            " from users " +
            " where user_id = ?";
    private static final String SELECT_USER_BY_FULL_NAME = "SELECT " +
            " user_id, email, first_name, last_name, password, registration_date, rights " +
            " FROM users " +
            " WHERE (last_name || ' ' || first_name) = ?";

    private static final String SELECT_USER_BY_EMAIL = "select " +
            " user_id, email, first_name, last_name, password, registration_date, rights " +
            " from users " +
            " where email = ?";

    private static final String SELECT_ALL_USERS = "select " +
            " user_id, email, first_name, last_name, password, registration_date, rights " +
            " from users";


    private static final String INSERT_USER = "insert into " +
            " users (email, first_name, last_name, password, registration_date, rights) " +
            " values (?,?,?,?,?,?)";

    private static final String INSERT_USER_FILE = "";
    private static final String INSERT_USER_FILE_ACCESS = "";


    private class UserRowMapper implements RowMapper<User> {
        public User mapRow(ResultSet resultSet, int i) throws SQLException {
            User user = null;
            BigInteger userId = resultSet.getBigDecimal(UserColumnName.USER_ID.toString()).toBigInteger();
            String email = resultSet.getString(UserColumnName.EMAIL.toString());
            String firstName = resultSet.getString(UserColumnName.FIRST_NAME.toString());
            String lastName = resultSet.getString(UserColumnName.LAST_NAME.toString());
            String password = resultSet.getString(UserColumnName.PASSWORD.toString());
            Date registrationDate = resultSet.getDate(UserColumnName.REGISTRATION_DATE.toString());
            int rights = resultSet.getInt(UserColumnName.RIGHTS.toString());
            UserRoles role = UserRoles.getRoleByValue(rights);

            user = new UserImpl.UserBuilder(userId, firstName, lastName, email, password, registrationDate, role).buildUser();
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
