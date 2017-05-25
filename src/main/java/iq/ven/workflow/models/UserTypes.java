package iq.ven.workflow.models;

import iq.ven.workflow.common.IdList;

import java.math.BigInteger;

public enum UserTypes {

    ADMIN(IdList.ADMIN, "ROLE_ADMIN"),
    REGULAR_USER(IdList.REGULAR_USER, "ROLE_REGULAR_USER");


    final private BigInteger roleId;
    final private String roleName;

    UserTypes(BigInteger roleId, String roleName) {
        this.roleId = roleId;
        this.roleName = roleName;
    }

    public BigInteger getRoleId() {
        return roleId;
    }

    public String getRoleName() {
        return roleName;
    }


    public static UserTypes getRoleById(BigInteger roleId) {
        for (UserTypes e : UserTypes.values()) {
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
