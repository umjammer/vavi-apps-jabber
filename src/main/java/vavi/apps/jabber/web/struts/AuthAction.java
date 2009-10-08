/*
 * Copyright (c) 2004 by Naohide Sano, All Rights Reserved.
 *
 * Programmed by Naohide Sano
 */

package vavi.apps.jabber.web.struts;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;

import vavi.apps.jabber.web.Globals;
import vavi.util.Singleton;


/**
 * NotLoggedInHandler.
 *
 * @author	<a href=mailto:vavivavi@yahoo.co.jp>Naohide Sano</a> (nsano)
 * @version	0.00	030307	nsano	initial version <br>
 */
public final class AuthAction extends Action {

    /** */
    private Globals globals = Singleton.getInstance(Globals.class);

    /**
     *
     */
    public ActionForward execute(ActionMapping mapping,
                                 ActionForm form,
                                 HttpServletRequest request,
                                 HttpServletResponse response)
        throws Exception {

        //
        DynaActionForm profileForm = (DynaActionForm) form;
        profileForm.set("username", globals.get("debug.username"));
        profileForm.set("password", globals.get("debug.password"));
        profileForm.set("resource", globals.get("debug.resource"));
        profileForm.set("server", globals.get("debug.server"));
        profileForm.set("usessl", globals.get("debug.usessl"));

        //
        return mapping.findForward("success");
    }
}

/* */
