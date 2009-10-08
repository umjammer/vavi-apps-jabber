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

import vavi.net.im.protocol.Protocol;
import vavi.util.Debug;
import vavi.util.StringUtil;


/**
 * Logout.
 * 
 * @author <a href=mailto:vavivavi@yahoo.co.jp>Naohide Sano</a> (nsano)
 * @version 0.00 030130 nsano initial version <br>
 */
public final class LogoutAction extends Action {

    /**
     *
     * @forward
     * 	name="success"
     *	path="/pages/top.jsp"
     *
     * @throws SQLException
     */
    public ActionForward execute(ActionMapping mapping,
                                 ActionForm form,
                                 HttpServletRequest request,
                                 HttpServletResponse response)
        throws Exception {

        //
        String username = request.getRemoteUser();
Debug.println("username: " + username);
        //
        HttpSession session = request.getSession(false);
        Protocol protocol = (Protocol) session.getAttribute("jabberClient");
Debug.print(StringUtil.paramString(protocol));
        protocol.disconnect();

        session.invalidate();

        return mapping.findForward("success");
    }
}

/* */
