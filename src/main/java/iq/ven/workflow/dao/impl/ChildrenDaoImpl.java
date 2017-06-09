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


            //   generalTemplate.update(INSERT_BASIC_CHILD);
            //    BigInteger childIdFromDb = generalTemplate.queryForObject(SELECT_BASIC_CHILD_ID_BY_PERSONAL_RECORD_CODE, BigInteger.class, personalRecordCode);
            //   generalTemplate.update(INSERT_CLARIFIED_CHILD, childIdFromDb.longValue(), isBirthCertificatePresent, clarifiedFirstName, clarifiedLastName, clarifiedMiddleName, clarifiedBirthDate,
            //          address, birthPlace, occupation);

            return getChildById(BigInteger.ONE);
        } catch (DataAccessException e) {
            LOGGER.error("Error in inserting child to db with params  " + child, e);
            return null;
        } catch (Exception e) {
            LOGGER.error("Error in inserting child to db with params " + child, e);
            return null;
        }
    }

    @Override
    public List<Child> getAllChildrenList() {
        try {
            return generalTemplate.query(SELECT_ALL_CHILDREN, new ChildrenCutRowMapper());
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
            return generalTemplate.queryForObject(SELECT_CHILD_BY_ID, new Object[]{childId.longValue()}, new ChildrenFullRowMapper());
        } catch (DataAccessException e) {
            LOGGER.error("Error in fetching child by ID " + childId, e);
            return null;
        } catch (Exception e) {
            LOGGER.error("Error in fetching child by ID " + childId, e);
            return null;
        }
    }

    @Override
    public List<Child> getChildrenByFullName(String fullName) {
        try {
            return generalTemplate.query(SELECT_CHILDREN_BY_FULL_NAME, new Object[]{fullName}, new ChildrenCutRowMapper());
        } catch (DataAccessException e) {
            LOGGER.error("Error in fetching children by full name " + fullName, e);
            return null;
        } catch (Exception e) {
            LOGGER.error("Error in fetching children by full name " + fullName, e);
            return null;
        }
    }

    @Override
    public List<Child> getChildrenByLastName(String lastName) {
        try {
            return generalTemplate.query(SELECT_CHILDREN_BY_LAST_NAME, new Object[]{lastName}, new ChildrenCutRowMapper());
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
            return generalTemplate.query(SELECT_CHILDREN_BY_BIRTH_DATE, new Object[]{birthDate}, new ChildrenCutRowMapper());
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
            return generalTemplate.query(SELECT_CHILDREN_BY_BIRTH_YEAR, new Object[]{birthYear}, new ChildrenCutRowMapper());
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
            return generalTemplate.query(SELECT_CHILDREN_BY_DISTRICT, new Object[]{district.getDbId()}, new ChildrenCutRowMapper());
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
    public List<Child> getChildrenByPersonalRecordCode(String personalRecordCode) {
        try {
            return generalTemplate.query(SELECT_CHILD_BY_PERSONAL_RECORD_CODE, new Object[]{personalRecordCode}, new ChildrenCutRowMapper());
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
                    new Object[]{after, before}, new ChildrenCutRowMapper());
        } catch (DataAccessException e) {
            LOGGER.error("Error in fetching children entered between " + after + " and " + before, e);
            return null;
        } catch (Exception e) {
            LOGGER.error("Error in fetching children entered between " + after + " and " + before, e);
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
                //       generalTemplate.update(INSERT_CHILD_DETENTION);
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
                //      generalTemplate.update(INSERT_CHILD_PARENT);
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
    public boolean addPhotoToChild(Child child, ChildFile file) {
        return false;
    }

    @Override
    public boolean addFileToChild(Child child, ChildFile file) {
        return false;
    }

    @Override
    public boolean deleteChild(Child child) {
        return false;
    }

    private static final String SELECT_ALL_CHILDREN = "SELECT" +
            " basic.child_id child_id," +
            " basic.personal_record_code code," +
            " basic.entrance_date entrance_date," +
            " basic.retire_date retire_date," +
            " basic.photo photo," +
            " clarified.first_name cfirstn," +
            " clarified.last_name clastn," +
            " clarified.middle_name cmiddlen," +
            " clarified.birth_date cbirth," +
            " clarified.district_id district," +
            " clarified.birth_place birth_place" +
            " FROM children_basic_info basic" +
            " JOIN children_clarified_info clarified ON basic.child_id = clarified.child_id";

    private static final String SELECT_CHILD_BY_ID = "SELECT" +
            " basic.child_id child_id," +
            " basic.first_name bfirstn," +
            " basic.last_name blastn," +
            " basic.middle_name bmiddlen," +
            " basic.birth_date bbirth," +
            " basic.personal_record_code code," +
            " basic.entrance_date entrance_date," +
            " basic.retire_date retire_date," +
            " basic.photo photo," +
            " clarified.first_name cfirstn," +
            " clarified.last_name clastn," +
            " clarified.middle_name cmiddlen," +
            " clarified.address address," +
            " clarified.occupation occupation," +
            " clarified.comes_from_city comefromcity," +
            " clarified.comes_from_date comefromdate," +
            " clarified.duty_officer dutyofficer," +
            " clarified.judged_or_detained_info judged," +
            " clarified.child_notes notes," +
            " clarified.birth_date cbirth," +
            " clarified.district_id district," +
            " clarified.birth_place birth_place," +
            " detentions.detention_by detby," +
            " detentions.detention_when detwhen," +
            " detentions.detention_why detwhy," +
            " detentions.detention_where detwhere" +
            " FROM children_basic_info basic" +
            " JOIN children_clarified_info clarified ON basic.child_id = clarified.child_id" +
            " JOIN detentions detentions ON clarified.detention_info_id = detentions.detention_id" +
            " WHERE basic.child_id = ?";

    private static final String SELECT_CHILDREN_BY_FULL_NAME = "SELECT" +
            " basic.child_id child_id," +
            " basic.personal_record_code code," +
            " basic.entrance_date entrance_date," +
            " basic.retire_date retire_date," +
            " basic.photo photo," +
            " clarified.first_name cfirstn," +
            " clarified.last_name clastn," +
            " clarified.middle_name cmiddlen," +
            " clarified.birth_date cbirth," +
            " clarified.district_id district," +
            " clarified.birth_place birth_place" +
            " FROM children_basic_info basic" +
            " JOIN children_clarified_info clarified ON basic.child_id = clarified.child_id" +
            " WHERE (clarified.last_name || ' ' || clarified.first_name) = ?";

    private static final String SELECT_CHILDREN_BY_LAST_NAME = "SELECT" +
            " basic.child_id child_id," +
            " basic.personal_record_code code," +
            " basic.entrance_date entrance_date," +
            " basic.retire_date retire_date," +
            " basic.photo photo," +
            " clarified.first_name cfirstn," +
            " clarified.last_name clastn," +
            " clarified.middle_name cmiddlen," +
            " clarified.birth_date cbirth," +
            " clarified.district_id district," +
            " clarified.birth_place birth_place" +
            " FROM children_basic_info basic" +
            " JOIN children_clarified_info clarified ON basic.child_id = clarified.child_id" +
            " WHERE clarified.last_name = ?";

    private static final String SELECT_CHILDREN_BY_BIRTH_DATE = "SELECT" +
            " basic.child_id child_id," +
            " basic.personal_record_code code," +
            " basic.entrance_date entrance_date," +
            " basic.retire_date retire_date," +
            " basic.photo photo," +
            " clarified.first_name cfirstn," +
            " clarified.last_name clastn," +
            " clarified.middle_name cmiddlen," +
            " clarified.birth_date cbirth," +
            " clarified.district_id district," +
            " clarified.birth_place birth_place" +
            " FROM children_basic_info basic" +
            " JOIN children_clarified_info clarified ON basic.child_id = clarified.child_id" +
            " WHERE clarified.birth_date = ?";

    private static final String SELECT_CHILDREN_BY_BIRTH_YEAR = "SELECT" +
            " basic.child_id child_id," +
            " basic.personal_record_code code," +
            " basic.entrance_date entrance_date," +
            " basic.retire_date retire_date," +
            " basic.photo photo," +
            " clarified.first_name cfirstn," +
            " clarified.last_name clastn," +
            " clarified.middle_name cmiddlen," +
            " clarified.birth_date cbirth," +
            " clarified.district_id district," +
            " clarified.birth_place birth_place" +
            " FROM children_basic_info basic" +
            " JOIN children_clarified_info clarified ON basic.child_id = clarified.child_id" +
            " WHERE EXTRACT(YEAR FROM clarified.birth_date) = ?";

    private static final String SELECT_CHILDREN_BY_DISTRICT = "SELECT" +
            " basic.child_id child_id," +
            " basic.personal_record_code code," +
            " basic.entrance_date entrance_date," +
            " basic.retire_date retire_date," +
            " basic.photo photo," +
            " clarified.first_name cfirstn," +
            " clarified.last_name clastn," +
            " clarified.middle_name cmiddlen," +
            " clarified.birth_date cbirth," +
            " clarified.district_id district," +
            " clarified.birth_place birth_place" +
            " FROM children_basic_info basic" +
            " JOIN children_clarified_info clarified ON basic.child_id = clarified.child_id" +
            " WHERE basic.district_id = ?";

    private static final String SELECT_CHILD_BY_PERSONAL_RECORD_CODE = "SELECT" +
            " basic.child_id child_id," +
            " basic.personal_record_code code," +
            " basic.entrance_date entrance_date," +
            " basic.retire_date retire_date," +
            " basic.photo photo," +
            " clarified.first_name cfirstn," +
            " clarified.last_name clastn," +
            " clarified.middle_name cmiddlen," +
            " clarified.birth_date cbirth," +
            " clarified.district_id district," +
            " clarified.birth_place birth_place" +
            " FROM children_basic_info basic" +
            " JOIN children_clarified_info clarified ON basic.child_id = clarified.child_id" +
            " WHERE basic.personal_record_code = ?";

    private static final String SELECT_CHILDREN_ENTERED_IN_RANGE_OF_DATES = "SELECT" +
            " basic.child_id child_id," +
            " basic.personal_record_code code," +
            " basic.entrance_date entrance_date," +
            " basic.retire_date retire_date," +
            " basic.photo photo," +
            " clarified.first_name cfirstn," +
            " clarified.last_name clastn," +
            " clarified.middle_name cmiddlen," +
            " clarified.birth_date cbirth," +
            " clarified.district_id district," +
            " clarified.birth_place birth_place" +
            " FROM children_basic_info basic" +
            " JOIN children_clarified_info clarified ON basic.child_id = clarified.child_id" +
            " WHERE basic.entrance_date BETWEEN ? AND ?";


    private static final String SELECT_CHILD_PARENTS = "SELECT " +
            " parent_id, child_id, parent_type_id, parent_name, parent_info, parent_birth_date" +
            " FROM parents " +
            " WHERE child_id = ?";

    private static final String INSERT_CHILD_DETENTION = "";

    private static final String INSERT_CHILD_PARENT = "INSERT INTO " +
            " parents (child_id, parent_type_id, parent_name, parent_info, parent_birth_date) " +
            " VALUES (?, ?, ?, ?, ?)";

    private class ChildrenFullRowMapper implements RowMapper<Child> {
        public Child mapRow(ResultSet rs, int i) throws SQLException {
            Child childFull = null;
            return childFull;
        }
    }

    private class ChildrenCutRowMapper implements RowMapper<Child> {
        public Child mapRow(ResultSet rs, int i) throws SQLException {

            BigInteger childId = rs.getBigDecimal(ChildColumnName.CHILD_ID.toString()).toBigInteger();
            String personalRecordCode = rs.getString(ChildColumnName.PERSONA_RECORD_CODE.toString());
            Date entranceDate = rs.getDate(ChildColumnName.ENTRANCE_DATE.toString());
            Date retireDate = rs.getDate(ChildColumnName.RETIRE_DATE.toString());
            Date clarifiedBirthDate = rs.getDate(ChildColumnName.CLARIFIED_BIRTH_DATE.toString());
            byte[] photo = rs.getBytes(ChildColumnName.PHOTO.toString());
            String clarifiedFirstName = rs.getString(ChildColumnName.CLARIFIED_FIRST_NAME.toString());
            String clarifiedLastName = rs.getString(ChildColumnName.CLARIFIED_LAST_NAME.toString());
            String clarifiedMiddleName = rs.getString(ChildColumnName.CLARIFIED_MIDDLE_NAME.toString());
            String birthPlace = rs.getString(ChildColumnName.BIRTH_PLACE.toString());
            BigInteger districtId = rs.getBigDecimal(ChildColumnName.DISTRICT.toString()).toBigInteger();

            Districts district = Districts.getDistrictById(districtId);

            ClarifiedChildImpl clarifiedChildCut = new ClarifiedChildImpl();
            clarifiedChildCut.setBirthPlace(birthPlace);
            clarifiedChildCut.setDistrict(district);

            Child childCut = new ChildImpl.ChildBasicBuilder(clarifiedFirstName, clarifiedLastName, clarifiedMiddleName,
                    clarifiedBirthDate, personalRecordCode, entranceDate)
                    .buildChildId(childId)
                    .buildRetiredDate(retireDate)
                    .buildPhoto(photo)
                    .buildClarifiedChild(clarifiedChildCut)
                    .buildChild();
            return childCut;
        }
    }


    private class ParentRowMapper implements RowMapper<Parent> {
        public Parent mapRow(ResultSet rs, int i) throws SQLException {
            BigInteger childId = rs.getBigDecimal(ParentColumnName.CHILD_ID.toString()).toBigInteger();
            BigInteger parentId = rs.getBigDecimal(ParentColumnName.PARENT_ID.toString()).toBigInteger();
            String parentFullName = rs.getString(ParentColumnName.PARENT_NAME.toString());
            String parentInfo = rs.getString(ParentColumnName.PARENT_INFO.toString());
            Date parentBirthDate = rs.getDate(ParentColumnName.PARENT_BIRTH_DATE.toString());
            BigInteger parentTypeId = rs.getBigDecimal(ParentColumnName.PARENT_TYPE_ID.toString()).toBigInteger();

            ParentTypes parentType = ParentTypes.getParentTypeById(parentTypeId);

            Parent parent = new ParentImpl.ParentBuilder(childId, parentType, parentFullName)
                    .buildParentBirthDate(parentBirthDate)
                    .buildParentId(parentId)
                    .buildParentInfo(parentInfo)
                    .buildParent();
            return parent;
        }
    }

    private enum ChildColumnName {
        CHILD_ID("child_id"),
        PERSONA_RECORD_CODE("code"),
        ENTRANCE_DATE("entrance_date"),
        RETIRE_DATE("retire_date"),
        PHOTO("photo"),
        CLARIFIED_FIRST_NAME("cfirstn"),
        CLARIFIED_LAST_NAME("clastn"),
        CLARIFIED_MIDDLE_NAME("cmiddlen"),
        CLARIFIED_BIRTH_DATE("cbirth"),
        DISTRICT("district"),
        BIRTH_PLACE("birth_place"),
        BASIC_FIRST_NAME("bfirstn"),
        BASIC_LAST_NAME("blastn"),
        BASIC_MIDDLE_NAME("bmiddlen"),
        BASIC_BIRTH_DATE("bbirth"),
        ADDRESS("address"),
        OCCUPATION("occupation"),
        COME_FROM_CITY("comefromcity"),
        COME_FROM_DATE("comefromdate"),
        DUTY_OFFICER("dutyofficer"),
        JUDGED_INFO("judged"),
        NOTES("notes"),
        DETAINED_BY_WHO("detby"),
        DETAINED_WHEN_DATE("detwhen"),
        DETAINED_WHY_REASON("detwhy"),
        DETAINED_WHERE_PLACE("detwhere");

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
        PARENT_INFO("parent_info"),
        PARENT_BIRTH_DATE("parent_birth_date");

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
