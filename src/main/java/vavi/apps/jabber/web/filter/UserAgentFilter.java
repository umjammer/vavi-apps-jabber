/*
 * Copyright (c) 2003 by K Laboratory Co., Ltd., All Rights Reserved.
 *
 * Programmed by Naohide Sano
 */

package vavi.apps.jabber.web.filter;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.Enumeration;
import java.util.Vector;

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
 * User-Agent Header を置換するフィルタです。
 * 
 * @web.filter
 *  name="userAgentFilter"
 * @web.filter-init-param
 *  name="userAgent"
 *  value="DoCoMo/1.0/D505i/c20/TB/W20H10"
 * @web.filter-mapping2
 *  url-pattern="*.do"
 * 
 * @author <a href=mailto:sano-n@klab.co.jp>Naohide Sano (nsano)</a>
 * @version 0.00 040301 nsano initial version <br>
 */
public class UserAgentFilter implements Filter {

    /** */
    private static Log logger = LogFactory.getLog(UserAgentFilter.class);

    /** User Agent */
    private String userAgent;

    /**
     * The filter configuration object we are associated with.  If this value
     * is null, this filter instance is not currently configured.
     */
    protected FilterConfig filterConfig = null;

    /**
     * Take this filter out of service.
     */
    public void destroy() {
        this.userAgent = null;
        this.filterConfig = null;
    }

    /** */
    private static final String TARGET_HEADER_NAME = "user-agent";

    /**
     *
     * @param request The servlet request we are processing
     * @param response The servlet response we are creating
     * @param chain The filter chain we are processing
     *
     * @exception IOException if an input/output error occurs
     * @exception ServletException if a servlet error occurs
     */
    public void doFilter(ServletRequest request,
                         ServletResponse response,
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

        this.userAgent = filterConfig.getInitParameter("userAgent");
    }

    /** */
    private class RequestWrapper extends HttpServletRequestWrapper {

        /** */
        RequestWrapper(HttpServletRequest request) {
            super(request);
        }

        /** */
        public String getHeader(String name) {
            if (TARGET_HEADER_NAME.equalsIgnoreCase(name)) {
logger.debug("user-agent: " + userAgent);
                return userAgent;
            } else {
                return super.getHeader(name);
            }
        }

        /** */
        public Enumeration<?> getHeaders(String name) {
            if (TARGET_HEADER_NAME.equalsIgnoreCase(name)) {
logger.debug("user-agent: " + userAgent);
                Vector<String> headers = new Vector<>();
                headers.add(userAgent);
                return headers.elements();
            } else {
                return super.getHeaders(name);
            }
        }
    }
}

/* */
