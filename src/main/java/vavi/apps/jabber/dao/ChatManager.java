/*
 * Copyright (c) 2004 by Naohide Sano, All Rights Reserved.
 *
 * Programmed by Naohide Sano
 */

package vavi.apps.jabber.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import vavi.apps.jabber.exception.UnavailableException;
import vavi.apps.jabber.web.Chat;
import vavi.apps.jabber.web.Message;
import vavi.util.Singleton;


/**
 * ChatManagert.
 *
 * @author	<a href=mailto:vavivavi@yahoo.co.jp>Naohide Sano</a> (nsano)
 * @version	0.00	040207	nsano	initial version <br>
 */
public class ChatManager extends Singleton {

    /** */
    private static final Log logger = LogFactory.getLog(ChatManager.class);

    /** */
    private ConnectionManager connectionManager;

    /** */
    public ChatManager() {
        connectionManager = Singleton.getInstance(ConnectionManager.class);
    }

    /** */
    public Chat getChat(String username,
                        String buddyname)
        throws UnavailableException {

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        Date startTime = calendar.getTime();
        calendar.add(Calendar.DAY_OF_YEAR, 1);
        Date endTime = calendar.getTime();

        try {
            return getChatInternal(username, buddyname, startTime, endTime);
        } catch (SQLException e) {
            throw new UnavailableException(e);
        }
    }

    /** */
    private Chat getChatInternal(String username,
                                 String buddyname,
                                 Date startTime,
                                 Date endTime)
        throws SQLException {

        Connection conn = connectionManager.getConnection();

        try {
            QueryRunner qr = new QueryRunner();
            ResultSetHandler rsh = new BeanListHandler(Message.class);
            List<?> messages = (List<?>) qr.query(
                conn,
                "SELECT * FROM Message " +
                "WHERE ((username = ? AND buddyname = ?)" +
                "  OR (username = ? AND buddyname = ?))" +
                "  AND (sent > ? AND sent <= ?)",
                new Object[] {
                    username,
                    buddyname,
                    buddyname,
                    username,
                    new Timestamp(startTime.getTime()),
                    new Timestamp(endTime.getTime())
                },
                rsh); 
//Debug.println("messages: " + messages);
    	    //
    	    Chat chat = new Chat(username, buddyname);
            chat.setStartTime(startTime);
            chat.setEndTime(endTime);
            if (messages != null) {
                for (int i = 0; i < messages.size(); i++) {
                    chat.addMessage((Message) messages.get(i));
                }
            }
            return chat;

        } finally {
            DbUtils.close(conn);            
        }
    }
}

/* */
