package iq.ven.workflow.models.impl;

import iq.ven.workflow.models.ClarifiedChild;

import java.math.BigInteger;
import java.util.Date;

public class ClarifiedChildImpl implements ClarifiedChild {

    private boolean isBirthCertificatePresent;
    private BigInteger clarifiedInfoId;
    private BigInteger childId;
    private String firstName;
    private String lastName;
    private String middleName;
    private Date birthDate;
    private String address;
    private String birthPlace;
    private String occupation;

    private ClarifiedChildImpl(ClarifiedChildBuilder builder) {
        this.isBirthCertificatePresent = builder.isBirthCertificatePresent;
        this.clarifiedInfoId = builder.clarifiedInfoId;
        this.childId = builder.childId;
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.middleName = builder.middleName;
        this.birthDate = builder.birthDate;
        this.address = builder.address;
        this.birthPlace = builder.birthPlace;
        this.occupation = builder.occupation;
    }

    public boolean getBirthCertificatePresenceInfo() {
        return isBirthCertificatePresent;
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

    public String getAddress() {
        return address;
    }

    public String getBirthPlace() {
        return birthPlace;
    }

    public String getOccupation() {
        return occupation;
    }


    public static class ClarifiedChildBuilder {

        private boolean isBirthCertificatePresent;
        private BigInteger clarifiedInfoId;
        private BigInteger childId;
        private String firstName;
        private String lastName;
        private String middleName;
        private Date birthDate;
        private String address;
        private String birthPlace;
        private String occupation;

        public ClarifiedChildBuilder(boolean isBirthCertificatePresent, BigInteger clarifiedInfoId, BigInteger childId, String firstName,
                                     String lastName, String middleName, Date birthDate, String address, String birthPlace, String occupation) {

            this.isBirthCertificatePresent = isBirthCertificatePresent;
            this.clarifiedInfoId = clarifiedInfoId;
            this.childId = childId;
            this.firstName = firstName;
            this.lastName = lastName;
            this.middleName = middleName;
            this.birthDate = birthDate;
            this.address = address;
            this.birthPlace = birthPlace;
            this.occupation = occupation;
        }

        public ClarifiedChildImpl buildClarifiedChild() {
            return new ClarifiedChildImpl(this);
        }

    }
}
