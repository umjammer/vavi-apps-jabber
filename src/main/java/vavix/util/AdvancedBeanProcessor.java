/*
 * Copyright (c) 2004 by Naohide Sano, All Rights Reserved.
 *
 * Programmed by Naohide Sano
 */

package vavix.util;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.dbutils.BeanProcessor;

import vavi.util.Debug;


/**
 * AdvancedBeanProcessor.
 * 
 * @author <a href=mailto:vavivavi@yahoo.co.jp>Naohide Sano</a> (nsano)
 * @version 0.00 040211 nsano initial version <br>
 */
public class AdvancedBeanProcessor extends BeanProcessor {
    /** */
    @SuppressWarnings("unchecked")
    protected Object processColumn(ResultSet rs, int index, Class propType)
        throws SQLException {

        ResultSetMetaData meta = rs.getMetaData();
Debug.print(index + ": " + meta.getColumnName(index) + ": " + propType);

        if (propType.equals(Boolean.TYPE) || propType.equals(Boolean.class)) {
            if ((meta.getColumnType(index) == Types.CHAR) ||
                (meta.getColumnType(index) == Types.VARCHAR)) {
                String value = rs.getString(index);
                return new Boolean("true".equals(value) || "on".equals(value) ||
                                   "yes".equals(value));
            } else if ((meta.getColumnType(index) == Types.BIT) ||
                       (meta.getColumnType(index) == Types.TINYINT) ||
                       (meta.getColumnType(index) == Types.SMALLINT) ||
                       (meta.getColumnType(index) == Types.INTEGER) ||
                       (meta.getColumnType(index) == Types.BIGINT)) {
                int value = rs.getInt(index);
                return new Boolean(value != 0);
            }
        } else if (propType.equals(Calendar.class) &&
                   (meta.getColumnType(index) == Types.TIMESTAMP)) {
            Timestamp value = rs.getTimestamp(index);
            Calendar calendar = Calendar.getInstance();
            if (value != null) {
                calendar.setTime(new Date(value.getTime()));
            }
            return calendar;
        } else if (propType.equals(Date.class) &&
                   (meta.getColumnType(index) == Types.TIMESTAMP)) {
            Timestamp value = rs.getTimestamp(index);

//log.debug(meta.getColumnName(index) + ": value: " + value);
            Date date = (value != null) ? new Date(value.getTime()) : null;
            return date;
        }

        return super.processColumn(rs, index, propType);
    }
}

/* */
