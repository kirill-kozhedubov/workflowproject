package iq.ven.workflow.dao.generators;

import io.codearte.jfairy.Fairy;
import io.codearte.jfairy.producer.DateProducer;
import io.codearte.jfairy.producer.person.Person;
import io.codearte.jfairy.producer.person.PersonProperties;
import io.codearte.jfairy.producer.text.TextProducer;
import iq.ven.workflow.dao.ChildrenDAO;
import iq.ven.workflow.dao.DaoTestClass;
import iq.ven.workflow.models.*;
import iq.ven.workflow.models.impl.ChildImpl;
import iq.ven.workflow.models.impl.ClarifiedChildImpl;
import iq.ven.workflow.models.impl.DetentionImpl;
import iq.ven.workflow.models.impl.ParentImpl;
import org.apache.log4j.Logger;
import org.joda.time.DateTime;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class ChildrenGenerator {
    private static final Logger LOGGER = Logger.getLogger(ChildrenGenerator.class);
    ChildrenDAO childrenDAO;

    Random random = new Random();
    Fairy fairy = Fairy.create();
    TextProducer textProducer = fairy.textProducer();
    DateProducer dateProducer = fairy.dateProducer();

    public static void main(String[] args) {
        ChildrenGenerator childrenGenerator = new ChildrenGenerator();
        childrenGenerator.setupdao();
        LOGGER.info("DAO setup completed");
        Random random = new Random();

        LOGGER.info("Starging generation...");
        for (int i = 0; i < 100; i++) {
            Child genChild = childrenGenerator.generateChild();
          //  LOGGER.info("genChild: " + genChild);
            Child genChildFromDB = childrenGenerator.saveGeneratedChild(genChild);
            LOGGER.info("Child I got from db: " + genChildFromDB);

            List<Parent> parents = new ArrayList<>();


            for (int j = 0; j < random.nextInt(3); j++) {
                parents.add(childrenGenerator.generateChildsParent(genChildFromDB));
            }


            childrenGenerator.saveGeneratedChildsParents(parents, genChildFromDB);
            LOGGER.info("Child : " + genChild.getFullName() + " added to db, moving to next one.");
        }
    }


    private void setupdao() {
        DaoTestClass daoTest = DaoTestClass.getInstance();
        childrenDAO = daoTest.childrenDao;
    }

    private Child generateChild() {
        Person person = fairy.person(PersonProperties.withMiddleName(textProducer.latinWord()));

        String personalRecordCode = random.nextInt() + "/" + random.nextInt();
        Date entranceDate = dateProducer.randomDateBetweenYearAndNow(2007).toDate();
        Date retireDate = dateProducer.randomDateBetweenTwoDates(new DateTime(entranceDate), DateTime.now()).toDate();
        ;
        byte[] photo = textProducer.latinSentence().getBytes();
        String clarifiedFirstName = person.getFirstName() + "c";
        String clarifiedLastName = person.getLastName() + "c";
        String clarifiedMiddleName = person.getMiddleName() + "c";
        Date clarifiedBirthDate = person.getDateOfBirth().toDate();
        Districts district = Districts.values()[random.nextInt(Districts.values().length)];
        String birthPlace = person.getAddress().getCity();
        String basicFirstName = person.getFirstName() + "b";
        String basicLastName = person.getLastName() + "b";
        String basicMiddleName = person.getMiddleName() + "b";
        Date basicBirthDate = person.getDateOfBirth().toDate();
        String address = person.getAddress().toString();
        String occupation = person.getCompany().getName();
        String comeFromCity = person.getAddress().getCity();
        Date comeFromDate = dateProducer.randomDateBetweenYears(2000, 2007).toDate();
        String dutyOfficer = fairy.person().getFullName();
        String judgedInfo = textProducer.paragraph(3);
        String notes = textProducer.paragraph(3);

        Person detentionPerson = fairy.person();
        String detainedBy = detentionPerson.getFullName();
        Date detainedWhen = dateProducer.randomDateInThePast(3).toDate();
        String detainedWhy = textProducer.latinSentence(3);
        String detainedWhere = detentionPerson.getAddress().getAddressLine1();

        Detention detention = new DetentionImpl(detainedWhere, detainedWhen, detainedBy, detainedWhy);

        ClarifiedChild clarifiedChildFull = new ClarifiedChildImpl.ClarifiedChildBuilder(null, clarifiedFirstName, clarifiedLastName, clarifiedMiddleName, clarifiedBirthDate)
                .buildAddress(address)
                .buildBirthPlace(birthPlace)
                .buildDetention(detention)
                .buildDistrict(district)
                .buildOccupation(occupation)
                .buildNotes(notes)
                .buildDutyOfficer(dutyOfficer)
                .buildFromCame(comeFromCity)
                .buildWhenCame(comeFromDate)
                .buildJudgedOrDetainedInfo(judgedInfo)
                .buildClarifiedChild();
        Child childFull = new ChildImpl.ChildBasicBuilder(basicFirstName, basicLastName, basicMiddleName, basicBirthDate, personalRecordCode, entranceDate)
                .buildPhoto(photo)
                .buildClarifiedChild(clarifiedChildFull)
                .buildRetiredDate(retireDate)
                .buildChildId(null)
                .buildChild();
        return childFull;
    }

    private Parent generateChildsParent(Child child) {
        Person personParent = fairy.person();
        BigInteger childId = child.getChildId();
        ParentTypes parentType = ParentTypes.values()[random.nextInt(ParentTypes.values().length)];
        String parentName = personParent.getFullName();
        String parentInfo = textProducer.latinSentence();
        Date parentBirthDate = dateProducer.randomDateBetweenYearAndNow(1900).toDate();

        Parent parent = new ParentImpl.ParentBuilder(childId, parentType, parentName)
                .buildParentBirthDate(parentBirthDate).buildParentInfo(parentInfo).buildParent();
        return parent;
    }

    private Child saveGeneratedChild(Child child) {
        Child generateChild = childrenDAO.saveChildToDB(child);
        return generateChild;
    }

    private void saveGeneratedChildsParents(List<Parent> parents, Child child) {
        for (Parent parent : parents) {
            childrenDAO.addParentToChild(child.getChildId(), parent);
        }

    }

}
