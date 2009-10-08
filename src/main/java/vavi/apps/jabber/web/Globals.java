/*
 * Copyright (c) 2004 by Naohide Sano, All Rights Reserved.
 *
 * Programmed by Naohide Sano
 */

package vavi.apps.jabber.web;

import java.io.IOException;
import java.util.Properties;

import vavi.util.Singleton;



/**
 * Globals.
 * 
 * @author <a href=mailto:vavivavi@yahoo.co.jp>Naohide Sano</a> (nsano)
 * @version 0.00 040130 nsano initial version <br>
 */
public final class Globals extends Singleton {

    /** */
    private Properties props = new Properties();

    /** */
    public Object get(String name) {
        return props.get(name);
    }

    /** */
    public void set(String name, Object value) {
        props.put(name, value);
    }

    /** */
    public void remove(String name) {
        props.remove(name);
    }

    /** */
    public void load() throws IOException {
        props.load(Globals.class.getResourceAsStream("globals.properties"));
    }

    /** */
    static {
        try {
            Globals globals = Singleton.getInstance(Globals.class);
            globals.load();
        } catch (IOException e) {
e.printStackTrace(System.err);
        }
    }
}

/* */
