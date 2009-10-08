/*
 * Copyright (c) 2004 by Naohide Sano, All Rights Reserved.
 *
 * Programmed by Naohide Sano
 */

package vavi.apps.jabber.web;

/**
 * Profile.
 * 
 * @author <a href=mailto:vavivavi@yahoo.co.jp>Naohide Sano</a> (nsano)
 * @version 0.00 040130 nsano initial version <br>
 */
public class Profile {

    /* */
    private String username;

    /* */
    public String getUsername() {
        return username;
    }

    /* */
    public void setUsername(String username) {
        this.username = username;
    }

    /* */
    private String password;

    /* */
    public String getPassword() {
        return password;
    }

    /* */
    public void setPassword(String password) {
        this.password = password;
    }

    /* */
    private String server;

    /* */
    public String getServer() {
        return server;
    }

    /* */
    public void setServer(String server) {
        this.server = server;
    }

    /* */
    private String resource;

    /* */
    public String getResource() {
        return resource;
    }

    /* */
    public void setResource(String resource) {
        this.resource = resource;
    }

    /* */
    private boolean usessl;

    /* */
    public boolean isUsessl() {
        return usessl;
    }

    /* */
    public void setUsessl(boolean usessl) {
        this.usessl = usessl;
    }
}

/* */
