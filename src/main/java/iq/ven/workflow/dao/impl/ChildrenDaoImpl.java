package iq.ven.workflow.dao.impl;

import iq.ven.workflow.dao.ChildrenDAO;
import iq.ven.workflow.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

@Repository("childrenDaoImpl")
public class ChildrenDaoImpl implements ChildrenDAO {

    @Autowired
    private SimpleJdbcCall simpleCallTemplate;

    @Autowired
    private JdbcTemplate generalTemplate;


    public Child saveChildToDB(String firstName, String lastName, String middleName, Date birthDate, Districts district, String personalRecordCode, Date entranceDate, boolean isBirthCertificatePresent, String clarifiedFirstName, String clarifiedLastName, String clarifiedMiddleName, Date clarifiedBirthDate, String address, String birthPlace, String occupation) {
        return null;
    }

    public List<Child> getAllChildrenList() {
        return null;
    }

    public Child getChildById(BigInteger childId) {
        return null;
    }

    public Child getChildByFullName(String fullName) {
        return null;
    }

    public List<Child> getChildrenByLastName(String lastName) {
        return null;
    }

    public List<Child> getChildrenByBirthDate(Date birthDate) {
        return null;
    }

    public List<Child> getChildrenByBirthYear(int birthYear) {
        return null;
    }

    public List<Child> getChildrenByDistrict(Districts district) {
        return null;
    }

    public Child getChildByPersonalRecordCode(String personalRecordCode) {
        return null;
    }

    public List<Child> getChildrenEnteredInRangeOfDates(Date after, Date before) {
        return null;
    }

    public ClarifiedChild getClarifiedChild(Child child) {
        return null;
    }

    public List<Detention> getChildDetentions(Child child) {
        return null;
    }

    public List<Parent> getChildParents(Child child) {
        return null;
    }

    public boolean addDetentionToChild(Child child, Detention detention) {
        return false;
    }

    public boolean addParentToChild(Child child, Parent parent) {
        return false;
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
            " values (?,?,?,?,)";
    private static final String INSERT_CHILD_PARENT = "insert into " +
            " parents (child_id, parent_type_id, parent_name, parent_info) " +
            " values (?, ?, ?, ?)";


    private class ChildrenRowMapper implements RowMapper<Child> {
        public Child mapRow(ResultSet resultSet, int i) throws SQLException {
            Child child = null;
            ClarifiedChild clarifiedChild = null;

            return null;
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
        CODE("code"),
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
