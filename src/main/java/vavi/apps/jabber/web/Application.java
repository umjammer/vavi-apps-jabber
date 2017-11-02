/*
 * Copyright (c) 2004 by Naohide Sano, All Rights Reserved.
 *
 * Programmed by Naohide Sano
 */

package vavi.apps.jabber.web;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import vavi.util.Debug;
import vavi.util.Singleton;
import vavix.kumi.Action;
import vavix.kumi.ActionContext;
import vavix.kumi.Request;
import vavix.kumi.Response;


/**
 * Application.
 * 
 * @author <a href=mailto:vavivavi@yahoo.co.jp>Naohide Sano</a> (nsano)
 * @version 0.00 040205 nsano initial version <br>
 */
public class Application extends Singleton {

    /** */
    public void error(Throwable t) {
Debug.printStackTrace(t);
    }

    /** */
    public void exec(String name) throws Exception {
        ActionContext context = new ActionContext();
        context.setName(name);
        exec(context);
    }

    /**
     * @throws IllegalArgumentException
     */
    public void exec(ActionContext context) throws Exception {
        String name = context.getName();
        if (actions.containsKey(name)) {
            Action action = actions.get(name);
            Request request = context.getRequest();	// @@@
            if (request == null) {
                request = new Request();
                context.setRequest(request);
            }
            Response response = context.getResponse();
            if (response == null) {
                response = new Response();
                context.setResponse(response);
            }
            action.execute(context.getForm(), request, response);
        } else {
            throw new IllegalArgumentException("unknown action: " + name);
        }
    }

    /** */
    private static Map<String, Action> actions = new HashMap<>();

    /** */
    static {
        try {
            final String path = "application.properties";
            Properties props = new Properties();
            props.load(Application.class.getResourceAsStream(path));
            Enumeration<?> e = props.propertyNames();
            while (e.hasMoreElements()) {
                String key = (String)e.nextElement();
                String value = props.getProperty(key);
                Class<?> clazz = Class.forName(value);
                Action action = (Action) clazz.newInstance();
                actions.put(key, action);
Debug.println(key + ": " + value);
            }
        } catch (Exception e) {
Debug.printStackTrace(e);
        }
    }
}

/* */
