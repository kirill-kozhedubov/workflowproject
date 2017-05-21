package iq.ven.workflow.models.impl;

import iq.ven.workflow.models.Detention;

import java.math.BigInteger;
import java.util.Date;

public class DetentionImpl implements Detention {

    private BigInteger detentionId;
    private BigInteger getChildId;
    private String detentionAddress;
    private Date detentionDate;
    private String detentionDoneByWho;

    private DetentionImpl(DetentionBuilder builder) {
        this.detentionId = builder.detentionId;
        this.getChildId = builder.getChildId;
        this.detentionAddress = builder.detentionAddress;
        this.detentionDate = builder.detentionDate;
        this.detentionDoneByWho = builder.detentionDoneByWho;
    }

    public BigInteger getDetentionId() {
        return detentionId;
    }

    public BigInteger getChildId() {
        return getChildId;
    }

    public String getDetentionAddress() {
        return detentionAddress;
    }

    public Date getDetentionDate() {
        return detentionDate;
    }

    public String getDetentionDoneByWho() {
        return detentionDoneByWho;
    }

    public static class DetentionBuilder {
        private BigInteger detentionId;
        private BigInteger getChildId;
        private String detentionAddress;
        private Date detentionDate;
        private String detentionDoneByWho;

        public DetentionBuilder(BigInteger detentionId, BigInteger getChildId, String detentionAddress, Date detentionDate, String detentionDoneByWho) {
            this.detentionId = detentionId;
            this.getChildId = getChildId;
            this.detentionAddress = detentionAddress;
            this.detentionDate = detentionDate;
            this.detentionDoneByWho = detentionDoneByWho;
        }

        public DetentionImpl buildDetention() {
            return new DetentionImpl(this);
        }
    }

    @Override
    public String toString() {
        return "DetentionImpl{" +
                "detentionId=" + detentionId +
                ", getChildId=" + getChildId +
                ", detentionAddress='" + detentionAddress + '\'' +
                ", detentionDate=" + detentionDate +
                ", detentionDoneByWho='" + detentionDoneByWho + '\'' +
                '}';
    }
}