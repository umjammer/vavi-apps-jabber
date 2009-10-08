/*
 * Copyright (c) 2004 by Naohide Sano, All Rights Reserved.
 *
 * Programmed by Naohide Sano
 */

package vavi.apps.jabber.web.filter;

import javax.servlet.http.HttpSession;
import org.apache.catalina.session.StandardSessionFacade;


/**
 * Jabber を用いた HttpSession クラスです。
 * 
 * @author <a href=mailto:vavivavi@yahoo.co.jp>Naohide Sano</a> (nsano)
 * @version 0.00 040224 nsano initial version <br>
 */
public class JabberSession extends StandardSessionFacade {

    /** */
    public JabberSession(HttpSession session) {
        super(session);
    }
}

/* */
