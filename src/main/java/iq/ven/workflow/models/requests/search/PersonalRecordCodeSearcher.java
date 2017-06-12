package iq.ven.workflow.models.requests.search;

import iq.ven.workflow.dao.ChildrenDAO;
import iq.ven.workflow.models.Child;
import iq.ven.workflow.models.SearchCriteria;

import java.util.List;

public class PersonalRecordCodeSearcher implements Searcher {

    static final SearchCriteria SEARCH_CRITERIA = SearchCriteria.PERSONAL_RECORD_CODE;

    @Override
    public List<Child> search(String value, ChildrenDAO childrenDAO) {
        return childrenDAO.getChildrenByPersonalRecordCode(value);
    }

    @Override
    public SearchCriteria getSearchCriteria() {
        return SEARCH_CRITERIA;
    }
}
