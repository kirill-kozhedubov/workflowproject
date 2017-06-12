package iq.ven.workflow.models.requests.search;

import iq.ven.workflow.common.IdList;
import iq.ven.workflow.dao.ChildrenDAO;
import iq.ven.workflow.models.Child;
import iq.ven.workflow.models.SearchCriteria;
import org.apache.log4j.Logger;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

public class DateOfBirthSearcher implements Searcher {
    private static final Logger LOGGER = Logger.getLogger(DateOfBirthSearcher.class);
    static final SearchCriteria SEARCH_CRITERIA = SearchCriteria.DATE_OF_BIRTH;

    @Override
    public List<Child> search(String value, ChildrenDAO childrenDAO) {
        Date dateFromString;
        try {
            dateFromString = IdList.SIMPLE_DATE_FORMATTER.parse(value);
        } catch (ParseException e) {
            LOGGER.error("Parse error with data " + value, e);
            return null;
        }
        return childrenDAO.getChildrenByBirthDate(dateFromString);
    }

    @Override
    public SearchCriteria getSearchCriteria() {
        return SEARCH_CRITERIA;
    }
}
