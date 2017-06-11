package iq.ven.workflow.services;

import iq.ven.workflow.models.*;
import iq.ven.workflow.models.impl.ChildImpl;
import iq.ven.workflow.models.impl.ClarifiedChildImpl;
import iq.ven.workflow.models.impl.DetentionImpl;
import iq.ven.workflow.models.impl.ParentImpl;
import iq.ven.workflow.models.requests.ChildCreationRequest;
import iq.ven.workflow.models.requests.ParentRequest;

import java.math.BigInteger;

public class ObjectFromRequestBuilder {

    public static Child buildObjectFromRequest(ChildCreationRequest childRequest) {
        Detention detention = new DetentionImpl(childRequest.getDetentionAddress(),
                childRequest.getDetentionDate(), childRequest.getDetainedBy(),
                childRequest.getDetainedFor());

        ClarifiedChild clarifiedChildFull = new ClarifiedChildImpl.ClarifiedChildBuilder(
                null, childRequest.getClarifiedFirstName(), childRequest.getClarifiedLastName(),
                childRequest.getClarifiedMiddleName(), childRequest.getClarifiedDateOfBirth())
                .buildAddress(childRequest.getAddress())
                .buildBirthPlace(childRequest.getBirthPlace())
                .buildDetention(detention)
                .buildDistrict(Districts.getDistrictById(childRequest.getDistrict()))
                .buildOccupation(childRequest.getOccupation())
                .buildNotes(childRequest.getNotes())
                .buildDutyOfficer(childRequest.getDutyOfficer())
                .buildFromCame(childRequest.getFromCame())
                .buildWhenCame(childRequest.getWhenCame())
                .buildJudgedOrDetainedInfo(childRequest.getJudgedOrDetainedInfo())
                .buildClarifiedChildId(null)
                .buildClarifiedChild();
        Child childFull = new ChildImpl.ChildBasicBuilder(childRequest.getBasicFirstName(),
                childRequest.getBasicLastName(), childRequest.getBasicMiddleName(), childRequest.getBasicDateOfBirth(),
                childRequest.getPersonalRecordCode(), childRequest.getEnteredDate())
                .buildPhoto(childRequest.getPhoto())
                .buildClarifiedChild(clarifiedChildFull)
                .buildRetiredDate(childRequest.getRetireDate())
                .buildChildId(null)
                .buildChild();
        return childFull;
    }


    public static Parent buildObjectFromRequest(BigInteger childId, ParentRequest parentRequest) {
        Parent parent = new ParentImpl.ParentBuilder(childId, ParentTypes.getParentTypeById(parentRequest.getParentTypeId()),
                parentRequest.getParentName())
                .buildParentBirthDate(parentRequest.getParentDateOfBirth())
                .buildParentInfo(parentRequest.getParentInfo())
                .buildParent();
        return parent;
    }
}
