package iq.ven.workflow.models.impl;

import iq.ven.workflow.models.Parent;
import iq.ven.workflow.models.ParentTypes;

import java.math.BigInteger;
import java.util.Date;

public class ParentImpl implements Parent {

    private BigInteger parentId;
    private BigInteger childId;
    private ParentTypes parentType;
    private String parentName;
    private String parentInfo;
    private Date parentBirthDate;


    public ParentImpl() {
    }

    private ParentImpl(ParentBuilder builder) {
        this.parentId = builder.parentId;
        this.childId = builder.childId;
        this.parentType = builder.parentType;
        this.parentName = builder.parentName;
        this.parentInfo = builder.parentInfo;
        this.parentBirthDate = builder.parentBirthDate;
    }

    @Override
    public BigInteger getParentId() {
        return parentId;
    }

    @Override
    public BigInteger getChildId() {
        return childId;
    }

    @Override
    public ParentTypes getParentType() {
        return parentType;
    }

    @Override
    public String getParentName() {
        return parentName;
    }

    @Override
    public String getParentInfo() {
        return parentInfo;
    }

    @Override
    public Date getParentBirthDate() {
        return parentBirthDate;
    }

    public void setParentId(BigInteger parentId) {
        this.parentId = parentId;
    }

    public void setChildId(BigInteger childId) {
        this.childId = childId;
    }

    public void setParentType(ParentTypes parentType) {
        this.parentType = parentType;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public void setParentInfo(String parentInfo) {
        this.parentInfo = parentInfo;
    }

    public void setParentBirthDate(Date parentBirthDate) {
        this.parentBirthDate = parentBirthDate;
    }

    public static class ParentBuilder {
        private BigInteger parentId;
        private BigInteger childId;
        private ParentTypes parentType;
        private String parentName;
        private String parentInfo;
        private Date parentBirthDate;


        public ParentBuilder(BigInteger childId, ParentTypes parentType, String parentName) {
            this.childId = childId;
            this.parentType = parentType;
            this.parentName = parentName;
        }

        public ParentBuilder buildParentId(BigInteger parentId) {
            this.parentId = parentId;
            return this;
        }

        public ParentBuilder buildParentInfo(String parentInfo) {
            this.parentInfo = parentInfo;
            return this;
        }

        public ParentBuilder buildParentBirthDate(Date parentBirthDate) {
            this.parentBirthDate = parentBirthDate;
            return this;
        }


        public ParentImpl buildParent() {
            return new ParentImpl(this);
        }
    }

    @Override
    public String toString() {
        return "ParentImpl{" +
                "parentId=" + parentId +
                ", childId=" + childId +
                ", parentType=" + parentType +
                ", parentName='" + parentName + '\'' +
                ", parentInfo='" + parentInfo + '\'' +
                ", parentBirthDate=" + parentBirthDate +
                '}';
    }
}
