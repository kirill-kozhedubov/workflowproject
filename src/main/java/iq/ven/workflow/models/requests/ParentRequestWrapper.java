package iq.ven.workflow.models.requests;

import java.math.BigInteger;
import java.util.List;

public class ParentRequestWrapper {

    private List<ParentRequest> parentRequestList;
    private BigInteger childId;

    public List<ParentRequest> getParentRequestList() {
        return parentRequestList;
    }

    public void setParentRequestList(List<ParentRequest> parentRequestList) {
        this.parentRequestList = parentRequestList;
    }

    public BigInteger getChildId() {
        return childId;
    }

    public void setChildId(BigInteger childId) {
        this.childId = childId;
    }

    @Override
    public String toString() {
        return "ParentRequestWrapper{" +
                "parentRequestList=" + parentRequestList +
                ", childId=" + childId +
                '}';
    }
}
