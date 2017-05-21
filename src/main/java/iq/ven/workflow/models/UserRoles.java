package iq.ven.workflow.models;

public enum UserRoles {

    ADMIN(100000),
    REGUALR_USER(1000);


    final private int rolePoints;

    UserRoles(int rolePoints) {
        this.rolePoints = rolePoints;
    }

    public int getRolePoints() {
        return rolePoints;
    }

    public static UserRoles getRoleByValue(int rolePoints) {
        for (UserRoles e : UserRoles.values()) {
            if (e.rolePoints == rolePoints) {
                return e;
            }
        }
        return null;// not found
    }
}
