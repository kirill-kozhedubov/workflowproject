package iq.ven.workflow.dao;

import iq.ven.workflow.models.Child;
import iq.ven.workflow.models.ChildFile;
import iq.ven.workflow.models.User;
import iq.ven.workflow.models.UserFile;

import java.math.BigInteger;
import java.util.List;

public interface FileDAO {
    void insertUserFile(UserFile userFile);

    void deleteUserFile(UserFile userFile);

    List<UserFile> getAllUserFiles(User user);

    List<UserFile> getFilesUserHaveAccessTo(User user);

    List<UserFile> getFilesUserIsAuthorOf(User user);

    UserFile getUserFileById(BigInteger fileId);


    void insertChildFile(ChildFile childFile);

    void deleteChildFile(ChildFile childFile);

    List<ChildFile> getAllChildFiles(Child child);

    ChildFile getChildFileById(BigInteger fileId);


}
