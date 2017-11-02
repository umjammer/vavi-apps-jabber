/*
 * Copyright (c) 2004 by Naohide Sano, All Rights Reserved.
 *
 * Programmed by Naohide Sano
 */

package vavi.apps.jabber.web.filter;

import java.io.IOException;
import java.security.Principal;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpSession;

import vavi.apps.jabber.exception.AuthenticationException;
import vavi.net.im.protocol.xmpp.JabberProtocol;
import vavi.util.Debug;


/**
 * Jabber 認証を用いた認証フィルタクラスです。
 *
 * @web.filter
 *  name="jabberAuthenticationFilter"
 * @web.filter-init-param
 * 	name="encoding"
 *	value="Windows-31J"
 * @web.filter-init-param
 * 	name="ignore"
 *	value="true"
 * @web.filter-mapping2
 * 	url-pattern="/main/*"
 *
 * @author <a href=mailto:vavivavi@yahoo.co.jp>Naohide Sano</a> (nsano)
 * @version 0.00 040224 nsano initial version <br>
 */
public class JabberAuthenticationFilter implements Filter {

    /** */
    protected FilterConfig filterConfig = null;

    /**
     * Take this filter out of service.
     */
    public void destroy() {
        this.filterConfig = null;
    }

    /**
     */
    public void doFilter(ServletRequest request,
                         ServletResponse response,
                         FilterChain chain)
        throws IOException, ServletException {

        RequestWrapper rw = null;

        try {
            rw = new RequestWrapper((HttpServletRequest) request);
        } catch (AuthenticationException e) {
            throw new ServletException(e);
        }

        // Pass control on to the next filter
        chain.doFilter(rw, response);
    }

    /** */
    private class RequestWrapper extends HttpServletRequestWrapper {

        JabberProtocol jabberClient;

        /** */
        RequestWrapper(HttpServletRequest request)
            throws AuthenticationException {

            super(request);

            HttpSession session = request.getSession(false);
            if (session == null) {
Debug.println("no session");
                throw new AuthenticationException("no session");
            }
            jabberClient = (JabberProtocol) session.getAttribute("jabberClient");
            if (jabberClient == null) {
Debug.println("no jabber client");
                throw new AuthenticationException("no jabber client");
            }
        }

        /** */
        public String getAuthType() {
            return "JabberAuthentication";
        }

        /** */
        public Principal getUserPrincipal() {
            return null;
        }

        /** */
        public boolean isUserInRole(String role) {
            return false;
        }

        /** */
        public String getRemoteUser() {
            if (jabberClient == null) {
                return null;
            } else {
                return jabberClient.getUser().getUsername();
            }
        }
    }

    /**
     * Place this filter into service.
     */
    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
    }
}

/* */
