/*
 * Copyright (c) 2004 by Naohide Sano, All Rights Reserved.
 *
 * Programmed by Naohide Sano
 */

package vavi.apps.jabber.web.taglib;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.strutsel.taglib.utils.EvalHelper;

import vavi.apps.jabber.web.Globals;
import vavi.net.im.Buddy;
import vavi.net.im.Group;
import vavi.net.im.protocol.Protocol;
import vavi.net.im.protocol.xmpp.JabberProtocol;
import vavi.util.Debug;
import vavi.util.Singleton;


/**
 * インスタントメッセンジャーのアイコンを表示するタグです。
 *
 * @jsp.tag
 * 	name="imIcon"
 *	body-content="empty"
 *	description="インスタントメッセンジャーのアイコンを表示します"
 *
 * @author <a href=mailto:vavivavi@yahoo.co.jp>Naohide Sano</a> (nsano)
 * @version 0.00 040222 nsano initial version <br>
 */
public class ImIconTag extends TagSupport {

    /** */
    private Globals globals = Singleton.getInstance(Globals.class);

    /** */
    private String user;

    /**
     * @jsp.attribute
     * 	name="user"
     *	required="false"
     *	rtexprvalue="true"
     */
    public void setUser(String user) {
        this.user = user;
    }

    /**
     */
    public String getUser() {
        try {
            return EvalHelper.evalString("user", user, this, pageContext);
        } catch (JspException e) {
Debug.println(e);
            return null;
        }
    }

    /** */
    public int doStartTag() throws JspException {
        HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();

        JabberProtocol protocol = (JabberProtocol) request.getAttribute("jabberClient");
Debug.println("user: " + getUser());
        String status = getBuddyStatus(protocol, getUser());
        String path = getPresenceImagePath(status);

        try {
            pageContext.getOut().print("<img src=\"" + path + "\">");
        } catch (IOException e) {
            throw new JspException(e);
        }

        return SKIP_BODY;
    }

    /** @return null when not found */
    private String getBuddyStatus(Protocol protocol, String name) {
        for (Buddy buddy : protocol.getDefaultGroup()) {
            if (buddy.getUsername().equals(name)) {
                return buddy.getStatus();
            }
        }
        for (Group group : protocol.getGroups()) {
            for (Buddy buddy : group) {
                if (buddy.getUsername().equals(name)) {
                    return buddy.getStatus();
                }
            }
        }
        return null;
    }

    /** */
    private String getPresenceImagePath(String type) {
        String path = null;
Debug.println("type: " + type);
        if ("available".equals(type)) {
            path = (String) globals.get("icon.available");
        } else if ("away".equals(type)) {
            path = (String) globals.get("icon.invisible");
        } else if ("chat".equals(type)) {
            path = (String) globals.get("icon.invisible");
        } else if ("do_not_disturb".equals(type)) {
            path = (String) globals.get("icon.invisible");
        } else if ("extended_away".equals(type)) {
            path = (String) globals.get("icon.invisible");
        } else if ("invisible".equals(type)) {
            path = (String) globals.get("icon.invisible");
        } else {
            path = (String) globals.get("icon.invisible");
        }
        return path;
    }
}

/* */
