package iq.ven.workflow.models.requests.search;

import iq.ven.workflow.dao.ChildrenDAO;
import iq.ven.workflow.models.Child;
import iq.ven.workflow.models.SearchCriteria;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface Searcher {

    Map<SearchCriteria, Searcher> mapOfClasses = initSearcherMap();

    static Map<SearchCriteria, Searcher> initSearcherMap() {
        Map<SearchCriteria, Searcher> result = new HashMap<>();
        result.put(SearchCriteria.FULL_NAME, new FullNameSearcher());
        result.put(SearchCriteria.PARENT_NAME, new ParentNameSearcher());
        result.put(SearchCriteria.DATE_OF_BIRTH, new DateOfBirthSearcher());
        result.put(SearchCriteria.YEAR_OF_BIRTH, new YearOfBirthSearcher());
        result.put(SearchCriteria.DISTRICT, new DistrictSearcher());
        result.put(SearchCriteria.DUTY_OFFICER, new DutyOfficerSearcher());
        result.put(SearchCriteria.PERSONAL_RECORD_CODE, new PersonalRecordCodeSearcher());
        result.put(SearchCriteria.ALL_CHILDREN, new AllChildrenSearcher());
        return Collections.unmodifiableMap(result);
    }

    List<Child> search(String value, ChildrenDAO childrenDAO);

    SearchCriteria getSearchCriteria();

    static Searcher getSearcherBySearchCriteria(SearchCriteria searchCriteria) {
        return mapOfClasses.get(searchCriteria);
    }
}
