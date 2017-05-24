package iq.ven.workflow.dao.impl;

import iq.ven.workflow.dao.ChildrenDAO;
import iq.ven.workflow.models.*;
import iq.ven.workflow.models.impl.ChildImpl;
import iq.ven.workflow.models.impl.ClarifiedChildImpl;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.math.BigDecimal;
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


    public Child saveChildToDB(String firstName, String lastName, String middleName, Date birthDate, Districts district,
                               String personalRecordCode, Date entranceDate, boolean isBirthCertificatePresent, String clarifiedFirstName,
                               String clarifiedLastName, String clarifiedMiddleName, Date clarifiedBirthDate, String address, String birthPlace,
                               String occupation) {
        simpleCallTemplate.withFunctionName(INSERT_CHILD_FUNCTION);
        SqlParameterSource in = new MapSqlParameterSource()
                .addValue("_firstname", firstName)
                .addValue("_lastname", lastName)
                .addValue("_middlename", middleName)
                .addValue("_birthdate", birthDate)
                .addValue("_district", district.getDbId())
                .addValue("_personalrecordcode", personalRecordCode)
                .addValue("_entrancedate", entranceDate)
                .addValue("_isbirthcertificatepresent", isBirthCertificatePresent)
                .addValue("_clarifiedfirstname", clarifiedFirstName)
                .addValue("_clarifiedlastname", clarifiedLastName)
                .addValue("_clarifiedmiddlename", clarifiedMiddleName)
                .addValue("_clarifiedbirthdate", clarifiedBirthDate)
                .addValue("_address", address)
                .addValue("_birthplace", birthPlace)
                .addValue("_occupation", occupation);
        BigInteger insertedChildId = simpleCallTemplate.executeFunction(BigDecimal.class, in).toBigInteger();

        return getChildById(insertedChildId);
    }

    public List<Child> getAllChildrenList() {
        try {
            return generalTemplate.query(SELECT_ALL_CHILDREN, new ChildrenRowMapper());
        } catch (DataAccessException e) {
            LOGGER.error("", e);
            return null;
        } catch (Exception e) {
            LOGGER.error("", e);
            return null;
        }
    }

    public Child getChildById(BigInteger childId) {
        try {
            return generalTemplate.queryForObject(SELECT_CHILD_BY_ID, new Object[]{childId}, new ChildrenRowMapper());
        } catch (DataAccessException e) {
            LOGGER.error("", e);
            return null;
        } catch (Exception e) {
            LOGGER.error("", e);
            return null;
        }
    }

    public Child getChildByFullName(String fullName) {
        try {
            return generalTemplate.queryForObject(SELECT_CHILD_BY_FULL_NAME, new Object[]{fullName}, new ChildrenRowMapper());
        } catch (DataAccessException e) {
            LOGGER.error("", e);
            return null;
        } catch (Exception e) {
            LOGGER.error("", e);
            return null;
        }
    }

    public List<Child> getChildrenByLastName(String lastName) {
        try {
            return generalTemplate.query(SELECT_CHILDREN_BY_LAST_NAME, new Object[]{lastName}, new ChildrenRowMapper());
        } catch (DataAccessException e) {
            LOGGER.error("", e);
            return null;
        } catch (Exception e) {
            LOGGER.error("", e);
            return null;
        }
    }

    public List<Child> getChildrenByBirthDate(Date birthDate) {
        try {
            return generalTemplate.query(SELECT_CHILDREN_BY_BIRTH_DATE, new Object[]{birthDate}, new ChildrenRowMapper());
        } catch (DataAccessException e) {
            LOGGER.error("", e);
            return null;
        } catch (Exception e) {
            LOGGER.error("", e);
            return null;
        }
    }

    public List<Child> getChildrenByBirthYear(int birthYear) {
        try {
            return generalTemplate.query(SELECT_CHILDREN_BY_BIRTH_YEAR, new Object[]{birthYear}, new ChildrenRowMapper());
        } catch (DataAccessException e) {
            LOGGER.error("", e);
            return null;
        } catch (Exception e) {
            LOGGER.error("", e);
            return null;
        }
    }

    public List<Child> getChildrenByDistrict(Districts district) {
        try {
            return generalTemplate.query(SELECT_CHILDREN_BY_DISTRICT, new Object[]{district.getDbId()}, new ChildrenRowMapper());
        } catch (DataAccessException e) {
            LOGGER.error("", e);
            return null;
        } catch (Exception e) {
            LOGGER.error("", e);
            return null;
        }
    }

    public Child getChildByPersonalRecordCode(String personalRecordCode) {
        try {
            return generalTemplate.queryForObject(SELECT_CHILD_BY_PERSONAL_RECORD_CODE, new Object[]{personalRecordCode}, new ChildrenRowMapper());
        } catch (DataAccessException e) {
            LOGGER.error("", e);
            return null;
        } catch (Exception e) {
            LOGGER.error("", e);
            return null;
        }
    }

    public List<Child> getChildrenEnteredInRangeOfDates(Date after, Date before) {
        try {
            return generalTemplate.query(SELECT_CHILDREN_ENTERED_IN_RANGE_OF_DATES, new Object[]{after, before}, new ChildrenRowMapper());
        } catch (DataAccessException e) {
            LOGGER.error("", e);
            return null;
        } catch (Exception e) {
            LOGGER.error("", e);
            return null;
        }
    }

    public ClarifiedChild getClarifiedChild(Child child) {
        return null;
    }

    public List<Detention> getChildDetentions(Child child) {
        try {
            return generalTemplate.query(SELECT_CHILD_DETENTIONS, new Object[]{child.getChildId()}, new DetentionRowMapper());
        } catch (DataAccessException e) {
            LOGGER.error("", e);
            return null;
        } catch (Exception e) {
            LOGGER.error("", e);
            return null;
        }
    }

    public List<Parent> getChildParents(Child child) {
        try {
            return generalTemplate.query(SELECT_CHILD_PARENTS, new Object[]{child.getChildId()}, new ParentRowMapper());
        } catch (DataAccessException e) {
            LOGGER.error("", e);
            return null;
        } catch (Exception e) {
            LOGGER.error("", e);
            return null;
        }
    }

    public boolean addDetentionToChild(Child child, Detention detention) {
        try {
            if (child != null && detention != null) {
                generalTemplate.update(INSERT_CHILD_DETENTION,
                        child.getChildId(), detention.getDetentionDoneByWho(), detention.getDetentionDate(), detention.getDetentionAddress());
                return true;
            } else {
                return false;
            }
        } catch (DataAccessException e) {
            LOGGER.error("", e);
            return false;
        } catch (Exception e) {
            LOGGER.error("", e);
            return false;
        }
    }

    public boolean addParentToChild(Child child, Parent parent) {
        try {
            if (child != null && parent != null) {
                generalTemplate.update(INSERT_CHILD_PARENT,
                        child.getChildId(), parent.getParentType().getDbId(), parent.getParentName(), parent.getParentInfo());
                return true;
            } else {
                return false;
            }
        } catch (DataAccessException e) {
            LOGGER.error("", e);
            return false;
        } catch (Exception e) {
            LOGGER.error("", e);
            return false;
        }
    }

    public boolean addFileToChild(Child child, File file) {
        return false;
    }

    public boolean deleteChild(Child child) {
        return false;
    }

    private static final String SELECT_ALL_CHILDREN = "select " +
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
            " from children_basic_info basic " +
            " join children_clarified_info clarified on basic.child_id = clarified.child_id";

    private static final String SELECT_CHILD_BY_ID = "select " +
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
            " from children_basic_info basic " +
            " join children_clarified_info clarified on basic.child_id = clarified.child_id " +
            " where basic.child_id = ?";

    private static final String SELECT_CHILD_BY_FULL_NAME = "select " +
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
            " from children_basic_info basic " +
            " join children_clarified_info clarified on basic.child_id = clarified.child_id " +
            " where (clarified.last_name || ' ' || clarified.first_name) = ?";

    private static final String SELECT_CHILDREN_BY_LAST_NAME = "select " +
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
            " from children_basic_info basic " +
            " join children_clarified_info clarified on basic.child_id = clarified.child_id " +
            " where clarified.last_name = ?";

    private static final String SELECT_CHILDREN_BY_BIRTH_DATE = "select " +
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
            " from children_basic_info basic " +
            " join children_clarified_info clarified on basic.child_id = clarified.child_id " +
            " where clarified.birth_date = ?";

    private static final String SELECT_CHILDREN_BY_BIRTH_YEAR = "select " +
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
            " from children_basic_info basic " +
            " join children_clarified_info clarified on basic.child_id = clarified.child_id " +
            " where EXTRACT(YEAR FROM clarified.birth_date) = ?";

    private static final String SELECT_CHILDREN_BY_DISTRICT = "select " +
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
            " from children_basic_info basic " +
            " join children_clarified_info clarified on basic.child_id = clarified.child_id " +
            " where basic.district_id = ?";

    private static final String SELECT_CHILD_BY_PERSONAL_RECORD_CODE = "select " +
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
            " from children_basic_info basic " +
            " join children_clarified_info clarified on basic.child_id = clarified.child_id " +
            " where basic.personal_record_code = ?";

    private static final String SELECT_CHILDREN_ENTERED_IN_RANGE_OF_DATES = "select " +
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
            " from children_basic_info basic " +
            " join children_clarified_info clarified on basic.child_id = clarified.child_id " +
            " where basic.entrance_date BETWEEN ? AND ?";

    private static final String SELECT_CHILD_DETENTIONS = "select " +
            " detention_id, child_id, detentioned_by, detention_date, detention_address " +
            " from detentions " +
            " where child_id = ?";


    private static final String SELECT_CHILD_PARENTS = "select " +
            " parent_id, child_id, parent_type_id, parent_name, parent_info " +
            " from parents " +
            " where child_id = ?";

    private static final String INSERT_CHILD_FUNCTION = "child_initial_insert";

    private static final String INSERT_CHILD_DETENTION = "insert into " +
            " detentions (child_id, detentioned_by, detention_date, detention_address) " +
            " values (?,?,?,?)";
    private static final String INSERT_CHILD_PARENT = "insert into " +
            " parents (child_id, parent_type_id, parent_name, parent_info) " +
            " values (?, ?, ?, ?)";


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
            Parent parent = null;


            return null;
        }
    }

    private class DetentionRowMapper implements RowMapper<Detention> {
        public Detention mapRow(ResultSet resultSet, int i) throws SQLException {
            Detention detention = null;


            return null;
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

    private enum DetentionColumnName {
        DETENTION_ID("detention_id"),
        CHILD_ID("child_id"),
        DETENTIONED_BY("detentioned_by"),
        DETENTION_DATE("detention_date"),
        DETENTION_ADDRESS("detention_address");

        final private String colName;

        DetentionColumnName(String colName) {
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
