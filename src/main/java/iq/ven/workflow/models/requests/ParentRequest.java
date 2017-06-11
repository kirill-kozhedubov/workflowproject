package iq.ven.workflow.models.requests;

import java.math.BigInteger;
import java.util.Date;

public class ParentRequest {

    private BigInteger parentTypeId;
    private String parentName;
    private Date parentDateOfBirth;
    private String parentInfo;

    public ParentRequest() {
    }

    public BigInteger getParentTypeId() {
        return parentTypeId;
    }

    public void setParentTypeId(BigInteger parentTypeId) {
        this.parentTypeId = parentTypeId;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public Date getParentDateOfBirth() {
        return parentDateOfBirth;
    }

    public void setParentDateOfBirth(Date parentDateOfBirth) {
        this.parentDateOfBirth = parentDateOfBirth;
    }

    public String getParentInfo() {
        return parentInfo;
    }

    public void setParentInfo(String parentInfo) {
        this.parentInfo = parentInfo;
    }

    @Override
    public String toString() {
        return "ParentRequest{" +
                "parentTypeId=" + parentTypeId +
                ", parentName='" + parentName + '\'' +
                ", parentDateOfBirth=" + parentDateOfBirth +
                ", parentInfo='" + parentInfo + '\'' +
                '}';
    }
}
