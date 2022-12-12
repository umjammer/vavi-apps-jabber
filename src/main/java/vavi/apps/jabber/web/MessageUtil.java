/*
 * Copyright (c) 2004 by Naohide Sano, All Rights Reserved.
 *
 * Programmed by Naohide Sano
 */

package vavi.apps.jabber.web;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;



/**
 * MessageUtil.
 * 
 * @author <a href=mailto:vavivavi@yahoo.co.jp>Naohide Sano</a> (nsano)
 * @version 0.00 040208 nsano initial version <br>
 */
public class MessageUtil {

    /** */
    public static String getTime() {
        DateFormat formatter = new SimpleDateFormat("[HH:mm:ss]");
        String time = formatter.format(new Date());

        return time;
    }

    /** */
    public static String replaceText(String message, boolean flag) {
        return null;
    }

    /** */
    public static Message getSignedOnMessage(String username, String buddyname) {
        Message message = new Message(username, buddyname);
        StringBuilder sb = new StringBuilder(); // TODO
        message.setMessage(sb.toString());
        return message;
    }

    /** */
    public static Message getSignedOffMessage(String username, String buddyname) {
        Message message = new Message(username, buddyname);
        StringBuilder sb = new StringBuilder(); // TODO
        message.setMessage(sb.toString());
        return message;
    }

    /** */
    public static Message getOfflineMessage(String username, String buddyname) {
        Message message = new Message(username, buddyname);
        StringBuilder sb = new StringBuilder(); // TODO
        message.setMessage(sb.toString());
        return message;
    }
}

/* */
