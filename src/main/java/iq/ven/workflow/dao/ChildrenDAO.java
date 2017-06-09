package iq.ven.workflow.dao;

import iq.ven.workflow.models.*;

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

    List<Parent> getChildParents(Child child);

    boolean addDetentionToChild(Child child, Detention detention);

    boolean addParentToChild(Child child, Parent parent);

    boolean addPhotoToChild(Child child, ChildFile file);

    boolean addFileToChild(Child child, ChildFile file);

    boolean deleteChild(Child child);

}
