package iq.ven.workflow.models.impl;

import iq.ven.workflow.models.*;

import java.io.File;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;

public class ChildImpl implements Child {

    private BigInteger childId;
    private ClarifiedChild clarifiedInfo;
    private String firstName;
    private String lastName;
    private String middleName;
    private Date birthDate;
    private Districts district;
    private List<Parent> parents;
    private List<Detention> detentionList;
    private String personalRecordCode;
    private Date entranceDate;
    private List<File> childFiles;

    private ChildImpl(ChildBasicBuilder builder) {
        this.childId = builder.childId;
        this.clarifiedInfo = builder.clarifiedInfo;
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.middleName = builder.middleName;
        this.birthDate = builder.birthDate;
        this.district = builder.district;
        this.parents = builder.parents;
        this.detentionList = builder.detentionList;
        this.personalRecordCode = builder.personalRecordCode;
        this.entranceDate = builder.entranceDate;
        this.childFiles = builder.childFiles;
    }

    public BigInteger getChildId() {
        return childId;
    }

    public ClarifiedChild getClarifiedInfo() {
        return clarifiedInfo;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getFullName() {
        return lastName + " " + firstName + " " + middleName;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public Districts getDistrict() {
        return district;
    }

    public List<Parent> getParents() {
        return parents;
    }

    public List<Detention> getDetentionList() {
        return detentionList;
    }

    public String getPersonalRecordCode() {
        return personalRecordCode;
    }

    public Date getEntranceDate() {
        return entranceDate;
    }

    public List<File> getChildFiles() {
        return childFiles;
    }


    public static class ChildBasicBuilder {
        private BigInteger childId;
        private ClarifiedChild clarifiedInfo;
        private String firstName;
        private String lastName;
        private String middleName;
        private Date birthDate;
        private Districts district;
        private List<Parent> parents;
        private List<Detention> detentionList;
        private String personalRecordCode;
        private Date entranceDate;
        private List<File> childFiles;

        public ChildBasicBuilder(BigInteger childId, String firstName, String lastName, String middleName,
                                 Date birthDate, String personalRecordCode, Date entranceDate) {
            this.childId = childId;

            this.firstName = firstName;
            this.lastName = lastName;
            this.middleName = middleName;
            this.birthDate = birthDate;
            this.personalRecordCode = personalRecordCode;
            this.entranceDate = entranceDate;
        }

        public ChildBasicBuilder setClarifiedChild(ClarifiedChild clarifiedInfo) {
            this.clarifiedInfo = clarifiedInfo;
            return this;
        }

        public ChildBasicBuilder setParents(List<Parent> parents) {
            this.parents = parents;
            return this;
        }

        public ChildBasicBuilder setDetentionList(List<Detention> detentionList) {
            this.detentionList = detentionList;
            return this;
        }

        public ChildBasicBuilder setDistrict(Districts district) {
            this.district = district;
            return this;
        }

        public ChildBasicBuilder setFiles(List<File> childFiles) {
            this.childFiles = childFiles;
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
                ", district=" + district +
                ", parents=" + parents +
                ", detentionList=" + detentionList +
                ", personalRecordCode='" + personalRecordCode + '\'' +
                ", entranceDate=" + entranceDate +
                ", childFiles=" + childFiles +
                '}';
    }
}
