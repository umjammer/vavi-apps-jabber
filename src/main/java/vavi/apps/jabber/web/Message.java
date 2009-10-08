/*
 * Copyright (c) 2004 by Naohide Sano, All Rights Reserved.
 *
 * Programmed by Naohide Sano
 */

package vavi.apps.jabber.web;

import java.util.Date;


/**
 * Message.
 * 
 * @author <a href=mailto:vavivavi@yahoo.co.jp>Naohide Sano</a> (nsano)
 * @version 0.00 040307 nsano initial version <br>
 */
public class Message {

    /** */
    private String username;
    /** */
    private String buddyname;
    /** */
    private Date sent;
    /** */
    private String message;
    /** */
    private int type;

    /** */
    public Message() {
        this.sent = new Date();
    }

    /** */
    public Message(String username, String buddyname) {
        this();

        this.username = username;
        this.buddyname = buddyname;
    }

    /** */
    public void setUsername(String username) {
        this.username = username;
    }

    /** */
    public String getUsername() {
        return username;
    }

    /** */
    public void setBuddyname(String buddyname) {
        this.buddyname = buddyname;
    }

    /** */
    public String getBuddyname() {
        return buddyname;
    }

    /** */
    public void setSent(Date sent) {
        this.sent = sent;
    }

    /** */
    public Date getSent() {
        return sent;
    }

    /** */
    public void setMessage(String message) {
        this.message = message;
    }

    /** */
    public String getMessage() {
        return message;
    }

    /** */
    public void setType(int type) {
        this.type = type;
    }

    /** */
    public int getType() {
        return type;
    }
}

/* */
