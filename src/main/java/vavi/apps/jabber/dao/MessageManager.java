/*
 * Copyright (c) 2004 by Naohide Sano, All Rights Reserved.
 *
 * Programmed by Naohide Sano
 */

package vavi.apps.jabber.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;

import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import vavi.apps.jabber.exception.UnavailableException;
import vavi.apps.jabber.web.Message;
import vavi.util.Debug;
import vavi.util.Singleton;


/**
 * MessageManager.
 * 
 * @author <a href=mailto:vavivavi@yahoo.co.jp>Naohide Sano</a> (nsano)
 * @version 0.00 040307 nsano initial version <br>
 */
public class MessageManager extends Singleton {

    /** */
    private static final Log logger = LogFactory.getLog(MessageManager.class);

    /** */
    private ConnectionManager connectionManager = Singleton.getInstance(ConnectionManager.class);

    /** */
    public void insertMessage(Message message)
        throws UnavailableException {

	try {
            insertMessageInternal(message);
        } catch (SQLException e) {
            throw new UnavailableException(e);
        }
    }

    /** */
    private void insertMessageInternal(Message message)
        throws SQLException {

        Connection conn = connectionManager.getConnection();

        try {
            QueryRunner qr = new QueryRunner();
            int result = qr.update(
                conn,
                "INSERT INTO Message VALUES (?, ?, ?, ?, ?)",
                new Object[] {
                    message.getUsername(),
                    message.getBuddyname(),
                    new Timestamp(message.getSent().getTime()),
                    message.getMessage(),
                        message.getType()
                });
Debug.print("result: " + result);
        } finally {
            DbUtils.close(conn);            
        }
    }
}

/* */
