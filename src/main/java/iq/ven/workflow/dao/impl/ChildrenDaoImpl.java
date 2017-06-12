package iq.ven.workflow.dao.impl;

import iq.ven.workflow.dao.ChildrenDAO;
import iq.ven.workflow.models.*;
import iq.ven.workflow.models.impl.*;
import iq.ven.workflow.models.requests.ChildCreationRequest;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

@Repository("childrenDaoImpl")
public class ChildrenDaoImpl implements ChildrenDAO {
    private static final Logger LOGGER = Logger.getLogger(ChildrenDaoImpl.class);

    @Autowired
    private JdbcTemplate generalTemplate;

    @Override
    public Child saveChildToDB(Child child) {
        try {
            generalTemplate.update(INSERT_BASIC_CHILD, child.getFirstName(), child.getLastName(), child.getMiddleName(),
                    child.getBirthDate(), child.getPersonalRecordCode(), child.getEntranceDate(), child.getRetiredDate());
            BigInteger childId = generalTemplate.queryForObject(SELECT_BASIC_CHILD_ID_BY_PERSONAL_RECORD_CODE, BigInteger.class, child.getPersonalRecordCode());

            Detention detention = child.getClarifiedInfo().getDetention();
            generalTemplate.update(INSERT_CHILD_DETENTION, detention.getDetainedBy(), detention.getDetentionDate(), detention.getDetentionAddress(), detention.getGotDetainedFor());
            BigInteger detentionId = generalTemplate.queryForObject(SELECT_DETENTION_ID, BigInteger.class,
                    detention.getDetentionDate(), detention.getGotDetainedFor());


            ClarifiedChild clarifiedChild = child.getClarifiedInfo();
            generalTemplate.update(INSERT_CLARIFIED_CHILD, childId.longValue(), clarifiedChild.getFirstName(), clarifiedChild.getLastName(),
                    clarifiedChild.getMiddleName(), clarifiedChild.getBirthDate(), clarifiedChild.getAddress(), clarifiedChild.getBirthPlace(),
                    clarifiedChild.getOccupation(), clarifiedChild.getFromCame(), clarifiedChild.getWhenCame(), clarifiedChild.getDutyOfficer(),
                    clarifiedChild.getDistrict().getDbId().longValue(), clarifiedChild.getJudgedOrDetainedInfo(), clarifiedChild.getNotes(),
                    detentionId.longValue());


            return getChildById(childId);
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
            return generalTemplate.queryForObject(SELECT_FULL_CHILD_BY_ID, new Object[]{childId.longValue()}, new ChildrenFullRowMapper());
        } catch (DataAccessException e) {
            LOGGER.error("Error in fetching child by ID " + childId, e);
            return null;
        } catch (Exception e) {
            LOGGER.error("Error in fetching child by ID " + childId, e);
            return null;
        }
    }

    @Override
    public Child getChildByIdCut(BigInteger childId) {
        try {
            return generalTemplate.queryForObject(SELECT_CUT_CHILD_BY_ID, new Object[]{childId.longValue()}, new ChildrenCutRowMapper());
        } catch (DataAccessException e) {
            LOGGER.error("Error in fetching child by ID cut " + childId, e);
            return null;
        } catch (Exception e) {
            LOGGER.error("Error in fetching child by ID cut" + childId, e);
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
            return generalTemplate.query(SELECT_CHILDREN_BY_DISTRICT, new Object[]{district.getDbId().longValue()}, new ChildrenCutRowMapper());
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
    public List<Child> getChildrenByParentName(String parentName) {
        try {
            return generalTemplate.query(SELECT_CHILDREN_BY_PARENT_NAME,
                    new Object[]{parentName}, new ChildrenCutRowMapper());
        } catch (DataAccessException e) {
            LOGGER.error("Error in fetching children with parent named " + parentName, e);
            return null;
        } catch (Exception e) {
            LOGGER.error("Error in fetching children with parent named " + parentName, e);
            return null;
        }
    }


    @Override
    public List<Child> getChildrenByDutyOfficer(String dutyOfficerName) {
        try {
            return generalTemplate.query(SELECT_CHILDREN_BY_DUTY_OFFICER,
                    new Object[]{dutyOfficerName}, new ChildrenCutRowMapper());
        } catch (DataAccessException e) {
            LOGGER.error("Error in fetching children with duty officer named " + dutyOfficerName, e);
            return null;
        } catch (Exception e) {
            LOGGER.error("Error in fetching children with duty officer named " + dutyOfficerName, e);
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
    public List<Parent> getChildParents(BigInteger childId) {
        try {
            return generalTemplate.query(SELECT_CHILD_PARENTS, new Object[]{childId.longValue()}, new ParentRowMapper());
        } catch (DataAccessException e) {
            LOGGER.error("Error in fetching child's parents. id:" + childId, e);
            return null;
        } catch (Exception e) {
            LOGGER.error("Error in fetching child's parents. id:" + childId, e);
            return null;
        }
    }


    @Override
    public boolean addParentToChild(BigInteger childId, Parent parent) {
        try {
            if (childId != null && parent != null) {
                generalTemplate.update(INSERT_CHILD_PARENT,
                        childId.longValue(), parent.getParentType().getDbId().longValue(), parent.getParentName(), parent.getParentInfo(), parent.getParentBirthDate());
                return true;
            } else {
                LOGGER.error("Error in adding parent to child because of nulls. CHILD:" + childId + " PARENT:" + parent);
                return false;
            }
        } catch (DataAccessException e) {
            LOGGER.error("Error in adding parent to child. CHILD: " + childId + " PARENT:" + parent, e);
            return false;
        } catch (Exception e) {
            LOGGER.error("Error in adding parent to child. CHILD: " + childId + " PARENT:" + parent, e);
            return false;
        }
    }


    @Override
    public boolean addFileToChild(BigInteger childId, MultipartFile file) {
        try {
            if (childId != null && file != null) {
                generalTemplate.update(INSERT_CHILD_FILE, childId.longValue(), file.getOriginalFilename(), file.getBytes());
                return true;
            } else {
                LOGGER.error("Error in adding file to child because of nulls. CHILD:" + childId + " FILE:" + file.getOriginalFilename() + " size:" + file.getSize());
                return false;
            }
        } catch (DataAccessException e) {
            LOGGER.error("Error in adding file to child.CHILD:" + childId + " FILE:" + file.getOriginalFilename() + " size:" + file.getSize(), e);
            return false;
        } catch (Exception e) {
            LOGGER.error("Error in adding file to child. CHILD:" + childId + " FILE:" + file.getOriginalFilename() + " size:" + file.getSize(), e);
            return false;
        }
    }

    @Override
    public boolean deleteChild(BigInteger childId) {
        //!TODO delete parents
        //!TODO delete files
        //!TODO delete clarified
        //!TODO delete child
        return false;
    }

    @Override
    public boolean updateChildsPhoto(BigInteger childId, MultipartFile photo) {
        try {
            if (childId != null && photo != null) {
                generalTemplate.update(UPDATE_CHILDS_PHOTO, photo.getBytes(), childId.longValue());
                LOGGER.info("photo updated to " + photo.getOriginalFilename());
                return true;
            } else {
                LOGGER.error("Error in adding file to child because of nulls. CHILD:" + childId + " FILE:" + photo.getOriginalFilename() + " bytes size :" + photo.getSize());
                return false;
            }
        } catch (DataAccessException e) {
            LOGGER.error("Error in adding file to child id:" + childId + " FILE:" + photo.getOriginalFilename() + " bytes size :" + photo.getSize(), e);
            return false;
        } catch (Exception e) {
            LOGGER.error("Error in adding file to child id:" + childId + " FILE:" + photo.getOriginalFilename() + " bytes size :" + photo.getSize(), e);
            return false;
        }
    }

    @Override
    public boolean deleteParent(BigInteger parentId) {
        //!TODO delete one parent by id
        return false;
    }

    @Override
    public boolean deleteFile(BigInteger fileId) {
        //!TODO delete one file by id
        return false;
    }

    @Override
    public boolean updateParentInfo(BigInteger parentId, String info) {
        //!TODO update parent's Info
        return false;
    }

    @Override
    public byte[] getChildPhotoById(BigInteger id) {
        try {
            if (id != null) {
                return generalTemplate.queryForObject(SELECT_CHILDS_PHOTO, byte[].class, id.longValue());
            } else {
                LOGGER.error("Error in getting photo because of nulls. CHILD:" + id);
                return null;
            }
        } catch (DataAccessException e) {
            LOGGER.error("Error in getting photo of child id:" + id, e);
            return null;
        } catch (Exception e) {
            LOGGER.error("Error in getting photo of child id:" + id, e);
            return null;
        }

    }

    @Override
    public List<ChildFile> getChildFileList(BigInteger id) {
        try {
            if (id != null) {
                return generalTemplate.query(SELECT_LIST_OF_CHILD_FILES, new Object[]{id.longValue()}, new ChildFileRowMapper());
            } else {
                LOGGER.error("Error in getting files because of nulls. CHILD:" + id);
                return null;
            }
        } catch (DataAccessException e) {
            LOGGER.error("Error in getting files of child id:" + id, e);
            return null;
        } catch (Exception e) {
            LOGGER.error("Error in getting files of child id:" + id, e);
            return null;
        }


    }

    @Override
    public byte[] getChildFileById(BigInteger id) {
        try {
            if (id != null) {
                return generalTemplate.queryForObject(SELECT_CHILDS_FILE, byte[].class, id.longValue());
            } else {
                LOGGER.error("Error in getting file to because of nulls. CHILD:" + id);
                return null;
            }
        } catch (DataAccessException e) {
            LOGGER.error("Error in getting file of child id:" + id, e);
            return null;
        } catch (Exception e) {
            LOGGER.error("Error in getting file of child id:" + id, e);
            return null;
        }
    }

    @Override
    public Child updateChild(BigInteger childId, ChildCreationRequest creationRequest) {
        //!TODO full update on child
        return null;
    }


    private static final String UPDATE_CHILDS_PHOTO = "UPDATE children_basic_info SET photo = ? WHERE child_id = ?";

    private static final String UPDATE_PARENTS_INFO = "UPDATE parents SET photo = ? WHERE child_id = ?";

    private static final String SELECT_CHILDS_PHOTO = "SELECT photo FROM children_basic_info WHERE child_id = ?";

    private static final String SELECT_CHILDS_FILE = "SELECT file_blob FROM children_files WHERE file_id = ?";
    
    private static final String SELECT_LIST_OF_CHILD_FILES = "SELECT file_id, file_name, child_id FROM children_files WHERE child_id = ?";

    private static final String SELECT_ALL_CHILDREN = "SELECT" +
            " basic.child_id child_id," +
            " basic.personal_record_code code," +
            " basic.entrance_date entrance_date," +
            " basic.retire_date retire_date," +
            " clarified.first_name cfirstn," +
            " clarified.last_name clastn," +
            " clarified.middle_name cmiddlen," +
            " clarified.birth_date cbirth," +
            " clarified.district_id district," +
            " clarified.birth_place birth_place" +
            " FROM children_basic_info basic" +
            " JOIN children_clarified_info clarified ON basic.child_id = clarified.child_id";

    private static final String SELECT_CUT_CHILD_BY_ID = SELECT_ALL_CHILDREN +
            " WHERE basic.child_id = ?";
    private static final String SELECT_FULL_CHILD_BY_ID = "SELECT" +
            " basic.child_id child_id," +
            " basic.first_name bfirstn," +
            " basic.last_name blastn," +
            " basic.middle_name bmiddlen," +
            " basic.birth_date bbirth," +
            " basic.personal_record_code code," +
            " basic.entrance_date entrance_date," +
            " basic.retire_date retire_date," +
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

    private static final String SELECT_CHILDREN_BY_FULL_NAME = SELECT_ALL_CHILDREN +
            " WHERE (clarified.last_name || ' ' || clarified.first_name) = ?";

    private static final String SELECT_CHILDREN_BY_LAST_NAME = SELECT_ALL_CHILDREN +
            " WHERE clarified.last_name = ?";

    private static final String SELECT_CHILDREN_BY_BIRTH_DATE = SELECT_ALL_CHILDREN +
            " WHERE clarified.birth_date = ?";

    private static final String SELECT_CHILDREN_BY_BIRTH_YEAR = SELECT_ALL_CHILDREN +
            " WHERE EXTRACT(YEAR FROM clarified.birth_date) = ?";

    private static final String SELECT_CHILDREN_BY_DISTRICT = SELECT_ALL_CHILDREN +
            " WHERE clarified.district_id = ?";

    private static final String SELECT_CHILD_BY_PERSONAL_RECORD_CODE = SELECT_ALL_CHILDREN +
            " WHERE basic.personal_record_code = ?";

    private static final String SELECT_CHILDREN_ENTERED_IN_RANGE_OF_DATES = SELECT_ALL_CHILDREN +
            " WHERE basic.entrance_date BETWEEN ? AND ?";

    private static final String SELECT_CHILDREN_BY_PARENT_NAME = SELECT_ALL_CHILDREN +
            " WHERE  basic.child_id = (SELECT child_id FROM parents WHERE parent_name = ?)";

    private static final String SELECT_CHILDREN_BY_DUTY_OFFICER = SELECT_ALL_CHILDREN +
            " WHERE clarified.duty_officer = ?";

    private static final String SELECT_CHILD_PARENTS = "SELECT " +
            " parent_id, child_id, parent_type_id, parent_name, parent_info, parent_birth_date" +
            " FROM parents " +
            " WHERE child_id = ?";

    private static final String INSERT_CHILD_DETENTION = "INSERT INTO " +
            " detentions(detention_by, detention_when, detention_where, detention_why) " +
            " VALUES(?,?,?,?)";

    private static final String INSERT_CHILD_PARENT = "INSERT INTO " +
            " parents (child_id, parent_type_id, parent_name, parent_info, parent_birth_date) " +
            " VALUES (?, ?, ?, ?, ?)";

    private static final String INSERT_CHILD_FILE = "INSERT INTO " +
            " children_files (child_id, file_name, file_blob) " +
            " VALUES (?, ?, ?)";


    private static final String INSERT_BASIC_CHILD = "INSERT INTO" +
            " children_basic_info(first_name, last_name, middle_name, birth_date, personal_record_code, entrance_date, retire_date)" +
            " VALUES (?, ?, ?, ?, ?, ?, ?)";

    private static final String INSERT_CLARIFIED_CHILD = "INSERT INTO children_clarified_info(" +
            " child_id, first_name, last_name, middle_name, birth_date, address, birth_place, occupation, comes_from_city, " +
            " comes_from_date, duty_officer, district_id, judged_or_detained_info, child_notes,  detention_info_id)" +
            " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?, ?, ?, ?, ?)";

    private static final String SELECT_BASIC_CHILD_ID_BY_PERSONAL_RECORD_CODE = "SELECT child_id FROM children_basic_info " +
            " WHERE personal_record_code = ?";

    private static final String SELECT_DETENTION_ID = "SELECT detention_id" +
            " FROM detentions" +
            " WHERE detention_when = ?" +
            " AND  detention_why = ?" +
            " AND detention_id > (SELECT MAX(detention_id)-1 FROM detentions)";


    private class ChildFileRowMapper implements RowMapper<ChildFile> {

        @Override
        public ChildFile mapRow(ResultSet rs, int i) throws SQLException {

            BigInteger fileId = rs.getBigDecimal(FileColumnName.FILE_ID.toString()).toBigInteger();
            BigInteger childId = rs.getBigDecimal(FileColumnName.CHILD_ID.toString()).toBigInteger();
            String fileName = rs.getString(FileColumnName.FILE_NAME.toString());

            ChildFile childFile = new ChildFileImpl.ChildFileBuilder(fileId, fileName, childId).buildChildFile();
            return childFile;
        }
    }

    private class ChildrenFullRowMapper implements RowMapper<Child> {
        public Child mapRow(ResultSet rs, int i) throws SQLException {
            BigInteger childId = rs.getBigDecimal(ChildColumnName.CHILD_ID.toString()).toBigInteger();
            String personalRecordCode = rs.getString(ChildColumnName.PERSONA_RECORD_CODE.toString());
            Date entranceDate = rs.getDate(ChildColumnName.ENTRANCE_DATE.toString());
            Date retireDate = rs.getDate(ChildColumnName.RETIRE_DATE.toString());
            String clarifiedFirstName = rs.getString(ChildColumnName.CLARIFIED_FIRST_NAME.toString());
            String clarifiedLastName = rs.getString(ChildColumnName.CLARIFIED_LAST_NAME.toString());
            String clarifiedMiddleName = rs.getString(ChildColumnName.CLARIFIED_MIDDLE_NAME.toString());
            Date clarifiedBirthDate = rs.getDate(ChildColumnName.CLARIFIED_BIRTH_DATE.toString());
            BigInteger districtId = rs.getBigDecimal(ChildColumnName.DISTRICT.toString()).toBigInteger();
            String birthPlace = rs.getString(ChildColumnName.BIRTH_PLACE.toString());
            String basicFirstName = rs.getString(ChildColumnName.BASIC_FIRST_NAME.toString());
            String basicLastName = rs.getString(ChildColumnName.BASIC_LAST_NAME.toString());
            String basicMiddleName = rs.getString(ChildColumnName.BASIC_MIDDLE_NAME.toString());
            Date basicBirthDate = rs.getDate(ChildColumnName.BASIC_BIRTH_DATE.toString());
            String address = rs.getString(ChildColumnName.ADDRESS.toString());
            String occupation = rs.getString(ChildColumnName.OCCUPATION.toString());
            String comeFromCity = rs.getString(ChildColumnName.COME_FROM_CITY.toString());
            Date comeFromDate = rs.getDate(ChildColumnName.COME_FROM_DATE.toString());
            String dutyOfficer = rs.getString(ChildColumnName.DISTRICT.toString());
            String judgedInfo = rs.getString(ChildColumnName.JUDGED_INFO.toString());
            String notes = rs.getString(ChildColumnName.NOTES.toString());
            String detainedBy = rs.getString(ChildColumnName.DETAINED_BY_WHO.toString());
            Date detainedWhen = rs.getDate(ChildColumnName.DETAINED_WHEN_DATE.toString());
            String detainedWhy = rs.getString(ChildColumnName.DETAINED_WHY_REASON.toString());
            String detainedWhere = rs.getString(ChildColumnName.DETAINED_WHERE_PLACE.toString());

            Districts district = Districts.getDistrictById(districtId);


            Detention detention = new DetentionImpl(detainedWhere, detainedWhen, detainedBy, detainedWhy);

            ClarifiedChild clarifiedChildFull = new ClarifiedChildImpl.ClarifiedChildBuilder(childId, clarifiedFirstName, clarifiedLastName, clarifiedMiddleName, clarifiedBirthDate)
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
                    .buildClarifiedChild(clarifiedChildFull)
                    .buildRetiredDate(retireDate)
                    .buildChildId(childId)
                    .buildChild();
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

    private enum FileColumnName {
        FILE_ID("file_id"),
        CHILD_ID("child_id"),
        FILE_NAME("file_name"),
        FILE_BLOB("file_blob");

        final private String colName;

        FileColumnName(String colName) {
            this.colName = colName;
        }

        @Override
        public String toString() {
            return colName;
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
