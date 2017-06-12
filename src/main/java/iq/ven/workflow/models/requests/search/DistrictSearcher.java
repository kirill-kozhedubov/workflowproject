package iq.ven.workflow.models.requests.search;

import iq.ven.workflow.dao.ChildrenDAO;
import iq.ven.workflow.models.Child;
import iq.ven.workflow.models.Districts;
import iq.ven.workflow.models.SearchCriteria;
import org.apache.log4j.Logger;

import java.math.BigInteger;
import java.util.List;

public class DistrictSearcher implements Searcher {
    private static final Logger LOGGER = Logger.getLogger(DistrictSearcher.class);
    static final SearchCriteria SEARCH_CRITERIA = SearchCriteria.DISTRICT;

    @Override
    public List<Child> search(String value, ChildrenDAO childrenDAO) {
        Districts district = Districts.getDistrictByName(value);
        if (district != null) {
            return childrenDAO.getChildrenByDistrict(district);
        } else {
            return null;
        }
    }

    @Override
    public SearchCriteria getSearchCriteria() {
        return SEARCH_CRITERIA;
    }
}
