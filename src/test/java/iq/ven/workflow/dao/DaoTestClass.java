package iq.ven.workflow.dao;

import iq.ven.workflow.common.configurations.AppContext;
import iq.ven.workflow.dao.impl.ChildrenDaoImpl;
import iq.ven.workflow.dao.impl.FileDaoImpl;
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
    public UserDaoImpl userDao;
    public ChildrenDaoImpl childrenDao;
    public FileDaoImpl fileDao;

    //context
    public DataSource dataSource;
    public DataSourceTransactionManager transactionManager;
    public JdbcTemplate generalTemplate;
    public SimpleJdbcCall simpleCallTemplate;


    public static DaoTestClass getInstance() {
        DaoTestClass daoTestClass = new DaoTestClass();
        daoTestClass.raiseContext();
        daoTestClass.prepareDaoObjects();
        daoTestClass.testCode();
        return daoTestClass;
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
        fileDao = new FileDaoImpl();

        Class<UserDaoImpl> classU = UserDaoImpl.class;
        Class<ChildrenDaoImpl> classC = ChildrenDaoImpl.class;
        Class<FileDaoImpl> classF = FileDaoImpl.class;

        Field jdbcTemplateU = null;
        Field jdbcTemplateF = null;
        Field jdbcTemplateC = null;

        try {
            jdbcTemplateC = classC.getDeclaredField("generalTemplate");
            jdbcTemplateU = classU.getDeclaredField("generalTemplate");
            jdbcTemplateF = classF.getDeclaredField("generalTemplate");
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

        jdbcTemplateC.setAccessible(true);
        jdbcTemplateU.setAccessible(true);
        jdbcTemplateF.setAccessible(true);

        try {
            jdbcTemplateC.set(childrenDao, generalTemplate);
            jdbcTemplateU.set(userDao, generalTemplate);
            jdbcTemplateF.set(fileDao, generalTemplate);
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
