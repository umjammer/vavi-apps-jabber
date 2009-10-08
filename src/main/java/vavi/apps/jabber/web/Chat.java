/*
 * Copyright (c) 2004 by Naohide Sano, All Rights Reserved.
 *
 * Programmed by Naohide Sano
 */

package vavi.apps.jabber.web;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;



/**
 * Chat.
 * 
 * @author <a href=mailto:vavivavi@yahoo.co.jp>Naohide Sano</a> (nsano)
 * @version 0.00 040306 nsano initial version <br>
 */
public class Chat {

    /** */
    private String username;
    /** */
    private String buddyname;
    /** */
    private Date startTime;
    /** */
    private Date endTime;
    /** */
    private List<Message> messages = new ArrayList<Message>();

    /** */
    public Chat() {
        // default is today's messages
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        this.startTime = calendar.getTime();
        calendar.add(Calendar.DAY_OF_YEAR, 1);
        this.endTime = calendar.getTime();
    }

    /** */
    public Chat(String username, String buddyname) {
        this();

        this.username = username;
        this.buddyname = buddyname;
    }

    /** */
    public void setUsername(String username) {
        this.username = username;
    }

    /** */
    public void setBuddyname(String buddyname) {
        this.buddyname = buddyname;
    }

    /** */
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    /** */
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    /** */
    public void addMessage(Message message) {
        messages.add(message);
    }

    /** */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < messages.size(); i++) {
            Message message = messages.get(i);
            switch (message.getType()) {
            case 0:
                if (message.getUsername().equals(username)) {
                    sb.append("<font color='red'>");
                    sb.append("me: ");
                    sb.append("</font>");
                    sb.append(message.getMessage());
                    sb.append("<br>");
                } else {
                    sb.append("<font color='blue'>");
                    sb.append(message.getUsername());
                    sb.append(": ");
                    sb.append("</font>");
                    sb.append(message.getMessage());
                    sb.append("<br>");
                }
                break;
            case 1:
                if (message.getUsername().equals(username)) {
                    sb.append("<font color='green'>");
                    sb.append("<b> ");
                    sb.append(message.getBuddyname());
                    sb.append(" signed on</b></font><br>");
                }
                break;
            case 2:
                if (message.getUsername().equals(username)) {
                    sb.append("<font color='green'>");
                    sb.append("<b> ");
                    sb.append(message.getBuddyname());
                    sb.append(" signed off</b></font><br>");
                }
                break;
            case 3:
                if (message.getUsername().equals(username)) {
                    sb.append("<font color='green'>");
                    sb.append("<b> ");
                    sb.append(message.getBuddyname());
                    sb.append(" offline</b></font><br>");
                }
                break;
            default:
                break;
            }
        }
        return sb.toString();
    }
}

/* */
