package iq.ven.workflow.models.requests.search;

import iq.ven.workflow.dao.ChildrenDAO;
import iq.ven.workflow.models.Child;
import iq.ven.workflow.models.SearchCriteria;
import org.apache.log4j.Logger;

import java.util.List;

public class DutyOfficerSearcher implements Searcher {
    private static final Logger LOGGER = Logger.getLogger(DutyOfficerSearcher.class);
    static final SearchCriteria SEARCH_CRITERIA = SearchCriteria.DUTY_OFFICER;

    @Override
    public List<Child> search(String value, ChildrenDAO childrenDAO) {
        childrenDAO.getChildrenByDutyOfficer(value);

        return null;
    }

    @Override
    public SearchCriteria getSearchCriteria() {
        return SEARCH_CRITERIA;
    }
}
