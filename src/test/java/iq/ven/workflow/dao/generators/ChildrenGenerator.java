package iq.ven.workflow.dao.generators;

import iq.ven.workflow.dao.ChildrenDAO;
import iq.ven.workflow.dao.DaoTestClass;
import iq.ven.workflow.models.*;
import iq.ven.workflow.models.impl.ChildImpl;
import iq.ven.workflow.models.impl.ClarifiedChildImpl;
import iq.ven.workflow.models.impl.DetentionImpl;
import iq.ven.workflow.models.impl.ParentImpl;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class ChildrenGenerator {
    ChildrenDAO childrenDAO;

    Random random = new Random();

    public static void main(String[] args) {
        ChildrenGenerator childrenGenerator = new ChildrenGenerator();
        childrenGenerator.setupdao();
        Random random = new Random();

        for (int i = 0; i < 100; i++) {
            Child genChild = childrenGenerator.generateChild();
            Child genChildFromDB = childrenGenerator.saveGeneratedChild(genChild);


            List<Parent> parents = new ArrayList<>();
            List<Detention> detentions = new ArrayList<>();

            for (int j = 0; j < random.nextInt(3); j++) {
                parents.add(childrenGenerator.generateChildsParent(genChildFromDB));
            }
            for (int j = 0; j < random.nextInt(3); j++) {
                detentions.add(childrenGenerator.generateChildsDetention(genChildFromDB));
            }

            childrenGenerator.saveGeneratedChildsDetentions(detentions, genChildFromDB);
            childrenGenerator.saveGeneratedChildsParents(parents, genChildFromDB);

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
        Date birthDate = new Date(Math.abs(System.currentTimeMillis() - random.nextLong()));
        Districts district = Districts.getDistrictById(BigInteger.valueOf(random.nextInt(Districts.values().length)));
        String personalRecordCode = "code" + random.nextInt(500000) + random.nextBoolean();
        Date entranceDate = new Date(Math.abs(System.currentTimeMillis() - random.nextLong()));
        boolean isBirthCertificatePresent = random.nextBoolean();
        String clarifiedFirstName = "cFirst" + random.nextInt(100000);
        String clarifiedLastName = "cLast" + random.nextInt(100000);
        String clarifiedMiddleName = "cMiddle" + random.nextInt(100000);
        Date clarifiedBirthDate = new Date(Math.abs(System.currentTimeMillis() - random.nextLong()));
        String address = "address" + random.nextInt(100000);
        String birthPlace = "city" + random.nextInt(100000);
        String occupation = "occupation" + random.nextInt(100000);

        ClarifiedChild clarifiedChild = new ClarifiedChildImpl.ClarifiedChildBuilder(isBirthCertificatePresent, null, null,
                clarifiedFirstName, clarifiedLastName, clarifiedMiddleName, clarifiedBirthDate, address, birthPlace, occupation)
                .buildClarifiedChild();

        Child child = new ChildImpl.ChildBasicBuilder(null, firstName, lastName, middleName, birthDate, personalRecordCode, entranceDate)
                .setDistrict(district)
                .setClarifiedChild(clarifiedChild)
                .buildChild();
        return child;
    }

    private Parent generateChildsParent(Child child) {
        BigInteger childId = child.getChildId();
        ParentTypes parentType = ParentTypes.getParentTypeById(BigInteger.valueOf(random.nextInt(ParentTypes.values().length)));
        String parentName = "Parent " + parentType.toString() + " " + random.nextInt(100000);
        String parentInfo = "Info" + random.nextInt(100000) + random.nextInt(100000) + random.nextInt(100000);
        Parent parent = new ParentImpl.ParentBuilder(null, childId, parentType, parentName, parentInfo).buildParent();
        return parent;
    }

    private Detention generateChildsDetention(Child child) {
        BigInteger childId = child.getChildId();
        String detentionAdress = "Detention Address" + random.nextInt(1234567890);
        String detentionByWho = "Detention Guy" + random.nextInt(1234567890);
        Date detentionDate = new Date(Math.abs(System.currentTimeMillis() - random.nextLong()));
        Detention detention = new DetentionImpl.DetentionBuilder(null, childId, detentionAdress, detentionDate, detentionByWho).buildDetention();
        return detention;
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

    private void saveGeneratedChildsDetentions(List<Detention> detentions, Child child) {
        for (Detention detention : detentions) {
            childrenDAO.addDetentionToChild(child, detention);
        }
    }


}
