/*
 * Copyright (c) 2004 by Naohide Sano, All Rights Reserved.
 *
 * Programmed by Naohide Sano
 */

package vavix.kumi;

import java.util.HashMap;
import java.util.Map;


/**
 * ActionProperties.
 * 
 * @author <a href=mailto:vavivavi@yahoo.co.jp>Naohide Sano</a> (nsano)
 * @version 0.00 040207 nsano initial version <br>
 */
public abstract class ActionProperties {

    /** */
    protected Map<String, Object> props = new HashMap<>();

    /** */
    public void set(String name, Object value) {
        props.put(name, value);
    }

    /** */
    public Object get(String name) {
        return props.get(name);
    }

    /** */
    public void remove(String name) {
        props.remove(name);
    }
}

/* */
