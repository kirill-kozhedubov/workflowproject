package iq.ven.workflow.models.requests.search;

import iq.ven.workflow.dao.ChildrenDAO;
import iq.ven.workflow.models.Child;
import iq.ven.workflow.models.SearchCriteria;
import org.apache.log4j.Logger;

import java.util.List;

public class YearOfBirthSearcher implements Searcher {
    private static final Logger LOGGER = Logger.getLogger(YearOfBirthSearcher.class);
    static final SearchCriteria SEARCH_CRITERIA = SearchCriteria.YEAR_OF_BIRTH;

    @Override
    public List<Child> search(String value, ChildrenDAO childrenDAO) {
        int intFromValue = -20;
        try {
            intFromValue = Integer.parseInt(value);
        } catch (NumberFormatException e) {
            LOGGER.error("Parse error with data " + value, e);
            return null;
        }

        return childrenDAO.getChildrenByBirthYear(intFromValue);
    }

    @Override
    public SearchCriteria getSearchCriteria() {
        return SEARCH_CRITERIA;
    }
}
