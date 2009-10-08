/*
 * Copyright (c) 2004 by Naohide Sano, All Rights Reserved.
 *
 * Programmed by Naohide Sano
 */

package vavi.apps.jabber.dao;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.SQLException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import vavi.apps.jabber.web.Globals;
import vavi.util.Singleton;


/**
 * CoonectionManager.
 * 
 * @author <a href=mailto:vavivavi@yahoo.co.jp>Naohide Sano</a> (nsano)
 * @version 0.00 040208 nsano initial version <br>
 */
public final class ConnectionManager extends Singleton {

    /** */
    private static Log logger = LogFactory.getLog(ConnectionManager.class);

    /** */
    private Driver poolDriver;
    /** */
    private Driver jdbcDriver;

    /** */

    /** TODO */
    public ConnectionManager() {
        Globals globals = Singleton.getInstance(Globals.class);
        String jdbcDriverName = (String) globals.get("jdbc.driver");
        String poolDriverName = (String) globals.get("pool.driver");

        try {
            jdbcDriver = (Driver) Class.forName(jdbcDriverName).newInstance();
logger.debug("jdbc.driver: " + jdbcDriver);
            poolDriver = (Driver) Class.forName(poolDriverName).newInstance();
logger.debug("pool.driver: " + poolDriver);
        } catch (ClassNotFoundException e) {
            throw (RuntimeException) new IllegalStateException().initCause(e);
        } catch (InstantiationException e) {
            throw (RuntimeException) new IllegalStateException().initCause(e);
        } catch (IllegalAccessException e) {
            throw (RuntimeException) new IllegalStateException().initCause(e);
        }
    }

    /** */
    public Connection getConnection() throws SQLException {
        Globals globals = Singleton.getInstance(Globals.class);
        String poolUrl = (String) globals.get("pool.url");

        Connection conn = poolDriver.connect(poolUrl, null);
logger.debug("conn: " + conn);
        return conn;
    }
}

/* */
