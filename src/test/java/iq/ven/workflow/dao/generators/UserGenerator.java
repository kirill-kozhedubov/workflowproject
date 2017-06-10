package iq.ven.workflow.dao.generators;

import io.codearte.jfairy.Fairy;
import io.codearte.jfairy.producer.person.Person;
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
    Fairy fairy = Fairy.create();

    public static void main(String[] args) {
        UserGenerator userGenerator = new UserGenerator();
        userGenerator.setupdao();
        LOGGER.info("DAO setup completed");
        LOGGER.info("Starging generation...");
        for (int i = 0; i < 200; i++) {
            User u = userGenerator.generateUser();
            LOGGER.info("User generated " + u);
            userGenerator.saveUser(u);
        }

    }

    private void setupdao() {
        DaoTestClass daoTest = DaoTestClass.getInstance();
        userDAO = daoTest.userDao;
    }

    private User generateUser() {
        Person person = fairy.person();

        String firstName = person.getFirstName();
        String lastName = person.getLastName();
        String email = ((random.nextInt(2) >= 1 ? person.getFirstName() : person.getLastName())
                + random.nextInt(3000) + "@mail" + fairy.baseProducer().randomBetween('a', 'z') + "." + person.getCompany().getDomain()).toLowerCase();
        String password = person.getPassword();
        UserTypes userType = UserTypes.values()[random.nextInt(UserTypes.values().length)];
        User user = new UserImpl.UserBuilder(firstName, lastName, email, password, userType).buildUser();
        return user;
    }

    private void saveUser(User user) {
        userDAO.createUser(user);
    }
}
