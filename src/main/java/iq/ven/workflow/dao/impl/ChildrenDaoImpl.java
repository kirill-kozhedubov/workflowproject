package iq.ven.workflow.dao.impl;

import iq.ven.workflow.dao.ChildrenDAO;
import iq.ven.workflow.models.*;
import iq.ven.workflow.models.impl.ChildImpl;
import iq.ven.workflow.models.impl.ClarifiedChildImpl;
import iq.ven.workflow.models.impl.ParentImpl;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

@Repository("childrenDaoImpl")
public class ChildrenDaoImpl implements ChildrenDAO {
    private static final Logger LOGGER = Logger.getLogger(ChildrenDaoImpl.class);


    @Autowired
    private SimpleJdbcCall simpleCallTemplate;

    @Autowired
    private JdbcTemplate generalTemplate;

    @Override
    public Child saveChildToDB(Child child) {
        try {
            String firstName = child.getFirstName();
            String lastName = child.getLastName();
            String middleName = child.getMiddleName();
            Date birthDate = child.getBirthDate();
            BigInteger district = child.getDistrict().getDbId();
            String personalRecordCode = child.getPersonalRecordCode();
            Date entranceDate = child.getEntranceDate();

            boolean isBirthCertificatePresent = child.getClarifiedInfo().getBirthCertificatePresenceInfo();
            String clarifiedFirstName = child.getClarifiedInfo().getFirstName();
            String clarifiedLastName = child.getClarifiedInfo().getLastName();
            String clarifiedMiddleName = child.getClarifiedInfo().getMiddleName();
            Date clarifiedBirthDate = child.getClarifiedInfo().getBirthDate();
            String address = child.getClarifiedInfo().getAddress();
            String birthPlace = child.getClarifiedInfo().getBirthPlace();
            String occupation = child.getClarifiedInfo().getOccupation();

            generalTemplate.update(INSERT_BASIC_CHILD, firstName, lastName, middleName, birthDate, district.longValue(), personalRecordCode, entranceDate);
            BigInteger childIdFromDb = generalTemplate.queryForObject(SELECT_BASIC_CHILD_ID_BY_PERSONAL_RECORD_CODE, BigInteger.class, personalRecordCode);
            generalTemplate.update(INSERT_CLARIFIED_CHILD, childIdFromDb.longValue(), isBirthCertificatePresent, clarifiedFirstName, clarifiedLastName, clarifiedMiddleName, clarifiedBirthDate,
                    address, birthPlace, occupation);

            return getChildById(childIdFromDb);
        } catch (DataAccessException e) {
            LOGGER.error("Error in inserting child to db with params  " + child, e);
            return null;
        } catch (Exception e) {
            LOGGER.error("Error in inserting child to db with params " + child, e);
            return null;
        }
    }

    private final static String INSERT_BASIC_CHILD = "INSERT INTO children_basic_info " +
            " (first_name, last_name, middle_name, birth_date, district_id, personal_record_code, entrance_date)" +
            " VALUES (?,?,?,?,?,?,?)";
    private final static String SELECT_BASIC_CHILD_ID_BY_PERSONAL_RECORD_CODE = "SELECT child_id FROM children_basic_info WHERE personal_record_code = ?";
    private final static String INSERT_CLARIFIED_CHILD = "INSERT INTO children_clarified_info " +
            "(child_id, birth_certificate_presence, first_name, last_name, middle_name, birth_date, address, birth_place, occupation) " +
            " VALUES (?,?,?,?,?,?,?,?,?)";


    @Override
    public List<Child> getAllChildrenList() {
        try {
            return generalTemplate.query(SELECT_ALL_CHILDREN, new ChildrenRowMapper());
        } catch (DataAccessException e) {
            LOGGER.error("Error in fetching all children list", e);
            return null;
        } catch (Exception e) {
            LOGGER.error("Error in fetching all children list", e);
            return null;
        }
    }

    @Override
    public Child getChildById(BigInteger childId) {
        try {
            return generalTemplate.queryForObject(SELECT_CHILD_BY_ID, new Object[]{childId.longValue()}, new ChildrenRowMapper());
        } catch (DataAccessException e) {
            LOGGER.error("Error in fetching child by ID " + childId, e);
            return null;
        } catch (Exception e) {
            LOGGER.error("Error in fetching child by ID " + childId, e);
            return null;
        }
    }

    @Override
    public Child getChildByFullName(String fullName) {
        try {
            return generalTemplate.queryForObject(SELECT_CHILD_BY_FULL_NAME, new Object[]{fullName}, new ChildrenRowMapper());
        } catch (DataAccessException e) {
            LOGGER.error("Error in fetching child by full name " + fullName, e);
            return null;
        } catch (Exception e) {
            LOGGER.error("Error in fetching child by full name " + fullName, e);
            return null;
        }
    }

    @Override
    public List<Child> getChildrenByLastName(String lastName) {
        try {
            return generalTemplate.query(SELECT_CHILDREN_BY_LAST_NAME, new Object[]{lastName}, new ChildrenRowMapper());
        } catch (DataAccessException e) {
            LOGGER.error("Error in fetching children by last name " + lastName, e);
            return null;
        } catch (Exception e) {
            LOGGER.error("Error in fetching children by last name " + lastName, e);
            return null;
        }
    }

    @Override
    public List<Child> getChildrenByBirthDate(Date birthDate) {
        try {
            return generalTemplate.query(SELECT_CHILDREN_BY_BIRTH_DATE, new Object[]{birthDate}, new ChildrenRowMapper());
        } catch (DataAccessException e) {
            LOGGER.error("Error in fetching children by birth date " + birthDate, e);
            return null;
        } catch (Exception e) {
            LOGGER.error("Error in fetching children by birth date " + birthDate, e);
            return null;
        }
    }

    @Override
    public List<Child> getChildrenByBirthYear(int birthYear) {
        try {
            return generalTemplate.query(SELECT_CHILDREN_BY_BIRTH_YEAR, new Object[]{birthYear}, new ChildrenRowMapper());
        } catch (DataAccessException e) {
            LOGGER.error("Error in fetching children by birth year " + birthYear, e);
            return null;
        } catch (Exception e) {
            LOGGER.error("Error in fetching children by birth year " + birthYear, e);
            return null;
        }
    }

    @Override

    public List<Child> getChildrenByDistrict(Districts district) {
        try {
            return generalTemplate.query(SELECT_CHILDREN_BY_DISTRICT, new Object[]{district.getDbId()}, new ChildrenRowMapper());
        } catch (DataAccessException e) {
            LOGGER.error("Error in fetching children by district " +
                    district.getDbId() + " " + district.name(), e);
            return null;
        } catch (Exception e) {
            LOGGER.error("Error in fetching children by district " +
                    district.getDbId() + " " + district.name(), e);
            return null;
        }
    }

    @Override
    public Child getChildByPersonalRecordCode(String personalRecordCode) {
        try {
            return generalTemplate.queryForObject(SELECT_CHILD_BY_PERSONAL_RECORD_CODE, new Object[]{personalRecordCode}, new ChildrenRowMapper());
        } catch (DataAccessException e) {
            LOGGER.error("Error in fetching child by personal code " + personalRecordCode, e);
            return null;
        } catch (Exception e) {
            LOGGER.error("Error in fetching child by personal code " + personalRecordCode, e);
            return null;
        }
    }

    @Override
    public List<Child> getChildrenEnteredInRangeOfDates(Date after, Date before) {
        try {
            return generalTemplate.query(SELECT_CHILDREN_ENTERED_IN_RANGE_OF_DATES,
                    new Object[]{after, before}, new ChildrenRowMapper());
        } catch (DataAccessException e) {
            LOGGER.error("Error in fetching children entered between " + after + " and " + before, e);
            return null;
        } catch (Exception e) {
            LOGGER.error("Error in fetching children entered between " + after + " and " + before, e);
            return null;
        }
    }

    @Override
    public ClarifiedChild getClarifiedChild(Child child) {
        return null; //maybe this method is not needed at all
    }

    @Override
    public List<Detention> getChildDetentions(Child child) {
        try {
            return generalTemplate.query(SELECT_CHILD_DETENTIONS, new Object[]{child.getChildId().longValue()}, new DetentionRowMapper());
        } catch (DataAccessException e) {
            LOGGER.error("Error in fetching child's detentions. " + child, e);
            return null;
        } catch (Exception e) {
            LOGGER.error("Error in fetching child's detentions. " + child, e);
            return null;
        }
    }

    @Override
    public List<Parent> getChildParents(Child child) {
        try {
            return generalTemplate.query(SELECT_CHILD_PARENTS, new Object[]{child.getChildId().longValue()}, new ParentRowMapper());
        } catch (DataAccessException e) {
            LOGGER.error("Error in fetching child's parents. " + child, e);
            return null;
        } catch (Exception e) {
            LOGGER.error("Error in fetching child's parents. " + child, e);
            return null;
        }
    }

    @Override
    public boolean addDetentionToChild(Child child, Detention detention) {
        try {
            if (child != null && detention != null) {
                generalTemplate.update(INSERT_CHILD_DETENTION,
                        child.getChildId().longValue(), detention.getDetentionDoneByWho(), detention.getDetentionDate(), detention.getDetentionAddress());
                return true;
            } else {
                LOGGER.error("Error in adding detention to child because of nulls. CHILD:" + child + " DETENTION:" + detention);
                return false;
            }
        } catch (DataAccessException e) {
            LOGGER.error("Error in adding detention to child. CHILD: " + child + " DETENTION:" + detention, e);
            return false;
        } catch (Exception e) {
            LOGGER.error("Error in adding detention to child. CHILD: " + child + " DETENTION:" + detention, e);
            return false;
        }
    }

    @Override
    public boolean addParentToChild(Child child, Parent parent) {
        try {
            if (child != null && parent != null) {
                generalTemplate.update(INSERT_CHILD_PARENT,
                        child.getChildId().longValue(), parent.getParentType().getDbId().longValue(), parent.getParentName(), parent.getParentInfo());
                return true;
            } else {
                LOGGER.error("Error in adding parent to child because of nulls. CHILD:" + child + " PARENT:" + parent);
                return false;
            }
        } catch (DataAccessException e) {
            LOGGER.error("Error in adding parent to child. CHILD: " + child + " PARENT:" + parent, e);
            return false;
        } catch (Exception e) {
            LOGGER.error("Error in adding parent to child. CHILD: " + child + " PARENT:" + parent, e);
            return false;
        }
    }

    @Override
    public boolean addFileToChild(Child child, ChildFile file) {
        return false;
    }

    @Override
    public boolean deleteChild(Child child) {
        return false;
    }

    private static final String SELECT_ALL_CHILDREN = "SELECT " +
            " basic.child_id child_id, " +
            " basic.first_name bfirstn, " +
            " basic.last_name blastn, " +
            " basic.middle_name bmiddlen, " +
            " basic.birth_date bbirth, " +
            " basic.district_id district, " +
            " basic.personal_record_code code, " +
            " basic.entrance_date entrance_date, " +
            " clarified.birth_certificate_presence certificate, " +
            " clarified.first_name cfirstn, " +
            " clarified.last_name clastn, " +
            " clarified.middle_name cmiddlen, " +
            " clarified.birth_date cbirth, " +
            " clarified.address address, " +
            " clarified.birth_place birth_place, " +
            " clarified.occupation occupation " +
            " FROM children_basic_info basic " +
            " JOIN children_clarified_info clarified ON basic.child_id = clarified.child_id";

    private static final String SELECT_CHILD_BY_ID = "SELECT " +
            " basic.child_id child_id, " +
            " basic.first_name bfirstn, " +
            " basic.last_name blastn, " +
            " basic.middle_name bmiddlen, " +
            " basic.birth_date bbirth, " +
            " basic.district_id district, " +
            " basic.personal_record_code code, " +
            " basic.entrance_date entrance_date, " +
            " clarified.birth_certificate_presence certificate, " +
            " clarified.first_name cfirstn, " +
            " clarified.last_name clastn, " +
            " clarified.middle_name cmiddlen, " +
            " clarified.birth_date cbirth, " +
            " clarified.address address, " +
            " clarified.birth_place birth_place, " +
            " clarified.occupation occupation " +
            " FROM children_basic_info basic " +
            " JOIN children_clarified_info clarified ON basic.child_id = clarified.child_id " +
            " WHERE basic.child_id = ?";

    private static final String SELECT_CHILD_BY_FULL_NAME = "SELECT " +
            " basic.child_id child_id, " +
            " basic.first_name bfirstn, " +
            " basic.last_name blastn, " +
            " basic.middle_name bmiddlen, " +
            " basic.birth_date bbirth, " +
            " basic.district_id district, " +
            " basic.personal_record_code code, " +
            " basic.entrance_date entrance_date, " +
            " clarified.birth_certificate_presence certificate, " +
            " clarified.first_name cfirstn, " +
            " clarified.last_name clastn, " +
            " clarified.middle_name cmiddlen, " +
            " clarified.birth_date cbirth, " +
            " clarified.address address, " +
            " clarified.birth_place birth_place, " +
            " clarified.occupation occupation " +
            " FROM children_basic_info basic " +
            " JOIN children_clarified_info clarified ON basic.child_id = clarified.child_id " +
            " WHERE (clarified.last_name || ' ' || clarified.first_name) = ?";

    private static final String SELECT_CHILDREN_BY_LAST_NAME = "SELECT " +
            " basic.child_id child_id, " +
            " basic.first_name bfirstn, " +
            " basic.last_name blastn, " +
            " basic.middle_name bmiddlen, " +
            " basic.birth_date bbirth, " +
            " basic.district_id district, " +
            " basic.personal_record_code code, " +
            " basic.entrance_date entrance_date, " +
            " clarified.birth_certificate_presence certificate, " +
            " clarified.first_name cfirstn, " +
            " clarified.last_name clastn, " +
            " clarified.middle_name cmiddlen, " +
            " clarified.birth_date cbirth, " +
            " clarified.address address, " +
            " clarified.birth_place birth_place, " +
            " clarified.occupation occupation " +
            " FROM children_basic_info basic " +
            " JOIN children_clarified_info clarified ON basic.child_id = clarified.child_id " +
            " WHERE clarified.last_name = ?";

    private static final String SELECT_CHILDREN_BY_BIRTH_DATE = "SELECT " +
            " basic.child_id child_id, " +
            " basic.first_name bfirstn, " +
            " basic.last_name blastn, " +
            " basic.middle_name bmiddlen, " +
            " basic.birth_date bbirth, " +
            " basic.district_id district, " +
            " basic.personal_record_code code, " +
            " basic.entrance_date entrance_date, " +
            " clarified.birth_certificate_presence certificate, " +
            " clarified.first_name cfirstn, " +
            " clarified.last_name clastn, " +
            " clarified.middle_name cmiddlen, " +
            " clarified.birth_date cbirth, " +
            " clarified.address address, " +
            " clarified.birth_place birth_place, " +
            " clarified.occupation occupation " +
            " FROM children_basic_info basic " +
            " JOIN children_clarified_info clarified ON basic.child_id = clarified.child_id " +
            " WHERE clarified.birth_date = ?";

    private static final String SELECT_CHILDREN_BY_BIRTH_YEAR = "SELECT " +
            " basic.child_id child_id, " +
            " basic.first_name bfirstn, " +
            " basic.last_name blastn, " +
            " basic.middle_name bmiddlen, " +
            " basic.birth_date bbirth, " +
            " basic.district_id district, " +
            " basic.personal_record_code code, " +
            " basic.entrance_date entrance_date, " +
            " clarified.birth_certificate_presence certificate, " +
            " clarified.first_name cfirstn, " +
            " clarified.last_name clastn, " +
            " clarified.middle_name cmiddlen, " +
            " clarified.birth_date cbirth, " +
            " clarified.address address, " +
            " clarified.birth_place birth_place, " +
            " clarified.occupation occupation " +
            " FROM children_basic_info basic " +
            " JOIN children_clarified_info clarified ON basic.child_id = clarified.child_id " +
            " WHERE EXTRACT(YEAR FROM clarified.birth_date) = ?";

    private static final String SELECT_CHILDREN_BY_DISTRICT = "SELECT " +
            " basic.child_id child_id, " +
            " basic.first_name bfirstn, " +
            " basic.last_name blastn, " +
            " basic.middle_name bmiddlen, " +
            " basic.birth_date bbirth, " +
            " basic.district_id district, " +
            " basic.personal_record_code code, " +
            " basic.entrance_date entrance_date, " +
            " clarified.birth_certificate_presence certificate, " +
            " clarified.first_name cfirstn, " +
            " clarified.last_name clastn, " +
            " clarified.middle_name cmiddlen, " +
            " clarified.birth_date cbirth, " +
            " clarified.address address, " +
            " clarified.birth_place birth_place, " +
            " clarified.occupation occupation " +
            " FROM children_basic_info basic " +
            " JOIN children_clarified_info clarified ON basic.child_id = clarified.child_id " +
            " WHERE basic.district_id = ?";

    private static final String SELECT_CHILD_BY_PERSONAL_RECORD_CODE = "SELECT " +
            " basic.child_id child_id, " +
            " basic.first_name bfirstn, " +
            " basic.last_name blastn, " +
            " basic.middle_name bmiddlen, " +
            " basic.birth_date bbirth, " +
            " basic.district_id district, " +
            " basic.personal_record_code code, " +
            " basic.entrance_date entrance_date, " +
            " clarified.birth_certificate_presence certificate, " +
            " clarified.first_name cfirstn, " +
            " clarified.last_name clastn, " +
            " clarified.middle_name cmiddlen, " +
            " clarified.birth_date cbirth, " +
            " clarified.address address, " +
            " clarified.birth_place birth_place, " +
            " clarified.occupation occupation " +
            " FROM children_basic_info basic " +
            " JOIN children_clarified_info clarified ON basic.child_id = clarified.child_id " +
            " WHERE basic.personal_record_code = ?";

    private static final String SELECT_CHILDREN_ENTERED_IN_RANGE_OF_DATES = "SELECT " +
            " basic.child_id child_id, " +
            " basic.first_name bfirstn, " +
            " basic.last_name blastn, " +
            " basic.middle_name bmiddlen, " +
            " basic.birth_date bbirth, " +
            " basic.district_id district, " +
            " basic.personal_record_code code, " +
            " basic.entrance_date entrance_date, " +
            " clarified.birth_certificate_presence certificate, " +
            " clarified.first_name cfirstn, " +
            " clarified.last_name clastn, " +
            " clarified.middle_name cmiddlen, " +
            " clarified.birth_date cbirth, " +
            " clarified.address address, " +
            " clarified.birth_place birth_place, " +
            " clarified.occupation occupation " +
            " FROM children_basic_info basic " +
            " JOIN children_clarified_info clarified ON basic.child_id = clarified.child_id " +
            " WHERE basic.entrance_date BETWEEN ? AND ?";

    private static final String SELECT_CHILD_DETENTIONS = "SELECT " +
            " detention_id, child_id, detentioned_by, detention_date, detention_address " +
            " FROM detentions " +
            " WHERE child_id = ?";


    private static final String SELECT_CHILD_PARENTS = "SELECT " +
            " parent_id, child_id, parent_type_id, parent_name, parent_info " +
            " FROM parents " +
            " WHERE child_id = ?";

    private static final String INSERT_CHILD_FUNCTION = "child_initial_insert";

    private static final String INSERT_CHILD_DETENTION = "INSERT INTO " +
            " detentions (child_id, detentioned_by, detention_date, detention_address) " +
            " VALUES (?,?,?,?)";
    private static final String INSERT_CHILD_PARENT = "INSERT INTO " +
            " parents (child_id, parent_type_id, parent_name, parent_info) " +
            " VALUES (?, ?, ?, ?)";


    private class ChildrenRowMapper implements RowMapper<Child> {
        public Child mapRow(ResultSet resultSet, int i) throws SQLException {
            BigInteger childId = resultSet.getBigDecimal(ChildColumnName.CHILD_ID.toString()).toBigInteger();
            String firstName = resultSet.getString(ChildColumnName.BASIC_FIRST_NAME.toString());
            String lastName = resultSet.getString(ChildColumnName.BASIC_LAST_NAME.toString());
            String middleName = resultSet.getString(ChildColumnName.BASIC_MIDDLE_NAME.toString());
            Date birthDate = resultSet.getDate(ChildColumnName.BASIC_BIRTH.toString());
            String personalRecordCode = resultSet.getString(ChildColumnName.PERSONAL_RECORD_CODE.toString());
            Date entranceDate = resultSet.getDate(ChildColumnName.ENTRANCE_DATE.toString());
            boolean isBirthCertificatePresent = resultSet.getBoolean(ChildColumnName.CERTIFICATE.toString());
            String clarifiedFirstName = resultSet.getString(ChildColumnName.CLARIFIED_FIRST_NAME.toString());
            String clarifiedLastName = resultSet.getString(ChildColumnName.CLARIFIED_LAST_NAME.toString());
            String clarifiedMiddleName = resultSet.getString(ChildColumnName.CLARIFIED_MIDDLE_NAME.toString());
            Date clarifiedBirthDate = resultSet.getDate(ChildColumnName.CLARIFIED_BIRTH.toString());
            String address = resultSet.getString(ChildColumnName.ADDRESS.toString());
            String birthPlace = resultSet.getString(ChildColumnName.BIRTH_PLACE.toString());
            String occupation = resultSet.getString(ChildColumnName.OCCUPATION.toString());

            BigInteger districtId = resultSet.getBigDecimal(ChildColumnName.CHILD_ID.toString()).toBigInteger();
            Districts district = Districts.getDistrictById(districtId);


            ClarifiedChild clarifiedChild = new ClarifiedChildImpl.ClarifiedChildBuilder(isBirthCertificatePresent, null, childId,
                    clarifiedFirstName, clarifiedLastName, clarifiedMiddleName, clarifiedBirthDate, address, birthPlace, occupation)
                    .buildClarifiedChild();

            Child child = new ChildImpl.ChildBasicBuilder(childId, firstName, lastName, middleName, birthDate, personalRecordCode, entranceDate)
                    .setDistrict(district)
                    .setClarifiedChild(clarifiedChild)
                    .buildChild();

            return child;
        }
    }


    private class ParentRowMapper implements RowMapper<Parent> {
        public Parent mapRow(ResultSet resultSet, int i) throws SQLException {
            BigInteger parentId = resultSet.getBigDecimal(ParentColumnName.PARENT_ID.toString()).toBigInteger();
            BigInteger childId = resultSet.getBigDecimal(ParentColumnName.CHILD_ID.toString()).toBigInteger();
            String parentName = resultSet.getString(ParentColumnName.PARENT_NAME.toString());
            String parentInfo = resultSet.getString(ParentColumnName.PARENT_INFO.toString());
            BigInteger parentTypeId = resultSet.getBigDecimal(ParentColumnName.PARENT_TYPE_ID.toString()).toBigInteger();

            ParentTypes parentType = ParentTypes.getParentTypeById(parentTypeId);

            Parent parent = new ParentImpl.ParentBuilder(parentId, childId,
                    parentType, parentName, parentInfo).buildParent();
            return parent;
        }
    }



    private enum ChildColumnName {
        CHILD_ID("child_id"),
        BASIC_FIRST_NAME("bfirstn"),
        BASIC_LAST_NAME("blastn"),
        BASIC_MIDDLE_NAME("bmiddlen"),
        BASIC_BIRTH("bbirth"),
        DISTRICT("district"),
        PERSONAL_RECORD_CODE("code"),
        ENTRANCE_DATE("entrance_date"),
        CERTIFICATE("certificate"),
        CLARIFIED_FIRST_NAME("cfirstn"),
        CLARIFIED_LAST_NAME("clastn"),
        CLARIFIED_MIDDLE_NAME("cmiddlen"),
        CLARIFIED_BIRTH("cbirth"),
        ADDRESS("address"),
        BIRTH_PLACE("birth_place"),
        OCCUPATION("occupation");

        final private String colName;

        ChildColumnName(String colName) {
            this.colName = colName;
        }

        @Override
        public String toString() {
            return colName;
        }

    }

    private enum ParentColumnName {
        PARENT_ID("parent_id"),
        CHILD_ID("child_id"),
        PARENT_TYPE_ID("parent_type_id"),
        PARENT_NAME("parent_name"),
        PARENT_INFO("parent_info");

        final private String colName;

        ParentColumnName(String colName) {
            this.colName = colName;
        }

        @Override
        public String toString() {
            return colName;
        }
    }


}
