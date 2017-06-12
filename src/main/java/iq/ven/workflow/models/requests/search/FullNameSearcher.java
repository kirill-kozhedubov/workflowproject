package iq.ven.workflow.models.requests.search;

import iq.ven.workflow.dao.ChildrenDAO;
import iq.ven.workflow.models.Child;
import iq.ven.workflow.models.SearchCriteria;

import java.util.List;


public class FullNameSearcher implements Searcher {

    static final SearchCriteria SEARCH_CRITERIA = SearchCriteria.FULL_NAME;

    @Override
    public List<Child> search(String value, ChildrenDAO childrenDAO) {
        return childrenDAO.getChildrenByFullName(value);
    }

    @Override
    public SearchCriteria getSearchCriteria() {
        return SEARCH_CRITERIA;
    }
}
