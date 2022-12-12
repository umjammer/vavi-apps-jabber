/*
 * Copyright (c) 2003 by K Laboratory Co., Ltd., All Rights Reserved.
 *
 * Programmed by Naohide Sano
 */

package vavi.apps.jabber.web.filter;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


/**
 * DoCoMo の UID を置換するフィルタです。
 *
 * @web.filter
 * 	name="imodeUidFilter"
 * @web.filter-init-param
 *  name="method"
 *	value="always"
 * @web.filter-init-param
 * 	name="uid"
 *	value="00eGQwXqhTrA"
 * @web.filter-mapping2
 * 	url-pattern="*.do"
 *
 * @author <a href=mailto:sano-n@klab.co.jp>Naohide Sano (nsano)</a>
 * @version 0.00 040301 nsano initial version <br>
 */
public class ImodeUidFilter implements Filter {

    /** */
    private static Log logger = LogFactory.getLog(ImodeUidFilter.class);

    /** 最後にアクセスした UID を常に保持してそれで置換する方法 */
    public static final String METHOD_LAST = "last";

    /** web.xml ファイルで設定した値で置換する方法 */
    public static final String METHOD_ALWAYS = "always";

    /** "always" の時の uid */
    private String uid;

    /** "last" の時の uid */
    private String lastUid;

    /** 置換方法 */
    private String method;

    /**
     * The filter configuration object we are associated with.  If this value
     * is null, this filter instance is not currently configured.
     */
    protected FilterConfig filterConfig = null;

    /**
     * Take this filter out of service.
     */
    public void destroy() {
        this.method = null;
        this.filterConfig = null;
    }

    /** */
    private static final String NULL_UID = "NULLGWDOCOMO";

    /** */
    private static final Pattern docomoPattern = Pattern.compile(NULL_UID);

    /**
     *
     * @param request The servlet request we are processing
     * @param response The servlet response we are creating
     * @param chain The filter chain we are processing
     *
     * @exception IOException if an input/output error occurs
     * @exception ServletException if a servlet error occurs
     */
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain)
	throws IOException, ServletException {

        RequestWrapper rw = new RequestWrapper((HttpServletRequest) request);
        @SuppressWarnings("unused")
        WeakReference<HttpServletRequestWrapper> wr = new WeakReference<>(rw);

        // Pass control on to the next filter
        chain.doFilter(rw, response);
    }

    /**
     * Place this filter into service.
     *
     * @param filterConfig The filter configuration object
     */
    public void init(FilterConfig filterConfig) throws ServletException {

	this.filterConfig = filterConfig;

logger.debug("☆☆☆☆ filter init ☆☆☆☆");
        this.method = filterConfig.getInitParameter("method");
logger.debug("init.method: " + method);
        if (METHOD_ALWAYS.equals(method)) {
            this.uid = filterConfig.getInitParameter("uid");
logger.debug("init.uid: " + uid);
        }
    }

    /** */
    private class RequestWrapper extends HttpServletRequestWrapper {

        /** */
        RequestWrapper(HttpServletRequest request) {
            super(request);

            if (METHOD_LAST.equals(method)) {
                String uid = request.getParameter("uid");
                if (uid != null && !NULL_UID.equals(uid)) {
                    if (lastUid == null) {
                        lastUid = uid;
logger.debug("set last: " + lastUid);
                    } else if (!lastUid.equals(uid)) {
                        lastUid = uid;
logger.debug("set last: " + lastUid);
                    }
                }
            }
        }

        /** */
        public String getQueryString() {
            String query = super.getQueryString();
            if (query == null) {
                return null;
            }

            Matcher docomoMatcher = docomoPattern.matcher(query);
            if (METHOD_LAST.equals(method)) {
                if (lastUid != null) {
logger.debug("do last: " + lastUid);
                    return docomoMatcher.replaceAll(lastUid);
                } else {
logger.debug("no last");
                }
            } else if (METHOD_ALWAYS.equals(method)) {
logger.debug("do always: " + uid);
                return docomoMatcher.replaceAll(uid);
            }

            return query;
        }

        /** */
        public String getParameter(String name) {
            return super.getParameter(name);
        }

        /** */
        public String[] getParameterValues(String name) {
            String[] values = super.getParameterValues(name);
            return values;
        }

        /** */
        public Map<?, ?> getParameterMap() {
            Map<?, ?> map = super.getParameterMap();
            return map;
        }
    }
}

/* */
