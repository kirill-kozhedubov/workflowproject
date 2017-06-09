package iq.ven.workflow.models.impl;

import iq.ven.workflow.models.Detention;

import java.math.BigInteger;
import java.util.Date;

public class DetentionImpl implements Detention {

    private BigInteger detentionId;
    private String detentionAddress;
    private Date detentionDate;
    private String detainedBy;
    private String gotDetainedFor;

    public DetentionImpl(BigInteger detentionId, String detentionAddress, Date detentionDate, String detainedBy, String gotDetainedFor) {
        this.detentionId = detentionId;
        this.detentionAddress = detentionAddress;
        this.detentionDate = detentionDate;
        this.detainedBy = detainedBy;
        this.gotDetainedFor = gotDetainedFor;
    }

    @Override
    public BigInteger getDetentionId() {
        return null;
    }

    @Override
    public String getDetentionAddress() {
        return null;
    }

    @Override
    public Date getDetentionDate() {
        return null;
    }

    @Override
    public String getDetainedBy() {
        return null;
    }

    @Override
    public String getGotDetainedFor() {
        return null;
    }

    public void setDetentionId(BigInteger detentionId) {
        this.detentionId = detentionId;
    }

    public void setDetentionAddress(String detentionAddress) {
        this.detentionAddress = detentionAddress;
    }

    public void setDetentionDate(Date detentionDate) {
        this.detentionDate = detentionDate;
    }

    public void setDetainedBy(String detainedBy) {
        this.detainedBy = detainedBy;
    }

    public void setGotDetainedFor(String gotDetainedFor) {
        this.gotDetainedFor = gotDetainedFor;
    }


    @Override
    public String toString() {
        return "DetentionImpl{" +
                "detentionId=" + detentionId +
                ", detentionAddress='" + detentionAddress + '\'' +
                ", detentionDate=" + detentionDate +
                ", detainedBy='" + detainedBy + '\'' +
                ", gotDetainedFor='" + gotDetainedFor + '\'' +
                '}';
    }

}