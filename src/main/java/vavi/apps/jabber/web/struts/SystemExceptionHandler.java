/*
 * Copyright (c) 2004 by Naohide Sano, All Rights Reserved.
 *
 * Programmed by Naohide Sano
 */

package vavi.apps.jabber.web.struts;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ExceptionHandler;
import org.apache.struts.config.ExceptionConfig;


/**
 * ExceptionHandler.
 * 
 * @author <a href=mailto:vavivavi@yahoo.co.jp>Naohide Sano</a> (nsano)
 * @version 0.00 030130 nsano initial version <br>
 */
public class SystemExceptionHandler extends ExceptionHandler {

    /** */
    private static Log logger =
        LogFactory.getLog(SystemExceptionHandler.class);

    /** */
    public ActionForward execute(Exception e,
                                 ExceptionConfig config,
                                 ActionMapping mapping,
                                 ActionForm form,
                                 HttpServletRequest request,
                                 HttpServletResponse response)
        throws ServletException {

        logger.error(String.valueOf(e), e);

        return super.execute(e, config, mapping, form, request, response);
    }
}

/* */
