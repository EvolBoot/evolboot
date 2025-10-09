package org.evolboot.security.api.access;

/**
 * @author evol
 */
public interface AccessAuthorities {

    String OR = "  or  ";
    String AND = "  and  ";

    String ROLE_PREFIX = "hasRole('";
    String ROLE_SUFFIX = "')";
    String AUTHORITY_PREFIX = "hasAuthority('";
    String AUTHORITY_SUFFIX = "')";

    String ROLE_SUPER_ADMIN = "ROLE_SUPER_ADMIN", HAS_ROLE_SUPER_ADMIN = ROLE_PREFIX + ROLE_SUPER_ADMIN + ROLE_SUFFIX;
    String ROLE_STAFF = "ROLE_STAFF", HAS_ROLE_STAFF = ROLE_PREFIX + ROLE_STAFF + ROLE_SUFFIX;
    String ROLE_MEMBER = "ROLE_MEMBER", HAS_ROLE_MEMBER = ROLE_PREFIX + ROLE_MEMBER + ROLE_SUFFIX;

    String ROLE_TENANT_OWNER = "ROLE_SUPER_ADMIN", HAS_ROLE_TENANT_OWNER = ROLE_PREFIX + ROLE_TENANT_OWNER + ROLE_SUFFIX;
    String ROLE_TENANT_STAFF = "ROLE_STAFF", HAS_ROLE_TENANT_STAFF = ROLE_PREFIX + ROLE_TENANT_STAFF + ROLE_SUFFIX;



    static String hasRolePack(String value) {
        return "hasRole('" + value + "')";
    }

    static String hasRoleUnpack(String value) {
        return value.replace("hasRole('", "").replace("')", "");
    }


    static String hasAuthorityPack(String value) {
        return "hasAuthority('" + value + "')";
    }

    static String hasAuthorityUnpack(String value) {
        return value.replace("hasAuthority('", "").replace("')", "");
    }


}
