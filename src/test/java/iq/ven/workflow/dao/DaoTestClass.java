package iq.ven.workflow.dao;

import iq.ven.workflow.common.configurations.AppContext;
import iq.ven.workflow.dao.impl.ChildrenDaoImpl;
import iq.ven.workflow.dao.impl.UserDaoImpl;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.lang.reflect.Field;

public class DaoTestClass {
    private static final Logger LOGGER = Logger.getLogger(DaoTestClass.class);

    //dao
    private UserDaoImpl userDao;
    private ChildrenDaoImpl childrenDao;

    //context
    private DataSource dataSource;
    private DataSourceTransactionManager transactionManager;
    private JdbcTemplate generalTemplate;
    private SimpleJdbcCall simpleCallTemplate;


    public static void main(String[] args) {
        DaoTestClass daoTestClass = new DaoTestClass();
        daoTestClass.raiseContext();
        daoTestClass.prepareDaoObjects();
        daoTestClass.testCode();

    }

    void raiseContext() {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppContext.class);
        dataSource = (DataSource) applicationContext.getBean("dataSource");
        transactionManager = (DataSourceTransactionManager) applicationContext.getBean("transactionManager");
        generalTemplate = (JdbcTemplate) applicationContext.getBean("generalTemplate");
        simpleCallTemplate = (SimpleJdbcCall) applicationContext.getBean("simpleCallTemplate");
    }

    void prepareDaoObjects() {
        userDao = new UserDaoImpl();
        childrenDao = new ChildrenDaoImpl();

        Class<UserDaoImpl> classU = UserDaoImpl.class;
        Class<ChildrenDaoImpl> classC = ChildrenDaoImpl.class;

        Field simpleCallU = null;
        Field jdbcTemplateU = null;

        Field simpleCallC = null;
        Field jdbcTemplateC = null;

        try {
            simpleCallC = classC.getDeclaredField("simpleCallTemplate");
            jdbcTemplateC = classC.getDeclaredField("generalTemplate");
            simpleCallU = classU.getDeclaredField("simpleCallTemplate");
            jdbcTemplateU = classU.getDeclaredField("generalTemplate");
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

        simpleCallC.setAccessible(true);
        jdbcTemplateC.setAccessible(true);
        simpleCallU.setAccessible(true);
        jdbcTemplateU.setAccessible(true);

        try {
            simpleCallC.set(childrenDao, simpleCallTemplate);
            jdbcTemplateC.set(childrenDao, generalTemplate);
            simpleCallU.set(userDao, simpleCallTemplate);
            jdbcTemplateU.set(userDao, generalTemplate);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    void testCode() {
        LOGGER.info(System.getenv("PGSQL_JDBC_URL"));
        LOGGER.info(System.getenv("PGSQL_LOGIN"));
        LOGGER.info(System.getenv("PGSQL_PASSWORD"));

        LOGGER.info(userDao.getUserByFullName("Кожедубов Кирилл"));

    }
}
