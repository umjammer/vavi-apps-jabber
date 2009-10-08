/*
 * Copyright (c) 2004 by Naohide Sano, All Rights Reserved.
 *
 * Programmed by Naohide Sano
 */

package vavi.apps.jabber.web.filter;

import java.io.IOException;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import vavi.net.im.protocol.xmpp.JabberProtocol;
import vavi.util.Debug;


/**
 * JabberSessionListener.
 * 
 * @web.listener
 * 
 * @author <a href=mailto:vavivavi@yahoo.co.jp>Naohide Sano</a> (nsano)
 * @version 0.00 040307 nsano initial version <br>
 */
public final class JabberSessionListener implements HttpSessionListener {

    /** */
    public void sessionCreated(HttpSessionEvent event) {
        HttpSession session = event.getSession();
        String sid = session.getId();
        long time = session.getCreationTime();
//Debug.printStackTrace(new Exception("dummy"));
Debug.println("@@@@@@@ new session id: " + sid + ", " + time);
    }

    /** */
    public void sessionDestroyed(HttpSessionEvent event) {
Debug.println("session " + event.getSession().getId() + " destroyed");
        try {
            HttpSession session = event.getSession();
            JabberProtocol jabberClient = (JabberProtocol) session.getAttribute("jabberClient");
            if (jabberClient == null) {
Debug.println("no jabber client");
            } else {
                jabberClient.disconnect();
            }
        } catch (IOException e) {
Debug.println(e.getMessage());
        }
    }
}

/* */
