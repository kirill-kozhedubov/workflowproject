package iq.ven.workflow.dao.generators;

import iq.ven.workflow.dao.DaoTestClass;
import iq.ven.workflow.dao.UserDAO;
import iq.ven.workflow.models.User;
import iq.ven.workflow.models.UserTypes;
import iq.ven.workflow.models.impl.UserImpl;
import org.apache.log4j.Logger;

import java.util.Random;

public class UserGenerator {
    private static final Logger LOGGER = Logger.getLogger(UserGenerator.class);
    UserDAO userDAO;

    Random random = new Random();


    public static void main(String[] args) {
        UserGenerator userGenerator = new UserGenerator();
        userGenerator.setupdao();
        LOGGER.info("DAO setup completed");
        Random random = new Random();

        LOGGER.info("Starging generation...");

        for (int i = 0; i < 200; i++) {
            User u = userGenerator.generateUser();
            userGenerator.saveUser(u);
        }

    }

    private void setupdao() {
        DaoTestClass daoTest = DaoTestClass.getInstance();
        userDAO = daoTest.userDao;
    }

    private User generateUser() {
        String firstName = "uFirstName" + random.nextInt();
        String lastName = "uLastName" + random.nextInt();
        String email = "uEmail@email." + random.nextInt();
        String password = "passwordinho" + random.nextInt();
        UserTypes userType = UserTypes.values()[random.nextInt(UserTypes.values().length)];
        User user = new UserImpl.UserBuilder(null, firstName, lastName, email, password, null, userType).buildUser();
        return user;
    }

    private void saveUser(User user) {
        userDAO.createUser(user.getEmail(), user.getFirstName(), user.getLastName(), user.getPassword());
    }
}
