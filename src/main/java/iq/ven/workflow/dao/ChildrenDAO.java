package iq.ven.workflow.dao;

import iq.ven.workflow.models.*;

import java.io.File;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;

public interface ChildrenDAO {

    Child saveChildToDb(
            String firstName, String lastName, String middleName, Date birthDate, Districts district, String personalRecordCode, Date entranceDate, //basic info
            boolean isBirthCertificatePresent, String clarifiedFirstName, String clarifiedLastName, String clarifiedMiddleName, Date clarifiedBirthDate, //clarified info
            String address, String birthPlace, String occupation);  //clarified info continues

    List<Child> getAllChildrenList();

    Child getChildById(BigInteger childId);

    Child getChildByFullName(String fullName);

    List<Child> getChildrenByLastName(String lastName);

    List<Child> getChildrenByBirthDate(Date birthDate);

    List<Child> getChildrenByBirthYear(int birthYear);

    List<Child> getChildrenByDistrict(Districts district);

    Child getChildByPersonalRecordCode(String personalRecordCode);

    List<Child> getChildrenEnteredInRangeOfDates(Date after, Date before);

    ClarifiedChild getClarifiedChild(Child child);

    List<Detention> getChildDetentions(Child child);

    List<Parent> getChildParents(Child child);

    boolean addDetentionToChild(Child child, Detention detention);

    boolean addParentToChild(Child child, Parent parent);

    boolean addFileToChild(Child child, File file);

}
