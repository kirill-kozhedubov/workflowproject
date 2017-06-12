package iq.ven.workflow.models.requests.search;

import iq.ven.workflow.dao.ChildrenDAO;
import iq.ven.workflow.models.Child;
import iq.ven.workflow.models.SearchCriteria;

import java.util.List;

public class AllChildrenSearcher implements Searcher {

    static final SearchCriteria SEARCH_CRITERIA = SearchCriteria.ALL_CHILDREN;

    @Override
    public List<Child> search(String value, ChildrenDAO childrenDAO) {
        return childrenDAO.getAllChildrenList();
    }

    @Override
    public SearchCriteria getSearchCriteria() {
        return null;
    }
}
