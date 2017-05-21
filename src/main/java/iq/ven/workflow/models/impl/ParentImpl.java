package iq.ven.workflow.models.impl;

import iq.ven.workflow.models.Parent;
import iq.ven.workflow.models.ParentTypes;

import java.math.BigInteger;

public class ParentImpl implements Parent {

    private BigInteger parentId;
    private BigInteger childId;
    private ParentTypes parentType;
    private String parentName;
    private String parentInfo;

    private ParentImpl(ParentBuilder builder) {
        this.parentId = builder.parentId;
        this.childId = builder.childId;
        this.parentType = builder.parentType;
        this.parentName = builder.parentName;
        this.parentInfo = builder.parentInfo;
    }

    public BigInteger getParentId() {
        return parentId;
    }

    public BigInteger getChildId() {
        return childId;
    }

    public ParentTypes getParentType() {
        return parentType;
    }

    public String getParentName() {
        return parentName;
    }

    public String getParentInfo() {
        return parentInfo;
    }


    public static class ParentBuilder {
        private BigInteger parentId;
        private BigInteger childId;
        private ParentTypes parentType;
        private String parentName;
        private String parentInfo;

        public ParentBuilder(BigInteger parentId, BigInteger childId, ParentTypes parentType, String parentName, String parentInfo) {
            this.parentId = parentId;
            this.childId = childId;
            this.parentType = parentType;
            this.parentName = parentName;
        }

        public ParentBuilder setParentInfo(String parentInfo) {
            this.parentInfo = parentInfo;
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
                '}';
    }
}
