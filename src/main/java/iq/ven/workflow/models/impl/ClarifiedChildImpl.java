package iq.ven.workflow.models.impl;

import iq.ven.workflow.models.ClarifiedChild;
import iq.ven.workflow.models.Detention;
import iq.ven.workflow.models.Districts;

import java.math.BigInteger;
import java.util.Date;

public class ClarifiedChildImpl implements ClarifiedChild {

    private BigInteger clarifiedInfoId;
    private BigInteger childId;
    private String firstName;
    private String lastName;
    private String middleName;
    private Date birthDate;
    private String address;
    private String birthPlace;
    private String occupation;
    private String notes;
    private String dutyOfficer;
    private String fromCame;
    private Date whenCame;
    private Detention detention;
    private Districts district;
    private String judgedOrDetainedInfo;

    public ClarifiedChildImpl() {
    }

    private ClarifiedChildImpl(ClarifiedChildBuilder builder) {
        this.clarifiedInfoId = builder.clarifiedInfoId;
        this.childId = builder.childId;
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.middleName = builder.middleName;
        this.birthDate = builder.birthDate;
        this.address = builder.address;
        this.birthPlace = builder.birthPlace;
        this.occupation = builder.occupation;
        this.notes = builder.notes;
        this.dutyOfficer = builder.dutyOfficer;
        this.fromCame = builder.fromCame;
        this.whenCame = builder.whenCame;
        this.detention = builder.detention;
        this.district = builder.district;
        this.judgedOrDetainedInfo = builder.judgedOrDetainedInfo;

    }

    @Override
    public BigInteger getChildId() {
        return childId;
    }

    @Override
    public BigInteger getClarifiedInfoId() {
        return clarifiedInfoId;
    }

    @Override
    public String getFirstName() {
        return firstName;
    }

    @Override
    public String getLastName() {
        return lastName;
    }

    @Override
    public String getMiddleName() {
        return middleName;
    }

    @Override
    public String getFullName() {
        return lastName + " " + firstName + " " + middleName;
    }

    @Override
    public Date getBirthDate() {
        return birthDate;
    }

    @Override
    public String getAddress() {
        return address;
    }

    @Override
    public String getBirthPlace() {
        return birthPlace;
    }

    @Override
    public String getOccupation() {
        return occupation;
    }

    @Override
    public String getFromCame() {
        return fromCame;
    }

    @Override
    public Date getWhenCame() {
        return whenCame;
    }

    @Override
    public Detention getDetention() {
        return detention;
    }

    @Override
    public Districts getDistrict() {
        return district;
    }

    @Override
    public String getJudgedOrDetainedInfo() {
        return judgedOrDetainedInfo;
    }

    @Override
    public String getNotes() {
        return notes;
    }

    @Override
    public String getDutyOfficer() {
        return dutyOfficer;
    }


    public void setClarifiedInfoId(BigInteger clarifiedInfoId) {
        this.clarifiedInfoId = clarifiedInfoId;
    }

    public void setChildId(BigInteger childId) {
        this.childId = childId;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setBirthPlace(String birthPlace) {
        this.birthPlace = birthPlace;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public void setDutyOfficer(String dutyOfficer) {
        this.dutyOfficer = dutyOfficer;
    }

    public void setFromCame(String fromCame) {
        this.fromCame = fromCame;
    }

    public void setWhenCame(Date whenCame) {
        this.whenCame = whenCame;
    }

    public void setDetention(Detention detention) {
        this.detention = detention;
    }

    public void setDistrict(Districts district) {
        this.district = district;
    }

    public void setJudgedOrDetainedInfo(String judgedOrDetainedInfo) {
        this.judgedOrDetainedInfo = judgedOrDetainedInfo;
    }

    public static class ClarifiedChildBuilder {

        private BigInteger clarifiedInfoId;
        private BigInteger childId;
        private String firstName;
        private String lastName;
        private String middleName;
        private Date birthDate;
        private String address;
        private String birthPlace;
        private String occupation;
        private String notes;
        private String dutyOfficer;
        private String fromCame;
        private Date whenCame;
        private Detention detention;
        private Districts district;
        private String judgedOrDetainedInfo;

        public ClarifiedChildBuilder(BigInteger childId, String firstName,
                                     String lastName, String middleName, Date birthDate) {
            this.childId = childId;
            this.firstName = firstName;
            this.lastName = lastName;
            this.middleName = middleName;
            this.birthDate = birthDate;
        }

        public ClarifiedChildBuilder buildClarifiedChildId(BigInteger clarifiedInfoId) {
            this.clarifiedInfoId = clarifiedInfoId;
            return this;
        }


        public ClarifiedChildBuilder buildAddress(String address) {
            this.address = address;
            return this;
        }

        public ClarifiedChildBuilder buildBirthPlace(String birthPlace) {
            this.birthPlace = birthPlace;
            return this;
        }

        public ClarifiedChildBuilder buildOccupation(String occupation) {
            this.occupation = occupation;
            return this;
        }

        public ClarifiedChildBuilder buildNotes(String notes) {
            this.notes = notes;
            return this;
        }

        public ClarifiedChildBuilder buildDutyOfficer(String dutyOfficer) {
            this.dutyOfficer = dutyOfficer;
            return this;
        }

        public ClarifiedChildBuilder buildFromCame(String fromCame) {
            this.fromCame = fromCame;
            return this;
        }

        public ClarifiedChildBuilder buildWhenCame(Date whenCame) {
            this.whenCame = whenCame;
            return this;
        }

        public ClarifiedChildBuilder buildDetention(Detention detention) {
            this.detention = detention;
            return this;
        }

        public ClarifiedChildBuilder buildDistrict(Districts district) {
            this.district = district;
            return this;
        }

        public ClarifiedChildBuilder buildJudgedOrDetainedInfo(String judgedOrDetainedInfo) {
            this.judgedOrDetainedInfo = judgedOrDetainedInfo;
            return this;
        }

        public ClarifiedChildImpl buildClarifiedChild() {
            return new ClarifiedChildImpl(this);
        }

    }

    @Override
    public String toString() {
        return "ClarifiedChildImpl{" +
                "clarifiedInfoId=" + clarifiedInfoId +
                ", childId=" + childId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", birthDate=" + birthDate +
                ", address='" + address + '\'' +
                ", birthPlace='" + birthPlace + '\'' +
                ", occupation='" + occupation + '\'' +
                ", notes='" + notes + '\'' +
                ", dutyOfficer='" + dutyOfficer + '\'' +
                ", fromCame='" + fromCame + '\'' +
                ", whenCame=" + whenCame +
                ", detention=" + detention +
                ", district=" + district +
                ", judgedOrDetainedInfo='" + judgedOrDetainedInfo + '\'' +
                '}';
    }
}
