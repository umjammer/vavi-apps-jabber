/*
 * Copyright (c) 2004 by Naohide Sano, All Rights Reserved.
 *
 * Programmed by Naohide Sano
 */

package vavi.apps.jabber.web;

import java.util.SortedMap;
import java.util.TreeMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jivesoftware.smackx.muc.MultiUserChat;

import vavi.net.im.Buddy;
import vavi.net.im.protocol.xmpp.JabberProtocol;
import vavi.util.Debug;
import vavi.util.Singleton;


/**
 * ChatRoom.
 * 
 * @author <a href=mailto:vavivavi@yahoo.co.jp>Naohide Sano</a> (nsano)
 * @version 0.00 040205 nsano initial version <br>
 */
public class ChatRoom {

    /** */
    private Application application = Singleton.getInstance(Application.class);

    /** */
    private static final Log logger = LogFactory.getLog(ChatRoom.class);

    /** */
    private StringBuilder conversationArea = new StringBuilder();

    /** */
    private MultiUserChat chat;

    /** */
    private String subject = "No subject";
    
    /** */
    public void setSubject(String subject) {
        this.subject = subject;
        try {
            application.exec("update.subject");
        } catch (Exception e) {
Debug.println(e);
        }
        // subjectLabel.setText("Subject: " + subject);
    }
    
    /** */
    public void receiveMessage(String from, String message) {
        //replace emoticons, links, etc...
        message = MessageUtil.replaceText(message, false);
        
        if (from.equals("") ||
            from.equalsIgnoreCase(chat.getRoom())) {
            //server message
            conversationArea.append(MessageUtil.getTime()).append("<font color='green'> -> ").append(message).append("</font><br>");
        } else {
            if (message.startsWith(" /me ")) {
                message = message.replaceAll("^ \\/me ", "");
                conversationArea.append(MessageUtil.getTime()).append(" <b><font color='maroon'>* ").append(from).append("</font></b> ").append(message).append("<br>");
            } else {
                conversationArea.append(MessageUtil.getTime()).append(" <font color='#16569e'><b>").append(from).append("</b></font>: ").append(message).append("<br>");
            }
            try {
                application.exec("play.groupRecieved.sound");
            } catch (Exception e) {
Debug.println(e);
            }
        }
        
        logger.info(conversationArea.toString());
    }

    /** */
    private SortedMap<String, Buddy> buddyStatuses = new TreeMap<>();

    /** */
    public SortedMap<String, Buddy> getBuddyStatuses() {
        return buddyStatuses;
    }
    
    /** */
    private SortedMap<String, Buddy> pmBuddies = new TreeMap<>();

    /** */
    public void setPMBuddies(SortedMap<String, Buddy> pmBuddies) {
        this.pmBuddies = pmBuddies;
    }
    
    /** */
    public SortedMap<String, Buddy> getPMBuddies() {
        return pmBuddies;
    }

    /** */
    private JabberProtocol jabberProtocol;

    /** */
    public JabberProtocol getJabberProtocol() {
        return jabberProtocol;
    }

    /**
     * Gets the entire room name, server included
     **/
    public String getRoomName() {
        return chat.getRoom();
    }
}

/* */
