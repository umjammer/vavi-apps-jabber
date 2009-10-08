/*
 * Copyright (c) 2004 by Naohide Sano, All Rights Reserved.
 *
 * Programmed by Naohide Sano
 */

package vavi.apps.jabber.exception;

import java.io.IOException;


/**
 * UnavailableException.
 * 
 * @todo javax.resource.ResourceException ???
 * 
 * @author <a href=mailto:vavivavi@yahoo.co.jp>Naohide Sano</a> (nsano)
 * @version 0.00 040306 nsano initial version <br>
 */
public class UnavailableException extends IOException {

    /** */
    public UnavailableException(Exception e) {
        initCause(e);
    }
}

/* */
