package iq.ven.workflow.dao;

import iq.ven.workflow.models.*;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

public interface ChildrenDAO {


    Child saveChildToDB(Child child);


    List<Child> getAllChildrenList();


    Child getChildById(BigInteger childId);


    List<Child> getChildrenByFullName(String fullName);


    List<Child> getChildrenByLastName(String lastName);


    List<Child> getChildrenByBirthDate(Date birthDate);


    List<Child> getChildrenByBirthYear(int birthYear);


    List<Child> getChildrenByDistrict(Districts district);


    List<Child> getChildrenByPersonalRecordCode(String personalRecordCode);


    List<Child> getChildrenEnteredInRangeOfDates(Date after, Date before);

    List<Parent> getChildParents(BigInteger childId);

    boolean addParentToChild(BigInteger childId, Parent parent);

    boolean addFileToChild(BigInteger childId, MultipartFile file);

    boolean deleteChild(BigInteger childId);

    boolean updateChildsPhoto(BigInteger childId, MultipartFile photo);

}
