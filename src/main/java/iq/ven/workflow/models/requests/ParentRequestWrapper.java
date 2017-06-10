package iq.ven.workflow.models.requests;

import java.util.List;

public class ParentRequestWrapper {

    List<ParentRequest> parentRequestList;

    public List<ParentRequest> getParentRequestList() {
        return parentRequestList;
    }

    public void setParentRequestList(List<ParentRequest> parentRequestList) {
        this.parentRequestList = parentRequestList;
    }
}
