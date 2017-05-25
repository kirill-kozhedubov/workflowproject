package iq.ven.workflow.dao.impl;

import iq.ven.workflow.dao.FileDAO;
import iq.ven.workflow.models.Child;
import iq.ven.workflow.models.ChildFile;
import iq.ven.workflow.models.User;
import iq.ven.workflow.models.UserFile;
import iq.ven.workflow.models.impl.UserFileImpl;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.SqlLobValue;
import org.springframework.jdbc.support.lob.DefaultLobHandler;
import org.springframework.jdbc.support.lob.LobHandler;
import org.springframework.stereotype.Repository;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

@Repository("fileDaoImpl")
public class FileDaoImpl implements FileDAO {
    private static final Logger LOGGER = Logger.getLogger(FileDaoImpl.class);

    @Autowired
    private JdbcTemplate generalTemplate;


    public boolean giveUserAccessToFile(UserFile userFile, User user) {
        try {
            if (user != null && userFile != null) {
               /* generalTemplate.update(INSERT_OBJREFERENCE_RELATION, PROJECT_SHARED_RELATION_ATTR_ID, user.getId(), project.getId());*/
            } else {
                return false;
            }
        } catch (DataAccessException e) {
            LOGGER.error("Access to project (id: " + userFile.getFileId() + ", name: " + userFile.getFileName() +
                    ") not granted to User (id: " + user.getUserId() + ", name: " + user.getFullName() + ")", e);
            return false;
        } catch (Exception e) {
            LOGGER.error("Access to project (id: " + userFile.getFileId() + ", name: " + userFile.getFileName() +
                    ") not granted to User (id: " + user.getUserId() + ", name: " + user.getFullName() + ")", e);
            return false;
        }
        return true;
    }

    @Override
    public void insertUserFile(UserFile userFile) {
        LobHandler lobHandler = new DefaultLobHandler();
        InputStream byteInputStream = new ByteArrayInputStream(userFile.getFile());
        generalTemplate.update(
                INSERT_USER_FILE,
                new Object[]{userFile.getFileAuthor(), userFile.getFileName(), new SqlLobValue(byteInputStream, (int) userFile.getFile().length, lobHandler),},
                new int[]{Types.INTEGER, Types.VARCHAR, Types.BLOB});
    }

    @Override
    public void deleteUserFile(UserFile userFile) {

    }

    @Override
    public List<UserFile> getAllUserFiles(User user) {
        return null;
    }

    @Override
    public List<UserFile> getFilesUserHaveAccessTo(User user) {
        return null;
    }

    @Override
    public List<UserFile> getFilesUserIsAuthorOf(User user) {
        return null;
    }

    @Override
    public UserFile getUserFileById(BigInteger fileId) {
        //SELECT_USER_FILE_BY_FILE_ID_WITH_BLOB = "select file_id, author_id, file_name, file_blob, file_added_date from user_files where file_id = ?";
        return generalTemplate.queryForObject(SELECT_USER_FILE_BY_FILE_ID_WITH_BLOB, new Object[]{fileId.longValue()}, new UserFileRowMapper());
    }

    @Override
    public void insertChildFile(ChildFile childFile) {

    }

    @Override
    public void deleteChildFile(ChildFile childFile) {

    }

    @Override
    public List<ChildFile> getAllChildFiles(Child child) {
        return null;
    }

    @Override
    public ChildFile getChildFileById(BigInteger fileId) {
        return null;
    }


    private static final String INSERT_USER_FILE = "INSERT INTO user_files(author_id, file_name, file_blob) VALUES(?,?,?)";
    private static final String DELETE_USER_FILE = "";

    private static final String INSERT_CHILD_FILE = "";
    private static final String DELETE_CHILD_FILE = "";

    private static final String SELECT_ALL_USER_FILES = "";
    private static final String SELECT_SHARED_TO_USER_FILES = "";
    private static final String SELECT_USER_AUTHOR_FILES = "";
    private static final String SELECT_USER_FILE_BY_FILE_ID_WITH_BLOB = "select file_id, author_id, file_name, file_blob, file_added_date from user_files where file_id = ?";

    private static final String SELECT_ALL_CHILD_FILES = "";
    private static final String SELECT_CHILD_FILE_BY_ID_WITH_BLOB = "";


    private class UserFileRowMapper implements RowMapper<UserFile> {

        @Override
        public UserFile mapRow(ResultSet resultSet, int i) throws SQLException {
            BigInteger fileId = resultSet.getBigDecimal(UserFileColumnName.FILE_ID.toString()).toBigInteger();
            BigInteger fileAuthorId = resultSet.getBigDecimal(UserFileColumnName.AUTHOR_ID.toString()).toBigInteger();
            String fileName = resultSet.getString(UserFileColumnName.FILE_NAME.toString());
            byte[] file = resultSet.getBytes(UserFileColumnName.FILE_BLOB.toString());

            UserFile userFile = new UserFileImpl.UserFileBuilder(fileId, fileName, fileAuthorId).addFile(file).buildUserFile();
            return userFile;
        }
    }

    private enum UserFileColumnName {
        FILE_ID("file_id"),
        AUTHOR_ID("author_id"),
        FILE_BLOB("file_blob"),
        FILE_NAME("file_name"),
        FILE_ADDED_DATE("file_added_date");

        final private String colName;

        UserFileColumnName(String colName) {
            this.colName = colName;
        }

        @Override
        public String toString() {
            return colName;
        }

    }

    private class ChildFileRowMapper implements RowMapper<ChildFile> {
        @Override
        public ChildFile mapRow(ResultSet resultSet, int i) throws SQLException {
            return null;
        }
    }

    private enum ChildFileColumnName {
        FILE("childfile");

        final private String colName;

        ChildFileColumnName(String colName) {
            this.colName = colName;
        }

        @Override
        public String toString() {
            return colName;
        }

    }


}
