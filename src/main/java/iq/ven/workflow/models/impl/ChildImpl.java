package iq.ven.workflow.models.impl;

import iq.ven.workflow.models.Child;
import iq.ven.workflow.models.Districts;
import iq.ven.workflow.models.Parent;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

public class ChildImpl implements Child {

    private BigInteger childId;
    private BigInteger clarifiedInfoId;
    private String firstName;
    private String lastName;
    private String middleName;
    private Date birthDate;
    private Districts district;
    private List<Parent> parents;
    private String personalRecordCode;
    private Date entranceDate;

    private ChildImpl(ChildBasicBuilder builder) {
        this.childId = builder.childId;
        this.clarifiedInfoId = builder.clarifiedInfoId;
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.middleName = builder.middleName;
        this.birthDate = builder.birthDate;
        this.district = builder.district;
        this.parents = builder.parents;
        this.personalRecordCode = builder.personalRecordCode;
        this.entranceDate = builder.entranceDate;
    }

    public BigInteger getChildId() {
        return childId;
    }

    public BigInteger getClarifiedInfoId() {
        return clarifiedInfoId;
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

    public String getPersonalRecordCode() {
        return personalRecordCode;
    }

    public Date getEntranceDate() {
        return entranceDate;
    }


    public static class ChildBasicBuilder {
        private BigInteger childId;
        private BigInteger clarifiedInfoId;
        private String firstName;
        private String lastName;
        private String middleName;
        private Date birthDate;
        private Districts district;
        private List<Parent> parents;
        private String personalRecordCode;
        private Date entranceDate;

        public ChildBasicBuilder(BigInteger childId, BigInteger clarifiedInfoId, String firstName, String lastName, String middleName,
                                 Date birthDate, String personalRecordCode, Date entranceDate) {
            this.childId = childId;
            this.clarifiedInfoId = clarifiedInfoId;
            this.firstName = firstName;
            this.lastName = lastName;
            this.middleName = middleName;
            this.birthDate = birthDate;
            this.personalRecordCode = personalRecordCode;
            this.entranceDate = entranceDate;
        }

        public ChildBasicBuilder setParents(List<Parent> parents) {
            this.parents = parents;
            return this;
        }

        public ChildBasicBuilder setDistrict(Districts district) {
            this.district = district;
            return this;
        }

        public ChildImpl buildChild() {
            return new ChildImpl(this);
        }
    }
}
