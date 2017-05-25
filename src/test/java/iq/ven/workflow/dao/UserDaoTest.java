package iq.ven.workflow.dao;

import org.apache.log4j.Logger;

public class UserDaoTest {
    private static final Logger LOGGER = Logger.getLogger(UserDaoTest.class);
    UserDAO userDao;
    DaoTestClass daoTest;

    public static void main(String[] args) {
        UserDaoTest t = new UserDaoTest();
        t.init();

    }

    private void init() {
        daoTest = DaoTestClass.getInstance();
        userDao = daoTest.userDao;
    }


}
