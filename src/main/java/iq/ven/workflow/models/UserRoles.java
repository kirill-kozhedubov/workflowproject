package iq.ven.workflow.models;

import java.math.BigInteger;

public enum UserRoles {

    ADMIN(BigInteger.valueOf(1L), "ADMIN"),
    REGULAR_USER(BigInteger.valueOf(2L), "REGULAR_USER");


    final private BigInteger roleId;
    final private String roleName;

    UserRoles(BigInteger roleId, String roleName) {
        this.roleId = roleId;
        this.roleName = roleName;
    }

    public BigInteger getRoleId() {
        return roleId;
    }

    public String getRoleName() {
        return roleName;
    }


    public static UserRoles getRoleById(BigInteger roleId) {
        for (UserRoles e : UserRoles.values()) {
            if (e.roleId.equals(roleId)) {
                return e;
            }
        }
        return null;// not found
    }

    @Override
    public String toString() {
        return roleName;
    }
}
