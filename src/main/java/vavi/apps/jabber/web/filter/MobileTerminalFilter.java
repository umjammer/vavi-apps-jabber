/*
 * Copyright (c) 2003 by K Laboratory Co., Ltd., All Rights Reserved.
 *
 * Programmed by Naohide Sano
 */

package vavi.apps.jabber.web.filter;

import java.io.IOException;
import java.lang.ref.WeakReference;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


/**
 * 携帯端末用フィルタです。
 * 
 * @web.filter
 *  name="mobileTerminalFilter"
 * @web.filter-init-param
 *  name="foo" value="bar"
 * @web.filter-mapping2
 *  url-pattern="/imode/*.do"
 * 
 * @author <a href=mailto:sano-n@klab.co.jp>Naohide Sano (nsano)</a>
 * @version 0.00 040301 nsano initial version <br>
 */
public class MobileTerminalFilter implements Filter {

    /** */
    private static Log logger = LogFactory.getLog(MobileTerminalFilter.class);

    /**
     * The filter configuration object we are associated with.  If this value
     * is null, this filter instance is not currently configured.
     */
    protected FilterConfig filterConfig = null;

    /**
     * Take this filter out of service.
     */
    public void destroy() {
        this.filterConfig = null;
    }

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
        WeakReference<HttpServletRequestWrapper> wr = new WeakReference<HttpServletRequestWrapper>(rw);

        //
        String qs = ((HttpServletRequest)request).getQueryString();
        ((HttpServletResponse) response).sendRedirect(qs);

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
    }

    /** */
    private class RequestWrapper extends HttpServletRequestWrapper {
        /** */
        RequestWrapper(HttpServletRequest request) {
            super(request);
        }
    }
}

/* */
