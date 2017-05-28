package iq.ven.workflow.dao;


import iq.ven.workflow.common.configurations.ServletContext;
import iq.ven.workflow.models.User;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.math.BigInteger;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {ServletContext.class})
public class JUnitTestDao {
    private static final Logger LOGGER = Logger.getLogger(JUnitTestDao.class);
    @Autowired
    private UserDAO userDAO;

    //Below an example of test
    @Test
    public void someTest() {
        User user = userDAO.getUserById(BigInteger.valueOf(21));
LOGGER.info("SELECTED " + user);
        Assert.assertEquals(1, 1);
    }

}