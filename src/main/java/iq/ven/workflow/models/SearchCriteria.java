package iq.ven.workflow.models;

public enum SearchCriteria {
    ALL_CHILDREN(8, "Усі особисті справи"),
    PARENT_NAME(2, "Ім'я родича"),
    DATE_OF_BIRTH(3, "Дата народження"),
    YEAR_OF_BIRTH(4, "Рік народження"),
    DISTRICT(5, "Район"),
    DUTY_OFFICER(6, "Черговий з режиму"),
    PERSONAL_RECORD_CODE(7, "№ особистої справи"),
    FULL_NAME(1, "Полне ім\'я");

    private final String russianName;
    private final int id;

    SearchCriteria(int id, String russianName) {
        this.russianName = russianName;
        this.id = id;
    }

    public static SearchCriteria getSearchCriteriaById(int id) {
        for (SearchCriteria e : SearchCriteria.values()) {
            if (e.id == id) {
                return e;
            }
        }
        return null;// not found
    }

    public int getId() {
        return id;
    }

    public String getRussianName() {
        return russianName;
    }

    @Override
    public String toString() {
        return russianName;
    }
}
