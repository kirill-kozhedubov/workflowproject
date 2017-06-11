package iq.ven.workflow.models.requests;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Date;

public class ChildCreationRequest {

    //basic section
    private String basicFirstName;
    private String basicLastName;
    private String basicMiddleName;
    private String personalRecordCode;
    private Date basicDateOfBirth;
    private Date enteredDate;
    private Date retireDate;

    //photo
    private byte[] photo;

    //clarified section
    private String clarifiedFirstName;
    private String clarifiedLastName;
    private String clarifiedMiddleName;
    private Date clarifiedDateOfBirth;
    private BigInteger district;

    private String address;
    private String birthPlace;
    private String occupation;
    private String fromCame;
    private Date whenCame;

    //detention
    private String detentionAddress;
    private Date detentionDate;
    private String detainedBy;
    private String detainedFor;

    private String judgedOrDetainedInfo;
    private String notes;
    private String dutyOfficer;

    public ChildCreationRequest() {
    }

    public String getBasicFirstName() {
        return basicFirstName;
    }

    public void setBasicFirstName(String basicFirstName) {
        this.basicFirstName = basicFirstName;
    }

    public String getBasicLastName() {
        return basicLastName;
    }

    public void setBasicLastName(String basicLastName) {
        this.basicLastName = basicLastName;
    }

    public String getBasicMiddleName() {
        return basicMiddleName;
    }

    public void setBasicMiddleName(String basicMiddleName) {
        this.basicMiddleName = basicMiddleName;
    }

    public String getPersonalRecordCode() {
        return personalRecordCode;
    }

    public void setPersonalRecordCode(String personalRecordCode) {
        this.personalRecordCode = personalRecordCode;
    }

    public Date getBasicDateOfBirth() {
        return basicDateOfBirth;
    }

    public void setBasicDateOfBirth(Date basicDateOfBirth) {
        this.basicDateOfBirth = basicDateOfBirth;
    }

    public Date getEnteredDate() {
        return enteredDate;
    }

    public void setEnteredDate(Date enteredDate) {
        this.enteredDate = enteredDate;
    }

    public Date getRetireDate() {
        return retireDate;
    }

    public void setRetireDate(Date retireDate) {
        this.retireDate = retireDate;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    public String getClarifiedFirstName() {
        return clarifiedFirstName;
    }

    public void setClarifiedFirstName(String clarifiedFirstName) {
        this.clarifiedFirstName = clarifiedFirstName;
    }

    public String getClarifiedLastName() {
        return clarifiedLastName;
    }

    public void setClarifiedLastName(String clarifiedLastName) {
        this.clarifiedLastName = clarifiedLastName;
    }

    public String getClarifiedMiddleName() {
        return clarifiedMiddleName;
    }

    public void setClarifiedMiddleName(String clarifiedMiddleName) {
        this.clarifiedMiddleName = clarifiedMiddleName;
    }

    public Date getClarifiedDateOfBirth() {
        return clarifiedDateOfBirth;
    }

    public void setClarifiedDateOfBirth(Date clarifiedDateOfBirth) {
        this.clarifiedDateOfBirth = clarifiedDateOfBirth;
    }

    public BigInteger getDistrict() {
        return district;
    }

    public void setDistrict(BigInteger district) {
        this.district = district;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBirthPlace() {
        return birthPlace;
    }

    public void setBirthPlace(String birthPlace) {
        this.birthPlace = birthPlace;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getFromCame() {
        return fromCame;
    }

    public void setFromCame(String fromCame) {
        this.fromCame = fromCame;
    }

    public Date getWhenCame() {
        return whenCame;
    }

    public void setWhenCame(Date whenCame) {
        this.whenCame = whenCame;
    }

    public String getDetentionAddress() {
        return detentionAddress;
    }

    public void setDetentionAddress(String detentionAddress) {
        this.detentionAddress = detentionAddress;
    }

    public Date getDetentionDate() {
        return detentionDate;
    }

    public void setDetentionDate(Date detentionDate) {
        this.detentionDate = detentionDate;
    }

    public String getDetainedBy() {
        return detainedBy;
    }

    public void setDetainedBy(String detainedBy) {
        this.detainedBy = detainedBy;
    }

    public String getDetainedFor() {
        return detainedFor;
    }

    public void setDetainedFor(String detainedFor) {
        this.detainedFor = detainedFor;
    }

    public String getJudgedOrDetainedInfo() {
        return judgedOrDetainedInfo;
    }

    public void setJudgedOrDetainedInfo(String judgedOrDetainedInfo) {
        this.judgedOrDetainedInfo = judgedOrDetainedInfo;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getDutyOfficer() {
        return dutyOfficer;
    }

    public void setDutyOfficer(String dutyOfficer) {
        this.dutyOfficer = dutyOfficer;
    }

    @Override
    public String toString() {
        return "ChildCreationRequest{" +
                "basicFirstName='" + basicFirstName + '\'' +
                ", basicLastName='" + basicLastName + '\'' +
                ", basicMiddleName='" + basicMiddleName + '\'' +
                ", personalRecordCode='" + personalRecordCode + '\'' +
                ", basicDateOfBirth=" + basicDateOfBirth +
                ", enteredDate=" + enteredDate +
                ", retireDate=" + retireDate +
                ", photo=" + Arrays.toString(photo) +
                ", clarifiedFirstName='" + clarifiedFirstName + '\'' +
                ", clarifiedLastName='" + clarifiedLastName + '\'' +
                ", clarifiedMiddleName='" + clarifiedMiddleName + '\'' +
                ", clarifiedDateOfBirth=" + clarifiedDateOfBirth +
                ", district=" + district +
                ", address='" + address + '\'' +
                ", birthPlace='" + birthPlace + '\'' +
                ", occupation='" + occupation + '\'' +
                ", fromCame='" + fromCame + '\'' +
                ", whenCame=" + whenCame +
                ", detentionAddress='" + detentionAddress + '\'' +
                ", detentionDate='" + detentionDate + '\'' +
                ", detainedBy='" + detainedBy + '\'' +
                ", detainedFor='" + detainedFor + '\'' +
                ", judgedOrDetainedInfo='" + judgedOrDetainedInfo + '\'' +
                ", notes='" + notes + '\'' +
                ", dutyOfficer='" + dutyOfficer + '\'' +
                '}';
    }
}
