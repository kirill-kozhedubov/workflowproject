package iq.ven.workflow.dao;

import iq.ven.workflow.models.UserFile;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.util.Arrays;

public class FileDaoTest {
    private static final Logger LOGGER = Logger.getLogger(FileDaoTest.class);

    FileDAO fileDAO;
    DaoTestClass daoTest;

    public static void main(String[] args) throws IOException {
        FileDaoTest t = new FileDaoTest();
        t.init();
        t.code();

    }

    private void init() {
        daoTest = DaoTestClass.getInstance();
        fileDAO = daoTest.fileDao;
    }

    private void code() throws IOException {
        File file = new File("C:\\Users\\User\\IdeaProjects\\workflowprojectV2\\src\\test\\resources\\testfile.pdf");
        byte[] bits = Files.readAllBytes(file.toPath());
        LOGGER.info("Array downloaded " + bits);
        LOGGER.info(Arrays.toString(bits).substring(1,199));
       // UserFile userFile = new UserFileImpl.UserFileBuilder(null, "lul", BigInteger.ONE).addFile(bits).addAccessList(null).buildUserFile();
       //fileDAO.insertUserFile(userFile);
     UserFile file1 = fileDAO.getUserFileById(BigInteger.ONE);
       LOGGER.info(Arrays.toString(file1.getFile()).substring(1,199));
    }
}
