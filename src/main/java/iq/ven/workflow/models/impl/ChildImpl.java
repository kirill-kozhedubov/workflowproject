package iq.ven.workflow.models.impl;

import iq.ven.workflow.models.Child;
import iq.ven.workflow.models.ChildFile;
import iq.ven.workflow.models.ClarifiedChild;
import iq.ven.workflow.models.Parent;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class ChildImpl implements Child {

    private BigInteger childId;
    private ClarifiedChild clarifiedInfo;
    private String firstName;
    private String lastName;
    private String middleName;
    private Date birthDate;
    private List<Parent> parents;
    private String personalRecordCode;
    private Date entranceDate;
    private Date retiredDate;
    private List<ChildFile> childFiles;
    private byte[] photo;

    public ChildImpl() {
    }

    private ChildImpl(ChildBasicBuilder builder) {
        this.childId = builder.childId;
        this.clarifiedInfo = builder.clarifiedInfo;
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.middleName = builder.middleName;
        this.birthDate = builder.birthDate;
        this.parents = builder.parents;
        this.personalRecordCode = builder.personalRecordCode;
        this.entranceDate = builder.entranceDate;
        this.childFiles = builder.childFiles;
        this.retiredDate = builder.retiredDate;
        this.photo = builder.photo;
    }

    @Override
    public BigInteger getChildId() {
        return childId;
    }

    @Override
    public ClarifiedChild getClarifiedInfo() {
        return clarifiedInfo;
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
    public List<Parent> getParents() {
        return parents;
    }

    @Override
    public String getPersonalRecordCode() {
        return personalRecordCode;
    }

    @Override
    public Date getEntranceDate() {
        return entranceDate;
    }

    @Override
    public Date getRetiredDate() {
        return retiredDate;
    }

    @Override
    public List<ChildFile> getChildFiles() {
        return childFiles;
    }

    @Override
    public byte[] getPhoto() {
        return photo;
    }


    public void setChildId(BigInteger childId) {
        this.childId = childId;
    }

    public void setClarifiedInfo(ClarifiedChild clarifiedInfo) {
        this.clarifiedInfo = clarifiedInfo;
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

    public void setParents(List<Parent> parents) {
        this.parents = parents;
    }

    public void setPersonalRecordCode(String personalRecordCode) {
        this.personalRecordCode = personalRecordCode;
    }

    public void setEntranceDate(Date entranceDate) {
        this.entranceDate = entranceDate;
    }

    public void setRetiredDate(Date retiredDate) {
        this.retiredDate = retiredDate;
    }

    public void setChildFiles(List<ChildFile> childFiles) {
        this.childFiles = childFiles;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    public static class ChildBasicBuilder {
        private BigInteger childId;
        private ClarifiedChild clarifiedInfo;
        private String firstName;
        private String lastName;
        private String middleName;
        private Date birthDate;
        private List<Parent> parents;
        private String personalRecordCode;
        private Date entranceDate;
        private Date retiredDate;
        private List<ChildFile> childFiles;
        private byte[] photo;

        public ChildBasicBuilder(String firstName, String lastName, String middleName,
                                 Date birthDate, String personalRecordCode, Date entranceDate) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.middleName = middleName;
            this.birthDate = birthDate;
            this.entranceDate = entranceDate;
            this.personalRecordCode = personalRecordCode;
        }

        public ChildBasicBuilder buildClarifiedChild(ClarifiedChild clarifiedInfo) {
            this.clarifiedInfo = clarifiedInfo;
            return this;
        }

        public ChildBasicBuilder buildParents(List<Parent> parents) {
            this.parents = parents;
            return this;
        }

        public ChildBasicBuilder buildFiles(List<ChildFile> childFiles) {
            this.childFiles = childFiles;
            return this;
        }

        public ChildBasicBuilder buildPhoto(byte[] photo) {
            this.photo = photo;
            return this;
        }

        public ChildBasicBuilder buildRetiredDate(Date retiredDate) {
            this.retiredDate = retiredDate;
            return this;
        }

        public ChildBasicBuilder buildChildId(BigInteger childId) {
            this.childId = childId;
            return this;
        }


        public ChildImpl buildChild() {
            return new ChildImpl(this);
        }
    }

    @Override
    public String toString() {
        return "ChildImpl{" +
                "childId=" + childId +
                ", clarifiedInfo=" + clarifiedInfo +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", birthDate=" + birthDate +
                ", parents=" + parents +
                ", personalRecordCode='" + personalRecordCode + '\'' +
                ", entranceDate=" + entranceDate +
                ", retiredDate=" + retiredDate +
                ", childFiles=" + childFiles +
                ", photo=" + Arrays.toString(photo) +
                '}';
    }
}
