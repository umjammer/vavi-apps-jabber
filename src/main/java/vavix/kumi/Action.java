/*
 * Copyright (c) 2004 by Naohide Sano, All Rights Reserved.
 *
 * Programmed by Naohide Sano
 */

package vavix.kumi;

import org.apache.struts.action.ActionForm;


/**
 * Action.
 * 
 * @author <a href=mailto:vavivavi@yahoo.co.jp>Naohide Sano</a> (nsano)
 * @version 0.00 040211 nsano initial version <br>
 */
public abstract class Action {

    /**
     */
    public abstract void execute(ActionForm form,
                                 Request request,
                                 Response response) throws Exception;
}

/* */
