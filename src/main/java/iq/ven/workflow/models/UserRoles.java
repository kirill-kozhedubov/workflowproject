package iq.ven.workflow.models;

import iq.ven.workflow.common.IdList;

import java.math.BigInteger;

public enum UserRoles {

    ADMIN(IdList.ADMIN, "ADMIN"),
    REGULAR_USER(IdList.REGULAR_USER, "REGULAR_USER");


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
