/*
 * Copyright (c) 2004 by Naohide Sano, All Rights Reserved.
 *
 * Programmed by Naohide Sano
 */

package vavi.apps.jabber.web.struts;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import vavi.net.im.protocol.xmpp.JabberProtocol;


/**
 * Top.
 * 
 * @author <a href=mailto:vavivavi@yahoo.co.jp>Naohide Sano</a> (nsano)
 * @version 0.00 030130 nsano initial version <br>
 */
public final class TopAction extends Action {

    /**
     *
     * @forward
     * 	name="success"
     *	path="/pages/login.jsp"
     *
     * @throws SQLException
     */
    public ActionForward execute(ActionMapping mapping,
                                 ActionForm form,
                                 HttpServletRequest request,
                                 HttpServletResponse response)
        throws Exception {

        //
        HttpSession session = request.getSession(false);
        JabberProtocol jabberClient = (JabberProtocol) session.getAttribute("jabberClient");
//Debug.print(StringUtil.paramString(jabberClient));
        request.setAttribute("jabberClient", jabberClient);

        //
        return mapping.findForward("success");
    }
}

/* */
