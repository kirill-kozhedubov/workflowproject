package iq.ven.workflow.dao.generators;

import iq.ven.workflow.dao.ChildrenDAO;
import iq.ven.workflow.dao.DaoTestClass;
import iq.ven.workflow.models.Child;
import iq.ven.workflow.models.ClarifiedChild;
import iq.ven.workflow.models.Parent;
import iq.ven.workflow.models.ParentTypes;
import iq.ven.workflow.models.impl.ChildImpl;
import iq.ven.workflow.models.impl.ClarifiedChildImpl;
import iq.ven.workflow.models.impl.ParentImpl;
import org.apache.log4j.Logger;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class ChildrenGenerator {
    private static final Logger LOGGER = Logger.getLogger(ChildrenGenerator.class);
    ChildrenDAO childrenDAO;

    Random random = new Random();

    public static void main(String[] args) {
        ChildrenGenerator childrenGenerator = new ChildrenGenerator();
        childrenGenerator.setupdao();
        LOGGER.info("DAO setup completed");
        Random random = new Random();

        LOGGER.info("Starging generation...");
        for (int i = 0; i < 100; i++) {
            Child genChild = childrenGenerator.generateChild();
            LOGGER.info("genChild: " + genChild);
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
        String firstName = "First" + random.nextInt(100000);
        String lastName = "Last" + random.nextInt(100000);
        String middleName = "Middle" + random.nextInt(100000);
        Date birthDate = new Date(Math.abs(System.currentTimeMillis() - random.nextInt()));
        String personalRecordCode = "code" + random.nextInt(500000) + random.nextBoolean();
        Date entranceDate = new Date(Math.abs(System.currentTimeMillis() - random.nextInt()));
        boolean isBirthCertificatePresent = random.nextBoolean();
        String clarifiedFirstName = "cFirst" + random.nextInt(100000);
        String clarifiedLastName = "cLast" + random.nextInt(100000);
        String clarifiedMiddleName = "cMiddle" + random.nextInt(100000);
        Date clarifiedBirthDate = new Date(Math.abs(System.currentTimeMillis() - random.nextInt()));
        String address = "address" + random.nextInt(100000);
        String birthPlace = "city" + random.nextInt(100000);
        String occupation = "occupation" + random.nextInt(100000);

        ClarifiedChild clarifiedChild = new ClarifiedChildImpl.ClarifiedChildBuilder(isBirthCertificatePresent, null, null,
                clarifiedFirstName, clarifiedLastName, clarifiedMiddleName, clarifiedBirthDate, address, birthPlace, occupation)
                .buildClarifiedChild();

        Child child = new ChildImpl.ChildBasicBuilder(null, firstName, lastName, middleName, birthDate, personalRecordCode, entranceDate)
                .setClarifiedChild(clarifiedChild)
                .buildChild();
        return child;
    }

    private Parent generateChildsParent(Child child) {
        BigInteger childId = child.getChildId();
        ParentTypes parentType = ParentTypes.values()[random.nextInt(ParentTypes.values().length)];
        String parentName = "Parent " + parentType.toString() + " " + random.nextInt(100000);
        String parentInfo = "Info" + random.nextInt(100000) + random.nextInt(100000) + random.nextInt(100000);
        Parent parent = new ParentImpl.ParentBuilder(null, childId, parentType, parentName, parentInfo).buildParent();
        return parent;
    }

    private Child saveGeneratedChild(Child child) {
        Child generateChild = childrenDAO.saveChildToDB(child);
        return generateChild;
    }

    private void saveGeneratedChildsParents(List<Parent> parents, Child child) {
        for (Parent parent : parents) {
            childrenDAO.addParentToChild(child, parent);
        }

    }

}
