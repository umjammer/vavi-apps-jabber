/*
 * Copyright (c) 2004 by Naohide Sano, All Rights Reserved.
 *
 * Programmed by Naohide Sano
 */

package vavix.enterprise.webapp;


/**
 * SecurityRole.
 * 
 * @author <a href=mailto:vavivavi@yahoo.co.jp>Naohide Sano</a> (nsano)
 * @version 0.00 040302 nsano initial version <br>
 */
public class SecurityRole {

    /** */
    private String description;

    /** */
    public void setDescription(String description) {
        this.description = description;
    }

    /** */
    public String getDescription() {
        return description;
    }

    /** */
    private String roleName;

    /** */
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    /** */
    public String getRoleName() {
        return roleName;
    }
}

/* */
