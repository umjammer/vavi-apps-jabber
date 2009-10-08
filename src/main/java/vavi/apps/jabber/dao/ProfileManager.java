/*
 * Copyright (c) 2004 by Naohide Sano, All Rights Reserved.
 *
 * Programmed by Naohide Sano
 */

package vavi.apps.jabber.dao;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.commons.dbutils.BasicRowProcessor;
import org.apache.commons.dbutils.BeanProcessor;
import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.RowProcessor;
import org.apache.commons.dbutils.handlers.BeanHandler;

import vavi.apps.jabber.exception.UnavailableException;
import vavi.apps.jabber.web.Profile;
import vavi.util.Debug;
import vavi.util.Singleton;
import vavi.util.StringUtil;


/**
 * ProfileManager.
 * 
 * @author <a href=mailto:vavivavi@yahoo.co.jp>Naohide Sano</a> (nsano)
 * @version 0.00 040208 nsano initial version <br>
 */
public final class ProfileManager extends Singleton {

    /** */
    private ConnectionManager connectionManager = Singleton.getInstance(ConnectionManager.class);

    /** */
    private RowProcessor rp = new BasicRowProcessor(new BeanProcessor());

    /** */
    public Profile getProfile(String username)
        throws UnavailableException {

	try {
            return getProfileInternal(username);
        } catch (SQLException e) {
            throw new UnavailableException(e);
        }
    }

    /** */
    private Profile getProfileInternal(String username)
        throws SQLException {

        Connection conn = connectionManager.getConnection();

        try {
            QueryRunner qr = new QueryRunner();
            ResultSetHandler rsh = new BeanHandler(Profile.class, rp);
            Profile profile = (Profile) qr.query(
                conn,
                "SELECT * FROM Profile WHERE username = ?",
                username,
                rsh); 
Debug.print(StringUtil.paramString(profile));
            return profile;

        } finally {
            DbUtils.close(conn);            
        }
    }

    //----

    /** */
    @SuppressWarnings("unused")
    public static void main(String[] args) throws Exception {
        ProfileManager um1 = new ProfileManager();
        ProfileManager um2 = new ProfileManager();  // cause exception
    }
}

/* */
