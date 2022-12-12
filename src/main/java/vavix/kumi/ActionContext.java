/*
 * Copyright (c) 2004 by Naohide Sano, All Rights Reserved.
 *
 * Programmed by Naohide Sano
 */

package vavix.kumi;

import org.apache.struts.action.ActionForm;


/**
 * ActionContext.
 * 
 * @author <a href=mailto:vavivavi@yahoo.co.jp>Naohide Sano</a> (nsano)
 * @version 0.00 040207 nsano initial version <br>
 */
public class ActionContext {

    /** */
    private String name;

    /** */
    public String getName() {
        return name;
    }

    /** */
    public void setName(String name) {
        this.name = name;
    }

    /** TODO name is VO ??? */
    private ActionForm form;

    /** */
    public void setForm(ActionForm form) {
        this.form = form;
    }

    /** */
    public ActionForm getForm() {
        return form;
    }

    /** */
    private Request request;

    /** */
    public void setRequest(Request request) {
        this.request = request;
    }

    /** */
    public Request getRequest() {
        return request;
    }

    /** */
    private Response response;

    /** */
    public void setResponse(Response response) {
        this.response = response;
    }

    /** */
    public Response getResponse() {
        return response;
    }

    // convenience

    /** */
    public boolean getResponseBoolean(String name) {
        Boolean result = (Boolean) response.get(name);
        return result;
    }
}

/* */
