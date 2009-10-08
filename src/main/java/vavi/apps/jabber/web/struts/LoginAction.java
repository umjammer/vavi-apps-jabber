/*
 * Copyright (c) 2004 by Naohide Sano, All Rights Reserved.
 *
 * Programmed by Naohide Sano
 */

package vavi.apps.jabber.web.struts;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;

import vavi.apps.jabber.web.Application;
import vavi.util.Singleton;
import vavix.kumi.ActionContext;


/**
 * Login.
 * 
 * @author <a href=mailto:vavivavi@yahoo.co.jp>Naohide Sano</a> (nsano)
 * @version 0.00 040108 nsano initial version <br>
 */
public final class LoginAction extends Action {

    /** */
    private Application application = Singleton.getInstance(Application.class);

    /**
     */
    public ActionForward execute(ActionMapping mapping,
                                 ActionForm form,
                                 HttpServletRequest request,
                                 HttpServletResponse response)
        throws Exception {

        DynaActionForm profileForm = (DynaActionForm) form;
        String function = (String)profileForm.get("function");

        if ("Easy".equals(function)) {
            return mapping.findForward("easy-login");
        } else {
            //
            ActionContext context = new ActionContext();
            context.setName("jabber.login");
            context.setForm(form);

            application.exec(context);

//Debug.println("response: " + context.getResponse());
            Object jabberClient = context.getResponse().get("jabberClient");

            //
            HttpSession session = request.getSession(false);
            session.setAttribute("jabberClient", jabberClient);

            //
            request.setAttribute("jabberClient", jabberClient);

            //
            return mapping.findForward("success");
        }
    }
}

/* */
